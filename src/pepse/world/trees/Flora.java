
package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.Observer;
import pepse.util.ColorSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class represents the flora (trees, leaves, and fruits) in the game world.
 * It provides methods to generate and update flora GameObjects.
 * Flora objects include trees, leaves, and fruits, which are created within a specified range.
 * Trees are generated based on a given probability within the range.
 * Leaves and fruits are created around each tree based on custom probabilities.
 * Flora objects can be updated to change their appearance over time.
 *
 * @author Noam Barzilay
 * @version 1.0
 */
public class Flora implements Observer {
    // Random object for generating random values
    private static Random random = new Random();
    private GameObjectCollection gameObjectCollection;
    private Function<Float, Float> GroundHeightCallback;
    private Consumer<Float> avatarGainCallback;

    /**
     * Constructs a Flora object with the specified parameters.
     *
     * @param gameObjectCollection The collection of GameObjects in the game world.
     * @param getGroundHeight      A callback function to retrieve the ground height at a given position.
     * @param avatarGainCallback   A callback function to handle avatar gain.
     */
    public Flora(GameObjectCollection gameObjectCollection,
                 Function<Float, Float> getGroundHeight, Consumer<Float> avatarGainCallback) {
        this.gameObjectCollection = gameObjectCollection;
        this.GroundHeightCallback = getGroundHeight;
        this.avatarGainCallback = avatarGainCallback;
    }

    /**
     * Creates a list of tree GameObjects within the specified range.
     *
     * @param minX The minimum x-coordinate of the range.
     * @param maxX The maximum x-coordinate of the range.
     * @return A list of tree GameObjects created within the range.
     */
    public List<GameObject> createInRange(int minX, int maxX) {
        List<GameObject> treesList = new ArrayList<>();
        // Adjust minX and maxX to be multiples of Block size
        while (!(minX % Constants.BLOCK_SIZE == 0)) {
            minX--;
        }
        while (!(maxX % Constants.BLOCK_SIZE == 0)) {
            maxX--;
        }
        // Generate trees within the specified range
        for (int x = minX; x < maxX; x += Constants.BLOCK_SIZE) {
            if (random.nextDouble() < Constants.TREE_CREATE_PROBABILITY) {
                // Create tree
                Tree tree = createTree(x);
                // Create leaves and fruits around the tree
                Vector2 squareCenter = tree.getCenter().subtract(
                        new Vector2(0, Constants.LEAVES_SQUARE_FACTOR * tree.getDimensions().y()));
                createLeavesAndFruits(squareCenter);
                // Add the tree to the list
                treesList.add(tree);
            }
        }
        return treesList;
    }

    @Override
    public void update() {
        // Update flora objects
        for (GameObject gameObject : gameObjectCollection) {
            if (gameObject.getTag().equals("tree")) {
                // Change tree's trunk color
                gameObject.renderer().setRenderable(new RectangleRenderable
                        (ColorSupplier.approximateColor(Constants.TREE_TRUNK_COLOR)));
            }
            if (gameObject.getTag().equals("fruit")) {
                // Change fruit's color
                int randIndex = random.nextInt(Constants.FRUIT_COLORS.length);
                gameObject.renderer().setRenderable(new OvalRenderable(Constants.FRUIT_COLORS[randIndex]));
            }
            if (gameObject.getTag().equals("leaf")) {
                // Rotate leaf
                new Transition<Float>(gameObject, gameObject.renderer()::setRenderableAngle,
                        Constants.INITIAL90_ANGLE, Constants.FINAL90_ANGLE,
                        Transition.LINEAR_INTERPOLATOR_FLOAT, Constants.ANGLE90_TRANSITION_TIME,
                        Transition.TransitionType.TRANSITION_ONCE, null);
            }
        }
    }

    /**
     * Generates the height of a tree within a given block height.
     *
     * @param blockHeight The height of the block where the tree will be placed.
     * @return The height of the generated tree.
     */
    private int generateTreeHeight(float blockHeight) {
        int treeHeight = random.nextInt((int) blockHeight - (Constants.LEAVES_SQUARE_SIZE / 2));
        while (!(treeHeight % 2 == 0 && treeHeight >= (Constants.LEAVES_SQUARE_SIZE / 2))) {
            treeHeight = random.nextInt((int) blockHeight - (Constants.LEAVES_SQUARE_SIZE / 2));
        }
        return treeHeight;
    }

    /**
     * Creates a tree GameObject at the specified x-coordinate.
     *
     * @param x The x-coordinate of the tree.
     * @return The created tree GameObject.
     */
    private Tree createTree(int x) {
        Tree tree = new Tree(new RectangleRenderable
                (ColorSupplier.approximateColor(Constants.TREE_TRUNK_COLOR)), GroundHeightCallback);
        float blockHeight = tree.callback.apply((float) x);
        int treeHeight = generateTreeHeight(blockHeight);
        tree.setDimensions(new Vector2(Constants.BLOCK_SIZE, treeHeight));
        tree.setTopLeftCorner(new Vector2(x, blockHeight - treeHeight));
        tree.setTag("tree");
        gameObjectCollection.addGameObject(tree, Layer.STATIC_OBJECTS);
        return tree;
    }

    /**
     * Creates leaves and fruits around the specified center.
     *
     * @param center The center around which leaves and fruits will be created.
     */
    private void createLeavesAndFruits(Vector2 center) {
        for (int i = (int) center.x() - (Constants.LEAVES_SQUARE_SIZE / 2); i < (int) center.x()
                + Constants.LEAVES_SQUARE_SIZE / 2; i += (int) Constants.LEAF_SIZE) {
            for (int j = (int) center.y() - (Constants.LEAVES_SQUARE_SIZE / 2); j < (int) center.y()
                    + Constants.LEAVES_SQUARE_SIZE / 2; j += (int) Constants.LEAF_SIZE) {
                // Create leaf based on custom probability
                if (random.nextDouble() < Constants.LEAF_CREATE_PROBABILITY) {
                    Leaf leaf = new Leaf(new RectangleRenderable(
                            (ColorSupplier.approximateColor(Constants.LEAF_COLOR))));
                    leaf.setTopLeftCorner(new Vector2(i, j));
                    leaf.setTag("leaf");
                    gameObjectCollection.addGameObject(leaf, Layer.BACKGROUND);
                }
                // Create fruit based on custom probability
                if (random.nextDouble() < Constants.FRUIT_CREATE_PROBABILITY) {
                    Fruit fruit = new Fruit(new OvalRenderable(
                            (ColorSupplier.approximateColor(Constants.FRUIT_COLOR))),
                            new FruitStrategy(), avatarGainCallback);
                    fruit.setTopLeftCorner(new Vector2(i, j));
                    fruit.setTag("fruit");
                    gameObjectCollection.addGameObject(fruit);
                }
            }
        }
    }
}
