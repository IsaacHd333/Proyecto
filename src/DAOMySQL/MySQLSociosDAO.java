/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

// Importaciones requeridas
import DAO.DAOException;          // Excepción personalizada para el DAO
import Modelo.Socios;             // Clase entidad que representa un Socio
import java.util.List;             // Para devolver listas de objetos
import MySQLConexion.Conectar;     // Utilidad para conexión a BD
import java.sql.Connection;        // Interfaz Connection de JDBC
import java.sql.ResultSet;         // ResultSet para resultados de consultas
import java.sql.PreparedStatement; // PreparedStatement para consultas SQL parametrizadas
import java.sql.SQLException;      // Excepciones SQL
import java.util.ArrayList;        // Implementación de List
import DAO.ISociosDAO;            // Interfaz que define las operaciones del DAO

/**
 * Implementación concreta de ISociosDAO para MySQL
 * Proporciona operaciones CRUD para la entidad Socios
 */
public class MySQLSociosDAO implements ISociosDAO {
    
    // Objetos para manejo de base de datos
    private Connection conn = null;    // Conexión a la base de datos
    private ResultSet rs = null;       // Resultados de consultas SQL
    private PreparedStatement ps = null; // Sentencia SQL preparada

    // Consultas SQL como constantes para mejor mantenibilidad
    private final String INSERT = "INSERT INTO socios (nombre, fecha_nacimiento, telefono) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE socios SET nombre = ?, fecha_nacimiento = ?, telefono = ? WHERE idsocio = ?";
    private final String DELETE = "DELETE FROM socios WHERE idsocio = ?";
    private final String GETALL = "SELECT idsocio, nombre, fecha_nacimiento, telefono FROM socios";
    private final String GETONE = "SELECT idsocio, nombre, fecha_nacimiento, telefono FROM socios WHERE idsocio = ?";

    /**
     * Inserta un nuevo socio en la base de datos
     * @param socio Objeto Socios con los datos a insertar
     * @throws DAOException Si ocurre error SQL o falla la inserción
     */
    @Override
    public void insertar(Socios socio) throws DAOException {
        try {
            // 1. Establecer conexión con la BD
            conn = Conectar.realizarConexion();
            
            // 2. Preparar sentencia INSERT con parámetros
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, socio.getNombre());          // Nombre del socio
            ps.setString(2, socio.getFechaNacimiento()); // Fecha de nacimiento
            ps.setString(3, socio.getTelefono());        // Teléfono
            
            // 3. Ejecutar inserción y verificar si se insertó
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo guardar el nuevo socio");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al insertar socio en la BD", ex);
        } finally {
            // 4. Cerrar recursos siempre
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Método utilitario para cerrar recursos de BD de forma segura
     * @param ps PreparedStatement a cerrar
     * @param rs ResultSet a cerrar
     * @param conn Connection a cerrar
     */
    private void cerrarConexiones(PreparedStatement ps, ResultSet rs, Connection conn) {
        try {
            // Cerrar en orden inverso al de apertura
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            // Solo imprime el error, no relanza excepción
            ex.printStackTrace();
        }
    }
    
    /**
     * Actualiza los datos de un socio existente
     * @param socio Objeto Socios con los datos actualizados
     * @throws DAOException Si ocurre error SQL o no se encuentra el socio
     */
    @Override
    public void modificar(Socios socio) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(UPDATE);
            // Establecer parámetros para la actualización
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getFechaNacimiento());
            ps.setString(3, socio.getTelefono());
            ps.setInt(4, socio.getIdSocio()); // ID para identificar el socio
            
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo modificar el socio");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al actualizar socio en la BD", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Elimina un socio de la base de datos
     * @param idSocio ID del socio a eliminar
     * @throws DAOException Si ocurre error SQL o no se encuentra el socio
     */
    @Override
    public void eliminar(Integer idSocio) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idSocio); // Establecer ID como parámetro

            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo eliminar el socio");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar socio de la BD", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    /**
     * Obtiene todos los socios registrados en la base de datos
     * @return Lista de objetos Socios
     * @throws DAOException Si ocurre error SQL
     */
    @Override
    public List<Socios> obtenerTodos() throws DAOException {
        List<Socios> socios = new ArrayList<>();

        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETALL);
            rs = ps.executeQuery();
            
            // Procesar cada fila del resultado
            while (rs.next()) {
                Socios socio = new Socios();
                // Mapear datos de la BD al objeto Socios
                socio.setIdSocio(rs.getInt("idsocio"));
                socio.setNombre(rs.getString("nombre"));
                socio.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                socio.setTelefono(rs.getString("telefono"));
                socios.add(socio);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener socios de la BD", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return socios;
    }

    /**
     * Obtiene un socio específico por su ID
     * @param idSocio ID del socio a buscar
     * @return Objeto Socios encontrado o null si no existe
     * @throws DAOException Si ocurre error SQL
     */
    @Override
    public Socios obtener(Integer idSocio) throws DAOException {
        Socios miSocio = null;
        
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETONE);
            ps.setInt(1, idSocio); // Establecer ID como parámetro
            rs = ps.executeQuery();
            
            if (rs.next()) {
                miSocio = new Socios();
                // Mapear datos de la BD al objeto Socios
                miSocio.setIdSocio(rs.getInt("idsocio"));
                miSocio.setNombre(rs.getString("nombre"));
                miSocio.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                miSocio.setTelefono(rs.getString("telefono"));
            }
            // Si no se encuentra, devuelve null (no lanza excepción)
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener socio de la BD", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }

        return miSocio;
    }
}