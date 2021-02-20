package com.chen.dao;

import com.chen.pojo.Department;
import com.chen.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EmployeeDao
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/1/12 15:23
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();//创建一个部门表

        employees.put(1001, new Employee(1001, "AA", "A2417359111@qq.com", 0, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "B2417359111@qq.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "C2417359111@qq.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "D2417359111@qq.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EE", "E2417359111@qq.com", 0, new Department(105, "后勤部")));
    }

    //主键自增
    private static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    //查询全波员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除员工通过id
    public void delete(Integer id) {
        employees.remove(id);
    }

}
