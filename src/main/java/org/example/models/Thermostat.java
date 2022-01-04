package org.example.models;

public class Thermostat extends Thing{

    private final int min;
    private final int max;
    private int temperature;

    public Thermostat(int min, int max) {
        this.min=min;
        this.max=max;
        this.temperature=min;
    }

    @Override
    public String getTypeName() {
        return "Thermostat";
    }

    @Override
    public String getDescription() {
        return "temperature = " + this.temperature;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature < min ){
            this.temperature = min;
        }
        else if (temperature > max){
            this.temperature = max;
        }
        else{
            this.temperature = temperature;

        }
    }
}
