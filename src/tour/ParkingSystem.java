package tour;

import tool.MyQueue;
import tool.MyStack;

import java.util.Iterator;

/**
 * 本类为停车场车辆进出功能提供支持
 * 包括以下两个功能
 * 1. 汽车进场
 * 2. 汽车出场（包括停车费用的自动计算）
 */
public class ParkingSystem {

    private MyStack<Car> parkingLot = new MyStack<>();  //表示停车场
    private MyStack<Car> temp = new MyStack<>();  //临时停放车辆的栈
    private MyQueue<Car> road = new MyQueue<>();  //等待进入停车场的车辆

    private class Car {

        long arriveTime;
        String licenseTag;

        public Car(String licenseTag) {
            this.licenseTag = licenseTag;
        }
    }

    /**
     * 汽车进场，若停车场已经满了，则将车停在路上（加入队列）
     * 到达的车辆车牌号不能重复，若重复则会拒绝让其进入停车场
     * @param licenseTag 汽车车牌号
     * @return 模拟汽车进场的信息
     */
    public String arrive(String licenseTag) {
        Car car = new Car(licenseTag);
        if (isExit(licenseTag))
            return "车牌号不能重复!\n";
        if (parkingLot.isFull()) {
            road.enQueue(car);
            return String.format("车库已满, 汽车%s停在了便道上\n", car.licenseTag);
        } else {
            car.arriveTime = System.currentTimeMillis();
            return parking(car);
        }
    }

    /**
     * 用于将一辆车加入到停车场中
     * @param car 车辆对象
     * @return 模拟汽车进入停车场信息
     */
    private String parking(Car car) {
        parkingLot.push(car);
        return String.format("汽车%s停在了%d号车位\n", car.licenseTag, parkingLot.size());
    }

    /**
     * 模拟汽车离开停车场
     * 当有一辆汽车离开的时候，如果有等待中的车辆，会将等待中的车辆加入到停车场中
     *
     * @param licenseTag 车牌号
     * @return 汽车出场模拟信息
     */
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

        //将所有移出的车辆放回停车场
        while (!temp.isEmpty())
            s.append(parking(temp.pop()));

        // 将等待中的汽车加入到停车场中
        if (!road.isEmpty()){
            Car c = road.deQueue();
            c.arriveTime = System.currentTimeMillis();
            s.append(parking(c));
        }
        return s.toString();
    }

    // 判断停车场中是否存在车牌号为license的车辆
    private boolean isExit(String license) {
        Iterator<Car> it = parkingLot.iterator();
        while (it.hasNext())
            if (it.next().licenseTag.equals(license))
                return true;
        return false;
    }

    // 根据到达时间和离开时间计算停车费用
    private int money(Car car) {
        long now = System.currentTimeMillis();
        int hours = (int) (now - car.arriveTime) / 3600000;
        return hours * 2 + 2;   //每小时两元，未满一小时按照一小时计算
    }
}
