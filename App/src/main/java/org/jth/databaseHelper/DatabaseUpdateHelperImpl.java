package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.*;
import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseUpdateHelperImpl implements DatabaseUpdateHelper{

    @Override
    public void updatePrice(int list_id, double price) {
        try {
            Connection connection = connectingToDatabase();
            String sql = "UPDATE listings SET price = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, list_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with update Price! see below details: ");
            e.printStackTrace();
        }
    }
}
