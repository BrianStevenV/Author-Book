package com.example.BookManagement.Service;

import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.DAO.Repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorManagementService implements CrudService<Author>{
    private static final Field[] FIELDS = Author.class.getDeclaredFields();
    private final AuthorRepository authorRepository;

    public AuthorManagementService(final AuthorRepository authorRepository) { this.authorRepository = authorRepository; }
    @Transactional
    public Author createAuthor(final String id, final String name, final String lastName, final String dateBirth){
        final Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setLastName(lastName);
        author.setDateBirth(dateBirth);
        return this.authorRepository.save(author);
    }

    @Transactional
    @Override
    public Optional<Author> findById(String id){
        return this.authorRepository.findById(id);
    }
    @Transactional
    @Override
    public List<Author> findAll(Integer count){
        return this.authorRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void deleteById(String id){
        this.authorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<Author> updateEach(String id) {
        //this method update each  data if user want
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            for (Field field : FIELDS) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(author);
                    if (value != null) {
                        field.set(author, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            authorRepository.save(author);
            return Optional.of(author);
        }
        return Optional.empty();
    }
}
