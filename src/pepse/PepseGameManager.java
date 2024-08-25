package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.world.*;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Flora;

import java.util.Random;

/**
 * The PepseGameManager class represents the main game manager for the Pepse game.
 * It extends the GameManager class and initializes various game components and entities.
 * @author Noam Barzilay
 */
public class PepseGameManager extends GameManager {
    /** Random object for generating a seed for terrain generation. */
    private static final Random random = new Random();
    /** Seed for terrain generation. */
    private final int SEED = random.nextInt();

    /**
     * Initializes the game by creating and adding game objects, setting up the environment,
     * and configuring the player's avatar.
     * @param imageReader   The image reader for loading game assets.
     * @param soundReader   The sound reader for loading game audio assets.
     * @param inputListener The user input listener for controlling the game.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        // initialize sky
        GameObject sky = Sky.create(windowController.getWindowDimensions());
        gameObjects().addGameObject(sky, Layer.BACKGROUND);

        // initialize the ground
        Terrain terrain = new Terrain(windowController.getWindowDimensions(), SEED);
        for (Block block: terrain.createInRange(Constants.RANGE_START,
                (int) windowController.getWindowDimensions().x())) {
            gameObjects().addGameObject(block, Layer.STATIC_OBJECTS);
        }

        // initialize night
        GameObject night = Night.create(windowController.getWindowDimensions(), Constants.CYCLE_LENGTH);
        gameObjects().addGameObject(night, Layer.FOREGROUND);

        // initialize sun
        GameObject sun = Sun.create(windowController.getWindowDimensions(), Constants.CYCLE_LENGTH);
        gameObjects().addGameObject(sun, Layer.BACKGROUND);

        // initialize halo
        GameObject sunHalo = SunHalo.create(sun);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);

        // initialize avatar
        Avatar avatar = new Avatar(Vector2.ZERO, inputListener, imageReader, gameObjects());
        gameObjects().addGameObject(avatar);

        // initialize energy percentage display
        NumericEnergyDisplay numericEnergyDisplay = new NumericEnergyDisplay(Vector2.ZERO,
                Vector2.ONES.mult(Constants.DISPLAY_SIZE), gameObjects());
        gameObjects().addGameObject(numericEnergyDisplay, Layer.UI);

        // initialize trees
        Flora flora = new Flora(gameObjects(), terrain::groundHeightAt, avatar::gainEnergy);
        flora.createInRange(Constants.RANGE_START, (int) windowController.getWindowDimensions().x());

        // initialize the observer mechanism
        avatar.setObserver(flora);
    }

    /**
     * The main method to start the game by creating an instance of PepseGameManager and running it.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }
}