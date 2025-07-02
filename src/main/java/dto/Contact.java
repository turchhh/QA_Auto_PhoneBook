package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Contact {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;
}
