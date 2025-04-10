package lobacheva.book_service.mapper;

import lobacheva.book_service.dto.BookDtoOut;
import lobacheva.book_service.dto.NewBookDto;
import lobacheva.book_service.dto.UpdateBookDto;
import lobacheva.book_service.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBookFromNewBookDto(NewBookDto newBookDto);

    BookDtoOut toBookDto(Book book);

    List<BookDtoOut> toDtoList(List<Book> books);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateBook(UpdateBookDto updateBookDto, @MappingTarget Book bookToUpdate);
}