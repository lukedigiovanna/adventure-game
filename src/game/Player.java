package game;

import utils.Keyboard;
import utils.MathUtils.Vector2;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    public Player(float x, float y) {
        super(new Vector2(x, y));
        this.dimension = new Vector2(50, 50);
    }

    public void individualUpdate(float dt) {
        float speed = 100;
        Vector2 move = new Vector2(0, this.velocity.y);
        if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
            move.x+=speed;
        }
        if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
            move.x-=speed;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_W)) {
            move.y = -200;
        }

        this.velocity = move;
    }
}
