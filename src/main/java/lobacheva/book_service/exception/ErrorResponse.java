package lobacheva.book_service.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String error
) {
}