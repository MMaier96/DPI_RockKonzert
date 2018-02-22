package application.architecture.areas;

import application.architecture.sectors.ISector;

public interface IArea {

	void addSector(ISector sector);
	
	String getName();
	ISector getSectorByName(String name);
	
	void removeSector(ISector sector);

	void createSectors();
}
