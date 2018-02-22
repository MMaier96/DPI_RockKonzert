package application.architecture.areas.subareas;

import application.architecture.LocationType;
import application.architecture.areas.Area;
import application.architecture.sectors.subsectors.IndoorSector;

import static application.logger.Logger.*;

public class IndoorArea extends Area{

	private LocationType locationType;
	
	public IndoorArea(String name) {
		super(name);
		this.locationType = LocationType.INDOOR;
	}

	@Override
	public void createSectors() {
		printInfo("creating sector for IndoorArea: " + name);
		for (int i = 1; i <= 10; i++) {
			sectors.add(new IndoorSector(this,i));
		}
		printInfo(sectors.size() + " sectors were created for IndoorArea: " + name + " " + sectors.toString());
		
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}

}
