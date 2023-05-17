package daos;

import model.Slot;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SlotDAO {

    public void createSlot(Slot s) throws SQLException;

    public Slot readSlot(String codiProducte) throws SQLException;

    public ArrayList<Slot> readSlots() throws SQLException;

    public void updateSlot(Slot s) throws SQLException;

    public void deleteSlot(Slot s) throws SQLException;

    public void deleteSlot(String codiProducte) throws SQLException;

}
