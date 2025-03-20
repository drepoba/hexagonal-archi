package drepoba.domain;

import java.util.Date;
import java.util.Objects;

public record Command(Long id, Long customerId, double totalCost, Date created, Date updated) {
    public Command{
        Objects.requireNonNull(customerId, "customerId ne peut pas être null !");
        Objects.requireNonNull(totalCost,"totalCost ne peut pas être null");
    }
}
