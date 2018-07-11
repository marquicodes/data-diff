# Base64 data comparator

A Java web-app that makes based64 enconded Json comparisons.


### Description:

- Provides two http endpoints that accept JSON base64 encoded binary data on both endpoints:
  - <host>/v1/diff/<id>/left
  - <host>/v1/diff/<id>/right
- Provided data are diff-ed and results are available on the third endpoint:
  - <host>/v1/diff/<id>
- Results provide the following information in JSON format:
  - Data are equal,
  - Data have the same size but have differeces, and
  - Data differ on size.


## How to start the server

Clone the [repository](https://github.com/marquicodes/data-diff.git) into a directory.

```sh
$ cd data-diff
$ mvn spring-boot:run
```

## In-memory database (H2)

Application uses an in-memory database to persist the uploaded data. Each time server stops, data stored into this database is getting lost.

If you want to take a look of the database, you are free to navigate to [http://localhost:8080/h2](http://localhost:8080/h2) and fill-in the following info.

```sh
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:diff-db
User Name: sa
Password: <leave it blank>
```


## How to run tests

To run the tests using Maven type the command:

```sh
$ cd data-diff
$ mvn test
```

## Rest API

There are three endpoints that you can call.

| Title | URL | Method | Params | Data Params
| ------ | ------ | ------ | ------ | ------ |
| Upload left-side data | **host**:**port**/v1/diff/**id**/left | POST | Required: id=[integer] | data=[string] |
| Upload right-side data |**host**:**port**/v1/diff/**id**/right | POST | Required: id=[integer] | data=[string] |
| Diff Result |**host**:**port**/v1/diff/**id** | GET | Required: id=[integer] |


Examples:

##### Request
```sh
method: POST
url: http://localhost:8080/v1/diff/1/left
body: bGVmdCBzaWRlIGRhdGE=
```

##### Response
```sh
Status: 200 OK
body:
{
  "status": "OK",
  "timestamp": "11-07-2018 09:10:12",
  "message": "Success"
}
```

##### Request
```sh
method: GET
url: http://localhost:8080/v1/diff/1
```

##### Response
```sh
Status: 200 OK
body:
{
  "status": "OK",
  "timestamp": "11-07-2018 09:10:36",
  "message": "Data have different length."
}
```
