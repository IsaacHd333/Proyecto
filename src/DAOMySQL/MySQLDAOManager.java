/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOManager;
import DAO.IClaseSocioDAO;
import DAO.IClasesDAO;
import DAO.ISociosDAO;

/**
 *
 * @author Acer
 */
public class MySQLDAOManager implements DAOManager {
    // Instancias de los DAOs
    private IClasesDAO clases = null;
    private ISociosDAO socios = null;
    private IClaseSocioDAO claseSocio = null;
    
    // Instancia única para Singleton
    private static MySQLDAOManager instance;
    
    /**
     * Constructor privado para el patrón Singleton
     */
    private MySQLDAOManager() {
        // Inicialización si es necesaria
    }
    
    /**
     * Obtiene la instancia única del manager (Singleton)
     * @return Instancia de MySQLDAOManager
     */
    public static synchronized MySQLDAOManager getDefaultInstance() {
        if (instance == null) {
            instance = new MySQLDAOManager();
        }
        return instance;
    }
    
    /**
     * Obtiene el DAO para gestionar Clases
     * @return Implementación de IClasesDAO para MySQL
     */
    @Override
    public IClasesDAO getClasesDAO() {
        if (clases == null) {
            clases = new MySQLClasesDAO();
        }
        return clases;
    }
    
    /**
     * Obtiene el DAO para gestionar la relación entre Clases y Socios
     * @return Implementación de IClaseSocioDAO para MySQL
     */
    @Override
    public IClaseSocioDAO getClaseSocioDAO() {
        if (claseSocio == null) {
            claseSocio = new MySQLClaseSocioDAO();
        }
        return claseSocio;
    }
    
    /**
     * Obtiene el DAO para gestionar Socios
     * @return Implementación de ISociosDAO para MySQL
     */
    @Override
    public ISociosDAO getSociosDAO() {
        if (socios == null) {
            socios = new MySQLSociosDAO();
        }
        return socios;
    }
}
