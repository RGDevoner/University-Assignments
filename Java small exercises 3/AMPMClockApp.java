/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

import java.util.concurrent.TimeUnit;

class AMPMClockApp {
	
	public static void main (String args[]) throws Exception{
		
		Clock clock=new Clock();
        int h=16;
        
        clock.setHour(16);
		clock.setMin(28);
        clock.setSec(58);		
		
		
		// Fill your code here
		int time = 0;

        while (time <= 181) {
            System.out.println(clock);
            if(h>12){
                clock.setHour(04);
                }
            TimeUnit.SECONDS.sleep(1);
            
            clock.tick();
            time++;
        }
	}
}



