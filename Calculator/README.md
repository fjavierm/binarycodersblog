# Calculator project

A simple REST API to perform different additions.

URLs:
http://<server>:<port>/Calculator/resources/addition/exone (@GET)
http://<server>:<port>/Calculator/resources/addition/extwo (@GET)
http://<server>:<port>/Calculator/resources/addition/exthree (@GET)

Call example for /exone:
curl -i -H "Content-Type: application/json" -X GET -d '{"identifier":"1","data": {"firstOperand":1, "secondOperand":2}}' http://localhost:8080/Calculator/resources/addition/exone

Expected result:
{"identifier":"1", "result":3}

addition/exone   -> Basic REST method. It can receive one object.
addition/extwo   -> Asynchronous REST method. It can receive a list of objects.
addition/exthree -> Asynchronous REST method with cache support. It can receive a list of objects.