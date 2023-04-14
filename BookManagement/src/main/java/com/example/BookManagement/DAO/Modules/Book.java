package com.example.BookManagement.DAO.Modules;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
//Open the object with Lombok.
public class Book {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "datePublished")
    private String datePublished;
    @Column(name = "author")
    private Author author;
}
