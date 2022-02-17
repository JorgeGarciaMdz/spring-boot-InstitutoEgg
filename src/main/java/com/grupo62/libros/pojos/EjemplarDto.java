package com.grupo62.libros.pojos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EjemplarDto {

    private Long id;

    @NotBlank(message = "It cant be empty")
    @Length(min = 3, max = 150, message = "The length must be between 3 and 150 characters")
    private String edition;

    @NotBlank(message = "It cant be empty")
    @Length(min = 2, max = 100, message = "The length must be between 3 and 100 characters")
    private String location;

    @NotNull
    private Long idBook;

    private Date createdAt;

    public EjemplarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", edicion: " + this.edition + ", location: " + this.location + ", idBook: "
                + this.idBook + ", createdAt: " + this.createdAt;
    }
}
