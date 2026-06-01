// ==========================================
// EXERCISE 1: JAVASCRIPT BASICS & SETUP
// ==========================================
console.log("Welcome to the Community Portal"); // [cite: 8]
// alert("Community Portal JavaScript Engine Successfully Loaded!"); // [cite: 9] (Commented out for smooth automated testing)

// ==========================================
// EXERCISE 2: SYNTAX, DATA TYPES, AND OPERATORS
// ==========================================
const eventName = "Annual Park Clean-up"; // [cite: 13]
const eventDate = "June 15, 2026";        // [cite: 13]
let availableSeats = 45;                  // [cite: 13]

console.log(`--- Exercise 2: Initial Event Data Loaded ---`);
console.log(`Event Name: ${eventName}`); // [cite: 13]
console.log(`Scheduled Date: ${eventDate}`); // [cite: 13]
console.log(`Initial Seats Available: ${availableSeats}`); // [cite: 13]

availableSeats--; // [cite: 13]
console.log(`Registration successful! Updated Available Seats: ${availableSeats}`); // [cite: 13]

// ==========================================
// EXERCISE 3: CONDITIONALS, LOOPS, AND ERROR HANDLING
// ==========================================
console.log(`\n--- Exercise 3: Conditionals, Loops & Errors ---`);

const communityEvents = [
    { id: 1, name: "Annual Park Clean-up", seats: 12, isPast: false },
    { id: 2, name: "Weekly Farmers Market", seats: 0, isPast: false },
    { id: 3, name: "Summer Music Festival", seats: 150, isPast: false },
    { id: 4, name: "Neighborhood Safety Townhall", seats: 5, isPast: true }
];

console.log("Checking and displaying active upcoming events:");
communityEvents.forEach((event) => { // [cite: 19]
    if (event.isPast || event.seats === 0) { // [cite: 18]
        console.log(`🚫 Hiding Event: "${event.name}" (Reason: Event is past or fully booked)`); // [cite: 18]
    } else {
        console.log(`✅ Displaying Event: "${event.name}" - Seats Remaining: ${event.seats}`); // [cite: 18]
    }
});

function simulateRegistration(event) {
    try { // [cite: 20]
        console.log(`\nAttempting registration process for: ${event ? event.name : "Unknown Event"}...`);
        if (!event) {
            throw new Error("Registration failed: Missing valid event data record."); // [cite: 20]
        }
        if (event.seats <= 0) {
            throw new Error(`Registration failed: "${event.name}" has absolutely no seats left!`); // [cite: 20]
        }
        event.seats--;
        console.log(`🎉 Registration complete! Remaining seats: ${event.seats}`);
    } catch (error) { // [cite: 20]
        console.error(`⚠️ Exception Intercepted: ${error.message}`); // [cite: 20]
    }
}
simulateRegistration(communityEvents[0]);
simulateRegistration(communityEvents[1]);

// ==========================================
// EXERCISE 4: FUNCTIONS, SCOPE, CLOSURES, AND HIGHER-ORDER FUNCTIONS
// ==========================================
console.log(`\n--- Exercise 4: Advanced Functions & Closures ---`);

const globalEventRegistry = [
    { id: 101, name: "Annual Park Clean-up", category: "Environment", seats: 30 },
    { id: 102, name: "Weekly Farmers Market", category: "Food", seats: 50 },
    { id: 103, name: "Summer Music Festival", category: "Entertainment", seats: 120 }
];

function addEvent(id, name, category, seats) { // [cite: 23]
    const newEvent = { id: id, name: name, category: category, seats: seats };
    globalEventRegistry.push(newEvent);
    console.log(`➕ Event Added Successfully: "${name}"`);
}

function createRegistrationTracker(categoryName) {
    let totalRegistrations = 0; // [cite: 24]
    return function registerUser(eventName) { // [cite: 23, 24]
        totalRegistrations++; // [cite: 24]
        console.log(`🎟️ User registered for: "${eventName}"`);
        console.log(`[Metrics Tracker] Total registrations recorded for category "${categoryName}": ${totalRegistrations}`); // [cite: 24]
        return totalRegistrations;
    };
}
const trackEnvironmentRegistration = createRegistrationTracker("Environment");

function filterEventsByCategory(registry, searchCallback) { // [cite: 23, 24]
    const matchedResults = [];
    registry.forEach((event) => {
        if (searchCallback(event)) { // [cite: 24]
            matchedResults.push(event);
        }
    });
    return matchedResults;
}

addEvent(104, "Healthy Cooking Workshop", "Food", 25);
trackEnvironmentRegistration("Annual Park Clean-up");

const foodFilterRule = (event) => event.category === "Food";
const foodEvents = filterEventsByCategory(globalEventRegistry, foodFilterRule);
console.log("Filtered Results for 'Food' Category:", foodEvents);

// ==========================================
// EXERCISE 5: OBJECTS AND PROTOTYPES
// ==========================================
console.log(`\n--- Exercise 5: Objects & Prototype Chains ---`);

// 1. Define an Event constructor function to serve as an object blueprint
function CommunityPortalEvent(id, name, location, seats) { // [cite: 28]
    this.id = id;
    this.name = name;
    this.location = location;
    this.seats = seats;
} // [cite: 28]

// 2. Attach a shared performance method directly to the blueprint prototype pipeline
CommunityPortalEvent.prototype.checkAvailability = function() { // 
    return this.seats > 0;
}; // 

// 3. Create real object instances using the 'new' execution keyword
const techWorkshop = new CommunityPortalEvent(201, "AI Tech Lab Expo", "Hall A", 15);
const blockParty = new CommunityPortalEvent(202, "Neighborhood Street Dance", "Main Street Loop", 0);

// 4. Test prototype method execution tracking across our object instances
console.log(`Is "${techWorkshop.name}" accepting registrations? ${techWorkshop.checkAvailability() ? "Yes ✅" : "No ❌"}`);
console.log(`Is "${blockParty.name}" accepting registrations? ${blockParty.checkAvailability() ? "Yes ✅" : "No ❌"}`);

// 5. Inspect object structures dynamically using Object.entries()
console.log(`\nListing property map structures for techWorkshop instance:`);
const structuralEntries = Object.entries(techWorkshop); // 

structuralEntries.forEach(([key, value]) => {
    console.log(`Key Name: [${key}] ---> Value Assigned: [${value}]`);
}); //