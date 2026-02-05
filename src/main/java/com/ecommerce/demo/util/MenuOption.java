package com.ecommerce.demo.util;

import java.util.Arrays;
import java.util.Optional;

public interface MenuOption {
    /**
     * Obtiene el código de la opción
     *
     * @return Código de la opción
     */
    int getCode();

    /**
     * Obtiene el nombre de la opción
     *
     * @return Nombre de la opción
     */
    String getDescription();

    /**
     * Busca un enum basado en el número de la opción
     *
     * @param enumClass Clase del enum
     * @param code      Código de la opción
     * @param <E>       El tipo del Enum a buscar. Debe:
     *                  1. Ser un {@code Enum}.
     *                  2. Implementar {@code OpcionConCodigo}.
     * @return Un {@link Optional} que contiene la constante del Enum si se encuentra una coincidencia exacta;
     * de lo contrario, devuelve {@code Optional.empty()}
     *
     * @throws NullPointerException si {@code enumClass} es {@code null}.
     * */
    static <E extends Enum<E> & MenuOption> Optional<E> find(Class<E> enumClass, int code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getCode() == code)
                .findFirst();
    }
}
