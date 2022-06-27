package utils;

public class MathUtils {

    public static class Vector2 {
        public float x;
        public float y;

        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Vector2() {
            this(0, 0);
        }

        public Vector2 add(Vector2 other) {
            return new Vector2(this.x + other.x, this.y + other.y);
        }

        public Vector2 subtract(Vector2 other) {
            return new Vector2(this.x - other.x, this.y - other.y);
        }

        public Vector2 multiply(float scalar) {
            return new Vector2(this.x * scalar, this.y * scalar);
        }

        public Vector2 divide(float scalar) {
            return new Vector2(this.x / scalar, this.y / scalar);
        }

        public float norm2() {
            return this.x * this.x + this.y * this.y;
        }

        public float norm() {
            return (float) Math.sqrt(this.norm2());
        }

        public Vector2 normalize() {
            return this.divide(this.norm());
        }

        public float dot(Vector2 other) {
            return this.x * other.x + this.y * other.y;
        }

        public float cross(Vector2 other) {
            return this.x * other.y - this.y * other.x;
        }
    }
    
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public static Vector2 lineIntersection(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        float denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        float ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
        if (ua > 0 && ua < 1 && ub > 0 && ub < 1) {
            // return the position of the intersection.
            return new Vector2(x1 + ua * (x2 - x1), y1 + ua * (y2 - y1));
        }
        return null;
    }
}
