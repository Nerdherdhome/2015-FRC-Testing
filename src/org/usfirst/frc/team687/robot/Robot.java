
package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.image.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	DoubleSolenoid primeSol;
	Compressor compress;
	Talon talon0;
	Victor victor1;
	Joystick joy;
	PowerDistributionPanel pdp;
	AxisCamera cam;
	Image picture;
	ColorImage pics;
	FileReader fr;
	
    public void robotInit() {
    	
    	/**
    	 * Create the objects
    	 */
    	primeSol = new DoubleSolenoid(2,3);
        compress = new Compressor();
        talon0 = new Talon(0);
        victor1 = new Victor(1);
    	joy = new Joystick(1);
    	pdp = new PowerDistributionPanel();
//    	cam = new AxisCamera("10.6.87.11");
//    	try {
//    		//Open the file
////    		File file = new File("/dev/serial/by-path/");
////    		SmartDashboard.putString("FILE", file.listFiles()[0].getAbsolutePath());
////			fr = new FileReader(file.listFiles()[0]);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	

    	/**
    	 * Start the compressor
    	 */
    	compress.start();
    	
    	/**
    	 * Save camera image to file
    	 */
    
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }
    
    public void teleopInit()	{
    	//start the arduino

    }


    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
      	double power;
      	double current;
      	
//    	try{
//	    	Image picture = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB_U64, 0);
//	    	cam.getImage(picture);
//	    	BinaryImage thresholdImage = pics.thresholdHSV(105, 137, 230, 255, 133, 183);
//	    	//picture = NIVision.imaqEasyAcquire("cam0");
//	    	//NIVision.imaqWriteJPEGFile(picture, "XXX.jpeg", 750, NIVision.imaqReadCustomData(picture, "cam0"));
//    	}catch(Exception e){
//    		SmartDashboard.putString("Vision Exception",e.toString());
//    	}
    	
    	/**
    	 * Test Talons
    	 */
        power = -joy.getY();
        talon0.set(power);
        
        /**
         * Test Victor
         */
        power = -joy.getY();
        victor1.set(power);
        
        /**
         * Test Pneumatics
         */
        if(joy.getRawButton(3)){
    		primeSol.set(DoubleSolenoid.Value.kForward); //move solenoid out
    	}	
        else if(joy.getRawButton(4)){
    		primeSol.set(DoubleSolenoid.Value.kReverse);; //move solenoid back in
    	}
        
        /** 
         * Display current to SmartDashboard
         */
    	current = pdp.getCurrent(14);
    	double voltage = pdp.getVoltage();
        SmartDashboard.putNumber("Current Channel 14",current);
        SmartDashboard.putNumber("Voltage",voltage);
        SmartDashboard.putNumber("Joy", power);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}