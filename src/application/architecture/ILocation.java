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
	Entrance getEntranceByName(String entranceName);
	String getLocationName();
	IWaitingArea getWaitingAreaByIndex(int index);
	
	void removeArea(IArea area);
	void removeEntrance(Entrance entrance);
	void removeWaitingArea(IWaitingArea waitingArea);
	
	void setLocationName(String name);
	int getCarWaitingAreasSize();
	int getPublicTransportationWaitingAreasSize();
	
	ArrayList<IArea> getIndoorAreas();
	ArrayList<IArea> getOutdoorAreas();
	void setEventManager(IConcertMediator eventManager);
	void registerDisplays();
	void resetWaitingAreas();
}
