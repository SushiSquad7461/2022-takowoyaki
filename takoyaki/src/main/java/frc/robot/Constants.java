// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public final static class kHooper {
    public final static int MOTOR_ID = -1;
    public final static boolean INVERTED = false;
    public final static int CURRENT_LIMIT = 30;
    public final static double SPEED = 0.9;

    public static final int RUN_HOPPER = XboxController.Button.kX.value;
    public static final int REVERSE_HOPPER = XboxController.Button.kB.value;
  }
  public static final class kOI {
    public static final int DRIVE_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;
  }
}
