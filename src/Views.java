import model.Product;
import services.CartService;
import services.ProductService;

import javax.swing.*;

public class Views {
    private ProductService productService;
    private CartService cartService;
    public Views(){
        productService = new ProductService();
        cartService = new CartService(productService);
    }

    public void viewAddProductToCart(){
        try{

            String cartNameStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el nombre del producto",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );

            String quantityStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese la cantidad:",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );

            int quantity =  Integer.parseInt(quantityStr);

            cartService.addProductToCart(cartNameStr,quantity);

            JOptionPane.showMessageDialog(
                    null,
                    "Se agrego exitosamente!",
                    "Producto Agregado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public void viewAddProduct(){
        try{

            String productNameStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el nombre del producto",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );

            String priceStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el precio del producto",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );

            String stockStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese la cantidad:",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );


            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);

            productService.addProduct(new Product(productNameStr, price,stock));

            JOptionPane.showMessageDialog(
                    null,
                    "Se agrego exitosamente!",
                    "Producto Agregado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public void viewListProducts(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %10s\n",
                "NOMBRE", "PRECIO", "STOCK"));
        sb.append("─────────────────────────────────────────\n");

        productService.getAllProducts().forEach(product ->
                sb.append(String.format("%-20s $%-15s %10d\n",
                        product.getName(),
                        product.getPrice(),
                        product.getStock()))
        );

        JOptionPane.showMessageDialog(
                null,
                sb.toString(),
                "Lista de Productos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
