package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CustomerTable")
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String Address;
    private double salary;
}
