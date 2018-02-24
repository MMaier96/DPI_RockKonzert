package application.persons;

import application.commands.IDroneCommand;
import application.drones.IDrone;

public interface IConcertMediator {

	void addConcertMediatorListener(IConcertMediatorListener listener);

	void executeActualCommand();

	void notifyListeners();

	void registerDrone(IDrone drone);

	void removeConcertMediatorListener(IConcertMediatorListener listener);

	void setCommand(IDroneCommand command);

	void startManaging();
}
