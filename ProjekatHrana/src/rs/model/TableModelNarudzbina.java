package rs.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModelNarudzbina extends AbstractTableModel{
	ArrayList<Narudzbina> lista=new ArrayList<Narudzbina>();
	

	public TableModelNarudzbina(ArrayList<Narudzbina> lista) {
		super();
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Narudzbina n=lista.get(r);
		switch(c) {
		case 0: return n.getId_narudzbine();
		case 1: return n.getKlijent().getIme();
		case 2: return n.getKlijent().getPrezime();
		case 3: return n.getGlavnoJelo().getNaziv();
		case 4: return n.getSalata().getNaziv();
		case 5: return n.getSlatkis().getNaziv();
		case 6: return n.getKolicinaGlavnogJele();
		case 7: return n.getKolicinaSalate();
		case 8: return n.getEmail();
		case 9: return n.getDatumPorudzbine();
		default: return "Greska!";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "ID Narudzbine";
		case 1: return "Ime";
		case 2: return "Prezime";
		case 3: return "Glavno jelo";
		case 4: return "Salata";
		case 5: return "Slatkis";
		case 6: return "Kol Glavnog jela" ;
		case 7: return "Kolicina Salate";
		case 8: return "Email";
		case 9: return "Datum porudzbine";
		default: return "Greska!";
		}
	}

}
