package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.drivetrain.TankDrive;

@TeleOp(name="Main teleop", group="FTC 26")
public class MainTeleop extends OpMode{

    DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new TankDrive(
                hardwareMap.get(DcMotor.class, "rearLeft"),
                hardwareMap.get(DcMotor.class, "rearRight"),
                hardwareMap.get(DcMotor.class, "frontLeft"),
                hardwareMap.get(DcMotor.class, "frontRight")
        );
    }

    @Override
    public void loop() {
        driveTrain.drive(gamepad1);
    }
}

