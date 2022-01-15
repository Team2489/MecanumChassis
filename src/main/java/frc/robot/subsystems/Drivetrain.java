// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
   
  WPI_TalonSRX rightFrontMotor;
  WPI_TalonSRX rightBackMotor;
  WPI_TalonSRX leftFrontMotor;
  WPI_TalonSRX leftBackMotor;

  public MecanumDrive robotDrive;

  
  public Drivetrain() {

    rightFrontMotor = new WPI_TalonSRX(Constants.RIGHT_FRONT_MOTOR_PORT);
    rightBackMotor = new WPI_TalonSRX(Constants.RIGHT_BACK_MOTOR_PORT);
    leftFrontMotor = new WPI_TalonSRX(Constants.LEFT_FRONT_MOTOR_PORT);
    leftBackMotor = new WPI_TalonSRX(Constants.LEFT_BACK_MOTOR_PORT);
    
    
 

    robotDrive = new MecanumDrive(rightFrontMotor, rightBackMotor, leftFrontMotor, leftBackMotor);
     
    


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
