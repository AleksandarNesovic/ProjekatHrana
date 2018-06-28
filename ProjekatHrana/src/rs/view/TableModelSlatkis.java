package rs.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import rs.model.GlavnoJelo;
import rs.model.Slatkis;

public class TableModelSlatkis extends AbstractTableModel{
	ArrayList<Slatkis> lista=new ArrayList<>();

	public TableModelSlatkis(ArrayList<Slatkis> lista) {
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
		return 4;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Slatkis g=lista.get(r);
		switch(c) {
		case 0: return g.getId_slat();
		case 1: return g.getNaziv();
		case 2: return g.getKolicina();
		case 3: return g.getCena();
		default: return "Greska";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "Id slatkisa";
		case 1: return "Naziv";
		case 2: return "Kolicina";
		case 3: return "Cena";
		default: return "Greska";
		}
	}
	
}
