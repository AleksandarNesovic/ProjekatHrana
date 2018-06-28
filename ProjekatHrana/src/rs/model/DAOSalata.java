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
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:Narudzbine.db ");
	}
	public void createTable() throws ClassNotFoundException, SQLException {
		String sql="CREATE TABLE IF NOT EXISTS Salata ( `id_sal` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NazivSalate` varchar ( 255 )UNIQUE NOT NULL, `CenaSalate` double ( 11,2 ) NOT NULL );";
		connect();
		statement=connect.createStatement();
		statement.execute(sql);
	}
    public void insert(String naziv, double cena) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO Salata(nazivSalate,cenaSalate) VALUES(?,?)";
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
			pom.setNaziv(resultSet.getString("nazivSalate"));
			pom.setCena(resultSet.getDouble("cenaSalate"));

			lista.add(pom);
		}
		
		close();
		return lista;
	}

	public void insertSalata(Salata salata) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO Salata(nazivSalate, cenaSalate) VALUES (?,?)");

		preparedStatement.setString(1, salata.getNaziv());
		preparedStatement.setDouble(2, salata.getCena());
		
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
	public void deleteSalataByNaziv(String naziv) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("delete from Salata where nazivSalate = ?");

		preparedStatement.setString(1, naziv);
		
		preparedStatement.execute();
		
		
		close();
	}
	public void updateSalata(Salata gj) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement = connect.prepareStatement("UPDATE Salata SET nazivSalate=?, cenaSalate=? WHERE id_sal=?");

		preparedStatement.setString(1, gj.getNaziv());
		preparedStatement.setDouble(2, gj.getCena());
		preparedStatement.setInt(3, gj.getId_sal());

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
	public boolean daLiPostoji(Salata g) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement=connect.prepareStatement("SELECT * FROM Salata Where nazivSalate=?");
		preparedStatement.setString(1, g.getNaziv());
		preparedStatement.execute();
		resultSet=preparedStatement.getResultSet();
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

	public Salata selectSalataByNaziv(String naziv) throws ClassNotFoundException, SQLException {
		Salata salata = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Salata WHERE nazivSalate= ?");

		preparedStatement.setString(1, naziv);
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			salata = new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			salata.setNaziv(resultSet.getString("nazivSalate"));
			salata.setCena(resultSet.getDouble("cenaSalate"));
		}
		
		close();
		return salata;
	}
	public Salata selectIzabranaSalata(Salata sal) throws ClassNotFoundException, SQLException {
		Salata salata = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Salata");

		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			salata = new Salata();
			salata.setId_sal(resultSet.getInt("id_sal"));
			salata.setNaziv(resultSet.getString("nazivSalate"));
			salata.setCena(resultSet.getDouble("cenaSalate"));
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
			salata.setNaziv(resultSet.getString("nazivSalate"));
			salata.setCena(resultSet.getDouble("cenaSalate"));
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
