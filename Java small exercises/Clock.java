/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

class Clock {
	
	//Data
	
	private int hour;
	private int minute;
	private int second;
	
	
	// Methods
	
	void setHour(int h) {
		
		hour=h;
	}

	void setMin(int m) {
		minute=m;
	}
	
	void setSec(int s) {
		
		second=s;
	}

	void tick() {
		if(second<59){
			second=second+01;
		}else(second=59){
          if(minute<59){
			  minute=minute+01;
			  second=00;
		  }else(minutes=59){
            if (hour<23){
				hour=hour+01;
				minute=minute+01;
				second=second+01;
			}else{
				hour=00;
				minute=00;
				second=00;
			}

		  }

		}
		h=hour;
		m=minute;
		s=second;
		return h;
		return m;
		return s;
	}

	public String toString() {
		String Ho=String.valueOf(hour);
		String Mi=String.valueOf(minutes);
		String Se=String.valueOf(second);
		return Ho;
		return Mi;
		return Se;
	} 
	
}
}

