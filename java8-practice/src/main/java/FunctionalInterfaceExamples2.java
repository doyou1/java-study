import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamples2 {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("10.00")),
                new Product(2L, "B", new BigDecimal("55.50")),
                new Product(3L, "C", new BigDecimal("73.73")),
                new Product(4L, "D", new BigDecimal("22.99")),
                new Product(5L, "E", new BigDecimal("17.45"))
        );

        final BigDecimal twenty = new BigDecimal("20");

        List<Product> result = new ArrayList<>();
        for(final Product product : products) {
            if(product.getPrice().compareTo(twenty) >= 0) {
                result.add(product);
            }
        }
        System.out.println(result);

        List<Product> result2 = filter(products, product -> product.getPrice().compareTo(twenty) >= 0 );
        System.out.println("product >= $20: " + result2);

        List<Product> result3 = filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0 );
        System.out.println("product <= $10: " + result3);

        final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        List<DiscountProduct> discountedProducts = new ArrayList<>();
        for(final Product product : expensiveProducts) {
            discountedProducts.add(new DiscountProduct(product.getId(),product.getName(),product.getPrice()));
        }

        System.out.println("expensive products: " + expensiveProducts);
        System.out.println("discounted products: " + discountedProducts);

        final List<DiscountProduct> discountedProducts2 =
            map(expensiveProducts,
                    product -> new DiscountProduct(product.getId(),product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));

        System.out.println("expensive products: " + expensiveProducts);
        System.out.println("discounted products2: " + discountedProducts2);

    }


    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for(final T t : list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T,R> function) {
        final List<R> result = new ArrayList<>();
        for(final T t : list) {
            result.add(function.apply(t));
        }

        return result;
    }
}

class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class DiscountProduct extends Product {
    public DiscountProduct() {
    }

    public DiscountProduct(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

}