package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.vegamind.input.PrimaryInputMap;
import org.firstinspires.ftc.teamcode.vegamind.input.SecondaryInputMap;

public class RobotCentricDrivetrain extends Drivetrain {
    public RobotCentricDrivetrain( HardwareMap hardwareMap, IMU imu, Motors motors) {
        super(hardwareMap, imu, motors);
    }

    @Override
    public void run(Gamepad gamepad) {
        double y = primaryInputMap.getDriveY();
        double x = primaryInputMap.getDriveX() * 1.1;
        double rx = primaryInputMap.getRotation();

        if (x == 0 && y == 0 && rx == 0) {
            y = secondaryInputMap.getDriveY();
            x = secondaryInputMap.getDriveX() * 1.1;
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        motors.frontLeft.setPower(frontLeftPower);
        motors.rearLeft.setPower(backLeftPower);
        motors.frontRight.setPower(frontRightPower);
        motors.rearRight.setPower(backRightPower);
    }
}
