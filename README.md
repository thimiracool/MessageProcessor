# Message Processor

## Prerequisites
Java 8
Junit 1.4 or above

## Authors

* **Thimira Darshana Upatissa** -  - [Thimira Upatissa](https://github.com/thimiracool)

## Project Structure

Main Source : Main source is avaible under src/main location.
Logs        : Reports will be generated under /src/main/logs location.
Simulator   : Test simulator available under src/test/simulator location.
Unit Tests  : Unit tests are under test/processor location.

## Developer notes
### Message Types
        Record     - sales request.
        Bulk       - bulk sales requests.
        Adjustment - adjustment for sales requests.

### Adjustment Operations
        ADD, SUBTRACT, MULTIPLY
