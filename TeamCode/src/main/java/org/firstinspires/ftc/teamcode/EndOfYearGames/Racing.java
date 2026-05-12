package org.firstinspires.ftc.teamcode.EndOfYearGames;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

public class Racing extends OpMode {

    private MotorEx high1, high2, low1, low2, turn;
    int gear;
    private final int steeringCoef = 10;

    @Override
    public void init() {
        high1 = new MotorEx(hardwareMap,"high1");
//        high2 = new MotorEx(hardwareMap,"high2");
        low1 = new MotorEx(hardwareMap,"low1");
        low2 = new MotorEx(hardwareMap,"low2");
        turn = new MotorEx(hardwareMap,"turn", Motor.GoBILDA.RPM_312);
        gear = 1;

        high1.setRunMode(Motor.RunMode.RawPower);
        low1.setRunMode(Motor.RunMode.RawPower);
        low2.setRunMode(Motor.RunMode.RawPower);
        turn.setRunMode(Motor.RunMode.PositionControl);
    }

    @Override
    public void loop() {
        MotorGroup low = new MotorGroup(low1,low2);
        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
        if (gear == 1) {
            low.set(speed);
        } else if (gear == 2) {
            high1.set(speed);
        }
        if (gamepad1.xWasPressed()) {
            changeGear();
        }

        turn.setTargetPosition((int)(steeringCoef*gamepad1.left_stick_x));
    }

    public void changeGear() {
        if (gear == 1) {
            high1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
            low1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            low2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            gear = 2;
        } else {
            high1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            low1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
            low2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
            gear = 1;
        }
    }
    public void updateTel() {
        telemetry.addData("Turn Pos: ",turn.getCurrentPosition());
    }
}
