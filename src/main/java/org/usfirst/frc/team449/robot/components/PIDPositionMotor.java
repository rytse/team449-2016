package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * a PID controller to control a wheel's position through PID via the PIDSubsystem
 */
public class PIDPositionMotor extends PIDSubsystem {
    private SpeedController motor;
    private Encoder encoder;

    public PIDPositionMotor(double p, double i, double d, SpeedController motor, Encoder encoder) {
        super(p, i, d);
        this.motor = motor;
        this.encoder = encoder;
    }

    /**
     * used by the PIDSubsystem to calculate the output wanted for the setpoint
     * in this class, this returns the attached encoder's distance/position via get()
     * @return the rate of rotation of the gyro as per the encoder's get() method
     * @see Encoder#get()
     */
    @Override
    protected double returnPIDInput() {
        return encoder.get();
    }

    /**
     * Uses the output decided by the PIDSubsystem
     * This output is the normalized voltage to the motor, effectively directly propotional to the derivative of the wheel's position
     * @param v the output decided by the PIDSubsystem
     */
    @Override
    protected void usePIDOutput(double v) {
        this.motor.set(v);
    }

    @Override
    protected void initDefaultCommand() {

    }
}