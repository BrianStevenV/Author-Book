package com.example.BookManagement.DAO.Repository;

import com.example.BookManagement.DAO.Modules.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
