
package pepse.world.trees;

import java.util.Random;

import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.Constants;

/**
 * This class represents a leaf GameObject in the game world.
 * It extends the GameObject class and provides additional functionality for leaves.
 * Leaves are typically placed in the game world and can undergo transitions in angle and size over time.
 * Each leaf has a random initial delay before its transitions start.
 * Leaves can change their angle and size dynamically based on predefined transition parameters.
 *
 * @author Noam Barzilay
 * @version 1.0
 */
public class Leaf extends GameObject {
    // Random object for generating random delays
    private static final Random rand = new Random();

    /**
     * Constructs a Leaf object with the specified renderable component.
     * Initializes the leaf's transitions in angle and size after a random initial delay.
     *
     * @param renderable The renderable component for the leaf.
     */
    public Leaf(Renderable renderable) {
        super(Vector2.ZERO, Constants.LEAF_DIMENSIONS, renderable);
        // Generate a random initial delay for the leaf transitions
        float waitTime = rand.nextFloat() * Constants.LEAF_WAIT_TIME_CONST;
        // Schedule a task to start leaf transitions after the initial delay
        new ScheduledTask(this, waitTime, false, this::leafTransition);
    }

    /**
     * Initializes the transitions in angle and size for the leaf.
     * The leaf's angle and size change dynamically over time based on predefined transition parameters.
     */
    private void leafTransition() {
        // Initialize the leaf's angle transition
        new Transition<Float>(this, this.renderer()::setRenderableAngle, Constants.INITIAL_ANGLE,
                Constants.FINAL_ANGLE, Transition.LINEAR_INTERPOLATOR_FLOAT, Constants.ANGLE_TRANSITION_TIME,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
        // Initialize the leaf's size transition
        new Transition<Vector2>(this, this::setDimensions, Constants.LEAF_DIMENSIONS,
                Constants.LEAF_SIZE_CHANGE, Transition.LINEAR_INTERPOLATOR_VECTOR,
                Constants.SIZE_TRANSITION_TIME,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }
}
