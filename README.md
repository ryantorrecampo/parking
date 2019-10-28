# Parking Lot

Java project built with Maven to demonstrate object oriented design principles.

## Building

Run `mvn package`

## Executing

Run `java -cp target/parking-1.0-SNAPSHOT.jar com.torrecampo.app.App [input text file name here]`

All input text files should be placed in the root of the project.

## Input format

Group:[Origin]|[Lot]#[Capacity]\$[cost]%[Discount]

Enters:[Car ID],[Duration]

Exits: [Car ID]

### Example

Group:Asian|A#20\$.10\%.10

Group:American|B#4\$.15\%.15

Enters:Acura,53

Enters:Lexus,34

Enters:Honda,92

Exits:Acura

Exits:Honda

Enters:Ford,45

Exits:Lexus

Enters:Chevy,79

Exits:Ford

Exits:Chevy

## Expected outputs

input1.txt - Shows that a lot never has any cars enter it because the other lots have less available parking spots.

input2.txt - Shows that a car can only be in one place at a time.

input3.txt - Shows that if a car tries to exit even if it already has, nothing will happen since the car cannot be found in any of the parking lots.

input4.txt - Shows that a car will wait when both lots are fully occupied.

input5.txt - Shows an example of a "free garage" where cars of any type can park without being charged.
