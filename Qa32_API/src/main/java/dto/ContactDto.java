package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor

public class ContactDto {

    String address;
    String description;
    String email;
    String name;
    String lastName;
    String phone;
    int id;

}
