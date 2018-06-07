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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUINarudzbina extends JFrame {
	DAOGlavnoJelo daoglavno=new DAOGlavnoJelo();

	JComboBox<GlavnoJelo> comboBoxGlavno;
	JComboBox<Salata> comboBoxSalata;
	JComboBox<Slatkis> comboBoxSlatkis;
	GlavnoJelo pomGlavnoJelo=null;
	Salata pomSalata=null;
	Slatkis pomSlatkis=null;
	double sum;
	SendEmail mail;



	DAOSalata dsalata=new DAOSalata();
	DAOSlatkis dslatkis=new DAOSlatkis();

	private JPanel contentPane;

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
	private JTextField textFieldMail;
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 338, 445, 129);
		contentPane.add(scrollPane);
		
		JTextArea textAreaNarudzbina = new JTextArea();
		scrollPane.setViewportView(textAreaNarudzbina);

		textFieldMail = new JTextField();
		textFieldMail.setBounds(163, 494, 324, 25);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Unesite mail:");
		lblNewLabel.setBounds(27, 494, 142, 25);
		contentPane.add(lblNewLabel);

		JButton btnNaruci = new JButton("Naruci");
		btnNaruci.setBounds(66, 223, 117, 25);
		btnNaruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pomGlavnoJelo=(GlavnoJelo) comboBoxGlavno.getSelectedItem();
				pomSalata=(Salata) comboBoxSalata.getSelectedItem();
				pomSlatkis=(Slatkis) comboBoxSlatkis.getSelectedItem();
				sum=pomGlavnoJelo.getCena()+pomSalata.getCena()+pomSlatkis.getCena();
				JOptionPane.showMessageDialog(null, "Uspesno ste porucili");
			}
		});
		contentPane.add(btnNaruci);

		JButton btnPrikazinarudzbinu = new JButton("PrikaziNarudzbinu");
		btnPrikazinarudzbinu.setBounds(303, 223, 164, 25);
		btnPrikazinarudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textAreaNarudzbina.setText("Vasa narudzbina je: "+pomGlavnoJelo.ispis()+"\n"+pomSalata.ispis()+"\n"+pomSlatkis.ispis()+"\n\n Ukupna cena vase narudzbine je: "+sum);
			}
		});
		contentPane.add(btnPrikazinarudzbinu);

		JButton btnPosaljiPorudzbinu = new JButton("Posalji porudzbinu");
		btnPosaljiPorudzbinu.setBounds(163, 531, 261, 40);
		btnPosaljiPorudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textmail=textFieldMail.getText();
				if(textmail.contains("@") && textmail.contains(".")) {
					mail=new SendEmail(textmail, "Narudzbina", pomGlavnoJelo.ispis()+"\n"+pomSalata.ispis()+"\n"+pomSlatkis.ispis());
					JOptionPane.showMessageDialog(null, "Narudzbina poslata na mail");
					

				}else
					JOptionPane.showMessageDialog(null, "Unesite ispravnu email adresu");
			}
		});
		contentPane.add(btnPosaljiPorudzbinu);

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
}
