package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import helper.*;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class yoneticiGUI extends JFrame {

	static yonetici yoneticii = new yonetici();
	private JPanel w_pane;
	private JTextField fld_fName;
	private JTextField fld_fTur;
	private JTextField fld_filmID;
	private JTable table_film;
	private JTable table_hall;
	private JTextField fld_hallName;
	private DefaultTableModel calisanModel = null;
	private DefaultTableModel hallModel = null;
	private DefaultTableModel filmModel = null;
	private DefaultTableModel seansModel = null;
	private Object[] calisanData = null;
	private Object[] hallData = null;
	private Object[] filmData = null;
	private Object[] seansData = null;
	private JPopupMenu hallMenu;
	private JPopupMenu filmMenu;
	Hall hall = new Hall();
	Film film = new Film();
	private JTable table_HallFilm;
	private JTable table_seansFilm;
	
	
	DBConnection conn2 = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	private JTextField fld_filmid;
	private JTable table_seanslýFilm;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yoneticiGUI frame = new yoneticiGUI(yoneticii);
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
	public yoneticiGUI(yonetici yoneticii) throws SQLException {
	
	
	
		//Film Model
		
		filmModel = new DefaultTableModel();
		Object[] colFilmName = new Object[4];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colFilmName[0] = "ID";
		colFilmName[1] = "Film_adi";
		colFilmName[2] = "Tur";
		colFilmName[3] = "Status";
		filmModel.setColumnIdentifiers(colFilmName);
		filmData = new Object[4];
		for(int i=0; i<film.getFilmList().size();i++) {
			filmData[0] = film.getFilmList().get(i).getID();
			filmData[1] = film.getFilmList().get(i).getFilm_adi();
			filmData[2] = film.getFilmList().get(i).getTur();
			filmData[3] = film.getFilmList().get(i).getStatus();
			filmModel.addRow(filmData);
			
		}	
		
		// Seans Model
		
		seansModel = new DefaultTableModel();
		Object[] colseansFilmName = new Object[4];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colseansFilmName[0] = "id";
		colseansFilmName[1] = "Film_name";
		colseansFilmName[2] = "wdate";
		colseansFilmName[3] = "status";
		seansModel.setColumnIdentifiers(colseansFilmName);
		seansData = new Object[4];
		for(int i=0; i<film.getSeansFilmList().size();i++) {
			seansData[0] = film.getSeansFilmList().get(i).getId();
			seansData[1] = film.getSeansFilmList().get(i).getFilm_name();
			seansData[2] = film.getSeansFilmList().get(i).getWdate();
			seansData[3] = film.getSeansFilmList().get(i).getStatus2();
			seansModel.addRow(seansData);
			
		}
		
		// Hall Model
		
		hallModel = new DefaultTableModel();
		Object[] colhallName = new Object[2];	// User db'de 4 kolon var ve biz  4 kolonu görmek istediðimiz için 4 nesneli
		colhallName[0] = "ID";
		colhallName[1] = "Salonlar";

		hallModel.setColumnIdentifiers(colhallName);
		hallData = new Object[2];
		for(int i = 0 ; i <  hall.getList().size() ; i++) {
			hallData[0] = hall.getList().get(i).getId();
			hallData[1] = hall.getList().get(i).getH_name();
			hallModel.addRow(hallData);
			
		}
		//HallFilmModel
		DefaultTableModel HallFilmModel=new DefaultTableModel();
		Object[] colHallFilm= new Object[3];
		colHallFilm[0]= "ID";
		colHallFilm[1]="film_adi";
		colHallFilm[2]="Tur";
		HallFilmModel.setColumnIdentifiers(colHallFilm);
		Object[] HallFilmData = new Object[3];
		
		
		setTitle("Sinema Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 636);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + yoneticii.getName());
		lblNewLabel.setBounds(10, 11, 317, 35);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setBounds(785, 15, 144, 35);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_pane.add(btnNewButton);
		
		JTabbedPane w_yontappane = new JTabbedPane(JTabbedPane.TOP);
		w_yontappane.setBounds(10, 113, 968, 483);
		w_pane.add(w_yontappane);
		
		JPanel w_filmler = new JPanel();
		w_yontappane.addTab("Film Ekleme", null, w_filmler, null);
		w_filmler.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Film Ad\u0131");
		lblNewLabel_1.setBounds(603, 42, 260, 34);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_filmler.add(lblNewLabel_1);
		
		fld_fName = new JTextField();
		fld_fName.setBounds(603, 76, 260, 40);
		fld_fName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		w_filmler.add(fld_fName);
		fld_fName.setColumns(10);
		
		fld_fTur = new JTextField();
		fld_fTur.setBounds(603, 148, 260, 38);
		fld_fTur.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_fTur.setColumns(10);
		w_filmler.add(fld_fTur);
		
		JLabel lblNewLabel_1_1 = new JLabel("Film T\u00FCr\u00FC");
		lblNewLabel_1_1.setBounds(603, 114, 260, 34);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_filmler.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.setBounds(603, 214, 260, 40);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_fName.getText().length() == 0  || fld_fTur.getText().length() ==0) {
					Helper.showMsg("fill"); // helper classý sayesinde giriþ yaparken veya film eklerken baþarýlý veya hatalý bildirisi verebiliriz
				}	else {
					try {
						
						if(film.addFilm(fld_fName.getText(),fld_fTur.getText())) {
							Helper.showMsg("success");
							fld_fName.setText(null); // film ekleme iþlemi sona erdikten sonra kutucuklarýn temizlenmesi için yazdýk.
							fld_fTur.setText(null);
							
							updateFilmModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} 
				
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		w_filmler.add(btnNewButton_1);
		
		fld_filmID = new JTextField();
		fld_filmID.setBounds(603, 328, 260, 38);
		fld_filmID.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_filmID.setColumns(10);
		w_filmler.add(fld_filmID);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Film ID");
		lblNewLabel_1_2_1.setBounds(604, 296, 120, 34);
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		w_filmler.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton_1_1 = new JButton("Sil");
		btnNewButton_1_1.setBounds(603, 377, 260, 50);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_filmID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir film seçiniz");
				} else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_filmID.getText());
						try {
							boolean control = film.deleteFilm(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_filmID.setText(null);
								updateFilmModel();
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		w_filmler.add(btnNewButton_1_1);
		
		JScrollPane w_scrollYonetici = new JScrollPane();
		w_scrollYonetici.setBounds(10, 11, 498, 433);
		w_filmler.add(w_scrollYonetici);
	
		filmMenu = new JPopupMenu();		//film ekleme menüsünde sað týkladýðýmýzda güncelle ve sil menüsü açtýk
		JMenuItem updateMenu = new JMenuItem("Günücelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		filmMenu.add(updateMenu);
		filmMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_film.getValueAt(table_film.getSelectedRow(), 0).toString());	//seçilen satýrý almaya yarar
				Film selectFilm = getFetch(selID);		// veri tabanýndan týklanarak veri çektik
				UpdateFilmGUI updateGUI = new UpdateFilmGUI(selectFilm);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateFilmModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_film.getValueAt(table_film.getSelectedRow(), 0).toString());
					try {
						if(film.deleteFilm(selID)) {
							Helper.showMsg("succes");
							updateFilmModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		table_film = new JTable(filmModel);
		table_film.setComponentPopupMenu(filmMenu);
		table_film.addMouseListener(new MouseAdapter() {	//	mouse hareketlerini incelemeye yarar
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_film.rowAtPoint(point);
				table_film.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollYonetici.setViewportView(table_film);
		
		JPanel w_hall = new JPanel();
		w_yontappane.addTab("Salonlar", null, w_hall, null);
		w_hall.setLayout(null);
		
		JScrollPane scroll_hall = new JScrollPane();
		scroll_hall.setBounds(10, 11, 326, 433);
		w_hall.add(scroll_hall);
		
		
		table_hall = new JTable(hallModel);
		table_hall.setComponentPopupMenu(hallMenu);
		table_hall.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {		// ekranda hangi satýrda sað týkladýðýmýzý anlamasýný saðladýk
				Point point = e.getPoint();
				int sellectedRow = table_hall.rowAtPoint(point);
				table_hall.setRowSelectionInterval(sellectedRow, sellectedRow);
			
			}
		});
		scroll_hall.setViewportView(table_hall);
		
		JScrollPane scroll_HallFilm = new JScrollPane();
		scroll_HallFilm.setBounds(627, 11, 326, 433);
		w_hall.add(scroll_HallFilm);
		
		table_HallFilm = new JTable();
		scroll_HallFilm.setViewportView(table_HallFilm);
		
		JLabel lblNewLabel_1_3 = new JLabel("Salon \u0130smi");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(346, 14, 271, 34);
		w_hall.add(lblNewLabel_1_3);
		
		fld_hallName = new JTextField();
		fld_hallName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hallName.setColumns(10);
		fld_hallName.setBounds(346, 48, 271, 40);
		w_hall.add(fld_hallName);
		
		JButton btn_addHall = new JButton("Ekle");
		btn_addHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_hallName.getText().length()==0 ) {
					Helper.showMsg("fill");
				} else {
					try {
						if(hall.addHall(fld_hallName.getText())) {
							Helper.showMsg("success");
							fld_hallName.setText(null);
							updateHallModel();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_addHall.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_addHall.setBounds(346, 99, 271, 50);
		w_hall.add(btn_addHall);
		
		JComboBox select_film = new JComboBox();
		select_film.setBounds(346, 323, 271, 50);
		for(int i = 0 ; i<film.getFilmList().size(); i++) {
			select_film.addItem(new Item(film.getFilmList().get(i).getID(), film.getFilmList().get(i).getFilm_adi()));
		}
		
		select_film.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});  
		
		w_hall.add(select_film);
		
		JButton btn_addFilm = new JButton("Ekle");
		btn_addFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_hall.getSelectedRow();
				if(selRow >= 0) {
					String selHall = table_hall.getModel().getValueAt(selRow,0).toString();
					int selHallID = Integer.parseInt(selHall);
					Item filmItem = (Item) select_film.getSelectedItem();
					try {
						boolean control = yoneticii.addHallFilm(filmItem.getKey(), selHallID);
						if(control) {
							Helper.showMsg("success");
						DefaultTableModel clearModel = (DefaultTableModel) table_HallFilm.getModel();	
							clearModel.setRowCount(0);
							for(int i= 0 ; i < yoneticii.getHallFilmList(selHallID).size();i++) {
								HallFilmData[0]=yoneticii.getHallFilmList(selHallID).get(i).getID();
								HallFilmData[1]=yoneticii.getHallFilmList(selHallID).get(i).getFilm_adi();
								HallFilmData[2]=yoneticii.getHallFilmList(selHallID).get(i).getTur();
								HallFilmModel.addRow(HallFilmData);
								}
							table_HallFilm.setModel(HallFilmModel);
							
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir sinema salonu seçiniz.");
				}
			}
		});
		btn_addFilm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_addFilm.setBounds(346, 381, 271, 50);
		w_hall.add(btn_addFilm);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Filmler");
		lblNewLabel_1_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(346, 186, 271, 34);
		w_hall.add(lblNewLabel_1_3_1);
		
		JButton btn_workerSelect = new JButton("Listele");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int selRow=table_hall.getSelectedRow();
			if(selRow>=0){
				String selHall = table_hall.getModel().getValueAt(selRow,0).toString();
				int selHallID = Integer.parseInt(selHall);
				DefaultTableModel clearModel = (DefaultTableModel) table_HallFilm.getModel();	
				clearModel.setRowCount(0);
				try {
					for(int i= 0 ; i < yoneticii.getHallFilmList(selHallID).size();i++) {
						HallFilmData[0]=yoneticii.getHallFilmList(selHallID).get(i).getID();
						HallFilmData[1]=yoneticii.getHallFilmList(selHallID).get(i).getFilm_adi();
						HallFilmData[2]=yoneticii.getHallFilmList(selHallID).get(i).getTur();
						HallFilmModel.addRow(HallFilmData);
					}	
				} catch (Exception e2) {
					// TODO: handle exception
				}
				table_HallFilm.setModel(HallFilmModel);
				} else {
				Helper.showMsg("Lütfen bir salon seçiniz!");
			}
				
				}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_workerSelect.setBounds(346, 231, 271, 50);
		w_hall.add(btn_workerSelect);
		for(int i = 0 ; i<film.getFilmList().size(); i++) {
			select_film.addItem(new Item(film.getFilmList().get(i).getID(), film.getFilmList().get(i).getFilm_adi()));
		}
		
		select_film.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		
		JPanel w_seans = new JPanel();
		w_yontappane.addTab("Seanslar", null, w_seans, null);
		w_seans.setLayout(null);
		
		JScrollPane scroll_filmseans = new JScrollPane();
		scroll_filmseans.setBounds(10, 11, 355, 433);
		w_seans.add(scroll_filmseans);
		
		table_seansFilm = new JTable(filmModel); //filmModel yazýlacak
		scroll_filmseans.setViewportView(table_seansFilm);
		table_seansFilm.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fld_filmid.setText(table_seansFilm.getValueAt(table_seansFilm.getSelectedRow(), 0).toString());
				
			}
		});
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(375, 65, 199, 38);
		w_seans.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "12:00"}));
		select_time.setToolTipText(" ");
		select_time.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		select_time.setBounds(375, 131, 102, 38);
		w_seans.add(select_time);
		
		JButton btn_Filmhour = new JButton("Ekle");
		btn_Filmhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String date = "";
				
				try {
					
				date = sdf.format(select_date.getDate());
					
				} catch (Exception e2){
					
					e2.printStackTrace();
				}
				
				if(date.length() == 0) {
					
					Helper.showMsg("Lütfen bir tarih giriniz.");
					
				} else {
					int row = table_seansFilm.getSelectedRow();
					int filmId = (Integer) table_seansFilm.getModel().getValueAt(row, 0) ;
					String filmName = table_seansFilm.getModel().getValueAt(row, 1).toString();
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						
						boolean control = film.addWhour(filmId,filmName, selectDate);	
						
						if(control) {
							Helper.showMsg("success");
//  							updateSeansliModel();
						// ben yazdým	table_Filmhour.setModel(filmModel);
							
						} else {
							
							Helper.showMsg("error");
						
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_Filmhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
		btn_Filmhour.setBounds(376, 273, 199, 38);
		w_seans.add(btn_Filmhour);
		
		JScrollPane scroll_filmrandevuson = new JScrollPane();
		scroll_filmrandevuson.setBounds(588, 11, 365, 433);
		w_seans.add(scroll_filmrandevuson);
		
		table_seanslýFilm = new JTable(seansModel);
		scroll_filmrandevuson.setViewportView(table_seanslýFilm);




		
		fld_filmid = new JTextField();
		fld_filmid.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_filmid.setColumns(10);
		fld_filmid.setBounds(375, 224, 199, 38);
		w_seans.add(fld_filmid);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Film ID");
		lblNewLabel_1_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1_2_1_1.setBounds(375, 186, 198, 38);
		w_seans.add(lblNewLabel_1_2_1_1);
		
//		hallMenu = new JPopupMenu();						//salonlar için sað týk ile popup menü silme ve güncelleme iþlemleri oluþturuldu
//		JMenuItem updateMenu = new JMenuItem("Güncelle");
//		JMenuItem deleteMenu = new JMenuItem("Sil");
//		hallMenu.add(updateMenu);
//		hallMenu.add(deleteMenu);
		
/*		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_hall.getValueAt(table_hall.getSelectedRow(), 0).toString());
				Hall selectHall = hall.getFetch(selID);
				UpdateHallGUI updateGUI = new UpdateHallGUI(selectHall);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) { 	// düzenleme iþlemi için onay verildikten sonra gerçekleþen deðiþimi uygulamaya aktarmak için kullanýlýr.
						// TODO Auto-generated method stub
						try {
							updateHallModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
			}
		});	*/
		
/*		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_hall.getValueAt(table_hall.getSelectedRow(), 0).toString());
					try {
						
						if(hall.deleteHall(selID)) {
							
							Helper.showMsg("success");
							
							updateHallModel();
							
						}	else {
							
							Helper.showMsg("error");
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
						
					}

				}
				
			}
		});		*/
/*		for(int i = 0 ; i < yoneticii.getCalisanList().size() ; i++) {
			select_film.addItem(new Item(yoneticii.getCalisanList().get(i).getId() , yoneticii.getCalisanList().get(i).getName()));
		}
	*/	
	}
	

	
	public void updateHallModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_hall.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i < hall.getList().size();i++) {
			hallData[0] = hall.getList().get(i).getId();
			hallData[1] = hall.getList().get(i).getH_name();
			hallModel.addRow(hallData);
			
		}
	}
	
