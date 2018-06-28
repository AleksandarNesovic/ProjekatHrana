package Test;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import rs.model.DAOGlavnoJelo;
import rs.model.DAOKlijent;
import rs.model.DAONarudzbina;
import rs.model.DAOSalata;
import rs.model.DAOSlatkis;
import rs.model.GlavnoJelo;
import rs.model.Klijent;
import rs.model.Narudzbina;
import rs.model.Salata;
import rs.model.SendEmail;
import rs.model.Slatkis;
import rs.model.TableModelNarudzbina;
import rs.view.TableModelGlavnoJelo;
import rs.view.TableModelSalata;
import rs.view.TableModelSlatkis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JCheckBox;

public class GUINarudzbina extends JFrame {

	private static final Logger logger=Logger.getLogger(GUINarudzbina.class);
	
	DAOGlavnoJelo daoGlavno=new DAOGlavnoJelo();
	DAONarudzbina daoNarudzbina=new DAONarudzbina();
	Narudzbina narudzbinaPom=new Narudzbina();
	Narudzbina narudzbina=new Narudzbina();
	DAOKlijent daoKlijent=new DAOKlijent();
	Klijent klijent=null;
	JComboBox<GlavnoJelo> comboBoxGlavno;
	JComboBox<Salata> comboBoxSalata;
	JComboBox<Slatkis> comboBoxSlatkis;
	double sum;
	SendEmail mail;
	private JTextField textFieldMail;
	static JTextFieldDateEditor dtEditor;
	DAOSalata daoSalata=new DAOSalata();
	DAOSlatkis daoSlatkis=new DAOSlatkis();
	JComboBox comboBoxJela;

