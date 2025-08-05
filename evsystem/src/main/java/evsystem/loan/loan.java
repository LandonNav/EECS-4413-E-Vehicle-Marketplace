package evsystem.loan;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class loan
 */
@WebServlet("/loan")
public class loan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String defaultinterestRate = getServletContext().getInitParameter("defaultinterestRate");
		double interestRate = Double.parseDouble(defaultinterestRate);
		
		String defaultnumMonths = getServletContext().getInitParameter("defaultnumMonths");
		int numMonths = Integer.parseInt(defaultnumMonths);
		
		String defaultmonthlyPayment = getServletContext().getInitParameter("defaultmonthlyPayment");
		double monthlyPayment = Double.parseDouble(defaultmonthlyPayment);
		
		if (request.getParameter("interestRate") != null) {
			interestRate = Double.parseDouble(request.getParameter("interestRate"));
		}
		if (request.getParameter("numMonths") != null) {
			numMonths = Integer.parseInt(request.getParameter("numMonths"));
		} 
		if (request.getParameter("monthlyPayment") != null) {
			monthlyPayment = Double.parseDouble(request.getParameter("monthlyPayment"));
		}
		
		double l = LoanCalculator.calculateLoan(interestRate, numMonths, monthlyPayment);
		l = Math.round(l * Math.pow(10, 2)) / Math.pow(10, 2);
		
        request.setAttribute("interestRate", interestRate); 
        request.setAttribute("numMonths", numMonths); 
        request.setAttribute("monthlyPayment", monthlyPayment); 
        request.setAttribute("loanAmount", l); 

        // Forward the request to the JSP file

        RequestDispatcher dispatcher = request.getRequestDispatcher("/LoanDisplay.jsp"); 
        dispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
