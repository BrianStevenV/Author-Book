package com.example.BookManagement.Service;

import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.DAO.Modules.Book;
import com.example.BookManagement.DAO.Repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookManagementService implements CrudService<Book>{

    //Si hay errores, mirar que no sea por error de importaciones o de organizacion de paquetes.
    private static final Field[] FIELDS = Book.class.getDeclaredFields();
    private final BookRepository bookRepository;

    public BookManagementService(final BookRepository bookRepository) { this.bookRepository = bookRepository; }

    @Transactional
    public Book createBook(final String id, final String title, final String description, final String datePublished, final Author author){
        //logyc service layer
        final Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setDatePublished(datePublished);
        book.setAuthor(author);
        return this.bookRepository.save(book);
    }
    @Transactional
    @Override
    public Optional<Book> findById(String id){
        return this.bookRepository.findById(id);
    }
    @Transactional
    @Override
    public List<Book> findAll(Integer count){
        return this.bookRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void deleteById(String id){
        this.bookRepository.deleteById(id);
    }
    @Transactional
    @Override
    public Optional<Book> updateEach(String id) {
        //this method update each  data if user want
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            for (Field field : FIELDS) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(book);
                    if (value != null) {
                        field.set(book, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            bookRepository.save(book);
            return Optional.of(book);
        }
        return Optional.empty();
    }
}
