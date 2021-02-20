package com.chen.controller;

import com.chen.dao.DepartmentDao;
import com.chen.dao.EmployeeDao;
import com.chen.pojo.Department;
import com.chen.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @ClassName EmployeeController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/12 22:05
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //显示所以的员工
    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //添加员工
    @GetMapping("/emp")
    public String toAddPage(Model model) {

        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("=======>" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //去修改员工页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){

        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    //修改员工
    @PostMapping("/updateEmp")
    public String UpdateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
