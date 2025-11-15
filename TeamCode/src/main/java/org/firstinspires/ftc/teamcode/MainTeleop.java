package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.intake.Intake;
import org.firstinspires.ftc.teamcode.magazine.Magazine;
import org.firstinspires.ftc.teamcode.shooter.Shooter;

@TeleOp(name="Main teleop", group="FTC 26")
public class MainTeleop extends OpMode{

    DriveTrain driveTrain;
    Shooter shooter;
    Magazine magazine;
    Intake intake;

    @Override
    public void init() {
     /*   driveTrain = new TankDrive(
                hardwareMap.get(DcMotor.class, "rearLeft"),
                hardwareMap.get(DcMotor.class, "rearRight"),
                hardwareMap.get(DcMotor.class, "frontLeft"),
                hardwareMap.get(DcMotor.class, "frontRight")
        );

       shooter = new Shooter(hardwareMap.get(DcMotor.class, "shooter"), hardwareMap.get(Servo.class, "magazine"));
       */
        //intake = new Intake(hardwareMap.get(CRServo.class, "intakeLeft"), hardwareMap.get(CRServo.class, "intakeRight"));
       magazine = new Magazine(hardwareMap.get(Servo.class, "ballChamber"), hardwareMap.get(Servo.class, "ballDoor"));
    }

    @Override
    public void loop() {
        //driveTrain.drive(gamepad1);
        //shooter.shoot(gamepad1);
        //intake.intake(gamepad1);
        magazine.run(gamepad1);
    }
}

