package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Launcher extends SubsystemBase {
    private MotorEx motorLL, motorLR;
    private String status = "";

    public Launcher(HardwareMap hwMap) {
        motorLL = new MotorEx(hwMap,"motorLL");
        motorLR = new MotorEx(hwMap,"motorLR");
        motorLL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motorLR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motorLR.setInverted(true);
    }

    public void enable(double P) {
        motorLL.set(P);
        motorLR.set(P);
        status = "Enabled";
    }
    public void enable() {
        motorLL.set(0.35);
        motorLR.set(0.35);
    }
    public void disable() {
        motorLL.set(0);
        motorLR.set(0);
        status = "Disabled";
    }
    public String getStatus() {
        return status;
    }
    public double getSpeed() {
        return motorLL.getVelocity();
    }
}
