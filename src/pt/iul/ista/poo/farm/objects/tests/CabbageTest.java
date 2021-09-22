package pt.iul.ista.poo.farm.objects.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.iul.ista.poo.farm.objects.Cabbage;
import pt.iul.ista.poo.utils.Point2D;

class CabbageTest{
	Cabbage c1,c2;
	
	@BeforeEach
	public void setUp(){
		c1=new Cabbage(new Point2D(0,1));
	}
	
	@Test
	public void testGrow(){
		c1.grow();
		c1.grow();
		c1.grow();
		assertEquals(2,c1.getStage());
	}

	@Test 
	public void testGetName() {
		assertEquals(c1.getName(),"small_cabbage");
	}
	
	@Test
	public void testGetNameAmadurecer(){
		for(int i =0; i<=10;i++)
			c1.grow();
		c1.update();// o update é que faz mudar o nome da couve
		assertEquals("cabbage",c1.getName());
	}
	
	@Test
	public void testGetNameApodrecer(){
		for(int i =0; i<=30;i++){
			c1.grow();
			c1.update();
		}
		assertEquals("bad_cabbage",c1.getName());
	}
	
	@Test
	public void testNullAndNotNull() {
		assertNotNull(c1);
		assertNull(c2);
	}
	
	@Test
	public void testUpdate() {		
		for(int i = 0 ; i<11;i++)
			c1.grow();
		assertEquals(c1.getStage(), 10);
	}
	@Test
	public void testSetName(){
		c1.setName("cabbage");
		assertEquals("cabbage",c1.getName());		
	}
	@Test
	public void testGetLayer(){
		assertEquals(1,c1.getLayer());
	}
	@Test
	public void testGetPriority(){
		assertEquals(3,c1.getPriority());
	}
	
}
