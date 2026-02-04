/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

class Clock {
	
	//Data
	
	protected int hour;
	protected int minute;
	protected int second;
	
	// Methods
	
	void setHour(int h) {
		
		 this.hour=h;
		
	}

	void setMin(int m) {
		this.minute=m;
		
	}
	
	void setSec(int s) {
		
		this.second=s;
	
	}

	void tick() {
		if(this.second<59){
			this.second=this.second+1;
		}else if(this.second==59)
		{
          if(this.minute<59){
			  this.minute=this.minute+1;
			  this.second=0;
		  }
		  else if (this.minute==59)
		  {
            if (this.hour<23){
				this.hour=this.hour+1;
				this.minute=this.minute+1;
				this.second=this.second+1;
			}else if(this.hour==23){
				this.hour=0;
				this.minute=0;
				this.second=0;
			}

		  }

		}
		
		
	}

	public String toString(){
	boolean yes=true;
	String pp=" ";
	if(yes==true){
		
		
		   
		
	pp="pm";
	
	
	  
}
	else if(yes==false){
	 pp="am";	
	 
		
	}	
	String hour= String.format("%02d",this.hour);
	String minute=String.format("%02d",this.minute);
	String second=String.format("%02d",this.second); 
	
	
	   return hour+":"+minute+":"+second+pp;
	 
	}
} 
	



