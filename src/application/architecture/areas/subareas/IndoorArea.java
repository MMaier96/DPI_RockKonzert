package application.architecture.areas.subareas;

import application.architecture.LocationType;
import application.architecture.areas.Area;
import application.architecture.sectors.subsectors.IndoorSector;

public class IndoorArea extends Area{

	private LocationType locationType;
	
	public IndoorArea(String name) {
		super(name);
		this.locationType = LocationType.INDOOR;
		createSectors();
	}

	private void createSectors() {
		for (int i = 1; i <= 10; i++) {
			sectors.add(new IndoorSector(this,i));
		}
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}

}
