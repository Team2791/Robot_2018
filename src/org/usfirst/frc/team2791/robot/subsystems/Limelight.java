package org.usfirst.frc.team2791.robot.subsystems;


//import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableInstance;
import org.usfirst.frc.team2791.robot.RobotMap;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Limelight extends Subsystem {
    private NetworkTable table;

    double targetOffsetAngle_Horizontal;
    double targetOffsetAngle_Vertical;
    double targetArea;
    double targetSkew;
    boolean targetValid;
    double latency;

    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
    public void Limelight(){

        this.table = NetworkTable.getTable("limelight");
        this.targetOffsetAngle_Horizontal = table.getNumber("tx", 0);
        this.targetOffsetAngle_Vertical  = table.getNumber("ty", 0);
        this.targetArea = table.getNumber("ta", 0);
        this.targetSkew  = table.getNumber("ts", 0);
        this.targetValid = table.getNumber("tv", 0) == 1;
        this.latency = table.getNumber("tl", 0);
    }


    // Get Camera stats

    public double getOffsetAngleHorizontal(){
        return this.targetOffsetAngle_Horizontal;
    }
    public double getOffsetAngleVertical(){
        return this.targetOffsetAngle_Horizontal;
    }
    public double getTargetArea(){
        return this.targetArea;
    }
    public double getTargetSkew(){
        return this.targetSkew;
    }
    public boolean validTarget(){
        return this.targetValid;
    }
    public double getLatency(){
        return this.latency;
    }
}

