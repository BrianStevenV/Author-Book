package com.example.BookManagement.GraphQL.Mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.BookManagement.DAO.Modules.Author;
import com.example.BookManagement.DAO.Modules.Book;
import com.example.BookManagement.Exception.FailedAttemptException;
import com.example.BookManagement.Service.BookManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ManagementBookMutation implements GraphQLMutationResolver {
    /*Ventajas de tener un solo archivo para las Mutations:
    Simplifica la estructura del proyecto al reducir la cantidad de archivos.
    Es más fácil navegar y encontrar las Mutations que afectan tanto a Book como a Author en un solo archivo.

    Desventajas de tener un solo archivo para las Mutations:
    Si las Mutations son complejas, el archivo puede volverse largo y difícil de manejar.
    Puede ser difícil identificar y separar las Mutations que afectan solo a una entidad y no a la otra.

    Ventajas de tener dos archivos separados para las Mutations:
    Permite una mayor separación de responsabilidades en el proyecto.
    Hace que sea más fácil encontrar y mantener las Mutations que afectan solo a una entidad.

    Desventajas de tener dos archivos separados para las Mutations:
    Puede aumentar la complejidad del proyecto al tener más archivos.
    Puede requerir más esfuerzo para encontrar y mantener Mutations que involucren ambas entidades.*/
    @Autowired
    private BookManagementService bookManagementService;

    public Book createBookQuery(final String id, final String title, final String description, final String datePublished, final Author author){
        return bookManagementService.createBook(id, title, description, datePublished, author);
    }

    public Book updateBookQuery(String id){
        try{
            return bookManagementService.updateEach(id).get();
        }   catch (NoSuchElementException e){
            throw new FailedAttemptException();
        }
    }

    public void deleteBookQuery(String id){
        bookManagementService.deleteById(id);
    }
}
