package com.example.BookManagement.GraphQL.Query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.Service.AuthorManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ManagementAuthorQuery implements GraphQLQueryResolver {
    @Autowired
    private AuthorManagementService authorManagementService;
    public Optional<Author> findByIdAuthorQuery(final String id){
        return authorManagementService.findById(id);
    }

    public List<Author> findByAllAuthorQuery(final Integer count){
        return authorManagementService.findAll(count);
    }
}
