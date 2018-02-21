package application.architecture.areas.subareas;

import application.architecture.LocationType;
import application.architecture.areas.Area;
import application.architecture.sectors.subsectors.OutdoorSector;

public class OutdoorArea extends Area{

	private LocationType locationType;
	public OutdoorArea(String name) {
		super(name);
		this.locationType = LocationType.OUTDOOR;
		createSectors();
	}

	private void createSectors() {
		for (int i = 1; i <= 5; i++) {
			sectors.add(new OutdoorSector(this,i));
		}
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}
}
