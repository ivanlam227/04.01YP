package com.example.Rabota.Controller;


import com.example.Rabota.Models.Postavka;
import com.example.Rabota.repo.PostavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
@Controller
public class PostavkaController {
    @Autowired
    private PostavkaRepository postavkaRepository;

    @GetMapping("/Postavka")
    public String GetRab(Model model)
    {
        Iterable<Postavka> postavka = postavkaRepository.findAll();
        model.addAttribute("Postavka",postavka);
        return "Postavka";
    }


    @GetMapping("/Add/Postavka")
    public String GetAddPostavka(Model model)
    {
        model.addAttribute("postavka", new Postavka());
        return "Add-Postavka";
    }


    @PostMapping("/Add/Postavka")
    public String blogPostAdd(@Valid Postavka postavka, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "Add-Postavka";
        }
        postavkaRepository.save(postavka);
        return "redirect:/Postavka";
    }


    @GetMapping( path = "/Search/Postavka")
    public String blogFilter(Model model)
    {
        return "Search-Postavka";
    }

    //    @PostMapping("/Search/Client-result")
//    public String blogResult(@RequestParam String lastnamec, Model model)
//    {
//        List<Client> cl = clientRepository.findByLastnamec(lastnamec);
//        model.addAttribute("result", cl);
//        return "Search-Client";
//    }
    @PostMapping("/Search/Postavka")
    public String simpleSearch(@RequestParam String NameAvto, Model model)
    {
        List<Postavka> cl = postavkaRepository.findByNameAvtoContains(NameAvto);
        model.addAttribute("result", cl);
        return "Search-Client";
    }
    @GetMapping("/blog/Postavka/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Postavka postavka = postavkaRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid postavka Id" + id));
        model.addAttribute("postavka",postavka);
        return "Edit-Postavka";
    }

    @PostMapping("/blog/Postavka/{id}/Edit")
    public String blogPosyUpdate(
            @ModelAttribute("postavka")@Valid  Postavka postavka, BindingResult bindingResult,
            @PathVariable("id") long id) {

        postavka.setId(id);
        if (bindingResult.hasErrors()) {
            return "Edit-Client";
        }
        postavkaRepository.save(postavka);
        return "redirect:/Postavka";
    }
    @PostMapping("/blog/Postavka/{id}/Remove")
    public String blogEmployeeDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Postavka postavka = postavkaRepository.findById(id).orElseThrow();
        postavkaRepository.delete(postavka);
        return "redirect:/Postavka";
    }


    @GetMapping("/blog/Postavka/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Postavka> postavka = postavkaRepository.findById(id);
        ArrayList<Postavka> lis = new ArrayList<>();
        postavka.ifPresent(lis::add);
        model.addAttribute("postavka", lis);
        return "blog-PostavkaDetails";

    }
}
