package application.phases;

public abstract class Phase implements IPhase{

	private int id;
	private double indoorCapacity;
	private double outdoorCapacity;
	
	public Phase(int id, double indoor, double outdoor) {
		this.id = id;
		this.indoorCapacity = indoor;
		this.outdoorCapacity = outdoor;
	}

	@Override
	public double getIndoorCapacity() {
		return indoorCapacity;
	}

	@Override
	public double getOutdoorCapacity() {
		return outdoorCapacity;
	}

	@Override
	public int getPhaseId() {
		return id;
	}

	@Override
	public void setIndoorCapacity(double capacity) {
		this.indoorCapacity = capacity;
	}

	@Override
	public void setOutdoorCapacity(double capacity) {
		this.outdoorCapacity = capacity;
	}
	
	@Override
	public String toString() {
		return "Phase" + String.format("%02d", id);
	}
}
