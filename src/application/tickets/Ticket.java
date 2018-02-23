package application.tickets;

import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;

public class Ticket {
	
	private IArea area;
	private ISector sector;
	private Seat seat;
	
	public Ticket(IArea area, ISector sector, Seat seat) {
		this.area = area;
		this.sector = sector;
		this.seat = seat;
	}
	
	
	public IArea getArea() {
		return area;
	}
	public void setArea(IArea area) {
		this.area = area;
	}
	public ISector getSector() {
		return sector;
	}
	public void setSector(ISector sector) {
		this.sector = sector;
	}

	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	

}
