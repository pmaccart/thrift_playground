Apache Thrift Playground
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

The Thrift interface definitions can be found in ``tools/thrift/defs``.  To compile the definitions into language-specific
bindings, you'll need to install the Thrift compiler, plus any language-specific dependencies (for Java, these include a
1.5 JDK and Apache Ant).  See http://thrift.apache.org/docs/install/ for complete installation steps.

The ``Vagrantfile`` sitting at the root of the volume provides an Ubuntu instance with the necessary Thrift dependencies
installed.  There seems to be issues with the 0.9.1 tag of the Thrift repository, so I'd recommend using the 0.9.0
release instead.

Once the Thrift compiler has been installed, the interface definitions can be compiled with the following:
```
cd tools/thrift/defs
thrift -gen java forecast.thrift
thrift -gen js:node forecast.thrift
```

The language-specific stubs can then be copied to the appropriate location within the repository (i.e.
``server/src/main/java/com/pmaccart/thrift/model`` for the Java objects).
