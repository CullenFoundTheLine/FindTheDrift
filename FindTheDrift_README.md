# FindTheDrift

FindTheDrift is a Java-based simulation and performance analysis app designed for drift and race enthusiasts. It allows users to select real-world cars, customize them with parts, and simulate lap times or enter real-world lap data. The app is built to eventually support live ECU/CPU data integration for real-time performance tracking.

## Features

- Select real-world drift and race cars categorized by brand and type.
- Customize cars with upgrade parts from the PartConnect catalog.
- Simulate lap times based on grip-to-weight ratio, tire type, and track difficulty.
- Input real-world lap times and installed parts for analysis.
- Analyze cornering logic with braking zones and drift feedback.
- View results in a leaderboard format.
- Tire wear and performance calculation logic included.
- Modular codebase for easy updates and expansion.

## Getting Started

### Prerequisites

- Java 11 or higher
- IDE like IntelliJ IDEA or Eclipse

### Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/FindTheDrift.git
   ```

2. Open the project in your IDE.

3. Compile and run the `SystemClass.java` file.

### Folder Structure

```
FindTheDrift/
│
├── src/
│   ├── SystemClass.java
│   ├── Car.java
│   ├── Part.java
│   ├── Tire.java
│   ├── MapSelector.java
│   ├── Leaderboard.java
│   ├── Garage.java
│   ├── PartConnect.java
│   ├── CornerZone.java
│   └── CarCatalog.java
│
└── README.md
```

## Contributing

Feedback and contributions are welcome. Please fork the repository and open a pull request.

## License

This project is currently private. Licensing will be determined upon further development.
