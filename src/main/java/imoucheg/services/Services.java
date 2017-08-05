/**
 * 
 */
package imoucheg.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import net.sf.image4j.codec.ico.ICOEncoder;

/**
 * @author ImoucheG
 *
 */
public class Services {
	
	/**
	 * Convert image to icon
	 * @author ImoucheG
	 * @param pathImg
	 * @param pathIco
	 * @return boolean
	 */
	public static boolean convertImageToIcon(String pathImg, String pathIco) {
		boolean isSuccess = false;
		try {
			Image img = ImageIO.read(new File(pathImg));
			BufferedImage bf = toBufferedImage(img);
			ICOEncoder.write(bf, new File(pathIco+ "/PMS.ico"));
			System.out.println("Icon is create at " + pathIco+ "/PMS.ico");
			isSuccess = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	/**
	 * https://code.google.com/p/game-engine-for-java/source/browse/src/com/gej/util/ImageTool.java#31
	 * @param img
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		// Return the buffered image
		return bimage;
	}
	/**
	 * Create Autorun.inf
	 * @param pathIco
	 * @author ImoucheG
	 */
	public static boolean createAutorun(String pathIco) {
		boolean isSuccess = false;
		StringBuffer strb = new StringBuffer();
		String icon = "PMS.ico";
		String path = pathIco;
		strb.append("[autorun]");
		strb.append("\nicon=");
		strb.append(icon);
		try{
		 FileWriter fstream = new FileWriter(path+"/autorun.inf");
		 BufferedWriter out = new BufferedWriter(fstream);
		 out.write(strb.toString());
		 out.close();
		 System.out.println("Autorun is creating at " + path+"/autorun.inf");
		 isSuccess = true;
		}catch(IOException error){
			error.printStackTrace();
		}
		return isSuccess;
	}
	/**
	 * Set text of label for JLabel
	 * @author ImoucheG
	 * @param lb JLabel
	 * @param text String
	 */
	public static void setTextLb(JLabel lb, String text){
		try{
			lb.setText(text);
		}catch(NullPointerException error){
			error.printStackTrace();
		}
	}
	/**
	 * Set text of label for Jbutton
	 * @author ImoucheG
	 * @param btn JButton
	 * @param text String
	 */
	public static void setTextBtn(JButton btn, String text){
		try{
			btn.setText(text);
		}catch(NullPointerException error){
			error.printStackTrace();
		}
	}

}
