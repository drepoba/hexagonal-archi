package drepoba.domain;
import java.util.Objects;

public record CustomerProduct(Customer customer, Product product) {
    public CustomerProduct{
        Objects.requireNonNull(customer,"customer is required");
        Objects.requireNonNull(product,"product is required");
    }
}
