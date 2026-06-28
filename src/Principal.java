import dao.NumeroDAO;
import modelo.Numero;

public class Principal {

    public static void main(String[] args) {

        NumeroDAO dao = new NumeroDAO();

        System.out.println("Generando secuencia...");

        for(int i = 2; i <= 100; i += 2){

            Numero numero = new Numero(i);

            dao.guardar(numero);

            System.out.println("Guardado: " + i);

        }
        System.out.println();
        dao.mostrarTodos();

    }   
}