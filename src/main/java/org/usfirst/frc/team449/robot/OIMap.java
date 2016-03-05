package org.usfirst.frc.team449.robot;

import org.json.JSONObject;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and
 * not defined higher in the hierarchy
 */

public class OIMap extends RobotMap {

    public int LEFT_DRIVE_STICK;

    public int RIGHT_DRIVE_STICK;

    public int MAIN_CONTROLLER;

    public int INTAKE_IN;

    public int INTAKE_OUT;
    
    public int INTAKE_UP;
    
    public int INTAKE_DOWN;
    
    public int BREACH_CHIVAL;
    
    public int BREACH_PORTCULLIS;
    
    public int BREACH_CLOSE;
    
    public int CAMERA_TOGGLE;
    
    public int DRIVE_STRAIGHT;

    public double DEADBAND;

    public double POWER;

    public double MAX_VALUE;
    
    /**
     * creates a new Map based on the configuration in the given json
     * any maps in here are to be shared across all subsystems
     *
     * @param json a JSONObject containing the configuration for the maps in this object
     */
    public OIMap(JSONObject json) {
        super(json);
    }
}
