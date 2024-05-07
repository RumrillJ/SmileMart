# Project Requirements

## General Description
Create a full-stack web application using Spring Boot and React. The application should utilize AWS RDS for a PostgreSQL database, AWS EC2 for server-side deployment, and AWS S3 for client-side deployment. The purpose and functionality of the app will be determined by the specific project theme which is open-ended.

## Minimum Viable Product (MVP) Requirements

### Technical Requirements
- **Frontend**: Must use React.
- **Backend**: Must be based on Spring Boot.
- **Database**: Must include at least 3 related tables. One of these must be a `users` table for login and session management.
  - Example relationships include:
    - A join table in a many-to-many relationship (e.g., `users`, `items`, and an `orders` table to track when users order items).
    - A "love triangle" relationship where two tables have a one-to-many relationship with a third table, but not with each other (e.g., a `posts` table linked to `users` and a `comments` table linked to `posts`).

### Security
- **Authentication**: Must implement JWTs for secure HTTP requests and responses.

### State Management
- **Frontend State Management**: Must use the Context API or Redux for managing global state.

## Additional Project Standards

### Project Structure
- Code should be organized into packages (e.g., `main`, `database`, `models`).
- All configuration files should be stored within the `resources` folder.

### Database Connectivity & SQL Design
- Ensure the driver class is registered and the database connection is established.
- Schema design should follow best practices to reduce redundancy and support data integrity (Normalization).

### Functionality
- Implement CRUD operations accurately.
- Manage and format data output neatly.
- Handle invalid user input gracefully.

### Code Quality
- Apply OOP concepts and Solid Principles appropriately.
- Maintain consistent naming conventions and formatting in code.

### RESTful API Design
- API should use proper HTTP methods like GET, POST, etc.
- Design coherent and consistent resource URIs and endpoints.
- Use appropriate HTTP status codes to reflect API response statuses.

### Deployment
- Backend API should be deployed on AWS EC2 for public access.
- Frontend should be deployed on AWS S3 and made publicly accessible.

## Contribution Guidelines
- Detailed guidelines on how to contribute to the project should be provided.

