package com.atos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atos.model.Employee;
import com.atos.model.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao dao;  // will inject dao from XML file

	/*
	 * "command" is a reserved request attribute
	 *  which is used to display object data into form
	 */
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command", new Employee());
		return "empform";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Employee emp) {
		dao.save(emp);
		return "redirect:/viewemp";
	}

	/* It provides list of employees in model object */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Employee> list = dao.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}
}
