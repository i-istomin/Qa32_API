package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor

public class AuthRequestDto {

    String email;
    String password;
}
