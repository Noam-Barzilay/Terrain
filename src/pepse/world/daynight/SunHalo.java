
package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.Constants;

import java.awt.*;

/**
 * This class represents the halo around the sun in a day-night cycle in a game world.
 * It provides a method to create a GameObject representing the halo.
 * The halo GameObject is a transparent yellow oval that follows the movement of the sun,
 * simulating the glowing effect around the sun.
 *
 * @author Noam Barzilay
 */
public class SunHalo {

    /**
     * Creates a GameObject representing the halo around the sun.
     *
     * @param sun The GameObject representing the sun.
     * @return A GameObject representing the halo around the sun.
     */
    public static GameObject create(GameObject sun) {
        // Create a transparent yellow oval as the halo around the sun
        GameObject sunHalo = new GameObject(Vector2.ZERO, Vector2.ONES.mult(Constants.HALO_RADIUS * 2),
                new OvalRenderable(new Color(255, 255, 0, 20)));

        // Set coordinate space to camera coordinates
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);

        // Set tag for identification
        sunHalo.setTag("halo");

        // Make the halo follow the movement of the sun
        sunHalo.addComponent((deltaTime -> sunHalo.setCenter(sun.getCenter())));

        return sunHalo;
    }
}
