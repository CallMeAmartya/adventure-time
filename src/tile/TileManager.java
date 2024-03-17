package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static main.GamePanel.MAX_SCREEN_COL;
import static main.GamePanel.MAX_SCREEN_ROW;
import static main.GamePanel.TILE_SIZE;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[MAX_SCREEN_ROW][MAX_SCREEN_COL];
        loadMap("/maps/map01.txt");
        getTileImage();
    }

    void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dirt.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void loadMap(String mapFilePath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            for (int row = 0; row < MAX_SCREEN_ROW; row++) {
                String[] line = br.readLine().split(" ");
                for (int col = 0; col < MAX_SCREEN_COL; col++) {
                    int n = Integer.parseInt(line[col]);
                    mapTileNum[row][col] = n;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < MAX_SCREEN_ROW; row++) {
            for (int col = 0; col < MAX_SCREEN_COL; col++) {
                g2.drawImage(tile[mapTileNum[row][col]].image, col * TILE_SIZE, row * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE, null);
            }
        }
    }
}
