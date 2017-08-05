package imoucheg.ihm;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Choose a file or directory
 * 
 * @author ImoucheG
 *
 */
public class ChooseFile extends JPanel {
	private static final long serialVersionUID = -6819507031861178343L;
	/* File or directory */
	File file;

	/**
	 * Create the panel.
	 * 
	 * @param isDirectory
	 * @author ImoucheG
	 */
	public ChooseFile(final boolean isDirectory) {
		try {
			final JFileChooser fc = new JFileChooser();
			if (isDirectory)
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setSize(200, 200);
			this.add(fc);
			this.setVisible(true);
			//Listener
			fc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					file = fc.getSelectedFile();
				}
			});
			fc.showOpenDialog(this);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
