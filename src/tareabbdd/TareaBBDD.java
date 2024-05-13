/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tareabbdd;

/**
 *
 * @author postgres
 */
public class TareaBBDD {

    public static void main(String[] args) {
        CConexion objetoConexion = new CConexion();
        objetoConexion.establecerConexion();

        // Crear una tabla
        objetoConexion.crearTabla("Madrid");

        // Insertar algunos jugadores
        objetoConexion.insertarJugador("Lionel Messi", "Delantero", 10);
        objetoConexion.insertarJugador("Cristiano Ronaldo", "Delantero", 7);

        // Actualizar un jugador
        objetoConexion.actualizarJugador(1, "Leo Messi");

        // Eliminar un jugador
        objetoConexion.borrarJugador(2);
    }
}
