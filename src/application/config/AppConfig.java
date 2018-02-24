package application.config;

public enum AppConfig {
	instance;
	
	public int amountIndoorDrones = 1;
	public int amountOutdoorDrones = 4;
	public int amountParticipants = 70000;
	public int amountOutdoorAreas = 16;
	public int amountIndoorAreas = 1;
	public int amountIndoorSectorsPerArea = 10;
	public int amountOutdoorSectorsPerArea = 5;
	public int amountIndoorSeatsPerSector = 1000;
	public int amountOutdoorSeatsPerSector = 750;
	public int amountCarWaitingAreas = 2;
	public int amountPublicTransportationWaitingAreas = 8;
	public int indoorSeats = amountIndoorAreas * amountIndoorSectorsPerArea * amountIndoorSeatsPerSector; // 10.000
	public int outdoorSeats = amountOutdoorAreas * amountOutdoorSectorsPerArea * amountOutdoorSeatsPerSector; // 60.000
	
	
}
