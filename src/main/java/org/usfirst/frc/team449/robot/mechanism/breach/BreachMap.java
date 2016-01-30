package org.usfirst.frc.team449.robot.mechanism.breach;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * a map of constants needed for any form of Drive or its subclasses, and not defined higher in the hierarchy
 */
public class BreachMap extends MechanismMap {
	public final Motor motor;
	public final LimitSwitch upper;
	public final LimitSwitch lower;

	/**
	 * creates a new Breach Map based on the configuration in the given json
     * any maps in here are to be shared across all breaching subsystems
     * @param json a JSONObject containing the configuration for the maps in this object
	 */
	public BreachMap(JSONObject json) {
		super(json);
        String path = this.getPath();
        path += ".components";
        this.motor = new Motor(json, path+".motors.instances.motor");
        this.upper = new LimitSwitch(json, path+"limitswitches.instances.upper");
        this.lower = new LimitSwitch(json, path+"limitswitches.instances.lower");
	}

    @Override
    public String getPath() {
        return super.getPath() + ".breach";
    }
}
