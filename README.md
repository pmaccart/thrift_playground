thrift_playground
=================

Playground for with Apache Thrift framework

Vagrant file provided for installing baseline packages on a Ubuntu distro for supporting installation of the Thrift compiler.

To run the server:

``` shell
cd server
gradle run
```

To run the client:
``` shell
cd client_nodejs
npm install
node server.js
```
