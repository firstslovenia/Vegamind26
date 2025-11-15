package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.vegamind.Hardware;
import org.firstinspires.ftc.teamcode.vegamind.input.PrimaryInputMap;
import org.firstinspires.ftc.teamcode.vegamind.input.SecondaryInputMap;

public abstract class Drivetrain extends SampleMecanumDrive {
    protected IMU imu;

    Motors motors;

    public Drivetrain(HardwareMap hardwareMap, IMU imu, Motors motors) {
        super(hardwareMap);
        this.imu = imu;
        imu.resetYaw();
        this.motors = motors;
    }

    public abstract void run(Gamepad gamepad);
}
