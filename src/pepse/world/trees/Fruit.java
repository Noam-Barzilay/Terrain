
package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.CollisionStrategy;

import java.util.function.Consumer;

/**
 * This class represents a fruit GameObject in the game world.
 * It extends the GameObject class and provides additional functionality for fruits.
 * Fruits can collide with the avatar and trigger energy gain upon collision.
 * They can also have a collision strategy defined for handling collisions with other GameObjects.
 * Fruits are typically placed in the game world and interact with other GameObjects.
 *
 * @author Noam Barzilay
 * @version 1.0
 */
public class Fruit extends GameObject {
    // Collision strategy for handling collisions with other GameObjects
    private CollisionStrategy collisionStrategy;
    // Callback function for handling energy gain upon collision with the avatar
    private Consumer<Float> callback;

    /**
     * Constructs a Fruit object with the specified parameters.
     *
     * @param renderable       The renderable component for the fruit.
     * @param collisionStrategy The collision strategy for the fruit.
     * @param callback         The callback function for energy gain upon collision.
     */
    public Fruit(Renderable renderable, CollisionStrategy collisionStrategy, Consumer<Float> callback) {
        super(Vector2.ZERO, Constants.FRUIT_DIMENSIONS, renderable);
        this.collisionStrategy = collisionStrategy;
        this.callback = callback;
    }

    /**
     * Determines whether the fruit should collide with the specified GameObject.
     *
     * @param other The GameObject to check for collision.
     * @return True if the fruit should collide with the specified GameObject, otherwise false.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        if (other.getTag().equals("avatar")) {
            return true;
        }
        return super.shouldCollideWith(other);
    }

    /**
     * Handles actions to be performed when a collision occurs with another GameObject.
     *
     * @param other     The GameObject with which the collision occurs.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        // Perform energy gain upon collision with the avatar
        callback.accept(Constants.FRUIT_ENERGY_GAIN);
        // Invoke collision strategy if defined
        if (collisionStrategy != null) {
            collisionStrategy.onCollision(this, other);
        }
    }
}
