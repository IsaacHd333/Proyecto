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
import java.time.LocalDate;
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
public class JDSocios extends javax.swing.JDialog {
    
    // Objeto para gestionar todos los DAOs
    private DAOManager daoManager;
    
    // DAO para operaciones con socios
    private ISociosDAO sociosDAO;
    
    // DAO para relaciones entre clases y socios 
    private IClaseSocioDAO claseSocioDAO;
    
    // DAO para operaciones con clases
    private IClasesDAO clasesDAO;
    
    // Modelo de datos para la tabla de clases
    private DefaultTableModel tableModel;
    
    /**
     * 
     * Creates new form JDPeliculas
     * @param parent
     * @param modal 
     */
    public JDSocios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            // Inicializa los objetos DAO para interactuar con la base de datos
            inicializarDAOs();
        } catch (DAOException ex) {
            // Muestra error si hay problemas de conexión con la BD
            JOptionPane.showMessageDialog(this, 
                "Error al conectar con la base de datos: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDSocios.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Configura la tabla de clases
        configurarTabla();
    }
    
    /**
     * Inicializa los objetos DAO necesarios
     * @throws DAOException Si hay error al conectar con la base de datos
     */
    private void inicializarDAOs() throws DAOException {
        // Obtiene el gestor de DAOs para MySQL
        daoManager = MySQLDAOManager.getDefaultInstance();
        
        // Obtiene los DAOs específicos
        sociosDAO = daoManager.getSociosDAO(); // Para gestionar socios
        claseSocioDAO = daoManager.getClaseSocioDAO(); // Para relaciones clase-socio
        clasesDAO = daoManager.getClasesDAO(); // Para gestionar clases
    }
    
    /**
     * Configura la tabla que muestra las clases del socio
     */
    private void configurarTabla() {
        // Crea modelo de tabla con 3 columnas no editables
        tableModel = new DefaultTableModel(
            new Object[]{"ID Clase", "Nombre", "Horario"}, // Nombres columnas
            0 // 0 filas inicialmente
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no editables
            }
        };
        
        // Asigna el modelo a la tabla visual
        tableClases.setModel(tableModel);
        
        // Configura el ancho preferido de las columnas
        TableColumnModel columnModel = tableClases.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // Columna ID
        columnModel.getColumn(1).setPreferredWidth(150); // Columna Nombre
        columnModel.getColumn(2).setPreferredWidth(150); // Columna Horario
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        txtIdSocio.setText("-1"); // -1 indica nuevo registro
        txtNombre.setText(""); // Limpia nombre
        txtFechaNacimiento.setText(""); // Limpia fecha nacimiento
        txtTelefono.setText(""); // Limpia teléfono
        // Limpia la tabla de clases
        ((DefaultTableModel)tableClases.getModel()).setRowCount(0); 
    }
    
    /**
     * Carga los datos de un socio en el formulario
     * @param socio El socio a mostrar
     */
    private void cargarSocio(Socios socio) {
        // Muestra datos en los campos de texto
        txtIdSocio.setText(String.valueOf(socio.getIdSocio()));
        txtNombre.setText(socio.getNombre());
        txtFechaNacimiento.setText(socio.getFechaNacimiento());
        txtTelefono.setText(socio.getTelefono());
        
        // Carga las clases relacionadas con este socio
        cargarClasesDeSocio(socio.getIdSocio());
    }
    
    /**
     * Carga las clases de un socio específico en la tabla
     * @param idSocio El ID del socio
     */
    private void cargarClasesDeSocio(int idSocio) {
        // Obtiene el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tableClases.getModel();
        model.setRowCount(0); // Limpia la tabla
        
        try {
            // Obtiene la lista de clases para este socio
            List<Clases> clases = claseSocioDAO.obtenerClasesDeSocio(idSocio);
            
            // Agrega cada clase a la tabla
            for (Clases clase : clases) {
                model.addRow(new Object[]{
                    clase.getIdClase(),  // Columna 0: ID
                    clase.getNombre(),  // Columna 1: Nombre
                    clase.getHorario()   // Columna 2: Horario
                });
            }
        } catch (DAOException e) {
            // Muestra error si hay problemas al cargar las clases
            JOptionPane.showMessageDialog(this, 
                "Error al cargar clases: " + e.getMessage());
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
        txtIdSocio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlEntrada = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIdClase = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClases = new javax.swing.JTable();
        btnEliminarClase = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Id. Socio: ");

        txtIdSocio.setText("-1");
        txtIdSocio.setEnabled(false);

        jLabel2.setText("Nombre: ");

        jLabel3.setText("Fecha de nacimiento:");

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

        jLabel4.setText("Id.Socio: ");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Clases de Socio"));

        jLabel5.setText("<HTML>Agregar nueva clase: <br> (Escribe el Id de la clase del socio)</HTML>");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tableClases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Clase", "Nombre", "Horario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClases.setName("tableClases"); // NOI18N
        jScrollPane1.setViewportView(tableClases);

        btnEliminarClase.setText("Eliminar clase del socio");
        btnEliminarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClaseActionPerformed(evt);
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
                        .addComponent(btnEliminarClase)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtIdClase)
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
                    .addComponent(txtIdClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarClase)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jLabel6.setText("Teléfono:");

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre)
                                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(pnlEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("\"\"\n+ \"\"\n+ \"\"");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCampos(); // Limpia todos los campos
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Valida que los campos obligatorios no estén vacíos
        if (txtNombre.getText().trim().isEmpty() ||
            txtFechaNacimiento.getText().trim().isEmpty() ||
            txtTelefono.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "Debe completar todos los campos obligatorios",
                "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crea objeto Socio con los datos del formulario
            Socios socio = new Socios();
            socio.setNombre(txtNombre.getText());

            // Procesa la fecha para darle formato consistente
            try {
                DateTimeFormatter entrada = DateTimeFormatter.ofPattern("d/M/yyyy");
                DateTimeFormatter salida = DateTimeFormatter.ofPattern("dd/MM/yy");

                // Parsea y formatea la fecha
                LocalDate fecha = LocalDate.parse(txtFechaNacimiento.getText().trim(), entrada);
                String fechaFormateada = fecha.format(salida);

                socio.setFechaNacimiento(fechaFormateada);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Formato de fecha incorrecto. Use: dd/mm/yyyy",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            socio.setTelefono(txtTelefono.getText());
            
            // Determina si es nuevo registro o actualización
            if (txtIdSocio.getText().equals("-1")) {
                // Inserta nuevo socio
                sociosDAO.insertar(socio);
                JOptionPane.showMessageDialog(this, "Socio creado exitosamente");
            } else {
                // Actualiza socio existente
                socio.setIdSocio(Integer.parseInt(txtIdSocio.getText()));
                sociosDAO.modificar(socio);
                JOptionPane.showMessageDialog(this, "Socio actualizado exitosamente");
            }
            
            limpiarCampos(); // Prepara para nueva operación
            
        } catch (DAOException e) {
            // Muestra error si falla la operación en BD
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error al guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Valida que haya un socio seleccionado
        if (txtIdSocio.getText().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Seleccione un socio primero");
            return;
        }
        
        // Pide confirmación antes de eliminar
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar este socio?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Elimina el socio
                sociosDAO.eliminar(Integer.parseInt(txtIdSocio.getText()));
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Socio eliminado");
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
            
            // Busca el socio en la BD
            Socios socio = sociosDAO.obtener(id);
            
            if (socio != null) {
                // Si existe, muestra sus datos
                cargarSocio(socio);
            } else {
                JOptionPane.showMessageDialog(this,
                    "No existe socio con ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser numérico");
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error al buscar: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // Valida que haya un socio seleccionado
        if (txtIdSocio.getText().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Primero guarde el socio");
            return;
        }

        // Valida que se haya ingresado ID de clase
        if (txtIdClase.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese ID de clase");
            return;
        }

        try {
            // Obtiene IDs de socio y clase
            int idClase = Integer.parseInt(txtIdClase.getText().trim());
            int idSocio = Integer.parseInt(txtIdSocio.getText().trim());

            // Crea relación clase-socio
            ClaseSocio cs = new ClaseSocio();
            cs.setIdSocio(idSocio);
            cs.setIdClase(idClase);

            // Guarda la relación en BD
            claseSocioDAO.insertar(cs);
            
            // Actualiza la tabla de clases
            cargarClasesDeSocio(idSocio);
            txtIdClase.setText(""); // Limpia campo

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

    private void btnEliminarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClaseActionPerformed
        // Verifica que haya una clase seleccionada en la tabla
        int fila = tableClases.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una clase");
            return;
        }
        
        try {
            // Obtiene IDs de socio y clase
            int idSocio = Integer.parseInt(txtIdSocio.getText());
            int idClase = (int) tableClases.getValueAt(fila, 0);
            
            // Crea objeto relación para eliminar
            ClaseSocio cs = new ClaseSocio();
            cs.setIdSocio(idSocio);
            cs.setIdClase(idClase);
            
            // Elimina la relación
            claseSocioDAO.eliminarRelacion(cs);
            
            // Actualiza la tabla
            cargarClasesDeSocio(idSocio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar clase");
        }
    }//GEN-LAST:event_btnEliminarClaseActionPerformed

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
            java.util.logging.Logger.getLogger(JDSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton btnEliminarClase;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlEntrada;
    private javax.swing.JTable tableClases;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtIdClase;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    
}
