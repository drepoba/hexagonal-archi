package drepoba.domain;

import java.util.Date;
import java.util.Objects;

public record LineCommand(Long id, Long commandId,Long productId,int quantity, Date created) {
    public LineCommand {
        Objects.requireNonNull(commandId, "commandId cannot be null");
        Objects.requireNonNull(productId, "productId cannot be null");
        Objects.requireNonNull(quantity, "quantity cannot be null");
    }
}
