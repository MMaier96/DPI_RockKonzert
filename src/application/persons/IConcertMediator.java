package application.persons;

import application.commands.IDroneCommand;
import application.drones.IDrone;

public interface IConcertMediator {

		void registerDrone(IDrone drone);
		void setCommand(IDroneCommand command);
		void executeActualCommand();
		
		void addConcertMediatorListener(IConcertMediatorListener listener);
		void removeConcertMediatorListener(IConcertMediatorListener listener);
		void notifyListeners();
		void startManaging();
}
