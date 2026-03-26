package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Launcher;
import org.firstinspires.ftc.teamcode.Subsystems.Ramp;

@TeleOp(name = "manualEX")
public class ManualEX extends OpMode {
    Drive driveS;
    Launcher launcherS;
    Intake intakeS;
    Ramp rampS;

    GamepadEx driver;
    GamepadEx operator;


    @Override
    public void init() {
        driveS = new Drive(hardwareMap, telemetry);
        launcherS = new Launcher(hardwareMap);
        intakeS = new Intake(hardwareMap);
        rampS = new Ramp(hardwareMap);

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        driver.readButtons();
        operator.readButtons();

        driveS.fieldCentricDrive(driver.getLeftX(),-driver.getLeftY(),driver.getRightX());

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER) || driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
            launcherS.enable(0.35);
        } else if (operator.wasJustPressed(GamepadKeys.Button.X) || driver.wasJustPressed(GamepadKeys.Button.X)) {
            launcherS.enable(0.5);
        } else if (operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.3 || driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.3) {
            launcherS.enable(1);
        } else if (operator.wasJustPressed(GamepadKeys.Button.Y) || driver.wasJustPressed(GamepadKeys.Button.Y)) {
            launcherS.disable();
        }

        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER) || driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            intakeS.forward();
        } else if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN) || driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            intakeS.reverse();
        } else if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT) || driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
            intakeS.disable();
        }

        if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP) || driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
            rampS.forward();
        } else if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN) || driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            rampS.reverse();
        } else if (operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT) || driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            rampS.disable();
        }

        telemetry();
    }

    public void telemetry() {
        telemetry.addData("XPos: ",driveS.getX(DistanceUnit.INCH));
        telemetry.addData("YPos: ",driveS.getY(DistanceUnit.INCH));
        telemetry.addData("Heading: ",driveS.getH(AngleUnit.DEGREES));
    }
}
