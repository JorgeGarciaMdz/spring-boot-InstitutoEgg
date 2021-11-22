package com.grupo62.libros.webcontroller;

import javax.validation.Valid;

import com.grupo62.libros.entity.Cliente;
import com.grupo62.libros.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteWebController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String showClientList(Model model){
        model.addAttribute("clientes", clienteService.findAll());
        return "/cliente/cliente";
    }

    /* --------- create Cliente ------ */
    @GetMapping("/nuevo")
    public String showSignUpForm(Cliente cliente){
        System.out.println("------------");
        return "/cliente/form-cliente";
    }

    @PostMapping("/cliente-create")
    public String createCliente(@Valid Cliente cliente, BindingResult result, Model model){
        if( result.hasErrors())
            return "/cliente/form-cliente";
        clienteService.saveCliente(cliente);
        model.addAttribute("clientes", clienteService.findAll());
        return "redirect:/cliente";
    }

    /* ------- end create Cliente ------- */

    /* ------- update Cliente ------- */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, Model model){
        Cliente cliente = clienteService.findById(id);
        if( cliente == null ){
            model.addAttribute("clientes", clienteService.findAll());
            return "redirect:/cliente";
        }
        model.addAttribute("cliente", cliente);
        return "/cliente/update-cliente";
    }

    @PostMapping("/cliente-update/{id}")
    public String updateCliente(@PathVariable(name = "id") Long id, @Valid Cliente cliente, BindingResult result, Model model){
        if( result.hasErrors()){
            cliente.setId(id);
            return "/cliente/update-cliente";
        }
        clienteService.saveCliente(cliente);
        model.addAttribute("clientes", clienteService.findAll());
        return "redirect:/cliente";
    }
    /* ------- end update Cliente ------- */

    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable(name = "id") Long id, Model model){
        Cliente cliente = clienteService.findById(id);
        if( cliente != null )
        clienteService.deleteCliente(cliente);

        model.addAttribute("clientes", clienteService.findAll());
        return "redirect:/cliente";
    }
}
