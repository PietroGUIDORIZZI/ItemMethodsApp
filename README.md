# Inventory Console App

A simple inventory management system made in Java for the console.
This project started as a way to practice object-oriented programming 
and gradually evolved into a more structured application with separation of 
responsibilities, validation, services, persistence, and cleaner architecture.

The idea of the system is simple:

> Keep track of household items, quantities, categories, and locations inside a house.

---

# Features

- Add items
- Remove items
- Search items by name
- Filter items by category
- Move items between rooms
- Change item categories
- Restock items
- Use/consume items
- Show low stock items
- Show inventory statistics
- Save data to file
- Load data automatically on startup

---

# Technologies

- Java
- Object-Oriented Programming (OOP)
- File persistence with `.txt`
- Builder Pattern
- Layered architecture

---

# Project Structure

```text
controller/   -> application flow
service/      -> business rules
model/        -> entities and enums
persistence/  -> file handling
ui/           -> console interaction
util/         -> parsers and helpers
```

---

# What I Learned

This project became much bigger than I originally planned.

While building it, I practiced:

- Encapsulation
- Validation
- Exception handling
- Separation of concerns
- Clean code
- Layered architecture
- Builder pattern
- State protection inside entities
- Service-oriented logic
- Refactoring
- Input validation
- File persistence

It was also my transition from:

```text
"code that works"
```

to:

```text
"software organized like a real application"
```

---

# Example

```text
|========================|
|       Inventory        |
|========================|
|          View          |
|========================|
|  1 |List items         |
|  2 |Search items       |
|  3 |Filter by Category |
|  4 |Show Statistics    |
|========================|
```

---

# Future Plans

This console version is the foundation for the next steps:

- Spring Boot REST API
- Database integration
- Angular frontend
- Full-stack inventory system

---
# How to Run

## Requirements

- Java 17+ installed
- IntelliJ IDEA or another Java IDE

---

## Clone the repository

```bash
git clone https://github.com/your-username/inventory-console-app.git
```

---

## Open the project

Open the project folder in IntelliJ IDEA.

---

## Run the application

Locate the `Main.java` file and run it.

The console menu will appear:

```text
|========================|
|       Inventory        |
|========================|
```

---

## Data persistence

The application automatically creates and uses:

```text
items.txt
```

This file stores all inventory data between executions.

---

## Notes

- Invalid menu options are handled safely.
- Item quantities cannot become negative.
- Empty item names are not allowed.
- Data is automatically saved after changes.
  
# Author

Built by Pietro Guidorizzi while practicing Java and software architecture.
