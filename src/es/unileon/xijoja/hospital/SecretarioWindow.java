package es.unileon.xijoja.hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.admin.AdminWindow;





//TODO panel despedir empleados
//TODO problema con los ID al eliminar trabajadores

@SuppressWarnings("serial")
public class SecretarioWindow extends JFrame {
 
    Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos
 
    private JPanel seeEmployeesPanel;
    private JPanel addEmployeePane;
    Toolkit screen;
 
    private JTextField textFieldName;
    private JTextField textFieldSurname1;
    private JTextField textFieldSurname2;
    private JTextField textFieldNIFNIE;
    private JTextField textFieldHabitacion;
 
    private JLabel lblError;
 
    private PacientesDAO dao;
  
    private JPanel editEmployeesPanel;
    private JLabel label;
    private JLabel label_1;
    private JTextField textFieldNameEdit;
    private JTextField textFieldSurname1Edit;
    private JLabel label_2;
    private JTextField textFieldDNIEdit;
    private JLabel label_3;
    private JTextField textFieldBankEdit;
    private JLabel label_4;
    private JComboBox<Object> comboBox;
    private JLabel label_5;
    private JTextField textFieldEmailEdit;
    private JLabel labelUserNameEdit;
    private JLabel labelPasswordEdit;
    private JButton button;
    private JLabel label_8;
    private JTextField textFieldSurname2Edit;
    private JLabel label_9;
    private JLabel label_10;
    private JTextField textFieldSearch;
 
