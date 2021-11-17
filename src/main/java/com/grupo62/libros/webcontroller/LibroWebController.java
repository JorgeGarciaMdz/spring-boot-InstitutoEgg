package com.grupo62.libros.webcontroller;

import javax.validation.Valid;

import com.grupo62.libros.entity.Autor;
import com.grupo62.libros.entity.Editorial;
import com.grupo62.libros.entity.Libro;
import com.grupo62.libros.pojos.LibroDto;
import com.grupo62.libros.service.AutorService;
import com.grupo62.libros.service.EditorialService;
import com.grupo62.libros.service.LibroService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroWebController {

    private final LibroService libroService;
    private final AutorService autorService;
    private final EditorialService editorialService;

    public LibroWebController(LibroService libroService, AutorService autorService, EditorialService editorialService) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.editorialService = editorialService;
    }

    @GetMapping()
    public String showLibroList(Model model) {
        model.addAttribute("libros", libroService.findAll());
        return "/libro/libro";
    }

    /* ----- Creacion nuevo libro ------- */
    @GetMapping("/form-libro")
    public String getFormLibro(LibroDto libroDto, Model model) {
        model.addAttribute("autors", autorService.findAll());
        model.addAttribute("editorials", editorialService.findAll());
        return "/libro/form-libro";
    }

    @PostMapping("/new-libro")
    public String addNewLibro(@Valid LibroDto libroDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("new libro has errors: " + result.toString());
            model.addAttribute("autors", autorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "/libro/form-libro";
        }

        Autor autor = autorService.findById(libroDto.getAutorId());
        Editorial editorial = editorialService.findById(libroDto.getEditorialId());

        if (autor == null || editorial == null) {
            model.addAttribute("autors", autorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "/libro/form-libro";
        }
        Libro libro = new Libro();
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setCreatedAt(libroDto.getCreatedAt());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setIsbn(libroDto.getIsbn());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroService.saveLibro(libro);
        return "redirect:/libro";
    }
    /* ------ fin creacion nuevo libro */

    /* ----- Editar libro ----- */
    @GetMapping("/edit-libro/{id}")
    public String updateFormLibro(@PathVariable(name = "id") Long id, Model model) {
        Libro libro = libroService.findById(id);
        if (libro == null) {
            model.addAttribute("libros", libroService.findAll());
            return "/libro/libro";
        }
        LibroDto libroDto = new LibroDto();
        libroDto.setId(libro.getId());
        libroDto.setIsbn(libro.getIsbn());
        libroDto.setTitulo(libro.getTitulo());
        libroDto.setAnio(libro.getAnio());
        libroDto.setEjemplares(libro.getEjemplares());
        libroDto.setCreatedAt(libro.getCreatedAt());
        libroDto.setAutorId(libro.getAutor().getId());
        libroDto.setEditorialId(libro.getEditorial().getId());
        model.addAttribute("libroDto", libroDto);
        model.addAttribute("autors", autorService.findAll());
        model.addAttribute("editorials", editorialService.findAll());
        System.out.println(libroDto.toString());
        return "/libro/update-libro";
    }

    @PostMapping("/update-libro/{id}")
    public String updataLibro(@PathVariable(name = "id") Long id, LibroDto libroDto, BindingResult result,
            Model model) {
        System.out.println(libroDto.toString());
        if (result.hasErrors()) {
            System.out.println("new libro has errors: " + result.toString());
            model.addAttribute("autors", autorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "/libro/form-libro";
        }
        Libro libro = libroService.findById(id);
        if (libro == null) {
            return "redirect:/libro";
        }
        Autor autor = autorService.findById(libroDto.getAutorId());
        Editorial editorial = editorialService.findById(libroDto.getEditorialId());
        if (autor == null || editorial == null)
            return "redirect:/libro";
        libro.setIsbn(libroDto.getIsbn());
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setCreatedAt(libroDto.getCreatedAt());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroService.saveLibro(libro);
        return "redirect:/libro";
    }
    /* --------- fin editar libro ------- */

    @GetMapping("/delete/{id}")
    public String deleteLibro(@PathVariable(name = "id") Long id, Model model){
        Libro libro = libroService.findById(id);
        if( libro != null )
            libroService.delete(libro);
        return "redirect:/libro";

    }

}
