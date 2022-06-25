package com.hackaton.demo.controller;

import com.hackaton.demo.dao.Employee;
import com.hackaton.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public ModelAndView getWelcomePage(
            @RequestParam(name="name", required = false, defaultValue = "Guest") String name,
            Map<String, Object> model
    ) {
        model.put("name",name);
        return new ModelAndView("welcome_page", model);
    }

    @GetMapping(path="/employees")
    public ModelAndView getEmployees(Map<String, Object> model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.put("employees", employees);
        return new ModelAndView("employees", model);
    }

    @GetMapping(path="/add_user")
    public ModelAndView getAddUserPage(Map<String, Object> model) {
        return new ModelAndView("add_user", model);
    }

    @PostMapping(path="/add_user")
    public String addEmployee(
            @RequestParam String name,
            @RequestParam String email
    ) {
        Employee employee = new Employee(name, email);
        employeeRepository.save(employee);
        return "Поздравляем! Вы добавили нового пользователя";
    }
}
