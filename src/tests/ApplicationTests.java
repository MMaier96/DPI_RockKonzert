package tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import application.architecture.areas.IArea;
import application.concert.Concert;
import application.config.AppConfig;
import application.data.CombineNamesGenerator;

public class ApplicationTests {

	@Before
	public void before() {
		CombineNamesGenerator namesGenerator = new CombineNamesGenerator();
		namesGenerator.start();

		assertNotNull(namesGenerator);

		assertNotNull(namesGenerator.getCombinednamespath1());
		assertNotNull(namesGenerator.getCombinednamespath2());
		assertNotNull(namesGenerator.getCombinednamespath3());
		
		assertNotNull(namesGenerator.getCombinedNamesFile1());
		assertNotNull(namesGenerator.getCombinedNamesFile2());
		assertNotNull(namesGenerator.getCombinedNamesFile3());
		
		assertNotNull(namesGenerator.getForenames());
		assertNotNull(namesGenerator.getForenamesPath());
		assertNotNull(namesGenerator.getForenamesFile());
		
		assertNotNull(namesGenerator.getSurnames());
		assertNotNull(namesGenerator.getSurnamesPath());
		assertNotNull(namesGenerator.getSurnamesFile());

		assertNotNull(namesGenerator.getRandomGemerator());
		
		assertNotNull(namesGenerator.getTicketGenerator());
		assertNotNull(namesGenerator.getTicketList());
		

		assertEquals(namesGenerator.getForenames().size(), 70000);
		assertEquals(namesGenerator.getSurnames().size(), 70000);
				
	}
	
	@Test
	public void ConcertTest() {
		Concert concert = new Concert();
		concert.start();
		
		assertNotNull(concert);

		assertNotNull(concert.getEventManager());
		
		assertNotNull(concert.getParticipants());
		
		assertNotNull(concert.getLocation());
		
		assertNotNull(concert.getLocation().getIndoorAreas());
		for (IArea area : concert.getLocation().getIndoorAreas()) {
			for (int i = 0; i < AppConfig.instance.amountIndoorSectorsPerArea; i++) {
				assertNotNull(area.getSectorByIndex(i));
				assertNotNull(area.getSectorByIndex(i).getRelatedArea());
				assertEquals(area.getSectorByIndex(i).getEmptySeats().size(), 0);
			}
		}
		
		assertNotNull(concert.getLocation().getOutdoorAreas());
		for (IArea area : concert.getLocation().getOutdoorAreas()) {
			for (int i = 0; i < AppConfig.instance.amountOutdoorSectorsPerArea; i++) {
				assertNotNull(area.getSectorByIndex(i));
				assertNotNull(area.getSectorByIndex(i).getRelatedArea());
				assertEquals(area.getSectorByIndex(i).getEmptySeats().size(), 0);
			}
		}
		
	}
}
