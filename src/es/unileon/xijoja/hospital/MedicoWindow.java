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
		private PacientesDAO dao;


     public MedicoWindow() {
	
    	 screen = Toolkit.getDefaultToolkit();
		 
	     setBounds(1024 / 4, 768 / 10, 969, 496);
	     
	     setUndecorated(true);
     
	     setTitle("Medico");
	     
	     try {
	    	    dao = new PacientesDAO();
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
     }
     
     
     
     
     public class listener implements ActionListener {
    	 
         @Override
         public void actionPerformed(ActionEvent arg0) {
       }
    }
 }
     
     