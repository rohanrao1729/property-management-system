package entity;

public class DisplayPost {
	private String title;
	private String description;
	private int price;
	private int id;
	private String mail;
	private String sell_or_rent;
	private String type;
	public String getSell_or_rent() {
		return sell_or_rent;
	}
	public void setSell_or_rent(String sell_or_rent) {
		this.sell_or_rent = sell_or_rent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
