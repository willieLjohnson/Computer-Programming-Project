package ca.johnson.game.gfx;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Time{

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			int MillSec = 0;
			int Secs = 0;
			int Mins = 0;
			int Hrs = 0;

			@Override
			public void run() {
				ActionListener actionListener = new ActionListener() {

					public void actionPerformed(ActionEvent actionEvent) {
						// Establish's Millisecond
						MillSec += 10;
						// sets Seconds
						if (MillSec == 1000) {
							Secs++;
							MillSec = 0;
						}
						// sets minutes
						if (Secs == 60) {
							Mins++;
							Secs = 0;
						}
						// sets hours
						if (Mins == 60) {
							Hrs++;
							Mins = 0;
						}
						
						System.out.println("Milliseconds :" + MillSec);
						System.out.println("Seconds :" + Secs);
						System.out.println("Minutes :" + Mins);
						System.out.println("Hours :" + Hrs);
					}
				};
				Timer timer = new Timer(1, actionListener);
				timer.start();
			}
		});
	}

	
	
	
	
}