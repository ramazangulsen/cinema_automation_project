package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.*;
import model.Film;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateFilmGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_film›smi;
	private static Film film;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFilmGUI frame = new UpdateFilmGUI(film);
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
	public UpdateFilmGUI(Film film) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fld_film›smi = new JTextField();
		fld_film›smi.setText((String) null);
		fld_film›smi.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_film›smi.setColumns(10);
		fld_film›smi.setBounds(10, 40, 239, 50);
		fld_film›smi.setText(film.getFilm_adi());
		contentPane.add(fld_film›smi);
		
		JLabel lblNewLabel_1_3 = new JLabel("Film \u0130smi");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 11, 167, 25);
		contentPane.add(lblNewLabel_1_3);
		
		JButton btn_updateFilm = new JButton("D\u00FCzenle");
		btn_updateFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						film.updateFilm(film.getID(), fld_film›smi.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateFilm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_updateFilm.setBounds(10, 96, 239, 50);
		contentPane.add(btn_updateFilm);
	}
}


