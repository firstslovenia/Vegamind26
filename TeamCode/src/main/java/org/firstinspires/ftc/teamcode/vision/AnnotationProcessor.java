package org.firstinspires.ftc.teamcode.vision;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

public class AnnotationProcessor implements VisionProcessor {
    List<String> texts = new ArrayList<>();

    private Paint paint;
    private Object textLock = new Object();

    public AnnotationProcessor() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        return null;
    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        if (canvas == null) return;
        paint.setTextSize(50);
        int idx = 1;
        synchronized (textLock) {
            for (String text : texts) {
                canvas.drawText(text, onscreenWidth/20, onscreenWidth/20+(idx*60), paint);
                idx += 1;
            }
        }
    }

    public void clear() {
        synchronized (textLock) {
            texts.clear();
        }
    }

    public void addText(String label, String value) {
        synchronized (textLock) {
            if (label == null || value == null) return;
            texts.add(label + ": " + value);
        }
    }

    public void addLabel(String label) {
        synchronized (textLock) {
            if (label == null) return;
            texts.add(label);
        }
    }
}