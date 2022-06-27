package game;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private Level currentLevel;

    private boolean drawWalls = true;

    private Player player;
    private List<Entity> entities;

    public Game() {
        this.currentLevel = new Level();
        this.currentLevel.addWall(Wall.Type.PLATFORM, 200, 400, 500, 400);
        this.currentLevel.addWall(Wall.Type.SOLID, 500, 400, 600, 450);
        this.currentLevel.addWall(Wall.Type.SOLID, 200, 400, 200, 550);
        this.currentLevel.addWall(Wall.Type.SOLID, 200, 550, 700, 550);

        this.entities = new ArrayList<Entity>();

        this.player = new Player(350, 350);
        this.player.game = this;
    }

    public List<Wall> getWalls() {
        return this.currentLevel.getWalls();
    }

    public void update() {
        this.player.update(0.05f);
        for (Entity e : this.entities) {
            e.update(0.05f);
        }
    }

    public void render(Graphics2D g, int width, int height) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        if (drawWalls) {
            for (Wall wall : this.currentLevel.getWalls()) {
                g.setColor(wall.getType().color);
                g.drawLine((int) wall.ep1.x, (int) wall.ep1.y, (int) wall.ep2.x, (int) wall.ep2.y);
            }
        }

        g.setColor(Color.WHITE);
        g.fillRect((int)player.position.x, (int)player.position.y, (int)player.dimension.x, (int)player.dimension.y);
    }

    public float getGravity() {
        return 300.0f;
    }
}
