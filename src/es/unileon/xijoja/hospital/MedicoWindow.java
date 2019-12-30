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

import es.unileon.xijoja.hospital.SecretarioWindow.listener;
import es.unileon.xijoja.hospital.admin.AdminWindow;


	@SuppressWarnings("serial")
	public class MedicoWindow extends JFrame {

		Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos
	
		Toolkit screen;


     public MedicoWindow() {
	
    	 screen = Toolkit.getDefaultToolkit();
		 
	     setBounds(1024 / 4, 768 / 10, 969, 496);
	     
	     setUndecorated(true);
     
	     setTitle("Medico");
	     
	     try {
	            
	            initComponents();
	            initComponentsPanels();
	 
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	
	
     }
     
     
     private void initComponents() throws SQLException {
    	 
         getContentPane().setBackground(Color.WHITE);
         getContentPane().setLayout(null);
   
         listener list = new listener();
     }
     
     
     private void initComponentsPanels() throws SQLException {
     	
    	 listener list = new listener();
     }
     
     
     
     
     public class listener implements ActionListener {
    	 
         @Override
         public void actionPerformed(ActionEvent arg0) {
       }
    }
 }
     
     