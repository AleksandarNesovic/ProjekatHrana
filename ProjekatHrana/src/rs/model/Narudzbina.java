package rs.model;

import java.sql.Date;

public class Narudzbina {
	private Klijent klijent;
	private GlavnoJelo glavnoJelo;
	private Salata salata;
	private Slatkis slatkis;
	private int kolicinaGlavnogJele;
	private int kolicinaSalate;
	private String email;
	private java.sql.Date datumPorudzbine;
	
	public Narudzbina() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Date getDatumPorudzbine() {
		return datumPorudzbine;
	}



	public void setDatumPorudzbine(Date datumPorudzbine) {
		this.datumPorudzbine = datumPorudzbine;
	}
	

	public Klijent getKlijent() {
		return klijent;
	}



	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}



	public Narudzbina(Klijent klijent, GlavnoJelo glavnoJelo, Salata salata, Slatkis slatkis, int kolicinaGlavnogJele,
			int kolicinaSalate, String email, Date datumPorudzbine) {
		super();
		this.klijent = klijent;
		this.glavnoJelo = glavnoJelo;
		this.salata = salata;
		this.slatkis = slatkis;
		this.kolicinaGlavnogJele = kolicinaGlavnogJele;
		this.kolicinaSalate = kolicinaSalate;
		this.email = email;
		this.datumPorudzbine = datumPorudzbine;
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
				  + "g\nSlanje na mail adresu: " + email+"\n Datum porudzbine: "+datumPorudzbine;
	}
	
	
	

}
