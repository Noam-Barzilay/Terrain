
package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;

import java.awt.*;

/**
 * This class represents the night cycle in a game world.
 * It provides a method to create a GameObject representing the night.
 * The night GameObject is a black rectangle that covers the entire window,
 * simulating darkness.
 *
 * @author Noam Barzilay
 */
public class Night {

    /**
     * Creates a GameObject representing the night cycle.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The duration of the day-night cycle.
     * @return A GameObject representing the night.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        // Create a black rectangle as the night background
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Color.BLACK));

        // Set coordinate space to camera coordinates
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);

        // Set tag for identification
        night.setTag("night");

        // Create a transition effect for opacity to simulate fading in/out of night
        // Transition time is half of the full cycle time
        new Transition<Float>(night, night.renderer()::setOpaqueness, Constants.INITIAL_OPAQUENESS,
                Constants.MIDNIGHT_OPACITY, Transition.CUBIC_INTERPOLATOR_FLOAT, cycleLength * 0.5f,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);

        return night;
    }
}
