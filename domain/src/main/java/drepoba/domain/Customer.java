package drepoba.domain;

import java.util.Objects;

public record Customer(Long id, String firstName, String lastName, String email) {
    public Customer {
        Objects.requireNonNull(firstName, "firstName cannot be null");
        Objects.requireNonNull(lastName, "lastName cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
    }
}
