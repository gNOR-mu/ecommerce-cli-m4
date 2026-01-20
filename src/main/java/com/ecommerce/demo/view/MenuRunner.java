package com.ecommerce.demo.view;

/**
 * Clase utilizada para cargar los menús
 */
public class MenuRunner {
    /**
     * Ejecutor de un menu
     * @param menu Menú a mostrar
     */
    public static void run(Menu menu){
        try{
            menu.show();
        }catch (Exception e){
            System.out.println("Error al cargar el menú");
            e.printStackTrace();
        }
    }
}
