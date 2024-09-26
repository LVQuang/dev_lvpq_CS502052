# Specifications

## GENERAL DESCRIPTION

The CRM E-commerce Shoe System will manage purchasing, sales processes, order tracking, and customer retention. The system focuses on optimizing inventory management, sales processes, and improving customer experience through customer relationship management (CRM).

## FUNCTIONAL REQUIREMENTS

### 2.1 Purchasing Process

- **Inventory Management:** Track the quantity of products in stock in real-time.
- **Restocking Alerts:** Automatically alert when product quantities reach a minimum stock threshold.
- **Admin Restocking Assistance:** Suggest to the admin the required budget for restocking and create purchase orders.

### 2.2 Selling Process

- **Product Search:** Users can search for products based on keywords.
- **Shopping Cart:** Display the quantity and total value of products in the cart in real-time.
- **Payment:** Support multiple payment methods (bank transfer, e-wallet, credit card).
- **Order Management:** Customers can track the status of their orders after payment.

### 2.3 Package Tracking and Logistics

- **Awaiting Confirmation:** Customers confirm their orders.
- **Packaging:** Admin receives the packaging request.
- **Shipping:** Admin updates the order status during shipping.
- **Successful Delivery:** The system receives feedback from the customer once the order is successfully delivered.

> **Note**  
> The system must notify both the customer and the admin about the status at each stage of the shipping process.

### 2.4 Customer Relationship Management (CRM)

- **Customer Search:** Allows searching for customers through email or personal information.
- **Closing Sales:** Helps the admin to close deals with potential customers based on their purchase history.
- **Customer Retention:**
    - Based on customer loyalty points (evaluated through purchase value and frequency).
    - Store customers' purchase history.
    - Provide special offers or discounts to loyal customers.

## NON-FUNCTIONAL REQUIREMENTS

### 3.1 Security

- The system must use HTTPS encryption to protect data.
- Two-factor authentication (2FA) for admin to ensure login security.
- User and admin passwords must be securely encrypted and stored in the database.

### 3.2 Performance

- The system must handle a large number of users without interruptions, especially during peak shopping periods.
- The system's response time (page load time, product search, payment processing) must be fast and smooth.

### 3.3 Scalability

- The system must be flexible and easily scalable, allowing upgrades to handle increased customer volumes or new features without impacting current operations.

### 3.4 Integration

- The system should be able to integrate with third-party services, such as shipping management systems or online payment services (PayPal, MoMo).

### 3.5 User Interface

- The interface must be user-friendly and easy to navigate, especially for customers and admins.
- Support for multiple languages to cater to customers from different countries.

### 3.6 Data Backup and Recovery

- System data must be backed up regularly, and there should be a recovery mechanism in place in case of any system failure.
y