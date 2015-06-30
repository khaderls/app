package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import play.db.ebean.Model;

@Entity
public class Deposit extends Model{
	
	@Id
	public int somekey;
	

	public double value;
	
	public static Finder find = new Finder (Double.class, Deposit.class);
	

}
