package ca.johnson.game.gfx;

public class HealthBar extends Font{
	
	public static void render(int currentHealth, int maxHealth, Screen screen, int x, int y, int colour, int scale) {
		int spacer = 0;
        for (int i = 0; i < currentHealth/4; i++) {
        		spacer+=1;
                screen.render(x + spacer, y, 1002, colour, 0x00, 1);
        }
    }
}
