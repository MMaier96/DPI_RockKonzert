package application.drones;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;
import application.persons.IConcertMediator;

public abstract class Drone implements IDrone {

	protected ArrayList<IDroneListener> listeners;
	protected IConcertMediator mediator;
	protected int id;
	ArrayList<String> collectedSeats;
	ArrayList<String> seatsResult;
	private boolean finishedCollecting = false;

	public Drone(int id) {
		this.id = id;
		this.listeners = new ArrayList<IDroneListener>();
		this.collectedSeats = new ArrayList<String>();
		this.seatsResult = new ArrayList<String>();
	}

	@Override
	public void addEmptySeats(IArea currentArea, ISector currentSector, ArrayList<Seat> emptySeats) {
		int firstNumber = 0;
		int lastNumber = 0;
		int countFollowing = 0;

		for (Seat seat : emptySeats) {
			if (firstNumber == 0) {
				firstNumber = seat.getSeatNumber();
				lastNumber = firstNumber;
				continue;
			} else if ((seat.getSeatNumber() - 1) == lastNumber) {
				countFollowing++;
				lastNumber = seat.getSeatNumber();
				continue;
			} else if (countFollowing >= 2) {
				collectedSeats.add(firstNumber + " - " + lastNumber);
				firstNumber = seat.getSeatNumber();
				lastNumber = firstNumber;
				countFollowing = 0;
				continue;
			} else {
				if (firstNumber == lastNumber) {
					collectedSeats.add(firstNumber + "");
				} else {
					collectedSeats.add(firstNumber + ", " + lastNumber);
				}
				firstNumber = seat.getSeatNumber();
				lastNumber = firstNumber;
				countFollowing = 0;
			}
		}

		if (countFollowing >= 3) {
			collectedSeats.add(firstNumber + " - " + lastNumber);
		} else if (firstNumber == lastNumber) {
			collectedSeats.add(firstNumber + "");
		} else {
			collectedSeats.add(firstNumber + ", " + lastNumber);
		}
		seatsResult.add(currentSector.toString() + collectedSeats.toString());
		collectedSeats.clear();
	}

	@Override
	public void addListener(IDroneListener droneListener) {
		listeners.add(droneListener);
	}

	public int getId() {
		return id;
	}

	@Override
	public ArrayList<IDroneListener> getListeners() {
		return listeners;
	}

	@Override
	public ArrayList<String> getResultList() {
		return seatsResult;
	}

	@Override
	public boolean isFinishedCollecting() {
		return finishedCollecting;
	}

	@Override
	public void notifyListeners() {
		for (IDroneListener iDroneListener : listeners) {
			iDroneListener.handleNotification();
		}
	}

	@Override
	public void removeListener(IDroneListener droneListener) {
		if (listeners.contains(droneListener)) {
			listeners.remove(droneListener);
		}
	}

	@Override
	public void resetResults() {
		collectedSeats.clear();
		seatsResult.clear();

	}

	@Override
	public void setFinishedCollecting(boolean finishedCollecting) {
		this.finishedCollecting = finishedCollecting;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setListeners(ArrayList<IDroneListener> listeners) {
		this.listeners = listeners;
	}

}
