package application.architecture;

import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;

public interface ILocation {
	
	void addArea(IArea area);
	void addEntrance(Entrance entrance);
	void addWaitingArea(IWaitingArea waitingArea);
	
	IArea getAreaByName(String areaName);
	Entrance getEntranceByName(String entranceName);
	String getLocationName();
	
	void removeArea(IArea area);
	void removeEntrance(Entrance entrance);
	void removeWaitingArea(IWaitingArea waitingArea);
	
	void setLocationName(String name);
}
