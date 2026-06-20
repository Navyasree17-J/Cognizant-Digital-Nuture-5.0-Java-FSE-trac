// ==========================================
// EXERCISE 1: JAVASCRIPT BASICS & SETUP
// ==========================================

// Log a foundational diagnostic welcome message to the developer console
console.log("Welcome to the Community Portal");

// Trigger a browser-level alert dialog when the script execution engine initializes
alert("Community Portal JavaScript Engine Successfully Loaded!");


// ==========================================
// EXERCISE 2: SYNTAX, DATA TYPES, AND OPERATORS
// ==========================================

// 1. Declare event details using appropriate keywords [cite: 11, 13]
const eventName = "Annual Park Clean-up"; // String data type - permanent name 
const eventDate = "June 15, 2026";        // String data type - permanent date 
let availableSeats = 45;                  // Number data type - mutable seat counter 

// 2. Concatenate event info using modern ES6 template literals 
console.log(`--- Exercise 2: Initial Event Data Loaded ---`);
console.log(`Event Name: ${eventName}`);
console.log(`Scheduled Date: ${eventDate}`);
console.log(`Initial Seats Available: ${availableSeats}`);

// 3. Simulate a user registration tracking flow
console.log("...Simulating a new resident registering for this event...");

// Use the decrement operator (--) to manage seat counts on registration 
availableSeats--; 

// Output updated seat count using template strings to verify change 
console.log(`Registration successful! Updated Available Seats: ${availableSeats}`);


// ==========================================
// EXERCISE 3: CONDITIONALS, LOOPS, AND ERROR HANDLING
// ==========================================
console.log(`\n--- Exercise 3: Conditionals, Loops & Errors ---`);

// 1. Create a dataset list of community events to simulate a database feed
const communityEvents = [
    { id: 1, name: "Annual Park Clean-up", seats: 12, isPast: false },
    { id: 2, name: "Weekly Farmers Market", seats: 0, isPast: false },   // Full event
    { id: 3, name: "Summer Music Festival", seats: 150, isPast: false },
    { id: 4, name: "Neighborhood Safety Townhall", seats: 5, isPast: true } // Past event
];

// 2. Loop through the event list using forEach() to process displays conditionally
console.log("Checking and displaying active upcoming events:");

communityEvents.forEach((event) => {
    // Check if the event is in the past OR has no seats left
    if (event.isPast || event.seats === 0) {
        console.log(`🚫 Hiding Event: "${event.name}" (Reason: Event is past or fully booked)`);
    } else {
        console.log(`✅ Displaying Event: "${event.name}" - Seats Remaining: ${event.seats}`);
    }
});

// 3. Wrap registration logic in a try-catch block to manage runtime errors safely
function simulateRegistration(event) {
    try {
        console.log(`\nAttempting registration process for: ${event ? event.name : "Unknown Event"}...`);
        
        // Intentional error check: If no valid event object was provided, throw an explicit exception
        if (!event) {
            throw new Error("Registration failed: Missing valid event data record.");
        }
        
        if (event.seats <= 0) {
            throw new Error(`Registration failed: "${event.name}" has absolutely no seats left!`);
        }
        
        // Success path
        event.seats--;
        console.log(`🎉 Registration complete! Remaining seats: ${event.seats}`);
        
    } catch (error) {
        // Intercept the exception and print a safe user-friendly diagnostic warning
        console.error(`⚠️ Exception Intercepted: ${error.message}`);
    }
}

// 4. Run execution tests to verify both success and failure error paths
simulateRegistration(communityEvents[0]); // Success path: Has available seats
simulateRegistration(communityEvents[1]); // Failure path: Handles the "0 seats" custom error safely
simulateRegistration(null);              // Failure path: Handles the missing object parameter error safely

console.log("Execution flow continues smoothly because the errors were safely caught!");