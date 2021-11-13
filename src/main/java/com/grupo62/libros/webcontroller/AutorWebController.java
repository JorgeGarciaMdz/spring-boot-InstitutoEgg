package com.grupo62.libros.webcontroller;

import javax.validation.Valid;
import com.grupo62.libros.entity.Autor;
import com.grupo62.libros.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autor")
public class AutorWebController {

    @Autowired
    private AutorService autorService;

    @GetMapping()
    public String showAutorList(Model model) {
        model.addAttribute("autores", autorService.findAll());
        return "/autor/autor";
    }

    /* -------- dos pasos para la creacion ----------- */
    @GetMapping("/nuevo")
    public String showSingUpForm(Autor autor) {
        return "/autor/form-autor";
    }

    @PostMapping("/autor-create")
    public String createUser(@Valid Autor autor, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/autor/form-autor";

        autorService.saveAutor(autor);
        model.addAttribute("autores", autorService.findAll());
        return "redirect:/autor";
    }
    /* ---------- fin creacion --------- */

    /* ----- dos pasos para edicion ----- */

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, Model model) {
        Autor autor = autorService.findById(id);
        if (autor == null) {
            model.addAttribute("autores", autorService.findAll());
            return "redirect:/autor";
        }

        model.addAttribute("autor", autor);
        return "/autor/update-autor";
    }

    @PostMapping("/autor-update/{id}")
    public String updateAutor(@PathVariable(name = "id") Long id, @Valid Autor autor, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            autor.setId(id);
            return "/autor/update-autor";
        }
        autorService.saveAutor(autor);
        model.addAttribute("autores", autorService.findAll());
        return "redirect:/autor";
    }
    /* ------- fin edicion ------- */

    @GetMapping("/delete/{id}")
    public String deleteAutor(@PathVariable(name = "id") Long id, Model model){
        Autor autor = autorService.findById(id);
        if( autor != null )
            autorService.deleteAutor(autor);
        model.addAttribute("autores", autorService.findAll());
        return "redirect:/autor";
    }
}
