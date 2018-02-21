 # Rockconcert
 ## Used Design Patterns
 ### 1. Mediator (Example)
 The mediator design pattern implements the command design pattern. The mediator coordinates the behaviour of the registered objects. In this example the ADCMediator send a status to the runway or the flight, if they grants permission to land.
 ![Mediator UML](https://i.imgur.com/gN7Fp38.png)
 
 ### 2. Observer  (Example)
 An observer design pattern is used to register one or multiple objects to an observer which will be notified if an object triggers an observed event. In this example a fire department will be notified if a detector calls the falseAlarm() method
 ![Observer UML](https://i.imgur.com/kklq8PH.png)
 
 ### 3. Command  (Example)
 The command design pattern is used to control an object with multiple commands. In this example a light will be controlled by the RemoteControl. In this case there are two types of commands (LightsOff/LightsOn) objects which implements the ICommand interface. The command can be assigned to the RemoteControl to execute the command and control the light with it.
 ![Command UML](https://i.imgur.com/ARciRX8.png)
 
 
 ## Task
 
 > A band is holding a concert in a stadion. The concert is sold out with 70.000 tickets.
 > Every participant got a ticket. On each ticket got an related *area* and a *sector* on it.
 >
 > Before simulating a CSV file will be created, where each ticket get assigned to a participant.
 >
 > The stadion is separated into 17 areas *(1 indoor <A>/ 16 outdoor <B ... Q>)*.
 > Because of security aspects each area is seperated into different sectors
 > * Indoor: (each 1000 participants) 
 >   * A01 ... A10
 > * Outdoor: (each 750 participants)
 >   * B01 ... B05
 >   * ...
 >   * Q01 ... Q05
 >
 >  The simulation will iterate over 3 phases:
 >  * Phase 1:
 >	  * indoor -> 20% capacity
 >	  * outdoor -> 50% capacity
 >  * Phase 2:
 >	  * indoor -> 70% capacity
 >	  * outdoor -> 80% capacity
 >  * Phase 3:
 >	  * indoor -> 100% capacity
 >	  * outdoor -> 100% capacity
 >
 > 80% of the participants are coming by public transportation and 20% by car.
 > For each of those two parties there are waiting areas.
 >  * public transportation: 8 waiting areas
 >  * car: 2 waiting areas
 >
 > The start of the simulation the CSV file, with the assigned tickets to the participants, will be splitted into 3 different CSV files for each phase.
 > Each CSV file should match the capacity of the corresponding phase and all sectors in the area should be distributed equally.
 >
 > The stadion got 6 entrances:
 > * 1: A
 > * 2: B-D
 > * 3: E-G
 > * 4: H-K
 > * 5: L-O
 > * 5: P-Q
 >
 > For each sector the place number is incremented from the front-left to the back-right.
 > Each sector (from first to last) for in- & outdoor areas will be called out with the empty seats.
 > After each phase drones will fly over the areas/sectors to search for empty seats:
 > * indoor: 1 drone
 > * outdoor: 4 drones
 >
 > The drones are registered by a central mediator and are controlled by commands which are send out by the mediator.
 > * Commands: 
 >   * DEPART
 >   * COLLECT -> collecting all empty seats (start-duration: 500ms)
 >   * NEXT -> the next area will be observed
 >   * LAND
 >
 > The mediator observe the drones continuously. If the seats are collected, the next phase will start.
 > The collection of the seat numbers will be grouped if more than 3 empty seats are in a row.
 > Example: A01[1,5,20-27,66-72, ...]
 
  ## UML Diagram
  ![UML Diagram RockConcert](https://i.imgur.com/5GDDEem.png)
  
  ## Intention
  This project was created as an individual work for a testat of the lecture software engeneering #2.