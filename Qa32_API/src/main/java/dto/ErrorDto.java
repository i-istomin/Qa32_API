package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor


public class ErrorDto {

    String message;
    String details;
    int code;
}
