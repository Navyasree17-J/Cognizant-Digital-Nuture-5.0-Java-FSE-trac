# 🛠️ Module 4: Test-Driven Development & Automated Testing Frameworks

Welcome to my comprehensive showcase of **Module 4**. This repository documents my deep-dive journey into software craftsmanship, focusing on **Test-Driven Development (TDD)**, robust automation testing strategies, dynamic mocking architectures, and advanced enterprise framework testing. 

By flipping the traditional "code first, test later" approach, I learned how to build high-quality, self-documenting, and regression-immune software systems.

---

## 🚀 Core Concepts Mastered

### 1. Introduction to TDD (Test-Driven Development)
Test-Driven Development (TDD) is an advanced engineering discipline that flips the traditional software manufacturing sequence on its head. In traditional workflows, developers read requirements, build an entire feature, deploy it, and then write tests (or hand it off to a QA team) to figure out if it works.

TDD shifts testing from a reactive quality-check phase to a proactive design phase. You cannot write a single line of production business code until you have written an automated test proving that the code is missing. This forces you to think about how your software behaves from the user or consumer's perspective before you ever worry about internal implementation details.

## Real-World Analogy
Think of an architect building a luxury skyscraper. They don’t pour concrete first and then pull out a tape measure to check if the walls fit. Instead, they use a physical structural mold (die/cast). The mold defines the exact shape, bounds, and requirements of the concrete block before it exists. TDD is the process of creating digital structural molds for your code.

```
//JAVA code

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VotingEligibilityTest {

    @Test
    public void should_deny_voting_rights_if_age_is_under_18() {
        // The class 'VotingEngine' does not exist yet!
        VotingEngine engine = new VotingEngine(); 
        boolean isEligible = engine.canVote(16);
        
        assertFalse(isEligible);
    }
}
```
### 2. The Red-Green-Refactor Cycle
* **🔴 Red:** Formulating strict, intentional unit test cases that fail initially to validate missing capabilities.
* **🟢 Green:** Authoring the absolute minimum production logic required to force a failing test to pass.
* **🔵 Refactor:** Cleaning up duplication, extracting constants, and optimizing performance architecture *without* breaking external behaviors.

## Real-World Analogy
Imagine writing an essay.

* **Red:** You decide on the thesis statement of your paragraph (the target goal).

* **Green:** You vomit out a fast, grammatically messy first draft just to get the ideas down on paper.

* **Refactor:** You pull out a red pen, fix spelling mistakes, restructure sentences, and polish the style until it reads beautifully.

## Step 1:
* **The 🔴 RED Phase**
```
//JAVA Code
//We write a test checking if 0°C maps properly to 32°F. It fails because the class doesn't exist.

@Test
public void should_convert_zero_celsius_to_thirty_two_fahrenheit() {
    TemperatureConverter converter = new TemperatureConverter();
    assertEquals(32.0, converter.toFahrenheit(0.0));
}
```
## Step 2:
* **The 🟢 GREEN Phase**
```
//JAVA Code
public class TemperatureConverter {
    public double toFahrenheit(double celsius) {
        return 32.0; // Hardcoded! But the test turns green.
    }
}
```
## Step 3:
* **The 🔵 REFACTOR Phase**
```
//JAVA Code
public class TemperatureConverter {
    private static final double FAHRENHEIT_FREEZING_POINT = 32.0;
    private static final double CONVERSION_FACTOR = 1.8;

    public double toFahrenheit(double celsius) {
        // Refactored to be dynamically correct and highly readable
        return (celsius * CONVERSION_FACTOR) + FAHRENHEIT_FREEZING_POINT;
    }
}
```

### 3. Core Unit Testing Fundamentals
A Unit Test is an automated script that tests the smallest testable chunk of an application—typically a single method or function—in absolute quarantine.

* **Isolation Dynamics:** Isolating small, distinct code methods in absolute quarantine from outside factors (file systems, networks, or databases).
* **The Testing Pyramid:** Understanding why quick unit testing acts as the foundational baseline layer of a modern application deployment timeline compared to slow, heavy integration checks.

