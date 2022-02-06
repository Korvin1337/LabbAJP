package com.company.carfactory;

public class CarFactory {

    public static Car buildCar(CarType model) {
        Car car = null;
        switch (model) {
            case Sport -> car = new SportCar();
            case Luxury -> car = new LuxuryCar();
            case Family -> car = new FamilyCar();
            default -> System.out.println("Error, try again");
        }
        return car;
    }

    public static void main(String[] args) {

        Car bmw = CarFactory.buildCar(CarType.Sport);
        Car lexus = CarFactory.buildCar(CarType.Luxury);
        Car toyota = CarFactory.buildCar(CarType.Family);

        System.out.println(bmw);
        System.out.println(bmw.getModel());
        bmw.setModel(CarType.Family);
        System.out.println(bmw.getModel());

        System.out.println(lexus);
        System.out.println(lexus.getModel());

        System.out.println(toyota);
        System.out.println(toyota.getModel());


    }
}
