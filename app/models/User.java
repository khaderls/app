package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class User extends Model{
	
	@Id
	public String email;
	public String password;
	
	public User (String email, String password){
		this.email = email;
		this.password = password;
	}
	public static Finder<String,User> find = new Finder<String,User>(
	        String.class, User.class
	    ); 
	
	public static User authenticate(String email, String password) {
           return find.where().eq("email", email).eq("password", password).findUnique();
    }

}
