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

// ==========================================
// EXERCISE 4: FUNCTIONS, SCOPE, CLOSURES, AND HIGHER-ORDER FUNCTIONS
// ==========================================
console.log(`\n--- Exercise 4: Advanced Functions & Closures ---`);

// 1. Core Global Array acting as our central database register [cite: 32]
const globalEventRegistry = [
    { id: 101, name: "Annual Park Clean-up", category: "Environment", seats: 30 },
    { id: 102, name: "Weekly Farmers Market", category: "Food", seats: 50 },
    { id: 103, name: "Summer Music Festival", category: "Entertainment", seats: 120 }
];

// 2. Standard Function: Adds a brand new event object to our central registry [cite: 23]
function addEvent(id, name, category, seats) {
    const newEvent = { id: id, name: name, category: category, seats: seats };
    globalEventRegistry.push(newEvent);
    console.log(`➕ Event Added Successfully: "${name}"`);
}

// 3. Closure Architecture: Tracks total registrations for a category privately 
function createRegistrationTracker(categoryName) {
    // This variable is completely private and hidden from outside modification (Encapsulation)
    let totalRegistrations = 0; 
    
    // Return an inner function that maintains lexical access to the private counter 
    return function registerUser(eventName) { // [cite: 23]
        totalRegistrations++;
        console.log(`🎟️ User registered for: "${eventName}"`);
        console.log(`[Metrics Tracker] Total registrations recorded for category "${categoryName}": ${totalRegistrations}`);
        return totalRegistrations;
    };
}

// Instantiate specific registration trackers leveraging closures 
const trackEnvironmentRegistration = createRegistrationTracker("Environment");
const trackFoodRegistration = createRegistrationTracker("Food");


// 4. Higher-Order Function: Filters events using an abstract callback routine 
// It takes a "searchCallback" function as a parameter, making it incredibly reusable 
function filterEventsByCategory(registry, searchCallback) { // [cite: 23]
    const matchedResults = [];
    
    registry.forEach((event) => {
        // Execute the custom rule passed down inside our callback argument 
        if (searchCallback(event)) {
            matchedResults.push(event);
        }
    });
    
    return matchedResults;
}


// ==========================================
// RUNNING THE EXECUTION TESTS
// ==========================================

// Test 1: Add a new event using our functional setup [cite: 23]
addEvent(104, "Healthy Cooking Workshop", "Food", 25);

// Test 2: Simulate user sign-ups and observe the closure keeping count [cite: 21, 23, 24]
console.log("\n--- Testing Closure State Retention ---");
trackEnvironmentRegistration("Annual Park Clean-up"); // Counts: 1
trackEnvironmentRegistration("Beach Waste Collection"); // Counts: 2 (Remembers previous state!)
trackFoodRegistration("Weekly Farmers Market");         // Counts: 1 (Maintains separate instance scope!)

// Test 3: Use the Higher-Order Function with dynamic callbacks 
console.log("\n--- Testing Higher-Order Function Filter ---");

// Callback Rule A: Look exclusively for items under the "Food" category 
const foodFilterRule = (event) => event.category === "Food";
const foodEvents = filterEventsByCategory(globalEventRegistry, foodFilterRule); // [cite: 23]

console.log("Filtered Results for 'Food' Category:");
console.log(foodEvents);