package ek.osnb.functional.live;


import ek.osnb.functional.exercises.Product;
import ek.osnb.functional.exercises.SampleData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

// Streams
public class Live3 {
    static void main() {


        // .filter()

        List<Product> products = SampleData.products();

        // Filter only for products that are below 500 kr.
        List<Product> productsUnder500 = new ArrayList<>();
        for (var p : products) {
            if (p.price() < 500) {
                productsUnder500.add(p);
            }
        }
        System.out.println(productsUnder500);
//        System.out.println(productsUnder500.size());

        Predicate<Product> isBelow500 = p -> p.price() < 500;

        List<Product> productsUnder500WithStreams = SampleData.products()
                .stream()
                .filter(isBelow500)
                .toList();

        System.out.println(productsUnder500WithStreams);
        System.out.println(productsUnder500WithStreams.size());


        // .map()
        // I want only product names

        List<String> list = SampleData.products()
                .stream()
                .map(p -> p.name())
                .toList();

        // From Product -> ProductDto
        List<ProductDto> list1 = SampleData.products()
                .stream()
                .map(p -> new ProductDto(p.name(), p.price()))
                .toList();


        // List of lists => Use .flatMap()
        var orders = List.of(
                new Order(1L, List.of(
                        new Item("Laptop"),
                        new Item("Mouse")
                )),
                new Order(1L, List.of(
                        new Item("Milk"),
                        new Item("Oatmeal")
                )));

        List<Item> list2 = orders.stream()
                .flatMap(o -> o.items().stream())
                .toList();

        System.out.println(list2);


        // Combining .filter() and .map()
        // Only return categories of products that are above 1000 kr.
        List<String> list3 = SampleData.products()
                .stream()
                .filter(p -> p.price() > 1000)
                .map(Product::category) // method reference Product::category instead of p -> p.category()
                .toList();

        System.out.println(list3);


        // Sort and limit
        // Sort by cheapest
        List<Product> sorted = SampleData.products()
                .stream()
                .sorted(Comparator.comparing(Product::price))
                .limit(2)
                .toList(); // .reversed() on toList() ===> toList().reversed()

        // With comparator:
        List<Product> sortedReversed = SampleData.products()
                .stream()
                .sorted((p1, p2) -> Double.compare( p2.price(),p1.price())) //
                .limit(2)
                .toList(); // .reversed() on toList() ===> toList().reversed()


        System.out.println(sorted);


        // Count and sum

        // How many cheap ( under 100) products do we have???
        long count = SampleData.products().stream()
                .filter(p -> p.price() < 100)
                .count();
        System.out.println("How many cheap ( under 100) products do we have??? " + count);

        // Sum
        double total = SampleData.products().stream()
                .mapToDouble(Product::price)
                .sum();

        System.out.println("Total prices (idk): " + total);

    }

    record ProductDto(String name, double price) {
    }


    record Order(Long id, List<Item> items) {
    }

    record Item(String name) {
    }
}
