/**
 * Interface for shared memory.
 * <p>
 * Figure 3.15
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */
public interface Buffer<E> {
    // producers call this method
    void insert(E item);

    // consumers call this method
    E remove();
}
