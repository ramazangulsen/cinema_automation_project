package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Helper;
import model.Hall;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateHallGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_hallName;
	private JButton btn_updateHall;
	private static Hall hall; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateHallGUI frame = new UpdateHallGUI(hall);
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
	public UpdateHallGUI(Hall hall) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Salon \u0130smi");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 11, 167, 25);
		contentPane.add(lblNewLabel_1_3);
		
		fld_hallName = new JTextField();
		fld_hallName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hallName.setColumns(10);
		fld_hallName.setBounds(10, 40, 239, 50);
		fld_hallName.setText(hall.getH_name());
		contentPane.add(fld_hallName);
		
		JButton btn_updateHall = new JButton("D\u00FCzenle");
		btn_updateHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						hall.updateHall(hall.getId(), fld_hallName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateHall.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_updateHall.setBounds(10, 96, 239, 50);
		contentPane.add(btn_updateHall);
	}

}

