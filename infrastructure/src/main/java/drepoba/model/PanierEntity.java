package drepoba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Panier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="command_id")
    private CommandEntity command;
    private Date datecreated;
    private Date updatedate;
}
