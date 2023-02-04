package com.example.Rabota.Controller;


import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Client;
import com.example.Rabota.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/Client")
    public String GetRab(Model model)
    {
        Iterable<Client> client = clientRepository.findAll();
        model.addAttribute("Client",client);
        return "Client";
    }
    @GetMapping("/Add/Client")
    public String GetAddClient(Client client, Model model)
    {
        return "Add-Client";
    }
    @PostMapping("/Add/Client")
    public String blogPostAdd(@Valid Client client, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "Add-Client";
        }
        clientRepository.save(client);
        return "redirect:/Client";
    }


    @GetMapping( path = "/Search/Client")
    public String blogFilter(Model model)
    {
        return "Search-Client";
    }

    //    @PostMapping("/Search/Client-result")
//    public String blogResult(@RequestParam String lastnamec, Model model)
//    {
//        List<Client> cl = clientRepository.findByLastnamec(lastnamec);
//        model.addAttribute("result", cl);
//        return "Search-Client";
//    }
    @PostMapping("/Search/Client")
    public String simpleSearch(@RequestParam String lastnamec, Model model)
    {
        List<Client> cl = clientRepository.findByLastnamecContains(lastnamec);
        model.addAttribute("result", cl);
        return "Search-Client";
    }
    @GetMapping("/blog/Client/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Client client = clientRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid client Id" + id));
        model.addAttribute("client",client);
        return "Edit-Client";
    }

    @PostMapping("/blog/Client/{id}/Edit")
    public String blogPosyUpdate(
            @ModelAttribute("client")@Valid  Client client, BindingResult bindingResult,
            @PathVariable("id") long id) {

        client.setId(id);
        if (bindingResult.hasErrors()) {
            return "Edit-Client";
        }
        clientRepository.save(client);
        return "redirect:/Client";
    }
    @PostMapping("/blog/Client/{id}/Remove")
    public String blogEmployeeDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/Client";
    }
    @GetMapping("/blog/Client/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Client> employee = clientRepository.findById(id);
        ArrayList<Client> lis = new ArrayList<>();
        employee.ifPresent(lis::add);
        model.addAttribute("client", lis);
        return "blog-ClientDetails";

    }

}
