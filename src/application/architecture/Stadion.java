package application.architecture;

import static application.logger.Logger.printInfo;
import static application.logger.Logger.printMessage;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;
import application.architecture.areas.subareas.CarWaitingArea;
import application.architecture.areas.subareas.IndoorArea;
import application.architecture.areas.subareas.OutdoorArea;
import application.architecture.areas.subareas.PublicTransportationWaitingArea;
import application.config.AppConfig;
import application.persons.IConcertMediator;

public class Stadion implements ILocation {

	private ArrayList<IArea> areas;
	private ArrayList<Entrance> entrances;
	private ArrayList<IWaitingArea> waitingAreas;
	private String locationName;
	private IConcertMediator eventManager;

	public Stadion() {
		printMessage("Stadion creation started!");
		areas = new ArrayList<IArea>();
		entrances = new ArrayList<Entrance>();
		waitingAreas = new ArrayList<IWaitingArea>();

		printMessage("Area/Sector/Seat creation started!");

		createAreas();

		createSectorsOfAreas();

		createWaitingAreas();

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

	private void createAreas() {
		int charindex = 65;
		printInfo("Creating indoor areas ...");
		for (int i = 0; i < AppConfig.instance.amountIndoorAreas; i++) {
			areas.add(new IndoorArea((char) charindex++ + ""));
		}

		printInfo("Creating outdoor areas ...");
		for (int i = 0; i < AppConfig.instance.amountOutdoorAreas; i++) {
			areas.add(new OutdoorArea((char) charindex++ + ""));
		}

		printInfo(areas.size() + " areas were created! " + areas.toString());
	}

	private void createSectorsOfAreas() {

		for (IArea iArea : areas) {
			iArea.createSectors();
		}
	}

	private void createWaitingAreas() {
		printInfo("Creating car waitingAreas ...");
		for (int i = 0; i < AppConfig.instance.amountCarWaitingAreas; i++) {
			waitingAreas.add(new CarWaitingArea());
		}

		printInfo("Creating public transportation waitingAreas ...");
		for (int i = 0; i < AppConfig.instance.amountPublicTransportationWaitingAreas; i++) {
			waitingAreas.add(new PublicTransportationWaitingArea());
		}
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
	public int getCarWaitingAreasSize() {
		int sum = 0;
		for (int i = 0; i < AppConfig.instance.amountCarWaitingAreas; i++) {
			sum += waitingAreas.get(i).getParticipantsSize();
		}
		return sum;
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

	public ArrayList<IArea> getIndoorAreas() {
		return new ArrayList<IArea>(areas.subList(0, AppConfig.instance.amountIndoorAreas));
	}

	@Override
	public String getLocationName() {
		return locationName;
	}

	public ArrayList<IArea> getOutdoorAreas() {
		return new ArrayList<>(areas.subList(AppConfig.instance.amountIndoorAreas, areas.size()));
	}

	@Override
	public int getPublicTransportationWaitingAreasSize() {
		int sum = 0;
		for (int i = AppConfig.instance.amountCarWaitingAreas; i < waitingAreas.size(); i++) {
			sum += waitingAreas.get(i).getParticipantsSize();
		}
		return sum;
	}

	public IWaitingArea getWaitingAreaByIndex(int index) {
		return waitingAreas.get(index);
	}

	public void registerDisplays() {
		for (IWaitingArea iWaitingArea : waitingAreas) {
			eventManager.addConcertMediatorListener(iWaitingArea.getVisualDisplay());
		}
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
	public void resetWaitingAreas() {

		for (IWaitingArea iWaitingArea : waitingAreas) {
			iWaitingArea.resetParticipants();
		}
	}

	@Override
	public void setEventManager(IConcertMediator eventManager) {
		this.eventManager = eventManager;

	}

	@Override
	public void setLocationName(String name) {
		locationName = name;
	}

}
