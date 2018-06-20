package rs.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

public class DAONarudzbina {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:/home/dev33/Documents/EclipsWorkspace/Baze/Vezba3 ");
	}

	public ArrayList<Narudzbina> selectNarudzbina() throws ClassNotFoundException, SQLException {

		ArrayList<Narudzbina> lista = new ArrayList<Narudzbina>();
		Narudzbina pom=null;
		try {
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
			pom.setDatumPorudzbine(resultSet.getString("datumPorudzbine"));

			lista.add(pom);
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		close();
		return lista;
	}

	public void insertNarudzbina(Narudzbina n) throws ClassNotFoundException, SQLException {
		try {
		connect();
		
		preparedStatement = connect.prepareStatement("insert into Narudzbina (id_klijenta,id_glj,id_sal,id_slat,KolicinaGlavnogJela,KolicinaSalate,Email,datumPorudzbine) values (?,?,?,?,?,?,?,?)");

		preparedStatement.setInt(1, n.getKlijent().getId_klijenta());
		preparedStatement.setInt(2, n.getGlavnoJelo().getId_glj());
		preparedStatement.setInt(3, n.getSalata().getId_sal());
		preparedStatement.setInt(4, n.getSlatkis().getId_slat());
		preparedStatement.setInt(5, n.getKolicinaGlavnogJele());
		preparedStatement.setInt(6, n.getKolicinaSalate());
		preparedStatement.setString(7, n.getEmail());
		preparedStatement.setString(8, n.getDatumPorudzbine());

		preparedStatement.executeUpdate();
		}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

		close();
	}
	public ArrayList<Narudzbina> selectNarudzbinaByDatum(String datum) throws ClassNotFoundException, SQLException, ParseException {

		Narudzbina pom = null;
		ArrayList<Narudzbina> lista=new ArrayList<Narudzbina>();
		try {
		connect();
		preparedStatement = connect.prepareStatement("SELECT n.id_narudzbine, k.ime,k.prezime, g.naziv, sal.nazivSalate, slat.nazivSlatkisa, KolicinaGlavnogJela, KolicinaSalate, n.Email, datumPorudzbine FROM Narudzbina n,Klijenti k,Glavno_jelo g,Salata sal,Slatkis slat WHERE n.id_klijenta=k.id_klijenta AND n.id_glj=g.id_glj AND n.id_sal=sal.id_sal AND n.id_slat=slat.id_slat AND datumPorudzbine = ?");

		preparedStatement.setString(1,datum);

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
			glavno.setNaziv(resultSet.getString("naziv"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setNaziv(resultSet.getString("nazivSalate"));
			pom.setSalata(salata);

			Slatkis slatkis=new Slatkis();
			slatkis.setNaziv(resultSet.getString("nazivSlatkisa"));
			pom.setSlatkis(slatkis);

			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("Email"));
			pom.setDatumPorudzbine(resultSet.getString("datumPorudzbine"));
			lista.add(pom);
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		close();
		return lista;

	}
	public Narudzbina selectNarudzbinaByID(int id) throws ClassNotFoundException, SQLException, ParseException {
	
		Narudzbina pom = null;
		try {
		connect();
		preparedStatement = connect.prepareStatement("select * from Narudzbina,Glavno_jelo,Klijenti,Salata,Slatkis WHERE id_narudzbine = ?");

		preparedStatement.setInt(1, id);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();

			pom.setId_narudzbine(resultSet.getInt("id_narudzbine"));

			Klijent klijent=new Klijent();
			klijent.setId_klijenta(resultSet.getInt("id_klijenta"));
			klijent.setIme(resultSet.getString("Ime"));
			klijent.setPrezime(resultSet.getString("Prezime"));
			klijent.setBrojTelefona(resultSet.getString("BrojTelefona"));
			klijent.setEmail(resultSet.getString("EmailKlijenta"));
			pom.setKlijent(klijent);

			GlavnoJelo glavno=new GlavnoJelo();
			glavno.setId_glj(resultSet.getInt("id_glj"));
			glavno.setNaziv(resultSet.getString("naziv"));
			glavno.setCena(resultSet.getDouble("cena"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			salata.setNaziv(resultSet.getString("nazivSalate"));
			salata.setCena(resultSet.getDouble("cenaSalate"));
			pom.setSalata(salata);

			Slatkis slatkis=new Slatkis();
			slatkis.setId_slat(resultSet.getInt("id_slat"));
			slatkis.setNaziv(resultSet.getString("nazivSlatkisa"));
			slatkis.setKolicina(resultSet.getInt("Kolicina"));
			slatkis.setCena(resultSet.getDouble("cenaSlatkisa"));
			pom.setSlatkis(slatkis);

			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("Email"));
			pom.setDatumPorudzbine(resultSet.getString("datumPorudzbine"));
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		close();
		return pom;

	}
	public boolean searchNarudzbineById(int id) throws ClassNotFoundException, SQLException {

		Narudzbina pom=null;
		try {
		connect();

		preparedStatement=connect.prepareStatement("select id_narudzbine from Narudzbina");

		preparedStatement.execute();

		resultSet=preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();
			pom.setId_narudzbine(resultSet.getInt("id_narudzbine"));
			if(pom.getId_narudzbine()==id)
				
				return true;
			
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		
		close();
		return false;
	}
	public boolean proveraNarudzbina(int id,String datum) throws ClassNotFoundException, SQLException, ParseException {
		try {
		connect();

		preparedStatement = connect.prepareStatement("select * from Narudzbina where id_klijenta=? AND datumPorudzbine=?");

		preparedStatement.setInt(1, id);
		preparedStatement.setString(2,datum);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if(resultSet.next()) {
			
			return true;
			
			
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		close();
		return false;
	}

	public void close() {
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
