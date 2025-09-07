package org.firstinspires.ftc.teamcode.vision;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class Webcam {
    /**
     * Variables to store the position and orientation of the camera on the robot. Setting these
     * values requires a definition of the axes of the camera and robot:
     *
     * Camera axes:
     * Origin location: Center of the lens
     * Axes orientation: +x right, +y down, +z forward (from camera's perspective)
     *
     * Robot axes (this is typical, but you can define this however you want):
     * Origin location: Center of the robot at field height
     * Axes orientation: +x right, +y forward, +z upward
     *
     * Position:
     * If all values are zero (no translation), that implies the camera is at the center of the
     * robot. Suppose your camera is positioned 5 inches to the left, 7 inches forward, and 12
     * inches above the ground - you would need to set the position to (-5, 7, 12).
     *
     * Orientation:
     * If all values are zero (no rotation), that implies the camera is pointing straight up. In
     * most cases, you'll need to set the pitch to -90 degrees (rotation about the x-axis), meaning
     * the camera is horizontal. Use a yaw of 0 if the camera is pointing forwards, +90 degrees if
     * it's pointing straight left, -90 degrees for straight right, etc. You can also set the roll
     * to +/-90 degrees if it's vertical, or 180 degrees if it's upside-down.
     */
    private Position cameraPosition;
    private YawPitchRollAngles cameraOrientation;
    private AprilTagProcessor aprilTagProcessor;
    private AnnotationProcessor annotationProcessor;
    private VisionPortal portal;
    private Telemetry telemetry;
    private List<AprilTagDetection> tags;

    public Webcam(WebcamName webcamName, Position cameraPosition, YawPitchRollAngles cameraOrientation, Telemetry telemetry) {
        this.cameraPosition = cameraPosition;
        this.cameraOrientation = cameraOrientation;
        this.telemetry = telemetry;
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setCameraPose(cameraPosition, cameraOrientation)
                .build();
        annotationProcessor = new AnnotationProcessor();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(webcamName);
        builder.addProcessor(aprilTagProcessor);
        builder.addProcessor(annotationProcessor);
        portal = builder.build();
    }

    @SuppressLint("DefaultLocale")
    public void run() {
        annotationProcessor.clear();
        tags = aprilTagProcessor.getDetections();
        telemetry.addData("Detected AprilTags", tags.size());
        portal.resumeStreaming();
        for (AprilTagDetection tag : tags) {
            annotationProcessor.addText("Angle", Double.toString(Math.toDegrees(angleTo(getOffset(tag.id)))));
            annotationProcessor.addLabel(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                        tag.ftcPose.x,
                        tag.ftcPose.y,
                        tag.ftcPose.z));
            if (tag.metadata != null) {
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                        tag.ftcPose.x,
                        tag.ftcPose.y,
                        tag.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                        tag.ftcPose.pitch,
                        tag.ftcPose.roll,
                        tag.ftcPose.yaw));
            }
            else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", tag.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", tag.center.x, tag.center.y));
            }
        }
    }

    public AprilTagPoseFtc getOffset(int id) {
        if (tags == null) return null;
        for (AprilTagDetection tag : tags) {
            if (tag.id != id) continue;
            return tag.ftcPose;
        }
        return null;
    }

    public double angleTo(AprilTagPoseFtc target) {
        if (target.y == 0) return 0;
        double angle = Math.tan(target.x / target.y);
        return angle;
    }
}
