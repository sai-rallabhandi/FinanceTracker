package app;

import javax.swing.*;
import view.MainFrame;
public class Main {
	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> 
		new MainFrame());
	}
}
