/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

// Importaciones necesarias
import DAO.DAOException;          // Excepción personalizada para el DAO
import Modelo.Clases;             // Clase entidad que representa una Clase
import java.util.List;             // Para devolver listas de objetos
import MySQLConexion.Conectar;     // Utilidad para conexión a BD
import java.sql.Connection;        // Interfaz Connection de JDBC
import java.sql.ResultSet;         // ResultSet para resultados de consultas
import java.sql.PreparedStatement; // PreparedStatement para consultas SQL
import java.sql.SQLException;      // Excepciones SQL
import java.util.ArrayList;        // Implementación de List
import DAO.IClasesDAO;            // Interfaz que define las operaciones del DAO

/**
 * Implementación concreta de ICursosDAO para MySQL
 * Proporciona operaciones CRUD para la entidad Clases
 */
public class MySQLClasesDAO implements IClasesDAO {
    
    // Objetos para la conexión a BD
    private Connection conn = null;    // Conexión a la base de datos
    private ResultSet rs = null;       // Resultados de consultas
    private PreparedStatement ps = null; // Sentencia SQL preparada

    // Consultas SQL como constantes para mejor mantenibilidad
    private final String INSERT = "INSERT INTO clases (nombre, horario) VALUES (?, ?)";
    private final String UPDATE = "UPDATE clases SET nombre = ?, horario = ? WHERE idclase = ?";
    private final String DELETE = "DELETE FROM clases WHERE idclase = ?";
    private final String GETALL = "SELECT idclase, nombre, horario FROM clases";
    private final String GETONE = "SELECT idclase, nombre, horario FROM clases WHERE idclase = ?";

    /**
     * Inserta una nueva clase en la base de datos
     * @param clase Objeto Clases con los datos a insertar
     * @throws DAOException Si ocurre error SQL o falla la inserción
     */
    @Override
    public void insertar(Clases clase) throws DAOException {
        try {
            // 1. Establecer conexión con la BD
            conn = Conectar.realizarConexion();
            
            // 2. Preparar sentencia INSERT con parámetros
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, clase.getNombre());    // Establecer parámetro nombre
            ps.setString(2, clase.getHorario());  // Establecer parámetro horario
            
            // 3. Ejecutar inserción y verificar si se insertó
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo guardar la nueva clase");
            }
        } catch (SQLException ex) {
            // 4. Manejar excepciones SQL
            throw new DAOException("Error de SQL al insertar clase", ex);
        } finally {
            // 5. Cerrar recursos siempre
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Método utilitario para cerrar recursos de BD de forma segura
     * @param ps PreparedStatement a cerrar
     * @param rs ResultSet a cerrar
     * @param conn Connection a cerrar
     * @throws DAOException Si ocurre error al cerrar los recursos
     */
    private void cerrarConexiones(PreparedStatement ps, ResultSet rs, Connection conn) throws DAOException {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            throw new DAOException("Error al cerrar conexiones", ex);
        }
    }
    
    /**
     * Modifica una clase existente en la BD
     * @param clase Objeto Clases con los datos actualizados
     * @throws DAOException Si ocurre error SQL o no se encuentra la clase
     */
    @Override
    public void modificar(Clases clase) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(UPDATE);
            // Establecer parámetros para la actualización
            ps.setString(1, clase.getNombre());
            ps.setString(2, clase.getHorario());
            ps.setInt(3, clase.getIdClase()); // ID para identificar la clase a actualizar
            
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo modificar la clase");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL al actualizar clase", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Elimina una clase de la BD por su ID
     * @param idClase ID de la clase a eliminar
     * @throws DAOException Si ocurre error SQL o no se encuentra la clase
     */
    @Override
    public void eliminar(Integer idClase) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idClase); // Establecer ID como parámetro

            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo eliminar la clase");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL al eliminar clase", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Obtiene todas las clases de la BD
     * @return Lista de objetos Clases
     * @throws DAOException Si ocurre error SQL
     */
    @Override
    public List<Clases> obtenerTodos() throws DAOException {
        List<Clases> clases = new ArrayList<>();

        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETALL);
            rs = ps.executeQuery();
            
            // Procesar cada fila del resultado
            while (rs.next()) {
                Clases clase = new Clases();
                // Mapear datos de la BD al objeto Clases
                clase.setIdClase(rs.getInt("idclase"));
                clase.setNombre(rs.getString("nombre"));
                clase.setHorario(rs.getString("horario"));
                clases.add(clase);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL al obtener clases", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return clases;
    }

    /**
     * Obtiene una clase específica por su ID
     * @param idClase ID de la clase a buscar
     * @return Objeto Clases encontrado
     * @throws DAOException Si ocurre error SQL o no se encuentra la clase
     */
    @Override
    public Clases obtener(Integer idClase) throws DAOException {
        Clases miClase = null;

        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETONE);
            ps.setInt(1, idClase); // Establecer ID como parámetro
            rs = ps.executeQuery();
            
            if (rs.next()) {
                miClase = new Clases();
                // Mapear datos de la BD al objeto Clases
                miClase.setIdClase(rs.getInt("idclase"));
                miClase.setNombre(rs.getString("nombre"));
                miClase.setHorario(rs.getString("horario"));
            } else {
                throw new DAOException("No se encontró la clase");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL al obtener clase", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return miClase;
    }
}
