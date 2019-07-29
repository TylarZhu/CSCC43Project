package org.jth.databaseHelper;

import org.jth.fields.ListingType;

import java.util.Date;

public interface DatabaseUpdateHelper {
    void updatePrice(int list_id, double price);

    void updateAvailability(int list_id, boolean availability);

    void updateHost(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth,
                      String occupation);

    void updateRenter(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth,
                      String occupation, String cardNumnber, String card_expiry_date, int cvv);
}
