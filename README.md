# ShopSphere

ShopSphere is an e-commerce web application designed to offer a comprehensive online shopping experience. It includes features such as product listing, shopping cart management, user authentication, an admin panel for product management and order tracking, and secure payment processing with Stripe.

## Features

- **Product Listing**: Browse through a wide variety of products with detailed descriptions.
- **Shopping Cart**: Manage items in your cart - add, update, or remove products.
- **User Authentication**: Secure user registration and login functionalities.
- **Admin Panel**: Admin functionalities to manage products.
- **Payment Integration**: Secure payment processing with Stripe.

## Tech Stack

- **Frontend**: 
  - React
  - Redux
  - Axios
- **Backend**: 
  - Spring Boot
  - Spring Security
  - Hibernate
  - MongoDB (NoSQL)
- **Payment Integration**: 
  - Stripe
- **Build Tools**: 
  - Maven (Backend)
  - Webpack (Frontend)
- **Deployment**: 
  - AWS

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- MongoDB
- Stripe Account

### Installation

1. **Clone the repository**

    ```bash
    git clone https://github.com/Baburao-sanivada/ShopSphere.git
    cd ShopSphere
    ```

2. **Setup the backend**

    - Ensure MongoDB is running on your system.
    - Update `src/main/resources/application.properties` with your MongoDB and Stripe configuration details.

    - Build and run the backend:
        ```bash
        cd backend
        mvn clean install
        mvn spring-boot:run
        ```

3. **Setup the frontend**

    - Install dependencies and start the frontend:
        ```bash
        cd frontend
        npm install
        npm start
        ```

## Usage

- Access the frontend at `http://localhost:3000`.
- The backend API is available at `http://localhost:8081`.

### API Endpoints

### API Endpoints

- **Cart API**
    - `POST /cart/addItemToCart`: Add an item to the cart.
    - `POST /cart/removeItemFromCart`: Remove an item from the cart.
    - `GET /cart/getCartData`: Retrieve cart data.

- **Product API**
    - `POST /product/add`: Add a new product.
    - `GET /product/allProducts`: Retrieve all products.
    - `POST /product/delete/{productId}`: Delete a product by its ID.
    - `GET /product/newCollections`: Retrieve the latest product collections.

- **User API**
    - `POST /user/signup`: Sign up a new user.
    - `POST /user/login`: Log in a user.
    - `GET /user/getEmail`: Retrieve the email of a user from the JWT token.

- **Payment API**
    - `POST /payment/create`: Create a payment intent using Stripe.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/name`).
3. Commit your changes (`git commit -m 'Description about changes'`).
4. Push to the branch (`git push origin feature/name`).
5. Create a new Pull Request.
