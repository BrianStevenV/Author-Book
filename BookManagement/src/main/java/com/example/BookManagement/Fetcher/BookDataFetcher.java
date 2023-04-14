package com.example.BookManagement.Fetcher;

import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.DAO.Modules.Book;
import com.example.BookManagement.DAO.Repository.AuthorRepository;
import com.example.BookManagement.DAO.Repository.BookRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDataFetcher {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookRepository.findById(bookId).orElse(null);
        };
    }

    public DataFetcher<List<Book>> getAllBooksDataFetcher(){
        return dataFetchingEnvironment -> {
            Sort sort = Sort.by("title").ascending();
            return bookRepository.findAll(sort);
        };
    }

    public DataFetcher<Book> createBookDataFetcher(){
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String title = dataFetchingEnvironment.getArgument("title");
            String description = dataFetchingEnvironment.getArgument("description");
            String publicationDate = dataFetchingEnvironment.getArgument("datePublished");
            String authorId = dataFetchingEnvironment.getArgument("id");
            Optional<Author> author = authorRepository.findById(authorId);
            if(author.isPresent()){
                Book book = new Book(id,title, description, publicationDate, author.get());
                return bookRepository.save(book);
            }   else{
                throw new IllegalArgumentException("Author not found for id: " + authorId);
            }

        };
    }

    public DataFetcher<Book> updateBookDataFetcher(){
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String title = dataFetchingEnvironment.getArgument("title");
            String description = dataFetchingEnvironment.getArgument("description");
            String publicationDate = dataFetchingEnvironment.getArgument("datePublished");
            String authorId = dataFetchingEnvironment.getArgument("id");
            Optional<Author> author = authorRepository.findById(authorId);
            if(author.isPresent()){
                Book book = bookRepository.findById(id).orElse(null);
                if(book != null){
                    book.setTitle(title);
                    book.setDescription(description);
                    book.setDatePublished(publicationDate);
                    book.setAuthor(author.get());
                    return bookRepository.save(book);
                }   else{
                    return null;
                }
            }   else{
                throw new IllegalArgumentException("Author not found for id: " + authorId);
            }
        };
    }

    public DataFetcher<Book> deleteBookDataFetcher(){
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            Optional<Book> book = bookRepository.findById(id);
            if(book.isPresent()){
                bookRepository.delete(book.get());
                return book.get();
            }   else{
                return null;
            }
        };
    }

//    public DataFetcher<Book> deleteBookDataFetcher() {
//        return dataFetchingEnvironment -> {
//            String bookId = dataFetchingEnvironment.getArgument("id");
//            Book book = bookRepository.findById(bookId).orElse(null);
//            if (book != null) {
//                bookRepository.delete(book);
//                return book;
//            } else {
//                return null;
//            }
//        };
//    } Muestro otra forma de hacerlo, quise por que esta forma la habia hecho el metodo anterior

}
