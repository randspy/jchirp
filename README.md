jchirp
======

Twitter console simulator. My interpretation of Clean Architecture by Uncle Bob.

## Usage

To run this application you need to execute

    gradle install
    ./build/install/jchirp/bin/jchirp


Possible operations

    posting: <user name> -> <message>
    reading: <user name>
    following: <user name> follows <another user>
    wall: <user name> wall

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

    Following: Charlie can subscribe to Alice’s and Bob’s timelines,
    and view an aggregated list of all subscriptions

    > Charlie -> I'm in New York today! Anyone want to have a coffee?
    > Charlie follows Alice
    > Charlie wall
    Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
    Alice - I love the weather today (5 minutes ago)

    > Charlie follows Bob
    > Charlie wall
    Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
    Bob - Good game though. (1 minute ago)
    Bob - Damn! We lost! (2 minutes ago)
    Alice - I love the weather today (5 minutes ago)

## License

Copyright (C) 2014 Przemyslaw Koziel

Distributed under The MIT License.