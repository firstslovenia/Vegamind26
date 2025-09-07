package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.vision.Webcam;

@TeleOp(name="Main teleop", group="FTC 26")
public class MainTeleop extends OpMode {
    DriveTrain driveTrain;
    Webcam webcam;

    @Override
    public void init() {
        driveTrain = new TankDrive(
                hardwareMap.get(DcMotor.class, "rearLeft"),
                hardwareMap.get(DcMotor.class, "rearRight"),
                hardwareMap.get(DcMotor.class, "frontLeft"),
                hardwareMap.get(DcMotor.class, "frontRight")
        );
        webcam = new Webcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"),
                new Position(DistanceUnit.INCH, 0, 0, 5.5, 0),
                new YawPitchRollAngles(AngleUnit.DEGREES, 0, -90, 0, 0),
                telemetry
        );
    }

    @Override
    public void init_loop() {
        webcam.run();
    }

    @Override
    public void loop() {
        driveTrain.drive(gamepad1);
        webcam.run();
        telemetry.update();
    }
}