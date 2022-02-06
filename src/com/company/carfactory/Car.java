package com.company.carfactory;

public abstract class Car {

    private CarType model;

    public Car(CarType model) {
        this.model = model;
    }

    public CarType getModel() {
        return model;
    }

    public void setModel(CarType model) {
        this.model = model;
    }
}
