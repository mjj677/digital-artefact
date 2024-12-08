# Pokemon Battle System
## Built by Matt Johnston

A Java-based command-line application featuring a Pokemon battle system, TDEE calculator, and user data management.

## Features

### User Management
- Collects and stores user information
- Loads previous user data when available
- Saves user interactions and battle history

### Pokemon Battle System
- Three starter Pokemon: Charizard, Blastoise, and Venusaur
- Type-based advantage system (Fire, Water, Grass)
- Battle mechanics including:
  
   - Attacks with varying power and accuracy
   - Healing system  
   - Battle commentary
   - Battle history tracking

### TDEE Calculator
- Calculates Total Daily Energy Expenditure
- Takes into account:
  
   - Age
   - Weight 
   - Height
   - Activity level

### Getting Started
#### Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher

#### Installation

Clone the repository:

```
git clone https://github.com/mjj677/digital-artefact/
```

Navigate to the project directory:

```
cd digital-artefact
```

Build the project:

```
./gradlew build
```

Run the application:

```
./gradlew run
```

#### Running Tests

To run the test suite:

```
./gradlew test
```

### Usage

On first run, you'll be prompted to enter your details.

Choose from the main menu options:

- Calculate years until retirement
- View workplace information
- See your full name
- Get personalised encouragement
- Calculate TDEE
- Start a Pokemon battle


For Pokemon battles:

- Choose your starter Pokemon
- Battle against a computer opponent
- Use attacks or healing items

### File Storage

The application stores data in a ```user_data``` directory:

- User information
- Battle history
- TDEE calculations
