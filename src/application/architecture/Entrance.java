package application.architecture;

import java.util.ArrayList;

import application.architecture.areas.IArea;

public class Entrance {

	private String name;
	private ArrayList<IArea> entranceToAreas;

	public Entrance(String name, IArea... areas) {
		this.name = name;
		this.entranceToAreas = new ArrayList<IArea>();
		for (IArea iArea : areas) {
			entranceToAreas.add(iArea);
		}
	}

	public void addAreaToEntrance(IArea area) {
		entranceToAreas.add(area);
	}

	public ArrayList<IArea> getEntranceToAreas() {
		return entranceToAreas;
	}

	public String getName() {
		return name;
	}

	public boolean isAreaReachable(IArea area) {
		return entranceToAreas.contains(area);
	}

	public void removeAreaToEntrance(IArea area) {
		if (entranceToAreas.contains(area)) {
			entranceToAreas.remove(area);
		}
	}

	public void setEntranceToAreas(ArrayList<IArea> entranceToAreas) {
		this.entranceToAreas = entranceToAreas;
	}

	public void setName(String name) {
		this.name = name;
	}

}
