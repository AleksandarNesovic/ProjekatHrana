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
		return 9;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Narudzbina n=lista.get(r);
		switch(c) {
		case 0: return n.getId_narudzbine();
		case 1: return n.getKlijent().getId_klijenta();
		case 2: return n.getGlavnoJelo().getId_glj();
		case 3: return n.getSalata().getId_sal();
		case 4: return n.getSlatkis().getId_slat();
		case 5: return n.getKolicinaGlavnogJele();
		case 6: return n.getKolicinaSalate();
		case 7: return n.getEmail();
		case 8: return n.getDatumPorudzbine();
		default: return "Greska!";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "ID Narudzbine";
		case 1: return "ID Klijenta";
		case 2: return "ID Glavnog";
		case 3: return "ID Salate";
		case 4: return "ID Slatkisa";
		case 5: return "Kol Glavnog jela" ;
		case 6: return "Kolicina Salate";
		case 7: return "Email";
		case 8: return "Datum porudzbine";
		default: return "Greska!";
		}
	}

}
