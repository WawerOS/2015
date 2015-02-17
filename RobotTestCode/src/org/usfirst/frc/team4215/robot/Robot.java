package org.usfirst.frc.team4215.robot;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot { 
	
	// Talon def
	Talon frontLeft = new Talon(0);
	Talon backLeft = new Talon(1);
	Talon backRight = new Talon(2);			
	Talon frontRight = new Talon(3);
	
	Ultrasonic rangefinder = new Ultrasonic(2,1);
	
	Timer time = new Timer();
	
	double frontLeftDriveValue = 0.0;
	double frontRightDriveValue = 0.0;
	double backLeftDriveValue = 0.0;
	double backRightDriveValue = 0.0;
		
	
	private final double maxInputDriver = 1.0;
    private final double minInputDriver = 0.0;
		
    public void operatorControl() {   	
        while (isOperatorControl() && isEnabled()) {
        }
    }
        
    public void autonomous() {

    	boolean autoComplete = false;
        while (isAutonomous() && isEnabled() && !autoComplete) {	
        	try {
        		autonomousA();
        	}
        	catch(Exception e) {
        		SmartDashboard.putString("Exception", e.getMessage());
        	}
        	finally {
        		autoComplete = true;
        	}
        }
    }

    public void autonomousA() {
//    	RobotDrive myRobotDrive = new RobotDrive(0, 1, 2, 3);
//    	Gyro gyro = new Gyro(0);
//    	gyro.initGyro();
    	//TODO: gyro sampling rate
    	    	
    	// move foward to tote
    	while(rangefinder.getRangeMM() < 500) {
//    		myRobotDrive.mecanumDrive_Cartesian(.25, 0, 0, gyro.getAngle());
    		frontLeft.set(.2);
    		backLeft.set(.2);
    		backRight.set(.2);			
    		frontRight.set(.2);
    	}
//    	myRobotDrive.stopMotor();
		frontLeft.set(0);
		backLeft.set(0);
		backRight.set(0);			
		frontRight.set(0);
    }

    /** 
     * Runs during test mode
     */
    public void test() {    	
    }
}
