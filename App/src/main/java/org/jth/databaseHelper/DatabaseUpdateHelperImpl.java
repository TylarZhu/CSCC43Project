package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.*;
import java.util.Date;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseUpdateHelperImpl implements DatabaseUpdateHelper{

    private DatabaseCheckDataHelperImpl databaseCheckDataHelper = new DatabaseCheckDataHelperImpl();

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

    @Override
    public void updateAvailability(int list_id, boolean availability) {
        try {
            Connection connection = connectingToDatabase();
            String sql = "UPDATE listings SET availability = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, availability);
            preparedStatement.setInt(2, list_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with update availability! see below details: ");
            e.printStackTrace();
        }
    }

    @Override
    public void updateHost(int ins, String firstName, String lastName, String address, String postal_code,
                           Date date_of_birth, String occupation) {

    }

    @Override
    public void updateRenter(int ins, String firstName, String lastName, String address, String postal_code,
                             Date date_of_birth, String occupation, String cardNumnber, String card_expiry_date, int cvv) {

    }
}
