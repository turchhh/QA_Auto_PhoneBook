package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ContactsDto {
    private Contact[] contacts;
}
