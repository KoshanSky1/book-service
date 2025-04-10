package lobacheva.book_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record NewBookDto(
        @Positive(message = "Vendor code must be positive")
        Long vendorCode,
        @NotBlank(message = "Title can not be blank")
        String title,
        @Positive(message = "Year must be positive")
        @Max(value = 2025, message = "The publication year must not be greater than the current year.")
        Integer year,
        @NotBlank(message = "Brand can not be blank")
        String brand,
        @Positive(message = "Stock code must be positive")
        Integer stock,
        @Positive(message = "Price must be positive")
        Double price
) {
}