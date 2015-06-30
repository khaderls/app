package controllers;

import models.Deposit;
import models.User;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.mvc.*;
import views.html.*;
import play.data.*;
import java.util.*;
import play.libs.*;

public class Application extends Controller {
	
	public static class Login {
		public String email;
		public String password;
		
		public String validate() {
		    if (User.authenticate(email, password) == null) {
		      return "Invalid user or password";
		    }
		    return null;
		}
	}
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result addValue() {
    	Deposit deposit = Form.form(Deposit.class).bindFromRequest().get();
    	if (deposit.value >= 1.0 && deposit.value <= 1000.0)
    	{
    		try {
        		Deposit val = (Deposit) Deposit.find.all().get(0);
                val.value = deposit.value + val.value;
                val.save();
            }
            catch (IndexOutOfBoundsException e)
        	{
            	deposit.save();
            }
        	
        	return ok(failed.render("Transaction Successful"));
    	}
    	
    	else 
    	{
    	    return ok(failed.render("Transaction Failed"));
    	}
	
    }
    
    public static Result returnDeposit()
    {
    	return redirect(routes.Application.deposit());
    }
    
    public static Result deposit()
    {
    	Deposit val;
    	try{
    		val = (Deposit) Deposit.find.all().get(0);
    	}
    	catch(IndexOutOfBoundsException e)
    	{
    		val = new Deposit();
    		val.value = 0.0;
    	}
    	return ok(deposit.render(val.value));
    }
    
    
    public static Result widthrawal()
    {
    	Deposit val;
    	try{
    		val = (Deposit) Deposit.find.all().get(0);
    	}
    	catch(IndexOutOfBoundsException e)
    	{
    		val = new Deposit();
    		val.value = 0.0;
    	}
    	return ok(widthrawal.render(val.value));
    }
    
    public static Result returnWidthrawal()
    {
    	return redirect(routes.Application.widthrawal());
    }
    
    public static Result subtractValue()
    {
    	Deposit deposit = Form.form(Deposit.class).bindFromRequest().get();
    	try {
        		Deposit val = (Deposit) Deposit.find.all().get(0);
        		if (deposit.value > val.value)
        		{
        			return ok(failedWidthrawal.render("Transaction Failed - Value is greater than the balance"));
        		}
        		else
        		{
        			 val.value = val.value - deposit.value;
                     val.save();		
        		}
         }
    	catch (IndexOutOfBoundsException e)
    	{
    		return ok(failedWidthrawal.render("Transaction Failed - No funds in the account"));
    	}
        	
        return ok(failedWidthrawal.render("Transaction Successful"));
    }
    
    public static Result login() {
    	return ok (login.render(Form.form(Login.class)));
    }
    
    public static Result authenticate() { 
    	Form<Login>	loginForm = Form.form(Login.class).bindFromRequest();
    	if (Form.form(Login.class).bindFromRequest().hasErrors())
    	{
    		return badRequest(login.render(loginForm));
    	}
    	else{
    		session().clear();
    		session("email", loginForm.get().email);
    		return redirect(routes.Application.index());
    	}
    }
    
    public static Result register() {
    	User user = new User ("khader.ruff@gmail.com", "password");
    	user.save();
    	List<User> users = new Model.Finder(String.class, User.class).all();
        return ok(Json.toJson(users));
    }
    
}
