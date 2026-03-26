package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Commands.DriveToX;
import org.firstinspires.ftc.teamcode.Commands.DriveToY;
import org.firstinspires.ftc.teamcode.Commands.TurnToAngle;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Launcher;
import org.firstinspires.ftc.teamcode.Subsystems.Ramp;

@Autonomous(name = "AutoTest")
public class AutoTest extends CommandOpMode {
    @Override
    public void initialize() {
        Drive driveS = new Drive(hardwareMap, telemetry);
        Intake intakeS = new Intake(hardwareMap);
        Launcher launcherS = new Launcher(hardwareMap);
        Ramp rampS = new Ramp(hardwareMap);

        Pose2D start = new Pose2D(DistanceUnit.INCH,-60,-24, AngleUnit.DEGREES,0);
        driveS.setStart(start);

        Command x0 = new DriveToX(driveS,0);
        Command y0 = new DriveToY(driveS,0);
        Command t180 = new TurnToAngle(driveS, 180);
    }
}
