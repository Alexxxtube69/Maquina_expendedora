import model.Producte;

import java.sql.SQLSyntaxErrorException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {

    public static Producte crearProducte(){
        Scanner input = new Scanner(System.in);
        Producte p = new Producte();
        try {
            System.out.println("Codi Prodcute: ");
            p.setCodiProducte(input.nextLine());
            System.out.println("Nom producte: ");
            p.setNom(input.nextLine());
            System.out.println("Descripcio producte: ");
            p.setDescripcio(input.nextLine());
            System.out.println("Preu compra producte: ");
            p.setPreuCompra(input.nextFloat());
            System.out.println("Preu venta producte: ");
            p.setPreuVenta(input.nextFloat());

            return p;
        } catch (InputMismatchException e){
            System.err.println("El preu ha de ser un numero");
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static String comprarProducteNom(){
        Scanner input = new Scanner(System.in);
        System.out.println("Entra el nom del producte");
        return input.nextLine();
    }

    public static int comprarProductePosicio(){
        Scanner input = new Scanner(System.in);
        System.out.println("Entra la posicio del producte");
        return input.nextInt();
    }

    public static String seleccionarOpcio() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static int posicioSlot() {
        Scanner input = new Scanner(System.in);
        System.out.println("Indica la posicio: ");
        try {
            return input.nextInt();
        }catch (InputMismatchException e){
            System.err.println("S'ha d'entrar un digit");
            return 0;
        }

    }

    public static int quantitatSlot() {
        Scanner input = new Scanner(System.in);
        System.out.println("Indica la quantitat: ");
        return input.nextInt();
    }

    public static String producteId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Indica el producte: ");
        return input.nextLine();
    }
}
