package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {
    private MotorEx motorI;
    private String status = "";

    public Intake(HardwareMap hwMap) {
        motorI = new MotorEx(hwMap,"motorI");
        motorI.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void forward() {
        motorI.set(1);
        status = "Forward";
    }
    public void reverse() {
        motorI.set(-1);
        status = "Reverse";
    }
    public void disable() {
        motorI.set(0);
        status = "Disabled";
    }
    public String getStatus() {
        return status;
    }
}
