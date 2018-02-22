package application.architecture.areas.subareas;

import application.architecture.areas.WaitingArea;
import application.architecture.areas.types.WaitingAreaTypes;
import application.architecture.visualdisplay.VisualDisplay;

public class CarWaitingArea extends WaitingArea {

	private WaitingAreaTypes waitingAreaType;

	public CarWaitingArea(VisualDisplay visualDisplay) {
		super(visualDisplay);
		this.waitingAreaType = WaitingAreaTypes.CAR;
	}

	public WaitingAreaTypes getWaitingAreaType() {
		return waitingAreaType;
	}

	public void setWaitingAreaType(WaitingAreaTypes waitingAreaType) {
		this.waitingAreaType = waitingAreaType;
	}

}
