package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class modal extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private DefaultTableModel tabelModel;
	private JTable table;
	private JTextField textField;
	//private kasir k = new kasir();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modal frame = new modal();
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
	public void tutup()
	{
		this.dispose();
	}
	public modal() {
		setTitle("Daftar Barang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(348, 208, 640, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 600, 261);
		contentPane.add(scrollPane);

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
		        String nama = (String) tabelModel.getValueAt(a, 1);
		        int harga = (Integer) tabelModel.getValueAt(a, 3);
		        kasir.tampilBarang(id,nama,harga);
		        tutup();
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);
		
		textField = new JTextField();
		textField.setBounds(114, 11, 166, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cari");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tampilTabelCari();
			}
		});
		btnNewButton.setBounds(295, 10, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tampilTabel();
			}
		});
		btnNewButton_1.setBounds(394, 10, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Cari ID Barang");
		lblNewLabel.setBounds(10, 15, 94, 14);
		contentPane.add(lblNewLabel);
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
	
	public void tampilTabelCari()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM products where id like'%" +textField.getText()+ "%'";
            
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
}
