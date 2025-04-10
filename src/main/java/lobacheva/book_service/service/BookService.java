package lobacheva.book_service.service;

import jakarta.persistence.EntityNotFoundException;
import lobacheva.book_service.dto.UpdateBookDto;
import lobacheva.book_service.mapper.BookMapper;
import lobacheva.book_service.model.Book;
import lobacheva.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public Page<Book> getAllBooks(Integer year, String title, String brand, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAllWithFilters(year, title, brand, pageable);
    }

    public Book getBookByBookId(Long bookId) {
        Book book = getBookById(bookId);
        log.info("Book with id {} found", bookId);
        return book;
    }

    @Transactional
    public Book createBook(Book book) {
        Book bookSaved = bookRepository.save(book);
        log.info("Added a new book with id '{}'", bookSaved.getId());
        return bookSaved;
    }

    @Transactional
    public Book updateBook(Long bookId, UpdateBookDto dto) {
        final Book book = getBookById(bookId);
        String oldTitle = book.getTitle();
        String oldBrand = book.getBrand();
        bookMapper.updateBook(dto, book);
        if (dto.title().isBlank()) {
            book.setTitle(oldTitle);
        }
        if (dto.brand().isBlank()) {
            book.setBrand(oldBrand);
        }
        Book updatedBook = bookRepository.save(book);
        log.info("Book with id '{}' was updated", bookId);
        return updatedBook;
    }

    @Transactional
    public void deleteBook(Long bookId) {
        log.info("Parking lot with id '{}' was deleted", bookId);
        bookRepository.deleteById(bookId);
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("Book with id '%s' was not found", id)));
    }

}