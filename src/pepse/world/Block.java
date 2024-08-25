
package pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.Constants;

/**
 * This class represents a block GameObject in the game world.
 * It extends the GameObject class and provides additional functionality for blocks.
 * Blocks are typically used to construct the game environment and serve as platforms.
 * Each block is static and immovable, preventing intersections with other objects.
 * Blocks can have various renderable representations, such as textures or colors.
 * This class facilitates the creation and management of block objects in the game world.
 *
 * @author Noam Barzilay
 */
public class Block extends GameObject {

    /**
     * Constructs a Block object with the specified position and renderable component.
     * Initializes the block's properties, such as preventing intersections and setting immovable mass.
     *
     * @param topLeftCorner The top-left corner position of the block.
     * @param renderable    The renderable component for the block.
     */
    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, Vector2.ONES.mult(Constants.BLOCK_SIZE), renderable);
        // Prevent intersections and set mass to immovable
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }
}
