
public class EntryPair 
{
		  public String value;
		  public int priority;

		  public EntryPair(String aValue, int aPriority) { 
		    // variables set when object is created
		    value=aValue;
		    priority=aPriority;
		  }
		  public EntryPair() {
		    // default constructor, must set variables 
		    // after creating object using setters or directly
		  }  

		  public String getValue() { return value; }
		  public int getPriority() { return priority; }
		  public void setValue(String aValue) { value=aValue; }
		  public void setPriority(int aPriority) { priority=aPriority; }
		
}
