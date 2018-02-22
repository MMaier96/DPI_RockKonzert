package application.drones.subdrones;

import application.architecture.LocationType;
import application.drones.Drone;

public class IndoorDrone extends Drone {
	private LocationType type;
	
	public IndoorDrone() {
		super();
		this.type = LocationType.INDOOR;
	}

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}
}
