package main;

import javax.swing.JFrame;

public class Application {

    public static void main(String[] args) {

        JFrame window =  new JFrame("Adventure Time");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }

}
