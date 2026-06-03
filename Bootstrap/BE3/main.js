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

// ==========================================
// EXERCISE 3: CONDITIONALS, LOOPS, AND ERROR HANDLING
// ==========================================
console.log(`\n--- Exercise 3: Conditionals, Loops & Exception Handling ---`);

// Task 1: Create a structured array database of community event objects
const communityEvents = [
    { id: 1, name: "Annual Park Clean-up", seats: 12, isPast: false },
    { id: 2, name: "Weekly Farmers Market", seats: 0, isPast: false },
    { id: 3, name: "Summer Music Festival", seats: 150, isPast: false },
    { id: 4, name: "Neighborhood Safety Townhall", seats: 5, isPast: true }
];

console.log("Checking structural availability parameters for active upcoming events:");

// Task 2: Use an array .forEach loop to evaluate layout criteria via conditional operators
communityEvents.forEach((event) => {
    // Conditional logic: Check if an event belongs to the past OR has no seats left
    if (event.isPast || event.seats === 0) {
        console.log(`   🚫 Hiding Card: "${event.name}" (Reason: Event date has passed or seats are fully booked)`);
    } else {
        console.log(`   ✅ Displaying Card: "${event.name}" - Available Seating Slots: ${event.seats}`);
    }
});

// Task 3: Implement an isolated registration simulator using explicit Try...Catch error tracking blocks
function simulateRegistration(event) {
    try {
        console.log(`\nAttempting runtime portal registration for: ${event ? event.name : "Unknown Item"}...`);
        
        // Error Check 1: Verify data is not empty or corrupted
        if (!event) {
            throw new Error("Registration aborted: Missing valid event data reference.");
        }
        // Error Check 2: Verify seating spaces exist
        if (event.seats <= 0) {
            throw new Error(`Registration rejected: "${event.name}" has absolutely no seats left!`);
        }
        
        // Mutate numbers if checks pass successfully
        event.seats--;
        console.log(`   🎉 Registration successful! Updated remaining seats tracking metric: ${event.seats}`);
    } catch (error) {
        // Handle failures gracefully without letting the script crash the browser
        console.error(`   ⚠️ Exception Caught: ${error.message}`);
    }
}

// Execute tests to verify try-catch handlers execute cleanly
simulateRegistration(communityEvents[0]); // Valid event: prints success
simulateRegistration(communityEvents[1]); // Sold out event: triggers custom seat capacity error