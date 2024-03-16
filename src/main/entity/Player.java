package main.entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

import static main.GamePanel.TILE_SIZE;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultPlayerProperties();
    }

    void setDefaultPlayerProperties() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyHandler.upPressed) {
            y -= speed;
        } else if (keyHandler.downPressed) {
            y += speed;
        } else if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }
}
