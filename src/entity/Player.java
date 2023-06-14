package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage walkR, walk2R, walk1L, walk2L, idleR;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValue();
        getPlayerImage();
    }

    
    // Spawnpoint & Speed
    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage() {
        try {
            walkR = ImageIO.read(getClass().getResourceAsStream("/player/JakeW1R.png"));
            walk2R = ImageIO.read(getClass().getResourceAsStream("/player/JakeW2R.png"));
            walk1L = ImageIO.read(getClass().getResourceAsStream("/player/JakeW1L.png"));
            walk2L = ImageIO.read(getClass().getResourceAsStream("/player/JakeW2L.png"));
            idleR = ImageIO.read(getClass().getResourceAsStream("/player/JakeIdleR.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int dx = 0;
        int dy = 0;

        if (keyH.up) {
            dy -= speed;
        }
        if (keyH.down) {
            dy += speed;
        }
        if (keyH.left) {
            dx -= speed;
        }
        if (keyH.right) {
            dx += speed;
        }

        if (dx != 0 && dy != 0) {
            dx = (int) (dx / Math.sqrt(2));
            dy = (int) (dy / Math.sqrt(2));
        }

        x += dx;
        y += dy;

        if (dx > 0) {
            if (dy > 0) {
                direction = "down-right";
            } else if (dy < 0) {
                direction = "up-right";
            } else {
                direction = "right";
            }
        } else if (dx < 0) {
            if (dy > 0) {
                direction = "down-left";
            } else if (dy < 0) {
                direction = "up-left";
            } else {
                direction = "left";
            }
        } else {
            if (dy > 0) {
                direction = "down";
            } else if (dy < 0) {
                direction = "up";
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }





    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = walk1L;
                }
                if (spriteNum == 2) {
                    image = walk2L;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = walk2R;
                }
                if (spriteNum == 2) {
                    image = walkR;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = walk2L;
                }
                if (spriteNum == 2) {
                    image = walk1L;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = walk2R;
                }
                if (spriteNum == 2) {
                    image = walkR;
                }
                break;

            case "up-left":
                if (spriteNum == 1) {
                    image = walk2L;
                }
                if (spriteNum == 2) {
                    image = walk1L;
                }
                break;

            case "up-right":
                if (spriteNum == 1) {
                    image = walk2R;
                }
                if (spriteNum == 2) {
                    image = walkR;
                }
                break;

            case "down-left":
                if (spriteNum == 1) {
                    image = walk2L;
                }
                if (spriteNum == 2) {
                    image = walk1L;
                }
                break;

            case "down-right":
                if (spriteNum == 1) {
                    image = walk2R;
                }
                if (spriteNum == 2) {
                    image = walkR;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
