# DreamCar

# !! PROJECT MADE FOR EDUCATIONAL/LEARNING PURPOSES !!


Java Spring Boot Project who manage an inverse auction for a Car Company using API Calls.

DreamCar is a Inverse Auction company. The admin creates Auctions, and some offertants create offers for specific auctions. The auction ends when the target price defined is reached, or when the expire date occurs.

The admin user is already defined in the configuration.

Users can create accounts, create offers and edit them by sending  HTTP Requests to the server. ( API Calls)

Users are notified on Email to confirm the account. Also, they are notified when they won the auction.

Server is configured to use `MailDev` in order to simulate a mail server. More info here about MailDev: https://github.com/maildev/maildev

# REST API

The REST API to the DreamCar App is described below. Also, a postman collection with all following examples is included in the project.

#### Registration

### Request

POST /http://localhost:8080/dreamcar/registration/

Content-Type: application/json

{
"firstName" :"Nume",
"lastName": "PrenumeTest",
"email": "test@t.com",
"password": "1234",
"company_name":"Altex"
}

### Response 

HTTP/1.1 200 OK
Date: Thu, 24 Feb 2011 12:36:31 GMT
Status: 200 OK
Connection: close
Content-Type: application/json
Content-Length: 74

477861be-be41-4b29-8241-50cf681ec5d1

Token is used to generate the confirm link button . 



