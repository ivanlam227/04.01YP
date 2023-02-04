package com.example.Rabota.Controller;

import com.example.Rabota.Models.Avtosalon;
import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Vy;
import com.example.Rabota.repo.AvtosalonRepository;
import com.example.Rabota.repo.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class NalihieController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AvtosalonRepository avtosalonRepository;


    @GetMapping("/Nalihie")
    private String Main(Model model){
        Iterable<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        Iterable<Avtosalon> avtosalons = avtosalonRepository.findAll();
        model.addAttribute("avtosalons", avtosalons);


        return "/Nalihie";
    }

    @PostMapping("/Nalihie/add")
    public String blogPostAdd(@RequestParam Long car, @RequestParam Long avtosalon, Model model)
    {
        Car car1 = carRepository.findById(car).orElseThrow();
        Avtosalon avtosalon1 = avtosalonRepository.findById(avtosalon).orElseThrow();
        car1.getAvtosalonList().add(avtosalon1);
        //university2.getStudents().add(student2);
        avtosalonRepository.save(avtosalon1);
        return "redirect:/Nalihie";
    }

    @PostMapping("Nalihie/{avtosalon_id}/{car_id}/remove")
    public String blogPostDell( @PathVariable("avtosalon_id") Long id_salon, @PathVariable("car_id") Long id_car, Model model)
    {
        Car car = carRepository.findById(id_car).orElseThrow();
        Avtosalon avtosalon = avtosalonRepository.findById(id_salon).orElseThrow();
        car.getAvtosalonList().remove(avtosalon);
        carRepository.save(car);
        return "redirect:/Nalihie";
    }


    @PostMapping("/newAdress")
    public String vyPostAdd(@ModelAttribute("avtosalon") @Valid Avtosalon avtosalon, BindingResult bindingResult,
                            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "Nalihie";
        }
        avtosalonRepository.save(avtosalon);
        return "redirect:/Nalihie";
    }
    @GetMapping("/Add-adress")
    public String Addadress(Model model)
    {
        Iterable<Avtosalon> avtosalons = avtosalonRepository.findAll();
        model.addAttribute("avtosalons", avtosalons);
        return "Nalihie";
    }
    @GetMapping("/newAdress")
    public String newadress(Avtosalon avtosalon,Model model)
    {
        return "Add-Adress";
    }
}
