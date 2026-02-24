# Ride-Sharing System Simulation 🚗

A comprehensive console-based ride-sharing application simulating core functionalities of platforms like Uber. Built with Java, this system demonstrates object-oriented programming principles, data management, and real-time ride operations.

## 📸 Screenshot

*(Add your application screenshot here)*

## ✨ Features

### Core Functionality

- 🚕 **Driver Management**: Add and track drivers with their details (balance, rating, availability)
- 👤 **User Management**: Register users with location and wallet balance
- 🚗 **Ride Operations**: Request, start, and complete rides
- 💰 **Payment Processing**: Automatic fare calculation and balance deduction
- ⭐ **Rating System**: Rate drivers after ride completion
- 📊 **Ride History**: Track all completed rides

### System Capabilities

- 🔄 **Real-time Status Updates**: Track ride status (pending, matched, in-progress, completed)
- 🎯 **Automatic Driver Matching**: Find and assign available drivers
- 📍 **Location Tracking**: Update user and driver locations
- 💵 **Earnings Management**: Calculate driver earnings (80% of fare)
- 📈 **Rating Algorithm**: Dynamic driver rating updates based on passenger feedback

## 🛠️ Technologies Used

- Java (Core Java, OOP)
- Collections Framework (ArrayList, List)
- Scanner for user input
- Random for driver selection
- Object-Oriented Design Patterns

## 🎯 How to Use

### Main Menu Options

1. **View All Drivers** - Display all registered drivers with their details
2. **View All Users** - Show all system users and their balances
3. **Request a Ride** - Create a new ride request
4. **Start Current Ride** - Begin the matched ride
5. **Complete Current Ride** - Finish ride and process payment
6. **View Current Ride Details** - Check active ride information
7. **View Ride History** - See all completed rides
8. **Exit** - Close the application

graph LR
    A[Request Ride] --> B[Driver Matched]
    B --> C[Ride Started]
    C --> D[Ride Completed]
    D --> E[Payment & Rating]

