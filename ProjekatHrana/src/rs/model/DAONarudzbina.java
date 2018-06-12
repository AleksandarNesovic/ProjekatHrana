package rs.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		preparedStatement = connect.prepareStatement("insert into Narudzbina (id_klijenta,id_glj,id_sal,id_slat,KolicinaGlavnogJela,KolicinaSalate,Email,datumPorudzbine) values (?,?,?,?,?,?,?,?)");
		
		preparedStatement.setInt(1, n.getKlijent().getId_klijenta());
		preparedStatement.setInt(2, n.getGlavnoJelo().getId_glj());
		preparedStatement.setInt(3, n.getSalata().getId_sal());
		preparedStatement.setInt(4, n.getSlatkis().getId_slat());
		preparedStatement.setInt(5, n.getKolicinaGlavnogJele());
		preparedStatement.setInt(6, n.getKolicinaSalate());
		preparedStatement.setString(7, n.getEmail());
		preparedStatement.setDate(8, n.getDatumPorudzbine());
		
		preparedStatement.execute();
		
		close();
	}
	public ArrayList<Narudzbina> selectNarudzbinaByDatum(Date datum) throws ClassNotFoundException, SQLException {
		Narudzbina pom = null;
		ArrayList<Narudzbina> lista=new ArrayList<Narudzbina>();

		connect();
		preparedStatement = connect.prepareStatement("select * from Narudzbina WHERE datumPorudzbine = ?");

		preparedStatement.setDate(1, datum);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new Narudzbina();
			
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
