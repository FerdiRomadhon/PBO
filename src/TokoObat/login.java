package TokoObat;

import java.sql.*;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class login extends JFrame {
	public static javax.swing.JFrame f;
	public static javax.swing.JFrame f2;
	public static javax.swing.JFrame f3;
	public static String nama;
	private JLabel lblNewLabel;
	private JTextField txtUser;
	private JLabel lblNewLabel_1;
	private JPasswordField txtPass;
	
	/**
	 * Create the frame.
	 */
	public void tutup()
	{
		this.setVisible(false);
	}
	
	public void keluar()
	{
		this.dispose();
	}
	
	public login() {
		super("Login");
		setResizable(false);
		getContentPane().setBackground(new Color(204, 255, 204));
		//setClosable(true);
		//setIconifiable(true);
		setBounds(431, 267, 454, 223);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Nama Pegawai");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(51, 40, 112, 23);
		getContentPane().add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setBounds(173, 42, 215, 23);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 76, 92, 19);
		getContentPane().add(lblNewLabel_1);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(173, 76, 215, 23);
		getContentPane().add(txtPass);
		
		JButton button = new JButton("Masuk");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Class.forName("com.mckoi.JDBCDriver");
					Connection conn=DriverManager.getConnection("jdbc:mckoi://localhost/","admin","123");
			        String sql = "Select * from pegawai where namaP='"+txtUser.getText()+"' and password='" + txtPass.getText() + "'";
			        Statement st=conn.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(3).equalsIgnoreCase("admin"))
			        {
			            nama=txtUser.getText();
			            JOptionPane.showMessageDialog(null, "Login berhasil"); 
			            txtUser.setText("");
			            txtPass.setText("");
			        	tutup();
			        	admin a = new admin();
			        	a.setVisible(true);
			        }else{
			        	nama=txtUser.getText();
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            txtUser.setText("");
			            txtPass.setText("");
			        	tutup();
			        	kasir k = new kasir();
			        	k.setVisible(true);
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        txtUser.setText("");
		            txtPass.setText("");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "koneksi gagal");
			        }
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(176, 125, 101, 28);
		getContentPane().add(button);
		
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keluar();
			}
		});
		btnKeluar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKeluar.setBounds(299, 125, 89, 28);
		getContentPane().add(btnKeluar);
		setVisible(true);
		

	}
	public static void main(String []args)
	{
		login lgn=new login();
	}
}

