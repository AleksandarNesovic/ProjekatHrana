package Test;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.mail.handlers.text_xml;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class GUINarudzbina extends JFrame {
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
	DAOSalata dsalata=new DAOSalata();
	DAOSlatkis dslatkis=new DAOSlatkis();
	private JTextField textFieldMail;

	private JPanel contentPane;
	private JTextField textFieldKolicinaGklavnog;
	private JTextField textFieldKolicinaSalate;
	JDateChooser datePorudzbine;
	java.util.Date datum=new java.util.Date(System.currentTimeMillis());
	private JTable tableNarudzbina;
	private JTextField textFieldIdKlijenta;
	private JTextField textFieldIdNarudzbine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUINarudzbina frame = new GUINarudzbina();
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
	public GUINarudzbina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGlavnoJelo = new JLabel("Glavno jelo");
		lblGlavnoJelo.setBounds(41, 56, 106, 15);
		contentPane.add(lblGlavnoJelo);

		JLabel lblSalata = new JLabel("Salata");
		lblSalata.setBounds(210, 56, 70, 15);
		contentPane.add(lblSalata);

		JLabel lblSlatkis = new JLabel("Slatkis");
		lblSlatkis.setBounds(374, 56, 70, 15);
		contentPane.add(lblSlatkis);

		comboBoxGlavno = new JComboBox<GlavnoJelo>();
		comboBoxGlavno.setBounds(30, 110, 117, 25);
		contentPane.add(comboBoxGlavno);
		popunicomboBoxGlavno();

		comboBoxSalata = new JComboBox<Salata>();
		comboBoxSalata.setBounds(195, 110, 106, 25);
		contentPane.add(comboBoxSalata);
		popunicomboBoxSalata();

		comboBoxSlatkis = new JComboBox<Slatkis>();
		comboBoxSlatkis.setBounds(366, 110, 117, 25);
		contentPane.add(comboBoxSlatkis);
		popunicomboBoxSlatkis();

		datePorudzbine = new JDateChooser();
		datePorudzbine.setDateFormatString("yyyy-MM-dd");
		datePorudzbine.setBounds(391, 284, 151, 19);
		datePorudzbine.setDate(datum);
		contentPane.add(datePorudzbine);
		
		textFieldIdNarudzbine = new JTextField();
		textFieldIdNarudzbine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldIdNarudzbine.setBounds(446, 497, 114, 19);
		contentPane.add(textFieldIdNarudzbine);
		textFieldIdNarudzbine.setColumns(10);

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
		textFieldKolicinaGklavnog.setBounds(30, 211, 114, 19);
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
		textFieldKolicinaSalate.setBounds(195, 211, 114, 19);
		contentPane.add(textFieldKolicinaSalate);
		textFieldKolicinaSalate.setColumns(10);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 338, 445, 129);
		contentPane.add(scrollPane);

		tableNarudzbina = new JTable();
		scrollPane.setViewportView(tableNarudzbina);

		textFieldMail = new JTextField();
		textFieldMail.setBounds(163, 494, 217, 25);
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
		textFieldIdKlijenta.setBounds(397, 211, 114, 19);
		contentPane.add(textFieldIdKlijenta);
		textFieldIdKlijenta.setColumns(10);

		JLabel lblNewLabel = new JLabel("Unesite mail:");
		lblNewLabel.setBounds(27, 494, 142, 25);
		contentPane.add(lblNewLabel);

		JButton btnNaruci = new JButton("Naruci");
		btnNaruci.setBounds(30, 284, 117, 25);
		btnNaruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlavnoJelo pomGlavnoJelo=(GlavnoJelo) comboBoxGlavno.getSelectedItem();
				Salata	pomSalata=(Salata) comboBoxSalata.getSelectedItem();
				Slatkis	pomSlatkis=(Slatkis) comboBoxSlatkis.getSelectedItem();
				java.sql.Date sqldate = new java.sql.Date(datePorudzbine.getDate().getTime());
				try {
					if(!(textFieldIdKlijenta.getText().isEmpty())) {
						klijent = daoKlijent.selectKlijentaById(Integer.parseInt(textFieldIdKlijenta.getText()));
					}else {
						JOptionPane.showMessageDialog(null, "Unesite ID klijenta");
					}
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					if(validacija()==true && validacijaIdKlijenta()==true) {
						if(daoNarudzbina.proveraNarudzbina(klijent.getId_klijenta(), sqldate)==true) {
							
							JOptionPane.showMessageDialog(null, "Klijent je vec porucio za navedeni datum");
						}else {
							
							narudzbinaPom=new Narudzbina(klijent,pomGlavnoJelo, pomSalata, pomSlatkis, Integer.parseInt(textFieldKolicinaGklavnog.getText()),
									Integer.parseInt(textFieldKolicinaSalate.getText()), textFieldMail.getText(),sqldate);
							try {
								daoNarudzbina.insertNarudzbina(narudzbinaPom);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Uspesno ste porucili");
						}
						sum=((pomGlavnoJelo.getCena()/1000)*Integer.parseInt(textFieldKolicinaGklavnog.getText()))+((pomSalata.getCena()/1000)*Integer.parseInt(textFieldKolicinaSalate.getText()))+pomSlatkis.getCena();

					}else {
						JOptionPane.showMessageDialog(null, "Popunite sva polja.Unesite ispravan ID klijenta ili email adresu");
					}
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		});
		contentPane.add(btnNaruci);

		JButton btnPrikazinarudzbinu = new JButton("PrikaziNarudzbinu");
		btnPrikazinarudzbinu.setBounds(195, 284, 164, 25);
		btnPrikazinarudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAONarudzbina daoN=new DAONarudzbina();
				java.sql.Date sqldate = new java.sql.Date(datePorudzbine.getDate().getTime());
				try {
					ArrayList<Narudzbina> lista=daoN.selectNarudzbinaByDatum(sqldate);
					TableModelNarudzbina tmn=new TableModelNarudzbina(lista);
					tableNarudzbina.setModel(tmn);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(btnPrikazinarudzbinu);

		JButton btnPosaljiPorudzbinu = new JButton("Posalji porudzbinu");
		btnPosaljiPorudzbinu.setBounds(163, 531, 261, 40);
		btnPosaljiPorudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAONarudzbina daoN=new DAONarudzbina();
				String textmail=textFieldMail.getText();
				if(textFieldIdNarudzbine.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Unesite ispravan Id narudzbine!");
				}else {
				try {
					Narudzbina pom=new Narudzbina();
					if(textmail.contains("@") && textmail.contains(".") && validacijaIDNarudzbine()==true) {
						pom=daoN.selectNarudzbinaByID(Integer.parseInt(textFieldIdNarudzbine.getText()));
						double sum=((pom.getGlavnoJelo().getCena()/1000)*pom.getKolicinaGlavnogJele()+(pom.getSalata().getCena()/1000)*pom.getKolicinaSalate()+pom.getSlatkis().getCena());
						mail=new SendEmail(textmail, "Narudzbina", pom.toString()+sum);
						JOptionPane.showMessageDialog(null, "Narudzbina poslata na mail");
					}else
						JOptionPane.showMessageDialog(null, "Unesite ispravnu email adresu i Id narudzbine");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		contentPane.add(btnPosaljiPorudzbinu);

		JLabel lblKolicina = new JLabel("Kolicina(g):");
		lblKolicina.setBounds(110, 160, 170, 25);
		contentPane.add(lblKolicina);



		JLabel lblIdKlijenta = new JLabel("ID klijenta:");
		lblIdKlijenta.setBounds(412, 165, 99, 15);
		contentPane.add(lblIdKlijenta);
		
		
		
		JLabel lblIdNarudzbine = new JLabel("ID Narudzbine:");
		lblIdNarudzbine.setBounds(443, 470, 117, 15);
		contentPane.add(lblIdNarudzbine);


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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private boolean validacija() {
		
		if(textFieldKolicinaGklavnog.getText().isEmpty() || textFieldKolicinaSalate.getText().isEmpty() || textFieldMail.getText().isEmpty() ||
				textFieldIdKlijenta.getText().isEmpty() || !(textFieldMail.getText().contains("@") && textFieldMail.getText().contains("."))) {
			
			return false;
		}
		return true;
	}
	private boolean validacijaIdKlijenta() {
		DAOKlijent daoK=new DAOKlijent();
		try {
			if(daoK.searchById(Integer.parseInt(textFieldIdKlijenta.getText()))==true){
				return true;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	private boolean validacijaIDNarudzbine() throws NumberFormatException, ClassNotFoundException, SQLException {
		DAONarudzbina daoN=new DAONarudzbina();
		if(daoN.searchNarudzbineById(Integer.parseInt(textFieldIdNarudzbine.getText()))==true)
			return true;
		return false;
	}
}
