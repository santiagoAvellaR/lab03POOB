package Test;
import domain.*;
import domain.Garden.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TestGarden.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestGarden
{
    private Garden garden;
    /**
     * Default constructor for test class TestGarden
     */
    public TestGarden()
    {
        garden = new Garden();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        garden = new Garden();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDow0n()
    {
    }
    
    @Test
    public void droseraShouldDie(){
        Drosera drosera = new Drosera(garden, 20, 20);
        for(int i = 0; i < 3; i++){
            garden.ticTac();
        }
        assertFalse(drosera.isAlive());
    }
    
    @Test
    public void droseraShouldEatOnce(){
        Drosera drosera = new Drosera(garden, 20, 20);
        Flower flower = new Flower(garden, 15, 15);
        garden.ticTac();
        assertEquals(drosera.getRow(),15);
        assertEquals(drosera.getColumn(),15);
        for(int i = 0; i < 3; i++){
            garden.ticTac();
        }
        assertTrue(drosera.isAlive());
    }
    
    @Test
    public void droseraMainTest(){
        //Se crea una inquieta
        Drosera drosera = new Drosera(garden, 28, 28);
        //Tiene mucha hambre, para su suerte nacio una nueva flor muy apetitosa
        Flower flower = new Flower(garden, 15,15);
        //No se aguanta las ganas y va a comersela de un mordisco
        garden.ticTac();
        //Pero ahora la inquieta se atasco de tanto comer, necesita hidratarse
        garden.ticTac();
        //Por tomar agua se tuvo que trasladar a la ubicacion de fuente hidrica mas cercana, para que no muera agonizando
        assertEquals(drosera.getRow(),0);
        assertEquals(drosera.getColumn(),0);
        //Pasan los dias, pero dios la abandono, no hay suculentas flores nuevas, solo agua
        // Como no hay flores que comer, se mantiene en la misma posicion
        garden.ticTac();
        assertEquals(drosera.getRow(),0);
        assertEquals(drosera.getColumn(),0);
        garden.ticTac();
        //necesita hidratarse
        assertEquals(drosera.getRow(),36);
        assertEquals(drosera.getColumn(),36);
        garden.ticTac();
        //Tristemente por no poder alimentarse correctamente perece al tercer dia de su ultima comida
        assertFalse(drosera.isAlive());
    }
    
    @Test
    public void droseraShouldEatAndDrink(){
    }
    
    @Test
    public void sandShouldDisappear(){
        Sand sand = new Sand(garden, 0, 20);
        for(int i = 0; i < 100; i++){
            garden.ticTac();
        }
        assertNull(garden.getThing(0,20));
    }
    
}