```
//JAVA Code
// The Unit under test
public class OrderDiscountEngine {
    public double calculateFinalPrice(double basePrice, boolean isPremiumCustomer) {
        if (isPremiumCustomer) {
            return basePrice * 0.90; // 10% Off
        }
        return basePrice;
    }
}

// The Unit Test Suite
public class OrderDiscountEngineTest {
    @Test
    public void should_apply_ten_percent_discount_for_premium_tier() {
        OrderDiscountEngine engine = new OrderDiscountEngine();
        double actualPrice = engine.calculateFinalPrice(100.0, true);
        assertEquals(90.0, actualPrice);
    }
}
```
### 4. JUnit 5 Framework Mastery
* **Lifecycle Hooks:** Automating dynamic configurations across components using core annotations (`@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`).
* **Assertion Engine:** Running precise data checks via the `org.junit.jupiter.api.Assertions` library.
JUnit 5 (also known as JUnit Jupiter) is the primary automated testing framework for the Java language ecosystem. It provides the execution runner engine, lifecycle management annotations, and verification assertion tools needed to discover, isolate, and run test suites.

JUnit manages the runtime lifecycle of test suites via built-in hooks:

* **@Test:** Flags a standard method as an executable test case.

* **@BeforeEach:** Initializes test states before every separate test method runs to prevent cross-contamination.

* **@AfterEach:** Tears down state footprints after a single test finishes.

```
//JAVA Code
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountSystemTest {

    private StringBuilder logBuffer;

    @BeforeAll
    public static void globalInit() {
        System.out.println("Starting entire testing suite sequence...");
    }

    @BeforeEach
    public void initializeCleanWorkspace() {
        // Gives every single test a fresh, clean memory buffer
        logBuffer = new StringBuilder("START:");
    }

    @Test
    public void testAppendOperation() {
        logBuffer.append("DATA_A");
        assertEquals("START:DATA_A", logBuffer.toString());
    }

    @Test
    public void testAlternativeAppendOperation() {
        logBuffer.append("DATA_B");
        assertEquals("START:DATA_B", logBuffer.toString()); // Confirms buffer was reset!
    }

    @AfterEach
    public void cleanupWorkspace() {
        logBuffer.setLength(0); // Erases references
    }

    @AfterAll
    public static void globalTeardown() {
        System.out.println("All tests completed successfully.");
    }
}
```

### 5. The Arrange-Act-Assert (AAA) Architectural Pattern
The Arrange-Act-Assert (AAA) Pattern is a structural layout design standard for writing clean, scannable unit test methods. It divides a test method into three visually distinct blocks, ensuring readability across global development engineering teams.

* **Arrange:** Prepare the environment. Instantiate target classes, initialize primitive primitive variables, declare inputs, and prepare configurations.
* **Act:** Trigger the targeted operation. Execute the micro-specific method under test. This should almost always be a single line of code.
* **Assert:** Verify the outcome. Use assertion tools to confirm that the real-world output perfectly matches your expected baseline.

