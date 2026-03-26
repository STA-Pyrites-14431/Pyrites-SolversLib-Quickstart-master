package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Drive extends SubsystemBase {
    private final MotorEx motorFL, motorFR, motorBL, motorBR;
    private final MecanumDrive mec;
    private final GoBildaPinpointDriver ODM;

    public Drive (HardwareMap hardwareMap, Telemetry tel) {
        motorFL = new MotorEx(hardwareMap,"motorFL", Motor.GoBILDA.RPM_312); //EH0
        motorFR = new MotorEx(hardwareMap,"motorFR",Motor.GoBILDA.RPM_312); //CH0
        motorBL = new MotorEx(hardwareMap,"motorBL",Motor.GoBILDA.RPM_312); //EH1
        motorBR = new MotorEx(hardwareMap,"motorBR",Motor.GoBILDA.RPM_312); //CH1

        motorFL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motorFR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motorBL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motorBR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        motorBR.setInverted(true);
        motorBL.setInverted(true);
        motorFL.setInverted(true);

        mec = new MecanumDrive(motorFL,motorFR,motorBL,motorBR);

        ODM = hardwareMap.get(GoBildaPinpointDriver.class,"ODM");
        ODM.setOffsets(55,-168, DistanceUnit.MM);
        ODM.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        ODM.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        ODM.resetPosAndIMU();

        Pose2D start = new Pose2D(DistanceUnit.INCH,-60,-24, AngleUnit.DEGREES,0);
//        ODM.setPosition(start);
    }
    public void fieldCentricDrive(double y, double x, double xr) {
        mec.driveFieldCentric(y,x,xr,ODM.getHeading(AngleUnit.DEGREES));
    }
    public void driveRobotCentric(double y, double x, double xr) {
        mec.driveRobotCentric(y,x,xr);
    }
    public double getX(DistanceUnit d) {
        return ODM.getPosX(d);
    }
    public double getY(DistanceUnit d) {
        return ODM.getPosY(d);
    }
    public double getH(AngleUnit d) {
        return ODM.getHeading(d);
    }
    public void updateOdom() {
        ODM.update();
    }
    public void stop() {
        mec.stop();
    }
    public void setStart(Pose2D start) {
        ODM.setPosition(start);
    }
    public void resetPose() {
        ODM.setPosition(new Pose2D(DistanceUnit.INCH,0,0,AngleUnit.DEGREES,0));
    }
    public void resetX() {
        ODM.setPosition(new Pose2D(DistanceUnit.INCH,0,ODM.getPosY(DistanceUnit.INCH),AngleUnit.DEGREES,ODM.getHeading(AngleUnit.DEGREES)));
    }
    public void resetY() {
        ODM.setPosition(new Pose2D(DistanceUnit.INCH,ODM.getPosX(DistanceUnit.INCH),0,AngleUnit.DEGREES,ODM.getHeading(AngleUnit.DEGREES)));
    }
    public void resetH() {
        ODM.setPosition(new Pose2D(DistanceUnit.INCH,ODM.getPosX(DistanceUnit.INCH),ODM.getPosY(DistanceUnit.INCH),AngleUnit.DEGREES,0));
    }
    public Pose2D getPos2D() {
        return ODM.getPosition();
    }
}
