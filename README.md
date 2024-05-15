
# WIP:  SmileMart

Welcome to **SmileMart**, your one-stop online shop for everything that makes you smile! Our e-commerce platform offers a wide range of products from your favorite categories, ensuring a seamless and joyful shopping experience. Whether you're looking for the latest gadgets, home essentials, or personal care items, SmileMart has it all. Shop with us today and bring a smile to your face with every purchase!

## Overview

The SmileMart application consists of two main components:
- **Frontend**: Built with React, the frontend provides an intuitive user interface.
- **Backend**: Developed with Spring Boot, the backend handles business logic, data management, and communication with the frontend.

## Features

- **User Authentication**: Secure login and registration system for users.
- **Product Browsing**: Explore a variety of products across multiple categories.
- **Shopping Cart**: Add items to your cart and manage your orders easily.
- **Order Management**: Users can view their order history and track new orders.
- **Admin Panel**: Admins can add, edit, or remove products, view orders, and manage users.

[Add main page image]

### Validation
- **User Authentication**: The system ensures that only logged-in users can access functionalities. Using Spring Security, the backend manages sessions and authentication states securely. Each request is verified to ensure that it comes from an authenticated session before any user-specific actions are processed. In case of session expiration or non-authentication, the system redirects users to the login page.

### Integration of Frontend and Backend
- **API Communication**: The frontend uses Axios to make HTTP requests to the backend. These requests include credentials where necessary, and the backend uses session cookies to maintain user state across requests.
- **Secure Data Flow**: The communication between the frontend and the backend is secured through HTTPS, ensuring that all data transferred remains encrypted and secure from interceptors.
- **Frontend Authentication Checks**: The frontend has mechanisms to check whether the user is logged in before rendering protected routes. It interacts with the backend to fetch authentication status and user details, which are then stored in the global context or local storage for quick access and to manage user sessions effectively.
- **Error Handling and User Feedback**: Both frontend and backend include robust error handling mechanisms to deal with authentication errors, such as unauthorized access attempts or session timeouts. Users are promptly informed with appropriate messages guiding them to re-authenticate or correct their actions.


## Technical Stack

- **Frontend**: React, Axios for API calls
- **Backend**: Spring Boot with Spring Security for secure API endpoints, Spring Data JPA for database interactions
- **Database**: PostgreSQL
- **Deployment**: AWS EC2 (backend), AWS S3 (frontend), AWS RDS (database)

## Database Architecture

The database architecture includes tables for ...

[Add ER-diagram screenshot]

## Requirements

For detailed project requirements, please refer to [Project Requirements](requirements.md).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Node.js
- npm
- Java 11
- Maven
- PostgreSQL
- AWS CLI (for deployment)

### Installing

**Setting up the frontend:**

1. Clone the repository and navigate to the frontend directory.
   ```bash
   git clone https://github.com/yourusername/SmileMart.git
   cd SmileMart/frontend
   ```

2. Install dependencies.
   ```bash
   npm install
   ```

3. Start the development server.
   ```bash
   npm start
   ```

**Setting up the backend:**

1. Navigate to the backend directory from the root of the repository.
   ```bash
   cd backend
   ```

2. Install Maven dependencies.
   ```bash
   mvn install
   ```

3. Run the Spring Boot application.
   ```bash
   mvn spring-boot:run
   ```

### Environment Configuration

Make sure to configure the following environment variables:

- `REACT_APP_API_URL` - URL to the backend API
- `SPRING_DATASOURCE_URL` - JDBC URL for the PostgreSQL database
- `AWS_ACCESS_KEY_ID` - AWS access key for S3 deployment
- `AWS_SECRET_ACCESS_KEY` - AWS secret key for S3 deployment

## Deployment

To deploy SmileMart on AWS:

1. Set up your AWS RDS instance for PostgreSQL.
2. Deploy the backend to AWS EC2 using the AWS CLI or Elastic Beanstalk.
3. Deploy the frontend static files to AWS S3 and configure it for web hosting.

## Contributing

We welcome contributions to SmileMart! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- **[Aruna Assija](https://github.com/arunaassijaqa)**
- **[Brian Lam](https://github.com/brianlxm)**
- **[Chikaosolu Amaechi](https://github.com/camaechi)**
- **[Daniel Istre](https://github.com/danielri02)**
- **[Gaetano Barreca](https://github.com/gaebar)**
- **[James Ta](https://github.com/jhtwhb)**
- **[Jeremy Eddy](https://github.com/illstar)**
- **[John Rumrill](https://github.com/RumrillJ)**
- **[Jonathan Tackett](https://github.com/JonCTack)**
- **[Mcantony Benson Okey](https://github.com/CPTNTBS)**
- **[Sidney Bowe](https://github.com/bowe1)**
- **[ThomasHarding](https://github.com/ThomHarding)**
- **[Ward Alhadid](https://github.com/wardalhadid)**
- **[Yanyan Jiang](https://github.com/Yanyan07)**


## License

This project is licensed under the [MIT License](https://opensource.org/license/mit).