## Example
Think of a medical laboratory clinician testing a patient's blood sample:
* **Arrange:** They sanitize the workbench, unpack a sterile needle, and prepare the testing solution.
* **Act:** They drop the specific chemical agent into the blood vial (The Action).
* **Assert:** They look under the microscope to verify if the sample turns blue or remains red (The Assertion).
```
//JAVA Code
@Test
public void should_correctly_calculate_compound_interest() {
    // 1. ARRANGE
    FinancialCalculator calculator = new FinancialCalculator();
    double principalAmount = 1000.0;
    double interestRate = 0.05; // 5%
    int compoundingYears = 2;

    // 2. ACT
    double totalValue = calculator.calculateCompoundInterest(principalAmount, interestRate, compoundingYears);

    // 3. ASSERT
    double expectedValue = 1102.50;
    assertEquals(expectedValue, totalValue, 0.01); // 0.01 delta tolerance for floating point rounding differences
}
```
### 6. Advanced JUnit Capabilities
* **Data-Driven Engineering:** Writing highly reusable **Parameterized Tests** using `@ValueSource` and `@CsvSource` to run multiple inputs through a single assertion pipeline.
* **Execution & Bounds Safety:** Controlling test execution orders with `@Order`, optimizing pipeline speed via `@Tag` filtering, and ensuring runtime bounds with `@Timeout` performance barriers.
```
//JAVA Code
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedValidationTest {

    // 1. PARAMETERIZED TESTS WITH CSV SOURCE
    @ParameterizedTest
    @CsvSource({
        "10, 20, 20",   // Case A: Input 10, 20 -> Max is 20
        "99, 14, 99",   // Case B: Input 99, 14 -> Max is 99
        "-5, -1, -1"    // Case C: Input -5, -1 -> Max is -1
    })
    public void multi_range_maximum_value_check(int num1, int num2, int expectedMax) {
        MathEngine engine = new MathEngine();
        assertEquals(expectedMax, engine.findMax(num1, num2));
    }

    // 2. EXCEPTION TESTING BOUNDS
    @ParameterizedTest
    @CsvSource({"-1", "-50", "-999"})
    public void should_throw_illegal_argument_exception_on_negative_inputs(int invalidAge) {
        UserRegistration reg = new UserRegistration();
        
        assertThrows(IllegalArgumentException.class, () -> {
            reg.registerAge(invalidAge);
        });
    }

    // 3. TIMEOUT PERFORMANCE BARS
    @ParameterizedTest
    @CsvSource({"1000", "5000"})
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) // Fails if calculation stretches past 500ms
    public void processing_heavy_arrays_must_be_under_half_a_second(int arraySize) {
        DataSorter sorter = new DataSorter();
        sorter.sortLargeArray(arraySize);
    }
}
```
### 7. Core Mockito Basics
* **Component Simulation:** Leveraging `@Mock` and `@InjectMocks` to strip away external infrastructure blockers during unit test scopes.
* **Behavior Manipulation:** Controlling interactions with dependencies via **Stubbing** (`when().thenReturn()`), setting up flexible arguments with **Argument Matchers**, and handling `void` methods using `doThrow()`.

In enterprise applications, classes have complex internal dependencies (e.g., a **BillingService** needs a **CreditCardProcessorGateway** and an **EmailNotificationRepository**). In a pure unit test, you want to test only the internal structural logic of the **BillingService**.

Mockito is an advanced mocking framework that uses runtime bytecode manipulation to create simulated copies of interfaces or heavy concrete components (called Mocks). These act as controllable dummies. You use Stubbing (when().thenReturn()) to instruct the mock exactly how to respond when called.
```
//JAVA Code
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Integrates Mockito into JUnit 5 engine
public class ProductServiceTest {

    @Mock
    private ProductDatabaseRepository mockRepository; // Dummy proxy object

    @InjectMocks
    private ProductService productService; // Instantiates service and injects mock repo into it

    @Test
    public void should_fetch_valid_product_details() {
        // Arrange
        Product fakeProduct = new Product(77, "Mechanical Keyboard", 120.0);
        
        // STUBBING: Directing the mock's behavior
        when(mockRepository.findProductById(77)).thenReturn(fakeProduct);

        // Act
        Product result = productService.getProductDetails(77);

        // Assert
        assertNotNull(result);
        assertEquals("Mechanical Keyboard", result.getName());
        assertEquals(120.0, result.getPrice());
    }
}
```

### 8. Interaction & Verification Testing
* **Behavior Verification:** Moving beyond standard data checks to verify interactions using Mockito's `verify()` module.
* **Invocation Auditing:** Tracking exact method invocations using constraints like `times(n)` and `never()` to secure backend logical flows.

Data testing (assertEquals) works perfectly when a method returns a value. However, many enterprise methods return void. They perform actions behind the scenes, such as writing data to a file or sending an alert payload via SMS.

To test these methods, you use Interaction Verification. Mockito's verify() utility examines the internal memory logs of a mock object to confirm that a specific dependency method was called, how many times it was invoked, and exactly what parameters were passed to it.

##Real-World Analogy
Imagine a parent leaving their child at home with a checklist of chores, including "water the backyard garden plant pots." When the parent returns, they don't look at the plants directly. Instead, they check the home's smart security camera logs (Verification) to confirm that the child walked to the garden exactly once at 4:00 PM carrying a watering can.

