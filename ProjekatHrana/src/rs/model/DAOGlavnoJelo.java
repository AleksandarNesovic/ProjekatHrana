package rs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOGlavnoJelo {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}


	public ArrayList<GlavnoJelo> selectGlavnoJelo() throws ClassNotFoundException, SQLException {

		ArrayList<GlavnoJelo> lista = new ArrayList<GlavnoJelo>();
		GlavnoJelo pom=null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo");



		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new GlavnoJelo();
			pom.setId_glj(resultSet.getInt("id_glj"));
			pom.setNaziv(resultSet.getString("naziv"));
			pom.setKolicina(resultSet.getInt("kolicina"));
			pom.setCena(resultSet.getDouble("cena"));


			lista.add(pom);
		}

		close();
		return lista;
	}
	public int countGlavnoJelo() throws ClassNotFoundException, SQLException {

		//ArrayList<GlavnoJelo> lista = new ArrayList<GlavnoJelo>();
		//GlavnoJelo pom=null;
		int num=0;
		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo");



		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();
		for (int i = 0; i < resultSet.getRow(); i++) {
			num++;
		}

		close();
		return num;
	}
	public GlavnoJelo selectGlavnoJeloByNaziv(String naziv) throws ClassNotFoundException, SQLException {
		GlavnoJelo glavnoJelo = null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo WHERE naziv= ?");

		preparedStatement.setString(1, naziv);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setKolicina(resultSet.getInt("kolicina"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}

		close();
		return glavnoJelo;
	}
	public GlavnoJelo selectGlavnoJeloById(int id_glj) throws ClassNotFoundException, SQLException {
		GlavnoJelo glavnoJelo = null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo WHERE id_glj= ?");

		preparedStatement.setInt(1, id_glj);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setKolicina(resultSet.getInt("kolicina"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}

		close();
		return glavnoJelo;
	}
/*	public GlavnoJelo selectGlavnoJeloByNaziv(String naziv) throws ClassNotFoundException, SQLException {
		GlavnoJelo glavnoJelo = null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo WHERE naziv= ?");

		preparedStatement.setString(1, naziv);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setKolicina(resultSet.getInt("kolicina"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}

		close();
		return glavnoJelo;
	}*/
	public GlavnoJelo selectIzabranoGlavnoJelo(GlavnoJelo glavno) throws ClassNotFoundException, SQLException {
		GlavnoJelo glavnoJelo = null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo");

		//preparedStatement.setInt(1, id_glj);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setKolicina(resultSet.getInt("kolicina"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}

		close();
		return glavnoJelo;
	}

	public void insertGlavnoJelo(GlavnoJelo gj) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO Glavno_jelo(naziv, kolicina, cena) VALUES (?,?,?)");

		preparedStatement.setString(1, gj.getNaziv());
		preparedStatement.setInt(2, gj.getKolicina());
		preparedStatement.setDouble(3, gj.getCena());

		preparedStatement.execute();


		close();
	}

	public void deleteGlavnoJeloByID(int id) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("delete from Glavno_jelo where id_glj = ?");

		preparedStatement.setInt(1, id);

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
