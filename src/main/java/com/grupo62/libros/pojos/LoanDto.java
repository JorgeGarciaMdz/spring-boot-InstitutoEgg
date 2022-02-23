package com.grupo62.libros.pojos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class LoanDto {

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    @NotNull(message = "You must select a ejemplar")
    private Long idEjemplar;

    @NotNull(message = "You must select a customer")
    private Long idPartner;

    @NotNull(message = "You must select a book")
    private Long idBook;

    public LoanDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(Long idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public Long getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(Long idPartner) {
        this.idPartner = idPartner;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", loanDate: " + this.loanDate + ", returnDate: " + this.returnDate + ", idEjemplar: "
                + this.idEjemplar + ", idPartner: " + this.idPartner + ", idBook: " + this.idBook;
    }
}
