package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeIn extends Command {

	/**
	 * Instantiate a new <code>IntakeIn</code>
	 */
	public IntakeIn() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		System.out.println("IntakeIn init");
	}

	@Override
	protected void execute() {
		Robot.intake.setMotorSpeed(IntakeMap.OUTPUT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return Robot.intake.getCloseEnough();
	}

	@Override
	protected void end() {
		System.out.println("IntakeIn end");
	}

	@Override
	protected void interrupted() {
		System.out.println("IntakeIn interrupted");
	}
}
