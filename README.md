
## Library Management System

The Library Management System is a Java-based application developed using JavaFX for the user interface and MySQL for the database. It is designed to manage the book categories, track books issued and returned, and maintain member information. The application follows a layered architecture for better maintainability and scalability.



## Features

- Book Category Management (Add, Update, Delete, Search)
- Book Management (Add, Update, Delete, Search)
- Member Management (Add, Update, Delete, Search)
- Book Issuing and Returning
- Fine Calculation for Late Returns
- Statistics for Borrowed and Returned Books


## Architecture

The application follows a layered architecture consisting of:
- **Presentation Layer:** JavaFX-based user interface
- **Service Layer:** Business logic and service handling
- **Data Access Layer:** Database interaction using JDBC
- **Database Layer:** MySQL database for storing library data

## Technologies Used

- Java
- JavaFX
- MySQL
- JDBC
- Git

## Usage

1. **Add Books and Members:**
   - Navigate to the respective sections to add books and members.
   - Fill in all required fields. The system will validate the input and display warning messages for any incorrect or missing details.

2. **Issue Books:**
   - Go to the 'Issue Book' section.
   - Enter the book ID and member ID. The system will validate the IDs and show warnings if they are invalid or if the fields are left empty.
   - Upon successful validation, the book will be issued to the member.

3. **Return Books:**
   - Access the 'Return Book' section.
   - Enter the issued book ID. The system will check the validity of the ID and display any applicable warnings.
   - If the return is valid and within the due date, the book will be returned. If the due date has passed, a fine will be calculated and displayed.

4. **View Statistics:**
   - Navigate to the 'Statistics' section to view details on borrowed and returned books.
   - The system will fetch and display relevant statistics, including book IDs, member IDs, book names, category IDs, issue dates, return dates, and fines.


## Screenshots

![login](https://github.com/user-attachments/assets/42899953-5b52-4d2c-b3a2-3489a2194512)
![signup](https://github.com/user-attachments/assets/81f6253a-b9a4-4753-9d13-5c4dc4a3ccf9)
![img1](https://github.com/user-attachments/assets/7191569b-9cc6-4497-869f-575d516ad11e)
![img2](https://github.com/user-attachments/assets/9bec6e04-b319-4fc1-af11-374d4eecf4f9)
![img3](https://github.com/user-attachments/assets/43c72835-15fb-4bd0-89aa-e825289c8ab1) 
