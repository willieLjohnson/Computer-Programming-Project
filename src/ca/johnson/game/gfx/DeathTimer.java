package ca.johnson.game.gfx;

import ca.johnson.game.Game;
import ca.johnson.game.entities.Player;
import ca.johnson.game.net.packets.Packet02Move;

public class DeathTimer implements Runnable {
	private String name;
	private int time;
	private Player player;
	
	public DeathTimer(String n, int t, Player p) {
		name = n;
		time = t;
		player = p;
	}

	@Override
	public void run() {
		player.setDead(true);
		Packet02Move packet = new Packet02Move(player.getUsername(), player.x,
				player.y, player.getNumSteps(), player.isMoving(), player.getMovingDir(), player.isDead());
		packet.writeData(Game.game.socketClient);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.x = 100;
		player.y = 100;
		player.setDead(false);
		packet = new Packet02Move(player.getUsername(), player.x,
				player.y, player.getNumSteps(), player.isMoving(), player.getMovingDir(), player.isDead());
		packet.writeData(Game.game.socketClient);
	}

}