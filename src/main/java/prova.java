import daos.DAOFactory;
import daos.ProducteDAO;
import daos.SlotDAO;
import model.Producte;
import model.Slot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class prova {

    private static DAOFactory df = DAOFactory.getInstance();
    private static ProducteDAO producteDAO = df.getProducteDAO();
    private static SlotDAO slotDAO = df.getSlotDao();

    public static void main(String[] args) throws SQLException {
        Path path = Paths.get("src/main/java/daos/Properties.txt");

        try (Scanner lector = new Scanner(path)) {
            while (lector.hasNextLine()) {
                String linia = lector.nextLine();
                System.out.println(linia);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
