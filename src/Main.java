import model.Product;
import services.ProductService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductService service = new ProductService();
        try {
            service.addProduct(new Product("Arroz",26000 , 20));

            service.addProduct(new Product("Arroz de coco",36000 , 10));
            service.addProduct(new Product("Carne",50000 , 40));
            service.addProduct(new Product("Carne",50000 , 40));
            service.addProduct(new Product("Carne",50000 , 40));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(service.getAllProducts());

    }


    static void menu(){
        System.out.println("""
                    -- MENU -- 
                    
                1. Agregar producto.
                2. Listar inventario.
                3. Comprar producto.
                4. Mostrar estadísticas (más barato y más caro).
                5. Buscar producto por nombre.
                6. Salir con ticket final.
                
                """);
    }
}