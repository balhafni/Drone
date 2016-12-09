
import de.yadrone.base.ARDrone;
import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.navdata.AttitudeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bashar.alhafni
 */
public class Driver {

    static ARDrone drone = null;

    public static void main(String[] args) {

        try {
            drone = new ARDrone(); //a new ARDrone object is instantiated
            drone.start(); //all managers (i.e. Command-, Configuration, NavData- and VideoManager) will begin their work
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {
            @Override
            public void attitudeUpdated(float pitch, float roll, float yaw) {
                System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
            }

            @Override
            public void attitudeUpdated(float pitch, float roll) {
            }

            @Override
            public void windCompensation(float pitch, float roll) {
            }
        });
        VideoManager video = new VideoManager(drone);
        control();
    }

    public static void control() {
        drone.getCommandManager().setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
        drone.getCommandManager().takeOff();
        drone.getCommandManager().waitFor(5000);
//        drone.getCommandManager().forward(20);
//        drone.getCommandManager().waitFor(1000);
        drone.getCommandManager().landing();
    }
}
