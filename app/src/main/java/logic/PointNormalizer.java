package logic;
import java.util.ArrayList;
import java.util.List;

public class PointNormalizer {

    public static List<float[]> normalizePoints(List<Point> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("Points list cannot be null or empty.");
        }

        // Find min and max values for latitude and longitude
        double minLat = Double.MAX_VALUE, maxLat = Double.MIN_VALUE;
        double minLon = Double.MAX_VALUE, maxLon = Double.MIN_VALUE;

        for (Point point : points) {
            minLat = Math.min(minLat, point.getLatitude());
            maxLat = Math.max(maxLat, point.getLatitude());
            minLon = Math.min(minLon, point.getLongitude());
            maxLon = Math.max(maxLon, point.getLongitude());
        }

        // Normalize each point
        List<float[]> normalizedPoints = new ArrayList<>();
        for (Point point : points) {
            float normalizedX = (float) ((point.getLongitude() - minLon) / (maxLon - minLon));
            float normalizedY = (float) ((point.getLatitude() - minLat) / (maxLat - minLat));
            normalizedPoints.add(new float[]{normalizedX, normalizedY,(float) point.getType()});
        }

        return normalizedPoints;
    }
}
