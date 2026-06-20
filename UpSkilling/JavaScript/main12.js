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

if (typeof registerLiveDOMUser !== 'function') {
    window.registerLiveDOMUser = function(eventId) {
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
}


// ==========================================
// EXERCISE 8: EVENT HANDLING
// ==========================================
console.log(`\n--- Exercise 8: Interactive Event Handling Stack ---`);

const nameInputField = document.getElementById("fullName");

if (nameInputField) {
    nameInputField.addEventListener("focus", (event) => {
        event.target.style.backgroundColor = "#e8f5e9"; 
        event.target.style.borderColor = "#2e7d32";
        console.log("💡 Registration field received active user focus.");
    });

    nameInputField.addEventListener("blur", (event) => {
        event.target.style.backgroundColor = ""; 
        event.target.style.borderColor = "";
        console.log("💤 Registration field lost active focus (blur event triggered).");
    });
}

const searchTextAreaInput = document.getElementById("userMessage");

if (searchTextAreaInput) {
    searchTextAreaInput.placeholder = "⚡ TYPE HERE TO SEARCH/FILTER ABOVE GREEN CARDS LIVE...";

    searchTextAreaInput.addEventListener("input", (event) => {
        const searchStringValue = event.target.value.toLowerCase().trim();
        const renderedCardsList = document.querySelectorAll(".dom-event-card");

        renderedCardsList.forEach((card) => {
            const cardTitleText = card.querySelector(".event-title").innerText.toLowerCase();

            if (cardTitleText.includes(searchStringValue)) {
                card.parentElement.style.display = ""; 
                card.style.opacity = "1";
            } else {
                card.parentElement.style.display = "none"; 
                console.log(`🔍 Hiding card match discrepancy for query string: "${searchStringValue}"`);
            }
        });
    });
}


// ==========================================
// EXERCISE 9: ASYNCHRONOUS JAVASCRIPT
// ==========================================
console.log(`\n--- Exercise 9: Asynchronous Execution Engine ---`);

function fetchRemotePortalEvents() {
    return new Promise((resolve, reject) => {
        console.log("📡 Asynchronous Stream: Connecting to remote server cloud data pipeline...");
        
        setTimeout(() => {
            const processingErrorFault = false; 

            if (!processingErrorFault) {
                const simulatedCloudPayload = [
                    { id: 991, name: "Drone Aviation Basic Coding Camp", venue: "Tech Wing Room 3", spaces: 20 },
                    { id: 992, name: "Cross-District Badminton Cup Finals", venue: "Indoor Arena Gymnasium", spaces: 14 }
                ];
                resolve(simulatedCloudPayload);
            } else {
                reject(new Error("Network Data Sync Failure: Gateway connection timeout."));
            }
        }, 2000); 
    });
}

async function loadPortalDashboardAsynchronously() {
    try {
        console.log("⏳ UI Thread Notice: Displaying loading spinner in console background...");
        const freshCloudPayloadData = await fetchRemotePortalEvents();
        console.log("✨ Asynchronous Stream Success! Data packet received safely:", freshCloudPayloadData);

        const dynamicCellInsertionTarget = document.querySelector("#events .event-gallery tr");
        
        if (dynamicCellInsertionTarget) {
            freshCloudPayloadData.forEach((cloudEvent) => {
                const cloudTableCell = document.createElement("td");
                cloudTableCell.innerHTML = `
                    <div class="eventCard" style="background-color: #e1f5fe; border: 2px dashed #0288d1;">
                        <h3 style="color: #0288d1; font-size: 1.1rem;">🌐 Cloud: ${cloudEvent.name}</h3>
                        <p style="font-size: 0.9rem; margin: 4px 0;">📍 Venue: ${cloudEvent.venue}</p>
                        <p style="font-size: 0.9rem; margin: 4px 0;">🎟️ Available: <strong>${cloudEvent.spaces} openings</strong></p>
                    </div>
                `;
                dynamicCellInsertionTarget.appendChild(cloudTableCell);
            });
            console.log("✅ Appended external asynchronous cloud items cleanly to screen view!");
        }

    } catch (apiExceptionError) {
        console.error(`⚠️ Async Lifecycle Exception Logged: ${apiExceptionError.message}`);
    } finally {
        console.log("🏁 Async Operation Complete: Removing layout loading indicator spinner asset.");
    }
}
loadPortalDashboardAsynchronously();


// ==========================================
// EXERCISE 10: MODERN JAVASCRIPT FEATURES
// ==========================================
console.log(`\n--- Exercise 10: ES6+ Modern Refactoring Framework ---`);

function buildModernEventObject(title, capacity = 50, category = "General Community") {
    return {
        title: title,
        capacity: capacity,
        category: category,
        initializedDate: new Date().toLocaleDateString()
    };
}

const manualEvent = buildModernEventObject("Town Hall Meet", 100, "Civic Governance");
const defaultParametersEvent = buildModernEventObject("Seniors Morning Coffee Walk");

console.log("Generated Object with Manual Fields:", manualEvent);
console.log("Generated Object using Default Fallbacks:", defaultParametersEvent);

function displayModernEventDestructured(eventObject) {
    const { title, capacity, category } = eventObject;
    console.log(`[Destructured Readout] Heading: "${title}" | Group: ${category} | Max Capacity: ${capacity} entries.`);
}
displayModernEventDestructured(manualEvent);

const baseSourceArray = ["Art Expo", "Book Club", "Chess Match"];
const clonedWorkingArray = [...baseSourceArray];
clonedWorkingArray.push("Yoga Circle");

console.log("Original Base Array (Untouched):", baseSourceArray);
console.log("Cloned Array Stack (Safely Appended):", clonedWorkingArray);


// ==========================================
// EXERCISES 11, 12 & 13: FORMS, API DISPATCH & MODERN DEBUGGING LOGS
// ==========================================
console.log(`\n--- Exercises 11, 12 & 13: Live Form, AJAX & Diagnostics Audit Framework ---`);

const registrationFormNode = document.querySelector("#register form") || document.forms[0];

if (registrationFormNode) {
    registrationFormNode.addEventListener("submit", async function(event) {
        event.preventDefault(); 
        
        // ==========================================
        // EXERCISE 13: STEP 1 - LOG FORM SUBMISSION INIT
        // ==========================================
        console.group("🔍 [Diagnostic Audit Loop] Form Submission Event Triggered"); // Group logs together cleanly
        console.log("⏱️ Timestamp:", new Date().toISOString());
        console.log("📂 Target Element Structure:", event.target);

        const inputtedName = registrationFormNode.elements["fullName"].value.trim();
        const inputtedEmail = registrationFormNode.elements["emailAddress"] ? registrationFormNode.elements["emailAddress"].value.trim() : "";
        const selectedEventSelection = registrationFormNode.elements["eventSelect"] ? registrationFormNode.elements["eventSelect"].value : "";

        // ==========================================
        // EXERCISE 13: STEP 2 - INSPECT CAPTURED VARIABLES
        // ==========================================
        console.log("📥 Captured Values for Inspection:");
        console.log(`   -> Full Name string literal input: "${inputtedName}" (Length: ${inputtedName.length})`);
        console.log(`   -> Target Contact Email string input: "${inputtedEmail}"`);
        console.log(`   -> Associated Selection List Option ID: "${selectedEventSelection}"`);

        // Trigger an intentional pause breakpoint fallback statement for manual inspector attachment
        // To test real browser pausing, uncomment the keyword line below:
        // debugger; // 🛑 Browser DevTools will freeze execution stack frames here if your console is open!

        let formulationValidationStatus = true;

        if (inputtedName.length < 3) {
            formulationValidationStatus = false;
            console.warn("❌ Validation Discrepancy Detected: Full Name string fails length parameter checks.");
            displayInlineFormValidationError("fullName", "❌ Name field error: Entry must be at least 3 alphabetical characters long.");
        } else {
            clearInlineFormValidationError("fullName");
        }

        if (inputtedEmail === "" || !inputtedEmail.includes("@")) {
            formulationValidationStatus = false;
            console.warn("❌ Validation Discrepancy Detected: Email address string missing standard '@' envelope token.");
            if(registrationFormNode.elements["emailAddress"]) {
                displayInlineFormValidationError("emailAddress", "❌ Email address error: Please provide a valid email format.");
            }
        } else {
            if(registrationFormNode.elements["emailAddress"]) clearInlineFormValidationError("emailAddress");
        }

        if (formulationValidationStatus) {
            console.log("✅ Local Form Validation: PASSED. Assembling HTTP request package context.");

            const registrationPayload = {
                residentName: inputtedName,
                contactEmail: inputtedEmail,
                targetEventId: selectedEventSelection,
                submissionTimestamp: new Date().toISOString()
            };

            // ==========================================
            // EXERCISE 13: STEP 3 - LOG FETCH REQUEST PAYLOAD
            // ==========================================
            console.log("📦 Serializing Outgoing Fetch Payload JSON Object Map:");
            console.dir(registrationPayload); // Beautiful interactive object dropdown inside the DevTools Console

            let dynamicStatusBanner = document.getElementById("api-status-banner");
            if (!dynamicStatusBanner) {
                dynamicStatusBanner = document.createElement("div");
                dynamicStatusBanner.id = "api-status-banner";
                dynamicStatusBanner.style.padding = "12px";
                dynamicStatusBanner.style.margin = "15px 0";
                dynamicStatusBanner.style.borderRadius = "4px";
                dynamicStatusBanner.style.fontWeight = "bold";
                dynamicStatusBanner.style.textAlign = "center";
                registrationFormNode.appendChild(dynamicStatusBanner);
            }

            dynamicStatusBanner.style.backgroundColor = "#fff3e0"; 
            dynamicStatusBanner.style.color = "#e65100";
            dynamicStatusBanner.innerText = "⏳ Dispatching transmission securely to server data grid. Please hold...";
            
            const submitActionButton = registrationFormNode.querySelector("button[type='submit']");
            if(submitActionButton) submitActionButton.disabled = true;

            try {
                console.log("🌐 Network Tab Notice: Initiating network stream request frame. Open Network Tab to verify 'posts' resource status.");

                const networkServerResponse = await fetch("https://jsonplaceholder.typicode.com/posts", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(registrationPayload)
                });

                await new Promise(timerResolve => setTimeout(timerResolve, 1500));

                console.log(`📡 Response Received. Status Header Flags: HTTP ${networkServerResponse.status} ${networkServerResponse.statusText}`);

                if (networkServerResponse.ok) {
                    const responseJsonMetadata = await networkServerResponse.json();
                    
                    console.log("🎉 Server Transaction Completed Successfully! Unpacked JSON payload payload:");
                    console.log(responseJsonMetadata);

                    dynamicStatusBanner.style.backgroundColor = "#e8f5e9"; 
                    dynamicStatusBanner.style.color = "#2e7d32";
                    dynamicStatusBanner.innerHTML = `✅ Registration Completed Successfully!<br><span style='font-size:0.85rem; font-weight:normal;'>Server Record Generated. Tracking ID: ${responseJsonMetadata.id}</span>`;

                    registrationFormNode.reset();
                } else {
                    throw new Error(`Server dropped connection frame with HTTP Error Code: ${networkServerResponse.status}`);
                }

            } catch (networkFaultException) {
                // ==========================================
                // EXERCISE 13: STEP 4 - LOG AJAX PIPELINE FAILURE EXCEPTIONS
                // ==========================================
                console.error("💥 Critical Failure Caught inside Asynchronous Pipeline Runtime Flow Context:", networkFaultException);

                dynamicStatusBanner.style.backgroundColor = "#ffebee"; 
                dynamicStatusBanner.style.color = "#c62828";
                dynamicStatusBanner.innerText = `❌ Network Error: Unable to complete cloud submission. (${networkFaultException.message})`;
            } finally {
                if(submitActionButton) submitActionButton.disabled = false;
                console.groupEnd(); // Close out our clean diagnostic console layout grouping cleanly
            }

        } else {
            console.warn("⚠️ Submission Lifecycle Aborted: Data verification failures remain unresolved inside the form schema scope.");
            console.groupEnd(); // End group context output on validation abort as well
        }
    });
}

function displayInlineFormValidationError(elementId, errorMessageText) {
    const targetInputField = document.getElementById(elementId);
    if (targetInputField) {
        targetInputField.style.borderColor = "#d32f2f";
        let existingErrorSpan = targetInputField.parentElement.querySelector(".inline-error-msg");
        if (!existingErrorSpan) {
            existingErrorSpan = document.createElement("span");
            existingErrorSpan.className = "inline-error-msg";
            existingErrorSpan.style.color = "#d32f2f";
            existingErrorSpan.style.fontSize = "0.8rem";
            existingErrorSpan.style.display = "block";
            existingErrorSpan.style.marginTop = "4px";
            targetInputField.parentElement.appendChild(existingErrorSpan);
        }
        existingErrorSpan.innerText = errorMessageText;
    }
}

function clearInlineFormValidationError(elementId) {
    const targetInputField = document.getElementById(elementId);
    if (targetInputField) {
        targetInputField.style.borderColor = "";
        const existingErrorSpan = targetInputField.parentElement.querySelector(".inline-error-msg");
        if (existingErrorSpan) {
            existingErrorSpan.remove();
        }
    }
}