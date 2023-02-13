# clientes-apirest

This project is a backend __@RestController__ aplication created with a spring-boot version 3.0.2.

Frontend Aplication is: [ClientesApp](https://github.com/cardavid7/clientesApp)

## Database 

The database engine used is MySQL and initially the database named "db_clientes_apirest" must be created. Then when the spring application is executed, the table will be created with their respective fields and information with some clients.

You can see the application.properties and import.sql files for more information on clientes-apirest\src\main\resources

## Api REST service response

The service returns a modified json response with 3 fields data, code and message.

* Data: Returns the requested information, it can be a customer, null, or a list of customers

* Code: Returns the transaction code, it can be OK, ERROR or DATA_NOT_FOUND

* Message: Returns the message in the transaction, it can be OK or a custom message

Example:

* Response of one cliente

```
{
    "data": {
        "id": 1,
        "nombres": "Carlos David",
        "apellidos": "Serrano",
        "email": "carlos@gmail.com",
        "createAt": "2023-01-28"
    },
    "code": "OK",
    "message": "OK"
}
```

* Response of one client that not found in database

```
{
    "data": null,
    "code": "DATA_NOT_FOUND",
    "message": "Datos no encontrados"
}
```


## Api REST service methods

For CRUD methods the standard GET, POST, PUT and DELETE verbs are used. You can try the methods in postman


* __GET__ request to http://localhost:8080/api/clientes/responseEntity/getAllClientes --> returns a list of clients
* __GET__ request to http://localhost:8080/api/clientes/responseEntity/getClienteById/1 --> return a client by id
* __POST__ request to http://localhost:8080/api/clientes/responseEntity/createCliente --> create a client

select content-type as ‘application/json’ and Content-Length as ‘ <calculated when request is sent> ’ and  this content body
```
{
    "nombres": "Eduardo",
    "apellidos": "Jimenez",
    "email": "eduardo.jimenez@gmail.com"
}
```

* __PUT__ request to http://localhost:8080/api/clientes/responseEntity/editClienteById/1 --> edit a client

select content-type as ‘application/json’ and Content-Length as ‘ <calculated when request is sent> ’ and  this content body
```
{
    "nombres": "Carlos Alberto",
    "apellidos": "Serrano",
    "email": "carlos@gmail.com"
}
```

* __DELETE__ request to http://localhost:8080/api/clientes/responseEntity/deleteCliente/1 --> delete a client

