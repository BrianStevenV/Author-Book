package com.example.BookManagement.Fetcher;

import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.DAO.Repository.AuthorRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDataFetcher {
    @Autowired
    private AuthorRepository authorRepository;
    public DataFetcher<Author> getAuthorByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorRepository.findById(authorId).orElse(null);
        };
    }

    public DataFetcher<List<Author>> getAllAuthorsDataFetcher() {
        return dataFetchingEnvironment -> {
            Sort sort = Sort.by("lastName").ascending().and(Sort.by("firstName").ascending());
            return authorRepository.findAll(sort);
        };
    }

    public DataFetcher<Author> createAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            String firstName = dataFetchingEnvironment.getArgument("name");
            String lastName = dataFetchingEnvironment.getArgument("lastName");
            String dateOfBirth = dataFetchingEnvironment.getArgument("dateBirth");
            Author author = new Author(authorId,firstName, lastName, dateOfBirth);
            return authorRepository.save(author);
        };
    }

    public DataFetcher<Author> updateAuthorDataFetcher(){
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            String firstName = dataFetchingEnvironment.getArgument("name");
            String lastName = dataFetchingEnvironment.getArgument("lastName");
            String dateOfBirth = dataFetchingEnvironment.getArgument("dateBirth");
            Optional<Author> author = authorRepository.findById(authorId);
            if(author.isPresent()){
                Author authorResponse = author.get();
                authorResponse.setName(firstName);
                authorResponse.setLastName(lastName);
                authorResponse.setDateBirth(dateOfBirth);
                return authorRepository.save(authorResponse);
            }   else{
                return null;
            }
        };
    }

    public DataFetcher<Author> deleteAuthorDataFetcher(){
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            Optional<Author> author = authorRepository.findById(id);
            if(author.isPresent()){
                authorRepository.delete(author.get());
                return author.get();
            }   else{
                return null;
            }
        };
    }

}
