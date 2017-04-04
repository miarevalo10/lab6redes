package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Cliente;

public class InterfazCliente extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtIp;
	private JTextField txtPuerto;
	private JTextField txtNumero;

	private InterfazCliente(){


		setTitle("Conexión UDP");
		getContentPane( ).setLayout( new BorderLayout( ) );
		setSize( 600, 200 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


		JPanel info=new JPanel();
		info.setLayout(new GridBagLayout());
		info.setBackground( Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Direccion IP:"),gbc);

		gbc = new GridBagConstraints( 1, 0, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtIp= new JTextField();
		info.add(txtIp,gbc);


		gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Puerto:"),gbc);

		gbc = new GridBagConstraints( 1, 1, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtPuerto= new JTextField();
		info.add(txtPuerto,gbc);


		gbc = new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Numero de Objetos:"),gbc);

		gbc = new GridBagConstraints( 1, 2, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtNumero= new JTextField();
		info.add(txtNumero,gbc);

		gbc = new GridBagConstraints( 6, 3, 5, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

		JButton btnConexion= new JButton("Enviar");
		btnConexion.setActionCommand("Enviar");
		btnConexion.addActionListener(this);

		info.add(btnConexion,gbc);

		add(info,BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Enviar")){

			try{

				Cliente.enviarObjetos(txtIp.getText(),Integer.parseInt(txtPuerto.getText()),Integer.parseInt(txtNumero.getText()));
			}
			catch(Exception e){

				JOptionPane.showMessageDialog( this, " ", "Error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}


	public static void main(String[] args) {
		InterfazCliente interfaz = new InterfazCliente( );
		interfaz.setLocationRelativeTo(null);
		interfaz.setVisible( true );
	}
}
