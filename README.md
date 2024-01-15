
# Book Store

The Book Store API is a Java-based web service that provides endpoints for managing a book store. It allows users to perform operations like adding new books, retrieving book details, updating book information, and much more.
* overseeing the management of available books and categories (including adding new items and removing outdated ones)
* handling the population of shopping carts with cart items and facilitating order completion based on their contents
* managing the user's orders
## Features
* CRUD operations for managing books.
* User authentication and authorization.
* Shopping cart functionality.
* Order processing and management.
## Used Technologies
Core Technologies:
* Java
* Maven (Build tool)
### Spring Framework:
* Spring Boot
* Spring Data JPA
* Spring Boot Security
* Lombok
* Mapstruct
### Database and Persistence:
* Hibernate
* MySQL
* Liquibase
### Testing:
* JUnit 5
* Mockito
* Docker
### API Documentation:
* Swagger

## Functionality of controllers

### Book controller
####Get a List of All Available Books

```http
  GET /api/books
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `user/admin` | `string` | User's role (admin/user). |
`page`         |`number`  |Page number for pagination (optional).
`size`         |`number`  |Page size for pagination (optional).

#### Find Book by Its ID

```http
  GET /api/books/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Id of the book to fetch. |

#### Save Book to the Inventory

```http
  POST /api/books
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `title`      | `string` | Title of the book. |
| `author` | `string` | Author of the book. |
| `isbn` | `string` | Isbn of the book. |
| `price` | `number` |Price of the book. |
| `description` | `string` |Description of the book. |
| `coverImage` | `string` | Cover image the book. |
| `categoryIds` | `number[]` | List of category IDs. |

#### Update an Existing Book by ID

```http
  GET /api/books/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Id of the book to update. |

#### Delete a Book by Its ID

```http
  GET /api/books/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Id of the book to delete. |

### Authentication Controller

#### User Registration

```http
  POST /api/auth/register
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`	 | `string`	 |  User's email address.
| `password` | `string` | User's password. |
| `repeatPassword` | `string` | Repeat of the user's password.|
| `firstName` | `string` |User's first name. |
| `lastName` | `string` |User's last name. |
| `shippingAddress` | `string` | User's shipping address. |

#### User Login

```http
  POST /auth/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | User's email. |
| `password`      | `string` | User's password. |

### Category Controller

#### Get a List of All Available Categories

```http
   GET /api/categories
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Id of the book to delete. |
|`user/admin`	|`boolean`| User's role (admin/user).
|`page`	      |`number`|	Page number for pagination (optional).
|`size`	      |`number`|	Page size for pagination (optional).

#### Find Category by Its ID

```http
   GET /api/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Id of the category to fetch. |

#### Save Category

```http
   POST /api/categories
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`name` |	`string` |	 Name of the category.
|`description` |	`string` | Description of the category.

#### Update an Existing Category by ID

```http
  PUT /api/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` |  Id of the category to update. |

#### Delete a Category by Its ID

```http
  DELETE /api/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` |  Id of the category to delete. |

### Order Controller

#### Get All Orders

```http
   GET /api/orders
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`user`	| `boolean`	| User's role (user).

#### Create an Order

```http
  POST /api/orders
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`user`	| `boolean`	| User's role (user).
|`shippingAddress` |	`string` |Shipping address for the order.

#### Update Order Status

```http
  PATCH /api/orders/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` |  Id of the order to update. |

#### Get Order Items by Order ID

```http
   GET /api/orders/${orderId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `string` |  Id of the order to fetch items. |

#### Get a Certain Order Item by Order and Item ID

```http
    GET /api/orders/${orderId}/items/${itemId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `string` |  Id of the order to fetch items. |
|`itemId`	| `string`	| Id of the item to fetch within the order.

### Shopping Cart Controller

#### Add a Cart Item to Shopping Cart

```http
  POST /api/cart
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`user`	| `boolean`	| User's role (user).
|`bookId `	| `	number`	|	ID of the book to add to the cart.
|`quantity`	| `	number`	|	Quantity of the book to be added.

#### Get a Shopping Cart with Items

```http
   GET /api/cart
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`user`	| `boolean`	| User's role (user).

#### Delete a Cart Item from a Shopping Cart

```http
  DELETE /api/cart/cart-items/${cartItemId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`cartItemId`	| `string`|	Id of the cart item to be deleted.

#### Update a Quantity of Books in a Shopping Cart

```http
  PUT /api/cart/cart-items/${cartItemId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|`cartItemId`	| `string`|	Id of the cart item to be deleted.
|`quantity`	| `number`|	Updated quantity of the book.

## Project Structure
* **model**: Entity models representing the database schema
* **repository**: Spring Data JPA repositories for database operations
* **service**: Implementation of the business logic
* **controller**: Controllers containing endpoints with methods for handling HTTP requests
* **dto**: Data Transfer Objects used for communication between the client and server
* **mapper**: Mapper interfaces used for conversions between DTOs and entity models
## How to test the project from your side?
* Make sure to have JDK 17+, Docker and Postman installed
* Clone this repository
* Create the .env file with the corresponding variables
* Build images using _docker-compose build_ and run the service in containers using _docker-compose up_
## What was difficult about creating the project?
Creating the project presented several significant challenges, each requiring dedicated effort and perseverance. Understanding the entire architecture of the application was initially daunting, but through extensive research and valuable interactions with experienced professionals, I gradually gained insights and identified an appropriate structure.

One of the noteworthy challenges revolved around implementing Spring Security. Navigating the intricacies of security configurations demanded time and effort. However, with persistence and continuous learning, I successfully configured Spring Security to meet the project requirements.

Additionally, setting up Docker proved to be a substantial undertaking. The process demanded patience and meticulous attention to detail. Despite the initial complexity, I embraced the challenge and successfully established a Docker environment for the project.

In retrospect, while these challenges were demanding, the overall experience of creating this project was immensely rewarding. I look forward to applying the knowledge gained and tackling more complex projects in the future.
## What is next?
While the current state of the project may suggest its completion, I have further plans to enhance its functionality. One crucial addition is implementing a return feature, providing customers with the ability to return goods—an essential aspect for any online store.

In addition to backend improvements, I am considering the integration of a front-end component and enhancing the overall design. Improving the user interface will contribute to a more user-friendly experience.

Stay tuned for updates on this project! Feel free to follow for the latest developments and improvements. Your feedback and suggestions are always appreciated.