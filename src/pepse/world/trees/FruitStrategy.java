
package pepse.world.trees;

import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.CollisionStrategy;

/**
 * This class represents a collision strategy for fruits in the game world.
 * It implements the CollisionStrategy interface and provides functionality for handling
 * collisions involving fruits. When a collision occurs between a fruit and the avatar, the fruit
 * "disappears" momentarily and then reappears.
 * This class defines the behavior of fruits upon collision with other GameObjects.
 * Fruits can trigger specific actions when colliding with certain GameObjects, such as the avatar.
 *
 * @author Noam Barzilay
 * @version 1.0
 */
public class FruitStrategy implements CollisionStrategy {

    /**
     * Handles actions to be performed when a collision occurs involving a fruit.
     * When a fruit collides with the avatar, it "disappears" momentarily and then reappears.
     *
     * @param thisObj   The fruit GameObject involved in the collision.
     * @param otherObj  The other GameObject involved in the collision (e.g., the avatar).
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        // Check if the collision involves the avatar
        if (otherObj.getTag().equals("avatar")) {
            // Make the fruit "disappear"
            thisObj.setDimensions(Vector2.ZERO);
            // Schedule a task to make the fruit reappear after a certain delay
            new ScheduledTask(thisObj, Constants.CYCLE_LENGTH, false,
                    () -> thisObj.setDimensions(Constants.FRUIT_DIMENSIONS));
        }
    }
}
