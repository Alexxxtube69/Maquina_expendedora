package daos;

import model.Producte;
import model.Slot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SlotDAO_MySQL implements SlotDAO {

    //Dades de connexió a la base de dades
    private static final String DB_DRIVER = dadesMaquina().get(0);
    private static final String DB_ROUTE = dadesMaquina().get(1);
    private static final String DB_USER = dadesMaquina().get(2);
    private static final String DB_PWD = dadesMaquina().get(3);

    private Connection conn = null;

    public SlotDAO_MySQL()
    {
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_ROUTE, DB_USER, DB_PWD);
            System.out.println("Conexió a slots correcta");
        } catch (Exception e) {
            System.out.println("S'ha produit un error en intentar connectar amb la base de dades. Revisa els paràmetres");
            System.out.println(e);
        }
    }


    @Override
    public void createSlot(Slot s) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO slot VALUES(?,?,?)");

        ps.setInt(1,s.getPosicio());
        ps.setInt(2,s.getQuantitat());
        ps.setString(3,s.getCodi_producte());

        int rowCount = ps.executeUpdate();
    }

    @Override
    public Slot readSlot(String codiProducte) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM slot WHERE codi_producte = ?");

        ps.setString(1, codiProducte);

        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Slot s = new Slot();

            s.setPosicio(rs.getInt(1));
            s.setQuantitat(rs.getInt(2));
            s.setCodi_producte(rs.getString(3));

            return s;
        }
        return null;
    }

    @Override
    public ArrayList<Slot> readSlots() throws SQLException {
        ArrayList<Slot> llistaSlots = new ArrayList<Slot>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM slot");

        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Slot s = new Slot();
            s.setPosicio(rs.getInt(1));
            s.setQuantitat(rs.getInt(2));
            s.setCodi_producte(rs.getString(3));

            llistaSlots.add(s);
        }

        return llistaSlots;
    }

    @Override
    public void updateSlot(Slot s) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE slot SET codi_producte = ?, quantitat = ? WHERE posicio = ?");

        ps.setInt(3, s.getPosicio());
        ps.setInt(2, s.getQuantitat());
        ps.setString(1, s.getCodi_producte());

        ps.executeUpdate();
    }

    @Override
    public void deleteSlot(Slot s) throws SQLException {
        //Delete per a taula slot
        PreparedStatement ps = conn.prepareStatement("Delete from slot where codi_producte = ?");
        ps.setString(1, s.getCodi_producte());

        //Delete per a taula producte, ja que hi ha una foreign key que no deixa executar la primera sentència
        PreparedStatement ps2 = conn.prepareStatement("DELETE from producte where codi_producte = ?");
        ps2.setString(1, s.getCodi_producte());
        ps2.execute();


        ps.execute();
    }

    @Override
    public void deleteSlot(String codiProducte) throws SQLException {
        //Delete per a taula slot
        PreparedStatement ps = conn.prepareStatement("Delete from slot where codi_producte = ?");
        ps.setString(1, codiProducte);

        //Delete per a taula producte, ja que hi ha una foreign key que no deixa executar la primera sentència
        PreparedStatement ps2 = conn.prepareStatement("DELETE from producte where codi_producte = ?");
        ps2.setString(1, codiProducte);
        ps2.execute();

        ps.execute();
    }

    public static ArrayList<String> dadesMaquina(){
        ArrayList<String> dadesBdD = new ArrayList<>();

        Path path = Paths.get("src/main/java/daos/Properties.txt");

        try (Scanner lector = new Scanner(path)) {
            while (lector.hasNextLine()) {
                String linia = lector.nextLine();
                dadesBdD.add(linia);
            }
            return dadesBdD;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
