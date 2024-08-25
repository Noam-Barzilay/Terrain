
package pepse.world.trees;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.function.Function;

/**
 * This class represents a tree GameObject in the game world.
 * It extends the GameObject class and provides additional functionality for trees.
 * Trees are typically placed in the game world and interact with other GameObjects.
 * Each tree has a callback function to retrieve the ground height at its position.
 * Trees are static objects that prevent intersections and have immovable mass.
 *
 * @author Noam Barzilay
 * @version 1.0
 */
public class Tree extends GameObject {
    // Callback function to retrieve the ground height at the tree's position
    final Function<Float, Float> callback;

    /**
     * Constructs a Tree object with the specified renderable component and ground height callback function.
     * Initializes the tree's properties, such as preventing intersections and setting immovable mass.
     *
     * @param renderable      The renderable component for the tree.
     * @param getGroundHeight The callback function to retrieve the ground height at the tree's position.
     */
    public Tree(Renderable renderable, Function<Float, Float> getGroundHeight) {
        super(Vector2.ZERO, Vector2.ONES, renderable);
        this.callback = getGroundHeight;
        // Prevent intersections from any direction
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        // Set mass to immovable
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }
}
