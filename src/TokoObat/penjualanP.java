package TokoObat;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class penjualanP extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private JTable table;
	private DefaultTableModel tabelModel;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					penjualanP frame = new penjualanP();
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
	public penjualanP() {
		setBounds(100, 100, 597, 384);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 25, 549, 425);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Item Terjual");
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
            String query = "select p.Nama, sum(dt.jumlah) from products p, dtransaksi dt where p.id=idProduct group by(p.Nama)";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
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
