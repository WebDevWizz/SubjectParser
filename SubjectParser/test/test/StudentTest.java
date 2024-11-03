package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ClassUnderTest.SubjectParser;

public class StudentTest {
	
	private SubjectParser subjectParser; 

	@Before
	public void setUp() throws Exception {
		//Inizializzo con stringa di test:
		subjectParser = new SubjectParser("2023 sw testing (2/10)"); 
	}

	@After
	public void tearDown() throws Exception {
		subjectParser = null; 
	}

	//Esempio di test
	@Test
	public void testGetId() {
		// Caso di test 0: Stringa valida con identificatore numerico
        
		//SubjectParser subjectParser = new SubjectParser("2023 sw testing (2/10)");
        //Mi prendo l'identificatore numerico (sarà di tipo long)
        long id = subjectParser.getId();
        assertEquals(2023L, id);
	}
	
	//TEST 2: stringa valida senza id: 
	@Test
	public void testGetId_NonValid() {
		SubjectParser subjectParser1 = new SubjectParser("sw testing (2/10)"); 
		assertEquals(-1,subjectParser1.getId()); 
	}
	
	//TEST 3: Stringa con id non valido
	@Test
	public void testGetId2() {
		SubjectParser subjectParser3= new SubjectParser("2023sw testing (2/10)"); 
		assertEquals(-1, subjectParser3.getId());
	}
	
	
	//TEST 4: PRENDERE UN RANGE CON UN INTERVALLO VALIDO.
	//Iniziamo con il Lower range => 
	@Test
	public void testGetThisRange() {
		//Intervallo valido già definito ovviamente nel setup: 
		int lowerRange = subjectParser.getThisRange(); 
		assertEquals(2, lowerRange); //Val. di default messo nel setup
	}
	
	//TEST 5: STRINGA CON FORMATO NON VALIDO: 
	@Test
	public void testGetThisRange_NonValidString() {
		SubjectParser subjectParser1 = new SubjectParser("sw testing"); 
		assertNotNull(subjectParser1.getThisRange()); 
	}
	
	//TEST 6: STRINGA CON INTERVALLO IN PARENTESI QUADRE: 
	@Test
	public void testGetThisRange2() {
		SubjectParser subjectParser2 = new SubjectParser("2023 sw testing [2/10]"); 
		assertEquals(2, subjectParser2.getThisRange());
	}
	
	//TEST 7: STRINGA IN CUI DI DEFAULT IL LOWERRANGE E' 1: 
	@Test
	public void testGetThisRange3() {
		SubjectParser subjectParser2 = new SubjectParser("2023 sw testing"); 
		assertEquals(1, subjectParser2.getThisRange());
	}
	
	
	//TESTING DELL'UPPER RANGE: 

	//TEST 8: SEMPLICE INVOCAZIONE DEL getUpperRange SULL'UPPER:
	@Test
	public void testGetUpperRange1() {
		int upperRange = subjectParser.getUpperRange(); 
		assertEquals(10, upperRange); 
	}
	
	//TEST 9: DI DEFAULT E' 1: 
	@Test
	public void testGetUpperRange2() {
		SubjectParser subjectParser2 = new SubjectParser("2023 sw testing"); 
		assertEquals(1, subjectParser2.getUpperRange());
	}
	
	//TEST 10: STRINGA IN FORMATO NON VALIDO: 
	@Test
	public void testGetUpperRange3() {
		SubjectParser subjectParser3 = new SubjectParser("sw testing"); 
		assertNotNull(subjectParser3.getUpperRange()); 
	}
	
	//TEST 11: STRINGA CON RANGE IN []
	@Test
	public void testGetUpperRange4() {
		SubjectParser subjectParser4 = new SubjectParser("2023 sw testing [2/10]"); 
		assertEquals(10, subjectParser4.getUpperRange());
	}

	
	//TEST 12: STRINGA CON RANGE VALID (già messo nel setup):
	@Test
	public void testGetRangeString() {
		String rangeValid = subjectParser.getRangeString(); 
		assertNotNull((2/10)); 
	}
	
	//TEST 13: STRINGA CON RANGE NON VALIDO:
	@Test
	public void testGetRangeString_NonValid() {
		SubjectParser subjectParser = new SubjectParser("2023 sw testing"); 
		assertEquals(null, subjectParser.getRangeString()); 
	}

	
	
	//Concludiamo ora con il testing del titolo.
	
	//TEST 14: STRINGA CON TITOLO VALIDO
	@Test
	public void testGetTitle() {
		String title = subjectParser.getTitle(); 
		assertEquals("sw testing", title); 
	}
	
	//TEST 15: STRINGA SENZA TITOLO (con ()): 
	@Test
	public void emptyTitle() {
		SubjectParser subjectParser1 = new SubjectParser("2023 (2/10)");
		assertEquals("", subjectParser1.getTitle()); 
	}
	
	//TEST 16: STRINGA SENZA TITOLO (con []): 
	@Test
	public void emptyTitle2() {
		SubjectParser subjectParser2 = new SubjectParser("2023 [2/10]");
		assertEquals("", subjectParser2.getTitle()); 
	}

	//TEST 17: STRINGA CON TITOLO + RANGE ():
	@Test
	public void testGetTitle2() {
		SubjectParser subjectParser3 = new SubjectParser("2023 sw testing ()");
		assertNotNull(subjectParser3.getTitle()); 
	}
	
	//TEST 18: STRINGA CON TITOLO + RANGE []: 
	@Test
	public void testGetTitle3() {
		SubjectParser subjectParser4 = new SubjectParser("2023 sw testing [])");
		assertNotNull(subjectParser4.getTitle()); 
	}
	
	//TEST 19: STRINGA CON TITOLO + DOPPIO RANGE: 
	@Test
	public void testGetTitle4() {
		SubjectParser subjectParser5 = new SubjectParser("2023 sw testing (3/4) [4/5]");
		assertNotNull(subjectParser5.getTitle()); 
	}


}
