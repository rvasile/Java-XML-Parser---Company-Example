package FramesGood;

public class Validare {

	public String u1 = "admin";
	public String p1 = "admin";
	
	public String u2="user";
	public String p2="user";


	public String ValideazaUser(String user, String parola) {
		String tip;
		if(user.equals(u1) && parola.equals(p1)){
			tip="admin";
			return tip;
		}else if(user.equals(u2) && parola.equals(p2)){
			tip="user";
			return tip;
		}else{
			return "";
		}
	}

}
