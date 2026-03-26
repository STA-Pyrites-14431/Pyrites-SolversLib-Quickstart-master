package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class DriveToY extends CommandBase {
    private final Drive driveS;
    private double dy, Y, speed;

    public DriveToY(Drive driveS, double y) {
        this.driveS = driveS;
        this.dy = y;
    }

    @Override
    public void initialize() {
        Y = driveS.getY(DistanceUnit.INCH);
    }
    @Override
    public void execute() {
        if (dy > Y) {
            speed = -0.5;
        } else {
            speed = 0.5;
        }
        driveS.fieldCentricDrive(speed,0,0);
        driveS.updateOdom();
        Y = driveS.getY(DistanceUnit.INCH);
    }
    @Override
    public void end (boolean interrupted) {
        driveS.stop();
    }
    @Override
    public boolean isFinished() {
        return !(Y>dy-0.3 && Y<dy+0.3);
    }
}
