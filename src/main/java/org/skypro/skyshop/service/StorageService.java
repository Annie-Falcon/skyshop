package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService() {
        storageProduct = new HashMap<>();
        storageArticle = new HashMap<>();
        createStorageProducts();
        createStorageArticle();
    }

    public Collection<Product> getStorageProduct() {
        return new ArrayList<>(storageProduct.values());
    }

    public Collection<Article> getStorageArticle() {
        return new ArrayList<>(storageArticle.values());
    }

    public Collection<Searchable> getSearchEngine() {
        return Stream.concat(storageProduct.values().stream(),
                storageArticle.values().stream()).collect(Collectors.toList());
    }

    private void createStorageProducts() {
        FixPriceProduct bread = new FixPriceProduct(UUID.randomUUID(), "хлеб");
        SimpleProduct apple = new SimpleProduct(UUID.randomUUID(), "яблоко", 20);
        DiscountedProduct milk = new DiscountedProduct(UUID.randomUUID(), "молоко", 80, 17);
        DiscountedProduct coffee = new DiscountedProduct(UUID.randomUUID(), "кофе", 300, 42);
        SimpleProduct banana = new SimpleProduct(UUID.randomUUID(), "банан", 30);
        SimpleProduct salt = new SimpleProduct(UUID.randomUUID(), "соль", 50);
        SimpleProduct breadS = new SimpleProduct(UUID.randomUUID(), "хлеб", 75);
        DiscountedProduct breadD = new DiscountedProduct(UUID.randomUUID(), "хлеб", 64, 35);

        storageProduct.put(bread.getId(), bread);
        storageProduct.put(coffee.getId(), coffee);
        storageProduct.put(milk.getId(), milk);
        storageProduct.put(apple.getId(), apple);
        storageProduct.put(breadD.getId(), breadD);
        storageProduct.put(banana.getId(), banana);
        storageProduct.put(salt.getId(), salt);
        storageProduct.put(breadS.getId(), breadS);
    }

    private void createStorageArticle() {
        Article arBread = new Article(UUID.randomUUID(), "Полезный хлеб", "хлеб подходит к напиткам: кофе, молоко");
        Article arMilk = new Article(UUID.randomUUID(), "Завтрак с молоком!", "Смузи - в молоко добавьте: банан, яблоко; подавайте к кофе с молоком");
        Article arCoffee = new Article(UUID.randomUUID(), "Рецепт кофе с молоком", "Добавьте: в кофе молоко");
        Article arBanana = new Article(UUID.randomUUID(), "Ищите банан", "Увидите яблоко - где-то рядом банан. Не забудьте молоко");
        Article arApple = new Article(UUID.randomUUID(), "Богатырское яблоко", "Полезнее, чем банан. Для смузи используйте кефир, не молоко");

        storageArticle.put(arBread.getId(), arBread);
        storageArticle.put(arMilk.getId(), arMilk);
        storageArticle.put(arCoffee.getId(), arCoffee);
        storageArticle.put(arBanana.getId(), arBanana);
        storageArticle.put(arApple.getId(), arApple);
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageProduct.get(id));
    }
}
