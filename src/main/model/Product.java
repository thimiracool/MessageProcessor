package main.model;

/**
 * Created by thimira on 06/05/17.
 */
public class Product {
    String productType;
    double value;

    public Product(String productType, double value) {
        this.productType = productType;
        this.value = value;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", value=" + value +
                '}';
    }
}
