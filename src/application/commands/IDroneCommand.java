package application.commands;

import application.drones.IDrone;

public interface IDroneCommand {

	void execute();
	IDrone getDrone();
	void setDrone(IDrone drone);

}
