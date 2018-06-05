package Test;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rs.model.DAOGlavnoJelo;
import rs.model.DAOSalata;
import rs.model.DAOSlatkis;
import rs.model.GlavnoJelo;
import rs.model.Salata;
import rs.model.SendEmail;
import rs.model.Slatkis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GUINarudzbina extends JFrame {
	GlavnoJelo pomGlavnoJelo=null;
	Salata pomSalata=null;
	Slatkis pomSlatkis=null;
	double sum;
	SendEmail mail;
	private ButtonGroup grupaGlavno=new ButtonGroup();
	private ButtonGroup grupaSlatkis=new ButtonGroup();
	private ButtonGroup grupaSalata=new ButtonGroup();

	DAOGlavnoJelo dglj=new DAOGlavnoJelo();
	DAOSalata dsalata=new DAOSalata();
	DAOSlatkis dslatkis=new DAOSlatkis();

	private JPanel contentPane;
	private JTextField textFieldNarudzbina;

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
	private Connection connect = null;
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba1 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}


	/**
	 * Create the frame.
	 */
	public GUINarudzbina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGlavnoJelo = new JLabel("Glavno jelo");
		lblGlavnoJelo.setBounds(30, 37, 106, 15);
		contentPane.add(lblGlavnoJelo);

		JLabel lblSalata = new JLabel("Salata");
		lblSalata.setBounds(202, 37, 70, 15);
		contentPane.add(lblSalata);

		JLabel lblSlatkis = new JLabel("Slatkis");
		lblSlatkis.setBounds(378, 37, 70, 15);
		contentPane.add(lblSlatkis);

		JRadioButton rdbtnKaradjordjeva = new JRadioButton("Karadjordjeva");
		rdbtnKaradjordjeva.setSelected(true);
		rdbtnKaradjordjeva.setBounds(19, 79, 149, 23);
		contentPane.add(rdbtnKaradjordjeva);

		JRadioButton rdbtnPasulj = new JRadioButton("Pasulj");
		rdbtnPasulj.setBounds(19, 117, 149, 23);
		contentPane.add(rdbtnPasulj);

		JRadioButton rdbtnPunjeniBatak = new JRadioButton("Punjeni batak");
		rdbtnPunjeniBatak.setBounds(19, 154, 149, 23);
		contentPane.add(rdbtnPunjeniBatak);

		JRadioButton rdbtnSopska = new JRadioButton("Sopska");
		rdbtnSopska.setSelected(true);
		rdbtnSopska.setBounds(184, 79, 149, 23);
		contentPane.add(rdbtnSopska);

		JRadioButton rdbtnRuskaSalata = new JRadioButton("Ruska salata");
		rdbtnRuskaSalata.setBounds(184, 117, 149, 23);
		contentPane.add(rdbtnRuskaSalata);

		JRadioButton rdbtnSamRolna = new JRadioButton("Sam rolna");
		rdbtnSamRolna.setSelected(true);
		rdbtnSamRolna.setBounds(361, 79, 149, 23);
		contentPane.add(rdbtnSamRolna);

		JRadioButton rdbtnJaffa = new JRadioButton("Jaffa");
		rdbtnJaffa.setBounds(361, 117, 149, 23);
		contentPane.add(rdbtnJaffa);

		JRadioButton rdbtnCokolada = new JRadioButton("Cokolada");
		rdbtnCokolada.setBounds(361, 154, 149, 23);
		contentPane.add(rdbtnCokolada);
		JRadioButton rdbtnPileciPaketici = new JRadioButton("Pileci paketici");
		rdbtnPileciPaketici.setBounds(19, 197, 149, 23);
		contentPane.add(rdbtnPileciPaketici);

		JRadioButton rdbtnCevapi = new JRadioButton("Cevapi");
		rdbtnCevapi.setBounds(19, 240, 149, 23);
		contentPane.add(rdbtnCevapi);

		JRadioButton rdbtnSladoled = new JRadioButton("Sladoled");
		rdbtnSladoled.setBounds(361, 197, 149, 23);
		contentPane.add(rdbtnSladoled);

		JRadioButton rdbtnKupusSalata = new JRadioButton("Kupus salata");
		rdbtnKupusSalata.setBounds(184, 154, 149, 23);
		contentPane.add(rdbtnKupusSalata);
		grupaGlavno.add(rdbtnKaradjordjeva);
		grupaGlavno.add(rdbtnPasulj);
		grupaGlavno.add(rdbtnPunjeniBatak);
		grupaGlavno.add(rdbtnPileciPaketici);
		grupaGlavno.add(rdbtnCevapi);
		grupaSalata.add(rdbtnKupusSalata);
		grupaSalata.add(rdbtnSopska);
		grupaSalata.add(rdbtnRuskaSalata);
		grupaSlatkis.add(rdbtnSladoled);
		grupaSlatkis.add(rdbtnSamRolna);
		grupaSlatkis.add(rdbtnJaffa);
		grupaSlatkis.add(rdbtnCokolada);
		JButton btnNaruci = new JButton("Naruci");
		btnNaruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(rdbtnKaradjordjeva.isSelected()) {
						pomGlavnoJelo=dglj.selectGlavnoJeloByNaziv("Karadjordjeva");
					}else if(rdbtnPasulj.isSelected()) {
						pomGlavnoJelo=dglj.selectGlavnoJeloByNaziv("Pasulj");
					}else if(rdbtnPunjeniBatak.isSelected()) {
						pomGlavnoJelo=dglj.selectGlavnoJeloByNaziv("Punjeni batak");
					}else if(rdbtnPileciPaketici.isSelected()) {
						pomGlavnoJelo=dglj.selectGlavnoJeloByNaziv("Pileci paketici");

					}else if(rdbtnCevapi.isSelected()) {
						pomGlavnoJelo=dglj.selectGlavnoJeloByNaziv("Cevapi");
					}
					if(rdbtnSopska.isSelected()) {
						pomSalata=dsalata.selectSalataByNaziv("Sopska salata");
					}else if(rdbtnRuskaSalata.isSelected()) {
						pomSalata=dsalata.selectSalataByNaziv("Ruska salata");
					}else if(rdbtnKupusSalata.isSelected()) {
						pomSalata=dsalata.selectSalataByNaziv("Kupus salata");
					}
					if(rdbtnSamRolna.isSelected()) {
						pomSlatkis=dslatkis.selectSLatkisByNaziv("Sam rolna");
					}else if(rdbtnJaffa.isSelected()) {
						pomSlatkis=dslatkis.selectSLatkisByNaziv("Jaffa");
					}else if(rdbtnCokolada.isSelected()) {
						pomSlatkis=dslatkis.selectSLatkisByNaziv("Cokolada");
					}else if(rdbtnSladoled.isSelected()) {
						pomSlatkis=dslatkis.selectSLatkisByNaziv("Sladoled");
					}

					sum=pomGlavnoJelo.getCena()+pomSalata.getCena()+pomSlatkis.getCena();
					JOptionPane.showMessageDialog(null, "Uspesno ste porucili");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNaruci.setBounds(84, 355, 117, 25);
		contentPane.add(btnNaruci);

		textFieldNarudzbina = new JTextField();
		textFieldNarudzbina.setBounds(66, 411, 451, 87);
		contentPane.add(textFieldNarudzbina);
		textFieldNarudzbina.setColumns(10);

		JButton btnPrikazinarudzbinu = new JButton("PrikaziNarudzbinu");
		btnPrikazinarudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldNarudzbina.setText("Vasa narudzbina je: "+pomGlavnoJelo+" "+pomSalata+"  "+pomSlatkis+" Ukupna cena vase narudzbine je: "+sum);
			}
		});
		btnPrikazinarudzbinu.setBounds(309, 355, 164, 25);
		contentPane.add(btnPrikazinarudzbinu);

		JButton btnPosaljiPorudzbinu = new JButton("Posalji porudzbinu");
		btnPosaljiPorudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mail=new SendEmail("ragnarlotbruk203@gmail.com", "Narudzbina", pomGlavnoJelo.toString()+pomSalata.toString()+pomSlatkis);
				//JOptionPane.showMessageDialog(null, "Porudzbina je poslata");
			}
		});
		btnPosaljiPorudzbinu.setBounds(163, 531, 261, 40);
		contentPane.add(btnPosaljiPorudzbinu);


	}
}
