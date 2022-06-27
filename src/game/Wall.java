package game;

import utils.MathUtils;
import utils.MathUtils.Vector2;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a wall in the game. This may be a floor, a wall, or a ceiling.
 *
 * @author Luke DiGiovanna (lukedigiovanna@gmail.com)
 */
public class Wall {
    public static enum Type {
        SOLID(Color.YELLOW), 
        PLATFORM(Color.GREEN);
        
        public Color color;
        Type(Color c) {
            this.color = c;
        }
    }

    public Vector2 ep1, ep2;
    private Type type;


    public Wall(Type type, Vector2 ep1, Vector2 ep2) {
        this.ep1 = ep1;
        this.ep2 = ep2;
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public List<Vector2> getIntersections(Entity entity) {
        List<Vector2> intersections = new ArrayList<Vector2>();
        Vector2 i1 = MathUtils.lineIntersection(entity.position.x, entity.position.y, entity.position.x + entity.dimension.x, entity.position.y, ep1.x, ep1.y, ep2.x, ep2.y);
        Vector2 i2 = MathUtils.lineIntersection(entity.position.x + entity.dimension.x, entity.position.y, entity.position.x + entity.dimension.x, entity.position.y + entity.dimension.y, ep1.x, ep1.y, ep2.x, ep2.y);
        Vector2 i3 = MathUtils.lineIntersection(entity.position.x + entity.dimension.x, entity.position.y + entity.dimension.y, entity.position.x, entity.position.y + entity.dimension.y, ep1.x, ep1.y, ep2.x, ep2.y);
        Vector2 i4 = MathUtils.lineIntersection(entity.position.x, entity.position.y + entity.dimension.y, entity.position.x, entity.position.y, ep1.x, ep1.y, ep2.x, ep2.y);
        if (i1 != null) {
            intersections.add(i1);
        }
        if (i2 != null) {
            intersections.add(i2);
        }
        if (i3 != null) {
            intersections.add(i3);
        }
        if (i4 != null) {
            intersections.add(i4);
        }
        return intersections;
    }
}
