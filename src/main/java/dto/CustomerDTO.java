package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private String id;
    private String name;
    private String Address;
    private double salary;
}
