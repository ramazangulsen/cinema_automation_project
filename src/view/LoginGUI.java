package view;

import java.awt.BorderLayout;
import helper.*;
import helper.DBConnection;
import helper.Helper;
//import model.Calisan;
import model.yonetici;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_musLogin;
	private JTextField fld_musPass;
	private JTextField fld_yonLogin;
	private JPasswordField fld_yonPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Sinema Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 636);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setBounds(330, 11, 267, 194);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("S\u0130NEMA OTOMASYONUNA HO\u015EGELD\u0130N\u0130Z");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblNewLabel.setBounds(229, 216, 468, 68);
		w_pane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 308, 938, 288);
		w_pane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Müþteri Giriþi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lbl_musLogin = new JLabel("KULLANICI ADI:");
		lbl_musLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lbl_musLogin.setBounds(86, 31, 358, 49);
		panel.add(lbl_musLogin);
		
		JLabel lbl_musPass = new JLabel("\u015E\u0130FRE:");
		lbl_musPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lbl_musPass.setBounds(86, 91, 358, 49);
		panel.add(lbl_musPass);
		
		fld_musLogin = new JTextField();
		fld_musLogin.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fld_musLogin.setBounds(473, 31, 369, 49);
		panel.add(fld_musLogin);
		fld_musLogin.setColumns(10);
		
		fld_musPass = new JTextField();
		fld_musPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fld_musPass.setColumns(10);
		fld_musPass.setBounds(473, 91, 369, 49);
		panel.add(fld_musPass);
		
		JButton btn_kayitol = new JButton("KAYIT OL");
		btn_kayitol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_kayitol.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		btn_kayitol.setBounds(168, 169, 239, 66);
		panel.add(btn_kayitol);
		
		JButton btn_musLogin = new JButton("G\u0130R\u0130\u015E");
		btn_musLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_musLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		btn_musLogin.setBounds(451, 169, 239, 66);
		panel.add(btn_musLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Yönetici Giriþi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lbl_yonLogin = new JLabel("KULLANICI ADI:");
		lbl_yonLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lbl_yonLogin.setBounds(91, 29, 358, 49);
		panel_1.add(lbl_yonLogin);
		
		JLabel lbl_yonPass = new JLabel("\u015E\u0130FRE:");
		lbl_yonPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lbl_yonPass.setBounds(91, 89, 358, 49);
		panel_1.add(lbl_yonPass);
		
		fld_yonLogin = new JTextField();
		fld_yonLogin.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fld_yonLogin.setColumns(10);
		fld_yonLogin.setBounds(478, 29, 369, 49);
		panel_1.add(fld_yonLogin);
		
		JButton btn_yonLogin = new JButton("G\u0130R\u0130\u015E");
		btn_yonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_yonLogin.getText().length() == 0 || fld_yonPass.getText().length() == 0) {
					Helper.showMsg("fill");
					
				}	else {
					
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM film.user");
						while(rs.next()) {
							if(fld_yonLogin.getText().equals(rs.getString("UserID")) && fld_yonPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("Yonetici")) {			// giriþ yapan yetkili burada çalýþan ve yönetici olarak ayrýlmþtýr
									yonetici yon = new yonetici();
									yon.setId(rs.getInt("id"));
									yon.setPassword("password");
									yon.setUserID(rs.getString("UserID"));
									yon.setName(rs.getString("name"));
									yon.setType(rs.getString("type"));
									yoneticiGUI yGUI = new yoneticiGUI(yon);
									yGUI.setVisible(true);
									dispose();
								}
								
							
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_yonLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_yonLogin.setBounds(297, 165, 309, 68);
		panel_1.add(btn_yonLogin);
		
		fld_yonPass = new JPasswordField();
		fld_yonPass.setBounds(478, 88, 369, 50);
		panel_1.add(fld_yonPass);
	}
}
