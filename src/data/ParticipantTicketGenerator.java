package data;

import java.util.ArrayList;

public class ParticipantTicketGenerator {
	private ArrayList<String> tickets;
	private int areaChar = 65;

	public ArrayList<String> getTicketList() {
		return tickets;
	}

	public ParticipantTicketGenerator() {
		tickets = new ArrayList<String>();
	}

	public void create() {
		createIndoorTickets();
		createOutdoorTickets();
	}

	private void createOutdoorTickets() {
		for (int area = 0; area < 16; area++) {
			for (int sector = 1; sector <= 5; sector++) {
				for (int seat = 1; seat <= 750; seat++) {
					tickets.add((char) areaChar + "," + sector + "," + String.format("%02d", seat));
				}
			}
			areaChar++;
		}
	}

	private void createIndoorTickets() {
		for (int area = 0; area < 1; area++) {
			for (int sector = 1; sector <= 10; sector++) {
				for (int seat = 1; seat <= 1000; seat++) {
					tickets.add((char) areaChar + "," + sector + "," + seat);
				}
			}
			areaChar++;
		}
	}
}
