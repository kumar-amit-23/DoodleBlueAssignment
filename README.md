# DoodleBlueAssignment

1. User Service API's : -
    - To Register a User send a POST request to http://localhost:9090/api/user/register with response body of User type
      ```
      {
        "name" : "amit",
        "email" : "kumaramit97456501@gmail.com",
        "password" : "Amit123#asd",
        "gender" : "Male",
        "Age" : 22
      }
      ```

    - To login send a POST request to http://localhost:9090/api/user/login with response body of User type
        ```
      {
        "email" : "kumaramit97456501@gmail.com",
        "password" : "Amit123#asd",
      }
      ```
      
2. Product Microservice API's :-
    - first Send a POST request to http://localhost:9100/order to save dummy data values in the table.
    - To get Order by user id send a POST Request to http://localhost:9100/order/1 with response body .
    ```
      {
          "orderId" : 1,
          "userId" : 1,
          "quantity" : 80,
          "productId" : 1
      }
      ```
    - To check stock by productId send a GET request to http://localhost:9100/product/1.
    - To Get all orders by userId send a GET request to http://localhost:9100/order/1.
