package Test;

import java.sql.SQLException;

import rs.model.DAOGlavnoJelo;
import rs.model.DAOSalata;
import rs.model.DAOSlatkis;
import rs.model.GlavnoJelo;
import rs.model.Salata;
import rs.model.Slatkis;

public class Proba1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAOGlavnoJelo dgj=new DAOGlavnoJelo();
		DAOSalata dsalata=new DAOSalata();
		DAOSlatkis dslatkis=new DAOSlatkis();
		try {
			//dgj.insertGlavnoJelo(new GlavnoJelo("Becka", 200, 380));
			//dsalata.insertSalata(new Salata("Kupus salata", 120, 80));
			//dslatkis.insertSlatkis(new Slatkis("Isleri", 95, 105.65));
			//dgj.deleteGlavnoJeloByID(4);
			//dsalata.deleteSalataByID(3);
			//dslatkis.deleteSlatkisByID(4);
			System.out.println(dgj.selectGlavnoJeloByNaziv("Karadjordjeva"));
			System.out.println(dsalata.selectSalataByNaziv("Sopska"));
			System.out.println(dslatkis.selectSLatkisByNaziv("Jaffa"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
