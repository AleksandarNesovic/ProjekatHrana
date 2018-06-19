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
		connect = DriverManager.getConnection("jdbc:sqlite:/home/dev33/Documents/EclipsWorkspace/Baze/Vezba3 ");
	}
	
		public ArrayList<Klijent> selectKlijenta() throws ClassNotFoundException, SQLException {

			ArrayList<Klijent> lista = new ArrayList<Klijent>();
			Klijent pom=null;

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
				pom.setEmail(resultSet.getString("Email"));

				lista.add(pom);
			}
			close();
			return lista;
		}
		
		public Klijent selectKlijentaById(int id) throws ClassNotFoundException, SQLException {

			Klijent pom=null;

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
				pom.setEmail(resultSet.getString("Email"));

			}
			close();
			return pom;
		}
		public boolean searchById(int id) throws ClassNotFoundException, SQLException {
			
			Klijent pom=null;
			
			connect();
			
			preparedStatement=connect.prepareStatement("select id_klijenta from Klijenti");
			
			preparedStatement.execute();
			
			resultSet=preparedStatement.getResultSet();
			
			while (resultSet.next()) {
				pom=new Klijent();
				pom.setId_klijenta(resultSet.getInt("id_klijenta"));
				if(pom.getId_klijenta()==id)
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
