
package pepse.world;

import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.util.ColorSupplier;
import pepse.util.NoiseGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the terrain in the game world.
 * It generates the ground height using noise functions and creates blocks to represent the terrain.
 * The terrain's appearance is influenced by parameters such as window dimensions and seed for
 * noise generation. This class facilitates the generation and management
 * of terrain objects in the game world.
 *
 * @author Noam Barzilay
 */
public class Terrain {
    /** Initial ground height at x=0 */
    public float groundHeightAtX0;
    // Noise generator for terrain generation
    private NoiseGenerator noiseGenerator;
    // Dimensions of the window or viewport
    private Vector2 windowDimensions;

    /**
     * Constructs a Terrain object with the specified window dimensions and seed for noise generation.
     * Initializes the ground height at x=0 and creates a noise generator for terrain generation.
     *
     * @param windowDimensions The dimensions of the window or viewport.
     * @param seed             The seed for noise generation.
     */
    public Terrain(Vector2 windowDimensions, int seed) {
        this.groundHeightAtX0 = windowDimensions.y() * Constants.INITIAL_GROUND_HEIGHT_FACTOR;
        noiseGenerator = new NoiseGenerator(seed, (int) groundHeightAtX0);
        this.windowDimensions = windowDimensions;
    }

    /**
     * Calculates the ground height at the specified x-coordinate using noise functions.
     *
     * @param x The x-coordinate at which to calculate the ground height.
     * @return The calculated ground height at the specified x-coordinate.
     */
    public float groundHeightAt(float x) {
        float noise = (float) noiseGenerator.noise(x, Constants.BLOCK_SIZE * 7);
        return groundHeightAtX0 + noise;
    }

    /**
     * Creates a list of Block objects within the specified x-range.
     * Generates blocks to represent the terrain within the given range.
     *
     * @param minX The minimum x-coordinate of the range.
     * @param maxX The maximum x-coordinate of the range.
     * @return A list of Block objects representing the terrain within the specified range.
     */
    public List<Block> createInRange(int minX, int maxX) {
        List<Block> result = new ArrayList<>();
        // Get starting X as multiple of block size
        int startX = minX;
        while (!(startX % Constants.BLOCK_SIZE == 0)) {
            startX--;
        }

        for (int i = startX; i < maxX; i += Constants.BLOCK_SIZE) {
            int startHeight = (int) Math.floor(groundHeightAt(i) /
                    Constants.BLOCK_SIZE) * Constants.BLOCK_SIZE;

            // Get height of last block as multiple of block size
            int endHeight = (int) windowDimensions.y();
            while (!(endHeight % Constants.BLOCK_SIZE == 0)) {
                endHeight--;
            }

            for (int j = startHeight; j < endHeight; j += Constants.BLOCK_SIZE) {
                Block block = new Block(new Vector2(i, j), new RectangleRenderable
                        (ColorSupplier.approximateColor(Constants.BASE_GROUND_COLOR)));
                block.setTag("ground");
                result.add(block);
            }
        }
        return result;
    }
}
