package application.drones.subdrones;

import java.util.ArrayList;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.drones.Drone;

public class OutdoorDrone extends Drone {

	private LocationType type;
	private ArrayList<IArea> outdoorAreas;
	private int areaIndex;

	public OutdoorDrone(int id, ArrayList<IArea> outdoorAreas) {
		super(id);
		this.outdoorAreas = outdoorAreas;
		this.type = LocationType.OUTDOOR;
	}

	@Override
	public int getCurrentAreaIndex() {
		return areaIndex;
	}

	public ArrayList<IArea> getOutdoorAreas() {
		return outdoorAreas;
	}

	@Override
	public LocationType getType() {
		return type;
	}

	@Override
	public void setCurrentAreaIndex(int index) {
		areaIndex = index;

	}

	public void setOutdoorAreas(ArrayList<IArea> outdoorAreas) {
		this.outdoorAreas = outdoorAreas;
	}

	@Override
	public void setType(LocationType type) {
		this.type = type;
	}
}
