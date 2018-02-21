package application.tickets;

import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;

public class Ticket {
	
	private IArea area;
	private ISector sector;
	
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
	

}
