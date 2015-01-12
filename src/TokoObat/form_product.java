package TokoObat;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class form_product extends JInternalFrame {
	private JTable table;
	private JTextField idProd;
	private JTextField idSup;
	private JTextField nmP;
	private JTextField hrg;
	private JComboBox nmS;
	private JLabel lblIdProduk;
	private JLabel lblNewLabel;
	private JLabel lblNamaProduk;
	private JLabel lblHarga;
	private DefaultTableModel tabelModel;
	DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JLabel lblStok;
	private JTextField stok;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public void refresh()
	{
		idProd.setText("");
		idSup.setText("");
		nmP.setText("");
		hrg.setText("");
		stok.setText("");
	}
	public form_product() {
		getContentPane().setBackground(new Color(153, 255, 204));
		setBounds(100, 100, 635, 500);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 600, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);	
        tampilTabel();
		
		idProd = new JTextField();
		idProd.setBounds(109, 272, 315, 20);
		getContentPane().add(idProd);
		idProd.setColumns(10);
		
		idSup = new JTextField();
		idSup.setColumns(10);
		idSup.setBounds(109, 304, 89, 20);
		getContentPane().add(idSup);
		
		nmP = new JTextField();
		nmP.setColumns(10);
		nmP.setBounds(109, 335, 315, 20);
		getContentPane().add(nmP);
		
		hrg = new JTextField();
		hrg.setColumns(10);
		hrg.setBounds(109, 366, 315, 20);
		getContentPane().add(hrg);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO products VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, idProd.getText());
		        prepare.setString(2, nmP.getText());
		        prepare.setString(3, idSup.getText());
		        prepare.setInt(4, Integer.parseInt(hrg.getText()));
		        prepare.setInt(5, Integer.parseInt(stok.getText()));

		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            refresh();
		        }
			}
		});
		btnTambah.setBounds(109, 429, 89, 23);
		getContentPane().add(btnTambah);
		
		JButton button = new JButton("Ubah");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = config.getCon();
			        String query = "UPDATE products SET Nama = ?,idSup=?,harga=?,stok=? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, nmP.getText());
			        prepare.setString(2, idSup.getText());
			        prepare.setInt(3, Integer.parseInt(hrg.getText()));
			        prepare.setInt(4, Integer.parseInt(stok.getText()));
			        prepare.setString(5, idProd.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Produk berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Produk gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            refresh();            
			        }
			}
			
		});
		button.setBounds(208, 429, 89, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            Connection konek = config.getCon();
		            String query = "DELETE FROM products WHERE id = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, idProd.getText());
		            prepare.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Produk berhasil dihapus");
		            prepare.close();
		        }
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Produk gagal dihapus");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            refresh();
		        }
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String id = (String) tabelModel.getValueAt(a, 0);
		        idProd.setText(id);
		        String nama = (String) tabelModel.getValueAt(a, 1);
		        nmP.setText(nama);
		        String idSupp = (String) tabelModel.getValueAt(a, 2);
		        idSup.setText(idSupp);
		        int harga = (Integer) tabelModel.getValueAt(a, 3);
		        hrg.setText(""+harga);
		        int stk = (Integer) tabelModel.getValueAt(a, 4);
		        stok.setText(""+stk);
		        idProd.setEnabled(false);
		        
		       
			}
		});
		button_1.setBounds(306, 429, 89, 23);
		getContentPane().add(button_1);
		
		nmS = new JComboBox();
		nmS.setBounds(208, 304, 216, 20);
		getContentPane().add(nmS);
		
		//tampil kode suppliers dari nama suppliers yang dipilih di combo box
		nmS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama=nmS.getSelectedItem().toString();
		        try {
		            Connection konek = config.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select id from suppliers where Nama='"+nama+"'");
		            while (rs.next()) {
		                idSup.setText(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		// end
		
		lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(10, 275, 67, 14);
		getContentPane().add(lblIdProduk);
		
		lblNewLabel = new JLabel("ID supplier :");
		lblNewLabel.setBounds(10, 307, 67, 14);
		getContentPane().add(lblNewLabel);
		
		lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(10, 338, 89, 14);
		getContentPane().add(lblNamaProduk);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setBounds(10, 369, 46, 14);
		getContentPane().add(lblHarga);
		
		//akses method isisuppliers
		isisuppliers();
		//model comboBox di set sesuai comboBoxModel pada method isisuppliers
		nmS.setModel(model);
		
		lblStok = new JLabel("Stok :");
		lblStok.setBounds(10, 388, 46, 38);
		getContentPane().add(lblStok);
		
		stok = new JTextField();
		stok.setBounds(109, 397, 315, 20);
		getContentPane().add(stok);
		stok.setColumns(10);
		
		

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM products";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
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
	
	//method isisuppliers untuk tampilkan nama suppliers ke comboBox. Semua nama suppliers di masukan ke comboBoxmodel
	public void isisuppliers()
	{
		try {
            Connection konek = config.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select Nama from suppliers");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
}
