package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class laporan extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					laporan frame = new laporan();
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
	public void kembali()
	{
		this.dispose();
		admin a= new admin();
		a.setVisible(true);
	}
	
	public laporan() {
		setBounds(348, 77, 665, 615);
		//setLocation(0,0);
		this.setTitle("Laporan");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 255, 153));
		tabbedPane.addTab("Laporan Transaksi Penjualan", null, panel, null);
		panel.setLayout(null);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 625, 500);
		panel.add(desktopPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 255, 153));
		tabbedPane.addTab("Laporan Penjualan Barang", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(10, 11, 625,500);
		panel_1.add(desktopPane_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnKembali = new JMenu("Kembali");
		mnKembali.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				kembali();
			}
		});
		menuBar.add(mnKembali);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				transaksiP p = new transaksiP();
				Dimension size1=desktopPane.getSize();
				desktopPane.add(p);
				p.setVisible(true);
				p.setSize(size1);
				p.setLocation(0,0);
				
				penjualanP s =new penjualanP();
				Dimension size2=desktopPane_1.getSize();
				desktopPane_1.add(s);
				s.setVisible(true);
				s.setSize(size2);
				s.setLocation(0,0);
				
			}
		});
	}
}
