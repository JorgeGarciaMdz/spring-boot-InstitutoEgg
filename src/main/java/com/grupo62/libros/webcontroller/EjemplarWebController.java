package com.grupo62.libros.webcontroller;

import java.util.Date;

import javax.validation.Valid;

import com.grupo62.libros.entity.Ejemplar;
import com.grupo62.libros.entity.Book;
import com.grupo62.libros.pojos.EjemplarDto;
import com.grupo62.libros.service.EjemplarService;
import com.grupo62.libros.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ejemplar")
public class EjemplarWebController {

    @Autowired
    private EjemplarService ejemplarService;

    @Autowired
    private BookService bookService;

    @GetMapping("/{idBook}/new")
    public String showFormEjemplar(@PathVariable(name = "idBook") Long idBook, EjemplarDto ejemplarDto, Model model) {
        ejemplarDto.setIdBook(idBook);
        return "ejemplar/form-ejemplar";
    }

    @PostMapping("/ejemplar-create")
    public String createEjemplar(@Valid EjemplarDto ejemplarDto, BindingResult result, Model model) {
        if (result.hasErrors())
            return "ejemplar/form-ejemplar";

        Book book = bookService.findById(ejemplarDto.getIdBook());
        if (book == null)
            return "redirect:/book/" + ejemplarDto.getIdBook() + "/ejemplares";
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setEdition(ejemplarDto.getEdition());
        ejemplar.setLocation(ejemplarDto.getLocation());
        ejemplar.setAvailable(true);
        ejemplar.setCreatedAt(new Date());
        ejemplar.setUpdatedAt(new Date());
        ejemplarService.save(ejemplar);

        book.addEjemplares(ejemplar);
        bookService.save(book);
        return "redirect:/book/" + ejemplarDto.getIdBook() + "/ejemplares";
    }

    @GetMapping("/{idBook}/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") Long id, @PathVariable(name = "idBook") Long idBook,
            Model model) {
        Ejemplar ejemplar = ejemplarService.findById(id);
        if (ejemplar == null)
            return "redirect:/book";
        EjemplarDto ejemplarDto = new EjemplarDto();
        ejemplarDto.setId(ejemplar.getId());
        ejemplarDto.setEdition(ejemplar.getEdition());
        ejemplarDto.setCreatedAt(ejemplar.getCreatedAt());
        ejemplarDto.setLocation(ejemplar.getLocation());
        ejemplarDto.setIdBook(idBook);
        model.addAttribute("ejemplarDto", ejemplarDto);
        return "ejemplar/update-ejemplar";
    }

    @PostMapping("/ejemplar-update/{id}")
    public String updateEjemplar(@PathVariable(name = "id") Long id, @Valid EjemplarDto ejemplarDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            ejemplarDto.setId(id);
            return "ejemplar/update-ejemplar";
        }
        Ejemplar ejemplar = ejemplarService.findById(id);
        if (ejemplar == null)
            return "redirect:/book/" + ejemplarDto.getIdBook() + "/ejemplares";

        ejemplar.setEdition(ejemplarDto.getEdition());
        ejemplar.setLocation(ejemplarDto.getLocation());
        ejemplar.setAvailable(false);
        ejemplar.setUpdatedAt(new Date());
        ejemplarService.save(ejemplar);
        return "redirect:/book/" + ejemplarDto.getIdBook() + "/ejemplares";
    }

    @GetMapping("/{idBook}/delete/{id}")
    public String deleteEjemplar(@PathVariable(name = "id") Long id, @PathVariable(name = "idBook") Long idBook) {
        Ejemplar ejemplar = ejemplarService.findById(id);
        Book book = bookService.findById(idBook);
        if (ejemplar == null || book == null)
            return "redirect:/book/" + idBook + "/ejemplares";
        ejemplarService.delete(ejemplar);
        book.removeEjemplar(ejemplar);
        bookService.save(book);
        return "redirect:/book/" + idBook + "/ejemplares";
    }

}
