# Fakestore API in Spring Boot java

This is a simple Spring Boot application that returns a list of products from a JSON file. 
I made this to learn Spring Boot and to use it as a backend for testing with my home server. 
It has the same functions as my [Node-Api-Server](https://github.com/knottem/Node-Api-Server) which was written in Node.js.
Wrote the node server when [fakestoreapi.com](https://fakestoreapi.com) was down and I needed a backend for school project, so now I decided to make the java version.

## Run the server

Download the jar file from the [releases](https://github.com/knottem/fakestoreapi/releases)

You need to have java installed on your computer. 
Then you can run the server with the following command in the terminal/cmd when you are in the same directory as the jar file:

```bash
java -jar fakestoreapi-0.1.0.jar
```
the server will run on port 3000.

so you can access the products with the following url:

```bash
http://localhost:3000/products
```

To kill the server press `ctrl + c` in the terminal or if you accidently started the jar not in the terminal you can

open terminal/cmd and type:

```bash
jps
```
to find the process id of the server and then type:

```bash
taskkill -f /PID <process id>
```

## Test the server

You can test the server with [Postman](https://www.postman.com/).

or you can just open the url in your browser, it will return a json file.

