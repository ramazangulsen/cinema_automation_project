package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import helper.Item;
import model.Film;
import model.Hall;
import model.Musteri;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class MusteriGUI extends JFrame {

	private JPanel w_pane;
	private static Musteri musteri = new Musteri();
	private Hall hall = new Hall();
	private Film film = new Film();
	private DefaultTableModel filmModel;
	private Object[] filmData = null;
	private Object[] seansData = null;
	private JTable table_filmSeans;
	private DefaultTableModel filmSeansModel;
	private JTextField fld_filmID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriGUI frame = new MusteriGUI(musteri);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MusteriGUI(Musteri musteri) throws SQLException {
		
		
		filmSeansModel = new DefaultTableModel();
		Object[] colseansFilmName = new Object[4];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colseansFilmName[0] = "id";
		colseansFilmName[1] = "Film_name";
		colseansFilmName[2] = "wdate";
		colseansFilmName[3] = "status";
		filmSeansModel.setColumnIdentifiers(colseansFilmName);
		seansData = new Object[4];
		for(int i=0; i<film.getSeansFilmList().size();i++) {
			seansData[0] = film.getSeansFilmList().get(i).getId();
			seansData[1] = film.getSeansFilmList().get(i).getFilm_name();
			seansData[2] = film.getSeansFilmList().get(i).getWdate();
			seansData[3] = film.getSeansFilmList().get(i).getStatus2();
			filmSeansModel.addRow(seansData);
			
		}
		
		// film Model
		
		filmModel = new DefaultTableModel();
		Object[] colfilm = new Object[3];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colfilm[0] = "ID";
		colfilm[1] = "film_adi";
		colfilm[2] = "tur";
		filmModel.setColumnIdentifiers(colfilm);
		filmData = new Object[4];

		
		

		
		
		setResizable(false);
		setTitle("Sinema Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 636);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + musteri.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 317, 35);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		btnNewButton.setBounds(785, 15, 144, 35);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tap = new JTabbedPane(JTabbedPane.TOP);
		w_tap.setBounds(10, 113, 968, 483);
		w_pane.add(w_tap);
		
		JPanel panel = new JPanel();
		w_tap.addTab("Bilet Alma", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblSinemaSalonu_1 = new JLabel("Film Seanslar\u0131");
		lblSinemaSalonu_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblSinemaSalonu_1.setBounds(20, 11, 294, 35);
		panel.add(lblSinemaSalonu_1);
		
		JScrollPane w_scrollFilm_1 = new JScrollPane();
		w_scrollFilm_1.setBounds(20, 57, 457, 263);
		panel.add(w_scrollFilm_1);
		
		table_filmSeans = new JTable(filmSeansModel);
		w_scrollFilm_1.setViewportView(table_filmSeans);
		table_filmSeans.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fld_filmID.setText(table_filmSeans.getValueAt(table_filmSeans.getSelectedRow(), 0).toString());
				
			}
		});
		
		JButton btnNewButton_1_1_1 = new JButton("koltuk se\u00E7");
		btnNewButton_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btnNewButton_1_1_1.setBounds(20, 380, 260, 50);
		panel.add(btnNewButton_1_1_1);
		
		fld_filmID = new JTextField();
		fld_filmID.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_filmID.setColumns(10);
		fld_filmID.setBounds(20, 331, 260, 38);
		panel.add(fld_filmID);
	}
}

