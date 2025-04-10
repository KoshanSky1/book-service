package lobacheva.book_service.dto;

import lombok.Builder;

@Builder
public record UpdateBookDto(
        Long vendorCode,
        String title,
        Integer year,
        String brand,
        Integer stock,
        Double price
) {
}