package lobacheva.book_service.repository;

import lobacheva.book_service.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b " +
            "WHERE (:year IS NULL OR b.year = :year) " +
            "AND (:title IS NULL OR b.title LIKE '%' || :title || '%') " +
            "AND (:brand IS NULL OR b.brand LIKE '%' || :brand || '%')",
            nativeQuery = true)
    Page<Book> findAllWithFilters(
            @Param("year") Integer year,
            @Param("title") String title,
            @Param("brand") String brand,
            Pageable pageable
    );
}