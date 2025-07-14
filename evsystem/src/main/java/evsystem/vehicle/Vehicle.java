package evsystem.vehicle;

public class Vehicle 
{
	private int id;
	private String model;
	private String type;
	private double price;
	private int rating;
	
	//Getter methods
	public int getID()
	{
		return id;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public String getType()
	{
		return type;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	//Setter methods
	public void setID(int newID)
	{
		this.id = newID;
	}
	
	public void setModel(String newModel)
	{
		this.model = newModel;
	}
	
	public void setType(String newType)
	{
		this.type = newType;
	}
	
	public void setPrice(double newPrice)
	{
		this.price = newPrice;
	}
	
	public void setRating(int newRating)
	{
		this.rating = newRating;
	}
}
