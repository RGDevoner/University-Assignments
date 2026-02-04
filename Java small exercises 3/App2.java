/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

class App2 { 

	public static void main (String args[]){	
		float A=0;
        float B=0;
        float C=0;
	 	final int pin [][] =new int[5][3];
         pin [0][0]=182;
         pin [1][0]=145;
         pin [2][0]=195;
         pin [3][0]=110;
         pin [4][0]=255;
         pin [0][1]=41;
         pin [1][1]=85;
         pin [2][1]=15;
         pin [3][1]=24;
         pin [4][1]=11;
         pin [0][2]=202;
         pin [1][2]=325;
         pin [2][2]=115;
         pin [3][2]=407;
         pin [4][2]=357;
         int all=0;  		
        System.out.println("______________________________________________________");
        System.out.println("|  Eklogiki  | Ypopsifios A|Ypopsifios B|Ypopsifios C|");
        System.out.println("|  Periferia |             |            |            |");
        System.out.println("|____________|_____________|____________|____________|"); 
        System.out.println("|      1     |    182      |     41     |   202      |");
        System.out.println("|____________|_____________|____________|____________|");
        System.out.println("|      2     |    145      |     85     |    325     |");  
        System.out.println("|____________|_____________|____________|____________|"); 
        System.out.println("|      3     |    195      |     15     |    115     |"); 
        System.out.println("|____________|_____________|____________|____________|");
        System.out.println("|      4     |    110      |     24     |    407     |");
        System.out.println("|____________|_____________|____________|____________|");
        System.out.println("|      5     |    225      |     11     |    357     |");
        System.out.println("|____________|_____________|____________|____________|");






         for (int i=0;i<=2;i++){
            int sum=0; 
            
            for(int y=0;y<=4;y++){
                int w=y+1;
               
                sum=sum+pin[y][i];
                  
                }
                if (i==0){
                    System.out.println("O ypopsifios A pire "+sum+" psifous");
                A=sum;    
                }else if(i==1){
                    System.out.println("O ypopsifios B pire "+sum+" psifous");
                B=sum;   
                }else if(i==2){
                    System.out.println("O ypopsifios C pire"+sum+" psifous");
                C=sum;
                    }
                
                all=all+sum;
            }

             
             
        
        String k= "%";
        A=(A/all*100);
         B=(B/all*100);
         C=(C/all*100);
        System.out.printf("To pososto toy ypopsifiou A einai %.2f\n",A,"%");
        System.out.printf("To pososto toy ypopsifiou B einai %.2f\n",B,k);
        System.out.printf("To pososto toy ypopsifiou C einai %.2f\n",C,k);


        if (C>50 | B>50 | A>50){
            if (A>50){
            System.out.println("Nikitis ton eklogon o A");
            }else if(B>50){
             System.out.println("Nikitis ton eklogon o B");   
            }else if(C>50){
             System.out.println("Nikitis ton eklogon o C");
            }
        }else{
            if(A>C & B>C){
                System.out.println("Tha dieksathei epanaliptikos giros metaksi tou A me "+A+"% kai ston B me"+B+"%");
            }else if(C>B & A>B){
                System.out.println("Tha dieksathei epanaliptikos giros metaksi tou A me "+A+"% kai ston C me"+C+"%");
            }else if (C>A & B>A){
                System.out.println("Tha dieksathei epanaliptikos giros metaksi tou B me "+B+"% kai ston C me"+C+"%");
            }

            }
        
    }		
        
        }
     
        


     
 
 
   		

