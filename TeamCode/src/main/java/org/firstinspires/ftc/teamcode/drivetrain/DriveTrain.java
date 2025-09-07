package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public abstract class DriveTrain {
    protected DriveTrain(DcMotor backLeft, DcMotor backRight, DcMotor frontLeft, DcMotor frontRight) {
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
    }
    public abstract void drive(Gamepad gamepad);
    DcMotor backLeft, backRight, frontLeft, frontRight;
}
