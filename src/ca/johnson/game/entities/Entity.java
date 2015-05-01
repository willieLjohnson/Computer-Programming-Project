package ca.johnson.game.entities;

import ca.johnson.game.gfx.Screen;
import ca.johnson.game.level.Level;

public abstract class Entity {

	public int x, y;
	public int health;
	protected Level level;

	public Entity(Level level) {
		init(level);
	}

	public final void init(Level level) {
		this.level = level;
	}

	public abstract void tick();

	public abstract void render(Screen screen);
}
