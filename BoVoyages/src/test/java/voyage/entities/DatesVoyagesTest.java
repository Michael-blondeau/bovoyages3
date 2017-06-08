package voyage.entities;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class DatesVoyagesTest {

	@Test
	public void testDatesVoyages() {
		DatesVoyages dv = new DatesVoyages();
		assertNull(dv.getDateDepart());
		assertNull(dv.getDateRetour());
		assertEquals(0, dv.getNbVoyageurs());
		assertEquals(0, dv.getPrix(), 0.001);
		assertEquals(0, dv.getId());
	}

	@Test
	public void testDatesVoyagesDateDateDoubleInt() {
		Date depart = new Date();
		Date retour = new Date();
		DatesVoyages dv = new DatesVoyages(depart, retour, 101.5, 5);
		assertEquals(depart, dv.getDateDepart());
		assertEquals(retour, dv.getDateRetour());
		assertEquals(5, dv.getNbVoyageurs());
		assertEquals(101.5, dv.getPrix(), 0.001);
		assertEquals(0, dv.getId());
	}

	@Test
	public void testId() {
		DatesVoyages dv = new DatesVoyages();
		dv.setId(17);
		assertEquals(17, dv.getId());
	}

	@Test
	public void testDateDepart() {
		DatesVoyages dv = new DatesVoyages();
		Date depart = new Date();
		dv.setDateDepart(depart);
		assertEquals(depart, dv.getDateDepart());
	}

	@Test
	public void testDateRetour() {
		DatesVoyages dv = new DatesVoyages();
		Date retour = new Date();
		dv.setDateRetour(retour);
		assertEquals(retour, dv.getDateRetour());
	}

	@Test
	public void testPrix() {
		DatesVoyages dv = new DatesVoyages();
		dv.setPrix(4521.6);
		assertEquals(4521.6, dv.getPrix(), 0.001);
	}

	@Test
	public void testNbVoyageurs() {
		DatesVoyages dv = new DatesVoyages();
		dv.setNbVoyageurs(7);
		assertEquals(7, dv.getNbVoyageurs());
	}

}
