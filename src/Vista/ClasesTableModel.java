/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

// Importaciones necesarias
import DAO.DAOException;           // Excepción personalizada para el DAO
import DAO.IClaseSocioDAO;         // Interfaz DAO para relación clase-socio
import Modelo.Clases;              // Clase entidad que representa una Clase
import java.util.ArrayList;        // Implementación de List
import java.util.List;             // Para trabajar con listas de objetos
import javax.swing.table.AbstractTableModel;  // Clase base para modelos de tabla

/**
 * Modelo de tabla personalizado para mostrar las clases de un socio
 * Extiende AbstractTableModel para integrarse con JTable
 */
public class ClasesTableModel extends AbstractTableModel {
    
    // Referencia al DAO para obtener datos
    private IClaseSocioDAO claseSocio;

    // Lista que almacena los datos a mostrar en la tabla
    private List<Clases> datos = new ArrayList<>();

    /**
     * Constructor que recibe el DAO necesario
     * @param clasesocio Objeto DAO para acceder a los datos
     */
    public ClasesTableModel(IClaseSocioDAO clasesocio) {
        this.claseSocio = claseSocio;
    }

    /**
     * Devuelve el nombre de la columna especificada
     * @param column Índice de la columna
     * @return Nombre de la columna como String
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Clase";
            case 1: return "Nombre";
            case 2: return "Horario";
            default: return "[N/A]";  // Valor por defecto para columnas no definidas
        }
    }

    /**
     * Devuelve el número de filas (registros) en el modelo
     * @return Cantidad de filas como entero
     */
    @Override
    public int getRowCount() {
        return datos.size();
    }
    
    /**
     * Devuelve el número de columnas en el modelo
     * @return Cantidad de columnas como entero
     */
    @Override
    public int getColumnCount() {
        return 3;  // Se corrige a 3 columnas (ID, Nombre, Horario)
    }

    /**
     * Obtiene el valor en una celda específica
     * @param rowIndex Fila de la celda
     * @param columnIndex Columna de la celda
     * @return Objeto en la posición solicitada
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Obtiene el objeto Clase correspondiente a la fila
        Clases clase = datos.get(rowIndex);
        
        // Devuelve el valor según la columna solicitada
        switch (columnIndex) {
            case 0: return clase.getIdClase();    // ID de la clase
            case 1: return clase.getNombre();     // Nombre de la clase
            case 2: return clase.getHorario();   // Horario de la clase
            default: return null;                // Para columnas no definidas
        }
    }

    /**
     * Actualiza los datos del modelo con las clases de un socio específico
     * @param idSocio ID del socio cuyas clases se quieren mostrar
     * @throws DAOException Si ocurre un error al acceder a los datos
     */
    public void updateModel(int idSocio) throws DAOException {
        // Obtiene las clases del socio desde el DAO
        this.datos = claseSocio.obtenerClasesDeSocio(idSocio);
        // Notifica a la tabla que los datos han cambiado
        fireTableDataChanged();
    }
}