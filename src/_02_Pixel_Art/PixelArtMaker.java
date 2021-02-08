package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PixelArtMaker implements MouseListener, ActionListener{
	private final String SAVE_FILE = "src/_02_Pixel_Art/savedGrid.dat";
	
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	private JButton saveButton;
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = loadGrid(w, h, r, c);
		csp = new ColorSelectionPanel();
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(saveButton);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public void saveGrid() {
		try(FileOutputStream fos = new FileOutputStream(new File(SAVE_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);
			JOptionPane.showMessageDialog(null, "Session Saved Successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Session Save Failed: File Not Found Exception", "ERROR", 0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Session Save Failed: IO Exception", "ERROR", 0);
			e.printStackTrace();
		}
	}
	
	public GridPanel loadGrid(int w, int h, int r, int c) {
		try(FileInputStream fis = new FileInputStream(new File(SAVE_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			if(JOptionPane.showConfirmDialog(null, "Pixel Art Save File Found. Would you like to load it?") == JOptionPane.YES_OPTION) {
				return (GridPanel) ois.readObject();
			} else {
				JOptionPane.showMessageDialog(null, "New Session created using entered values.");
				return new GridPanel(w, h, r, c);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: File Not Found Exception | New Session created using entered values.", "ERROR", 0);
			e.printStackTrace();
			return new GridPanel(w, h, r, c);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: IO Exception | New Session created using entered values.", "ERROR", 0);
			e.printStackTrace();
			return new GridPanel(w, h, r, c);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Class Not Found Exception | New Session created using entered values.", "ERROR", 0);
			e.printStackTrace();
			return new GridPanel(w, h, r, c);
		}
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == saveButton) {
			if(JOptionPane.showConfirmDialog(null, "Warning: Saving this session will overwrite any previous save file. Continue?") == JOptionPane.YES_OPTION) {
				saveGrid();
			} else {
				JOptionPane.showMessageDialog(null, "Save Cancelled");
			}
		}
	}
}
