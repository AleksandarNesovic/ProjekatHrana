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
		return 8;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Narudzbina n=lista.get(r);
		switch(c) {
		case 0: return n.getKlijent().getId_klijenta();
		case 1: return n.getGlavnoJelo().getId_glj();
		case 2: return n.getSalata().getId_sal();
		case 3: return n.getSlatkis().getId_slat();
		case 4: return n.getKolicinaGlavnogJele();
		case 5: return n.getKolicinaSalate();
		case 6: return n.getEmail();
		case 7: return n.getDatumPorudzbine();
		default: return "Greska!";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "ID Klijenta";
		case 1: return "ID Glavnog jela";
		case 2: return "ID Salate";
		case 3: return "ID Slatkisa";
		case 4: return "Kol Glavnog jela" ;
		case 5: return "Kolicina Salate";
		case 6: return "Email";
		case 7: return "Datum porudzbine";
		default: return "Greska!";
		}
	}

}
