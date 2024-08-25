
package pepse;

import danogl.GameObject;

/**
 * The CollisionStrategy interface defines a strategy for handling collisions between game objects.
 * Classes that implement this interface provide specific collision handling logic.
 * When a collision occurs between two game objects, the onCollision method is invoked
 * to perform the necessary actions or behaviors associated with the collision.
 * Implementations of this interface can define custom collision behaviors based on the types
 * of objects involved and the requirements of the game.
 *
 * @author Noam Barzilay
 */
public interface CollisionStrategy {
    /**
     * Defines the behavior to be executed when a collision occurs between two game objects.
     *
     * @param thisObj  The game object on which the collision occurred.
     * @param otherObj The game object with which the collision occurred.
     */
    void onCollision(GameObject thisObj, GameObject otherObj);
}
