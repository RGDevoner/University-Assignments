
/**
* @author      Togantzi Maria
* @author      Kalergis Chris
*/

class Person {
	
    private String name;
    private String phone;
    private String address;
       
    Person (String n, String p, String a)  {
        name = n;
        phone = p;
        address = a;
    }
	
	void setPhone(String newPhone) {
        phone = newPhone;
    }
    
    String getName()  {
        return name;
    }
    
    String getPhone()  {
        return phone;
    }
    
    String getAddress() {
        return address;
    }
    	
    public String toString() {
        return "Name: "+getName() + "\t\tPhone Number: " + getPhone() + "\t\tAddress: " + getAddress();
    }  
	
} // Person