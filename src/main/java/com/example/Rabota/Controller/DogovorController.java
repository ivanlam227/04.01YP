package com.example.Rabota.Controller;

import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Client;
import com.example.Rabota.Models.Dogovor;
import com.example.Rabota.Models.Employee;
import com.example.Rabota.repo.CarRepository;
import com.example.Rabota.repo.ClientRepository;
import com.example.Rabota.repo.DogovorRepository;
import com.example.Rabota.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;
@PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
@Controller
public class DogovorController {

    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public DogovorRepository dogovorRepository;
    @Autowired
    public CarRepository carRepository;

    @GetMapping("/dogovor")
    public String Main( Model model){

        Iterable<Dogovor> dogovors = dogovorRepository.findAll();
        model.addAttribute("dogovors", dogovors);
        Dogovor dogovor = new Dogovor();
        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("dogovor", dogovor);
//
//        Dogovor dogovor = new Dogovor();
//        model.addAttribute("dogovor",dogovor);
        model.addAttribute("employee",employee);
        Iterable<Car> car = carRepository.findAll();
        model.addAttribute("car",car);
        Iterable<Client> client = clientRepository.findAll();
       model.addAttribute("client",client);
//        return "Dogovor";
       // Iterable<Dogovor> dogovor = dogovorRepository.findAll();
        model.addAttribute("dogovor",dogovor);
        return "Dogovor";
    }
//    @GetMapping("/dogovor/add")
//    public String ProductAdd(Dogovor dogovor,Model model)
//    {
//        return "Dogovor";
//    }

//    @GetMapping("/dogovor/add")
//    public String registration( Model model){
//    Iterable<Employee> employee = employeeRepository.findAll();
//
//        Dogovor dogovor = new Dogovor();
//        model.addAttribute("dogovor",dogovor);
//        model.addAttribute("employee",employee);
//        Iterable<Car> car = carRepository.findAll();
//        model.addAttribute("car",car);
//        Iterable<Client> client = clientRepository.findAll();
//        model.addAttribute("client",client);
//        return "Dogovor";
//}

    @PostMapping("/dogovor/add")
    public String blogPostAdd( Dogovor dogovor,
                               @RequestParam long clientid,
                               @RequestParam long employerid,
                               @RequestParam long carid,
                               Model model){
      //  dogovor.setClient(new Client());
        //dogovor.setEmployee(new Employee());
        Client client = clientRepository.findById(clientid).orElseThrow();
        Employee employee = employeeRepository.findById(employerid).orElseThrow();
        Car car = carRepository.findById(carid).orElseThrow();
        dogovor.setClient(client);
        dogovor.setEmployee(employee);
        dogovor.setCarss(car);
//        Iterable<Client> client = clientRepository.findAll();
//        model.addAttribute("client", client);
//        Iterable<Car> carss = carRepository.findAll();
//        model.addAttribute("carss", carss);
//        Iterable<Employee> employee = employeeRepository.findAll();
//        model.addAttribute("employee", employee);
        dogovorRepository.save(dogovor);
        return "redirect:/dogovor";
    }
    @GetMapping("/Dogovor/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Dogovor> dogovor = dogovorRepository.findById(id);
        ArrayList<Dogovor> res = new ArrayList<>();
        dogovor.ifPresent(res::add);
        model.addAttribute("dogovor", res);
        if(!dogovorRepository.existsById(id))
        {
            return "redirect:/MainTovar";
        }
        return "Dogovor-details";
    }

    @GetMapping("/Dogovor/{id}/edit")
    public String blogEdit(@PathVariable("id")Long id,
                           Model model)
    {
        Dogovor dogovor = dogovorRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid arenda Id" + id));
        model.addAttribute("dogovor",dogovor);
        Iterable<Client> client = clientRepository.findAll();
        model.addAttribute("client",client);
        Iterable<Car> carss = carRepository.findAll();
        model.addAttribute("carss",carss);
        Iterable<Employee> employee = employeeRepository.findAll();
        model.addAttribute("employee",employee);
        return "Edit-Dogovor";

    }

    @PostMapping("/Dogovor/{id}/edit")
    public String blogEdit(
            @ModelAttribute("dogovor")@Valid  Dogovor dogovor, BindingResult bindingResult,
            @PathVariable("id") long id) {

        dogovor.setId(id);
        if (bindingResult.hasErrors()) {
            return "Edit-Dogovor";
        }
        dogovorRepository.save(dogovor);
        return "redirect:/";
    }


    @PostMapping("/Dogovor/{id}/remove")
    public String blogEmployeeDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Dogovor dogovor = dogovorRepository.findById(id).orElseThrow();
        dogovorRepository.delete(dogovor);
        return "redirect:/dogovor";
    }
    }
