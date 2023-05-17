import daos.DAOFactory;
import daos.ProducteDAO;
import daos.SlotDAO;
import model.Producte;
import model.Slot;

import java.sql.SQLException;

public class prova {

    private static DAOFactory df = DAOFactory.getInstance();
    private static ProducteDAO producteDAO = df.getProducteDAO();
    private static SlotDAO slotDAO = df.getSlotDao();

    public static void main(String[] args) throws SQLException {
        for (Slot slot: slotDAO.readSlots()){
            System.out.println(slot);
        }
    }

}
