package daos;

public class DAOFactory {
    private static DAOFactory instance;
    private static ProducteDAO producteDAOinstance;
    private static SlotDAO slotDAOinstance;

    private DAOFactory() {
        // init ConnectionFactory
    }
    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }
    public ProducteDAO getProducteDAO() {
        if (producteDAOinstance == null)
            producteDAOinstance = new ProducteDAO_MySQL();
        return producteDAOinstance;
    }

    public SlotDAO getSlotDao() {
        if (slotDAOinstance == null)
            slotDAOinstance = new SlotDAO_MySQL();
        return slotDAOinstance;
    }

}
