package lobacheva.book_service.controller;

import jakarta.validation.constraints.Positive;
import lobacheva.book_service.dto.BookDtoOut;
import lobacheva.book_service.dto.NewBookDto;
import lobacheva.book_service.dto.UpdateBookDto;
import lobacheva.book_service.mapper.BookMapper;
import lobacheva.book_service.model.Book;
import lobacheva.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public String getAllBooks(@RequestParam(required = false) Integer year,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String brand,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        log.info("---START GET ALL BOOKS ENDPOINT---");

        Page<Book> allBooks = bookService.getAllBooks(year, title, brand, page, size);
        model.addAttribute("allBooks", allBooks.getContent());
        model.addAttribute("totalPages", allBooks.getTotalPages());
        model.addAttribute("currentPage", allBooks.getNumber());
        model.addAttribute("currentSize", allBooks.getSize());
        model.addAttribute("year", year);
        model.addAttribute("title", title);
        model.addAttribute("brand", brand);
        return "books";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createBook(NewBookDto dto, Model model) {
        log.info("---START CREATE BOOK ENDPOINT---");
        bookService.createBook(bookMapper.toBookFromNewBookDto(dto));
        Page<Book> allBooks = bookService.getAllBooks(null, null, null, 0, 10);
        model.addAttribute("allBooks", allBooks.getContent());
        model.addAttribute("totalPages", allBooks.getTotalPages());
        model.addAttribute("currentPage", allBooks.getNumber());
        model.addAttribute("currentSize", allBooks.getSize());
        model.addAttribute("year", null);
        model.addAttribute("title", null);
        model.addAttribute("brand", null);
        return "books";
    }

    @GetMapping("/{bookId}")
    public String getBookById(@PathVariable("bookId") Long bookId,
                              Model model) {
        log.info("---START GET BOOK BY ID ENDPOINT---");
        BookDtoOut bookDtoOut = bookMapper.toBookDto(bookService.getBookByBookId(bookId));
        model.addAttribute("book", bookDtoOut);
        return "edit-book";
    }

    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable @Positive Long bookId,
                             UpdateBookDto dto) {
        log.debug("Updating book with id '{}'", bookId);
        bookService.updateBook(bookId, dto);
        return "redirect:/api/books";
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable @Positive Long bookId) {
        log.info("---START DELETE BOOK ENDPOINT---");
        bookService.deleteBook(bookId);
        return "redirect:/api/books";
    }

}