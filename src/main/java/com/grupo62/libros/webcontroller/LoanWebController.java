package com.grupo62.libros.webcontroller;

import java.util.Date;

import javax.validation.Valid;

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
}
