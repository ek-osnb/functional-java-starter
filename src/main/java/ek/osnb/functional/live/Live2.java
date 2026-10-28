package ek.osnb.functional.live;

import ek.osnb.functional.exercises.Product;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//@FunctionalInterface
//interface PriceRule2 {
//    double apply(double price);
//}

interface PriceRule2 extends Function<Double,Double> {}

public class Live2 {

    static double calculatePrice(double price, Function<Double,Double> rule) {
        return rule.apply(price);
    }
    static void main() {
        // Function<    T,      R >
        //              ^       ^
        //        Input type  Output type
        // One abstract method: R apply(T t)
        Function<Double,Double> studentDiscount = p -> p * 0.9;

        System.out.println(calculatePrice(200,studentDiscount));

        // Combine
        // Problem: Student get 10% discount and shipping fee of 49
        Function<Double,Double> shippingFee = price -> price + 49;
        final double PRICE = 200;

        double withDiscount = studentDiscount.apply(PRICE);
        double totalPrice = shippingFee.apply(withDiscount);
        System.out.println(totalPrice);

        Function<Double, Double> total = studentDiscount.andThen(shippingFee);

        System.out.println(total.apply(PRICE));

        // Order matters
        // studentDiscount.andThen(shippingFee);
        // Is not the same as
        // shippingFee.andThen(studentDiscount);


        // Changing type along the way
        Function<Double, String> formatPrice = price -> price + " kr.";

        Function<Double,String> priceTag = studentDiscount.andThen(formatPrice);

        // studenDiscount:  double -> double
        // formatPrice:     double -> string
        // combined:        double -> string

        System.out.println(priceTag.apply(PRICE));


        Function<Double,Function<Double,Double>> myPercentDiscount = (Double percent1) -> (Double price) -> price *  (1 - percent1 / 100.0);
        Function<Double, Double> studentDiscountWithHigherOrderFunction = myPercentDiscount.apply(10.0);
        System.out.println(studentDiscountWithHigherOrderFunction.apply(PRICE));

        // Predicate<T>
        //           ^
        //         INput type => return true/false
        // one abstract method: boolean test(T t)

        // Is the product below 100??
        Predicate<Product> isCheap = product -> product.price() < 100;
        Product p = new Product("Laptop", 8000, "Electronics");

        System.out.println(isCheap.test(p));

        Predicate<Product> isElectronic = product -> product.category().equals("Electronics");

        System.out.println(isCheap.or(isElectronic).test(p));

        // Consumer
        Consumer<String> print = str -> System.out.println(str);

        print.accept("Hello world");


        // Supplier
        Supplier<Integer> random = () -> new Random().nextInt(0,100);

        System.out.println(random.get());
        System.out.println(random.get());
        System.out.println(random.get());
        System.out.println(random.get());
        System.out.println(random.get());

    }
}
