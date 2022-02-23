package com.grupo62.libros.webcontroller;

import java.util.Date;

import javax.validation.Valid;

import com.grupo62.libros.entity.Book;
import com.grupo62.libros.entity.Ejemplar;
import com.grupo62.libros.entity.Loan;
import com.grupo62.libros.pojos.LoanDto;
import com.grupo62.libros.service.PartnerService;
import com.grupo62.libros.service.EjemplarService;
import com.grupo62.libros.service.BookService;
import com.grupo62.libros.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan")
public class LoanWebController {

  @Autowired
  private LoanService loanService;

  @Autowired
  private PartnerService partnerService;

  @Autowired
  private BookService bookService;

  @Autowired
  private EjemplarService ejemplarService;

  @GetMapping
  public String showloans(Model model) {
    model.addAttribute("loans", loanService.findAll());
    return "loan/loan.html";
  }

  @GetMapping("/new")
  public String showFormLoan(Model model, LoanDto loanDto) {
    model.addAttribute("loanDto", loanDto);
    model.addAttribute("partners", partnerService.findAll());
    model.addAttribute("books", bookService.findAll());
    return "loan/form-loan";
  }

  @PostMapping("/loan-create")
  public String createLoan(@Valid LoanDto loanDto, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("loanDto", loanDto);
      model.addAttribute("partners", partnerService.findAll());
      model.addAttribute("books", bookService.findAll());
      return "loan/form-loan";
    } else {
      Loan loan = new Loan();
      loan.setLoanDate(loanDto.getLoanDate());
      loan.setReturnDate(loanDto.getReturnDate());
      loan.setCreatedAt(new Date());
      loan.setPartner(partnerService.findById(loanDto.getIdPartner()));
      Ejemplar ejemplar = ejemplarService.findById(loanDto.getIdEjemplar());
      ejemplar.setAvailable(false);
      ejemplarService.save(ejemplar);
      loan.setEjemplar(ejemplar);
      loanService.save(loan);
      return "redirect:/loan";
    }
  }

  @GetMapping("/edit-loan/{id}")
  public String showFormUpdate(@PathVariable(name = "id") Long id, Model model) {
    Loan loan = loanService.findById(id);
    Book book = bookService.findByEjemplar(loan.getEjemplar());
    if (loan == null || book == null)
      return "redirect:/loan";
    LoanDto loanDto = new LoanDto();
    loanDto.setId(loan.getId());
    loanDto.setLoanDate(loan.getLoanDate());
    loanDto.setReturnDate(loan.getReturnDate());
    loanDto.setIdEjemplar(loan.getEjemplar().getId());
    loanDto.setIdPartner(loan.getPartner().getId());
    model.addAttribute("loanDto", loanDto);
    model.addAttribute("book", book);
    model.addAttribute("partnerFullName", loan.getPartner().getFullname());
    model.addAttribute("ejemplar", loan.getEjemplar());
    return "loan/update-loan";
  }

  @PostMapping("/edit-update/{id}")
  public String update(@PathVariable(name = "id") Long id, LoanDto loanDto, BindingResult result, Model model) {
    if (result.hasErrors()) {
      Loan loan = loanService.findById(id);
      Book book = bookService.findByEjemplar(loan.getEjemplar());
      model.addAttribute("loanDto", loanDto);
      model.addAttribute("book", book);
      model.addAttribute("partnerFullName", loan.getPartner().getFullname());
      model.addAttribute("ejemplar", loan.getEjemplar());
      return "loan/update-loan";
    }
    Loan loan = loanService.findById(id);
    loan.setLoanDate(loanDto.getLoanDate());
    loan.setReturnDate(loanDto.getReturnDate());
    loanService.save(loan);
    return "redirect:/loan";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable(name = "id") Long id){
    Loan loan = loanService.findById(id);
    if( loan != null ){
      loan.setDeletedAt(new Date());
      loan.getEjemplar().setAvailable(true);
      ejemplarService.save(loan.getEjemplar());
      loanService.save(loan);
    }
    return "redirect:/loan";
  }
}
