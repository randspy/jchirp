jchirp
======

Twitter console simulator. My interpretation of Clean Architecture by Uncle Bob.

## Usage

To run this application you need to execute

    mvn install
    java -jar target/jchirp-0.0.1-jar-with-dependencies.jar


Possible operations

    posting: <user name> -> <message>
    reading: <user name>

    exit: exit

Examples

    Posting: Alice can publish messages to a personal timeline

    > Alice -> I love the weather today
    > Bob -> Damn! We lost!
    > Bob -> Good game though.

    Reading: Bob can view Alice’s timeline

    > Alice
    I love the weather today (5 minutes ago)
    > Bob
    Good game though. (1 minute ago)
    Damn! We lost! (2 minutes ago)

## TODO

Following and wall usecases.

## License

Copyright (C) 2014 Przemyslaw Koziel

Distributed under The MIT License.