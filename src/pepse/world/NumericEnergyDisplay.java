
package pepse.world;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.*;

/**
 * This class represents a numeric energy display GameObject in the game world.
 * It extends the GameObject class and provides functionality for displaying the avatar's
 * energy level numerically. The display shows the current energy level as a percentage.
 * The displayed value updates dynamically based on changes in the avatar's energy.
 * This class facilitates the creation and management of numeric energy display objects in the game UI.
 *
 * @author Noam Barzilay
 */
public class NumericEnergyDisplay extends GameObject {
    // Text renderable component for displaying the numeric energy value
    private TextRenderable textRenderable;

    /**
     * Constructs a NumericEnergyDisplay object with the specified parameters.
     * Initializes the numeric energy display and adds it to the game UI.
     *
     * @param topLeftCorner         The top-left corner position of the display.
     * @param dimensions            The dimensions of the display.
     * @param gameObjectCollection The collection of GameObjects in the game world.
     */
    public NumericEnergyDisplay(Vector2 topLeftCorner, Vector2 dimensions,
                                GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, null);
        // Initialize text renderable with current energy level as string
        textRenderable = new TextRenderable((int) AvatarEnergy.getEnergy() + "%");
        textRenderable.setColor(Color.BLACK);
        // Create a GameObject with text renderable and add it to the UI layer
        GameObject energyNum = new GameObject(topLeftCorner, dimensions, textRenderable);
        gameObjectCollection.addGameObject(energyNum, Layer.UI);
    }

    /**
     * Updates the numeric energy display with the current energy level.
     * Updates the displayed value dynamically based on changes in the avatar's energy.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Update the text renderable with the current energy level as string
        textRenderable.setString((int) AvatarEnergy.getEnergy() + "%");
    }
}
