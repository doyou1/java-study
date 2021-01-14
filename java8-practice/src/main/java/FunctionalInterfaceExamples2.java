import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamples2 {
    public static void main(String[] args) {
        Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        Product productB = new Product(2L, "B", new BigDecimal("55.50"));
        Product productC = new Product(3L, "C", new BigDecimal("73.73"));
        Product productD = new Product(4L, "D", new BigDecimal("22.99"));
        Product productE = new Product(5L, "E", new BigDecimal("17.45"));
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

        final Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;
        System.out.println(filter(discountedProducts2, lessThanOrEqualTo30));
//         discountProduct용으로 만들었기에 슈퍼클래스는 X, 반대의 경우도 X
        System.out.println(filter(products, lessThanOrEqualTo30));

        final List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for(BigDecimal price : prices) {
            total = total.add(price);
        }
        System.out.println(total);

        final BigDecimal newTotal = total(products, product -> product.getPrice());
        System.out.println(newTotal);

        final BigDecimal discountTotal = total(discountedProducts, product -> product.getPrice());
        System.out.println(discountTotal);

        Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, productA, 2),
                new OrderedItem(1L, productB, 1),
                new OrderedItem(1L, productC, 10),
                new OrderedItem(1L, productD, 3),
                new OrderedItem(1L, productE, 4)
        ));

        BigDecimal orderTotal = BigDecimal.ZERO;
        for(OrderedItem item : order.getItems()) {
            orderTotal = orderTotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        System.out.println("legacy order total: "+ orderTotal);
        System.out.println("modern order total: "+ order.totalPrice());
    }

//      위 제네릭 타입에 따라 슈퍼클래스, 상속받은 클래스를 상호로 사용할 수 없는 문제점 해결
//      파라미터로 Product, DiscountProduct 모두를 받고, return만 Product(슈퍼 클래스)로 설정
//    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
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

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for(final T t : list) {
            total = total.add(mapper.apply(t));
        }
        return total;
    }


    static  class Product {
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

    static class DiscountProduct extends Product {
        public DiscountProduct() {
        }

        public DiscountProduct(Long id, String name, BigDecimal price) {
            super(id, name, price);
        }

    }

    static class OrderedItem {
        private Long id;
        private Product product;
        private int quantity;

        public OrderedItem() {
        }

        public OrderedItem(Long id, Product product, int quantity) {
            this.id = id;
            this.product = product;
            this.quantity = quantity;
        }

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "OrderedItem{" +
                    "id=" + id +
                    ", product=" + product +
                    ", quantity=" + quantity +
                    '}';
        }
    }
    static class Order {
        private Long id;
        private String orderNumber;
        private List<OrderedItem> items;

        public Order() {
        }

        public Order(Long id, String orderNumber, List<OrderedItem> items) {
            this.id = id;
            this.orderNumber = orderNumber;
            this.items = items;
        }

        public BigDecimal totalPrice() {
            return total(items, item -> item.getItemTotal());
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public List<OrderedItem> getItems() {
            return items;
        }

        public void setItems(List<OrderedItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", orderNumber='" + orderNumber + '\'' +
                    ", items=" + items +
                    '}';
        }

    }
}

