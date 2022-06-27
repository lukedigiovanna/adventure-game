package game;

import utils.MathUtils.Vector2;
import java.util.List;
import java.util.ArrayList;

public abstract class Entity {
    protected Vector2 position, dimension, velocity;
    protected Game game;
    protected boolean isRigid = true, hasGravity = true;

    public Entity(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2();
    }

    public void update(float dt) {
        // update velocity based on gravity
        if (hasGravity) {
            this.velocity.y += game.getGravity() * dt;
        }
        if (isRigid) {
            List<Wall> currWalls = game.getWalls();
            // move along the y-direction first
            this.position.y += this.velocity.y * dt;
            // now check for intersection with this object and the walls.
            List<Vector2> intersections = new ArrayList<Vector2>();
            for (Wall wall : currWalls) {
                if (wall.getType() == Wall.Type.SOLID || (this.velocity.y > 0 && wall.getType() == Wall.Type.PLATFORM)) {
                    intersections.addAll(wall.getIntersections(this));
                }
            }
            if (this.velocity.y > 0) {
                // we are moving down, so we want to check for the highest intersection (lowest value)
                float lowest = Float.MAX_VALUE;
                for (Vector2 intersection : intersections) {
                    if (intersection.y < lowest) {
                        lowest = intersection.y;
                    }
                }
                if (lowest != Float.MAX_VALUE) {
                    this.position.y = lowest - this.dimension.y;
                    this.velocity.y = 0;
                }
            } else if (this.velocity.y < 0) {
                // we are moving up, so we want to check for the lowest intersection (highest value)
                float highest = Float.MIN_VALUE;
                for (Vector2 intersection : intersections) {
                    if (intersection.y > highest) {
                        highest = intersection.y;
                    }
                }
                if (highest != Float.MIN_VALUE) {
                    this.position.y = highest;
                    this.velocity.y = 0;
                }
            }
            // now check x direction
            this.position.x += this.velocity.x * dt;
            intersections.clear();
            for (Wall wall : currWalls) {
                if (wall.getType() == Wall.Type.SOLID) {
                    intersections.addAll(wall.getIntersections(this));
                }
            }
            if (this.velocity.x > 0) {
                // we are moving right, so we want to check for the lowest intersection
                float lowest = Float.MAX_VALUE;
                for (Vector2 intersection : intersections) {
                    if (intersection.x < lowest) {
                        lowest = intersection.x;
                    }
                }
                if (lowest != Float.MAX_VALUE) {
                    this.position.x = lowest - this.dimension.x;
                    this.velocity.x = 0;
                }
            } else if (this.velocity.x < 0) {
                // we are moving left, so we want to check for the highest intersection
                float highest = Float.MIN_VALUE;
                for (Vector2 intersection : intersections) {
                    if (intersection.x > highest) {
                        highest = intersection.x;
                    }
                }
                if (highest != Float.MIN_VALUE) {
                    this.position.x = highest;
                    this.velocity.x = 0;
                }
            }
        }
        else {
            this.position = this.position.add(this.velocity.multiply(dt));
        }
        // move the position 
        this.individualUpdate(dt);
    }

    public abstract void individualUpdate(float dt);
}
