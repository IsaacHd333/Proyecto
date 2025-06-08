package Reportes;

import MySQLConexion.Conectar; // Importa tu clase de conexión a la base de datos.
import net.sf.jasperreports.engine.JRException; // Excepción específica de JasperReports.
import net.sf.jasperreports.engine.JasperFillManager; // Clase para llenar el reporte con datos.
import net.sf.jasperreports.engine.JasperPrint; // Representa el reporte llenado, listo para visualizar o exportar.
import net.sf.jasperreports.engine.JasperReport; // Representa el objeto reporte compilado.
import net.sf.jasperreports.engine.util.JRLoader; // Utilidad para cargar objetos de JasperReports.
import net.sf.jasperreports.view.JasperViewer; // Visor para mostrar reportes de JasperReports.

import javax.swing.JOptionPane; // Para mostrar cuadros de diálogo informativos o de error.
import java.awt.Window; // Importa Window, que es un tipo genérico para JFrame o JDialog, usado para la ventana padre.

import java.io.InputStream; // Para leer el archivo .jasper como un flujo de entrada.
import java.sql.Connection; // Para la conexión a la base de datos.
import java.sql.SQLException; // Excepción para errores de SQL.
import java.util.HashMap; // Para crear mapas de parámetros.
import java.util.Map; // Interfaz para mapas de clave-valor.

/**
 * La clase `GenerarReportesSocios` se encarga de gestionar la generación y visualización
 * de reportes de JasperReports, específicamente el reporte de "Catálogo de Socios".
 * Establece una conexión a la base de datos para llenar el reporte con la información
 * de los socios y luego lo muestra en un visor.
 *
 * @author Acer
 */
public class GenerarReportesSocios {

    /**
     * Muestra el reporte de socios en una ventana de JasperViewer.
     * Realiza los siguientes pasos:
     * 1. Establece una conexión a la base de datos.
     * 2. Carga el archivo compilado del reporte `.jasper` desde los recursos del proyecto.
     * 3. Prepara los parámetros necesarios para el reporte (si los hay).
     * 4. Llena el reporte con los datos obtenidos de la conexión a la base de datos.
     * 5. Muestra el reporte llenado en una ventana de JasperViewer.
     * Maneja diversas excepciones que pueden ocurrir durante el proceso (SQL, JasperReports, genéricas).
     *
     * @param parentWindow La ventana padre desde la cual se invoca este método.
     * Se utiliza para centrar el visor del reporte y para
     * mostrar los cuadros de diálogo de error en relación con esta ventana.
     */
    public void mostrarReporteSocios(Window parentWindow) {
        Connection conn = null; // Declara una variable para la conexión a la base de datos, inicializada a null.
        try {
            // 1. Intenta establecer la conexión a la base de datos utilizando la clase 'Conectar'.
            conn = Conectar.realizarConexion();
            System.out.println("Conexión a la base de datos establecida."); // Mensaje de confirmación en consola.

            // 2. Carga el archivo compilado del reporte (.jasper) desde los recursos del classpath.
            // Se espera que 'reportsocios_1.jasper' esté en la carpeta 'reportes' dentro de los recursos del proyecto.
            InputStream jasperStream = getClass().getResourceAsStream("/reportes/reportsocios_1.jasper");

            // Verifica si el archivo .jasper se encontró correctamente.
            if (jasperStream == null) {
                // Si el archivo no se encuentra, muestra un mensaje de error al usuario.
                JOptionPane.showMessageDialog(parentWindow,
                        "Error: No se encontró el archivo del reporte 'reportsocios_1.jasper'.\n" +
                        "Asegúrate de que está en la carpeta 'reportes' de tu proyecto y que el nombre es correcto.",
                        "Archivo de Reporte no Encontrado", JOptionPane.ERROR_MESSAGE);
                return; // Es crucial salir del método si el stream es nulo para evitar NullPointerException.
            }

            // 3. Cargar el objeto JasperReport a partir del flujo de entrada del archivo .jasper.
            JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);

            // 4. Preparar los parámetros del reporte.
            // Se utiliza un HashMap para almacenar los parámetros.
            // En este caso, el mapa está vacío, lo que indica que el reporte no requiere parámetros de entrada.
            Map<String, Object> parametros = new HashMap<>();
            // Ejemplo de cómo agregar un parámetro si el reporte lo necesitara:
            // parametros.put("TITULO_REPORTE", "Catálogo de Socios del Gimnasio");

            // 5. Llenar el reporte con los datos de la base de datos.
            // `fillReport` toma el reporte compilado, los parámetros y la conexión a la base de datos.
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);

            // 6. Mostrar el reporte en una ventana de visor de JasperReports.
            // El segundo argumento `false` permite que la aplicación principal continúe ejecutándose independientemente del visor.
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Catálogo de Socios"); // Establece el título de la ventana del visor.
            viewer.setAlwaysOnTop(false); // Configura la ventana para que no esté siempre por encima de otras aplicaciones.
            viewer.setLocationRelativeTo(parentWindow); // Centra la ventana del visor en relación con la ventana padre.
            viewer.setVisible(true); // Hace visible la ventana del visor.

        } catch (SQLException ex) {
            // Captura y maneja excepciones relacionadas con operaciones de base de datos (ej. problemas de conexión, errores SQL).
            JOptionPane.showMessageDialog(parentWindow, "Error de base de datos al generar el reporte:\n" + ex.getMessage(), "Error de DB", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } catch (JRException ex) {
            // Captura y maneja excepciones específicas de JasperReports (ej. reporte dañado, problemas al llenar).
            JOptionPane.showMessageDialog(parentWindow, "Error al procesar el reporte de JasperReports:\n" + ex.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } catch (Exception ex) { // Captura cualquier otra excepción inesperada.
            JOptionPane.showMessageDialog(parentWindow, "Ha ocurrido un error inesperado al generar el reporte:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila para depuración.
        } finally {
            // El bloque `finally` asegura que este código se ejecute siempre, sin importar si hubo una excepción o no.
            // Su propósito principal aquí es cerrar la conexión a la base de datos de manera segura.
            if (conn != null) { // Verifica si la conexión se estableció (no es nula).
                try {
                    // Cierra la conexión a la base de datos utilizando el método 'realizarDesconexion' de la clase 'Conectar'.
                    Conectar.realizarDesconexion();
                } catch (SQLException ex) {
                    // Si ocurre un error al cerrar la conexión, lo imprime en la consola de errores.
                    System.err.println("Error al cerrar la conexión a la base de datos: " + ex.getMessage());
                    ex.printStackTrace(); // Imprime la traza de la pila para depuración.
                }
            }
        }
    }
}