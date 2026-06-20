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

// =========================================================================
// EXERCISE 6: ARRAY METHODS (MAP AND FILTER)
// =========================================================================
console.log(`\n--- Exercise 6: Functional Array Methods Engine ---`);

// Task 1: Establish our baseline events array collection database
const masterEventDatabase = [
    { id: 301, title: "City Tree Planting", group: "Environment", capacity: 40 },
    { id: 302, title: "Neighborhood Soup Kitchen", group: "Charity", capacity: 15 },
    { id: 303, title: "Youth Coding Bootcamp", group: "Education", capacity: 0 },
    { id: 304, title: "Beach Waste Reclamation", group: "Environment", capacity: 22 }
];

console.log("Original Master Event Database:", masterEventDatabase);

// Task 2: Use .filter() to isolate upcoming "Environment" events
// The rule keeps the item only if its group matches "Environment"
const environmentalEventsOnly = masterEventDatabase.filter((eventItem) => {
    return eventItem.group === "Environment";
});

console.log("\nFiltered Array Results (.filter() for 'Environment' group matches only):", environmentalEventsOnly);

// Task 3: Use .map() to extract a clean list of event titles
// It transforms each full event object into a simple string containing just the title
const eventTitlesRegistryList = masterEventDatabase.map((eventItem) => {
    return eventItem.title;
});

console.log("\nMapped Array Results (.map() isolating event titles into strings):", eventTitlesRegistryList);

// Verify that our functional loops left the source arrays completely untouched
console.log("\nVerification Check: Original Database structure remains unmutated:", masterEventDatabase);

// =========================================================================
// EXERCISE 7: DOM SELECTORS AND FORM INTERACTION CAPTURE
// =========================================================================
console.log(`--- Exercise 7: DOM Node Selection & Interaction Hooks Active ---`);

// Task 1: Capture structural HTML elements using explicit DOM Selectors
const registrationFormElement = document.getElementById("portalRegistrationForm");
const inputNameField = document.querySelector("#inputUsername");
const inputEmailField = document.querySelector("#inputEmail");

// Verify that the JavaScript engine captured the layout blocks correctly
console.log("Form Container Element Verified:", registrationFormElement);
console.log("Input Fields Hooked:", { nameField: inputNameField, emailField: inputEmailField });

// Task 2: Attach an asynchronous form submission listening hook
registrationFormElement.addEventListener("submit", (event) => {
    
    // Task 3: Block the standard default browser reload action routine
    event.preventDefault();
    
    console.log("\n⚡ Form Submission Event Captured Successfully!");
    
    // Extract input strings directly from the input targets inside memory
    const capturedNameString = inputNameField.value;
    const capturedEmailString = inputEmailField.value;
    
    // Output captured data parameters to the development console
    console.log(`   📝 Captured User Profile Name: ${capturedNameString}`);
    console.log(`   📧 Captured User Contact Email: ${capturedEmailString}`);
    
    // Simulate user validation confirmation alert menu
    alert(`Success!\nThank you, ${capturedNameString}.\nYour portal registration verification has been logged for: ${capturedEmailString}`);
    
    // Optional: Reset the input form clean after complete tracking loop processing
    registrationFormElement.reset();
    console.log("🔄 Form field values flushed back to baseline states cleanly.");
});


// =========================================================================
// EXERCISE 8: ADVANCED DOM STYLING, INPUTS, AND LIST INJECTIONS
// =========================================================================
console.log(`--- Exercise 8: Dynamic Form Engine & DOM Mutations Online ---`);

// 1. Select the core interactive tracking elements
const registrationWrapperCard = document.getElementById("registrationContainer");
const highlightTriggerButton = document.getElementById("btnToggleHighlight");
const loggingListWrapper = document.getElementById("logOutputList");

// Select inputs from Exercise 8.2 layout for real-time tracking
const floatingEmailInput = document.getElementById("floatingEmail");
const floatingPasswordInput = document.getElementById("floatingPassword");

// 2. Task 1: Use .classList.toggle() to dynamically toggle a focus card highlight style
highlightTriggerButton.addEventListener("click", () => {
    // Toggles our custom high-visibility blue border accent state on and off
    registrationWrapperCard.classList.toggle("interactive-highlight");
    
    // Log action cleanly down into our user interface system list
    appendSessionLogItem("Executed highlight class toggle mutation on registration form container.");
});

// 3. Task 2: Listen to focus changes on form text elements using .classList.add / .remove
// When user clicks into the email input, add visual emphasis onto the host wrapper card
floatingEmailInput.addEventListener("focus", () => {
    registrationWrapperCard.classList.add("border-primary");
    appendSessionLogItem("Email field gained active cursor focus. Base border tint altered.");
});

// When user clicks away from the email input, remove that emphasis style
floatingEmailInput.addEventListener("blur", () => {
    registrationWrapperCard.classList.remove("border-primary");
    appendSessionLogItem("Email field lost active cursor focus.");
});


// 4. Task 3: Helper utility function that dynamically creates and appends list items to the DOM
function appendSessionLogItem(logMessageText) {
    // Check if the initial default placeholder text exists, clear it out if it does
    if (loggingListWrapper.innerText.includes("Waiting for user actions...")) {
        loggingListWrapper.innerHTML = "";
    }

    // Programmatically instantiate a brand new <li> layout tag inside memory
    const newLogListItem = document.createElement("li");
    
    // Assign standard Bootstrap list layout items and text strings
    newLogListItem.classList.add("list-group-item", "px-0", "bg-transparent", "text-success-emphasis", "border-0");
    
    // Get the current local timestamp format
    const currentTimeStamp = new Date().toLocaleTimeString();
    newLogListItem.innerText = `⏱️ [${currentTimeStamp}] ${logMessageText}`;
    
    // Inject the fully built node into your view page layout
    loggingListWrapper.appendChild(newLogListItem);
    console.log(`DOM Append Log: ${logMessageText}`);
}