### 9. Tiered Spring Boot Application Testing
Spring Boot applications rely heavily on Dependency Injection (DI), wiring layers (Controller $\rightarrow$ Service $\rightarrow$ Repository) together via its application context graph. Testing these systems requires a tiered strategy that balances execution speed and coverage:
* **Controller Layer (@WebMvcTest):** Isolates the HTTP presentation layer. It sets up MockMvc to simulate API calls without opening real server network sockets.
* **Repository Layer (@DataJpaTest):** Isolates database communication. It provisions a fast, temporary, in-memory SQL server instance and automatically rolls back database updates after every test to keep data clean.
* **End-to-End System (@SpringBootTest):** Boots up the entire application framework context on a live network port for full system integration checks.

##Code
##Component A: Controller Slice Test
```
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SecureClientController.class)
public class SecureClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Overrides real bean container mapping with a Mockito proxy shell
    private ClientDataService clientDataService;

    @Test
    public void controller_endpoint_should_return_json_payload() throws Exception {
        when(clientDataService.getClientName(99)).thenReturn("AlphaCorp");

        mockMvc.perform(get("/api/clients/99"))
               .andExpect(status().isOk()) // Confirms HTTP status 200 OK
               .andExpect(jsonPath("$.clientName").value("AlphaCorp"));
    }
}
```
##Component B: Full Integration System Test
```
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullSystemEndToEndIntegrationTest {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate; // Real HTTP Client engine

    @Test
    public void entire_system_smoke_test_run() {
        String targetUrl = "http://localhost:" + randomServerPort + "/api/health";
        
        // Makes an actual network call across the local network interface card loopback
        ResponseEntity<String> response = restTemplate.getForEntity(targetUrl, String.class);
        
        assertEquals(200, response.getStatusCode().value());
        assertEquals("SYSTEM_UP_AND_OPERATIONAL", response.getBody());
    }
}
```

### 10. Isolation of External Dependencies
Modern cloud applications interact heavily with external files, networks, and third-party SaaS APIs (like Stripe or AWS S3). If external services encounter outages, rate limits, or latency spikes, your internal pipeline can break.

To keep tests stable, follow these patterns:

* **The Ports and Adapters Architecture:** Wrap low-level third-party calls inside clean interface boundaries (Ports). Test your core business engine by mocking the interface port rather than the volatile third-party service itself.

* **@TempDir File Management:** Use JUnit’s isolated disk directory tool to handle file operations without cluttering your development machine's real drive storage paths.


## 💻 Tech Stack & Tools Utilized

* **Language:** Java
* **Testing Framework:** JUnit 5 (Jupiter Engine)
* **Mocking Utility:** Mockito Architecture
* **Enterprise Framework:** Spring Boot Test Environment
* **Project Management:** Maven / Gradle
##CODE
```
//JAVA Code
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class SecureFileArchiverTest {

    // 1. ISOLATING DISK PERMISSIONS VIA JUNIT TEMPORARY SANDBOX DIRECTORIES
    @Test
    public void should_safely_write_encrypted_logs_to_file(@TempDir Path dynamicTestFolder) throws IOException {
        // Arrange
        Path secureFileTarget = dynamicTestFolder.resolve("encrypted_audit.log");
        SecureFileArchiver archiver = new SecureFileArchiver();

        // Act
        archiver.archiveData(secureFileTarget, "SECRET_ENCRYPTED_PAYLOAD_STRING");

        // Assert
        assertTrue(Files.exists(secureFileTarget)); // Verifies the file was created on disk
        String fileContent = Files.readString(secureFileTarget).trim();
        assertEquals("SECRET_ENCRYPTED_PAYLOAD_STRING", fileContent);
        // JUnit 5 automatically deletes the dynamicTestFolder and its contents here!
    }
}
```
---

## 🧠 Key Takeaways & Developer Mindset
> "Good unit tests don't just find bugs; they define your system's architectural integrity."
Through this module, I stopped viewing automated tests as a chore or an afterthought. TDD has helped me write decoupled, highly maintainable code with a safety net that catches regression issues in milliseconds—long before code ever reaches production.
