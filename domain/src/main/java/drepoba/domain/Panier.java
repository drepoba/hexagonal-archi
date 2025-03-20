package drepoba.domain;

import java.util.Date;
import java.util.Objects;

public record Panier(Long id, Command command, Date created, Date updated) {
    public Panier{
        Objects.requireNonNull(command,"command cannot be null");
    }
}
