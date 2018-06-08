package rs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			GlavnoJelo glavno=new GlavnoJelo();
			glavno.setNaziv(resultSet.getString("Naziv"));
			glavno.setCena(resultSet.getInt("Cena"));
			pom.setGlavnoJelo(glavno);

			Salata salata=new Salata();
			salata.setNaziv(resultSet.getString("Naziv"));
			salata.setCena(resultSet.getInt("Cena"));
			pom.setSalata(salata);
			
			Slatkis slatkis=new Slatkis();
			slatkis.setNaziv(resultSet.getString("Naziv"));
			slatkis.setCena(resultSet.getInt("Cena"));
			pom.setSlatkis(slatkis);
			
			pom.setKolicinaGlavnogJele(resultSet.getInt("KolicinaGlavnogJela"));
			pom.setKolicinaSalate(resultSet.getInt("KolicinaSalate"));
			pom.setEmail(resultSet.getString("Email"));
			
			

			lista.add(pom);
		}

		close();
		return lista;
	}
	
	public void insertNarudzbina(Narudzbina n) throws ClassNotFoundException, SQLException {
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		int id_glj = 0;
		int id_sal=0;
		int id_slat=0;
		
		connect();
		preparedStatement = connect.prepareStatement("select id_glj from Glavno_jelo");
		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			id_glj=resultSet.getInt("id_glj");
		}
		preparedStatement = connect.prepareStatement("select id_sal from Salata");
		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			id_sal=resultSet.getInt("id_sal");
		}
		preparedStatement = connect.prepareStatement("select id_slat from Slatkis");
		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			id_slat=resultSet.getInt("id_slat");
		}
		preparedStatement = connect.prepareStatement("insert into Narudzbina (id_glj,id_sal,id_slat,KolicinaGlavnogJela,KolicinaSalate,Email) values (?,?,?,?,?,?)");
		
		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		preparedStatement.setInt(1, id_glj);
		preparedStatement.setInt(2, id_sal);
		preparedStatement.setInt(3, id_slat);
		preparedStatement.setInt(4, n.getKolicinaGlavnogJele());
		preparedStatement.setInt(5, n.getKolicinaSalate());
		preparedStatement.setString(6, n.getEmail());
		
		preparedStatement.execute();
		
		close();
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
