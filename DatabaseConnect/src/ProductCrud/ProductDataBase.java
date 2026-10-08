package ProductCrud;

import java.sql.SQLException;

public class ProductDataBase {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DatabaseConfig config = new DatabaseConfig();

        config.getData();

        ProductData productData = new ProductData();

        productData.setProduct_name("Laptop");
        productData.setPrice(22000);
        productData.setCategory("Electronics");
        productData.setQuantity(7);

        config.insertData(productData);

        
        config.getData();


        ProductData updateProduct = new ProductData();

        updateProduct.setProduct_id(1);
        updateProduct.setProduct_name("Gaming Laptop");
        updateProduct.setPrice(65000);
        updateProduct.setCategory("Electronics");
        updateProduct.setQuantity(8);

        config.updateData(updateProduct);
        
        config.getData();
        
        config.deleteData(4);
        
        config.getData();
    }
}

