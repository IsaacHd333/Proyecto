/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

// Importaciones necesarias
import DAO.DAOException;
import DAO.IClaseSocioDAO;
import Modelo.ClaseSocio;
import Modelo.Clases;
import Modelo.Socios;
import MySQLConexion.Conectar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de IClaseSocioDAO para MySQL
 * @author Acer
 */
public class MySQLClaseSocioDAO implements IClaseSocioDAO {

    // CONSTANTES CON CONSULTAS SQL
    private final String INSERT = "INSERT INTO clase_socio(idsocio, idclase) VALUES(?, ?)";
    private final String DELETE = "DELETE FROM clase_socio WHERE idsocio=? AND idclase=?";
    private final String DELETE_BY_ID = "DELETE FROM clase_socio WHERE id=?";
    private final String GET_CLASES = "SELECT c.* FROM clases c JOIN clase_socio cs ON c.idclase=cs.idclase WHERE cs.idsocio=?";
    private final String GET_SOCIOS = "SELECT s.* FROM socios s JOIN clase_socio cs ON s.idsocio=cs.idsocio WHERE cs.idclase=?";
    private final String GET_ALL = "SELECT * FROM clase_socio";
    private final String GET_ONE = "SELECT * FROM clase_socio WHERE id=?";

    /**
     * Obtiene todas las clases asociadas a un socio específico
     * @param idSocio ID del socio a consultar
     * @return Lista de objetos Clases
     * @throws DAOException Si ocurre un error en la base de datos
     */
    @Override
    public List<Clases> obtenerClasesDeSocio(int idSocio) throws DAOException {
        List<Clases> clases = new ArrayList<>();
        
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(GET_CLASES)) {
            
            // Establecer parámetro en la consulta
            ps.setInt(1, idSocio);
            
            try (ResultSet rs = ps.executeQuery()) {
                // Procesar cada fila del resultado
                while (rs.next()) {
                    Clases clase = new Clases();
                    // Mapear datos del ResultSet al objeto Clases
                    clase.setIdClase(rs.getInt("idclase"));
                    clase.setNombre(rs.getString("nombre"));
                    clase.setHorario(rs.getString("horario"));
                    clases.add(clase);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener clases", ex);
        }
        
        return clases;
    }

    /**
     * Obtiene todos los socios inscritos en una clase específica
     * @param idClase ID de la clase a consultar
     * @return Lista de objetos Socios
     * @throws DAOException Si ocurre un error en la base de datos
     */
    @Override
    public List<Socios> obtenerSociosDeClase(int idClase) throws DAOException {
        List<Socios> socios = new ArrayList<>();
        
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(GET_SOCIOS)) {
            
            ps.setInt(1, idClase);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Socios socio = new Socios();
                    // Mapear datos del ResultSet al objeto Socios
                    socio.setIdSocio(rs.getInt("idsocio"));
                    socio.setNombre(rs.getString("nombre"));
                    socio.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    socio.setTelefono(rs.getString("telefono"));
                    socios.add(socio);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener socios", ex);
        }
        
        return socios;
    }

    /**
     * Elimina una relación específica entre socio y clase
     * @param a Objeto ClaseSocio con la relación a eliminar
     * @throws DAOException Si ocurre un error en la base de datos o no se encuentra la relación
     */
    @Override
    public void eliminarRelacion(ClaseSocio a) throws DAOException {
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            
            // Establecer parámetros
            ps.setInt(1, a.getIdSocio());
            ps.setInt(2, a.getIdClase());
            
            // Verificar si se eliminó alguna fila
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se eliminó ninguna relación");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar relación", ex);
        }
    }

    /**
     * Crea una nueva relación entre socio y clase
     * @param a Objeto ClaseSocio con la nueva relación
     * @throws DAOException Si ocurre un error en la base de datos o no se pudo crear la relación
     */
    @Override
    public void insertar(ClaseSocio a) throws DAOException {
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            
            ps.setInt(1, a.getIdSocio());
            ps.setInt(2, a.getIdClase());
            
            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo crear la relación");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al crear relación socio-clase", ex);
        }
    }

    /**
     * Método no implementado (operación no soportada)
     * @param a Objeto a modificar
     * @throws DAOException Siempre lanza UnsupportedOperationException
     */
    @Override
    public void modificar(ClaseSocio a) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina una relación por su ID
     * @param id ID de la relación a eliminar
     * @throws DAOException Si ocurre un error en la base de datos
     */
    @Override
    public void eliminar(String id) throws DAOException {
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID)) {
            
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar relación por ID", ex);
        }
    }

    /**
     * Obtiene todas las relaciones socio-clase existentes
     * @return Lista de objetos ClaseSocio
     * @throws DAOException Si ocurre un error en la base de datos
     */
    @Override
    public List<ClaseSocio> obtenerTodos() throws DAOException {
        List<ClaseSocio> relaciones = new ArrayList<>();
        
        try (Connection conn = Conectar.realizarConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(GET_ALL)) {
            
            while (rs.next()) {
                ClaseSocio cs = new ClaseSocio();
                cs.setIdSocio(rs.getInt("idsocio"));
                cs.setIdClase(rs.getInt("idclase"));
                relaciones.add(cs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener todas las relaciones", ex);
        }
        return relaciones;
    }

    /**
     * Obtiene una relación específica por su ID
     * @param id ID de la relación a buscar
     * @return Objeto ClaseSocio encontrado o null si no existe
     * @throws DAOException Si ocurre un error en la base de datos
     */
    @Override
    public ClaseSocio obtener(String id) throws DAOException {
        try (Connection conn = Conectar.realizarConexion();
             PreparedStatement ps = conn.prepareStatement(GET_ONE)) {
            
            ps.setInt(1, Integer.parseInt(id));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClaseSocio cs = new ClaseSocio();
                    cs.setIdSocio(rs.getInt("idsocio"));
                    cs.setIdClase(rs.getInt("idclase"));
                    return cs;
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al obtener relación por ID", ex);
        }
        return null;
    }
}
