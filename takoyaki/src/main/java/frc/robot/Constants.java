// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Scanner;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {

  // set tuning mode to true to enable tuning values over NT
  public static final boolean TUNING_MODE = true;

  // the unit of measurement for Talon FX encoder velocity is known as the "Tran"
  // encoder ticks per 100ms
  public static double convertRPMToTrans(double RPM) {
    return RPM * 2048.0 / 600.0;
  }

  public static double convertTransToRPM(double trans) {
    return trans * 600.0 / 2048.0;
  }

  // used for unit conversions for ff constants on talon fx
  public static double voltageToPercent(double voltage) {
    return voltage / 12.0;
  }

  public static SupplyCurrentLimitConfiguration currentLimit(int fuseAmps) {
    return new SupplyCurrentLimitConfiguration(true, fuseAmps - 5, fuseAmps, 0.75);
  }

  public static final class kClimb {

    public static final int LEFT_MOTOR_CAN_ID = 2;
    public static final int RIGHT_MOTOR_CAN_ID = 17;

    public static final double OPEN_LOOP_UP_POWER = 0.3;
    public static final double OPEN_LOOP_DOWN_POWER = -1;

    public static final double LEFT_TOP_SETPOINT = -290000;
    public static final double RIGHT_TOP_SETPOINT = -290000;
    public static final double BOTTOM_SETPOINT = -5000;
    public static final double LEFT_LATCH_PASSIVE = -41500;
    public static final double RIGHT_LATCH_PASSIVE = -46200;
    public static final double LATCH_MAIN = -300000;

    public static final double kP = 0.6;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kF = 0;

    // timings in seconds for traversal
    public static final double MID_PASSIVE_LATCH_PAUSE = 3;
    public static final double HIGH_MAIN_LATCH_PAUSE = 3;

    //current thresholds
    public static final double CLIMBING_CURRENT = 5;
  }

  public static final class kOI {
    public static final int DRIVE_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;

    // drive buttons
    public static final int INVERT_DRIVE = XboxController.Button.kY.value;

    // shooting buttons
    public static final int SHOOT = XboxController.Button.kB.value;
    public static final int RANGED_SHOOT = XboxController.Button.kLeftBumper.value;
    public static final int REVERSE_SHOOT = XboxController.Button.kBack.value;

    // intake buttons
    public static final int TOGGLE_INTAKE = XboxController.Button.kA.value;
    public static final int REVERSE_INTAKE = XboxController.Button.kStart.value;

    // climb buttons
    public static final int EXTEND_LATCH_MAIN = XboxController.Button.kX.value;
    public static final int CLIMB_LATCH_PASSIVE = XboxController.Button.kB.value;
    public static final int OPEN_LOOP_RAISE_CLIMB = XboxController.Button.kA.value;
    public static final int OPEN_LOOP_LOWER_CLIMB = XboxController.Button.kY.value;

    public static final String TRAJECTORY_NAME = "path";
  }

  public static final class kHopper {
    public static int MOTOR_ID;
    public static boolean INVERTED;
    public static final double SPEED = 0.7;
  }

  public static final class kIntake {
    public static final class kFalcon {
      public static final TalonFXInvertType INVERT_TYPE = TalonFXInvertType.CounterClockwise;
      public static final TalonFXControlMode CONTROL_MODE = TalonFXControlMode.PercentOutput;
      public static final int CURRENT_LIMIT = 30;
    }

    public static final class kSpark {
      public static final CANSparkMaxLowLevel.MotorType MOTOR_TYPE = CANSparkMaxLowLevel.MotorType.kBrushless;
      public static final int CURRENT_LIMIT = 35;
      public static final int OPEN_LOOP_RAMP_RATE = 0;
      public static final boolean INVERTED = true;
    }

    public static int MOTOR_ID;
    public static int LEFT_SOLENOID_FORWARD;
    public static int LEFT_SOLENOID_REVERSE;
    public static int RIGHT_SOLENOID_FORWARD;
    public static int RIGHT_SOLENOID_REVERSE;

    public static final double INTAKE_SPEED = 0.9;
  }

  public static final class kDrive {

    public static final FieldConstants FIELD_CONSTANTS = FieldConstants.BEAR_METAL;

    public enum FieldConstants {
      BEAR_METAL(0.75058, 2.3131, 0.46187, 0.000084582, 0, 0, 0.04, 0.1, 1, 0),
      ROYALS(0.77377, 2.3111, 0.23485, 0.000020568, 0, 0, 0.04, 0.1, 1, 0);

      public double ksVolts;
      public double kvVoltSecondsPerMeter;
      public double kaVoltSecondsSquaredPerMeter;
      public double kPDriveVel;
      public double kIDrive;
      public double kDDrive;

      // slew constants
      public double TRIGGER_SPEED_DERIVATIVE;
      public double LINEAR_SCALING_MIN_SPEED;
      public double MAX_ACCEL;
      public double MINIMUM_SENSOR_VELOCITY;

      private FieldConstants(double ksVolts, double kvVoltSecondsPerMeter, double kaVoltSecondsSquaredPerMeter,
          double kPDriveVel, double kIDrive, double kDDrive, double TRIGGER_SPEED_DERIVATIVE,
          double LINEAR_SCALING_MIN_SPEED, double MAX_ACCEL, double MINIMUM_SENSOR_VELOCITY) {
        this.ksVolts = ksVolts;
        this.kvVoltSecondsPerMeter = kvVoltSecondsPerMeter;
        this.kaVoltSecondsSquaredPerMeter = kaVoltSecondsSquaredPerMeter;
        this.kPDriveVel = kPDriveVel;
        this.kIDrive = kIDrive;
        this.kDDrive = kDDrive;

        this.TRIGGER_SPEED_DERIVATIVE = TRIGGER_SPEED_DERIVATIVE;
        this.LINEAR_SCALING_MIN_SPEED = LINEAR_SCALING_MIN_SPEED;
        this.MAX_ACCEL = MAX_ACCEL;
        this.MINIMUM_SENSOR_VELOCITY = MINIMUM_SENSOR_VELOCITY;
      }
    }

    public static void setupFieldConstants() {
      ksVolts = FIELD_CONSTANTS.ksVolts;
      kvVoltSecondsPerMeter = FIELD_CONSTANTS.kvVoltSecondsPerMeter;
      kaVoltSecondsSquaredPerMeter = FIELD_CONSTANTS.kaVoltSecondsSquaredPerMeter;
      kPDriveVel = FIELD_CONSTANTS.kPDriveVel;
      kIDrive = FIELD_CONSTANTS.kIDrive;
      kDDrive = FIELD_CONSTANTS.kDDrive;

      TRIGGER_SPEED_DERIVATIVE = FIELD_CONSTANTS.TRIGGER_SPEED_DERIVATIVE;
      LINEAR_SCALING_MIN_SPEED = FIELD_CONSTANTS.TRIGGER_SPEED_DERIVATIVE;
      MAX_ACCEL = FIELD_CONSTANTS.MAX_ACCEL;
      MINIMUM_SENSOR_VELOCITY = FIELD_CONSTANTS.MINIMUM_SENSOR_VELOCITY;
    }

    public static int FRONT_RIGHT_ID;
    public static int FRONT_LEFT_ID;
    public static int BACK_RIGHT_ID;
    public static int BACK_LEFT_ID;
    public static int NUM_MOTORS = 4;

    // to divide quick turn power by
    public static final double QUICK_TURN_DAMPENER = 2.0;

    public static double ksVolts;
    public static double kvVoltSecondsPerMeter;
    public static double kaVoltSecondsSquaredPerMeter;
    public static double kPDriveVel;
    public static double kIDrive;
    public static double kDDrive;

    // slew constants
    public static double TRIGGER_SPEED_DERIVATIVE;
    public static double LINEAR_SCALING_MIN_SPEED;
    public static double MAX_ACCEL;
    public static double MINIMUM_SENSOR_VELOCITY;

    public static final double MAX_VOLTAGE = 5;

    // odometry constants - drivetrain measurements
    public static final double TRACK_WIDTH_METERS = 0.71; // width between sides of dt // 0.603
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(
        TRACK_WIDTH_METERS);

    // ticks to meters conversion factor for falcon 500
    // (total ticks) * (motor rotations/tick) * (wheel rotations/motor rotations) *
    // (meters/wheel rotations)
    public static final double TICKS_TO_METERS = (1.0 / 2048.0) * (1.0 / 10.71) * (0.4788);

    // ramsete parameters
    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;
    public static final double OPEN_LOOP_RAMP_RATE = 0; // 0.3
    public static final double QUICKTURN_DAMPENER = 3; // bigger number = slower turns
    public static final double SLOW_MODE_VELOCITY = -0.1;
    public static final int SUPPLY_LIMIT = 40;
    public static final int STATOR_LIMIT = 70;

  }

  public static final class kShooter {

    public static final class kOpenLoop {
      public static final double SPEED = 1;
      public static final double BACK_SPEED = 1; // only used on double shooters
    }

    public static final class kDoubleClosedLoop {
      public static final double SETPOINT_RPM = 1325.0;// 1300 prac field // 1325 sundome // 1065 gpk CONSTANT

      public static final class kFront {
        public static final double ERROR_TOLERANCE = 30; // 30
        public static final double SETPOINT_OFFSET_RPM = 0;
        public static double kP;
        public static double kI;
        public static double kD;
        public static double kF;
        public static double kS = voltageToPercent(0.61716);
        public static double kV = voltageToPercent(0.10724);
        public static double kA = voltageToPercent(0.0082862);
      }

      public static final class kBack {
        public static final double ERROR_TOLERANCE = 100; // 30
        public static final double SETPOINT_OFFSET_RPM = 50;
        public static double kP;
        public static double kI;
        public static double kD;
        public static double kF;
        public static double kS;
        public static double kV;
        public static double kA;
        public static final double CURRENT_LIMIT = 15;
        public static final double CURRENT_LIMIT_THRESHOLD = 20;
        public static final double CURRENT_LIMIT_THRESHOLD_TIME = 3;
      }
    }

    public static final class kKicker {
      public static final double MOTOR_SPEED = 1;
      public static boolean KICKER_INVERSION;
    }

    public static int LEFT_MOTOR_ID;
    public static int RIGHT_MOTOR_ID;
    public static int KICKER_MOTOR_ID;
    public static int BACK_MOTOR_ID;
    public static final int DEFAULT_PROFILE_SLOT = 0;
    public static final int DEFAULT_CONFIG_TIMEOUT = 100;
    public static final double ERROR_TOLERANCE_PERCENT = 0.97;
    public static boolean KICKER_INVERSION;

    public static final double SPEED_KICKER = 1;
    public static final double KICKER_PERIOD = 50;
    public static final double KICKER_OFFSET = 0.3;
  }

  enum RobotType {
    PRACTICE,
    COMP
  }

  public static void setup() {

    kDrive.setupFieldConstants();
    RobotType robot = getRobotType();
    switch (robot) {
      case PRACTICE:
        kHopper.MOTOR_ID = 10;
        kIntake.MOTOR_ID = 8;
        kIntake.LEFT_SOLENOID_FORWARD = -1;
        kIntake.LEFT_SOLENOID_REVERSE = -1;
        kIntake.RIGHT_SOLENOID_FORWARD = -1;
        kIntake.RIGHT_SOLENOID_REVERSE = -1;
        kDrive.FRONT_RIGHT_ID = 3;
        kDrive.FRONT_LEFT_ID = 1;
        kDrive.BACK_RIGHT_ID = 4;
        kDrive.BACK_LEFT_ID = 2;
        kShooter.LEFT_MOTOR_ID = 12;
        kShooter.RIGHT_MOTOR_ID = 15;
        kShooter.KICKER_MOTOR_ID = 5;
        kShooter.kDoubleClosedLoop.kFront.kP = 0.15;
        kShooter.kDoubleClosedLoop.kFront.kI = 0.0000;
        kShooter.kDoubleClosedLoop.kFront.kD = 0.0;
        kShooter.kDoubleClosedLoop.kFront.kF = 0.045;
        kHopper.INVERTED = false;
        kShooter.kKicker.KICKER_INVERSION = true;
        break;
      default:
        kHopper.MOTOR_ID = 0;
        kIntake.MOTOR_ID = 9;
        kIntake.LEFT_SOLENOID_FORWARD = 15;
        kIntake.LEFT_SOLENOID_REVERSE = 1;
        kIntake.RIGHT_SOLENOID_FORWARD = 0;
        kIntake.RIGHT_SOLENOID_REVERSE = 14;
        kDrive.FRONT_RIGHT_ID = 3;
        kDrive.FRONT_LEFT_ID = 16;
        kDrive.BACK_RIGHT_ID = 4;
        kDrive.BACK_LEFT_ID = 15;
        kShooter.LEFT_MOTOR_ID = 5;
        kShooter.RIGHT_MOTOR_ID = 14;
        kShooter.KICKER_MOTOR_ID = 10;
        kShooter.BACK_MOTOR_ID = 19;
        kShooter.kDoubleClosedLoop.kFront.kP = 0.1;
        kShooter.kDoubleClosedLoop.kFront.kI = 0.0000;
        kShooter.kDoubleClosedLoop.kFront.kD = 0.0;
        kShooter.kDoubleClosedLoop.kFront.kF = 0.05;
        kShooter.kDoubleClosedLoop.kBack.kP = 0.180;
        kShooter.kDoubleClosedLoop.kBack.kI = 0;
        kShooter.kDoubleClosedLoop.kBack.kD = 0;
        kShooter.kDoubleClosedLoop.kBack.kF = 0.045;
        kShooter.kDoubleClosedLoop.kBack.kS = voltageToPercent(0.0070982);
        kShooter.kDoubleClosedLoop.kBack.kV = voltageToPercent(0.0011253);
        kShooter.kDoubleClosedLoop.kBack.kA = voltageToPercent(0.000047908);
        kHopper.INVERTED = true;
        kShooter.kKicker.KICKER_INVERSION = false;
        break;
    }
  }

  public static RobotType getRobotType() {
    // Map<String, String> env = System.getenv();
    // SmartDashboard.putString("Home", env.get("HOME"));
    File f = new File("/home/lvuser/id.txt");
    int id = 0;
    String errorMsg = "success";
    try {
      Scanner reader = new Scanner(f);
      id = reader.nextInt();
      reader.close();
    } catch (FileNotFoundException e) {
      errorMsg = "file not found exception";
      SmartDashboard.putString("robot type status", errorMsg);
    }
    if (id == 1) {
      SmartDashboard.putString("robot", "practice");
      return RobotType.PRACTICE;
    } else {
      SmartDashboard.putString("robot", "comp");
      return RobotType.COMP;
    }

  }
}
