package pepse;

/**
 * The Observer interface represents objects that should be notified of changes
 * in the subject they are observing.
 * Classes implementing this interface must define the behavior to be executed when an
 * update notification is received.
 * @author Noam Barzilay
 */
public interface Observer {
    /**
     * This method is called when the observed subject notifies its observers of a change.
     * Implementations of this method should define the actions to be taken in response to the update.
     */
    void update();
}
