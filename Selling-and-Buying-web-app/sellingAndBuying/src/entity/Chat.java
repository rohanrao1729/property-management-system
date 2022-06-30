package entity;

public class Chat {
	private String owner_id ;
	private String title;
	private String mail_id;
	private String message;
	private int own_or_cli;
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
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	@Override
	public String toString() {
		return "Chat [owner_id=" + owner_id + ", title=" + title + ", mail_id=" + mail_id + ", message=" + message
				+ ", own_or_cli=" + own_or_cli + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getOwn_or_cli() {
		return own_or_cli;
	}
	public void setOwn_or_cli(int own_or_cli) {
		this.own_or_cli = own_or_cli;
	}

}