/*	
	public void updateSeansliModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_seansFilm.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i < film.getSeansFilmList().size();i++) {
			seansData[0] = film.getSeansFilmList().get(i).getId();
			seansData[1] = film.getSeansFilmList().get(i).getFilm_name();
			seansData[2] = film.getSeansFilmList().get(i).getWdate();
			seansModel.addRow(seansData);
			
		}
	}
	*/
	public void updateFilmModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_film.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i < film.getFilmList().size();i++) {
			filmData[0] = film.getFilmList().get(i).getID();
			filmData[1] = film.getFilmList().get(i).getFilm_adi();
			filmData[2] = film.getFilmList().get(i).getTur();
			
			filmModel.addRow(filmData);
			
		}
	}
	
	/*public void updateWhourModel(Film film) {
		DefaultTableModel clearModel = (DefaultTableModel) table_Filmhour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i<film.getFilmhourList(film.getID()).size() ; i++) {
			whourData[0] = calisan.getWhourList(calisan.getId()).get(i).getId();
			whourData[1] = calisan.getWhourList(calisan.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	} */
	
	 public Film getFetch(int ID) {
		 Connection con = conn2.connDb();	
		 Film f = new Film(); 
		 try {
			st =con.createStatement();
			rs = st.executeQuery("SELECT * FROM filmler WHERE ID=" + ID);
			while(rs.next()) {
				f.setID(rs.getInt("ID"));
				f.setFilm_adi(rs.getString("film_adi"));
//				f.setTur(rs.getString("tur"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return f;
	 }
}
