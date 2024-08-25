
package pepse.world;

/**
 * This class manages the energy level of the avatar in the game world.
 * It provides methods to retrieve and update the avatar's energy level.
 * The avatar's energy level is used to determine its abilities and interactions in the game.
 * Energy can be gained or lost based on the avatar's actions.
 * This class helps maintain and track the avatar's energy throughout the game.
 *
 * @author Noam Barzilay
 */
public class AvatarEnergy {
    // Static variable to store the avatar's energy level
    private static float avatarEnergy;

    /**
     * Constructs an AvatarEnergy object with the specified initial energy level.
     * Initializes the avatar's energy level.
     *
     * @param energy The initial energy level of the avatar.
     */
    public AvatarEnergy(float energy) {
        avatarEnergy = energy;
    }

    /**
     * Retrieves the current energy level of the avatar.
     *
     * @return The current energy level of the avatar.
     */
    public static float getEnergy() {
        return avatarEnergy;
    }

    /**
     * Sets the energy level of the avatar to the specified value.
     *
     * @param val The value to set as the avatar's energy level.
     */
    public static void setEnergy(float val) {
        avatarEnergy = val;
    }
}
