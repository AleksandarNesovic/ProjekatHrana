package rs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSalata {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba2 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	
	public ArrayList<Salata> selectSalata() throws ClassNotFoundException, SQLException {
		
		ArrayList<Salata> lista = new ArrayList<Salata>();
		Salata pom=null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Salata");

		
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			pom=new Salata();
			pom.setId_sal(resultSet.getInt("id_sal"));
			pom.setNaziv(resultSet.getString("naziv"));
			pom.setPorcija(resultSet.getInt("porcija"));
			pom.setCena(resultSet.getDouble("cena"));

			lista.add(pom);
		}
		
		close();
		return lista;
	}

	public void insertSalata(Salata salata) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO Salata(naziv, porcija, cena) VALUES (?,?,?)");

		preparedStatement.setString(1, salata.getNaziv());
		preparedStatement.setInt(2, salata.getPorcija());
		preparedStatement.setDouble(3, salata.getCena());
		
		preparedStatement.execute();
		
		
		close();
	}
	
	public void deleteSalataByID(int id) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("delete from Salata where id_sal = ?");

		preparedStatement.setInt(1, id);
		
		preparedStatement.execute();
		
		
		close();
	}
	public Salata selectSalataByNaziv(String naziv) throws ClassNotFoundException, SQLException {
		Salata salata = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Salata WHERE naziv= ?");

		preparedStatement.setString(1, naziv);
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			salata = new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			salata.setNaziv(resultSet.getString("naziv"));
			salata.setPorcija(resultSet.getInt("porcija"));
			salata.setCena(resultSet.getDouble("cena"));
		}
		
		close();
		return salata;
	}
	public Salata selectSalataById(int id_sal) throws ClassNotFoundException, SQLException {
		Salata salata = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Salata WHERE id_sal= ?");

		preparedStatement.setInt(1, id_sal);
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			salata = new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			salata.setNaziv(resultSet.getString("naziv"));
			salata.setPorcija(resultSet.getInt("porcija"));
			salata.setCena(resultSet.getDouble("cena"));
		}
		
		close();
		return salata;
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
