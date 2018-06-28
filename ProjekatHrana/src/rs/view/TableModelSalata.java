package rs.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import rs.model.GlavnoJelo;
import rs.model.Salata;
import rs.model.Slatkis;

public class TableModelSalata extends AbstractTableModel{
	ArrayList<Salata> lista=new ArrayList<>();

	public TableModelSalata(ArrayList<Salata> lista) {
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
		Salata g=lista.get(r);
		switch(c) {
		case 0: return g.getId_sal();
		case 1: return g.getNaziv();
		case 2: return g.getCena();
		default: return "Greska";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "Id salate";
		case 1: return "Naziv";
		case 2: return "Cena";
		default: return "Greska";
		}
	}
	
}
