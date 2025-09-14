import model.Product;
import services.CartService;
import services.ProductService;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Views {
    private final DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");
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
        StringBuilder sbSimple = productListFormat();
        productService.getAllProducts().forEach(product ->
                sbSimple.append(String.format("| %-30s | $%17s | %7d |\n",
                        product.getName().length() > 30 ?
                                product.getName().substring(0, 27) + "..." :
                                product.getName(),
                        currencyFormat.format(product.getPrice()),
                        product.getStock()))
        );
        sbSimple.append("+--------------------------------+---------------------+---------+");
        sbSimple.append("</pre></html>");
        JOptionPane.showMessageDialog(
                null,
                sbSimple.toString(),
                "Lista de Productos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    public void viewListProductsByName(){
        try{
            StringBuilder sbSimple = productListFormat();
            String productNameStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el nombre del producto",
                    "Agregar Producto",
                    JOptionPane.QUESTION_MESSAGE
            );
            productService.getAllPartiallyProducts(productNameStr).forEach(product ->
                    sbSimple.append(String.format("| %-30s | $%17s | %7d |\n",
                            product.getName().length() > 30 ?
                                    product.getName().substring(0, 27) + "..." :
                                    product.getName(),
                            currencyFormat.format(product.getPrice()),
                            product.getStock()))
            );
            sbSimple.append("+--------------------------------+---------------------+---------+");
            sbSimple.append("</pre></html>");
            JOptionPane.showMessageDialog(
                    null,
                    sbSimple.toString(),
                    "Lista de Productos",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public void viewFinalTicket(){
        StringBuilder sbSimple = productListFormat();
        cartService.getAllItemsFromCart().forEach(product ->
                sbSimple.append(String.format("| %-30s | $%17s | %7d |\n",
                        product.getProduct().getName().length() > 30 ?
                                product.getProduct().getName().substring(0, 27) + "..." :
                                product.getProduct().getName(),
                        currencyFormat.format(product.getProduct().getPrice()),
                        product.getProduct().getStock()))
        );
        sbSimple.append("+--------------------------------+---------------------+---------+\n\n");
        sbSimple.append(String.format("%-30s %30s","TOTAL →", cartService.getTotalCart()));
        sbSimple.append("</pre></html>");
        JOptionPane.showMessageDialog(
                null,
                sbSimple.toString(),
                "Lista de Productos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }



    private StringBuilder productListFormat() {
        StringBuilder sbSimple = new StringBuilder();

        sbSimple.append("<html><pre style='font-family: monospace; font-size: 12px;'>");
        sbSimple.append("+--------------------------------+---------------------+---------+\n");
        sbSimple.append("| PRODUCTO                       |       PRECIO        |  STOCK  |\n");
        sbSimple.append("+--------------------------------+---------------------+---------+\n");

        return sbSimple;
    }

}
