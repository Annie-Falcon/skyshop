package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Component
public class BasketService {
    @Autowired
    private final ProductBasket basket;
    @Autowired
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public String addProduct(UUID id) {
        storageService.getProductById(id).orElseThrow(() -> new NoSuchProductException(id));
        basket.addProduct(id);
        return "*Продукт успешно добавлен*”";
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                basket.getBasket().entrySet().stream()
                        .map(k -> {
                            Product product = storageService.getProductById(k.getKey()).orElseThrow();
                            return new BasketItem(product, k.getValue());
                        })
                        .collect(Collectors.toList()));
    }
}
