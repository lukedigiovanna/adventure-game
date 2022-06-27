package game;

import java.util.*;
// import java.awt.Image;
import utils.MathUtils.Vector2;

public class Level {
    private List<Wall> walls;
    // private Image background;

    public Level() {
        this.walls = new ArrayList<Wall>();
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public void addWall(Wall.Type type, float x1, float y1, float x2, float y2) {
        this.walls.add(new Wall(type, new Vector2(x1, y1), new Vector2(x2, y2)));
    }
}
