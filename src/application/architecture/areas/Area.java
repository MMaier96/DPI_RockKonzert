package application.architecture.areas;

import java.util.ArrayList;

import application.architecture.sectors.ISector;

public abstract class Area implements IArea {

	protected ArrayList<ISector> sectors;
	protected String name;

	
	public Area(String name) {
		sectors = new ArrayList<ISector>();
		this.name = name;
	}
	
	
	@Override
	public void addSector(ISector sector) {
		sectors.add(sector);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ISector getSectorByName(String name) {
		for (ISector iSector : sectors) {
			if (iSector.getSectorName().equals(name)) {
				return iSector;
			}
		}
		return null;
	}

	@Override
	public void removeSector(ISector sector) {
		if (sectors.contains(sector)) {
			sectors.remove(sector);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
