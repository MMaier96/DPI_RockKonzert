package application.architecture.areas.subareas;

import static application.logger.Logger.printInfo;

import application.architecture.LocationType;
import application.architecture.areas.Area;
import application.architecture.sectors.subsectors.OutdoorSector;
import application.config.AppConfig;

public class OutdoorArea extends Area {

	private LocationType locationType;

	public OutdoorArea(String name) {
		super(name);
		this.locationType = LocationType.OUTDOOR;
	}

	@Override
	public void createSectors() {
		printInfo("creating sector for OutdoorArea: " + name);
		for (int i = 1; i <= AppConfig.instance.amountOutdoorSectorsPerArea; i++) {
			sectors.add(new OutdoorSector(this, i));
		}
		printInfo(sectors.size() + " sectors were created for OutdoorArea: " + name + " " + sectors.toString());
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType type) {
		this.locationType = type;
	}
}
