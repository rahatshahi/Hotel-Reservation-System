# Hotel Reservation System

A Java Swing desktop application for managing hotel customers, rooms, and reservations. This is an academic project built with Java and local file-based storage.

## Features

### Customer Management
- Add and save customer records
- Edit selected customer records
- Remove customer records
- Search customer records

### Room Management
- Add and edit rooms
- Remove rooms
- Select a room type and set a nightly price
- Search room records

### Reservation Management
- Create reservations with customer, room, check-in, and check-out details
- View pending and confirmed reservations separately
- Edit, confirm, and cancel reservations
- Check date overlaps to help prevent double-booking a room
- Show rooms available for the selected date range

### Interface
- Java Swing graphical user interface
- Separate screens for customers, rooms, and reservations
- Hotel-themed backgrounds, icons, and simple animations

## Technology

- Java (JDK 8 or later; JDK 17 is recommended)
- Java Swing and AWT
- Java Collections
- Java Object Serialization for local data persistence

> **Storage note:** This project does not use MySQL. It stores application data locally in Java serialization `.dat` files.

## Project Structure

All Java source files and image assets are kept in the project root because the application currently loads its images using relative file paths.

```text
Hotel-Reservation-System/
├── MainFrame.java
├── Customer.java
├── CustomerFrame.java
├── CustomerPanel.java
├── Room.java
├── RoomType.java
├── RoomFrame.java
├── RoomPanel.java
├── Reservation.java
├── ReservationFrame.java
├── ReservationPanel.java
├── DataStore.java
├── DateLabelFormatter.java
├── hotel_sketch.png
├── golden_background.png
├── customer_icon.png
├── room_icon.png
├── reservation_icon.png
├── screenshots/
│   └── main-menu.png
├── .gitignore
└── run.bat
```

## Run on Windows

1. Install a JDK (JDK 17 recommended).
2. Download or clone this repository.
3. Open the project folder.
4. Double-click `run.bat`.

Or run these commands in Command Prompt from the project folder:

```bat
javac -encoding UTF-8 -d out *.java
java -cp out MainFrame
```

## Data Files

When you save application records, the program creates local files such as:

- `customers.dat`
- `rooms.dat`
- `reservations.dat`
- `pending_reservations.dat`
- `confirmed_reservations.dat`

These files are intentionally excluded from Git because they can contain personal or test records. The application can start with empty data files when they do not exist.

## Screenshot

![Hotel Reservation System main menu](screenshots/main-menu.png)

## Notes

- This is an academic desktop project, not a production hotel-booking service.
- Data is stored locally on the computer running the application.
- The repository contains source code and required image assets; compiled `.class` files and local `.dat` records are excluded.
