package rs.model;

public class Klijent {
	private int id_klijenta;
	private String ime;
	private String prezime;
	private String brojTelefona;
	private String email;
	
	public int getId_klijenta() {
		return id_klijenta;
	}
	public void setId_klijenta(int id_klijenta) {
		this.id_klijenta = id_klijenta;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Klijent(String ime, String prezime, String brojTelefona, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
	}
	public Klijent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Klijent [id_klijenta=" + id_klijenta + ", ime=" + ime + ", prezime=" + prezime + ", brojTelefona="
				+ brojTelefona + ", email=" + email + "]";
	}
	

}
