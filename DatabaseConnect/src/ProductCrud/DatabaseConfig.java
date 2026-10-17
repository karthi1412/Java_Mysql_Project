package ProductCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConfig {

    String hostName = "jdbc:mysql://localhost:3306/productdatabase";
    String user = "root";
    String password = "MySQL@123";

    Connection connection;

    public void getData() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(hostName, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM products");

        List<ProductData> productDatas = new ArrayList<ProductData>();

        while (result.next()) {

            ProductData productData = new ProductData();

            productData.setProduct_id(result.getInt("product_id"));
            productData.setProduct_name(result.getString("product_name"));
            productData.setPrice(result.getDouble("price"));
            productData.setCategory(result.getString("category"));
            productData.setQuantity(result.getInt("quantity"));
            productDatas.add(productData);
        }

        System.out.println(productDatas);
    }


    public void insertData(ProductData productData) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(hostName, user, password);
        
        PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO products "
                        + "(product_name, price, category, quantity) "
                        + "VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, productData.getProduct_name());
        preparedStatement.setDouble(2, productData.getPrice());
        preparedStatement.setString(3, productData.getCategory());
        preparedStatement.setInt(4, productData.getQuantity());
        preparedStatement.executeUpdate();

        System.out.println("Product inserted successfully");
    }


    public void updateData(ProductData productData) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(hostName, user, password);

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "UPDATE products SET "
                        + "product_name = ?, "
                        + "price = ?, "
                        + "category = ?, "
                        + "quantity = ? "
                        + "WHERE product_id = ?");

        preparedStatement.setString(1, productData.getProduct_name());
        preparedStatement.setDouble(2, productData.getPrice());
        preparedStatement.setString(3, productData.getCategory());
        preparedStatement.setInt(4, productData.getQuantity());
        preparedStatement.setInt(5, productData.getProduct_id());
        preparedStatement.executeUpdate();

        System.out.println("Product updated successfully");
    }


    public void deleteData(int product_id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(hostName, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");

        preparedStatement.setInt(1, product_id);
        preparedStatement.executeUpdate();

        System.out.println("Product deleted successfully");
    }
}


