package rs.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOKlijent {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:Narudzbine.db ");
	}
	public void createTable() throws ClassNotFoundException, SQLException {
		String sql="CREATE TABLE IF NOT EXISTS Klijenti (`id_klijenta` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Ime` varchar ( 255 )UNIQUE NOT NULL, `Prezime` varchar ( 255 )UNIQUE NOT NULL, `BrojTelefona` varchar ( 255 )UNIQUE NOT NULL, `EmailKlijenta` varchar ( 255 )UNIQUE NOT NULL );";
		connect();
		statement=connect.createStatement();
		statement.execute(sql);
	}
    public void insert(String ime, String prezime,String brtel,String email) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO Klijenti(Ime,Prezime,BrojTelefona,EmailKlijenta) VALUES(?,?,?,?)";
        try {
        connect();
           	PreparedStatement pstmt = connect.prepareStatement(sql); 
            pstmt.setString(1, ime);
            pstmt.setString(2, prezime);
            pstmt.setString(3, brtel);
            pstmt.setString(4, email);
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
	
		public ArrayList<Klijent> selectKlijenta() throws ClassNotFoundException, SQLException {

			ArrayList<Klijent> lista = new ArrayList<Klijent>();
			Klijent pom=null;
			try {
			connect();
			preparedStatement = connect.prepareStatement("select * from Klijenti");
			
			preparedStatement.execute();

			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				pom=new Klijent();
				pom.setId_klijenta(resultSet.getInt("id_klijenta"));
				pom.setIme(resultSet.getString("Ime"));
				pom.setPrezime(resultSet.getString("Prezime"));
				pom.setBrojTelefona(resultSet.getString("BrojTelefona"));
				pom.setEmail(resultSet.getString("EmailKlijenta"));

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
		
		public Klijent selectKlijentaById(int id) throws ClassNotFoundException, SQLException {

			Klijent pom=null;
			try {
			connect();
			
			preparedStatement = connect.prepareStatement("select * from Klijenti WHERE id_klijenta=?");
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();

			resultSet = preparedStatement.getResultSet();

			while (resultSet.next()) {
				pom=new Klijent();
				pom.setId_klijenta(resultSet.getInt("id_klijenta"));
				pom.setIme(resultSet.getString("Ime"));
				pom.setPrezime(resultSet.getString("Prezime"));
				pom.setBrojTelefona(resultSet.getString("BrojTelefona"));
				pom.setEmail(resultSet.getString("EmailKlijenta"));
				
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
		public boolean searchById(int id) throws ClassNotFoundException, SQLException {
			
			Klijent pom=null;
			try {
			connect();
			
			preparedStatement=connect.prepareStatement("select id_klijenta from Klijenti");
			
			preparedStatement.execute();
			
			resultSet=preparedStatement.getResultSet();
			
			while (resultSet.next()) {
				pom=new Klijent();
				pom.setId_klijenta(resultSet.getInt("id_klijenta"));
				if(pom.getId_klijenta()==id)
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
