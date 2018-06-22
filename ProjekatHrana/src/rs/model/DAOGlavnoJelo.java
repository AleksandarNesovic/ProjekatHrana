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
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:Narudzbine.db ");
		}
	public void createTable() throws ClassNotFoundException, SQLException {
		String sql="CREATE TABLE IF NOT EXISTS Glavno_jelo ( `id_glj` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
				+"`Naziv` varchar ( 255 )UNIQUE NOT NULL, `Cena` double ( 11,2 )UNIQUE NOT NULL );";
		connect();
		statement=connect.createStatement();
		statement.execute(sql);
	}
    public void insert(String naziv, double cena) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO Glavno_jelo(naziv,cena) VALUES(?,?)";
        try {
        connect();
           	PreparedStatement pstmt = connect.prepareStatement(sql); 
            pstmt.setString(1, naziv);
            pstmt.setDouble(2, cena);
            pstmt.executeUpdate();
        } finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
        }
    }
    

	public ArrayList<GlavnoJelo> selectGlavnoJelo() throws ClassNotFoundException, SQLException {

		ArrayList<GlavnoJelo> lista = new ArrayList<GlavnoJelo>();
		GlavnoJelo pom=null;
		try {
		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo");

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			pom=new GlavnoJelo();
			pom.setId_glj(resultSet.getInt("id_glj"));
			pom.setNaziv(resultSet.getString("naziv"));
			pom.setCena(resultSet.getDouble("cena"));

			lista.add(pom);
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}}

		close();
		return lista;
	}
	public int countGlavnoJelo() throws ClassNotFoundException, SQLException {

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
		try {
		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo WHERE naziv= ?");

		preparedStatement.setString(1, naziv);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}}

		close();
		return glavnoJelo;
	}
	public GlavnoJelo selectGlavnoJeloById(int id_glj) throws ClassNotFoundException, SQLException {
		GlavnoJelo glavnoJelo = null;
		try {
		connect();
		preparedStatement = connect.prepareStatement("select * from Glavno_jelo WHERE id_glj= ?");

		preparedStatement.setInt(1, id_glj);

		preparedStatement.execute();

		resultSet = preparedStatement.getResultSet();

		if (resultSet.next()) {
			glavnoJelo = new GlavnoJelo();
			glavnoJelo.setId_glj(resultSet.getInt("id_glj"));
			glavnoJelo.setNaziv(resultSet.getString("naziv"));
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}}

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
	}
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
			glavnoJelo.setCena(resultSet.getDouble("cena"));
		}

		close();
		return glavnoJelo;
	}*/

	public void insertGlavnoJelo(GlavnoJelo gj) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO Glavno_jelo(naziv, kolicina, cena) VALUES (?,?,?)");

		preparedStatement.setString(1, gj.getNaziv());
		preparedStatement.setDouble(3, gj.getCena());

		preparedStatement.execute();
		}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}}

		close();
	}

	public void deleteGlavnoJeloByID(int id) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement = connect.prepareStatement("delete from Glavno_jelo where id_glj = ?");

		preparedStatement.setInt(1, id);

		preparedStatement.execute();
		}finally {
			try {
				if(preparedStatement!=null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				} }catch (SQLException e) {
					System.out.println(e.getMessage());
				}}

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
