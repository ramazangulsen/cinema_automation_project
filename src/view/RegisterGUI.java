package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Helper;
import model.Musteri;
import model.user;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {
	
	private JPanel w_pane;
	private JTextField fld_userName;
	private JTextField fld_userID;
	private JTextField fld_password;
	private Musteri musteri = new Musteri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Sinema Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 383);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 0, 87, 34);
		w_pane.add(lblNewLabel_1);
		
		fld_userName = new JTextField();
		fld_userName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_userName.setColumns(10);
		fld_userName.setBounds(10, 34, 260, 40);
		w_pane.add(fld_userName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 72, 120, 34);
		w_pane.add(lblNewLabel_1_1);
		
		fld_userID = new JTextField();
		fld_userID.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_userID.setColumns(10);
		fld_userID.setBounds(10, 106, 260, 38);
		w_pane.add(fld_userID);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 143, 87, 34);
		w_pane.add(lblNewLabel_1_2);
		
		fld_password = new JTextField();
		fld_password.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_password.setColumns(10);
		fld_password.setBounds(10, 178, 260, 38);
		w_pane.add(fld_password);
		
		JButton btnNewButton_1 = new JButton("Kay\u0131t Ol");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_userID.getText().length() ==0 || fld_password.getText().length() == 0 || fld_userName.getText().length() == 0) { 	// Kullanýcý kayýt olurken kutucuklarda boþluk býrakýrsa hata mesajý verir
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = musteri.register(fld_userID.getText(), fld_password.getText(), fld_userName.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							//Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btnNewButton_1.setBounds(10, 227, 260, 50);
		w_pane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Geri D\u00F6n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btnNewButton_1_1.setBounds(10, 287, 260, 50);
		w_pane.add(btnNewButton_1_1);
	}
}
