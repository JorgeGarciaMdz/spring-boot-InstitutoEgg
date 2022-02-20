package com.grupo62.libros.webcontroller;

import javax.validation.Valid;
import com.grupo62.libros.entity.Author;
import com.grupo62.libros.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorWebController {

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public String showAuthorList(Model model) {
        System.out.println("\n-------- author --------\n");
        model.addAttribute("authors", authorService.findAll());
        return "/author/author";
    }

    /* -------- dos pasos para la creacion ----------- */
    @GetMapping("/new")
    public String showSingUpForm(Author author) {
        return "/author/form-author";
    }

    @PostMapping("/author-create")
    public String createAuthor(@Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/author/form-author";

        authorService.create(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }
    /* ---------- fin creacion --------- */

    /* ----- dos pasos para edicion ----- */

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            model.addAttribute("authors", authorService.findAll());
            return "redirect:/author";
        }

        model.addAttribute("author", author);
        return "/author/update-author";
    }

    @PostMapping("/author-update/{id}")
    public String updateAuthor(@PathVariable(name = "id") Long id, @Valid Author author, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "/author/update-author";
        }
        authorService.update(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }
    /* ------- fin edicion ------- */

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable(name = "id") Long id, Model model){
        Author author = authorService.findById(id);
        if( author != null )
            authorService.delete(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }
}
