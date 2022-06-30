package dao;

import java.util.ArrayList;

import entity.Payment;
import entity.User;

public interface UserDao {
	boolean register(User u);
	boolean login(String mail_id,String password);
	User get(String mail);
	ArrayList<Payment> get_payments(String owner);
}
