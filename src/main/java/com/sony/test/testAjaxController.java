package com.sony.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sony.mts.entity.Employee;
import com.sony.mts.service.EmployeeService;

@Controller
@RequestMapping("/test")
public class testAjaxController {

	@Autowired
	EmployeeService employeeService;

	@ResponseBody
	@RequestMapping("/employeeList")
	public List<Employee> list() {
		List<Employee> employeeList = employeeService.selectAllEmp();
		return employeeList;
	}

}
