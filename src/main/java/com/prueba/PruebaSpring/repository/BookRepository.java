package com.prueba.PruebaSpring.repository;

import com.prueba.PruebaSpring.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
        ArrayList<Book> findByIsbn(String isbn);
        ArrayList<Book> findByTitle(String title);
        ArrayList<Book> findByAuthor(String author);
        ArrayList<Book> findByPublisher(String publisher);
}