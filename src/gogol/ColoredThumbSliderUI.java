package gogol;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * Created by khopf on 07/06/2017.
 */
class ColoredThumbSliderUI extends BasicSliderUI
{

	Color thumbColor;

	/**
	 * Custom design for the JSlider component
	 * @param s the slider for the design
	 * @param tColor custom color
	 */
	ColoredThumbSliderUI(JSlider s, Color tColor)
	{
		super(s);
		thumbColor = tColor;
	}

	/**
	 *
	 * paint-method for the component.
	 */
	public void paint(Graphics g, JComponent c)
	{
		recalculateIfInsetsChanged();
		recalculateIfOrientationChanged();
		Rectangle clip = g.getClipBounds();

		if (slider.getPaintTrack() && clip.intersects(trackRect))
		{
			paintTrack(g);
		}
		if (slider.getPaintTicks() && clip.intersects(tickRect))
		{
			paintTicks(g);
		}
		if (slider.getPaintLabels() && clip.intersects(labelRect))
		{
			paintLabels(g);
		}
		if (slider.hasFocus() && clip.intersects(focusRect))
		{
			paintFocus(g);
		}
		if (clip.intersects(thumbRect))
		{
			Color savedColor = slider.getBackground();
			slider.setBackground(thumbColor);
			paintThumb(g);
			slider.setBackground(savedColor);
		}
	}
}