import views.Views;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Views views = new Views();
        boolean running = true;
        while(running){
            try {
                String[] options = {
                        "1. Agregar producto",
                        "2. Listar inventario",
                        "3. Comprar producto",
                        "4. Mostrar estadísticas (más barato y más caro)",
                        "5. Buscar producto por nombre",
                        "6. Salir con ticket final",
                };

                String choice = (String) JOptionPane.showInputDialog(
                        null,
                        "\nSeleccione una opción:",
                        "MENU",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == null) break;

                switch (choice) {
                    case "1. Agregar producto":
                        views.viewAddProduct();
                        break;
                    case "2. Listar inventario":
                        views.viewListProducts();
                        break;
                    case "3. Comprar producto":
                        views.viewAddProductToCart();
                        break;
                    case "Mostrar estadísticas":
                        break;
                    case "5. Buscar producto por nombre":
                        views.viewListProductsByName();
                        break;
                    case "6. Salir con ticket final":
                        views.viewFinalTicket();
                        running = false;
                        break;

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }



}