/*
	Name:Alexandros Makrygiannis
	Student Number:p3210271
*/

class StudentList {
	
	private Student [] myList =new Student() ;
	
	private int length = 0;
	
		
	void InsertStudent(Student newStudent){
	 
    myList[length]=newStudent(String student,String rn,int grade);	
		// Fill your code here
	length++;

	}//InsertStudent
	
	
	void LookupStudent(String RN) {
	boolean f=false;
		for(int y=0,y<myList.length,y++){
            if(myList[y].getrn==RN){
            System.out.println(myList[y].getGrade);
            f=true;
        }
        }
        if(f){
            System.out.println("Student not found");
                }
        // Fill your code here
		
	}//LookupStudent
	
	
	void DisplayList() {
		
		for(int i=0,i==length,i++){
        System.out.println(myList[i]);
        }
        
        
        // Fill your code here
			
	}//DisplayList
	
}//StudentList	