//package com.example.Rabota.Controller;
//
//import com.example.Rabota.Models.Car;
//import com.example.Rabota.Models.Complect;
//import com.example.Rabota.repo.CarRepository;
//import com.example.Rabota.repo.ComplectRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class ComplectController {
//    @Autowired
//    public CarRepository carRepository;
//    @Autowired
//    public ComplectRepository complectRepository;
//    @GetMapping("/person")
//    public String Main(Model model){
//        Iterable<Complect> complect = complectRepository.findAll();
//        model.addAttribute("complect",complect);
//        return "person";
//    }
//
//    @PostMapping("/person/add")
//    public String blogPostAdd(@RequestParam String name, @RequestParam String street, Model model)
//    {
//        Complect complect = complectRepository.findByCompl(compl);
//        Car car = new Car(name, complect);
//        carRepository.save(car);
//        return "person";
//    }
//}
