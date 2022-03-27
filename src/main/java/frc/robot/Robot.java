// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autonomousCommand;

  private RobotContainer robotContainer;

  private Drivetrain driveTrain;

  private Limelight limelight;

  private Timer timer;

  I2C.Port i2cPort = I2C.Port.kOnboard;

  ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  CANSparkMax hoodNEO;




  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
    driveTrain = new Drivetrain();
    limelight = new Limelight();
    timer = new Timer();
    

   

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    timer.reset();
    timer.start();


    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

   
    System.out.println(limelight.getTa());
    
   
    if(limelight.getTv() == 0){
      driveTrain.robotDrive.driveCartesian(0,0,0);
    }
   else if(limelight.getTa() > 10 && limelight.getTa()<15) {
    driveTrain.robotDrive.driveCartesian(0,0,0);
    }

    else if(limelight.getTa() > 15) {
      driveTrain.robotDrive.driveCartesian(0,0.25,0);
      }
    
    else if(limelight.getTa()<5){
      driveTrain.robotDrive.driveCartesian(0, -0.35, 0);
    }
    else if(Math.abs(limelight.getTx()) < 0.875 && limelight.getTv() == 1 ){
      driveTrain.robotDrive.driveCartesian(0,-0.25,0);
      }
    else if(limelight.getTx()<0){
      driveTrain.robotDrive.driveCartesian(0,0,-0.25);
    }
    else if(limelight.getTx()>0){
      driveTrain.robotDrive.driveCartesian(0, 0, 0.25);
    }

    
    
   
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

   
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    Color detectedColor = colorSensor.getColor();

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    
    

    driveTrain.robotDrive.driveCartesian(robotContainer.xboxController.getRawAxis(0), robotContainer.xboxController.getRawAxis(1), robotContainer.xboxController.getRawAxis(4)*0.5);


    hoodNEO = new CANSparkMax(Constants.HOOD_NEO_PORT, MotorType.kBrushless);

    double adjust = robotContainer.xboxController.getRawAxis(5);

    hoodNEO.set(adjust);


    

    

    
    System.out.println(limelight.getTa());

    
    // driveTrain.robotDrive.driveCartesian(robotContainer.joystick.getRawAxis(0), robotContainer.joystick.getRawAxis(1), robotContainer.joystick2.getRawAxis(2));
   
    


    if(robotContainer.xboxController.getAButton()){

      
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      



    }
    else if(robotContainer.xboxController.getBButton()){

      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    }

    

   



  
  

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
