package org.jth.databaseHelper;

public interface DatabaseUpdateHelper {
    void updatePrice(int list_id, double price);

    void updateAvailability(int list_id, boolean availability);
}
