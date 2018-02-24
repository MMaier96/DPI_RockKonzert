package application.drones.subdrones;

import java.util.ArrayList;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.drones.Drone;

public class IndoorDrone extends Drone {
	private LocationType type;
	private ArrayList<IArea> indoorAreas;
	private int areaIndex;

	public IndoorDrone(int id, ArrayList<IArea> indoorAreas) {
		super(id);
		this.indoorAreas = indoorAreas;
		this.type = LocationType.INDOOR;
	}

	@Override
	public LocationType getType() {
		return type;
	}

	@Override
	public void setType(LocationType type) {
		this.type = type;
	}

	@Override
	public void setCurrentAreaIndex(int index) {
		areaIndex = index;
		
	}

	@Override
	public int getCurrentAreaIndex() {
		return areaIndex;
	}

	public ArrayList<IArea> getIndoorAreas() {
		return indoorAreas;
	}

	public void setIndoorAreas(ArrayList<IArea> indoorAreas) {
		this.indoorAreas = indoorAreas;
	}
}
