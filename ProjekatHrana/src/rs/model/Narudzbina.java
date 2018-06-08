package rs.model;

public class Narudzbina {
	private GlavnoJelo glavnoJelo;
	private Salata salata;
	private Slatkis slatkis;
	private int kolicinaGlavnogJele;
	private int kolicinaSalate;
	private String email;
	
	public Narudzbina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Narudzbina(GlavnoJelo glavnoJelo, Salata salata, Slatkis slatkis, int kolicinaGlavnogJele,
			int kolicinaSalate, String email) {
		super();
		this.glavnoJelo = glavnoJelo;
		this.salata = salata;
		this.slatkis = slatkis;
		this.kolicinaGlavnogJele = kolicinaGlavnogJele;
		this.kolicinaSalate = kolicinaSalate;
		this.email = email;
	}

	public GlavnoJelo getGlavnoJelo() {
		return glavnoJelo;
	}

	public void setGlavnoJelo(GlavnoJelo glavnoJelo) {
		this.glavnoJelo = glavnoJelo;
	}

	public Salata getSalata() {
		return salata;
	}

	public void setSalata(Salata salata) {
		this.salata = salata;
	}

	public Slatkis getSlatkis() {
		return slatkis;
	}

	public void setSlatkis(Slatkis slatkis) {
		this.slatkis = slatkis;
	}

	public int getKolicinaGlavnogJele() {
		return kolicinaGlavnogJele;
	}

	public void setKolicinaGlavnogJele(int kolicinaGlavnogJele) {
		this.kolicinaGlavnogJele = kolicinaGlavnogJele;
	}

	public int getKolicinaSalate() {
		return kolicinaSalate;
	}

	public void setKolicinaSalate(int kolicinaSalate) {
		this.kolicinaSalate = kolicinaSalate;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return glavnoJelo + " " + kolicinaGlavnogJele +"g\n"+ salata + " "+ kolicinaSalate+"g\n" + slatkis+" "+slatkis.getKolicina()
				  + "g\nSlanje na mail adresu: " + email;
	}
	
	
	

}
