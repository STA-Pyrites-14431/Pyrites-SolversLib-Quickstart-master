package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class DriveToX extends CommandBase {
    private Drive driveS;
    private double dx, X, speed;

    public DriveToX(Drive driveS, double x) {
        this.driveS = driveS;
        this.dx = x;
    }

    @Override
    public void initialize() {
        X = driveS.getX(DistanceUnit.INCH);
    }
    @Override
    public void execute() {
        if (dx < X) {
            speed = -0.5;
        } else {
            speed = 0.5;
        }
        driveS.fieldCentricDrive(0,speed,0);
        driveS.updateOdom();
        X = driveS.getX(DistanceUnit.INCH);
    }
    @Override
    public void end (boolean interrupted) {
        driveS.stop();
    }
    @Override
    public boolean isFinished() {
        return !(X>dx-0.3 && X<dx+0.3);
    }
}
