package Database; // Define el paquete donde se encuentra esta clase

// Importación de clases necesarias para la conexión y manejo de errores
import Database.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

// Clase que gestiona la conexión con la base de datos MySQL
public class conexion {
    // Constantes para la conexión: driver, URL base, nombre de la BD, usuario y contraseña
    private final String DRIVER = "com.mysql.jdbc.Driver"; // Driver JDBC para MySQL (versión antigua)
    private final String URL = "jdbc:mysql://localhost:3307/"; // URL base de la conexión
    private final String DB = "barbershop"; // Nombre de la base de datos
    private final String USER = "root"; // Usuario de la base de datos
    private final String PASSWORD = ""; // Contraseña del usuario

    // Variable que representa la conexión activa
    public Connection cadena;

    // Instancia única de la clase (patrón Singleton)
    public static conexion instancia;

    // Constructor privado: solo accesible desde dentro de la clase
    conexion() {
        this.cadena = null; // Inicializa la conexión como nula
    }

    // Método para establecer la conexión a la base de datos
    public Connection conectar() {
        try {
            Class.forName(DRIVER); // Carga el driver de MySQL
            this.cadena = DriverManager.getConnection(URL + DB, USER, PASSWORD); // Establece la conexión
        } catch (ClassNotFoundException | SQLException e) {
            // Muestra mensaje de error si no se puede conectar y termina el programa
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena; // Retorna la conexión
    }

    // Método para cerrar (desconectar) la conexión
    public void desconectar() {
        try {
            this.cadena.close(); // Intenta cerrar la conexión
        } catch (SQLException e) {
            // Muestra mensaje si ocurre un error al cerrar la conexión
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Método estático para obtener la única instancia de la clase (Singleton)
    public synchronized static conexion getInstancia() {
        if (instancia == null) {
            instancia = new conexion(); // Si no existe, la crea
        }
        return instancia; // Devuelve la instancia
    }
}
