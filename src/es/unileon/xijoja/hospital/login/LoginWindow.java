package es.unileon.xijoja.hospital.login;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import es.unileon.xijoja.hospital.InfoWindow;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Shape;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	private static final int PWIDTH = 750;
	private static final int PHEIGH = 348;
	private static final int WHEN_IN_FOCUSED_WINDOW = 0;
	private ControlerLoginWindow listener;
	/* LOGIN */
	protected JPanel loginPanel;
	protected JTextField loginUser;
	protected JTextField trama;
	protected JPasswordField loginPassword;
	protected PersonalDAO dao;
	protected JLabel lblLoginError;
	protected JLabel lblUser;
	protected JLabel lblPassword;
	protected Color ColorBorder;
	protected Color ColorBorderPass;

	private Logs log = new Logs();

	public LoginWindow() throws IOException {

		log.InfoLog("SE INICIA LA PANTALLA DE LOGIN");
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		this.listener = new ControlerLoginWindow(this);
		ColorBorder= Color.white;
		ColorBorderPass= Color.white;
		setBounds(1024 / 4, 768 / 6, PWIDTH, PHEIGH);

		setUndecorated(true);

		setTitle("Login");

		try {
			initComponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
		     GraphicsEnvironment ge =   GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("etc/rexlia.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
		
	}

	private void initComponents() throws IOException {

		dao = new PersonalDAO();// LLamamos al patron

		getContentPane().setLayout(null);

		JButton crossButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/cross.png")));
		crossButton.setBounds(720, 11, 15, 15);
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
		minButton.setBorder(null);
		minButton.setBackground(null);
		minButton.setBounds(690, 11, 15, 15);
		minButton.setOpaque(false);
		getContentPane().add(minButton);
		minButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR);
			}
		});

		JLabel backgroundLabel = new JLabel(new ImageIcon(LoginWindow.class.getResource("/resources/fondo.jpg")));
		backgroundLabel.setBounds(218, 0, 532, 348);
		getContentPane().add(backgroundLabel);

		loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBounds(0, 0, 750, 348);
		getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		JLabel iconLabel = new JLabel(new ImageIcon(LoginWindow.class.getResource("/resources/icon.png")));
		iconLabel.setBounds(24, 21, 45, 45);
		loginPanel.add(iconLabel);

		trama = new JTextField();
		trama.setBackground(Color.WHITE);
		trama.setBounds(0, 0, 0, 0);
		loginPanel.add(trama);
		
		JLabel lblLogin = new JLabel("ACCEDER");
		lblLogin.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 17));
		lblLogin.setBounds(40, 70, 150, 50);
		loginPanel.add(lblLogin);
		
		loginUser = new HintTextField("USUARIO",55);
		loginUser.setBackground(new Color(228,230,230));
		loginUser.setForeground(new Color(100,100,100));
		loginUser.setBounds(40, 115, 165, 40);
		loginUser.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));
		
		lblUser = new JLabel("USUARIO");
		lblUser.setVisible(false);
		lblUser.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 8));
		lblUser.setBounds(5, 0, 120, 20);
		loginUser.add(lblUser);
		
		loginPanel.add(loginUser);
		loginUser.setColumns(10);
		loginUser.addKeyListener(listener);

		loginPassword = new HintPasswordField("CONTRASEÑA",30);
		loginPassword.setBackground(new Color(228,230,230));
		loginPassword.setForeground(new Color(100,100,100));

		loginPassword.setBounds(40, 161, 165, 40);
		loginPassword.setEchoChar('\u0000');
		loginPassword.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));
		
		lblPassword = new JLabel("CONTRASEÑA");
		lblPassword.setVisible(false);
		lblPassword.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 8));
		lblPassword.setBounds(5, 0, 120, 20);
		loginPassword.add(lblPassword);
		loginPanel.add(loginPassword);
		loginPassword.addKeyListener(listener);
		



		ImageIcon icon = new ImageIcon(LoginWindow.class.getResource("/resources/FlechaGris.png"));
		Image scaleImage = icon.getImage().getScaledInstance(50, 35,Image.SCALE_DEFAULT);
		JButton btnLogin = new RoundedJButton(30);
		btnLogin.setBackground(new Color(228,230,230));
		btnLogin.setForeground(new Color(100,100,100));
		btnLogin.setBorder(null);
		btnLogin.setBounds(81, 220, 58, 40);
		btnLogin.setIcon(new ImageIcon(scaleImage));
		btnLogin.setBackground(new Color(228,232,230));
		btnLogin.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 12));

		btnLogin.setOpaque(false);
		btnLogin.addKeyListener(listener);
		btnLogin.addActionListener(listener);
		loginPanel.add(btnLogin);
		
		JLabel lblinfo = new JLabel("All Rights Reserved ©2020 Xijoja Software ");
		lblinfo.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 8));
		lblinfo.setBounds(5, 315, 230, 50);
		loginPanel.add(lblinfo);
		

		JButton buttonInfo = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		buttonInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow info = new InfoWindow("general");
				info.setVisible(true);
			}
		});

		buttonInfo.setOpaque(false);
		buttonInfo.setBorder(null);
		buttonInfo.setBackground((Color) null);
		buttonInfo.setBounds(10, 304, 23, 23);
		loginPanel.add(buttonInfo);
		
		
		 icon = new ImageIcon(LoginWindow.class.getResource("/resources/settings.png"));
		 scaleImage = icon.getImage().getScaledInstance(23, 23,Image.SCALE_DEFAULT);
		JButton buttonSettings = new JButton(new ImageIcon(scaleImage));
	
		buttonSettings.setOpaque(false);
		buttonSettings.setBorder(null);
		buttonSettings.setBackground((Color) null);
		buttonSettings.setBounds(40, 304, 23, 23);
		loginPanel.add(buttonSettings);
		  
		
		  JPopupMenu popupMenu = new JPopupMenu("Configuracion");

		    JMenuItem resetMenuItem = new JMenuItem("Resetear la base de datos");
		    resetMenuItem.addActionListener(listener);

		    popupMenu.add(resetMenuItem);

		    popupMenu.addSeparator();
		    
		    JMenuItem exportMenuItem = new JMenuItem("Exportar la base de datos");
		    exportMenuItem.addActionListener(listener);
		    popupMenu.add(exportMenuItem);
		   
		   
		    buttonSettings.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    popupMenu.show(buttonSettings, buttonSettings.getWidth()/2, buttonSettings.getHeight()/2);
                }
            } );
        
 
		lblLoginError = new JLabel("");
		lblLoginError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLoginError.setForeground(Color.RED);
		lblLoginError.setBounds(38, 230, 160, 14);
		loginPanel.add(lblLoginError);
		

		}

		public class HintTextField extends JTextField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;
		  private Shape shape;


		  public HintTextField(final String hint, int size) {
		    super(hint,size);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
	        setOpaque(false); // As suggested by @AVD in comment.
	    }
	    protected void paintComponent(Graphics g) {
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
	         super.paintComponent(g);
	    }
	    protected void paintBorder(Graphics g) {
	         g.setColor(ColorBorder);
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
	         }
	         return shape.contains(x, y);
	    }

		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      lblUser.setVisible(true);
		      loginUser.setBorder(BorderFactory.createLineBorder(Color.black,1));
		      ColorBorder= Color.BLACK;
		      loginUser.setForeground(Color.BLACK);
		      showingHint = false;
		    }
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      lblUser.setVisible(false);
		      loginUser.setForeground(new Color(100,100,100));
		      loginUser.setBorder(BorderFactory.createLineBorder(Color.white,1));
		      ColorBorder= Color.WHITE;
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : super.getText();
		  }
		}
		public class HintPasswordField extends JPasswordField implements FocusListener {

			private final String hint;
			  private boolean showingHint;
			  private Shape shape;


			  public HintPasswordField(final String hint, int size) {
			    super(hint,size);
			    this.hint = hint;
			    this.showingHint = true;
			    super.addFocusListener(this);
		        setOpaque(false); // As suggested by @AVD in comment.
		    }
		    protected void paintComponent(Graphics g) {
		         g.setColor(getBackground());
		         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		         super.paintComponent(g);
		    }
		    protected void paintBorder(Graphics g) {
		         g.setColor(ColorBorder);
		         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		    }
		    public boolean contains(int x, int y) {
		         if (shape == null || !shape.getBounds().equals(getBounds())) {
		             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		         }
		         return shape.contains(x, y);
		    }


			  @Override
			  public void focusGained(FocusEvent e) {
			    if(this.getText().isEmpty()) {
			      super.setText("");
				  this.setEchoChar('*');
				  loginPassword.setBorder(BorderFactory.createLineBorder(Color.black,1));
			      ColorBorderPass= Color.BLACK;
			      loginPassword.setForeground(Color.BLACK);
			      
				  lblPassword.setVisible(true);

			      showingHint = false;
			    }
			  }
			  @Override
			  public void focusLost(FocusEvent e) {
			    if(this.getText().isEmpty()) {
			      super.setText(hint);
			      showingHint = true;
			      loginPassword.setForeground(new Color(100,100,100));
			      loginPassword.setBorder(BorderFactory.createLineBorder(Color.white,1));
			      ColorBorderPass= Color.WHITE;
				  lblPassword.setVisible(false);

			     this.setEchoChar('\u0000');
			    }
			  }

			  @Override
			  public String getText() {
			    return showingHint ? "" : super.getText();
			  }
			}
		public class RoundedJButton extends JButton implements FocusListener {

			
			  private Shape shape;


			  public RoundedJButton(int size) {
			  
			    super.addFocusListener(this);
		        setOpaque(false); // As suggested by @AVD in comment.
		    }
		    protected void paintComponent(Graphics g) {
		         g.setColor(new Color(110,110,120));
		         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		         super.paintComponent(g);
		    }
		    protected void paintBorder(Graphics g) {
		         g.setColor(new Color(110,110,110));
		         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		    }
		    public boolean contains(int x, int y) {
		         if (shape == null || !shape.getBounds().equals(getBounds())) {
		             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		         }
		         return shape.contains(x, y);
		    }
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}


			  
			}
	}

