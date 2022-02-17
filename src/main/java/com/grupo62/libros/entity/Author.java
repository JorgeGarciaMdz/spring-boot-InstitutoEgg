package com.grupo62.libros.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "It cant be empty")
    @Length(min = 3, max = 100, message = "The length must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "It cant be empty")
    @Length(min = 3, max = 100, message = "The length must be between 3 and 100 characters")
    private String lastname;

    @NotBlank(message = "It cant be empty")
    @Length(min = 10, max = 150, message = "The length must be between 10 and 150 characters")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat
    private Date updatedAt;

    @DateTimeFormat
    private Date deletedAt;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Id: " + this.Id + ", name: " + this.name + ", lastname: " + this.lastname +
                ", email: " + this.email + ", createdAt: " + this.createdAt;
    }
}
