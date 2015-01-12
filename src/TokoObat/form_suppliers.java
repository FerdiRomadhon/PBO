package TokoObat;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class form_suppliers extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tabelModel;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public void refresh()
	{
		textField.setText("");
		textField_1.setText("");
		
	}
	public form_suppliers() {
		getContentPane().setBackground(new Color(153, 255, 204));
		setBounds(100, 100, 635, 501);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 599, 355);
		getContentPane().add(scrollPane);
		
		JLabel lblId = new JLabel("ID                  :");
		lblId.setBounds(20, 376, 82, 24);
		getContentPane().add(lblId);
		
		JLabel lblNamaSuplier = new JLabel("Nama Suplier :");
		lblNamaSuplier.setBounds(20, 401, 82, 24);
		getContentPane().add(lblNamaSuplier);
		
		textField = new JTextField();
		textField.setBounds(100, 378, 300, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 403, 300, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Hapus");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
		        {
		            Connection konek = config.getCon();
		            String query = "DELETE FROM suppliers WHERE id = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, textField.getText());
		            prepare.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Supplier berhasil dihapus");
		            prepare.close();
		        }
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Supplier gagal dihapus");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            refresh();
		        }
			}
		});
		btnNewButton.setBounds(520, 437, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ubah");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
			        Connection konek = config.getCon();
			        String query = "UPDATE suppliers SET Nama = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, textField_1.getText());
			        prepare.setString(2, textField.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Supplier berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Supplier gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            textField.setEnabled(true);
			            refresh();            
			        }
			}
		});
		btnNewButton_1.setBounds(421, 437, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Tambah");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try
			        {
			        Connection konek = config.getCon();
			        String query = "INSERT INTO suppliers VALUES(?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, textField.getText());
			        prepare.setString(2, textField_1.getText());


			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Suplier berhasil disimpan");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Suplier gagal disimpan");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            refresh();
			        }
			}
		});
		btnNewButton_2.setBounds(322, 437, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String id = (String) tabelModel.getValueAt(a, 0);
		        textField.setText(id);
		        String nama = (String) tabelModel.getValueAt(a, 1);
		        textField_1.setText(nama); 
		        textField.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID");
        tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);	
        tampilTabel();
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM suppliers";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[2];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
