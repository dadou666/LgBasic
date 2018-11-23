package monde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class EcranDessin extends JComponent implements KeyListener {
	public static int width = 512;
	public static int height = 512;
	public Map<String, Balle> balles = new HashMap<>();
	PrintWriter out;

	public EcranDessin() {


	}

	



	public void paint(Graphics g) {
		for (Map.Entry<String, Balle> e : balles.entrySet()) {
			e.getValue().paint(g);

		}

	}

	public static void main(String[] a) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		EcranDessin mc = new EcranDessin();
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		out.println("KeyPressed " + arg0.getKeyChar() + "\n");
		out.flush();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
