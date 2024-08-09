package com.example._05_kheang_kheangngichseang_jpa_hibernate_2_homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Transactional
public class BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Book insertBook(BookRequest book){
        Book bookToInsert = new Book();

        bookToInsert.setTitle(book.getTitle());
        bookToInsert.setDescription(book.getDescription());
        bookToInsert.setAuthor(book.getAuthor());
        bookToInsert.setPublicationYear(book.getPublicationYear());

        entityManager.persist(bookToInsert);
        return bookToInsert;
    }

    public Book getBookById(UUID id){
        return entityManager.find(Book.class, id);
    }

    public List<Book> getAllBooks() {
        return entityManager.createQuery("Select b from Book b", Book.class).getResultList();
    }

    public List<Book> getBooksByTitle(String title) {
        return entityManager.createNativeQuery("Select * FROM book WHERE title ILIKE '%"+title+"%'", Book.class).getResultList();
    }

    public Book updateBookById(UUID id, BookRequest book){
        Book bookToUpdate = entityManager.find(Book.class, id);
        entityManager.detach(bookToUpdate);

        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPublicationYear(book.getPublicationYear());

        return entityManager.merge(bookToUpdate);
    }

    public void deleteBookById(UUID id){
        Book bookToDelete = entityManager.find(Book.class, id);
        entityManager.remove(bookToDelete);
    }

}

