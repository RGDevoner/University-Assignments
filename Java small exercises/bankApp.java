/*
	Name: Alexandros Makrygiannis 
	Student Number:3210271
*/


public class bankApp  {

	public static void main(String args[]) {

    	String P1="Togantzi Maria";
		String AN1="100-00";
		Double BAL1=188.46;
		String P2="Kalergis Christos";
		String AN2="100-01";
		Double BAL2=140.21;
		String P3="Maras Petros";
		String AN3="100-02";
		Double BAL3=0.00;
		Account acct1 = new Account ("Togantzi Maria","100-00",188.46);
    	Account acct2 = new Account ("Kalergis Christos","100-01",140.21);
    	Account acct3 = new Account ("Maras Petros","100-02",0.00);
		String N;
        int amount;
		System.out.println ("New accounts","100-00",188.46);
		System.out.println ("Account Number:"+P1+"\nName:"+AN1+"\nBalance:"+BAL1);
		System.out.println ("Account Number:"+P2+"\nName:"+AN2+"\nBalance:"+BAL2);
		System.out.println ("Account Number:"+P2+"\nName:"+AN3+"\nBalance:"+BAL3);
		
			
		double deposit(amount=-10.00,N="100-00");
		double deposit(amount=500.1,N="100-01");
        double withdraw(amount=1420.75,N="100-02");
		double withdraw(amount=-10.00,N="100-02");
		double withdraw(amount=420.75,N="100-02");
		double getBalance();
        
		System.out.println ("\nadd interest ... ");
		double addInterest();
		BAL1=balance[1];
		BAL2=balance[2];
		BAL3=balance[3];


		   System.out.println ("Account Number:"+P1+"\nName:"+AN1+"\nBalance:"+BAL1);
		   System.out.println ("Account Number:"+P2+"\nName:"+AN2+"\nBalance:"+BAL2);
		   System.out.println ("Account Number:"+P2+"\nName:"+AN3+"\nBalance:"+BAL3);

   }

}

