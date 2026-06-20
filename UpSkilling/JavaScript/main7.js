// ==========================================
// EXERCISE 1: JAVASCRIPT BASICS & SETUP
// ==========================================
console.log("Welcome to the Community Portal");

// ==========================================
// EXERCISE 2: SYNTAX, DATA TYPES, AND OPERATORS
// ==========================================
const eventName = "Annual Park Clean-up";
const eventDate = "June 15, 2026";
let availableSeats = 45;

console.log(`--- Exercise 2: Initial Event Data Loaded ---`);
console.log(`Event Name: ${eventName}`);
console.log(`Scheduled Date: ${eventDate}`);
console.log(`Initial Seats Available: ${availableSeats}`);

availableSeats--;
console.log(`Registration successful! Updated Available Seats: ${availableSeats}`);

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
communityEvents.forEach((event) => {
    if (event.isPast || event.seats === 0) {
        console.log(`🚫 Hiding Event: "${event.name}" (Reason: Event is past or fully booked)`); 
    } else {
        console.log(`✅ Displaying Event: "${event.name}" - Seats Remaining: ${event.seats}`); 
    }
});

function simulateRegistration(event) {
    try {
        console.log(`\nAttempting registration process for: ${event ? event.name : "Unknown Event"}...`);
        if (!event) {
            throw new Error("Registration failed: Missing valid event data record."); 
        }
        if (event.seats <= 0) {
            throw new Error(`Registration failed: "${event.name}" has absolutely no seats left!`); 
        }
        event.seats--;
        console.log(`🎉 Registration complete! Remaining seats: ${event.seats}`);
    } catch (error) {
        console.error(`⚠️ Exception Intercepted: ${error.message}`); 
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

function addEvent(id, name, category, seats) {
    const newEvent = { id: id, name: name, category: category, seats: seats };
    globalEventRegistry.push(newEvent);
    console.log(`➕ Event Added Successfully: "${name}"`);
}

function createRegistrationTracker(categoryName) {
    let totalRegistrations = 0;
    return function registerUser(eventName) {
        totalRegistrations++; 
        console.log(`🎟️ User registered for: "${eventName}"`);
        console.log(`[Metrics Tracker] Total registrations recorded for category "${categoryName}": ${totalRegistrations}`); 
        return totalRegistrations;
    };
}
const trackEnvironmentRegistration = createRegistrationTracker("Environment");

function filterEventsByCategory(registry, searchCallback) {
    const matchedResults = [];
    registry.forEach((event) => {
        if (searchCallback(event)) { 
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

function CommunityPortalEvent(id, name, location, seats) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.seats = seats;
} 

CommunityPortalEvent.prototype.checkAvailability = function() {
    return this.seats > 0;
}; 

const techWorkshop = new CommunityPortalEvent(201, "AI Tech Lab Expo", "Hall A", 15);
const blockParty = new CommunityPortalEvent(202, "Neighborhood Street Dance", "Main Street Loop", 0);

console.log(`Is "${techWorkshop.name}" accepting registrations? ${techWorkshop.checkAvailability() ? "Yes ✅" : "No ❌"}`);
console.log(`Is "${blockParty.name}" accepting registrations? ${blockParty.checkAvailability() ? "Yes ✅" : "No ❌"}`);

console.log(`\nListing property map structures for techWorkshop instance:`);
const structuralEntries = Object.entries(techWorkshop);

structuralEntries.forEach(([key, value]) => {
    console.log(`Key Name: [${key}] ---> Value Assigned: [${value}]`);
}); 

// ==========================================
// EXERCISE 6: ARRAYS AND METHODS
// ==========================================
console.log(`\n--- Exercise 6: Arrays and Non-Mutating Methods ---`);

const activeEventsList = [
    { id: 301, title: "Rock the Block Concert", type: "Music", totalSeats: 200 },
    { id: 302, title: "Organic Vegetable Swap", type: "Food", totalSeats: 40 },
    { id: 303, title: "Symphony Under the Stars", type: "Music", totalSeats: 150 },
    { id: 304, title: "Intro to Web Coding", type: "Education", totalSeats: 30 }
];

activeEventsList.push({ id: 305, title: "Jazz Night Ensemble", type: "Music", totalSeats: 75 });

const musicEventsOnly = activeEventsList.filter((event) => event.type === "Music");
console.log("\nFiltered Results (.filter() where type === 'Music'):", musicEventsOnly);

const formattedDisplayCards = activeEventsList.map((event) => {
    return `✨ Workshop on ${event.title} [Type: ${event.type.toUpperCase()}] (Capacity: ${event.totalSeats} places)`; 
});
console.log("\nFormatted Output Matrices (.map() transformation results):", formattedDisplayCards);


// ==========================================
// EXERCISE 7: DOM MANIPULATION
// ==========================================
console.log(`\n--- Exercise 7: Living DOM Manipulation Engine ---`);

const incomingDOMEventsList = [
    { id: 701, name: "Neighborhood Tree Planting Drive", space: "East Sector Meadow Park", available: 15 },
    { id: 702, name: "Amateur Baking & Pastry Bake-Off", space: "Community Center Kitchen", available: 8 },
    { id: 703, name: "Outdoor Cinematic Classic Movie Night", space: "Central Courtyard Amphitheater", available: 60 }
];

const eventDisplayContainer = document.querySelector("#events .event-gallery tr");

if (eventDisplayContainer) {
    eventDisplayContainer.innerHTML = "";

    incomingDOMEventsList.forEach((eventItem) => {
        const tableCellNode = document.createElement("td");
        
        // Exercise 8: Note how we added class names "dom-event-card" to the card elements below to easily target them during searches
        tableCellNode.innerHTML = `
            <div class="eventCard dom-event-card" style="background-color: #f0f4c3; transition: all 0.3s ease;">
                <h3 class="event-title" style="color: #2e7d32; font-size: 1.1rem; margin-bottom: 8px;">🌱 ${eventItem.name}</h3>
                <p style="margin: 4px 0; font-size: 0.9rem;">📍 Location: <strong>${eventItem.space}</strong></p>
                <p style="margin: 4px 0; font-size: 0.9rem;">🎟️ Available Slots: <span class="seat-badge" id="seat-count-${eventItem.id}">${eventItem.available}</span></p>
                <button type="button" class="cta-button" style="padding: 6px 12px; font-size: 0.85rem; margin-top: 10px; background-color: #2e7d32;" 
                        onclick="registerLiveDOMUser(${eventItem.id})">
                    Quick Register
                </button>
            </div>
        `;
        eventDisplayContainer.appendChild(tableCellNode);
    });
}

function registerLiveDOMUser(eventId) {
    const badgeElement = document.getElementById(`seat-count-${eventId}`);
    if (badgeElement) {
        let activeCount = parseInt(badgeElement.innerText);
        if (activeCount > 0) {
            activeCount--;
            badgeElement.innerText = activeCount;
            if (activeCount === 0) {
                badgeElement.parentElement.innerHTML = "<strong style='color: red;'>⚠️ SOLD OUT FULLY</strong>";
            }
            alert("🎉 Awesome choice! Your registration counter updated cleanly on the screen layout.");
        }
    }
}


// ==========================================
// EXERCISE 8: EVENT HANDLING
// ==========================================
console.log(`\n--- Exercise 8: Interactive Event Handling Stack ---`);

// 1. Target the Full Name input field to listen for focus and blurring events
const nameInputField = document.getElementById("fullName");

if (nameInputField) {
    // Fire actions whenever the input field gains user highlight focus
    nameInputField.addEventListener("focus", (event) => { //
        event.target.style.backgroundColor = "#e8f5e9"; // Soft green highlight
        event.target.style.borderColor = "#2e7d32";
        console.log("💡 Registration field received active user focus.");
    });

    // Reset styles completely when the user leaves the input field (blur)
    nameInputField.addEventListener("blur", (event) => { //
        event.target.style.backgroundColor = ""; 
        event.target.style.borderColor = "";
        console.log("💤 Registration field lost active focus (blur event triggered).");
    });
}

// 2. Real-Time Dynamic Search: Target the "Additional Details" text area to act as a live filter box for testing
const searchTextAreaInput = document.getElementById("userMessage");

if (searchTextAreaInput) {
    // Alter its placeholder text programmatically to inform the user of its secondary capability
    searchTextAreaInput.placeholder = "⚡ TYPE HERE TO SEARCH/FILTER ABOVE GREEN CARDS LIVE...";

    // Listen to every individual key stroke using the 'input' event model
    searchTextAreaInput.addEventListener("input", (event) => { //
        const searchStringValue = event.target.value.toLowerCase().trim();
        
        // Grab all current green DOM cards rendered on the webpage by Exercise 7
        const renderedCardsList = document.querySelectorAll(".dom-event-card"); //

        renderedCardsList.forEach((card) => {
            const cardTitleText = card.querySelector(".event-title").innerText.toLowerCase();

            // 3. Conditional Filtering: If card title doesn't contain typed letters, hide its structural table container cell
            if (cardTitleText.includes(searchStringValue)) {
                card.parentElement.style.display = ""; // Show card normally
                card.style.opacity = "1";
            } else {
                card.parentElement.style.display = "none"; // Hide completely from view
                console.log(`🔍 Hiding card match discrepancy for query string: "${searchStringValue}"`);
            }
        });
    });
}