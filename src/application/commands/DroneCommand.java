package application.commands;

import application.drones.IDrone;

public abstract class DroneCommand implements IDroneCommand{
	
	protected IDrone drone;

	public IDrone getDrone() {
		return drone;
	}

	public void setDrone(IDrone drone) {
		this.drone = drone;
	}
	
	
}
