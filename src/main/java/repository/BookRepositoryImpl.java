package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import model.Book;

public class BookRepositoryImpl implements BookRepository {
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT e FROM Book e", Book.class).getResultList();
        }
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Book.class, id));
        }
    }
}
