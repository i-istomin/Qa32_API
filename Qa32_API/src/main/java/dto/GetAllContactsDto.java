package dto;

import lombok.*;

import java.util.List;



import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor

public class GetAllContactsDto {

   List<ContactDto> contacts;
}
