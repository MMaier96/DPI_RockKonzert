package application.architecture;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;
import application.persons.IConcertMediator;

public interface ILocation {

	void addArea(IArea area);

	void addEntrance(Entrance entrance);

	void addWaitingArea(IWaitingArea waitingArea);

	IArea getAreaByName(String areaName);

	int getCarWaitingAreasSize();

	Entrance getEntranceByName(String entranceName);

	ArrayList<IArea> getIndoorAreas();

	String getLocationName();

	ArrayList<IArea> getOutdoorAreas();

	int getPublicTransportationWaitingAreasSize();

	IWaitingArea getWaitingAreaByIndex(int index);

	void registerDisplays();

	void removeArea(IArea area);

	void removeEntrance(Entrance entrance);

	void removeWaitingArea(IWaitingArea waitingArea);

	void resetWaitingAreas();

	void setEventManager(IConcertMediator eventManager);

	void setLocationName(String name);
}
