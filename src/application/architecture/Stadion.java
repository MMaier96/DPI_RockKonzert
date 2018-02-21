package application.architecture;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;

public class Stadion implements ILocation {

	private ArrayList<IArea> areas;
	private ArrayList<Entrance> entrances;
	private ArrayList<IWaitingArea> waitingAreas;
	private String locationName;

	public Stadion() {
		areas = new ArrayList<IArea>();
		entrances = new ArrayList<Entrance>();
		waitingAreas = new ArrayList<IWaitingArea>();
	}

	@Override
	public void addArea(IArea area) {
		areas.add(area);
	}

	@Override
	public void addEntrance(Entrance entrance) {
		entrances.add(entrance);
	}

	@Override
	public void addWaitingArea(IWaitingArea waitingArea) {
		waitingAreas.add(waitingArea);
	}

	@Override
	public IArea getAreaByName(String areaName) {
		for (IArea iArea : areas) {
			if (iArea.getName().equals(areaName)) {
				return iArea;
			}
		}
		return null;
	}

	@Override
	public Entrance getEntranceByName(String entranceName) {
		for (Entrance iEntrance : entrances) {
			if (iEntrance.getName().equals(entranceName)) {
				return iEntrance;
			}
		}
		return null;
	}

	@Override
	public String getLocationName() {
		return locationName;
	}

	@Override
	public void removeArea(IArea area) {
		if (areas.contains(area)) {
			areas.remove(area);
		}
	}

	@Override
	public void removeEntrance(Entrance entrance) {
		if (entrances.contains(entrance)) {
			entrances.remove(entrance);
		}
	}

	@Override
	public void removeWaitingArea(IWaitingArea waitingArea) {
		if (waitingAreas.contains(waitingArea)) {
			waitingAreas.remove(waitingArea);
		}
	}

	@Override
	public void setLocationName(String name) {
		locationName = name;
	}

}
