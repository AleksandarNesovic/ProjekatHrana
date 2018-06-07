package rs.model;

public class GlavnoJelo {
	private int id_glj;
	private String naziv;
	private int kolicina;
	private double cena;
	public GlavnoJelo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GlavnoJelo(String naziv, int kolicina, double cena) {
		super();
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cena = cena;
	}
	public int getId_glj() {
		return id_glj;
	}
	public void setId_glj(int id_glj) {
		this.id_glj = id_glj;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
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
		return naziv+" "+kolicina+"g, "+cena+"din";
	}

}
