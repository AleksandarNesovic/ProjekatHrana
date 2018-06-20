package rs.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DAONarudzbina {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	public ArrayList<Narudzbina> selectNarudzbina() throws ClassNotFoundException, SQLException {

		ArrayList<Narudzbina> lista = new ArrayList<Narudzbina>();
		Narudzbina pom=null;

		connect();

		preparedStatement = connect.prepareStatement("select * from Narudzbina");

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();
			pom.setId_narudzbine(resultSet.getInt("id_narudzbine"));

			Klijent klijent=new Klijent();
			klijent.setId_klijenta(resultSet.getInt("id_klijenta"));
			pom.setKlijent(klijent);

			GlavnoJelo glavno=new GlavnoJelo();
			glavno.setId_glj(resultSet.getInt("id_glj"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			pom.setSalata(salata);

			Slatkis slatkis=new Slatkis();
			slatkis.setId_slat(resultSet.getInt("id_slat"));
			pom.setSlatkis(slatkis);

			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("Email"));
			pom.setDatumPorudzbine(resultSet.getDate("datumPorudzbine"));

			lista.add(pom);
		}
		close();
		return lista;
	}

	public void insertNarudzbina(Narudzbina n) throws ClassNotFoundException, SQLException {

		connect();

		preparedStatement = connect.prepareStatement("insert into Narudzbina (id_narudzbine,id_klijenta,id_glj,id_sal,id_slat,KolicinaGlavnogJela,KolicinaSalate,Email,datumPorudzbine) values (?,?,?,?,?,?,?,?,?)");

		preparedStatement.setInt(1, n.getId_narudzbine());
		preparedStatement.setInt(2, n.getKlijent().getId_klijenta());
		preparedStatement.setInt(3, n.getGlavnoJelo().getId_glj());
		preparedStatement.setInt(4, n.getSalata().getId_sal());
		preparedStatement.setInt(5, n.getSlatkis().getId_slat());
		preparedStatement.setInt(6, n.getKolicinaGlavnogJele());
		preparedStatement.setInt(7, n.getKolicinaSalate());
		preparedStatement.setString(8, n.getEmail());
		preparedStatement.setDate(9, n.getDatumPorudzbine());

		preparedStatement.execute();

		close();
	}
	public ArrayList<Narudzbina> selectNarudzbinaByDatum(Date datum) throws ClassNotFoundException, SQLException {

		Narudzbina pom = null;
		ArrayList<Narudzbina> lista=new ArrayList<Narudzbina>();

		connect();
		preparedStatement = connect.prepareStatement("SELECT n.id_narudzbine, k.ime,k.prezime, g.naziv, sal.naziv, slat.naziv, KolicinaGlavnogJela, KolicinaSalate, n.Email, datumPorudzbine FROM Narudzbina n,Klijenti k,Glavno_jelo g,Salata sal,Slatkis slat WHERE n.id_klijenta=k.id_klijenta AND n.id_glj=g.id_glj AND n.id_sal=sal.id_sal AND n.id_slat=slat.id_slat AND datumPorudzbine = ?");

		preparedStatement.setDate(1, datum);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();

			pom.setId_narudzbine(resultSet.getInt("n.id_narudzbine"));
			Klijent klijent=new Klijent();
			klijent.setIme(resultSet.getString("Ime"));
			klijent.setPrezime(resultSet.getString("Prezime"));
			pom.setKlijent(klijent);

			GlavnoJelo glavno=new GlavnoJelo();
			glavno.setNaziv(resultSet.getString("g.naziv"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setNaziv(resultSet.getString("sal.naziv"));
			pom.setSalata(salata);

			Slatkis slatkis=new Slatkis();
			slatkis.setNaziv(resultSet.getString("slat.naziv"));
			pom.setSlatkis(slatkis);

			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("n.Email"));
			pom.setDatumPorudzbine(resultSet.getDate("datumPorudzbine"));
			lista.add(pom);
		}
		close();
		return lista;

	}
	public Narudzbina selectNarudzbinaByID(int id) throws ClassNotFoundException, SQLException {

		Narudzbina pom = null;

		connect();
		preparedStatement = connect.prepareStatement("SELECT n.id_narudzbine, k.ime,k.prezime, g.naziv,g.cena, sal.naziv,sal.cena, slat.naziv,slat.cena, KolicinaGlavnogJela, KolicinaSalate, n.Email, datumPorudzbine FROM Narudzbina n,Klijenti k,Glavno_jelo g,Salata sal,Slatkis slat WHERE n.id_klijenta=k.id_klijenta AND n.id_glj=g.id_glj AND n.id_sal=sal.id_sal AND n.id_slat=slat.id_slat AND id_narudzbine = ?");

		preparedStatement.setInt(1, id);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();

			pom.setId_narudzbine(resultSet.getInt("id_narudzbine"));

			Klijent klijent=new Klijent();
			klijent.setIme(resultSet.getString("Ime"));
			klijent.setPrezime(resultSet.getString("Prezime"));
			pom.setKlijent(klijent);

			GlavnoJelo glavno=new GlavnoJelo();
			glavno.setNaziv(resultSet.getString("g.naziv"));
			glavno.setCena(resultSet.getDouble("g.cena"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setNaziv(resultSet.getString("sal.naziv"));
			salata.setCena(resultSet.getDouble("sal.cena"));
			pom.setSalata(salata);

			Slatkis slatkis=new Slatkis();
			slatkis.setNaziv(resultSet.getString("slat.naziv"));
			slatkis.setCena(resultSet.getDouble("slat.cena"));
			pom.setSlatkis(slatkis);

			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("n.Email"));
			pom.setDatumPorudzbine(resultSet.getDate("datumPorudzbine"));
		}
		close();
		return pom;

	}
	public boolean searchNarudzbineById(int id) throws ClassNotFoundException, SQLException {

		Narudzbina pom=null;

		connect();

		preparedStatement=connect.prepareStatement("select id_narudzbine from Narudzbina");

		preparedStatement.execute();

		resultSet=preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();
			pom.setId_narudzbine(resultSet.getInt("id_narudzbine"));
			if(pom.getId_narudzbine()==id)
				return true;
		}

		close();
		return false;
	}
	public boolean proveraNarudzbina(int id,Date datum) throws ClassNotFoundException, SQLException {

		connect();

		preparedStatement = connect.prepareStatement("select * from Narudzbina where id_klijenta=? AND datumPorudzbine=?");

		preparedStatement.setInt(1, id);
		preparedStatement.setDate(2, datum);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if(resultSet.next()) {
			return true;
		}

		close();
		return false;
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
