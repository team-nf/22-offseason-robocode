// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveTrainConstants {
        //Matematics (what?)
        public static final double MAX_SPEED = 3.0;
        public static final double MAX_ANGULAR_SPEED = Math.PI * 2;
        public static final double TRACK_WIDTH = 0.605;
        public static final double WHEEL_RADIUS = 0.0763;

        // Motor Ports
        public static final int MOTOR_PORT1 = 1;
        public static final int MOTOR_PORT2 = 2;
        public static final int MOTOR_PORT3 = 3;
        public static final int MOTOR_PORT4 = 4;

        // Encoder Ports
        public static final int LEFT_ENCODER_PORT_A = 0;
        public static final int LEFT_ENCODER_PORT_B = 1;
        public static final int RIGHT_ENCODER_PORT_A = 2;
        public static final int RIGHT_ENCODER_PORT_B = 3;

        public static final int ENCODER_RESOLUTION = 4096;
        public static final double DISTANCE_PER_PULSE = 2 * Math.PI * WHEEL_RADIUS / ENCODER_RESOLUTION;


        public static final byte I2C_ADDRESS = 0x68;

        //reversed?
        public static final boolean LEFT_ENCODER_REVERSED = false;
        public static final boolean RIGHT_ENCODER_REVERSED = true;
        public static final boolean LEFT_MOTORS_REVERSED = false;
        public static final boolean RIGHT_MOTORS_REVERSED = true;

    }
    
    public static final class ShooterConstants {
        public static final double MOTOR_SPEED = 0.3;
        public static final int MOTOR_PORT = 0;
    }

    public static final class HoodConstants {
        // motor port
        public static final int MOTOR_PORT = 1;

        // encoder ports
        public static final int ENCODER_PORT_A = 4;
        public static final int ENCODER_PORT_B = 5;

        // encoder constants
        public static final int ENCODER_RESOLUTION = 4096;
        public static final double DISTANCE_PER_PULSE = 360./400.;



    }

    public static final class HorizontalConstants {
        public static final int MOTOR_PORT = 2;
        public static final int DIGITAL_PORT = 6;
        public static final byte I2C_ADDRESS = 0x69;

        public static final double FILTER = 0.5;
        public static final double MAX_SPEED = 0.1;

        public static final double DISTANCE_TO_TARGET = 5.0;
    }

    public static final class CollectorConstants {

    }

    public static final class BallLifter {

    }

    public boolean isManual(Joystick controller) {
        if (-0.1 < controller.getZ() && controller.getZ() < 0.1) {
          return false;
        }
        return true;
      }
}
