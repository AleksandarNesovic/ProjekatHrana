package rs.model;

public class Salata {
	private int id_sal;
	private String naziv;
	private int porcija;
	private double cena;
	public Salata() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salata(String naziv, int porcija, double cena) {
		super();
		this.naziv = naziv;
		this.porcija = porcija;
		this.cena = cena;
	}
	public int getId_sal() {
		return id_sal;
	}
	public void setId_sal(int id_sal) {
		this.id_sal = id_sal;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getPorcija() {
		return porcija;
	}
	public void setPorcija(int porcija) {
		this.porcija = porcija;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	@Override
	public String toString() {
		return naziv ;
	}
	public String ispis() {
		return naziv+" "+porcija+"g, "+cena+"din";
	}

}
