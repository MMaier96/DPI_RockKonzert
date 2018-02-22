package application.drones.subdrones;

import application.architecture.LocationType;
import application.drones.Drone;

public class OutdoorDrone extends Drone{

private LocationType type;
	
	public OutdoorDrone() {
		super();
		this.type = LocationType.OUTDOOR;
	}

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}
}
