package Vue;

import Controleur.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ButtonChargerPlanListener implements ActionListener {
	Controleur controleur;
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc=new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			controleur.chargerPlan(selectedFile.getAbsolutePath());
			System.out.println(selectedFile.getAbsolutePath());
		}


	}

	public ButtonChargerPlanListener(Controleur controleur) {
		this.controleur = controleur;
	}
}
