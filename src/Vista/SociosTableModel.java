/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import DAO.DAOException;
import DAO.IClaseSocioDAO;
import Modelo.Socios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Acer
 */
public class SociosTableModel extends AbstractTableModel {
    //Propiedades
    private IClaseSocioDAO claseSocio;

    //lista de elementos de tipo Socios
    private List<Socios> datos = new ArrayList<>();

    //constructor con un parámetro
    public SociosTableModel (IClaseSocioDAO clasesocio){
        this.claseSocio = claseSocio;
    }

    /**
     * retorna el nombre de cada columna
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Socio";
            case 1: return "Nombre";
            case 2: return "Fecha Nacimiento";
            case 3: return "Teléfono";
            default: return "[N/A]";
        }
    }

    /**
     * retorna el número de elementos obtenidos de la tabla titulos
     * @return
     */
    @Override
    public int getRowCount() {
        return datos.size();
    }
    
    /**
     * retorna el número de columnas
     * @return
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     * retorna el valor que contenga la intersección de una fila y columna
     * pasadas como parámetro
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Socios socio = datos.get(rowIndex);
        switch (columnIndex) {
            case 0: return socio.getIdSocio();
            case 1: return socio.getNombre();
            case 2: return socio.getFechaNacimiento();
            case 3: return socio.getTelefono();
            default: return null;
        }
    }

    /**
     * Muestra una lista de la tabla Generos basados en su Pelicula
     * @param datos
     */
    public void updateModel(int idClase) throws DAOException {
        this.datos = claseSocio.obtenerSociosDeClase(idClase);
    }
}
