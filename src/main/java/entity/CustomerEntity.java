package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerEntity {
    private String id;
    private String name;
    private String Address;
    private double salary;
}
