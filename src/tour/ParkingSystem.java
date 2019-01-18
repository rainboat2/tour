package tour;

import tool.MyQueue;
import tool.MyStack;

import java.util.Iterator;

public class ParkingSystem {

    private MyStack<Car> parkingLot = new MyStack<>();
    private MyStack<Car> temp = new MyStack<>();
    private MyQueue<Car> road = new MyQueue<>();

    private class Car {

        long arriveTime;
        String licenseTag;

        public Car(String licenseTag) {
            this.licenseTag = licenseTag;
        }
    }

    public String arrive(String licenseTag) {
        Car car = new Car(licenseTag);
        if (parkingLot.isFull()) {
            road.enQueue(car);
            return String.format("车库已满, 汽车%s停在了便道上\n", car.licenseTag);
        } else {
            car.arriveTime = System.currentTimeMillis();
            return parking(car);
        }
    }

    private String parking(Car car) {
        parkingLot.push(car);
        return String.format("汽车%s停在了%d号车位\n", car.licenseTag, parkingLot.size());
    }

    public String leave(String licenseTag) {
        if (!isExit(licenseTag))
            return String.format("汽车%s不存在\n", licenseTag);

        StringBuilder s = new StringBuilder();
        while (!parkingLot.isEmpty()) {
            Car car = parkingLot.pop();
            if (car.licenseTag.equals(licenseTag)) {
                s.append(String.format("汽车%s离开停车场，需要缴费%d元\n", car.licenseTag, money(car)));
                break;
            } else {
                temp.push(car);
                s.append(String.format("汽车%s暂时离开停车场\n", car.licenseTag));
            }
        }

        while (!temp.isEmpty())
            s.append(parking(temp.pop()));

        if (!road.isEmpty())
            s.append(parking(road.deQueue()));
        return s.toString();
    }

    private boolean isExit(String license) {
        Iterator<Car> it = parkingLot.iterator();
        while (it.hasNext())
            if (it.next().licenseTag.equals(license))
                return true;
        return false;
    }

    private int money(Car car) {
        long now = System.currentTimeMillis();
        int hours = (int) (now - car.arriveTime) / 3600000;
        return hours * 2 + 2;   //每小时两元，未满一小时按照一小时计算
    }
}
