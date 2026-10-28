package ek.osnb.functional.live;

import ek.osnb.functional.exercises.Product;

// Lambda
public class Live1 {

    static double studentPrice(double price) {
        return price * 0.9;
    }

    static double salePrice(double price) {
        return price * 0.8;
    }

    static double calculatePrice(double price, PriceCalc rule) {
        return rule.apply(price);
    }

    static PriceCalc percentDiscount(double percent) {
        return price -> price * (1 - percent / 100.0);
    }

    static void main() {
        Product p = new Product("Laptop", 8000, "Electronics");
//        System.out.println(studentPrice(p.price()));

//        System.out.println(calculatePrice(p.price(),new SaleDiscount()));
//        System.out.println(calculatePrice(p.price(),new StudentDiscount()));


        // JavaScript: p => p.price > 100
        // JAVA: p -> p.price() > 100

//        System.out.println(calculatePrice(p.price(),price -> price * 0.8));
//        System.out.println(calculatePrice(p.price(),price -> price * 0.9));

//        PriceCalc studentDiscount = price -> price * 0.9; // 10%
//        PriceCalc saleDiscount = price -> price * 0.8; // 20%
//
//        System.out.println(calculatePrice(p.price(), studentDiscount));
//        System.out.println(calculatePrice(p.price(), saleDiscount));

        final double STUDENT_DISCOUNT_PERCENTAGE = 10;
        final double SALE_DISCOUNT_PERCENTAGE = 20;

        PriceCalc studentDiscount = percentDiscount(STUDENT_DISCOUNT_PERCENTAGE);
        PriceCalc saleDiscount = percentDiscount(SALE_DISCOUNT_PERCENTAGE);

        System.out.println(calculatePrice(p.price(), studentDiscount));
        System.out.println(calculatePrice(p.price(), saleDiscount));

    }
}


interface PriceCalc {
    double apply(double price);
}

//class StudentDiscount implements PriceCalc {
//    @Override
//    public double apply(double price) {
//        return price * 0.9;
//    }
//}
//
//class SaleDiscount implements PriceCalc {
//    @Override
//    public double apply(double price) {
//        return price * 0.8;
//    }
//}
