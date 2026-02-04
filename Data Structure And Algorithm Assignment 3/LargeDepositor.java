public class LargeDepositor {
    private int AFM; // AFM
    private String firstName; // Onoma
    private String lastName; // Eponimo
    private double savings; // Savings depositos
    private double taxedIncome; // Sinoliko eisodima poy exei dilothei tin teleutaia triaitia 

    // Constructor
    public LargeDepositor(int AFM, String firstName, String lastName, double savings,double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome=taxedIncome;
    }

    
    public int getAFM() {
        return AFM;
    }

    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

  
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   
    public double getSavings() {
        return this.savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }

  
    public int key() {
        return this.AFM;
    }
   
    public String toString(){
       
        return this.AFM + " " + this.firstName + " " + this.lastName + " " + this.savings + " " + this.taxedIncome;
    }

     
    public double SUS() {
        if(taxedIncome<8000){
        return -1;//an -1 tote forodiafevgei 100% kai mpainei aftomata stin lista
        }else{
        return savings - taxedIncome;
        }
    }
    public double compareTo(LargeDepositor Depositor2) {
        double SUS1 = SUS();
        double SUS2 = Depositor2.SUS();
            return Double.compare(SUS1, SUS2);
        
       
        
    }
}