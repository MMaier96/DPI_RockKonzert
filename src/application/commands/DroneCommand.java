package application.commands;

import application.drones.IDrone;

public abstract class DroneCommand implements IDroneCommand{
	
	protected IDrone drone;

	@Override
	public IDrone getDrone() {
		return drone;
	}

	@Override
	public void setDrone(IDrone drone) {
		this.drone = drone;
	}
	
	
}
