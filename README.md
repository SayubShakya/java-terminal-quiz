# 🧠 Terminal-Based Quiz Application

This is a **command-line Java quiz application** that allows users to take quizzes, manage questions, and track high scores — all within a terminal interface.

---

## 📄 Description

This project demonstrates a **text-based quiz system** written in Java, designed to run in a terminal or command prompt. It includes functionality for:

- Taking quizzes
- Managing quiz questions (CRUD operations)
- Viewing high scores

It’s simple, fast, and perfect for learning Java fundamentals like control flow, loops, user input, and data handling.

---

## 💡 What I Did

In this project, I built a fully functional quiz system in the terminal using core Java. Here's a breakdown of what I implemented:

### 🧭 1. Menu Navigation System
- Created a clear, interactive main menu for users to select actions like starting the quiz, managing questions, or viewing scores.
- Menu options are navigated using number input for simplicity and accessibility.

### 🛠️ 2. Quiz Management System
- Added CRUD operations to manage quiz questions:
    - **Add a question**
    - **View all questions**
    - **View question by ID**
    - **Delete question**
- These operations simulate an admin panel for quiz content management.

### 🧠 3. Quiz Gameplay
- When a user starts the quiz, questions are displayed one by one.
- The user selects an answer, and the system keeps track of correct answers.
- At the end of the quiz, the final score is shown.

### 🏆 4. High Score Tracking
- Stored and displayed the highest score achieved in the session.
- Encourages users to try again and beat the previous high score.

### 🔧 5. Modular Java Structure
Organized the application using multiple classes:
- `Main.java` — Entry point, handles main menu
- `QuizManager.java` — Handles question management
- `Quiz.java` — Runs the quiz gameplay
- `Question.java` — Model class representing a quiz question
- `HighScore.java` — Tracks and stores the high score

---

## 📋 Features

### Main Menu
- Press `1` to Start Quiz
- Press `2` to Manage Quiz
- Press `3` to View High Score
- Press `4` to Quit

### Manage Quiz Menu
- Press `1` to Add a Question
- Press `2` to View All Questions
- Press `3` to View Question by ID
- Press `4` to Delete Question
- Press `5` to Return to Main Menu

---

## 🔧 Technologies Used

- Java (Core Java)
- Terminal / Command Line Interface
- `Scanner` class for input handling

---

## 🚀 How to Run

1. Compile the project (adjust paths as necessary):
```bash
javac src/com/sayub/Main.java -cp ".:/<your_path>/dependency/*" -sourcepath src -d build/classes 
java -cp ".:/<your_path>/dependency/*:/<your_path>//build/classes" com.sayub.Main 
```
