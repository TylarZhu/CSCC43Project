package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.*;
import static org.jth.databaseHelper.DatabaseDriver.*;



public class DatabaseUpdateHelperImpl implements DatabaseUpdateHelper{


    @Override
    public void updateAvaliablity() {

    }

    @Override
    public void updatePrice() {
        try{
            Connection connection = connectingToDatabase();


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
