package es.unileon.falvad01.login;
import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
		//llamada al login
		LoginWindow window = new LoginWindow();
		
		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
