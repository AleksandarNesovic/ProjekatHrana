package rs.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import rs.model.GlavnoJelo;

public class TableModelGlavnoJelo extends AbstractTableModel{
	ArrayList<GlavnoJelo> lista=new ArrayList<>();

	public TableModelGlavnoJelo(ArrayList<GlavnoJelo> lista) {
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
		return 3;
	}

	@Override
	public Object getValueAt(int r, int c) {
		GlavnoJelo g=lista.get(r);
		switch(c) {
		case 0: return g.getId_glj();
		case 1: return g.getNaziv();
		case 2: return g.getCena();
		default: return "Greska";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "Id glavnog jela";
		case 1: return "Naziv";
		case 2: return "Cena";
		default: return "Greska";
		}
	}
	
}
