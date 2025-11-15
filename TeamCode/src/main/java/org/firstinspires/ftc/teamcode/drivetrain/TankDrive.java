package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class TankDrive extends Drivetrain {

    public TankDrive(Motors motors) {
        super(null, null, motors);
        motors.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void drive(Gamepad gamepad) {
        motors.rearRight.setPower(gamepad.left_stick_y);
        motors.frontRight.setPower(gamepad.left_stick_y);

        motors.rearLeft.setPower(-gamepad.right_stick_y);
        motors.frontLeft.setPower(-gamepad.right_stick_y);
    }
}
