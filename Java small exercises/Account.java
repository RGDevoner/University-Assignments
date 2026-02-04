/*
	Name: Alexandros Makrygiannis
	Student Number:3210271
*/

class Account extends bankApp {
	
	
   	private float final RATE = 0.015;
	private String name[];
   	private String acctNumber[];
   	private float balance[];
	int i=1;
   class Account (){
if (i==1){
		name[1]= acct1("Togantzi Maria");
   	acctNumber[1]=acct1("100-00");
    balance[1]=acct1(188.46);
	i=i+1;
}else if (i==2){
	name[2]= acct2("Kalergis Christos");
	acctNumber[2]=acct2("100-01");
	balance[2]=acct2(140.21);
	i=i+1;
}else if (i==3){
   name[3]= acct3("Maras Petros");
   acctNumber[3]=acct3("100-02");
   balance[3]=acct3(0.00);
}
}
	
	Account () {
		// Fill your code here
   	}
   	double deposit (double amount,String N);{
		int i;
		System.out.println("Deposit @ Account"+N);
		System.out.println("Requested:"+amount);
		if (N="100-00"){
			i=1;
		}
		else if(N="100-01"){
			i=2;
		
		}else if(N="100-02"){
			i=3;
		
		}
		if (amount=>0){
			balance[i]=balance[i]+amount;
			return balance[i];
			}else{
				System.out.println("Error:Deposit amount is invalid.");
			}
		
		System.out.println("New Balance"+balance[i]);
		}
			}

			
		
	}

	double withdraw (double amount,String N) ;{
		System.out.println("withDraw @ Account"+N)
		if (N="100-00"){
			i=1;
		}
		else if(N="100-01"){
			i=2;
		
		}else if(N="100-02"){
			i=3;
		}
		System.out.println("Balance"+balance[i]);
		System.out.println("Requested"+amount);

		if (amount=>0){
			if(balance[i]=>amount){
               balance[i]=balance[i]-amount;
			   return balance[i];
			}else{
				System.out.println("Error: Insufficient funds.");
			}


		}else{
			System.out.println("Error: Withdraw amount is invalid.");
		}
		System.out.println("New Balance"+balance[i]);
   	}

   	double addInterest (){
      	balance[1]=balance[1]+balance[1]*RATE;
		balance[2]=balance[2]+balance[2]*RATE;
        balance[3]=balance[3]+balance[3]*RATE;

   	}

   	double getBalance (){
      	return balance[1];
		return balance[2];
		return balance[3];
   	}

   	String getAccountNumber (){
		return acctNumber[1];
	    return acctNumber[2];
        return acctNumber[3];	
	}
   	public String toString(){
		return name[1];
		return name[2];
		return name[3];
		return acctNumber[1];
	    return acctNumber[2];
        return acctNumber[3];
		return balance[1];
		return balance[2];
		return balance[3];
   	}
}

