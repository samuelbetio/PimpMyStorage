package imoucheg.app;

import imoucheg.services.Services;
/**
 * 
 * @author ImoucheG
 *
 */
public class App {
	/**
	 * Default constructor
	 * @author ImoucheG
	 */
	public App() {
		super();
	}
	/**
	 * Create they elements
	 * @param pathImg
	 * @param pathIco
	 * @author ImoucheG
	 */
	public void createFiles(String pathImg, String pathIco) {
		boolean isSuccessAutorun = false;
		boolean isSuccessIcon = false;
		try {
			isSuccessIcon = Services.convertImageToIcon(pathImg, pathIco);
			isSuccessAutorun = Services.createAutorun(pathIco);
		} catch (Exception error) {
			error.printStackTrace();
		}
		try {
			if (!isSuccessAutorun || !isSuccessIcon)
				throw new Exception(
						"Exception for the process to create elements is impossible");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
