package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;

import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrailMapView extends View {

    private List<float[]> points = new ArrayList<>();
    private List<String> strings=new ArrayList<>();
    private TextView info;
    private Paint pointPaint;
    private Paint firstPointPaint;
    private Paint lastPointPaint;
    private Paint springPaint;
    private Paint trianglePaint;
    private int clickedPointIndex = -1;
    public TrailMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pointPaint = new Paint();
        pointPaint.setColor(0xFF185351); // Dark green color
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setAntiAlias(true);

        firstPointPaint = new Paint();
        firstPointPaint.setColor(0xFFFF0000); // Red color for the first point
        firstPointPaint.setStyle(Paint.Style.FILL);
        firstPointPaint.setAntiAlias(true);

        lastPointPaint = new Paint();
        lastPointPaint.setColor(0xFF0000FF); // Blue color for the last point
        lastPointPaint.setStyle(Paint.Style.FILL);
        lastPointPaint.setAntiAlias(true);

        springPaint = new Paint();
        springPaint.setColor(0xFF87CEFA); // Light blue color for springs
        springPaint.setStyle(Paint.Style.FILL);
        springPaint.setAntiAlias(true);

        trianglePaint = new Paint();
        trianglePaint.setColor(0xFF8B4513); // Brown color for triangles
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setAntiAlias(true);
    }

    // Set the points to be drawn
    public void setPoints(List<float[]> points,List<String> strings,TextView info) {
        this.points = points;
        this.strings=strings;
        this.info=info;
        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get dimensions of the view
        int width = getWidth();
        int height = getHeight();

        if (points.isEmpty()) return;

        float margin = 10; // Prevent edge cutting

        for (int i = 0; i < points.size(); i++) {
            float[] point = points.get(i);

            // Scale the coordinates to fit within the view, with margin
            float x = point[0] * (width - 2 * margin) + margin; // Adjust X
            float y = point[1] * (height - 2 * margin) + margin; // Adjust Y

            if (i == 0) {
                // Draw the first point in red
                canvas.drawCircle(x, y, 20, firstPointPaint);
            } else if (i == points.size() - strings.size()+1) {
                // Draw the last point in blue
                canvas.drawCircle(x, y, 20, lastPointPaint);
            } else if (point[2] == 1) {
                // Draw a light blue circle for springs
                canvas.drawCircle(x, y, 15, springPaint);
            } else if (point[2] == 2) {
                // Draw a brown triangle
                Path triangle = new Path();
                triangle.moveTo(x, y - 15); // Top of the triangle
                triangle.lineTo(x - 15, y + 15); // Bottom-left
                triangle.lineTo(x + 15, y + 15); // Bottom-right
                triangle.close();
                canvas.drawPath(triangle, trianglePaint);
            } else {
                // Draw all other points in green
                canvas.drawCircle(x, y, 5, pointPaint);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Get touch position
        float touchX = event.getX();
        float touchY = event.getY();

        // Get dimensions of the view
        int width = getWidth();
        int height = getHeight();
        float margin = 10; // Prevent edge cutting

        // Loop through points and check if touch is near any of them
        for (int i = 0; i < points.size(); i++) {
            float[] point = points.get(i);

            // Scale the coordinates to match the view's scale
            float x = point[0] * (width - 2 * margin) + margin;
            float y = point[1] * (height - 2 * margin) + margin;

            // Check if the touch position is near the point (within a small radius)
            float radius = 50; // Click tolerance radius
            if (Math.abs(touchX - x) <= radius && Math.abs(touchY - y) <= radius) {
                clickedPointIndex = i; // Store index of clicked point
                invalidate();
                showPointDetails(i); // Show details for the last point

                break;
            }
        }
        return true;
    }

    private void showPointDetails(int index) {
        // Example of showing a Toast with the point's information
        String message = "Clicked on point " + index;
        if (index==0)
            info.setText(strings.get(0));
        if(index-points.size()+strings.size()>=2)
            info.setText(  strings.get(index-points.size()+strings.size()));
        if (index-points.size()+strings.size()==1)
            info.setText(strings.get(1));

    }
}
