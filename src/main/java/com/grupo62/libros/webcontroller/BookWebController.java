package com.grupo62.libros.webcontroller;

import java.util.Date;

import javax.validation.Valid;

import com.grupo62.libros.entity.Author;
import com.grupo62.libros.entity.Editorial;
import com.grupo62.libros.entity.Book;
import com.grupo62.libros.pojos.BookDto;
import com.grupo62.libros.service.AuthorService;
import com.grupo62.libros.service.EditorialService;
import com.grupo62.libros.service.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookWebController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final EditorialService editorialService;

    public BookWebController(BookService bookService, AuthorService authorService, EditorialService editorialService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.editorialService = editorialService;
    }

    @GetMapping()
    public String showBookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/book";
    }

    /* ----- Creacion nuevo libro ------- */
    @GetMapping("/form-book")
    public String getFormBook(BookDto bookDto, Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("editorials", editorialService.findAll());
        return "book/form-book";
    }

    @PostMapping("/new-book")
    public String addNewBook(@Valid BookDto bookDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "book/form-book";
        }

        Author author = authorService.findById(bookDto.getAuthorId());
        Editorial editorial = editorialService.findById(bookDto.getEditorialId());

        if (author == null || editorial == null) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "book/form-book";
        }
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(author);
        book.setEditorial(editorial);
        book.setCreatedAt(new Date());
        book.setUpdatedAt(new Date());
        bookService.save(book);
        return "redirect:/book";
    }
    /* ------ fin creacion nuevo libro */

    /* ----- Editar libro ----- */
    @GetMapping("/edit-book/{id}")
    public String updateFormBook(@PathVariable(name = "id") Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            model.addAttribute("books", bookService.findAll());
            return "book/book";
        }
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setAuthorId(book.getAuthor().getId());
        bookDto.setEditorialId(book.getEditorial().getId());
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("editorials", editorialService.findAll());
        return "book/update-book";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable(name = "id") Long id, BookDto bookDto, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("editorials", editorialService.findAll());
            return "book/form-book";
        }
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/book";
        }
        Author author = authorService.findById(bookDto.getAuthorId());
        Editorial editorial = editorialService.findById(bookDto.getEditorialId());
        if (author == null || editorial == null)
            return "redirect:/book";
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(author);
        book.setEditorial(editorial);
        bookService.save(book);
        return "redirect:/book";
    }
    /* --------- fin editar libro ------- */

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id, Model model){
        Book book = bookService.findById(id);
        if( book != null )
            bookService.delete(book);
        return "redirect:/book";

    }

    @RequestMapping("/{idBook}/ejemplares")
    public String showBookEjemplares(@PathVariable(name = "idBook") Long idBook, Model model){
        Book book = bookService.findById(idBook);
        model.addAttribute("ejemplares", book.getEjemplares());
        model.addAttribute("idBook", idBook);
        return "ejemplar/ejemplar";
    }

}
