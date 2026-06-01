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