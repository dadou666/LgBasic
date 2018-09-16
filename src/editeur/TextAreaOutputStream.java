package editeur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TextAreaOutputStream extends OutputStream {

	private final JTextPane textArea;
	private int maxSize = 50;

	private final StringBuilder sb = new StringBuilder();

	public TextAreaOutputStream(final JTextPane textArea) {
		this.textArea = textArea;
	}

	@Override
	public void flush() {
		final String text = sb.toString() ;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				textArea.setText(text);
			}

		});
		sb.setLength(0);
	}

	@Override
	public void close() {
	}

	@Override
	public void write(int b) throws IOException {
		
		sb.append((char) b);
		
	
	}

}