package drepoba.domain;

import java.util.Objects;

public record Product(Long id, String name, String description, double price) {
    public Product {
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(description, "Product description cannot be null");

    }
}
