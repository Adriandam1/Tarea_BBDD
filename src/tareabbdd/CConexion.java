package tareabbdd;

import javax.swing.JOptionPane;
import java.sql.*;

public class CConexion {

    Connection conectar = null;
    String usuario = "postgres";
    String contrasenia = "debian";
    String bd = "futbol2";
    String ip = "localhost";
    String puerto = "5432";
    String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Se conectó a la BD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse: " + e.toString());
        }
        return conectar;
    }

    public void crearTabla(String equipo) {
        try (Statement stmt = conectar.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS "+equipo+" (" +
                         "ID SERIAL PRIMARY KEY, " +
                         "Nombre VARCHAR(50), " +
                         "Posicion VARCHAR(30), " +
                         "Numero INT)";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Tabla Jugadores creada con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertarJugador(String nombre, String posicion, int numero) {
        String sql = "INSERT INTO Madrid (Nombre, Posicion, Numero) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, posicion);
            pstmt.setInt(3, numero);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Jugador insertado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar jugador: " + e.getMessage());
        }
    }

    public void borrarJugador(int id) {
        String sql = "DELETE FROM Madrid WHERE ID = ?";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Jugador eliminado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el jugador con ID: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar jugador: " + e.getMessage());
        }
    }

    public void actualizarJugador(int id, String nuevoNombre) {
        String sql = "UPDATE Madrid SET Nombre = ? WHERE ID = ?";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setInt(2, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Nombre del jugador actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el jugador con ID: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar jugador: " + e.getMessage());
        }
    }
}
