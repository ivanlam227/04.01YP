package com.example.Rabota.Controller;

import com.example.Rabota.Models.Avtosalon;
import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Driver;
import com.example.Rabota.Models.Vy;
import com.example.Rabota.repo.DriverRepository;
import com.example.Rabota.repo.VyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
@Controller
public class PravaController {
    @Autowired
    private VyRepository vyRepository;
    @Autowired
    private DriverRepository driverRepository;


    @PostMapping("driver/{driver_id}/remove")
    public String blogPostDell(@PathVariable("driver_id") Long driver_id, Model model)
    {
        var dr = driverRepository.findById(driver_id).orElseThrow();
        dr.setVy(null);
        driverRepository.save(dr);

        Iterable<Vy> vys = vyRepository.findAll();
        model.addAttribute("vys", vys);
        Iterable<Driver> driver = driverRepository.findAll();
        model.addAttribute("driver", driver);

        return "redirect:/driver";
    }


    @GetMapping("/driver")
    public String MainGet(Model model){
        Iterable<Vy> vys = vyRepository.findAll();
        model.addAttribute("vys", vys);
        Iterable<Driver> driver = driverRepository.findAll();
        model.addAttribute("driver", driver);
        return "Add-Vy";
    }

    @GetMapping("/driver/add")
    public String driverGetAdd(@ModelAttribute("vy") Vy vy, Model model)
    {
        Iterable<Vy> vys = vyRepository.findAll();
        ArrayList<Vy> vyArrayList = new ArrayList<>();
        for (Vy vyys: vys) {
            if(vyys.getOwner()==null) {
                vyArrayList.add(vyys);
            }
        }
        model.addAttribute("vys",vyArrayList);
        return "redirect:/driver";
    }
    @PostMapping("/driver/add")
    public String blogPostAdd(@ModelAttribute("vys") @Valid Driver driver, BindingResult bindingResult, @RequestParam String name, @RequestParam String number, Model model)
    {
        try{
            var vy =vyRepository.findByNumber(number);
            driver.setVy(vy);
            if(bindingResult.hasErrors()){return "Add-Vy";}
            driverRepository.save(driver);
            return "redirect:/driver";
        }catch (Exception ex){
            return "redirect:/driver";
        }
    }
    @PostMapping("/newVy")
    public String vyPostAdd(@ModelAttribute("vy") @Valid Vy vv, BindingResult bindingResult,
                              Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/newVy";
        }
        vyRepository.save(vv);
        return "redirect:/driver";
    }
@GetMapping("/Add-Vy")
    public String AddVy(Model model)
    {
        Iterable<Vy> vv = vyRepository.findAll();
        model.addAttribute("vv", vv);
        return "Add-Vy";
    }
@GetMapping("/newVy")
    public String newVy(Vy vy,Model model)
    {
        return "Vy";
    }


}
