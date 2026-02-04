/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/


import java.util.concurrent.TimeUnit;

public class clockApp {
	
	public static void main (String args[]) throws Exception{
	    Clock ob=new Clock();
	    int q,r;
		int h=16;
        int m=28;
		int s=58;
		for(int i=2;i<=180;i++){

	     h=ob.setHour(int h);
         m=ob.setMin(int m);
		 s=ob.setSec(int s);
	     q=ob.Clock.trick(int h,int m,int s);
        String r=ob.Clock.toString();
		System.out.println(Ho+":"+Mi+":"+Se);
		}
		
	}
}



