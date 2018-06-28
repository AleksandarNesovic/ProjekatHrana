package rs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOSlatkis {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:Narudzbine.db ");
	}
	public void createTable() throws ClassNotFoundException, SQLException {
		String sql="CREATE TABLE IF NOT EXISTS Slatkis ( `Id_slat` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NazivSlatkisa` varchar ( 255 )UNIQUE NOT NULL, `Kolicina` int ( 11 ) NOT NULL, `CenaSlatkisa` double ( 11,2 ) NOT NULL );";
		connect();
		statement=connect.createStatement();
		statement.execute(sql);
	}
    public void insert(String naziv,int kolicina, double cena) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO Slatkis(nazivSlatkisa,Kolicina,cenaSlatkisa) VALUES(?,?,?)";
        try {
        connect();
           	PreparedStatement pstmt = connect.prepareStatement(sql); 
            pstmt.setString(1, naziv);
            pstmt.setInt(2, kolicina);
            pstmt.setDouble(3, cena);
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
    public boolean daLiPostoji(Slatkis g) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement=connect.prepareStatement("SELECT * FROM Slatkis Where nazivSlatkisa=?");
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
	
	public ArrayList<Slatkis> selectSlatkis() throws ClassNotFoundException, SQLException {
		
		ArrayList<Slatkis> lista = new ArrayList<Slatkis>();
		Slatkis pom=null;

		connect();
		preparedStatement = connect.prepareStatement("select * from Slatkis");

		
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			pom=new Slatkis();
			pom.setId_slat(resultSet.getInt("id_slat"));
			pom.setNaziv(resultSet.getString("nazivSlatkisa"));
			pom.setKolicina(resultSet.getInt("Kolicina"));
			pom.setCena(resultSet.getDouble("cenaSlatkisa"));


			lista.add(pom);
		}
		
		close();
		return lista;
	}

	public void insertSlatkis(Slatkis slatkis) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("INSERT INTO Slatkis(nazivSlatkisa, kolicina, cenaSlatkisa) VALUES (?,?,?)");

		preparedStatement.setString(1, slatkis.getNaziv());
		preparedStatement.setInt(2, slatkis.getKolicina());
		preparedStatement.setDouble(3, slatkis.getCena());
		
		preparedStatement.execute();
		
		
		close();
	}
	
	public void deleteSlatkisByID(int id) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("delete from Slatkis where id_slat = ?");

		preparedStatement.setInt(1, id);
		
		preparedStatement.execute();
		
		
		close();
	}
	public void deleteSlatkisByNaziv(String naziv) throws ClassNotFoundException, SQLException {

		connect();
		preparedStatement = connect.prepareStatement("delete from Slatkis where nazivSlatkisa = ?");

		preparedStatement.setString(1, naziv);
		
		preparedStatement.execute();
		
		
		close();
	}
	public void updateSlatkisi(Slatkis gj) throws ClassNotFoundException, SQLException {
		try {
		connect();
		preparedStatement = connect.prepareStatement("UPDATE Slatkis SET nazivSlatkisa=?,kolicina=?, cenaSlatkisa=? WHERE id_slat=?");

		preparedStatement.setString(1, gj.getNaziv());
		preparedStatement.setInt(2, gj.getKolicina());
		preparedStatement.setDouble(3, gj.getCena());
		preparedStatement.setInt(4, gj.getId_slat());

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

	public Slatkis selectSLatkisByNaziv(String naziv) throws ClassNotFoundException, SQLException {
		Slatkis slatkis = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Slatkis WHERE nazivSlatkisa= ?");

		preparedStatement.setString(1, naziv);
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			slatkis = new Slatkis();
			slatkis.setId_slat(resultSet.getInt("id_slat"));
			slatkis.setNaziv(resultSet.getString("nazivSlatkisa"));
			slatkis.setKolicina(resultSet.getInt("Kolicina"));
			slatkis.setCena(resultSet.getDouble("cenaSlatkisa"));
		}
		
		close();
		return slatkis;
	}
	public Slatkis selectSLatkisById(int id_slat) throws ClassNotFoundException, SQLException {
		Slatkis slatkis = null;
		
		connect();
		preparedStatement = connect.prepareStatement("select * from Slatkis WHERE id_slat= ?");

		preparedStatement.setInt(1, id_slat);
		
		preparedStatement.execute();
		
		resultSet = preparedStatement.getResultSet();
		
		if (resultSet.next()) {
			slatkis = new Slatkis();
			slatkis.setId_slat(resultSet.getInt("id_slat"));
			slatkis.setNaziv(resultSet.getString("nazivSlatkisa"));
			slatkis.setKolicina(resultSet.getInt("Kolicina"));
			slatkis.setCena(resultSet.getDouble("cenaSlatkisa"));
		}
		
		close();
		return slatkis;
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
