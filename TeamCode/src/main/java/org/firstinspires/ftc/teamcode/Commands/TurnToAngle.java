package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class TurnToAngle extends CommandBase {
    private Drive driveS;
    private double dA, heading, speed;

    public TurnToAngle(Drive driveS, double A) {
        this.driveS = driveS;
        this.dA = A;
    }

    @Override
    public void initialize() {
        heading = driveS.getH(AngleUnit.DEGREES);
    }
    @Override
    public void execute() {
        if (dA > heading) {
            speed = -0.4;
        } else {
            speed = 0.4;
        }
        driveS.fieldCentricDrive(0,0,speed);
        driveS.updateOdom();
        heading = driveS.getH(AngleUnit.DEGREES);
    }
    @Override
    public void end (boolean interrupted) {
        driveS.stop();
    }
    @Override
    public boolean isFinished() {
        return !(heading>dA-0.1 && heading<dA+0.1);
    }
}
