ODSS-TREX Planning Editor

This Scalatra project is the service for the planning component in the ODSS

Very preliminary; it is a prototype separated from the main ODSS codebase.

* Development:
$ ./sbt
> container:start
> ~ ;copy-resources;aux-compile
> container:start

* Creating the WAR file
$ ./sbt package



index3.html: obsolete

index4.html: a token is composed of 4 blocks: background, start, body, end

index4_1.html: refactoring of index4.html to separate timeline widget
functionality

index5.html:
- a token is again only one block

TODO:

- make all tokens be fully formatted UTC, so add "Z" to the ones coming
  from T-rex. ONLY convert to local for purposes of the base timeline widget.

- code clean up!!

