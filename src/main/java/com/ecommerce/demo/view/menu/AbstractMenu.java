package com.ecommerce.demo.view.menu;

import com.ecommerce.demo.util.InputHandler;

import static com.ecommerce.demo.util.Constants.CONFIRMATION;

/**
 * Clase abstracta para la manipulación de los menús
 * @author Gabriel Norambuena
 * @version 1.0
 */
public abstract class AbstractMenu implements Menu {
    protected final InputHandler inputHandler;

    /**
     * Constructor de la clase
     * @param inputHandler Clase de utilidad para manipular las entradas
     */
    protected AbstractMenu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        boolean running = true;
        while (running) {
            //imprimo el menú de la clase que lo implementa
            printMenuOptions();

            //pido que ingrese una opción
            int option = inputHandler.readInt("Ingrese una opción: ");

            // basado en la opción que ingreso, la implementación de handleOption decide si salir.
            running = handleOption(option);
        }
    }

    /**
     * Imprime las opciones del menú.
     */
    protected abstract void printMenuOptions();

    /**
     * Manipula la opción ingresada del usuario.
     * @param option Número de la opción
     * @return Verdadero si continua la ejecución, falso si decide cancelarla.
     */
    protected abstract boolean handleOption(int option);

    /**
     * Confirma una opción determinada
     * @param msg Texto a validar
     * @return Verdadero si el texto coincide con una de las opciones de confirmación
     *
     * @see com.ecommerce.demo.util.Constants#CONFIRMATION
     */
    protected boolean confirm(String msg) {
        return CONFIRMATION.contains(msg.toLowerCase());
    }
}
