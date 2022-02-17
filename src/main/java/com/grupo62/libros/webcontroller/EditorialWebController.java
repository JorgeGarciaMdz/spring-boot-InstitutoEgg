package com.grupo62.libros.webcontroller;

import javax.validation.Valid;
import com.grupo62.libros.entity.Editorial;
import com.grupo62.libros.service.EditorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
public class EditorialWebController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping()
    public String showAutorList(Model model) {
        model.addAttribute("editorials", editorialService.findAll());
        return "/editorial/editorial";
    }

    /* -------- dos pasos para la creacion ----------- */
    @GetMapping("/new")
    public String showSingUpForm(Editorial editorial) {
        return "/editorial/form-editorial";
    }

    @PostMapping("/editorial-create")
    public String createEditorial(@Valid Editorial editorial, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/editorial/form-editorial";

        editorialService.create(editorial);
        model.addAttribute("editorials", editorialService.findAll());
        return "redirect:/editorial";
    }
    /* ---------- fin creacion --------- */

    /* ----- dos pasos para edicion ----- */

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, Model model) {
        Editorial editorial = editorialService.findById(id);
        if (editorial == null) {
            model.addAttribute("editorials", editorialService.findAll());
            return "redirect:/editorial";
        }

        model.addAttribute("editorial", editorial);
        return "/editorial/update-editorial";
    }

    @PostMapping("/editorial-update/{id}")
    public String updateEditorial(@PathVariable(name = "id") Long id, @Valid Editorial editorial, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            editorial.setId(id);
            return "/editorial/update-editorial";
        }
        editorialService.update(editorial);
        model.addAttribute("editorials", editorialService.findAll());
        return "redirect:/editorial";
    }
    /* ------- fin edicion ------- */

    @GetMapping("/delete/{id}")
    public String deleteEditorial(@PathVariable(name = "id") Long id, Model model){
        Editorial editorial = editorialService.findById(id);
        if( editorial != null )
            editorialService.delete(editorial);
        model.addAttribute("editorials", editorialService.findAll());
        return "redirect:/editorial";
    }
}
