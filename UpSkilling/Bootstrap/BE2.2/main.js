// ==========================================
// EXERCISE 1: REMAINING INITIALIZATION LOGS
// ==========================================
console.log("Welcome to the Community Portal");

// ==========================================
// EXERCISE 2: SYNTAX, DATA TYPES, AND OPERATORS
// ==========================================

// Task 1: Declare immutable strings for values that won't change using 'const'
const eventName = "Annual Park Clean-up";
const eventDate = "June 15, 2026";

// Task 2: Declare a mutable number for values that will change using 'let'
let availableSeats = 45;

// Task 3: Use modern Template Literals (backticks) to log out data cleanly without old string addition loops (+)
console.log(`\n--- Exercise 2: Initial Event Data Loaded ---`);
console.log(`Event Name: ${eventName}`);
console.log(`Scheduled Date: ${eventDate}`);
console.log(`Initial Seats Available: ${availableSeats}`);

// Task 4: Simulate a user registration activity using the arithmetic decrement operator (--)
// This modifies the numerical value directly inside memory, changing 45 to 44
availableSeats--;

console.log(`[System Alert] Registration successful for ${eventName}!`);
console.log(`Updated Available Seats Remaining: ${availableSeats}`);