package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Ramp extends SubsystemBase {
    private MotorEx motorR;
    private String status = "";

    public Ramp(HardwareMap hwMap) {
        motorR = new MotorEx(hwMap,"motorR");
        motorR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void forward() {
        motorR.set(0.5);
        status = "Forward";
    }
    public void reverse() {
        motorR.set(-0.5);
        status = "Reverse";
    }
    public void disable() {
        motorR.set(0);
        status = "Disabled";
    }
    public String getStatus() {
        return status;
    }
}
