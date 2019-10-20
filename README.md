# Parking Lot

Java project built with Maven to demonstrate object oriented design principles.

## Building

Run `mvn package`

## Executing

Run `java -cp target/parking-1.0-SNAPSHOT.jar com.torrecampo.app.App [input text file name here]`

All input text files should be placed in the root of the project.

## Expected outputs

input1.txt - Shows that cars will be placed in queue if the lot is full. If it is full and a car leaves, the first in the queue will take over it's spot.

input2.txt - Shows that all cars enter the parking lot without having to worry about waiting because if a larger capacity.

input3.txt - Shows that if a car doesn't leave, the proft will be lower since the car hasn't paid.

input4.txt - Shows that if the lot is full and the cars inside don't leave, the cars in the queue never enter. Also since no car left, the lot made \$0 at the time of calcuation.

input5.txt - Shows that a car without a duration will cause the program to exit. It needs the duration to calculate the ticket cost.
