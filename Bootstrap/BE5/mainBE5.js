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


// =========================================================================
// EXERCISE 4: FUNCTIONS, SCOPE, CLOSURES, AND HIGHER-ORDER FUNCTIONS
// =========================================================================
console.log(`\n--- Exercise 4: Functions, Closures & Higher-Order Pipelines ---`);

// Task 1: Establish an internal global array store tracking core community events
const globalEventRegistry = [
    { id: 101, name: "Annual Park Clean-up", category: "Environment", seats: 30 },
    { id: 102, name: "Weekly Farmers Market", category: "Food", seats: 50 },
    { id: 103, name: "Summer Music Festival", category: "Entertainment", seats: 120 }
];

// Pure function utility that maps directly to your registration array pushes
function addEventToRegistry(id, name, category, seats) {
    const newEventObject = { id: id, name: name, category: category, seats: seats };
    globalEventRegistry.push(newEventObject);
    console.log(`➕ Successfully appended new event data node: "${name}"`);
}

// Task 2: Create an event tracking mechanism utilizing a Lexical Closure Function
function createRegistrationTracker(categoryName) {
    // This variable is private and locked inside this function's scope block
    let totalRegistrationsLogged = 0; 
    
    // Return the inner function that acts as the closure
    return function processNewRegistration(targetEventName) {
        totalRegistrationsLogged++; // Mutates the private variable securely
        console.log(`🎟️ [Closure Track Active] Registered user for event item: "${targetEventName}"`);
        console.log(`   -> Total verified registration metrics counter for "${categoryName}": ${totalRegistrationsLogged}`);
        return totalRegistrationsLogged;
    };
}

// Generate an isolated tracker instance specifically bound to the "Environment" category variable
const trackEnvironmentRegistrations = createRegistrationTracker("Environment");

// Task 3: Design a Higher-Order Function to handle advanced array filter routines
// This function takes a database array and a separate callback function (evaluationRule)
function filterEventsCustomEngine(eventsArray, evaluationRuleCallback) {
    const matchingDataCollector = [];
    
    eventsArray.forEach((eventItem) => {
        // Execute the callback rule function passed in as an argument
        if (evaluationRuleCallback(eventItem)) {
            matchingDataCollector.push(eventItem); // Save item if callback yields true
        }
    });
    
    return matchingDataCollector;
}

// --- TESTING EXECUTION CALLS ---

// Add a fourth row item to the registry array using our base function
addEventToRegistry(104, "Healthy Cooking Workshop", "Food", 25);

// Execute closure tracking interactions to confirm state persistence
trackEnvironmentRegistrations("Annual Park Clean-up"); // Prints count: 1
trackEnvironmentRegistrations("Tree Planting Drive");   // Prints count: 2 (the state was remembered!)

// Execute the Higher-Order Function using a custom inline callback logic rule
const foodFilterRule = (eventObj) => eventObj.category === "Food"; // Our predicate rule
const filteredFoodEvents = filterEventsCustomEngine(globalEventRegistry, foodFilterRule);

console.log("Filtered Results from Higher-Order Function Execution ('Food' Category Matches Only):", filteredFoodEvents);


// =========================================================================
// EXERCISE 5: OBJECTS AND PROTOTYPES
// =========================================================================
console.log(`\n--- Exercise 5: Constructor Factories & Prototype Chains ---`);

// Task 1: Define a structural Blueprint Template Constructor Function
function CommunityPortalEvent(id, name, location, seats) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.seats = seats;
}

// Task 2: Inject a shared operation method onto the Prototype Layer structure
CommunityPortalEvent.prototype.checkAvailability = function() {
    // Returns true if seating capacity tracks above zero, otherwise returns false
    return this.seats > 0;
};

// Spawn unique instance objects utilizing the 'new' instantiation operator
const techLabExpo = new CommunityPortalEvent(201, "AI Tech Lab Expo", "Conference Hall A", 15);
const blockDanceParty = new CommunityPortalEvent(202, "Neighborhood Street Dance", "Main Loop Core", 0);

// Execute prototype methods across instance targets
console.log(`Availability Evaluation for "${techLabExpo.name}": ${techLabExpo.checkAvailability() ? "SEATS AVAILABLE ✅" : "SOLD OUT ❌"}`);
console.log(`Availability Evaluation for "${blockDanceParty.name}": ${blockDanceParty.checkAvailability() ? "SEATS AVAILABLE ✅" : "SOLD OUT ❌"}`);

// Task 3: Unpack and print structural property boundaries using Object.entries()
console.log(`\nDebugging object properties using Object.entries():`);

// Object.entries takes an object and converts it into an array of [key, value] pairs
const structuralDataEntries = Object.entries(techLabExpo);

structuralDataEntries.forEach(([propertyKey, propertyValue]) => {
    console.log(`   -> Data Field Attribute: [${propertyKey}] --- Current Property Value: [${propertyValue}]`);
});