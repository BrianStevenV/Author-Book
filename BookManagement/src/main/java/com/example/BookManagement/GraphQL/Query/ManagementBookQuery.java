package com.example.BookManagement.GraphQL.Query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.BookManagement.DAO.Modules.Book;
import com.example.BookManagement.Service.AuthorManagementService;
import com.example.BookManagement.Service.BookManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManagementBookQuery implements GraphQLQueryResolver {
    @Autowired
    private BookManagementService bookManagementService;

    public Optional<Book> findByIdBookQuery(String id){
        return bookManagementService.findById(id);
    }

    public List<Book> findByAllBookQuery(final Integer count){
        return bookManagementService.findAll(count);
    }


}
