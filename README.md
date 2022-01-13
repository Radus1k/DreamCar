# DreamCar
Java Spring Boot Project who manage an inverse auction for a Car Company using API Calls.

DreamCar is a Inverse Auction company. The admin creates Auctions, and some offertants create offers for specific auctions. The auction ends when the target price defined is reached, or when the expire date occurs.

The admin user is already defined in the configuration.

Users can create accounts, create offers and edit them by sending  HTTP Requests to the server. ( API Calls)

Users are notified on Email to confirm the account. Also, they are notified when they won the auction.

Server is configured to use `MailDev` in order to simulate a mail server. More info here about MailDev: https://github.com/maildev/maildev

# REST API

The REST API to the DreamCar App is described  in `DreamCarSwagger.html` file in the main tree.



