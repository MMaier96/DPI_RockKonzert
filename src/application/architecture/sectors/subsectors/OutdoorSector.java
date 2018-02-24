package application.architecture.sectors.subsectors;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.architecture.sectors.Sector;

public class OutdoorSector extends Sector {

	private LocationType locationType;

	public OutdoorSector(IArea area, int id) {
		super(750, area, id);
		this.locationType = LocationType.OUTDOOR;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}
}
