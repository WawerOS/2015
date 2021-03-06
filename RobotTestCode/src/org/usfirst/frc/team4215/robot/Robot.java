package org.usfirst.frc.team4215.robot;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
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
	
	Talon elevator1 = new Talon(4);
	Talon rackPinion = new Talon(6);
	Victor brake = new Victor(7);

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
        		AutonomousBinToteLift();
        	}
        	catch(Exception e) {
        		SmartDashboard.putString("Exception", e.getMessage());
        	}
        	finally {
        		autoComplete = true;
        	}
        }
    }

    public void AutonomousSetSafetyAllMotors(boolean flag)
    {
    	frontLeft.setSafetyEnabled(flag);
    	frontRight.setSafetyEnabled(flag);
    	backLeft.setSafetyEnabled(flag);
    	backRight.setSafetyEnabled(flag);
    	elevator1.setSafetyEnabled(flag);
    	rackPinion.setSafetyEnabled(flag);    	
    }
    
    public void AutonomousStopDriveTrain() {
		frontLeft.stopMotor();
    	backLeft.stopMotor();
    	backRight.stopMotor();
    	frontRight.stopMotor();   	
    }
    
    public void AutonomousBinToteLift() {
    	AnalogUltrasonic rangefinder = new AnalogUltrasonic(2);
    	
    	AutonomousSetSafetyAllMotors(false);
    	
    	//minimum input for rack to prevent the bin from slipping
    	rackPinion.set(-.02);
    	
    	// lift the bin
    	elevator1.set(-.75);
    	Timer.delay(1.25);
    	elevator1.stopMotor();
    	
//    	double delta = 0;
//
//    	// move the robot forward
//    	while(rangefinder.GetRangeInCM() < (delta + 45)) {
//    		frontLeft.set(.25);
//        	backLeft.set(.25);
//        	backRight.set(-.25);
//        	frontRight.set(-.25);
//    	}
//    	
//    	AutonomousStopDriveTrain();
//    	   	
//    	// open arms
//    	rackPinion.set(-.25);
//    	Timer.delay(.1);
//    	rackPinion.set(0);
//    	
//    	// move arms down to the tote level
//    	elevator1.set(.8);
//    	Timer.delay(.5);
//    	elevator1.stopMotor();
//    	
//    	// close arms
//    	rackPinion.set(.25);
//    	Timer.delay(.3);
//    	rackPinion.set(-.02);
//    	
//    	// lift tote
//    	elevator1.set(-.8);
//    	Timer.delay(.5);
//    	elevator1.stopMotor();
//    	
//    	// rotate robot
//    	frontLeft.set(-.25);
//    	backLeft.set(-.25);
//    	backRight.set(-.25);
//    	frontRight.set(-.25);
//    	Timer.delay(1);
//    	
//    	frontLeft.set(0);
//    	backLeft.set(0);
//    	backRight.set(0);
//    	frontRight.set(0);
//    	
//    	// move into the auto zone
//    	while(rangefinder.GetRangeInCM() < (delta + 400)) {
//    		frontLeft.set(.25);
//        	backLeft.set(.25);
//        	backRight.set(-.25);
//        	frontRight.set(-.25);
//    	}
//    	AutonomousStopDriveTrain();
    	
    	AutonomousSetSafetyAllMotors(true);
    }
    
    public void autonomousA() {
    	
    	AnalogUltrasonic rangefinder = new AnalogUltrasonic(2);
//    	RobotDrive myRobotDrive = new RobotDrive(0, 1, 2, 3);
//    	Gyro gyro = new Gyro(0);
//    	gyro.initGyro();
    	//TODO: gyro sampling rate
    	    	
    	// move foward to tote
    	while(rangefinder.GetRangeInCM() < 50) {
//    		frontLeft.set(.2);
//    		backLeft.set(.2);
//    		backRight.set(-.2);
//    		frontRight.set(-.2);
    		SmartDashboard.putNumber("rangeFinder dist", rangefinder.GetRangeInCM());
    		SmartDashboard.putNumber("rangeFinder voltage", rangefinder.GetVoltage());
    	}
//    	myRobotDrive.stopMotor();
    	frontLeft.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
		frontRight.stopMotor();
    }

    public void autonomousB() {
    	double range = 0.0;
        do {
//        	rangefinder.getSmartDashboardType();
        	double value = rangefinder.pidGet();
            range = (value / (5.0 / 512.0));                // The 5 is the voltage range, 512 is
    		frontLeft.set(.2);
    		backLeft.set(.2);
    		backRight.set(-.2);
    		frontRight.set(-.2);
    		SmartDashboard.putNumber("rangeFinder value", value);
    		SmartDashboard.putNumber("rangeFinder range", range);
        }
    	while(range < 500);

        frontLeft.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
		frontRight.stopMotor();
    }
        
    public void autonomousC() {
//    	RobotDrive myRobotDrive = new RobotDrive(0, 1, 2, 3);
    	Gyro gyro = new Gyro(0);
    	gyro.initGyro();
    	double degrees= 0.0;
		SmartDashboard.putNumber("gyro degrees", degrees);
    	    	
		frontLeft.set(-.4);
		backLeft.set(-.4);
		backRight.set(-.4);			
		frontRight.set(-.4);

		do {
    		degrees = gyro.getAngle();
    		SmartDashboard.putNumber("gyro degrees", degrees);
//    		myRobotDrive.mecanumDrive_Cartesian(.25, 0, 0, degrees);
    	} while (degrees < 90);
//    	myRobotDrive.stopMotor();

		frontLeft.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
		frontRight.stopMotor();
    }

    public void autonomousD() {
    	
    	I2CGyro gyro = new I2CGyro();
    	gyro.Init();
    	double degrees= 0.0;
		SmartDashboard.putNumber("gyro degrees", degrees);

		frontLeft.set(-.4);
		backLeft.set(-.4);
		backRight.set(-.4);			
		frontRight.set(-.4);

		do {
    		degrees = gyro.getDegrees();
    		SmartDashboard.putNumber("gyro degrees", degrees);
    	} while (degrees < 360);

		frontLeft.stopMotor();
		backLeft.stopMotor();
		backRight.stopMotor();
		frontRight.stopMotor();
    }

    public void autonomousTimeBased() {
//    	RobotDrive myRobotDrive = new RobotDrive(0, 1, 2, 3);
    	
    	// minimum input for rack to prevent the bin from slipping
    	rackPinion.set(-.02);
    	
    	// lift the bin
    	elevator1.set(.5);
    	Timer.delay(.5);
    	
    	// move foward to tote
    	frontLeft.set(-.25);
    	backLeft.set(-.25);
    	backRight.set(.25);
    	frontRight.set(.25);
    	Timer.delay(1);
    	
    	frontLeft.set(0);
    	backLeft.set(0);
    	backRight.set(0);
    	frontRight.set(0);
    	
    	// lower bin onto the tote
    	elevator1.set(-.25);
    	Timer.delay(.8);
    	
    	// open arms
    	rackPinion.set(.25);
    	Timer.delay(.3);
    	rackPinion.stopMotor();
    	
    	// move arms down to the tote level
    	elevator1.set(-.5);
    	Timer.delay(.2);
    	elevator1.stopMotor();
    	
    	// close arms
    	rackPinion.set(.3);
    	Timer.delay(.25);
    	rackPinion.set(-.02);
    	
    	// lift the tote
    	elevator1.set(.4);
    	Timer.delay(.1);
    	elevator1.stopMotor();
    	
    	// rotate the robot
    	frontLeft.set(-1);
    	backLeft.set(-1);
    	backRight.set(1);
    	frontRight.set(1);
    	Timer.delay(.25);
    	
    	// drive the robot forward into the auto zone
    	frontLeft.set(.5);
    	backLeft.set(.5);
    	backRight.set(.5);
    	frontRight.set(.5);
    	Timer.delay(2);
    	
    	frontLeft.set(.25);
    	backLeft.set(.25);
    	backRight.set(.25);
    	frontRight.set(.25);
    	Timer.delay(.5);
    	
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
