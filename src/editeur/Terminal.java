package editeur;

import ihm.swing.SwingBuilder;
import model.Module;
import model.Univers;
import semantique.Erreur;
import semantique.Verificateur;
import syntaxe.Parseur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Terminal extends JFrame implements KeyListener, ActionListener, ListSelectionListener {
	JTextPane input;

	JTextPane output;
	Executeur executeur;
	// public static String chemin = "F:\\workspaces\\Lg";
	public static String chemin = "F://GitHub//LgBasic//src//test";
	Map<Color, AttributeSet> asets = new HashMap<>();
	public Map<String, String> sources;
	public Univers univers;

	/**
	 * @param args
	 */
	TextAreaOutputStream streamOutput;
	JList<String> list;
	JList<Erreur> listErreurSemantique;
	JButton nouveau;
	JButton executer;
	Verificateur verificateur;

	public Terminal(Executeur executeur) {
		this.executeur = executeur;

		SwingBuilder sb = new SwingBuilder(this);

		output = new JTextPane();
		streamOutput = new TextAreaOutputStream(output);
		System.setOut(new PrintStream(streamOutput));
	//	System.setErr(new PrintStream(streamOutput));
		JScrollPane outputScrollPane = new JScrollPane(output);
		input = new JTextPane();

		JScrollPane inputScrollPane = new JScrollPane(input);
		outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		list = new JList<String>();
		listErreurSemantique = new JList<Erreur>();
		try {
			this.chargerSources();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JScrollPane listScrollPane = new JScrollPane(list);
		nouveau = new JButton("Nouveau");
		nouveau.addActionListener(this);
		executer = new JButton("Executer");
		executer.addActionListener(this);
		if (executeur != null) {
			executer.setEnabled(executeur.test(this));
		}
		sb.beginY();
		sb.beginX();
		sb.setSize(300, 30);
		sb.add(nouveau);
		sb.setSize(300, 30);
		sb.add(executer);
		sb.end();
		sb.beginX();
		sb.beginY();
		sb.setSize(1600, 700);
		sb.add(inputScrollPane);
		sb.beginX();
		sb.setSize(600, 200);
		sb.add(outputScrollPane);
		sb.space(4);
		sb.setSize(996, 200);
		sb.add(listErreurSemantique);
		sb.end();
		sb.end();
		sb.space(4);
		sb.beginY();

		sb.setSize(300, 900);
		sb.add(listScrollPane);

		sb.end();
		sb.end();
		sb.end();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		input.addKeyListener(this);
		list.addListSelectionListener(this);
		// list.addLis
		sb.open("Langage fonctionnel");

	}

	public void chargerSources() throws IOException {
		String ls[] = new File(chemin).list();
		sources = new HashMap<String, String>();
		Vector<String> vector = new Vector<String>();
		for (String s : ls) {
			if (s.endsWith(".src")) {
				int i = s.indexOf(".src");
				String nom = s.substring(0, i);
				vector.add(nom);
				sources.put(nom, new String(Files.readAllBytes(Paths.get(chemin, s))));

			}
		}

		list.setListData(vector);
	}

	AttributeSet attributeSet(Color c) {
		AttributeSet as = asets.get(c);
		if (as == null) {
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			attributes = new SimpleAttributeSet();
			attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
			attributes.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.FALSE);
			attributes.addAttribute(StyleConstants.CharacterConstants.Foreground, c);
			StyleConstants.setFontSize(attributes, 14);
			as = attributes;
			asets.put(c, as);
		}
		return as;

	}

	public static void main(String[] args)
			throws XPathExpressionException, SQLException, SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		if (args.length >= 1) {
			chemin = args[0];

		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Terminal(new SimpleExecuteur()).setVisible(true);
			}
		});

	}

	boolean colorer = false;
	boolean erreur = false;

	public void compiler() throws IOException {
		this.executer.setEnabled(false);
		if (erreur) {
			return;
		}
		Parseur parseur = new Parseur();
		String sel = list.getSelectedValue();
		if (univers == null) {

			univers = parseur.lireSourceCode(sources, null);
		} else {
			Module module = parseur.lireModule(sel, sources.get(sel));
			univers.modules.put(sel, module);
			if (parseur.error) {
				return;
			}
			module.initNomModule(sel);

		}

		if (parseur.error) {
			return;
		}

		Verificateur verif = new Verificateur(univers);

		this.verificateur = null;
		verif.executer();
		Vector<Erreur> erreurs = new Vector<Erreur>();
		erreurs.addAll(verif.erreurs);
		this.listErreurSemantique.setListData(erreurs);
		if (erreurs.isEmpty()) {

			ColorierSource colorierSource = new ColorierSource();
			colorierSource.sets.addAll(verif.validations.keySet());
			Module module = univers.modules.get(sel);
			colorierSource.tp = this.input;
			colorierSource.visiter(module);
			this.verificateur = verif;
			if (this.executeur == null) {
				this.executer.setEnabled(true);
			} else {
				this.executer.setEnabled(this.executeur.test(this));
			}

		}
	}

	public void keyPressed(KeyEvent e) {
		/*
		 * try { this.compiler(); } catch (IOException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); }
		 */
	}

	String s = "";

	boolean selection = false;

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		erreur = false;
		String sel = list.getSelectedValue();
		if (sel == null) {
			return;
		}
		if (selection) {
			return;
		}
		StyledDocument document = input.getStyledDocument();
		String src = "";
		try {
			src = document.getText(0, document.getLength());
			this.sources.put(sel, src);
			Files.write(Paths.get(chemin, sel + ".src"), src.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (BadLocationException | IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		this.output.setText("");
		try {
			compiler();
		} catch (Throwable e2) {
			erreur = true;
			e2.printStackTrace();
		}

		this.streamOutput.flush();

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == executer) {
			if (executeur != null) {
				executeur.executer(this);
			}
			return;

		}
		if (arg0.getSource() == nouveau) {
			String nom = UI.request("nom ", this);
			for (int idx = 0; idx < this.list.getModel().getSize(); idx++) {
				String tmp = this.list.getModel().getElementAt(idx);
				if (tmp.equals(nom)) {
					UI.warning("nom existant", this);
					return;
				}
			}
			try {
				Files.write(Paths.get(chemin, nom + ".src"), this.input.getText().getBytes(),
						StandardOpenOption.CREATE_NEW);
				this.chargerSources();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			this.compiler();
		} catch (Throwable e) {
			erreur = true;
			e.printStackTrace();
		}

	}

	public void clearColor(Color color) {

		this.setColor(Color.black, 0, input.getStyledDocument().getLength());

	}

	public void setColor(Color c, int idx, int l) {

		JTextPane tp = this.input;

		tp.getStyledDocument().setCharacterAttributes(idx, l, this.attributeSet(c), true);

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		String sel = list.getSelectedValue();
		if (this.executeur != null) {
			this.executer.setEnabled(this.executeur.test(this));
		}
		if (sel == null) {

			return;
		}
		try {
			selection = true;
			this.input.setText("");

			this.input.setEditable(true);

			this.input.setText(this.sources.get(sel));
			this.setColor(Color.black, 0, input.getStyledDocument().getLength());
			this.compiler();
			this.input.setCaretPosition(0);
			selection = false;
		} catch (Throwable e) {
			erreur = true;
			e.printStackTrace();
		}

	}

}
