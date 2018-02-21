package application.architecture.sectors.subsectors;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.architecture.sectors.Sector;

public class IndoorSector extends Sector {

	private LocationType locationType;
	
	public IndoorSector(IArea area, int id) {
		super(1000, area, id);
		this.locationType = LocationType.INDOOR;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}
	
	

}
