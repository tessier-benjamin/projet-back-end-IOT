import org.example.models.Thermostat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThermostatTest {

    private Thermostat thermostat;

    @Before
    public void before(){
        thermostat = new Thermostat(10,50);
    }

    @Test
    public void setTemperatureWithinValidRangeSuccess(){
        Assert.assertEquals(thermostat.getMin(), thermostat.getTemperature());
        thermostat.setTemperature(thermostat.getMax());
        Assert.assertEquals(thermostat.getMax(), thermostat.getTemperature());
    }

    @Test
    public void setTemperatureBelowMinFail(){
        Assert.assertEquals(thermostat.getMin(), thermostat.getTemperature());
        thermostat.setTemperature(thermostat.getMin() -1);
        Assert.assertEquals(thermostat.getMin(), thermostat.getTemperature());
    }
    @Test
    public void setTemperatureAboveMaxFail(){
        Assert.assertEquals(thermostat.getMin(), thermostat.getTemperature());
        thermostat.setTemperature(thermostat.getMax() + 1);
        Assert.assertEquals(thermostat.getMax(), thermostat.getTemperature());
    }


}
