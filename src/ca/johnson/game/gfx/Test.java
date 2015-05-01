package ca.johnson.game.gfx;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Test extends JComponent {
	public ArrayList<Double> vals = new ArrayList<Double>();
	private int scale;
	final static private int HEIGHT = 1024;
	final static private int WIDTH = 1280;
	int next;
	int height, top;
	Color col = new Color(0, 0, 0);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		add(10);
		add(50);
		add(-30);
		add(40);
		add(-60);
		add(10);
		add(20);
		int start = 0;
		draw((Graphics2D) g, start);
	}

	// populate value array
	public void add(double value) {

		vals.add(value * 6);
		System.out.println(value + " Added");

		System.out.println("Current List: " + vals);

		scale = WIDTH / vals.size();
		System.out.println("Current scale: " + scale);
	}

	public void draw(Graphics2D g2, int start) {
		int leftCornerRect = start;
		System.out.println("Start Draw. Vals: " + vals);

		for (double d : vals) {
			
			if (d< 0) {
				System.out.println("D " + d);
				height = (int) d*-1;
				System.out.println("heigt: " + height);
				next = leftCornerRect +  scale;
				System.out.println("Next/2: " + next / 2);

				g2.setColor(getGradient(col));
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				g2.fillRect(leftCornerRect, 512,  scale, height);
				String stringHeight = String.valueOf((height / 6)*-1);
				g2.drawString(stringHeight, next -  scale, 522+height);

				leftCornerRect = next;
				System.out.println("End Draw. Vals: " +  vals);
			} else {
				System.out.println("D " + d);
				height = (int) d;
				top = (int) (HEIGHT - d)-512;
				System.out.println("heigt: " + height);
				next = leftCornerRect +  scale;
				System.out.println("Next/2: " + next / 6);

				g2.setColor(getGradient(col));
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				g2.fillRect(leftCornerRect, top,  scale, height);
				String stringHeight = String.valueOf(height / 6);
				g2.drawString(stringHeight, next -  scale, top);

				leftCornerRect = next;
				System.out.println("End Draw. Vals: " +  vals);
			}
			
		}
	}

	public Color getGradient(Color aCol) {
		Random randCol = new Random();

		int green = randCol.nextInt(127);
		int blue = randCol.nextInt(127);
		int red = randCol.nextInt(127);
		final int GRAD_FAC = 5;

		if (aCol.getRed() > 20)
			red = aCol.getRed() - GRAD_FAC;
		if (aCol.getRed() <= 20)
			red = 255;
		return (new Color(red, green, blue));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JFrame frame = new JFrame();
				frame.setSize(WIDTH, HEIGHT);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Super Duper Deduper FULL AD Graph Build v10.0");
				frame.setResizable(false);
				frame.add(new Test());
				frame.setVisible(true);
			}
		});

	}
}
