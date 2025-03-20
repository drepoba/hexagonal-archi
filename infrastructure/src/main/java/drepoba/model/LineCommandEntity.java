package drepoba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "LigneCommand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineCommandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "command_id")
    private CommandEntity command;

    @ManyToOne
    @JoinColumn(name = "poduct_id")
    private ProductEntity product;

    private int quantity;
    private Date created;
    private Date updated;
}
