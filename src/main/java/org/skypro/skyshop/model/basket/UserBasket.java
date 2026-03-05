package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.stream.Stream;

public class UserBasket {
    private final List<BasketItem> listProduct;
    private final int total;

    public UserBasket(List<BasketItem> listProduct) {
        this.listProduct = listProduct;
        this.total = listProduct.stream()
                .mapToInt(k -> k.getProduct().getPrice() * k.getAmount())
                .sum();
    }

    public List<BasketItem> getListProduct() {
        return listProduct;
    }

    public int getTotal() {
        return total;
    }
}
