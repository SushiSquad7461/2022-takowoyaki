// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain.Drivetrain;

/** An example command that uses an example subsystem. */
public class TurnToFender extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Drivetrain drivetrain;
    private final PhotonCamera camera;
    private final PIDController pid;
    private final Supplier<Double> linearVelocity;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TurnToFender(Drivetrain drivetrain, PhotonCamera camera, Supplier<Double> linearVelocity) {
        pid = new PIDController(Constants.kTurnToFender.kP, Constants.kTurnToFender.kI, Constants.kTurnToFender.kD);
        this.camera = camera;
        this.drivetrain = drivetrain;
        this.linearVelocity = linearVelocity;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            double pitch = result.getBestTarget().getPitch();
            double turnSpeed = pid.calculate(pitch, 0);
            drivetrain.curveDrive(linearVelocity.get(), turnSpeed, false);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
