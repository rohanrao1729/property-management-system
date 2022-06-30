package entity;

public class Payment {
	private String owner;
	private String title;
	private String buyer;
	private int pon;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getPon() {
		return pon;
	}
	public void setPon(int pon) {
		this.pon = pon;
	}
	
}
