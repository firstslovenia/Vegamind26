package org.firstinspires.ftc.teamcode.shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Shooter {
    private DcMotor motor;
    public Shooter(DcMotor motor) {
       this.motor = motor;

       motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void shoot(Gamepad gamepad) {
        if (gamepad.left_trigger != 0) {
            motor.setPower(1.0f);
        } else {
            motor.setPower(0.0f);
        }
    }
}

