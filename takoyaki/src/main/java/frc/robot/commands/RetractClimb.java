// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climb.Climb;

/** An example command that uses an example subsystem. */
public class RetractClimb extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Climb climb;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RetractClimb(Climb climb) {
    this.climb = climb;
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climb.retractClimb();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climb.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climb.isFinished();
  }
}
