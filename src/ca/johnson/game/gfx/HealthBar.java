package ca.johnson.game.gfx;

public class HealthBar extends Font{
	
	public static void render(int currentHealth, int maxHealth, Screen screen, int x, int y, int colour, int scale) {
		int spacer = 0;
        for (int i = 0; i < currentHealth; i++) {
        	int shrinker = maxHealth%(i+1);
        	System.out.println(shrinker);
        	if(shrinker>30) 
        		spacer++;
                screen.render(x + spacer, y, 1002, colour, 0x00, scale);
        }
    }
}
