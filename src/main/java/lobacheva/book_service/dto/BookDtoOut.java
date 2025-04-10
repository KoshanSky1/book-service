package lobacheva.book_service.dto;

import lombok.Builder;

@Builder
public record BookDtoOut(
        Long id,
        Long vendorCode,
        String title,
        Integer year,
        String brand,
        Integer stock,
        Double price
) {
}