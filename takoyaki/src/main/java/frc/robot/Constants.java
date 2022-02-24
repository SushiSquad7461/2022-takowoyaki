// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public final class Constants {
    public static final class kClimb {
        public static final int CLIMB_TO_TOP_BUTTON = XboxController.Button.kY.value;
        public static final int CLIMB_TO_BOTTOM_BUTTON = XboxController.Button.kA.value;

        public static final int CLIMB_OPEN_LOOP_LOWER_BUTTON = XboxController.Button.kX.value;
        public static final int CLIMB_OPEN_LOOP_RAISE_BUTTON = XboxController.Button.kB.value;

        public static final int CLIMB_ENCODER_RESET_BUTTON = XboxController.Button.kStart.value;

        // public static final int LEFT_MOTOR_CAN_ID = 15; // green climb
        public static final int LEFT_MOTOR_CAN_ID = 15; // red climb
        public static final int RIGHT_MOTOR_CAN_ID = 0;

        public static final double OPEN_LOOP_UP_POWER = -0.3;
        public static final double OPEN_LOOP_DOWN_POWER = 0.3;

        public static final double CLOSED_LOOP_UP_POWER = -0.5;
        public static final double CLOSED_LOOP_DOWN_POWER = 0.5;

        // TODO: identify correct setpoints
        public static final int TOP_ENCODER_VAL = -165000;
        public static final int BOTTOM_ENCODER_VAL = -3000;

        public static final double OPEN_LOOP_RAMP_RATE = 0.5;
    }

    public static final class kOI {
        public static final int DRIVE_CONTROLLER = 0;
    }
}
