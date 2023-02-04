package com.example.Rabota.Controller;


import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Employee;
import com.example.Rabota.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@PreAuthorize("hasAnyAuthority('MANAGER','USER', 'ADMIN')")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
public String GetRab(Model model)
    {
        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee",employee);
        return "Main";
    }
    @GetMapping("/Add/Employee")
    public String GetAddEmployee(Employee employee, Model model)
    {
        return "Add-Employee";
    }
    @PostMapping("/Add/Employee")
    public String blogPostAdd(@Valid Employee employee, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "Add-Employee";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }


    @GetMapping( path = "/Search/Employee")
    public String blogFilter(Model model)
    {
        return "Search-Employee";
    }

//    @PostMapping("/Search/Employee-result")
//    public String blogResult(@RequestParam String lastname, Model model)
//    {
//        List<Employee> emp = employeeRepository.findByLastname(lastname);
//        model.addAttribute("result", emp);
//        return "Search-Employee";
//    }
    @PostMapping("/Search/Employee")
    public String simpleSearch(@RequestParam String lastname, Model model)
    {
        List<Employee> emp = employeeRepository.findByLastnameContains(lastname);
        model.addAttribute("result", emp);
        return "Search-Employee";
    }
    @GetMapping("/blog/Employee/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model)


    {
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("employee",employee);
        return "Edit-Employee";
    }

    @PostMapping("/blog/Employee/{id}/Edit")
    public String blogPosyUpdate(
            @ModelAttribute("employee")@Valid  Employee employee, BindingResult bindingResult,
            @PathVariable("id") long id) {

        employee.setId(id);
        if (bindingResult.hasErrors()) {
            return "Edit-Employee";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }
    @PostMapping("/blog/Employee/{id}/Remove")
    public String blogEmployeeDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/";
    }
    @GetMapping("/blog/Employee/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> lis = new ArrayList<>();
        employee.ifPresent(lis::add);
        model.addAttribute("employee", lis);
        return "blog-EmployeeDetails";

    }

}
