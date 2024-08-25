
package pepse;

import danogl.util.Vector2;

import java.awt.*;

/**
 * The Constants class provides a collection of constant values used throughout the game.
 * These constants include various parameters such as sizes, colors, probabilities, and timings,
 * which define the behavior, appearance, and dynamics of the game elements.
 * This class centralizes the definition of constants to ensure consistency and easy maintenance.
 * Constants are grouped based on their functionality for better organization and readability.
 * Modification of these constants allows for easy tuning and adjustment of game mechanics and visuals.
 *
 * @author Noam Barzilay
 */
public class Constants {
    // Night
    /** Initial opaqueness of the night. */
    public static final float INITIAL_OPAQUENESS = 0f;
    /** Opacity of the night at midnight. */
    public static final Float MIDNIGHT_OPACITY = 0.5f;

    // Sun
    /** Radius of the sun. */
    public static final float SUN_RADIUS = 50;
    /** Initial height of the sun. */
    public static final float INITIAL_SUN_HEIGHT = (float) 1 / 3;
    /** Factor determining the center of the sun's path. */
    public static final float SUN_CENTER_FACTOR = (float) 2 / 3;
    /** Initial value for sun transition. */
    public static final float INITIAL_VALUE = 0;
    /** Final value for sun transition. */
    public static final float FINAL_VALUE = 360;

    // Halo
    /** Radius of the sun's halo. */
    public static final float HALO_RADIUS = 100;

    // Avatar
    /** Size of the avatar. */
    public static final float AVATAR_SIZE = 50;
    /** Horizontal velocity of the avatar. */
    public static final float VELOCITY_X = 350;
    /** Vertical velocity of the avatar (jump velocity). */
    public static final float VELOCITY_Y = -500;
    /** Gravity affecting the avatar. */
    public static final float GRAVITY = 400;
    /** Maximum energy level of the avatar. */
    public static float MAX_ENERGY = 100;
    /** Minimum energy level of the avatar. */
    public static float MIN_ENERGY = 0;
    /** Energy gained when idle. */
    public static float ENERGY_GAIN = 1;
    /** Energy lost when running. */
    public static float ENERGY_RUN_LOSS = 0.5f;
    /** Energy lost when jumping. */
    public static float ENERGY_JUMP_LOSS = 10;
    /** Time between images for avatar animations. */
    public static final double TIME_BETWEEN_IMAGES = 0.2;
    /** File paths for idle animation images. */
    public static String[] idleImagePaths = {"assets/idle_0.png", "assets/idle_1.png",
            "assets/idle_2.png", "assets/idle_3.png"};
    /** File paths for running animation images. */
    public static String[] runImagePaths =
            {"assets/run_0.png", "assets/run_1.png", "assets/run_2.png", "assets/run_3.png",
                    "assets/run_4.png", "assets/run_5.png"};
    /** File paths for jumping animation images. */
    public static String[] jumpImagePaths = {"assets/jump_0.png", "assets/jump_1.png",
            "assets/jump_2.png", "assets/jump_3.png"};

    // Block
    /** Size of each block. */
    public static final int BLOCK_SIZE = 30;

    // Numeric Energy Display
    /** Size of the energy display. */
    public static final float DISPLAY_SIZE = 50;

    // Sky
    /** Color of the basic sky. */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");

    // Terrain
    /** Initial factor determining ground height. */
    public static final float INITIAL_GROUND_HEIGHT_FACTOR = (float) 2 / 3;
    /** Base color of the ground. */
    public static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);

    // Tree
    /** Base trunk color of trees. */
    public static final Color TREE_TRUNK_COLOR = new Color(100, 50, 20);
    /** Probability of creating a tree in a block. */
    public static final float TREE_CREATE_PROBABILITY = 0.1f;

    // Leaf
    /** Size of each leaf. */
    public static final float LEAF_SIZE = 30f;
    /** Initial angle for leaf transition. */
    public static final float INITIAL_ANGLE = -15;
    /** Final angle for leaf transition. */
    public static final float FINAL_ANGLE = 15;
    /** Time taken for leaf angle transition. */
    public static final float ANGLE_TRANSITION_TIME = 4;
    /** Time taken for leaf size transition. */
    public static final float SIZE_TRANSITION_TIME = 4;
    /** Dimensions of each leaf. */
    public static final Vector2 LEAF_DIMENSIONS = new Vector2(LEAF_SIZE, LEAF_SIZE);
    /** Change in leaf size during transition. */
    public static final Vector2 LEAF_SIZE_CHANGE = LEAF_DIMENSIONS.mult(0.925f);
    /** Color of leaves. */
    public static final Color LEAF_COLOR = new Color(50, 200, 30);
    /** Probability of creating a leaf in a block. */
    public static final float LEAF_CREATE_PROBABILITY = 0.5f;
    /** Factor determining the size of the square containing leaves. */
    public static final float LEAVES_SQUARE_FACTOR = 0.5f;
    /** Size of the square containing leaves. */
    public static final int LEAVES_SQUARE_SIZE = (int) (LEAF_SIZE * 8);
    /** Initial angle for 90-degree leaf transition. */
    public static final float INITIAL90_ANGLE = 0;
    /** Final angle for 90-degree leaf transition. */
    public static final float FINAL90_ANGLE = 90;
    /** Time taken for 90-degree leaf angle transition. */
    public static final float ANGLE90_TRANSITION_TIME = 2;
    /** Constant to help calculate the leaf's wait time until transition. */
    public static final float LEAF_WAIT_TIME_CONST = 5;

    // Fruit
    /** Energy gained when consuming a fruit. */
    public static final float FRUIT_ENERGY_GAIN = 10;
    /** Size of each fruit. */
    public static final float FRUIT_SIZE = 25f;
    /** Dimensions of each fruit. */
    public static final Vector2 FRUIT_DIMENSIONS = new Vector2(FRUIT_SIZE, FRUIT_SIZE);
    /** Starting color of each fruit. */
    public static final Color FRUIT_COLOR = Color.RED;
    /** Array of possible colors for fruits. */
    public static final Color[] FRUIT_COLORS = {Color.RED, Color.YELLOW, Color.ORANGE};
    /** Probability of creating a fruit in a block. */
    public static final float FRUIT_CREATE_PROBABILITY = 0.05f;

    // PepseGameManager
    /** Start value for the range. */
    public static final int RANGE_START = 0;
    /** Length of the cycle. */
    public static final float CYCLE_LENGTH = 30;
}
