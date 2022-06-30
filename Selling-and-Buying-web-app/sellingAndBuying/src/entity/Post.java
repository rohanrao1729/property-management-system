package entity;

import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

public class Post {
	@Override
	public String toString() {
		return "Post [owner_id=" + owner_id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", sell_or_rent=" + sell_or_rent + ", pin=" + pin + ", place=" + place + ", state=" + state
				+ ", mark_as_done=" + mark_as_done + ", files=" + files + ", ids=" + ids + ", id=" + id + ", type="
				+ type + "]";
	}
	private String owner_id;
	private String title;
	private String description;
	private int price;
	private String sell_or_rent;
	private int pin;
	private String place;
	private String state;
	private int mark_as_done;
	private ArrayList<FileItem> files;
	private ArrayList<Integer> ids;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Integer> getIds() {
		return ids;
	}
	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
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

	public String getSell_or_rent() {
		return sell_or_rent;
	}
	public void setSell_or_rent(String sell_or_rent) {
		this.sell_or_rent = sell_or_rent;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMark_as_done() {
		return mark_as_done;
	}
	public void setMark_as_done(int mark_as_done) {
		this.mark_as_done = mark_as_done;
	}
	public ArrayList<FileItem> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<FileItem> files) {
		this.files = files;
	}
	
	
	
	
}
