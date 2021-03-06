package org.usfirst.frc.team449.robot.drive;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;

/**
 * a generic Drive subsystem outline, also used as a generic in Robot
 */
public abstract class DriveSubsystem extends MappedSubsystem {

	/**
	 * creates a mapped subsystem and sets its map
	 *
	 * @param map
	 *            the map of constants relevant to this subsystem
	 */
	public DriveSubsystem(RobotMap map) {
		super(map);
	}
}