package imoucheg.ihm;

import imoucheg.app.App;
import imoucheg.services.Services;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Main Window
 * 
 * @author ImoucheG
 *
 */
public class MainWindow {
	// Attributs
	private JFrame frmPimpmystorage;
	File fileChoose;
	File directoryDest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmPimpmystorage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			// Default
			JFileChooser.setDefaultLocale(Locale.US);
			String textLbGenerateForClickBis = "Click here for launch the generation";
			String textLbGenerateInit = "Please, choose your file and the directory path and click on";
			String textLbGenerateFinishBis = "Good job ! Your files are generated in your directory.";
			String textBtnGenerateInit = "Generate";
			ImageIcon imgIcon = new ImageIcon(
					"./src/main/resources/images/icon_pms.png");
			String patthConfig = "./src/main/resources/properties/config.properties";
			// Try to use properties
			try {
				try {
					FileReader reader = new FileReader(patthConfig);
					Properties resConfig = new Properties();
					resConfig.load(reader);

					reader = new FileReader(
							resConfig.getProperty("pathImagesProperties"));
					Properties resImages = new Properties();
					resImages.load(reader);

					reader = new FileReader(
							resConfig.getProperty("pathTextProperties"));
					Properties resText = new Properties();
					resText.load(reader);

					switch (resConfig.getProperty("lang")) {
					case "US":
						JFileChooser.setDefaultLocale(new Locale("en", "US"));
						break;
					}

					textLbGenerateForClickBis = resText
							.getProperty("lbGenerateForClick");
					textLbGenerateInit = resText.getProperty("lbGenerateInit");
					textLbGenerateFinishBis = resText
							.getProperty("lbGenerateFinish");
					textBtnGenerateInit = resText
							.getProperty("btnGenerateInit");
					imgIcon = new ImageIcon(resImages.getProperty("pathImages")
							+ resImages.getProperty("icon"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (Exception error) {
				error.printStackTrace();
			}
			final String textLbGenerateForClick = textLbGenerateForClickBis;
			final String textLbGenerateFinish = textLbGenerateFinishBis;

			frmPimpmystorage = new JFrame();
			frmPimpmystorage.setFont(new Font("Verdana", Font.PLAIN, 12));
			frmPimpmystorage.setTitle("PimpMyStorage");
			frmPimpmystorage.setBounds(100, 100, 500, 400);
			frmPimpmystorage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmPimpmystorage.setResizable(false);
			frmPimpmystorage.setIconImage(imgIcon.getImage());
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.window);
			frmPimpmystorage.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JButton btnGenerate = new JButton(textBtnGenerateInit);
			btnGenerate.setEnabled(false);

			final JLabel lblFile = new JLabel("");
			lblFile.setBounds(361, 145, 113, 14);
			panel.add(lblFile);

			final JLabel lblDirectory = new JLabel("");
			lblDirectory.setBounds(361, 235, 113, 14);
			panel.add(lblDirectory);

			JLabel lbGenerate = new JLabel(textLbGenerateInit);
			lbGenerate.setFont(new Font("Verdana", Font.PLAIN, 11));
			lbGenerate.setBounds(40, 331, 340, 14);
			lbGenerate.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lbGenerate);

			JLabel lblChooseYourImage = new JLabel("Choose your image file :");
			lblChooseYourImage.setFont(new Font("Verdana", Font.PLAIN, 11));
			lblChooseYourImage.setBounds(10, 144, 149, 14);
			panel.add(lblChooseYourImage);

			JLabel lbTopWelcome = new JLabel("Welcome to");
			lbTopWelcome.setFont(new Font("Verdana", Font.BOLD, 11));
			lbTopWelcome.setBounds(10, 11, 86, 14);
			panel.add(lbTopWelcome);

			JLabel lblPimpmystorage = new JLabel("PimpMyStorage");
			lblPimpmystorage.setForeground(SystemColor.textHighlight);
			lblPimpmystorage.setFont(new Font("Verdana", Font.BOLD, 11));
			lblPimpmystorage.setBounds(92, 11, 114, 14);
			panel.add(lblPimpmystorage);

			JButton btnBrowse = new JButton("Browse");
			btnBrowse.setBackground(SystemColor.control);
			// Listener
			btnBrowse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					ImagePreviewPanel preview = new ImagePreviewPanel();
					chooser.setAccessory(preview);
					chooser.addPropertyChangeListener(preview);
					// Listener
					chooser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							fileChoose = chooser.getSelectedFile();
						}
					});
					chooser.showOpenDialog(panel);
					if (fileChoose != null) {
						lblFile.setText(fileChoose.getName());
						if (directoryDest != null) {
							btnGenerate.setEnabled(true);
							Services.setTextLb(lbGenerate,
									textLbGenerateForClick);
						}
					}
				}
			});
			btnBrowse.setBounds(240, 141, 89, 23);
			panel.add(btnBrowse);

			JLabel lblChooseYourPath = new JLabel(
					"Choose your directory path :");
			lblChooseYourPath.setFont(new Font("Verdana", Font.PLAIN, 11));
			lblChooseYourPath.setBounds(10, 234, 196, 14);
			panel.add(lblChooseYourPath);

			JButton button = new JButton("Browse");
			button.setBackground(SystemColor.control);
			// Listener
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ChooseFile cf = new ChooseFile(true);
					directoryDest = cf.getFile();
					if (directoryDest != null) {
						lblDirectory.setText(directoryDest.getName());
						if (fileChoose != null) {
							btnGenerate.setEnabled(true);
							Services.setTextLb(lbGenerate,
									textLbGenerateForClick);
						}
					}
				}
			});
			button.setBounds(240, 231, 89, 23);
			panel.add(button);

			btnGenerate.setBackground(SystemColor.activeCaption);
			// Listener
			btnGenerate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (fileChoose != null && directoryDest != null) {
						App app = new App();
						app.createFiles(fileChoose.getPath(),
								directoryDest.getPath());
						Services.setTextLb(lbGenerate, textLbGenerateFinish);
					}
				}
			});
			btnGenerate.setBounds(385, 327, 89, 23);
			panel.add(btnGenerate);

		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
