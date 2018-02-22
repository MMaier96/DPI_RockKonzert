package application.phases;

public interface IPhase {
	double getIndoorCapacity();
	double getOutdoorCapacity();
	int getPhaseId();
	void setIndoorCapacity(double capacity);
	void setOutdoorCapacity(double capacity);
}
