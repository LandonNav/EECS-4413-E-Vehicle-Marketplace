package evsystem.user;

public class User 
{
	private int id;
	private String username;
	private String password;
	private String type;
	
	//Getter methods
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getType()
	{
		return type;
	}
	
	//Setter methods
	public void setID(int newID)
	{
		this.id = newID;
	}
	
	public void setName(String newName)
	{
		this.username = newName;
	}
	
	public void setPassword(String newPassword)
	{
		this.password = newPassword;
	}
	
	public void setType(String newType)
	{
		this.type = newType;
	}
	
}
