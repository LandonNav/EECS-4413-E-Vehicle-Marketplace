package evsystem.loan;
import java.lang.Math;

public class LoanCalculator {
	
    public static String validateInput(double interestRate, int numMonths, double monthlyPayment) { 

        if (interestRate < 0 || interestRate > 100) return "Interest rate must be between 0 and 100."; 

        if (numMonths <= 0) return "Number of months must be a positive number."; 

        if (monthlyPayment <= 0) return "Loan amount must be a positive number.";  
 
        return null; 

    } 
	
	public static double calculateLoan(double interestRate, int numMonths, double monthlyPayment){
		
        String validationError = validateInput(interestRate, numMonths, monthlyPayment); 

        if (validationError != null) { 

            throw new IllegalArgumentException(validationError); 

        } 
		
        double r = interestRate / 100 / 12;
        
		double loanAmount = (monthlyPayment * r * Math.pow(1 + r, numMonths)) / (Math.pow(1 + r, numMonths) - 1);
		
		return loanAmount;
	}
}
