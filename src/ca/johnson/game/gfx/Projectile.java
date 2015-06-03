package ca.johnson.game.gfx;

import java.awt.geom.Ellipse2D;

import ca.johnson.game.Game;
import ca.johnson.game.entities.Player;
import ca.johnson.game.gfx.Colours;
import ca.johnson.game.gfx.Screen;

public class Projectile implements Runnable {
	Screen screen;
	int x, y, speed, tickCount;
	int change = 0;
	int movingDir;
	int DISTANCE = 20;
	Player player;

	public Projectile(Screen screen, Player player, int speed, int tickCount, int movingDir) {
		this.player = player;
		this.speed = speed;
		this.screen = screen;
		this.tickCount = tickCount;
		this.movingDir = movingDir;
	}

	@Override
	public void run() {
		x = player.x;
		y = player.y;
		switch(movingDir) {
		case 0:
			while (Game.game.tickCount - tickCount < DISTANCE) {
				screen.render(x, y, 0 + 22 * 32,
						Colours.get(-1, -1, 550, -1), 4, 2);
				if (Game.game.tickCount - change > 1 || change == 0) {
					y-= 1 * speed;
					change = Game.game.tickCount;
					
					player.checkCollision(new Ellipse2D.Double(x,y-10,20,20));
				}
			}
			break;
		case 1:
			while (Game.game.tickCount - tickCount < DISTANCE) {
				screen.render(x, y, 0 + 22 * 32,
						Colours.get(-1, -1, 550, -1), 4, 2);
				if (Game.game.tickCount - change > 1 || change == 0) {
					y+= 1 * speed;
					change = Game.game.tickCount;
					player.checkCollision(new Ellipse2D.Double(x,y-10,20,20));
				}
			}
			break;
		case 2:
			while (Game.game.tickCount - tickCount < DISTANCE) {
				screen.render(x, y, 0 + 22 * 32,
						Colours.get(-1, -1, 550, -1), 4, 2);
				if (Game.game.tickCount - change > 1 || change == 0) {
					x-= 1 * speed;
					change = Game.game.tickCount;
					player.checkCollision(new Ellipse2D.Double(x,y-10,20,20));
				}
			}
			break;
		case 3:
			while (Game.game.tickCount - tickCount < DISTANCE) {
				screen.render(x, y, 0 + 22 * 32,
						Colours.get(-1, -1, 550, -1), 4, 2);
				if (Game.game.tickCount - change > 1 || change == 0) {
					x+= 1 * speed;
					change = Game.game.tickCount;
					player.checkCollision(new Ellipse2D.Double(x,y -10,20,20));
				}
			}
			break;
		}
		
	
	}
}
