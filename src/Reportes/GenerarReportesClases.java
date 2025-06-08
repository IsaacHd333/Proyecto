/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

import MySQLConexion.Conectar; // Importa la clase para establecer la conexión a la base de datos.
import net.sf.jasperreports.engine.JRException; // Excepción específica de JasperReports.
import net.sf.jasperreports.engine.JasperFillManager; // Clase para llenar el reporte con datos.
import net.sf.jasperreports.engine.JasperPrint; // Representa el reporte llenado, listo para visualizar o exportar.
import net.sf.jasperreports.engine.JasperReport; // Representa el objeto reporte compilado.
import net.sf.jasperreports.engine.util.JRLoader; // Utilidad para cargar objetos de JasperReports.
import net.sf.jasperreports.view.JasperViewer; // Visor para mostrar reportes de JasperReports.

import javax.swing.JOptionPane; // Para mostrar cuadros de diálogo informativos o de error.
import java.awt.Window; // Clase base para todas las ventanas de nivel superior.

import java.io.InputStream; // Para leer el archivo .jasper como un flujo de entrada.
import java.sql.Connection; // Para la conexión a la base de datos.
import java.sql.SQLException; // Excepción para errores de SQL.
import java.util.HashMap; // Para crear mapas de parámetros.
import java.util.Map; // Interfaz para mapas de clave-valor.

/**
 * La clase `GenerarReportesClases` se encarga de gestionar la generación y visualización
 * de reportes de JasperReports, específicamente el reporte de "Catálogo de Clases".
 * Utiliza una conexión a la base de datos para llenar el reporte con información.
 *
 * @author Acer
 */
public class GenerarReportesClases {

    /**
     * Muestra el reporte de clases en una ventana de JasperViewer.
     * Establece una conexión a la base de datos, carga el archivo .jasper del reporte,
     * lo llena con los datos obtenidos de la conexión y lo muestra en un visor.
     *
     * @param parentWindow La ventana padre desde la cual se invoca este método.
     * Se utiliza para centrar el visor del reporte y para
     * mostrar los cuadros de diálogo de error.
     */
    public void mostrarReporteClases(Window parentWindow) {
        Connection conn = null; // Variable para almacenar la conexión a la base de datos.
        try {
            // Intenta establecer una conexión a la base de datos.
            conn = Conectar.realizarConexion();
            System.out.println("Conexión a la base de datos establecida.");

            // Carga el archivo compilado del reporte (.jasper) desde los recursos del classpath.
            // Se asume que el archivo 'reporteclases_1.jasper' se encuentra en la carpeta 'reportes'
            // dentro de los recursos del proyecto.
            InputStream jasperStream = getClass().getResourceAsStream("/reportes/reporteclases_1.jasper");

            // Verifica si el archivo .jasper se encontró correctamente.
            if (jasperStream == null) {
                // Si el archivo no se encuentra, muestra un mensaje de error y sale del método.
                JOptionPane.showMessageDialog(parentWindow,
                        "Error: No se encontró el archivo del reporte 'reporteclases_1.jasper'.\n" +
                        "Asegúrate de que está en la carpeta 'reportes' de tu proyecto y que el nombre es correcto.",
                        "Archivo de Reporte no Encontrado", JOptionPane.ERROR_MESSAGE);
                return; // Sale del método si el archivo no se encuentra.
            }

            // Carga el objeto JasperReport a partir del flujo de entrada del archivo .jasper.
            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);

            // Crea un mapa para los parámetros del reporte. En este caso, no se usan parámetros.
            Map<String, Object> parametros = new HashMap<>();

            // Llena el reporte con los datos de la conexión.
            // El primer argumento es el reporte compilado, el segundo son los parámetros
            // y el tercero es la conexión a la base de datos.
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);

            // Crea un visor de JasperReports para mostrar el reporte llenado.
            // El segundo argumento 'false' indica que el visor no debe cerrarse al salir de la aplicación principal.
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Catálogo de Clases"); // Establece el título de la ventana del visor.
            viewer.setAlwaysOnTop(false); // Permite que otras ventanas puedan superponerse al visor.
            viewer.setLocationRelativeTo(parentWindow); // Centra el visor en relación con la ventana padre.
            viewer.setVisible(true); // Hace visible la ventana del visor.

        } catch (SQLException ex) {
            // Captura y maneja excepciones relacionadas con la base de datos.
            JOptionPane.showMessageDialog(parentWindow, "Error de base de datos al generar el reporte:\n" + ex.getMessage(), "Error de DB", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } catch (JRException ex) {
            // Captura y maneja excepciones específicas de JasperReports.
            JOptionPane.showMessageDialog(parentWindow, "Error al procesar el reporte de JasperReports:\n" + ex.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } catch (Exception ex) {
            // Captura cualquier otra excepción inesperada.
            JOptionPane.showMessageDialog(parentWindow, "Ha ocurrido un error inesperado al generar el reporte:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } finally {
            // Bloque finally para asegurar que la conexión a la base de datos se cierre.
            if (conn != null) { // Verifica si la conexión no es nula.
                try {
                    Conectar.realizarDesconexion(); // Intenta cerrar la conexión a la base de datos.
                } catch (SQLException ex) {
                    // Si ocurre un error al cerrar la conexión, lo imprime en la consola de errores.
                    System.err.println("Error al cerrar la conexión a la base de datos: " + ex.getMessage());
                    ex.printStackTrace(); // Imprime la traza de la pila para depuración.
                }
            }
        }
    }
}