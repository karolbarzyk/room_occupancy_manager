# Hotel Room Allocation

This project is a Spring Boot application that optimizes room occupancy for a hotel by allocating available Premium and Economy rooms to potential guests based on their willingness to pay. The goal is to maximize overall revenue while following the business rules provided.

## Requirements

Our hotel clients have two room categories:

- **Premium Rooms:** For guests willing to pay **€100 or more**.
- **Economy Rooms:** For guests paying less than **€100**.

**Allocation Rules:**

- Guests willing to pay **€100 or more** are prioritized for Premium rooms.
- Guests paying less than **€100** are assigned to Economy rooms.
- If all Economy rooms are filled and Premium rooms are still available, the highest paying Economy guests are upgraded to Premium.
- If there are more guests than available rooms, the highest paying guests are allocated.

**Test Scenarios:**

1. **Scenario 1:**
    - **Input:** Premium Rooms: 3, Economy Rooms: 3
    - **Expected Output:** Usage Premium: 3 (Revenue: €738), Usage Economy: 3 (Revenue: €167.99)
2. **Scenario 2:**
    - **Input:** Premium Rooms: 7, Economy Rooms: 5
    - **Expected Output:** Usage Premium: 6 (Revenue: €1054), Usage Economy: 4 (Revenue: €189.99)
3. **Scenario 3:**
    - **Input:** Premium Rooms: 2, Economy Rooms: 7
    - **Expected Output:** Usage Premium: 2 (Revenue: €583), Usage Economy: 4 (Revenue: €189.99)

The potential guests are represented by the following JSON array:

```json
[23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
```

API Specification
Endpoint: /occupancy
Method: POST


Request:

```json
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

Response:

```json
{
  "usagePremium": 6,
  "revenuePremium": 1054,
  "usageEconomy": 4,
  "revenueEconomy": 189.99
}

```


## Running via Script
There are several ways to run the application:

Using the run.sh Script

```bash
chmod +x run.sh
```

Run the script:

```bash
./run.sh
```

The script builds the project and starts the JAR. The application listens on port 8080.

## Running via Maven
Alternatively, start the application with:

```bash
mvn spring-boot:run
```


## Testing the Application

Run all tests with:

```bash
mvn test
```

This command executes both unit and integration tests.

## Manual Testing with Postman
Start the application using any of the methods above.

Open Postman and create a new POST request to:

```bash
http://localhost:8080/occupancy
```

Set the request body to raw JSON with the following content:
```json
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

Send the request and verify that you receive the expected response:

```json
{
  "usagePremium": 6,
  "revenuePremium": 1054,
  "usageEconomy": 4,
  "revenueEconomy": 189.99
}
```




