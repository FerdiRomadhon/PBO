package TokoObat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class kasir extends JFrame {

	private JPanel contentPane;
	
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblIdTransaksi;
	public static JTextField textField_2;
	private JButton btnCari;
	private JLabel lblNamaProduct;
	public static JTextField textField_3;
	private JLabel lblHarga;
	public static JTextField textField_4;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel label_1;
	private JLabel lblJumlahBarang;
	private JLabel label_2;
	private JLabel lblBayar;
	private JTextField textField_1;
	private JLabel lblRp;
	private JButton btnHitung;
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel label;
	private JLabel lblNamaPembeli;
	private JButton btnMasuk;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli=0;
	private JLabel lblNewLabel_1;
	private JLabel lblNamaKasir;
	private JLabel lblNamaKasir1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kasir frame = new kasir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void disable()
	{
		lblNamaPembeli.setEnabled(false);
		textField.setEnabled(false);
		btnMasuk.setEnabled(false);
	}
	public void disable2()
	{
		lblNamaPembeli.setEnabled(true);
		textField.setEnabled(true);
		btnMasuk.setEnabled(true);
		textField.setText("");
		
	}
	public static void tampilBarang (String a, String b, int c)
	{
		textField_2.setText(a);
		textField_3.setText(b);
		textField_4.setText(""+c);
	}
	
	public void tutup()
	{
		this.dispose();
		login f= new login();
	}
	public void enabled()
	{
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		textField_2.setEnabled(true);
		btnCari.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		textField_1.setEnabled(true);
		btnHitung.setEnabled(true);
	}
	
	public void enabled2()
	{
		table.setEnabled(false);
		lblIdProduct.setEnabled(false);
		textField_2.setEnabled(false);
		btnCari.setEnabled(false);
		lblNamaProduct.setEnabled(false);
		lblHarga.setEnabled(false);
		lblJumlah.setEnabled(false);
		spinner.setEnabled(false);
		btnTambah.setEnabled(false);
		lblJumlahBarang.setEnabled(false);
		lblTotal.setEnabled(false);
		lblBayar.setEnabled(false);
		textField_1.setEnabled(false);
		btnHitung.setEnabled(false);
	}
	
	public void setTanggal()
    {
        java.util.Date skrng = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        lblNewLabel.setText(kal.format(skrng));
    }
	public void autoNumber()
	{
		try
		{
			Connection konek = config.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM transaksi ";
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				label.setText("T001");
			}
			else{	
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					label.setText("T00"+IDPesan);

			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	public void autoDtrans()
	{
		try
		{
			Connection konek = config.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dtransaksi ";
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				dtrans="dt001";
			}
				else
				{
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					String IDFix = "00" + IDPesan;
					dtrans="dt" + IDFix;
				}
					
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	
	public void tampilTotal()
    {
        String kpesan=label.getText();
         try {
            Connection konek = config.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select SUM(subtotal) from dtransaksi where idTrans='"+kpesan+"'");
            while (rs.next()) {
                label_1.setText(""+rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
	public kasir() {
		getContentPane().setBackground(new Color(51, 255, 153));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lblNamaKasir1.setText(login.nama);
			}
		});
		
		autoDtrans();
		setBounds(276, 140, 815, 490);
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli :");
		lblNamaPembeli.setBounds(10, 11, 90, 14);
		getContentPane().add(lblNamaPembeli);
		
		textField = new JTextField();
		textField.setBounds(121, 8, 170, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnMasuk = new JButton("Beli");
		btnMasuk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO transaksi VALUES(?,?,?,?,0)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, label.getText());
		        prepare.setString(2, lblNewLabel.getText());
		        prepare.setString(3, login.nama);
		        prepare.setString(4, textField.getText());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        tampilTabel();
		        disable();
		        enabled();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
			}
		});
		btnMasuk.setBounds(301, 7, 89, 23);
		getContentPane().add(btnMasuk);
		
		lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(547, 7, 83, 23);
		getContentPane().add(lblNewLabel);
		setTanggal();
		
		lblIdTransaksi = new JLabel("ID transaksi :");
		lblIdTransaksi.setBounds(400, 11, 100, 14);
		getContentPane().add(lblIdTransaksi);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(10, 41, 782, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIdProduct = new JLabel("ID Product :");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(10, 215, 69, 20);
		panel.add(lblIdProduct);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(132, 215, 113, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modal m = new modal();
				m.setVisible(true);
			}
		});
		btnCari.setEnabled(false);
		btnCari.setBounds(276, 214, 69, 23);
		panel.add(btnCari);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(10, 241, 95, 14);
		panel.add(lblNamaProduct);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(132, 238, 275, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(10, 263, 46, 14);
		panel.add(lblHarga);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(132, 260, 275, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(10, 291, 46, 14);
		panel.add(lblJumlah);
		
		spinner = new JSpinner();
		spinner.setValue(1);
		spinner.setEnabled(false);
		spinner.setBounds(132, 288, 46, 20);
		panel.add(spinner);
		
		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO dtransaksi VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, dtrans);
		        prepare.setString(2, label.getText());
		        prepare.setString(3, textField_2.getText());
		        prepare.setInt(4, (Integer)spinner.getValue());
		        prepare.setInt(5,  Integer.valueOf(textField_4.getText())*(Integer) spinner.getValue());

		        prepare.executeUpdate();
		        jumlahBeli+=1;
		        label_2.setText(""+jumlahBeli);
		        JOptionPane.showMessageDialog(null, "Data pembelian disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data pembelian gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
		        
				
		        //tampil total
				autoDtrans();
		        tampilTotal();
		       		         
		         //update total database
		          try{
		        Connection konek = config.getCon();
		        String query = "UPDATE transaksi SET total = ? WHERE idTrans = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, Integer.parseInt(label_1.getText()));
		        prepare.setString(2, label.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal diubah");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		          
		          
		        //kurang stok  
		        try{
		        Connection konek = config.getCon();
		        String query = "UPDATE products SET stok = stok-? WHERE id = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) spinner.getValue());
		        prepare.setString(2, (String) textField_2.getText());
		        
		        prepare.executeUpdate();
		        tampilTabel();
	            table.setModel(tabelModel);	
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		        textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				spinner.setValue(1);
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(132, 330, 89, 23);
		panel.add(btnTambah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 759, 193);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Harga");
        tabelModel.addColumn("Jumlah");
        tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
		
		lblTotal = new JLabel("Total :");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(580, 241, 52, 14);
		panel.add(lblTotal);
		
		label_1 = new JLabel("-");
		label_1.setBounds(680, 241, 46, 14);
		panel.add(label_1);
		
		lblJumlahBarang = new JLabel("Jumlah Barang :");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(534, 218, 95, 14);
		panel.add(lblJumlahBarang);
		
		label_2 = new JLabel("-");
		label_2.setBounds(680, 218, 46, 14);
		panel.add(label_2);
		
		lblBayar = new JLabel("Bayar :");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(577, 263, 52, 14);
		panel.add(lblBayar);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(661, 263, 108, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblRp = new JLabel("Rp.");
		lblRp.setBounds(637, 266, 23, 14);
		panel.add(lblRp);
		
		btnHitung = new JButton("Hitung");
		btnHitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(textField_1.getText())-Integer.parseInt(label_1.getText());
				if (kembalian<0){
					JOptionPane.showMessageDialog(null, "Pembayaran Kurang");
					textField_1.setText("");
				}else
				{
	            JOptionPane.showMessageDialog(null, "Kembalian : Rp. "+kembalian);
				autoNumber();
				tampilTabel();
				jumlahBeli=0;
				textField_1.setText("");
				label_1.setText("-");
				label_2.setText("-");
				disable2();
				enabled2();
				}
			}
		});
		btnHitung.setEnabled(false);
		btnHitung.setBounds(667, 300, 89, 23);
		panel.add(btnHitung);
		
		btnNewButton = new JButton("Keluar");
		btnNewButton.setBounds(661, 354, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tutup();
			}
		});
		
		label = new JLabel("-");
		label.setBounds(491, 11, 46, 14);
		getContentPane().add(label);
		
		lblNamaKasir = new JLabel("Nama Kasir :");
		lblNamaKasir.setBounds(636, 11, 90, 14);
		getContentPane().add(lblNamaKasir);
		
		lblNamaKasir1 = new JLabel("-");
		lblNamaKasir1.setBounds(733, 11, 59, 14);
		getContentPane().add(lblNamaKasir1);
		

		
		autoNumber();
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT dt.idProduct,p.Nama,p.harga,dt.jumlah,dt.subtotal FROM transaksi t join dtransaksi dt on t.idTrans=dt.idTrans join products p on dt.idProduct=p.id where dt.idTrans='"+label.getText()+"'";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
             
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

}
