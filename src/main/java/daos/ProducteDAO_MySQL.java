package daos;

import com.sun.tools.jconsole.JConsoleContext;
import model.Producte;

import java.sql.*;
import java.util.ArrayList;

public class ProducteDAO_MySQL implements ProducteDAO {

    //Dades de connexió a la base de dades
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_ROUTE = "jdbc:mysql://localhost:3306/expenedora";
    private static final String DB_USER = "root";
    private static final String DB_PWD = "1324";

    private Connection conn = null;

    public ProducteDAO_MySQL()
    {
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_ROUTE, DB_USER, DB_PWD);
            System.out.println("Conexió establerta satisfactoriament");
        } catch (Exception e) {
            System.out.println("S'ha produit un error en intentar connectar amb la base de dades. Revisa els paràmetres");
            System.out.println(e);
        }
    }

    @Override
    public void createProducte(Producte p) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO producte VALUES(?,?,?,?,?)");

        ps.setString(1,p.getCodiProducte());
        ps.setString(2,p.getNom());
        ps.setString(3,p.getDescripcio());
        ps.setFloat(4,p.getPreuCompra());
        ps.setFloat(5,p.getPreuVenta());

        int rowCount = ps.executeUpdate();
    }

    @Override
    public Producte readProducte(String codiProducte) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM producte WHERE codi_producte = ?");

        ps.setString(1, codiProducte);

        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Producte p = new Producte();

            p.setCodiProducte(rs.getString(1));
            p.setNom(rs.getString(2));
            p.setDescripcio(rs.getString(3));
            p.setPreuCompra(rs.getFloat(4));
            p.setPreuVenta(rs.getFloat(5));

            return p;
        }
        return null;
    }


    @Override
    public ArrayList<Producte> readProductes() throws SQLException {
        ArrayList<Producte> llistaProductes = new ArrayList<Producte>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM producte");

        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Producte p = new Producte();

            p.setCodiProducte(rs.getString(1));
            p.setNom(rs.getString(2));
            p.setDescripcio(rs.getString(3));
            p.setPreuCompra(rs.getFloat(4));
            p.setPreuVenta(rs.getFloat(5));

            llistaProductes.add(p);
        }

        return llistaProductes;
    }

    @Override
    public void updateProducte(Producte p) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE producte set nom = ?, descripcio = ?, preu_compra = ?, preu_venta = ? WHERE codi_producte = ?");

        ps.setString(5, p.getCodiProducte());

        ps.setString(1, p.getNom());
        ps.setString(2, p.getDescripcio());
        ps.setFloat(3, p.getPreuCompra());
        ps.setFloat(4, p.getPreuVenta());

        ps.executeUpdate();

    }

    @Override
    public void deleteProducte(Producte p) throws SQLException {
        //Delete per a taula producte
        PreparedStatement ps = conn.prepareStatement("Delete from producte where codi_producte = ?");
        ps.setString(1, p.getCodiProducte());

        //Delete per a taula slot, ja que hi ha una foreign key que no deixa executar la primera sentencia
        PreparedStatement ps2 = conn.prepareStatement("DELETE from slot where codi_producte = ?");
        ps2.setString(1, p.getCodiProducte());
        ps2.execute();


        ps.execute();
    }

    @Override
    public void deleteProducte(String codiProducte) throws SQLException {
        //Delete per a taula producte
        PreparedStatement ps = conn.prepareStatement("Delete from producte where codi_producte = ?");
        ps.setString(1, codiProducte);

        //Delete per a taula slot, ja que hi ha una foreign key que no deixa executar la primera sentencia
        PreparedStatement ps2 = conn.prepareStatement("DELETE from slot where codi_producte = ?");
        ps2.setString(1, codiProducte);
        ps2.execute();


        ps.execute();
    }
}
