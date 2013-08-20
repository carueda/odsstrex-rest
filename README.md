ODSS-TREX Planning Editor supporting REST service

This Scalatra project is the service for the T-REX planning component in the ODSS

Very preliminary; it is a prototype separated from the main ODSS codebase.

## Build & Run ##


```shell
$ cp odsstrex.conf.template odsstrex.conf
$ vi odsstrex.conf  # to fill in the application configuration (T-REX endpoint URL)
$ sudo ln -s `pwd`/odsstrex.conf /etc/  # or edit web.xml to indicate a different config file location
$ ./sbt
> container:start
> ~ ;copy-resources;aux-compile
> container:start
> browse
```

* Creating the WAR file

```
$ ./sbt package
```

TODO:

- make all tokens be fully formatted UTC, so add "Z" to the ones coming
  from T-rex. ONLY convert to local for purposes of the base timeline widget.
- swaggerize

- code clean up!!
