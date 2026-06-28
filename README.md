[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=PROJECT_KEY&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=PROJECT_KEY)
Snakerer is a desktop application built in Java using Swing. It recreates the classic Snake game where the player controls a snake that grows by collecting apples while avoiding collisions with walls and itself.

The project is managed with Maven and includes JUnit 5 unit tests, JaCoCo code coverage, and Checkstyle for code quality.

Features
Classic Snake gameplay
Keyboard controls using arrow keys
Score tracking
Collision detection
Game Over screen
Random apple generation
Unit testing with JUnit 5
Code coverage reporting with JaCoCo
Static code analysis with Checkstyle
Ready for SonarQube integration

Prerequisites
Before running the project, make sure you have:
Java JDK 8 or later
Maven 3.6+
Git (optional)

Running the Project
Clone the repository:
git clone https://github.com/Arvat-collab/Snake/tree/main
cd Snake
Compile the project:
mvn clean compile
Run the application:
mvn exec:java -Dexec.mainClass="com.example.Snake"

Alternatively, you can run the Snake class directly from your IDE.
Running Tests
Execute all unit tests:
mvn test
Generate the JaCoCo coverage report:
mvn verify
The coverage report will be generated inside:
target/site/jacoco/

Controls
Key	Action
↑	Move Up
↓	Move Down
←	Move Left
→	Move Right
