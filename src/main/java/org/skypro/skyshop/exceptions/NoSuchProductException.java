package org.skypro.skyshop.exceptions;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(UUID id) {
        super("Не найден продукт с идентификатором " + id + " !");
    }
}
