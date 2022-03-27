// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


    
 public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
 
  
  
  
  
  


  public Limelight() {

}
public double getTv(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
}
public double getTx(){
   return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
}
public double getTy(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
}
public double getTa(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
}
public double getTs(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
}
public double getTl(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);
}
public double gettShort(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(0);
}
public double gettLong(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong").getDouble(0);
}
public double getthor(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0);
}
public double gettvert(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0);
}
public double getPipe(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("getPipe").getDouble(0);
}
public double getCamTran(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("camtran").getDouble(0);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
