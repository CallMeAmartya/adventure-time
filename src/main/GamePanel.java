package main;

import main.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    static final int ORIGINAL_TILE_SIZE = 16;                         // 16x16px tile
    static final int SCALE = 3;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;          // 48x48px tile
    static final int MAX_SCREEN_COL = 16;
    static final int MAX_SCREEN_ROW = 12;
    static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;        // 768px
    static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;       // 576px

    // FPS
    static final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

    // Set player's properties
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }

    void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval  = (double) 1_000_000_000 / FPS;     // 0.01666sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = System.nanoTime();

            if (delta >= 1) {
                // update information such as player position
                update();

                // draw screen with updated information
                super.repaint();

                delta--;
                drawCount++;
            }

            // Show FPS
            if (timer >= 1_000_000_000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
