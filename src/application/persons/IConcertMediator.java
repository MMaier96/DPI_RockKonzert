package application.persons;

import application.architecture.visualdisplay.IVisualDisplayListener;
import application.commands.IDroneCommand;
import application.drones.IDrone;

public interface IConcertMediator {

		void registerDrone(IDrone drone);
		void setCommand(IDroneCommand command);
		void executeActualCommand();
		
		void addVisualDisplayListener(IVisualDisplayListener listener);
		void removeVisualDisplayListener(IVisualDisplayListener listener);
		void notifyListeners();
}
