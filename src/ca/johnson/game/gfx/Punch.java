package ca.johnson.game.gfx;

import ca.johnson.game.Game;
import ca.johnson.game.entities.Player;

public class Punch implements Runnable {
	Screen screen;
	Player player;
	int xOffset, yOffset, tickCount, dir;

	public Punch(Screen screen, int xOffset, int yOffset, int tickCount,
			Player player, int dir) {
		this.screen = screen;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.tickCount = tickCount;
		this.player = player;
		this.dir = dir;
	}

	@Override
	public void run() {
		switch (dir) {

		case 0:
			while (Game.game.tickCount - tickCount < 9) {
				screen.render(player.x + 7, player.y - 15, 0 + 23 * 32,
						Colours.get(-1, -1, 500, -1), 4, 2);
			}
			while (Game.game.tickCount - tickCount < 18) {
				screen.render(player.x - 7, player.y - 15, 0 + 24 * 32,
						Colours.get(-1, -1, 500, -1), 4, 2);
			}
			break;
		case 3:
			while (Game.game.tickCount - tickCount < 9) {
				screen.render(player.x + 15, player.y + 5, 0 + 25 * 32,
						Colours.get(-1, -1, 500, -1), 2, 2);
			}
			while (Game.game.tickCount - tickCount < 18) {
				screen.render(player.x + 15, player.y -10, 0 + 26 * 32,
						Colours.get(-1, -1, 500, -1), 2, 2);
			}
			break;
		}
	}
}
