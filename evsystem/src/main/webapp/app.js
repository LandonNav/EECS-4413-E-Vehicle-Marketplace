function talk() {
    const know = {
        // Basic Responses
        "who are you": "Hello! I'm your virtual assistant for this Vehicle E-Commerce Website. How can I help?",
        "how are you": "I'm running smoothly, like a well-tuned engine! How about you?",
        "what can you do": "I can help you browse cars, check prices, compare models, and more. What do you need?",
        "bye": "Goodbye! Visit us again for the best car deals!",

        // Vehicle Queries
        "cars": "We sell sedans, SUVs, trucks, and EVs. What type are you looking for?",
        "electric cars": "Yes! We have EVs like the Honda Insight with 300+ mile range. Interested?",
        "cheapest car": "Our most affordable model is the 2013 Toyota Matrix, starting at $$20,910.",
        "test drive": "You can schedule a test drive online or at our dealership. Want a specific model?",
        "financing": "We offer loans with rates as low as 2.9%. Need a payment calculator?",
        "delivery": "We deliver nationwide. Share your zip code for estimates!",
        "return policy": "Our 365-day return policy covers unused vehicles. Terms apply.",
        "contact support": "Call 999-289-7351 or email v-eccommerce@gmail.com. We’re here from 8AM to 8PM GMT.",
        "discount": "Check our ‘Deals’ section for current promotions. Need help finding one?",
    };

    const userInput = document.getElementById('userBox').value
        .toLowerCase()    // Convert to lowercase
        .trim()          // Remove extra spaces
        .replace(/[^\w\s]/gi, '');  // Remove punctuation (e.g., "financing?" -> "financing")

	const chatLog = document.getElementById('chatLog');

	// Clear previous chat and display only the latest query + response
	chatLog.innerHTML = `You: ${userInput}<br>`;

    // Check for exact matches first
    if (know[userInput]) {
        document.getElementById('chatLog').innerHTML += `Bot: ${know[userInput]}<br>`;
    } 
    // Fuzzy matching (partial keywords)
    else {
        let response = "Bot: ";
        if (userInput.includes("car") || userInput.includes("vehicle")) {
            response += know["cars"];
        } 
        else if (userInput.includes("electric") || userInput.includes("ev")) {
            response += know["electric cars"];
        }
        else if (userInput.includes("financ") || userInput.includes("loan")) {
            response += know["financing"];
        }
        else if (userInput.includes("test drive")) {
            response += know["test drive"];
        }
        else if (userInput.includes("discount") || userInput.includes("deal")) {
            response += know["discount"];
        }
        else {
            response += "Sorry, I didn’t understand. Try asking about cars, pricing, or financing!";
        }
        document.getElementById('chatLog').innerHTML += response + "<br>";
    }

    document.getElementById('userBox').value = ""; // Clear input
}