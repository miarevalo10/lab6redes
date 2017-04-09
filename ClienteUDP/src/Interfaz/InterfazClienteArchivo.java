package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class InterfazClienteArchivo extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtIp;
	private JTextField txtPuerto;
	private JTextField txtTamBuffer;
	
	private JTextField txtArchivo;
	private File archivo;
	
	public InterfazClienteArchivo(){
		

		setTitle("Conexión UDP");
		getContentPane( ).setLayout( new BorderLayout( ) );
		setSize( 600, 300 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


		JPanel info=new JPanel();
		info.setLayout(new GridBagLayout());
		info.setBackground( Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel(" "),gbc);
		
		gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Direccion IP:"),gbc);

		gbc = new GridBagConstraints( 1, 1, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtIp= new JTextField("54.187.39.221");
		info.add(txtIp,gbc);

		gbc = new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Puerto:"),gbc);

		gbc = new GridBagConstraints( 1, 2, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtPuerto= new JTextField();
		info.add(txtPuerto,gbc);

		gbc = new GridBagConstraints( 0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel("Tamaño Buffer:"),gbc);

		gbc = new GridBagConstraints( 1, 3, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtTamBuffer= new JTextField();
		info.add(txtTamBuffer,gbc);
		
		gbc = new GridBagConstraints( 0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
		info.add(new JLabel(" "),gbc);

		add(info,BorderLayout.CENTER);
		
		
		JPanel pFile=new JPanel();
		pFile.setBorder(new TitledBorder("Archivo"));
		pFile.setLayout(new GridBagLayout());
		pFile.setBackground(Color.WHITE);
		
		gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		pFile.add(new JLabel(" "),gbc);
		
		gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		pFile.add(new JLabel(" "),gbc);
		
		gbc = new GridBagConstraints( 1, 1, 5, 1, 10, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		txtArchivo= new JTextField();
		pFile.add(txtArchivo,gbc);
		
		gbc = new GridBagConstraints( 6, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		JButton btnArchivo = new JButton("Archivo");
		btnArchivo.addActionListener(this);
		btnArchivo.setActionCommand("Archivo");
		pFile.add(btnArchivo,gbc);
		
		
		add(pFile,BorderLayout.NORTH);
		
		
		JPanel botones=new JPanel();
		GridLayout layout = new GridLayout(1,4);
		layout.setHgap(5);
		botones.setLayout(layout);
		botones.setBackground(Color.WHITE);
		
		botones.add(new JLabel(" "));
		botones.add(new JLabel(" "));
		botones.add(new JLabel(" "));
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setActionCommand("Enviar");
		botones.add(btnEnviar);
		
		add(botones,BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		InterfazClienteArchivo interfaz = new InterfazClienteArchivo( );
		interfaz.setLocationRelativeTo(null);
		interfaz.setVisible( true );
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getActionCommand().equals("Enviar")){
			JFileChooser fc = new JFileChooser( "./data" );
			fc.setDialogTitle( "Seleccione carpeta de origen" );
			if( fc.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
			{
				archivo=fc.getSelectedFile();
				txtArchivo.setText(archivo.getName());
			}
		}
		else if(event.getActionCommand().equals("Archivo")){
			JFileChooser fc = new JFileChooser( "./data" );
			fc.setDialogTitle( "Seleccione carpeta de origen" );
			if( fc.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
			{
				archivo=fc.getSelectedFile();
				txtArchivo.setText(archivo.getName());
			}
		}
		
	}

}
