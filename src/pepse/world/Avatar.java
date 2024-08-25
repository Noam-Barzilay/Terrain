/**
 * This class represents an avatar GameObject in the game world.
 * It extends the GameObject class.
 * The avatar can move right left up and down.
 * Each movement has its own transition.
 * The avatar also has energy which can be gained and lost.
 *
 * @author Noam Barzilay
 */


package pepse.world;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.AnimationRenderable;
import danogl.util.Vector2;
import pepse.Constants;
import pepse.Observer;

import java.awt.event.KeyEvent;

/**
 * Represents the avatar controlled by the player.
 */
public class Avatar extends GameObject {
    /** Current energy level of the avatar. */
    private static float energy;
    /** User input listener for controlling the avatar. */
    private UserInputListener inputListener;
    /** Animation renderable for idle state. */
    private static AnimationRenderable idleAnimationRenderable;
    /** Animation renderable for running state. */
    private static AnimationRenderable runAnimationRenderable;
    /** Animation renderable for jumping state. */
    private static AnimationRenderable jumpAnimationRenderable;
    /** Observer for tracking avatar's interactions with flora. */
    private Observer floraObserver;

    /**
     * Constructs an Avatar object.
     * @param pos Initial position of the avatar.
     * @param inputListener User input listener.
     * @param imageReader Image reader for avatar's animations.
     * @param gameObjectCollection Collection of game objects.
     */
    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader,
                  GameObjectCollection gameObjectCollection){
        super(pos, Vector2.ONES.mult(Constants.AVATAR_SIZE),
                imageReader.readImage("assets/idle_0.png", true));

        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(Constants.GRAVITY);
        this.inputListener = inputListener;
        this.setTag("avatar");
        energy = Constants.MAX_ENERGY;
        // initialize animation renderables
        idleAnimationRenderable = new AnimationRenderable(Constants.idleImagePaths, imageReader,
                true, Constants.TIME_BETWEEN_IMAGES);
        runAnimationRenderable = new AnimationRenderable(Constants.runImagePaths, imageReader,
                true, Constants.TIME_BETWEEN_IMAGES);
        jumpAnimationRenderable = new AnimationRenderable(Constants.jumpImagePaths, imageReader,
                true, Constants.TIME_BETWEEN_IMAGES);
    }

    /**
     * Updates the state of the avatar.
     * @param deltaTime Time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            xVel -= Constants.VELOCITY_X;
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            xVel += Constants.VELOCITY_X;
        }
        if (xVel != 0 && energy >= Constants.ENERGY_RUN_LOSS){
            transform().setVelocityX(xVel);
            // if avatar is on the run, energy is loss
            energy = Math.max(Constants.MIN_ENERGY, energy-Constants.ENERGY_RUN_LOSS);
            AvatarEnergy.setEnergy(energy);
            // set changing animation
            renderer().setRenderable(runAnimationRenderable);
            renderer().setIsFlippedHorizontally(getVelocity().x() < 0);
        }
        else {
            transform().setVelocityX(0);
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0) {
            if (energy >= Constants.ENERGY_JUMP_LOSS) {
                transform().setVelocityY(Constants.VELOCITY_Y);
                // if avatar is on the jump, energy is loss
                energy = Math.max(Constants.MIN_ENERGY, energy - Constants.ENERGY_JUMP_LOSS);
                AvatarEnergy.setEnergy(energy);
                // set changing animation
                renderer().setRenderable(jumpAnimationRenderable);
                // notify the observer that the avatar has jumped
                floraObserver.update();
            }
        }
        // if avatar is idle, energy is regained
        if (xVel == 0 && getVelocity().y() == 0){
            energy = Math.min(Constants.MAX_ENERGY, energy+Constants.ENERGY_GAIN);
            AvatarEnergy.setEnergy(energy);
            // set changing animation
            renderer().setRenderable(idleAnimationRenderable);
        }
    }

    /**
     * Increases the energy of the avatar.
     * @param valToAdd Value to add to the energy.
     */
    public void gainEnergy(float valToAdd){
        energy = Math.min(Constants.MAX_ENERGY, energy + valToAdd);
        AvatarEnergy.setEnergy(energy);
    }

    /**
     * Determines whether the avatar should collide with the specified game object.
     * @param other The game object to check collision with.
     * @return True if the avatar should collide with the other game object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        if (other.getTag().equals("fruit")){
            return true;
        }
        return super.shouldCollideWith(other);
    }

    /**
     * Sets the observer for the avatar.
     * @param flora The observer to set.
     */
    public void setObserver(Observer flora){
        this.floraObserver = flora;
    }
}
