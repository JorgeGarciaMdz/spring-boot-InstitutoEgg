package com.grupo62.libros.pojos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BookDto {

    @NotNull(message = "ISBN is required")
    private Long isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Publication date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;

    @NotNull(message = "Author is required")
    private Long authorId;

    @NotNull(message = "Editorial is required")
    private Long editorialId;

    private Long bookId;

    public BookDto() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(Long editorialId) {
        this.editorialId = editorialId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return ", title: " + this.title + ", isbn: " + this.isbn + ", publicationDate: " + this.publicationDate
                + ", authorId: " + this.authorId + ", editorialId: " + this.editorialId + ", bookId: " + this.bookId;
    }
}
