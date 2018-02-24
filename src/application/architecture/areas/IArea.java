package application.architecture.areas;

import application.architecture.sectors.ISector;

public interface IArea {

	void addSector(ISector sector);

	void createSectors();

	String getName();

	ISector getSectorByIndex(int index);

	ISector getSectorByName(String name);

	void removeSector(ISector sector);
}
