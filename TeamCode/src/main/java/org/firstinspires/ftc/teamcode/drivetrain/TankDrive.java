package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class TankDrive extends DriveTrain {

    public TankDrive(DcMotor backLeft, DcMotor backRight, DcMotor frontLeft, DcMotor frontRight) {
        super(backLeft, backRight, frontLeft, frontRight);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void drive(Gamepad gamepad) {
        backRight.setPower(gamepad.left_stick_y);
        backLeft.setPower(-gamepad.right_stick_y);
    }
}
