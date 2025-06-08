/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import DAO.DAOException;
import DAO.DAOManager;
import DAO.ISociosDAO;
import DAO.IClaseSocioDAO;
import DAO.IClasesDAO;
import DAOMySQL.MySQLDAOManager;
import Modelo.Socios;
import Modelo.ClaseSocio;
import Modelo.Clases;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author Acer
 */
public class JDClases extends javax.swing.JDialog {
    
    // Objeto para gestionar todos los DAOs
    private DAOManager daoManager;
    
    // DAO para operaciones con socios
    private ISociosDAO sociosDAO;
    
    // DAO para relaciones entre clases y socios
    private IClaseSocioDAO claseSocioDAO;
    
    // DAO para operaciones con clases
    private IClasesDAO clasesDAO;
    
    // Modelo de datos para la tabla de socios
    private DefaultTableModel tableModel;
    
    /**
     * 
     * Creates new form JDPeliculas
     * @param parent
     * @param modal 
     */
    public JDClases(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            // Inicializa los objetos DAO para interactuar con la base de datos
            inicializarDAOs();
        } catch (DAOException ex) {
            // Muestra error si hay problemas al conectar con la BD
            JOptionPane.showMessageDialog(this, 
                "Error al conectar con la base de datos: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDClases.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Configura la tabla de socios
        configurarTabla();
    }
    
    /**
     * Inicializa los objetos DAO necesarios
     */
    private void inicializarDAOs() throws DAOException {
        // Obtiene el gestor de DAOs para MySQL
        daoManager = MySQLDAOManager.getDefaultInstance();
        
        // Obtiene los DAOs específicos
        sociosDAO = daoManager.getSociosDAO();  // Para gestionar socios
        claseSocioDAO = daoManager.getClaseSocioDAO();  // Para relaciones clase-socio
        clasesDAO = daoManager.getClasesDAO();  // Para gestionar clases
    }
    
    /**
     * Configura la tabla que muestra los socios
     */
    private void configurarTabla() {
        // Crea modelo de tabla con 3 columnas no editables
        tableModel = new DefaultTableModel(
            new Object[]{"ID Clase", "Nombre", "Horario"},  // Nombres columnas
            0  // 0 filas inicialmente
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Todas las celdas no editables
            }
        };
        
        // Asigna el modelo a la tabla visual
        tableSocios.setModel(tableModel);
        
        // Configura el ancho preferido de las columnas
        TableColumnModel columnModel = tableSocios.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);   // Columna ID
        columnModel.getColumn(1).setPreferredWidth(150);  // Columna Nombre
        columnModel.getColumn(2).setPreferredWidth(150);  // Columna Horario
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        txtIdClase.setText("-1");  // -1 indica nuevo registro
        txtNombre.setText("");  // Limpia nombre
        txtHorario.setText("");  // Limpia horario
        // Limpia la tabla de socios
        ((DefaultTableModel)tableSocios.getModel()).setRowCount(0);
    }
    
    /**
     * Carga los datos de una clase en el formulario
     * @param clase La clase a mostrar
     */
    private void cargarClase(Clases clase) {
        // Muestra datos en los campos de texto
        txtIdClase.setText(String.valueOf(clase.getIdClase()));
        txtNombre.setText(clase.getNombre());
        txtHorario.setText(clase.getHorario());
        
        // Carga los socios relacionados con esta clase
        cargarSociosDeClase(clase.getIdClase());
    }
    
    /**
     * Carga los socios de una clase específica en la tabla
     * @param idClase El ID de la clase
     */
    private void cargarSociosDeClase(int idClase) {
        // Obtiene el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tableSocios.getModel();
        model.setRowCount(0);  // Limpia la tabla
        
        try {
            // Obtiene la lista de socios para esta clase
            List<Socios> socios = claseSocioDAO.obtenerSociosDeClase(idClase);
            
            // Agrega cada socio a la tabla
            for (Socios socio : socios) {
                model.addRow(new Object[]{
                    socio.getIdSocio(),  // Columna 0: ID
                    socio.getNombre(),  // Columna 1: Nombre
                    socio.getFechaNacimiento(),  // Columna 2: Fecha nacimiento
                    socio.getTelefono()  // Columna 3: Teléfono
                });
            }
        } catch (DAOException e) {
            // Muestra error si hay problemas al cargar los socios
            JOptionPane.showMessageDialog(this, 
                "Error al cargar socios: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIdClase = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlEntrada = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIdSocio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSocios = new javax.swing.JTable();
        btnEliminarSocio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Id. Clase: ");

        txtIdClase.setText("-1");
        txtIdClase.setEnabled(false);

        jLabel2.setText("Nombre: ");

        jLabel3.setText("Horario:");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        pnlEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar una entrada por:"));

        jLabel4.setText("Id.Clase: ");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradaLayout = new javax.swing.GroupLayout(pnlEntrada);
        pnlEntrada.setLayout(pnlEntradaLayout);
        pnlEntradaLayout.setHorizontalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEntradaLayout.setVerticalGroup(
            pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setText("<HTML>Agregar nuevo socio: <br> (Escribe el Id del socio de la clase)</HTML>");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Clase", "Nombre", "Fecha Nacimiento", "Teléfono"
            }
        ));
        tableSocios.setName("tableSocios"); // NOI18N
        jScrollPane1.setViewportView(tableSocios);

        btnEliminarSocio.setText("Eliminar socio de la clase");
        btnEliminarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSocioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnEliminarSocio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtIdSocio, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar)))
                .addGap(66, 66, 66))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarSocio)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdClase, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre)
                            .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(245, 245, 245)))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar))
                    .addComponent(pnlEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(pnlEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Valida que los campos obligatorios no estén vacíos
        if (txtNombre.getText().trim().isEmpty() ||
            txtHorario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe completar todos los campos", 
                "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crea objeto Clase con los datos del formulario
            Clases clase = new Clases();
            clase.setNombre(txtNombre.getText());

            // Procesa el horario para darle formato consistente
            String horarioOriginal = txtHorario.getText().trim();
            try {
                String[] partes = horarioOriginal.split(" ");
                String dia = partes[0];  // Ej: "Lunes"
                
                // Formateadores para la hora
                DateTimeFormatter entrada = DateTimeFormatter.ofPattern("H:mm");
                DateTimeFormatter salida = DateTimeFormatter.ofPattern("hh:mm a");

                // Parsea las horas
                LocalTime inicio = LocalTime.parse(partes[1], entrada);
                LocalTime fin = LocalTime.parse(partes[3], entrada);
                
                // Crea string formateado (ej: "Lunes 09:00 AM - 10:00 AM")
                String horarioFormateado = dia + " " + inicio.format(salida) + " - " + fin.format(salida);
                clase.setHorario(horarioFormateado);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Formato de horario incorrecto. Use: Día HH:MM - HH:MM", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Determina si es nuevo registro o actualización
            if (txtIdClase.getText().equals("-1")) {
                // Inserta nueva clase
                clasesDAO.insertar(clase);
                JOptionPane.showMessageDialog(this, "Clase creada exitosamente");
            } else {
                // Actualiza clase existente
                clase.setIdClase(Integer.parseInt(txtIdClase.getText()));
                clasesDAO.modificar(clase);
                JOptionPane.showMessageDialog(this, "Clase actualizada exitosamente");
            }

            limpiarCampos();  // Prepara para nueva operación

        } catch (DAOException e) {
            // Muestra error si falla la operación en BD
            JOptionPane.showMessageDialog(this, 
                "Error al guardar: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Valida que haya una clase seleccionada
        if (txtIdClase.getText().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Seleccione una clase primero");
            return;
        }
        
        // Pide confirmación antes de eliminar
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar esta clase?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Elimina la clase
                clasesDAO.eliminar(Integer.parseInt(txtIdClase.getText()));
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Clase eliminada");
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            // Obtiene ID a buscar
            int id = Integer.parseInt(txtBuscar.getText());
            
            // Busca la clase en la BD
            Clases clase = clasesDAO.obtener(id);
            
            if (clase != null) {
                // Si existe, muestra sus datos
                cargarClase(clase);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No existe clase con ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser numérico");
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // Valida que haya una clase seleccionada
        if (txtIdClase.getText().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Primero guarde la clase");
            return;
        }

        // Valida que se haya ingresado ID de socio
        if (txtIdClase.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID de socio");
            return;
        }

        try {
            // Obtiene IDs de clase y socio
            int idSocio = Integer.parseInt(txtIdSocio.getText().trim());
            int idClase = Integer.parseInt(txtIdClase.getText().trim());

            // Crea relación clase-socio
            ClaseSocio cs = new ClaseSocio();
            cs.setIdSocio(idSocio);
            cs.setIdClase(idClase);

            // Guarda la relación en BD
            claseSocioDAO.insertar(cs);
            
            // Actualiza la tabla de socios
            cargarSociosDeClase(idClase);
            txtIdClase.setText("");  // Limpia campo

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID debe ser numérico");
        } catch (DAOException daoEx) {
            JOptionPane.showMessageDialog(this, 
                "Error en BD: " + daoEx.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSocioActionPerformed
        // Verifica que haya un socio seleccionado en la tabla
        int fila = tableSocios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un socio");
            return;
        }
        
        try {
            // Obtiene IDs de clase y socio
            int idClase = Integer.parseInt(txtIdClase.getText());
            int idSocio = (int) tableSocios.getValueAt(fila, 0);
            
            // Crea objeto relación para eliminar
            ClaseSocio cs = new ClaseSocio();
            cs.setIdSocio(idSocio);
            cs.setIdClase(idClase);
            
            // Elimina la relación
            claseSocioDAO.eliminarRelacion(cs);
            
            // Actualiza la tabla
            cargarSociosDeClase(idClase);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar socio");
        }
    }//GEN-LAST:event_btnEliminarSocioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDClases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDClases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDClases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDClases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDClases dialog = null;
                dialog = new JDClases(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarSocio;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlEntrada;
    private javax.swing.JTable tableSocios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JTextField txtIdClase;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
