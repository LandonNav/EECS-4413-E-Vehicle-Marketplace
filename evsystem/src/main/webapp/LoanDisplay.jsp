<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Loan Calculator</title>
</head>

<body>
   	<header> 
        <h1>Loan Calculator</h1> 
    </header> 
	<main> 
	<div class="calculator-results">
		<fieldset> 
			<legend>Loan Results</legend> 
			<p>You entered:</p> 
		    <ul> 
		        <li>Your Interest Rate: <%= request.getAttribute("interestRate") %></li> 
		        <li>Number of Months: <%= request.getAttribute("numMonths") %></li> 
		        <li>Loan Amount: <%= request.getAttribute("monthlyPayment") %></li> 
		    </ul> 
    		<p>Your monthly payment is calculated as:</p> 
    		<p>(Loan Amount * Interest Rate * (1 + Interest Rate)^Number of Months / (1 + Interest Rate)^Number of Months - 1)</p> 
   			<p>The total loan amount is: $<%= request.getAttribute("loanAmount") %></p> 
		</fieldset> 
	</div>
    </main> 
</body>
</html>