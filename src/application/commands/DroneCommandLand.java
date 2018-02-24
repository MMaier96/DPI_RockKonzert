package application.commands;

import static application.logger.Logger.*;

public class DroneCommandLand extends DroneCommand {

	@Override
	public void execute() {
		printInfo("Drone # " + drone.getId() + " starts landing!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printInfo("Drone # " + drone.getId() + " landed!");
	}

}
