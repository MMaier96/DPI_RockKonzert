package application.architecture;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;
import application.architecture.areas.subareas.IndoorArea;
import application.architecture.areas.subareas.OutdoorArea;
import application.config.AppConfig;

import static application.logger.Logger.*;


public class Stadion implements ILocation {

	private ArrayList<IArea> areas;
	private ArrayList<Entrance> entrances;
	private ArrayList<IWaitingArea> waitingAreas;
	private String locationName;

	public Stadion() {
		printMessage("Stadion creation started!");
		areas = new ArrayList<IArea>();
		entrances = new ArrayList<Entrance>();
		waitingAreas = new ArrayList<IWaitingArea>();
		

		printMessage("Area creation started!");
		
		createAreas();
		
		createSectorsOfAreas();
	}

	private void createSectorsOfAreas() {

		for (IArea iArea : areas) {
			iArea.createSectors();
		}
	}

	private void createAreas() {
		int charindex = 65;
		printInfo("creating indoor areas ...");
		for (int i = 0; i < AppConfig.instance.amountIndoorAreas; i++) {
			areas.add(new IndoorArea((char) charindex++  + ""));
		}
		
		printInfo("creating outdoor areas ...");
		for (int i = 0; i < AppConfig.instance.amountOutdoorAreas; i++) {
			areas.add(new OutdoorArea((char) charindex++  + ""));
		}
		
		printInfo(areas.size() + " areas were created! " + areas.toString());
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

	
	public ArrayList<IArea> getIndoorAreas() {
		return (ArrayList<IArea>) areas.subList(0, AppConfig.instance.amountIndoorAreas);
	}
	
	public ArrayList<IArea> getOutdoorAreas() {
		return (ArrayList<IArea>) areas.subList(AppConfig.instance.amountIndoorAreas, areas.size()-1);
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
