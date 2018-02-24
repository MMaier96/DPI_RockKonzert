package application.architecture.visualdisplay;

import java.util.ArrayList;

import application.persons.IConcertMediatorListener;
import application.phases.IPhase;

public class VisualDisplay implements IConcertMediatorListener{
	
	private ArrayList<IVisualDisplayListener> listeners;
	private IPhase actualPhase;
	
	public VisualDisplay() {
		listeners = new ArrayList<IVisualDisplayListener>();
	}
	
	public void addVisualDisplayListener(IVisualDisplayListener listener) {
		listeners.add(listener);
	}
	
	
	public void removeVisualDisplayListener(IVisualDisplayListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
	
	public ArrayList<IVisualDisplayListener> getParticipants() {
		return listeners;
	}


	public void setParticipants(ArrayList<IVisualDisplayListener> listener) {
		this.listeners = listener;
	}


	public IPhase getActualPhase() {
		return actualPhase;
	}


	public void setActualPhase(IPhase actualPhase) {
		this.actualPhase = actualPhase;
	}


	public void notifyListeners() {
		for (IVisualDisplayListener listener : listeners) {
			listener.handleNotification();
		}
	}
	
	@Override
	public void handleNotification() {
		notifyListeners();
	}

}
