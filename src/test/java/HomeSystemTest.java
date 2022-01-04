import org.example.HomeSystem;
import org.example.SystemLogger;
import org.example.models.Light;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HomeSystemTest {

    private SystemLogger logger;
    private HomeSystem homeSystem ;

    @Before
    public void before(){
        logger = Mockito.mock(SystemLogger.class);
        homeSystem = new HomeSystem(logger);
    }

    @Test
    public void thingEmptyAtInitial(){
        Assert.assertTrue(homeSystem.getThings().isEmpty());
    }

    @Test
    public void addLightSuccess(){
        homeSystem.addThing(new Light());
        Assert.assertEquals(1, homeSystem.getThings().size());
    }

    @Test
    public void toggleLightWhenHomeSystemIsOnSuccess(){
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());
        homeSystem.toggleAllLights(true);

        for (Light l : homeSystem.getLights()){
            Assert.assertTrue(l.isLightOn());
        }
    }

    @Test
    public void toggleLightWhenHomeSystemIsOffFail(){
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());
        homeSystem.setState(HomeSystem.State.OFF);

        homeSystem.toggleAllLights(true);

        for (Light l : homeSystem.getLights()){
            Assert.assertFalse(l.isLightOn());
        }
    }

    @Test
    public void toggleLightTriggersHomeSystemAddLogSuccess(){
        Light light = new Light();
        light.setLightChangedListener(homeSystem);

        light.setLightOn(true);

        Mockito.verify(logger).addLog(Mockito.anyString());

    }
}
