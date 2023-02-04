package com.example.Rabota.Controller;

import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Driver;
import com.example.Rabota.Models.Employee;
import com.example.Rabota.Models.Testdrive;
import com.example.Rabota.repo.CarRepository;
import com.example.Rabota.repo.DriverRepository;
import com.example.Rabota.repo.TestdriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasAnyAuthority('MANAGER', 'USER', 'ADMIN')")
@Controller
public class TestdriveController {
    @Autowired
    public CarRepository carRepository;
    @Autowired
    public TestdriveRepository testdriveRepository;
    @Autowired
    public DriverRepository driverRepository;

    @GetMapping("/testdrive")
    public String Main(Model model){
        Iterable<Testdrive> testdrive = testdriveRepository.findAll();
        model.addAttribute("testdrive",testdrive);
        Iterable<Car> car = carRepository.findAll();
        model.addAttribute("car",car);
        Iterable<Driver> driver = driverRepository.findAll();
        model.addAttribute("driver",driver);
        return "Testdrive";
    }

    @PostMapping("/testdrive/add")
    public String blogPostAdd(@RequestParam String data, @RequestParam String marks, @RequestParam String name, Model model)
    {

        Car car = carRepository.findByMarks(marks);
        Driver driver = driverRepository.findByName(name);
        Testdrive testdrive = new Testdrive(data,car, driver);
        //Testdrive testdrive1 = new Testdrive(data,driver);
        testdriveRepository.save(testdrive);
       // testdriveRepository.save(testdrive1);
        return  "redirect:/testdrive";
    }
    @PostMapping("/testdrive/{id}/Remove")
    public String blogEmployeeDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Testdrive testdrive = testdriveRepository.findById(id).orElseThrow();
        testdriveRepository.delete(testdrive);
        return "redirect:/testdrive";
    }
    @GetMapping("testdrive/{id}/edit")
    public String employeeEdit(@PathVariable("id")long id,
                               Model model)
    {
        Iterable<Car> car = carRepository.findAll();
        Iterable<Driver> driver = driverRepository.findAll();

        Testdrive testdrive = testdriveRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid students Id" + id));
        model.addAttribute("car", car);
        model.addAttribute("driver", driver);
        model.addAttribute("testdrive",testdrive);

        return "Testdrive-Edit";
    }

    @PostMapping("testdrive/{id}/edit")
    public String employeeUpdate(@ModelAttribute("testdrive")@Valid Testdrive testdrive, BindingResult bindingResult,
                                 @RequestParam String marks,
                                 @RequestParam String name,
                                 @PathVariable("id") long id) {

        testdrive.setId(id);
        if (bindingResult.hasErrors()) {
            Iterable<Driver> driver = driverRepository.findAll();
            Iterable<Car> car = carRepository.findAll();

            return "Testdrive-Edit";
        }
        testdrive.setCar(carRepository.findByMarks(marks));
        testdrive.setDriver(driverRepository.findByName(name));
        testdriveRepository.save(testdrive);

        return "Testdrive";
    }
}
