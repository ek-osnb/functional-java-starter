package ek.osnb.functional.live;

import ek.osnb.functional.exercises.Product;
import ek.osnb.functional.exercises.SampleData;

import java.util.Optional;
import java.util.function.Supplier;

public class Live4 {
    static void main() {
        // We want the first product that costs more than 10,000 kr. What should we get back if no product matches?

        Product foundProduct = SampleData.products()
                .stream()
                .filter(p -> p.name().equals("Laptop"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NOT FOUND"));

        System.out.println(foundProduct);
    }

    static Optional<Product> findByCondition() {
// We want the first product that costs more than 10,000 kr. What should we get back if no product matches?
        return SampleData.products()
                .stream()
                .filter(p -> p.price() > 10000)
                .findFirst();
    }
}
