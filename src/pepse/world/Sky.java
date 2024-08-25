
package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;

/**
 * This class represents the sky GameObject in the game world.
 * It provides a static method to create a sky GameObject with specified dimensions and color.
 * The sky serves as the background of the game world.
 * The sky's color can be customized based on the provided color constant.
 * This class facilitates the creation of sky objects in the game world.
 *
 * @author Noam Barzilay
 */
public class Sky {

    /**
     * Creates a sky GameObject with the specified dimensions and color.
     * Sets the coordinate space to camera coordinates and assigns a tag to the sky object.
     *
     * @param windowDimensions The dimensions of the window or viewport.
     * @return The created sky GameObject.
     */
    public static GameObject create(Vector2 windowDimensions) {
        // Create a sky GameObject with rectangle renderable and specified color
        GameObject sky = new GameObject(
                Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Constants.BASIC_SKY_COLOR));
        // Set coordinate space to camera coordinates
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        // Assign a tag to the sky GameObject
        sky.setTag("sky");
        return sky;
    }
}
