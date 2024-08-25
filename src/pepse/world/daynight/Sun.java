
package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.Constants;

import java.awt.*;

/**
 * This class represents the sun in a day-night cycle in a game world.
 * It provides a method to create a GameObject representing the sun.
 * The sun GameObject is a yellow oval that moves in a circular path,
 * simulating the sun's movement across the sky.
 *
 * @author Noam Barzilay
 */
public class Sun {

    /**
     * Creates a GameObject representing the sun in the day-night cycle.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The duration of the day-night cycle.
     * @return A GameObject representing the sun.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        // Create a yellow oval as the sun
        GameObject sun = new GameObject(Vector2.ZERO,
                Vector2.ONES.mult(Constants.SUN_RADIUS * 2), new OvalRenderable(Color.YELLOW));

        // Set coordinate space to camera coordinates
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);

        // Set tag for identification
        sun.setTag("sun");

        // Calculate initial position of the sun
        Vector2 initialSunCenter = new Vector2(windowDimensions.x() * 0.5f,
                windowDimensions.y() * Constants.INITIAL_SUN_HEIGHT + Constants.SUN_RADIUS);

        // Calculate center of the circular path
        Vector2 cycleCenter = new Vector2(windowDimensions.x() * 0.5f,
                windowDimensions.y() * Constants.SUN_CENTER_FACTOR);

        // Create a transition effect for sun's movement across the sky
        new Transition<Float>(sun, (angle) ->
                sun.setCenter(initialSunCenter.subtract(cycleCenter).rotated(angle).add(cycleCenter)),
                Constants.INITIAL_VALUE, Constants.FINAL_VALUE, Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength, Transition.TransitionType.TRANSITION_LOOP, null);

        return sun;
    }

}
