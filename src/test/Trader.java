package test;

import java.util.Comparator;

public class Trader {
	
	String name;
	String city;
	String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	 public static Comparator<Trader> sortNameAscending = new Comparator<Trader>() {

			public int compare(Trader t1, Trader t2) {
			   String traderName1 = t1.getName().toUpperCase();
			   String traderName2 = t2.getName().toUpperCase();

			   //ascending order
			   return traderName1.compareTo(traderName2);

		    }};
		    
		    
    public String toString(){
    	return "id : "+ this.id +" | name "+this.name + " | city : "+this.city;
    }



}
