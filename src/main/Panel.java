package main;

import game.Game;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    private BufferedImage screen;
    private Game game;

    public Panel() {
        this.setFocusable(true);

        this.screen = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        this.game = new Game();

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    game.update();
                    render();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        mainThread.start();
    }

    public void render() {
        Graphics2D g = screen.createGraphics();
        this.game.render(g, this.screen.getWidth(), this.screen.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Adventure Game // DEVELOPMENT VERSION", 5, 25);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(screen, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
