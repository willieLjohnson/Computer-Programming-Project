package ca.johnson.game.entities;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import ca.johnson.game.Game;
import ca.johnson.game.InputHandler;
import ca.johnson.game.gfx.Colours;
import ca.johnson.game.gfx.DeathTimer;
import ca.johnson.game.gfx.Font;
import ca.johnson.game.gfx.HealthBar;
import ca.johnson.game.gfx.Screen;
import ca.johnson.game.level.Level;
import ca.johnson.game.math.Geometry;
import ca.johnson.game.net.GameClient;
import ca.johnson.game.net.packets.Packet02Move;

import java.awt.geom.Point2D;
import java.sql.Time;
import java.util.List;
import java.util.Random;

import javax.management.timer.Timer;

public class Player extends Mob {

	private InputHandler input;
	private int colour = Colours.get(-1, 111, 145, 543);
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private String username;
	int sprintSp = 1;
	private Screen specialScreen;
	private static int MAX_HEALTH = 100;
	String DeathMessage = "";

	public Player(Level level, int x, int y, InputHandler input, String username) {
		super(level, "Player", x, y, 1);
		this.health = MAX_HEALTH;
		this.input = input;
		this.username = username;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;

		if (input != null && !isDead) {
			if (input.up.isPressed()) {
				ya -= 1 * sprintSp;
			}
			if (input.down.isPressed()) {
				ya += 1 * sprintSp;
			}
			if (input.left.isPressed()) {
				xa -= 1 * sprintSp;
			}
			if (input.right.isPressed()) {
				xa += 1 * sprintSp;
			}

			if (input.sprint.isPressed()) {
				sprintSp = 2;
				System.out.println(input.sprint.getNumTimesPressed());
			} else {
				sprintSp = 1;
			}

			if (input.swing.isPressed()) {

				switch (this.movingDir) {
				case 1: // down
					Ellipse2D attackRngDown = new Ellipse2D.Double(x - 10, y,
							20, 20);
					checkCollision(attackRngDown);
					break;
				case 2: // left
					Ellipse2D attackRngLeft = new Ellipse2D.Double(x - 20,
							y - 20, 20, 20);
					checkCollision(attackRngLeft);
					break;
				case 3: // right
					Ellipse2D attackRngRight = new Ellipse2D.Double(x, y - 20,
							20, 20);
		
					checkCollision(attackRngRight);
					break;
				case 0: // up
					Ellipse2D attackRngUp = new Ellipse2D.Double(x - 10,
							y - 40, 20, 20);
					checkCollision(attackRngUp);
					break;
				}

			}

		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;

			Packet02Move packet = new Packet02Move(this.getUsername(), this.x,
					this.y, this.numSteps, this.isMoving, this.movingDir,
					this.isDead);
			packet.writeData(Game.game.socketClient);
		} else {
			isMoving = false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;
	}

	public void render(Screen screen) {
		if (!isDead) {
			int xTile = 0;
			int yTile = 28;
			int walkingSpeed = 4 / sprintSp;
			int flipTop = (numSteps >> walkingSpeed) & 1;
			int flipBottom = (numSteps >> walkingSpeed) & 1;

			if (movingDir == 1) {
				xTile += 2;
			} else if (movingDir > 1) {
				xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
				flipTop = (movingDir - 1) % 2;
			}

			int modifier = 8 * scale;
			int xOffset = x - modifier / 2;
			int yOffset = y - modifier / 2 - 4;
			if (isSwimming) {
				int waterColour = 0;
				yOffset += 4;
				if (tickCount % 60 < 15) {
					waterColour = Colours.get(-1, -1, 225, -1);
				} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
					yOffset -= 1;
					waterColour = Colours.get(-1, 225, 115, -1);
				} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
					waterColour = Colours.get(-1, 115, -1, 225);
				} else {
					yOffset -= 1;
					waterColour = Colours.get(-1, 225, 115, -1);
				}
				screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour,
						0x00, 1);
				screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32,
						waterColour, 0x01, 1);
			}
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, colour, flipTop, scale);
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, colour, flipTop, scale);

			if (!isSwimming) {
				screen.render(xOffset + (modifier * flipBottom), yOffset
						+ modifier, xTile + (yTile + 1) * 32, colour,
						flipBottom, scale);
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + modifier, (xTile + 1) + (yTile + 1) * 32,
						colour, flipBottom, scale);
			}
			if (username != null) {
				Font.render(username, screen, xOffset
						- ((username.length() - 1) / 2 * 8), yOffset - 10,
						Colours.get(-1, -1, -1, 555), 1);
				
				
			} 
			if (health < 100) 
				HealthBar.render(health, MAX_HEALTH, screen, xOffset-5, yOffset + 13,
						Colours.get(-1, -1, -1, 500), 1);
		}
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername() {
		return this.username;
	}

	private void checkCollision(Ellipse2D blade) {
		for (Player p : Game.players) {
			Point2D player = new Point2D.Double(p.x, p.y - 10);
			System.out.println(player + " " + p.username);
			if (blade.contains(player)) {
				p.health--;
				System.out.println(health);
				System.out.println("A player " + " was hit" + " and now has "
						+ p.health + " health");
				if (p.health <= 0) {
					Thread respawning = new Thread(new DeathTimer(p.username,
							5000, p));
					respawning.start();
					p.health = MAX_HEALTH;
				}
			}

		}
	}

}
