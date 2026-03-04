package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket;

    public ProductBasket() {
        this.basket = new HashMap<>();
    }

    // Метод добавления продукта в корзину
    public void addProduct(UUID id) {
        if (id != null) {
            basket.computeIfAbsent(id, k -> 1);
            if (basket.containsKey(id)) {
                basket.put(id, basket.get(id) + 1);
            }
        }
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
