package com.grupo62.libros.pojos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class LibroDto {

    private Long id;

    @NotNull(message = "isb es obligatorio")
    private Long isbn;

    @NotBlank(message = "Titulo es obligatorio")
    private String titulo;

    @NotNull(message = "Anio es obligatorio")
    private Integer anio;

    @NotNull(message = "Ejemplares es obligatorio")
    private Integer ejemplares;

    @NotNull(message = "Date up es obligatorio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @NotNull(message = "Autor es obligatorio")
    private Long autorId;

    @NotNull(message = "Editorial es obligatorio")
    private Long editorialId;

    public LibroDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(Long editorialId) {
        this.editorialId = editorialId;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", titulo: " + this.titulo + ", anio: " + this.anio + ", ejemplares: "
                + this.ejemplares + ", createdAt: " + this.createdAt + ", autorId: " + this.autorId + ", editorialId: "
                + this.editorialId;
    }
}