	private JPanel contentPane;
	private JTextField textFieldKolicinaGklavnog;
	private JTextField textFieldKolicinaSalate;
	static JDateChooser datePorudzbine;
	java.util.Date datum=new java.util.Date(System.currentTimeMillis());
	private JTable tableNarudzbina;
	private JTextField textFieldIdKlijenta;
	FileWriter file1;
	FileWriter file2;
	private JTextField textFieldaNaziv;
	private JTextField textFieldKolicinaUpdate;
	private JTextField textFieldCenaUpdate;
	private JTextField textFieldIDUpdate;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {



		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUINarudzbina frame = new GUINarudzbina();
					frame.setVisible(true);
					inputDateEditor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUINarudzbina() {
		try {
			daoNarudzbina.createTable();
			daoSalata.createTable();
			daoSlatkis.createTable();
			daoKlijent.createTable();
			daoGlavno.createTable();
			daoKlijent.insert("Pera", "Peric", "0656985123", "peraperic@gmail.com");
			daoKlijent.insert("Mika", "Mikic", "0639856789", "mikamikic@gmail.com");
			daoKlijent.insert("Toma", "Tomic", "0645823147", "tomatomic@gmail.com");
			daoKlijent.insert("Zika", "Zikic", "0698745632", "zikazikic@gmail.com");
			daoSalata.insert("---------------",0);
			daoSalata.insert("Sopska salata", 300);
			daoSalata.insert("Ruska salataa", 350);
			daoSalata.insert("Kupus salata", 200);
			daoSalata.insert("Cezar salata", 700);
			daoSlatkis.insert("---------------", 0, 0);
			daoSlatkis.insert("Sam rolna", 120, 150);
			daoSlatkis.insert("Jaffa", 110, 105);
			daoSlatkis.insert("Cokolada", 300, 350);
			daoSlatkis.insert("Sladoled", 240, 170);
			daoSlatkis.insert("Rum kasato", 80, 75);
			daoGlavno.insert("Karadjordjeva", 1600);
			daoGlavno.insert("Pasulj", 400);
			daoGlavno.insert("Punjeni batak", 1400);
			daoGlavno.insert("Pileci paketici", 1300);
			daoGlavno.insert("Cevapi", 800);
			daoGlavno.insert("Gulas", 900);
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			file1=new FileWriter("Porudzbina.txt");
			file2=new FileWriter("DneviIzvestaj.txt");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 800, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGlavnoJelo = new JLabel("Glavno jelo");
		lblGlavnoJelo.setBounds(27, 28, 106, 15);
		contentPane.add(lblGlavnoJelo);

		JLabel lblSalata = new JLabel("Salata");
		lblSalata.setBounds(27, 93, 70, 15);
		contentPane.add(lblSalata);

		JLabel lblSlatkis = new JLabel("Slatkis");
		lblSlatkis.setBounds(27, 160, 70, 15);
		contentPane.add(lblSlatkis);

		comboBoxGlavno = new JComboBox<GlavnoJelo>();
		comboBoxGlavno.setBounds(12, 56, 117, 25);
		contentPane.add(comboBoxGlavno);
		popunicomboBoxGlavno();

		comboBoxSalata = new JComboBox<Salata>();
		comboBoxSalata.setBounds(12, 123, 117, 25);
		contentPane.add(comboBoxSalata);
		popunicomboBoxSalata();

		comboBoxSlatkis = new JComboBox<Slatkis>();
		comboBoxSlatkis.setBounds(12, 184, 117, 25);
		contentPane.add(comboBoxSlatkis);
		popunicomboBoxSlatkis();
		
		JPanel panelDodaci = new JPanel();
		panelDodaci.setBounds(12, 262, 339, 145);
		contentPane.add(panelDodaci);
		panelDodaci.setLayout(null);
		
		JCheckBox chckbxPavlaka = new JCheckBox("Pavlaka");
		chckbxPavlaka.setBounds(8, 8, 129, 23);
		panelDodaci.add(chckbxPavlaka);
		
		JCheckBox chckbxMajonez = new JCheckBox("Majonez");
		chckbxMajonez.setBounds(8, 45, 129, 23);
		panelDodaci.add(chckbxMajonez);
		
		JCheckBox chckbxKecap = new JCheckBox("Kecap");
		chckbxKecap.setBounds(8, 76, 129, 23);
		panelDodaci.add(chckbxKecap);
		
		JCheckBox chckbxKupus = new JCheckBox("Kupus");
		chckbxKupus.setBounds(167, 76, 129, 23);
		panelDodaci.add(chckbxKupus);
		
		JCheckBox chckbxZelenaSalata = new JCheckBox("Zelena salata");
		chckbxZelenaSalata.setBounds(167, 8, 129, 23);
		panelDodaci.add(chckbxZelenaSalata);
		
		JCheckBox chckbxUrnebes = new JCheckBox("Urnebes");
		chckbxUrnebes.setBounds(167, 45, 129, 23);
		panelDodaci.add(chckbxUrnebes);

		datePorudzbine = new JDateChooser();
		datePorudzbine.setDateFormatString("yyyy-MM-dd");
		datePorudzbine.setBounds(410, 400, 151, 19);
		datePorudzbine.setDate(datum);
		contentPane.add(datePorudzbine);

		textFieldKolicinaGklavnog = new JTextField();
		textFieldKolicinaGklavnog.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldKolicinaGklavnog.setBounds(163, 54, 114, 19);
		contentPane.add(textFieldKolicinaGklavnog);
		textFieldKolicinaGklavnog.setColumns(10);

		textFieldKolicinaSalate = new JTextField();
		textFieldKolicinaSalate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldKolicinaSalate.setBounds(163, 126, 114, 19);
		contentPane.add(textFieldKolicinaSalate);
		textFieldKolicinaSalate.setColumns(10);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 437, 608, 129);
		contentPane.add(scrollPane);

		tableNarudzbina = new JTable();
		tableNarudzbina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=tableNarudzbina.getSelectedRow();
				if(comboBoxJela.getSelectedItem().equals("Glavno jelo")) {
					TableModelGlavnoJelo model=(TableModelGlavnoJelo)tableNarudzbina.getModel();
					textFieldaNaziv.setText(model.getValueAt(selectedRow, 1).toString());
					textFieldCenaUpdate.setText(model.getValueAt(selectedRow, 2).toString());
					textFieldIDUpdate.setText(model.getValueAt(selectedRow, 0).toString());
				}else if(comboBoxJela.getSelectedItem().equals("Salata")) {
					TableModelSalata model=(TableModelSalata) tableNarudzbina.getModel();
					textFieldaNaziv.setText(model.getValueAt(selectedRow, 1).toString());
					textFieldCenaUpdate.setText(model.getValueAt(selectedRow, 2).toString());
					textFieldIDUpdate.setText(model.getValueAt(selectedRow, 0).toString());
				}else {
					TableModelSlatkis model=(TableModelSlatkis) tableNarudzbina.getModel();
					textFieldaNaziv.setText(model.getValueAt(selectedRow, 1).toString());
					textFieldKolicinaUpdate.setText(model.getValueAt(selectedRow, 2).toString());
					textFieldCenaUpdate.setText(model.getValueAt(selectedRow, 3).toString());
					textFieldIDUpdate.setText(model.getValueAt(selectedRow, 0).toString());
				}
			}
		});
		scrollPane.setViewportView(tableNarudzbina);

		textFieldMail = new JTextField();
		textFieldMail.setBounds(166, 593, 348, 25);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);

		textFieldIdKlijenta = new JTextField();
		textFieldIdKlijenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldIdKlijenta.setBounds(163, 197, 114, 19);
		contentPane.add(textFieldIdKlijenta);
		textFieldIdKlijenta.setColumns(10);

		JLabel lblNewLabel = new JLabel("Unesite mail:");
		lblNewLabel.setBounds(30, 593, 142, 25);
		contentPane.add(lblNewLabel);

		JButton btnNaruci = new JButton("Naruci");
		btnNaruci.setBounds(39, 400, 117, 25);
		btnNaruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logger.info("Izvrsavanje narudzbine");
				GlavnoJelo pomGlavnoJelo=(GlavnoJelo) comboBoxGlavno.getSelectedItem();
				Salata	pomSalata=(Salata) comboBoxSalata.getSelectedItem();
				Slatkis	pomSlatkis=(Slatkis) comboBoxSlatkis.getSelectedItem();
				java.sql.Date sqldate = new java.sql.Date(datePorudzbine.getDate().getTime());
				String dodaci="";
				for (Component c : panelDodaci.getComponents()) {
					if(c.getClass().equals(JCheckBox.class)) {
						JCheckBox jck=(JCheckBox)c;
						if(jck.isSelected()) {
							dodaci+=jck.getText()+" ";
						}
				}}
				try {
					if(!(textFieldIdKlijenta.getText().isEmpty())) {
						klijent = daoKlijent.selectKlijentaById(Integer.parseInt(textFieldIdKlijenta.getText()));
					}else {
						JOptionPane.showMessageDialog(null, "Unesite ID klijenta");
					}
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					if(validacija()==true && validacijaIdKlijenta()==true) {
						if(daoNarudzbina.proveraNarudzbina(klijent.getId_klijenta(), sqldate.toString())==true) {

							JOptionPane.showMessageDialog(null, "Klijent je vec porucio za navedeni datum");
							
						}else {

							narudzbinaPom=new Narudzbina(klijent,pomGlavnoJelo, pomSalata, pomSlatkis, Integer.parseInt(textFieldKolicinaGklavnog.getText()),
									Integer.parseInt(textFieldKolicinaSalate.getText()),sqldate.toString());

							try {
								
								daoNarudzbina.insertNarudzbina(narudzbinaPom);
								double sum=((narudzbinaPom.getGlavnoJelo().getCena()/1000)*narudzbinaPom.getKolicinaGlavnogJele()+(narudzbinaPom.getSalata().getCena()/1000)*narudzbinaPom.getKolicinaSalate()+narudzbinaPom.getSlatkis().getCena());

								PrintWriter pw=new PrintWriter(file1);
								pw.println(narudzbinaPom.toString()+sum+"\nDodaci: "+dodaci+"\n");
								pw.flush();
								clearFields();

							} catch (ClassNotFoundException e1) {
								logger.error("GRESAKA");
								e1.printStackTrace();
							} catch (SQLException e1) {
								logger.error("GRESKA");
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Uspesno ste porucili");
							logger.info("Narudzbina uspesno izvrsena");
						}
						

					}else {
						
						JOptionPane.showMessageDialog(null, "Popunite sva polja.Unesite ispravan ID klijenta");
						logger.warn("Polja nisu uspesno popnjena");
					}
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
					logger.error("GRESKA");
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}}
		});
		contentPane.add(btnNaruci);

		JButton btnPrikazinarudzbinu = new JButton("PrikaziNarudzbinu");
		btnPrikazinarudzbinu.setBounds(203, 400, 164, 25);
		btnPrikazinarudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAONarudzbina daoN=new DAONarudzbina();
				java.sql.Date sqldate = new java.sql.Date(datePorudzbine.getDate().getTime());
				try {
					ArrayList<Narudzbina> lista=daoN.selectNarudzbinaByDatum(sqldate.toString());
					TableModelNarudzbina tmn=new TableModelNarudzbina(lista);
					tableNarudzbina.setModel(tmn);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(btnPrikazinarudzbinu);

		JButton btnPosaljiPorudzbinu = new JButton("Posalji porudzbinu");
		btnPosaljiPorudzbinu.setBounds(203, 663, 261, 40);
		btnPosaljiPorudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logger.info("Pokusaj slanja na mail");
				ArrayList<Narudzbina> lista=new ArrayList<>();
				DAONarudzbina daoN=new DAONarudzbina();
				String textmail=textFieldMail.getText();
				
				
					try {
						PrintWriter pw=new PrintWriter(file2);
						lista=daoN.selectNarudzbinaByDatum(new java.sql.Date(datePorudzbine.getDate().getTime()).toString());
						pw.print(lista.toString());
						pw.flush();
						if(validacijaEmail()==true) {
							mail=new SendEmail(textmail, "Narudzbina", lista.toString());
							JOptionPane.showMessageDialog(null, "Narudzbina poslata na mail");
						}else {
							JOptionPane.showMessageDialog(null, "Unesite ispravnu email adresu i Id narudzbine");
							logger.warn("Uneta netacna email adresa ili ID narudzbine");
						}
					} catch (NumberFormatException e1) {
						logger.error("GRESKA! Poruka: "+e1.getMessage());
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}

			
		});
		contentPane.add(btnPosaljiPorudzbinu);

		JLabel lblKolicina = new JLabel("Kolicina(g):");
		lblKolicina.setBounds(163, 23, 170, 25);
		contentPane.add(lblKolicina);

		JLabel lblIdKlijenta = new JLabel("ID klijenta:");
		lblIdKlijenta.setBounds(173, 160, 99, 15);
		contentPane.add(lblIdKlijenta);
		
		JPanel panelUpdate = new JPanel();
		panelUpdate.setBackground(Color.GRAY);
		panelUpdate.setBounds(363, 88, 353, 223);
		contentPane.add(panelUpdate);
		panelUpdate.setLayout(null);
		
		textFieldaNaziv = new JTextField();
		textFieldaNaziv.setBounds(12, 37, 114, 19);
		panelUpdate.add(textFieldaNaziv);
		textFieldaNaziv.setColumns(10);
		
		textFieldKolicinaUpdate = new JTextField();
		textFieldKolicinaUpdate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
						getToolkit().beep();
						e.consume();
			}
		}});
		textFieldKolicinaUpdate.setBounds(12, 91, 114, 19);
		panelUpdate.add(textFieldKolicinaUpdate);
		textFieldKolicinaUpdate.setColumns(10);
		
		textFieldCenaUpdate = new JTextField();
		textFieldCenaUpdate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
					char c=e.getKeyChar();
					if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
						getToolkit().beep();
						e.consume();
			}
		}});
		textFieldCenaUpdate.setBounds(12, 140, 114, 19);
		panelUpdate.add(textFieldCenaUpdate);
		textFieldCenaUpdate.setColumns(10);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setForeground(Color.CYAN);
		lblNaziv.setBounds(30, 12, 70, 15);
		panelUpdate.add(lblNaziv);
		
		JLabel lblKolicina_1 = new JLabel("Kolicina:");
		lblKolicina_1.setForeground(Color.CYAN);
		lblKolicina_1.setBounds(30, 68, 70, 15);
		panelUpdate.add(lblKolicina_1);
		
		JLabel lblCena = new JLabel("Cena:");
		lblCena.setForeground(Color.CYAN);
		lblCena.setBounds(30, 113, 70, 15);
		panelUpdate.add(lblCena);
		
		comboBoxJela = new JComboBox();
		comboBoxJela.setModel(new DefaultComboBoxModel(new String[] {"Glavno jelo", "Salata", "Slatkis"}));
		comboBoxJela.setBounds(171, 34, 120, 19);
		panelUpdate.add(comboBoxJela);
		
		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxJela.getSelectedItem().equals("Glavno jelo")) {
					ArrayList<GlavnoJelo> lista=new ArrayList<>();
					try {
						lista=daoGlavno.selectGlavnoJelo();
						TableModelGlavnoJelo model=new TableModelGlavnoJelo(lista);
						tableNarudzbina.setModel(model);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(comboBoxJela.getSelectedItem().equals("Salata")) {
					ArrayList<Salata> lista=new ArrayList<>();
					try {
						lista=daoSalata.selectSalata();
						TableModelSalata model=new TableModelSalata(lista);
						tableNarudzbina.setModel(model);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					ArrayList<Slatkis> lista=new ArrayList<>();
					try {
						lista=daoSlatkis.selectSlatkis();
						TableModelSlatkis model=new TableModelSlatkis(lista);
						tableNarudzbina.setModel(model);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnPrikazi.setBounds(171, 63, 117, 25);
		panelUpdate.add(btnPrikazi);
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validacijaUpdate()==true) {
					String naziv=textFieldaNaziv.getText();
					double cena=Double.parseDouble(textFieldCenaUpdate.getText());
				if(comboBoxJela.getSelectedItem().equals("Glavno jelo")) {
					GlavnoJelo g=new GlavnoJelo(naziv, cena);
					
					try {
						if(daoGlavno.daLiPostoji(g)==false) {
						daoGlavno.insertGlavnoJelo(g);
						clearFieldsUpdate();
						}else {
							JOptionPane.showMessageDialog(null,"Jelo vec postoji");
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(comboBoxJela.getSelectedItem().equals("Salata")) {
					Salata s=new Salata(naziv, cena);
					try {
						if(daoSalata.daLiPostoji(s)==false) {
						daoSalata.insertSalata(s);
						clearFieldsUpdate();
						}else {
							JOptionPane.showMessageDialog(null,"Salata vec postoji");
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}}else {
						if(!(textFieldKolicinaUpdate.getText().isEmpty())) {
						int kolicina=Integer.parseInt(textFieldKolicinaUpdate.getText());
						Slatkis sl=new Slatkis(naziv, kolicina, cena);
						try {
							if(daoSlatkis.daLiPostoji(sl)==false) {
							daoSlatkis.insertSlatkis(sl);
							clearFieldsUpdate();
							}else {
								JOptionPane.showMessageDialog(null,"Slatkis vec postoji");
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}}else {
							JOptionPane.showMessageDialog(null,"Popunite polje za kolicinu");
						}
					}}else {
						JOptionPane.showMessageDialog(null,"Popunite polja naziv i cena");
					}
			}
		});
		btnUnesi.setBounds(171, 88, 117, 25);
		panelUpdate.add(btnUnesi);
		
		JButton btnObrsi = new JButton("Obrsi");
		btnObrsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=textFieldaNaziv.getText();
				if(comboBoxJela.getSelectedItem().equals("Glavno jelo")) {
					try {
						daoGlavno.deleteGlavnoJeloByNaziv(naziv);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(comboBoxJela.getSelectedItem().equals("Salata")) {
					try {
						daoSalata.deleteSalataByNaziv(naziv);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					try {
						daoSlatkis.deleteSlatkisByNaziv(naziv);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnObrsi.setBounds(171, 125, 117, 25);
		panelUpdate.add(btnObrsi);
		
		JButton btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validacijaUpdate()==true) {
				int id=Integer.parseInt(textFieldIDUpdate.getText());
				String naziv=textFieldaNaziv.getText();
				double cena=Double.parseDouble(textFieldCenaUpdate.getText());
					try {
						if(comboBoxJela.getSelectedItem().equals("Glavno jelo")) {
							GlavnoJelo g=new GlavnoJelo(naziv, cena);
							g.setId_glj(id);
							daoGlavno.updateGlavnoJelo(g);
							clearFieldsUpdate();
						}else if(comboBoxJela.getSelectedItem().equals("Salata")) {
							Salata sal=new Salata(naziv,cena);
							sal.setId_sal(id);
							daoSalata.updateSalata(sal);
							clearFieldsUpdate();
						}else {
							if(!(textFieldKolicinaUpdate.getText().isEmpty())) {
								int kolicina=Integer.parseInt(textFieldKolicinaUpdate.getText());
								Slatkis slat=new Slatkis(naziv, kolicina, cena);
								slat.setId_slat(id);
								daoSlatkis.updateSlatkisi(slat);
								clearFieldsUpdate();
							}else {
								JOptionPane.showMessageDialog(null, "Popunite polje za kolicinu");
							}}
					}catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}}else {
						JOptionPane.showMessageDialog(null,"Popunite polja naziv i cena");
					}
					
				
			}
		});
		btnIzmeni.setBounds(171, 150, 117, 25);
		panelUpdate.add(btnIzmeni);
		
		textFieldIDUpdate = new JTextField();
		textFieldIDUpdate.setEditable(false);
		textFieldIDUpdate.setBounds(12, 192, 114, 19);
		panelUpdate.add(textFieldIDUpdate);
		textFieldIDUpdate.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.CYAN);
		lblId.setBounds(30, 165, 70, 15);
		panelUpdate.add(lblId);
		
		JLabel lblIzmenaJelovnika = new JLabel("Izmena jelovnika:");
		lblIzmenaJelovnika.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIzmenaJelovnika.setBounds(437, 48, 275, 31);
		contentPane.add(lblIzmenaJelovnika);
		
		JLabel lblDodaci = new JLabel("Dodaci:");
		lblDodaci.setBounds(12, 245, 70, 15);
		contentPane.add(lblDodaci);
	}
	private void popunicomboBoxSlatkis(){

		ArrayList<Slatkis> listaSlatkisa=new ArrayList<Slatkis>();
		DAOSlatkis daoSlatkis=new DAOSlatkis();

		try {
			listaSlatkisa=daoSlatkis.selectSlatkis();
			for (Slatkis slatkis : listaSlatkisa) {
				comboBoxSlatkis.addItem(slatkis);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void popunicomboBoxSalata() {
		ArrayList<Salata> listaSalata=new ArrayList<Salata>();
		DAOSalata daoSalata=new DAOSalata();
		try {
			listaSalata=daoSalata.selectSalata();
			for (Salata salata : listaSalata) {
				comboBoxSalata.addItem(salata);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void popunicomboBoxGlavno() {
		ArrayList<GlavnoJelo> listaGlavno=new ArrayList<GlavnoJelo>();
		DAOGlavnoJelo daoGlavno=new DAOGlavnoJelo();
		try {
			listaGlavno=daoGlavno.selectGlavnoJelo();
			for (GlavnoJelo glavnoJelo : listaGlavno) {
				comboBoxGlavno.addItem(glavnoJelo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private boolean validacija() {

		if(textFieldKolicinaGklavnog.getText().isEmpty() || textFieldKolicinaSalate.getText().isEmpty() ||
				textFieldIdKlijenta.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	private boolean validacijaUpdate() {
		if(textFieldaNaziv.getText().isEmpty() || textFieldCenaUpdate.getText().isEmpty())
			return false;
		return true;
	}
	private boolean validacijaIdKlijenta() {
		DAOKlijent daoK=new DAOKlijent();
		try {
			if(daoK.searchById(Integer.parseInt(textFieldIdKlijenta.getText()))==true){
				return true;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	private boolean validacijaEmail() {
		
		if (Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", textFieldMail.getText())) {
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "The email is not valid", "Error!", JOptionPane.ERROR_MESSAGE);
			return false;         
		}
	}
	private void clearFields() {
		textFieldIdKlijenta.setText("");
		textFieldKolicinaGklavnog.setText("");
		textFieldKolicinaSalate.setText("");
		textFieldMail.setText("");
	}
	private void clearFieldsUpdate() {
		textFieldaNaziv.setText("");
		textFieldCenaUpdate.setText("");
		textFieldIDUpdate.setText("");
		textFieldKolicinaUpdate.setText("");
	}
	private static void inputDateEditor() {
		dtEditor=(JTextFieldDateEditor) datePorudzbine.getDateEditor();
		dtEditor.setEditable(false);
		dtEditor.setBackground(Color.white);
	}
}
