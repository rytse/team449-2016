package org.usfirst.frc.team449.robot.mechanism.intake;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.SmoothedValue;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.UpdateUS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * a class for intake using a single motor (probably connected to a roller)
 */
public class IntakeSubsystem extends MechanismSubsystem {
	/**
	 * The <code>SpeedController</code> driving the suck in/spit out wheels
	 */
	private SpeedController mainMotor;

	/**
	 * The <code>DoubleSolenoid</code> controlling the piston raising and
	 * lowering the intake subsystem
	 */
	private DoubleSolenoid solenoid;

	/**
	 * The left ultrasonic rangefinding sensor's <code>AnalogInput<code> channel
	 */
	private AnalogInput leftChannel;

	/**
	 * The right ultrasonic rangefinding sensor's
	 * <code>AnalogInput<code> channel
	 */
	private AnalogInput rightChannel;

	/**
	 * The left ultrasonic rangefinder's <code>Value</code>
	 */
	private SmoothedValue leftVal;

	/**
	 * The right ultrasonic rangefinder's <code>Value</code>
	 */
	private SmoothedValue rightVal;
	
	private AnalogInput leftIR;
	
	private AnalogInput rightIR;

	public IntakeSubsystem(RobotMap map) {
		super(map);
		System.out.println("Intake init started");

		if (!(map instanceof IntakeMap)) {
			System.err.println("Intake has a map of class "
					+ map.getClass().getSimpleName() + " and not IntakeMap");
		}

		IntakeMap intakeMap = (IntakeMap) map;

		this.mainMotor = new VictorSP(intakeMap.motor.PORT);
		this.mainMotor.setInverted(intakeMap.motor.INVERTED);

		solenoid = new DoubleSolenoid(intakeMap.solenoid.forward,
				intakeMap.solenoid.reverse);
		this.leftIR = new AnalogInput(intakeMap.leftIR.PORT);
		this.rightIR = new AnalogInput(intakeMap.rightIR.PORT);
		
		leftChannel = new AnalogInput(intakeMap.leftUltrasonic.PORT);
		rightChannel = new AnalogInput(intakeMap.rightUltrasonic.PORT);
		
		leftVal = new SmoothedValue(1);
		rightVal = new SmoothedValue(1);

		System.out.println("Intake init finished");
	}

	/**
	 * sets the motor for intake to go at the given speed
	 * 
	 * @param speed
	 *            the normalized speed of the motor (between -1 and 1)
	 */
	public void setMotorSpeed(double speed) {
		mainMotor.set(speed);
	}

	/**
	 * sets the double solenoid to forward its forward state
	 */
	public void setSolenoidForward() {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * sets the double solenoid to forward its reverse state
	 */
	public void setSolenoidReverse() {
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public double getValLeft() {
		return 0.0982 * leftChannel.getValue() + 2.2752;
	}

	public double getValRight() {
		return 0.0497 * rightChannel.getValue() - 0.2725;
	}

	public void updateVals() {
		leftVal.set(leftChannel.getValue());
		rightVal.set(rightChannel.getValue());
	}

	public double getAngle() {
		double y = Math.abs(getValLeft() - getValRight());
		double x = 24; // 2 feet apart
		return Math.toDegrees(Math.atan2(y, x));
	}

	/**
	 * checks on the infrareds whether they sense the ball or not
	 * @return true if at least one IR is sensing the ball, false otherwise
	 */
	public boolean findBall() {
		double right = rightIR.getAverageVoltage();
		double left = leftIR.getAverageVoltage();
		IntakeMap intakeMap = (IntakeMap) map;
		SmartDashboard.putNumber("right ir voltage", right);
		SmartDashboard.putNumber("left ir voltage", left);
		return (intakeMap.rightIR.LOWER_BOUND < right && right < intakeMap.rightIR.UPPER_BOUND)
				|| (intakeMap.leftIR.LOWER_BOUND < left && left < intakeMap.leftIR.UPPER_BOUND);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new UpdateUS());
	}
}
