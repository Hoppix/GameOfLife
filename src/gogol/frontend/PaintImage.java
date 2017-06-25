package gogol.frontend;

import java.awt.*;

/**
 * Created by khopf on 01/06/2017.
 */
public class PaintImage
{

	protected static Image drawnImage;

	/**
	 * This class holds the image where all painting actions are done
	 * @param img Image for drawing
	 */
	public PaintImage(Image img)
	{
		drawnImage = img;
	}

}
