package drepoba.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Command")
@Data
public class CommandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;
    private double totalCost;
    private Date created;
    private Date updated;
}
