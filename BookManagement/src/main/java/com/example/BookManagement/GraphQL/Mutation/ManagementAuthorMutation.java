package com.example.BookManagement.GraphQL.Mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.Exception.FailedAttemptException;
import com.example.BookManagement.Service.AuthorManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ManagementAuthorMutation implements GraphQLMutationResolver {

    @Autowired
    private AuthorManagementService authorManagementService;
    public Author createAuthorQuery(final String id, final String name, final String lastName, final String dateBirth){
        return authorManagementService.createAuthor(id,name,lastName,dateBirth);
    }

    public Author updateAuthorQuery(String id){
        try{
            return authorManagementService.updateEach(id).get();
        } catch (NoSuchElementException e){
            throw new FailedAttemptException();
        }
    }

    public void deleteAuthorQuery(String id){
        authorManagementService.deleteById(id);
    }

}
