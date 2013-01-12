package application_admin;

public interface AdminRfidInterface {
	
	public void setCurrentCard(String a);
	
	public String getCurrentCard(long id);
	
	public void createIdCard(long id);
	
	public void createIdCard(String b, long i);
}