    public SecretarioWindow() {
	
        screen = Toolkit.getDefaultToolkit();
 
        setBounds(1024 / 4, 768 / 10, 969, 496);
 
        setUndecorated(true);
 
        setTitle("Secretario");
 
        try {
            dao = new PacientesDAO();
            initComponents();
            initComponentsPanels();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    
    /*
     * TODO comprobar que se lanza desde loginwindow
     * 
     * 
     * 
     */
    private void initComponents() throws SQLException {
 
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
  
        listener list = new listener();
 
        JButton crossButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/cross.png")));
        crossButton.setBounds(944, 11, 15, 15);
        getContentPane().add(crossButton);
        crossButton.setBackground(null);
        crossButton.setBorder(null);
        crossButton.setOpaque(false);
        crossButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
 
        JButton minButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/min.png")));
        minButton.setBounds(918, 11, 15, 15);
        getContentPane().add(minButton);
        minButton.setBorder(null);
        minButton.setBackground(null);
        minButton.setOpaque(false);
        minButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.CROSSHAIR_CURSOR);
            }
        });
 
        JButton btnNewButton = new JButton("A�adir Paciente");
        btnNewButton.setOpaque(false);
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(28, 33, 234, 41);
        getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(list);
 
     
        JButton btnEditEmployee = new JButton("Ver habitaci�n");
        btnEditEmployee.setBackground(Color.WHITE);
        btnEditEmployee.setOpaque(false);
        btnEditEmployee.setBounds(28, 85, 234, 41);
        getContentPane().add(btnEditEmployee);
        btnEditEmployee.addActionListener(list);
 
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(274, 11, 7, 474);
        getContentPane().add(separator);
 
        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(Color.BLACK);
        separator_1.setBounds(10, 21, 7, 41);
        getContentPane().add(separator_1);
 
        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.BLACK);
        separator_2.setBounds(20, 11, 117, 8);
        getContentPane().add(separator_2);
 
       
 
    }
 
    private void initComponentsPanels() throws SQLException {
    	
    	 listener list = new listener();
 
 
        seeEmployeesPanel = new JPanel();
        seeEmployeesPanel.setLayout(null);
        seeEmployeesPanel.setForeground(Color.WHITE);
        seeEmployeesPanel.setBackground(Color.WHITE);
        seeEmployeesPanel.setBounds(284, 11, 624, 450);
        seeEmployeesPanel.setPreferredSize(new Dimension(630, 700));
        seeEmployeesPanel.setVisible(false);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        editEmployeesPanel = new JPanel();
        editEmployeesPanel.setBounds(278, 11, 630, 450);
        getContentPane().add(editEmployeesPanel);
        editEmployeesPanel.setLayout(null);
        editEmployeesPanel.setForeground(Color.WHITE);
        editEmployeesPanel.setBackground(Color.WHITE);
        editEmployeesPanel.setVisible(false);
 
        label = new JLabel("Nombre");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(10, 74, 63, 23);
        editEmployeesPanel.add(label);
 
        label_1 = new JLabel("1� Apellido");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_1.setBounds(10, 125, 80, 23);
        editEmployeesPanel.add(label_1);
 
        textFieldNameEdit = new JTextField();
        textFieldNameEdit.setColumns(10);
        textFieldNameEdit.setBounds(100, 77, 143, 20);
        editEmployeesPanel.add(textFieldNameEdit);
 
        textFieldSurname1Edit = new JTextField();
        textFieldSurname1Edit.setColumns(10);
        textFieldSurname1Edit.setBounds(100, 128, 143, 20);
        editEmployeesPanel.add(textFieldSurname1Edit);
 
        label_2 = new JLabel("NIF/NIE");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_2.setBounds(10, 217, 63, 23);
        editEmployeesPanel.add(label_2);
 
        textFieldDNIEdit = new JTextField();
        textFieldDNIEdit.setColumns(10);
        textFieldDNIEdit.setBounds(100, 220, 143, 20);
        editEmployeesPanel.add(textFieldDNIEdit);
 
        label_3 = new JLabel("Cuenta bancaria");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_3.setBounds(10, 262, 120, 23);
        editEmployeesPanel.add(label_3);
 
        textFieldBankEdit = new JTextField();
        textFieldBankEdit.setColumns(10);
        textFieldBankEdit.setBounds(132, 265, 143, 20);
        editEmployeesPanel.add(textFieldBankEdit);
 
        label_4 = new JLabel("Puesto");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_4.setBounds(390, 74, 63, 23);
        editEmployeesPanel.add(label_4);
 
        comboBox = new JComboBox<Object>();
        comboBox.setOpaque(false);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(450, 76, 133, 23);
        editEmployeesPanel.add(comboBox);
 
        label_5 = new JLabel("Email");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_5.setBounds(390, 125, 63, 23);
        editEmployeesPanel.add(label_5);
 
        textFieldEmailEdit = new JTextField();
        textFieldEmailEdit.setColumns(10);
        textFieldEmailEdit.setBounds(450, 128, 133, 20);
        editEmployeesPanel.add(textFieldEmailEdit);
 
        labelUserNameEdit = new JLabel("");
        labelUserNameEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelUserNameEdit.setBorder(new TitledBorder("Usuario"));
        labelUserNameEdit.setBounds(390, 170, 193, 41);
        editEmployeesPanel.add(labelUserNameEdit);
 
        labelPasswordEdit = new JLabel("");
        labelPasswordEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelPasswordEdit.setBorder(new TitledBorder("Constrase�a"));
        labelPasswordEdit.setBounds(390, 225, 193, 41);
        editEmployeesPanel.add(labelPasswordEdit);
 
        button = new JButton("Registrar");
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setBounds(390, 307, 212, 47);
        editEmployeesPanel.add(button);
 
        label_8 = new JLabel("2� Apellido");
        label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_8.setBounds(10, 170, 80, 23);
        editEmployeesPanel.add(label_8);
 
        textFieldSurname2Edit = new JTextField();
        textFieldSurname2Edit.setColumns(10);
        textFieldSurname2Edit.setBounds(100, 173, 143, 20);
        editEmployeesPanel.add(textFieldSurname2Edit);
 
        label_9 = new JLabel("");
        label_9.setForeground(Color.RED);
        label_9.setBounds(390, 241, 212, 14);
        editEmployeesPanel.add(label_9);
 
        label_10 = new JLabel("New label");
        label_10.setBounds(43, 292, 252, 194);
        editEmployeesPanel.add(label_10);
 
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 52, 610, 33);
        editEmployeesPanel.add(separator);
 
        JLabel lblNewLabel = new JLabel("Introduce nombre y apellidos o DNI");
        lblNewLabel.setBounds(25, 11, 193, 23);
        editEmployeesPanel.add(lblNewLabel);
 
        textFieldSearch = new JTextField();
        textFieldSearch.setBounds(209, 12, 150, 20);
        editEmployeesPanel.add(textFieldSearch);
        textFieldSearch.setColumns(10);
       
    
   
 
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
        addEmployeePane = new JPanel();
        addEmployeePane.setForeground(Color.WHITE);
        addEmployeePane.setBackground(Color.WHITE);
        addEmployeePane.setBounds(284, 11, 630, 450);
        getContentPane().add(addEmployeePane);
        addEmployeePane.setLayout(null);
 
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 27, 63, 23);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addEmployeePane.add(lblNombre);
 
        JLabel lblApellidos = new JLabel("1� Apellido");
        lblApellidos.setBounds(10, 78, 80, 23);
        lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addEmployeePane.add(lblApellidos);
 
        textFieldName = new JTextField();
        textFieldName.setBounds(100, 30, 143, 20);
        addEmployeePane.add(textFieldName);
        textFieldName.setColumns(10);
 
        textFieldSurname1 = new JTextField();
        textFieldSurname1.setBounds(100, 81, 143, 20);
        addEmployeePane.add(textFieldSurname1);
        textFieldSurname1.setColumns(10);
 
        JLabel lblNifnie = new JLabel("NIF/NIE");
        lblNifnie.setBounds(10, 170, 63, 23);
        lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addEmployeePane.add(lblNifnie);
 
        textFieldNIFNIE = new JTextField();
        textFieldNIFNIE.setBounds(100, 173, 143, 20);
        textFieldNIFNIE.setColumns(10);
        addEmployeePane.add(textFieldNIFNIE);
 
        JLabel lblFecha = new JLabel("Habitacion");
        lblFecha.setBounds(390, 27, 93, 23);
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addEmployeePane.add(lblFecha);
 
        
        
        textFieldHabitacion = new JTextField();
        textFieldHabitacion.setBounds(470, 30, 103, 20);
        textFieldHabitacion.setColumns(10);
        addEmployeePane.add(textFieldHabitacion);
        
    
 
        JButton btnRegister = new JButton("A�adir");
        btnRegister.setBounds(390, 260, 212, 47);
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setOpaque(false);
        addEmployeePane.add(btnRegister);
        btnRegister.addActionListener(list);
  
        JLabel lblApellido = new JLabel("2� Apellido");
        lblApellido.setBounds(10, 123, 80, 23);
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addEmployeePane.add(lblApellido);
 
        textFieldSurname2 = new JTextField();
        textFieldSurname2.setBounds(100, 126, 143, 20);
        textFieldSurname2.setColumns(10);
        addEmployeePane.add(textFieldSurname2);
 
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(390, 241, 212, 14);
        addEmployeePane.add(lblError);
 
        JLabel iconLabel = new JLabel("New label");
        iconLabel.setBounds(43, 245, 252, 194);
        addEmployeePane.add(iconLabel);
        iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
        getContentPane().add(seeEmployeesPanel);
 
    }
 
    public class listener implements ActionListener {
    	 
        @Override
        public void actionPerformed(ActionEvent arg0) {
 
            if (arg0.getActionCommand().equals("A�adir")) {////////////////////////////////// A�adir
 
                boolean add = true;
 
                if ((textFieldName.getText().equals("")) || (textFieldSurname1.getText().equals(""))
                        || (textFieldSurname2.getText().equals("")) || (textFieldNIFNIE.getText().equals(""))
                        || (textFieldHabitacion.getText().equals(""))) {// Comprobamos
                    // si algum
                    // campo esta
                    // vacio
 
                    add = false;
                    lblError.setText("Hay campos vacios");
                } else {
                    lblError.setText("");
                }
 
                if (add) {// Si da error no se a�ade el empleado
                    System.out.println("Correcto");
                    
                  //TODO getlast id?� para que�?
                    
                    int id = dao.getLastID();
 
                    Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
 
                    try {
 
                        dao.addPaciente(id, textFieldName.getText(), textFieldSurname1.getText(),
                                textFieldSurname2.getText(), textFieldNIFNIE.getText(), date,
                                Integer.parseInt(textFieldHabitacion.getText()));// LLamamos a la
                                                                                                    // funcion del DAO
                                                                                                    // que inserta el
                                                                                                    // paciente
                    } catch (SQLException e1) {
 
                        e1.printStackTrace();
                    }
//                    try {
//                        lblAdministrador.setText(String.valueOf(numAdmin));
//                        lblMedico.setText(String.valueOf(numDoc));
//                        lblEnfermero.setText(String.valueOf(numNurse));
//                        lblSecretario.setText(String.valueOf(numSecre));
//                        lblTotal.setText(String.valueOf(numAdmin + numDoc + numNurse + numSecre));
//                    } catch (SQLException e) {
// 
//                        e.printStackTrace();
//                    }
//                }
 
            } 
                else if (arg0.getActionCommand().equals("Buscar")) {
               
               
               /*
                * TODO busqueda por nombre y por habitacion
                if (Character.isDigit(textFieldSearch.getText().charAt(0))) {
 
                    System.out.println("Busqueda por DNI");
                    try {
                        String[] employee = dao.getPaciente(textFieldSearch.getText().toString());
                        textFieldNameEdit.setText(employee[0]);
                        textFieldSurname1Edit.setText(employee[1]);
                       
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
 
                } else {
                   
                    System.out.println("Busqueda por nombre y apellidos");
                }
                */
               
               
            }
        }
    }
    }
}
