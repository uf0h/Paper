package org.bukkit.block;

import org.bukkit.Skin;
import org.bukkit.SkullType;

import java.util.UUID;

/**
 * Represents a Skull
 */
public interface Skull extends BlockState {

    /**
     * Checks to see if the skull has an owner
     *
     * @return true if the skull has an owner
     */
    public boolean hasOwner();

    /**
     * Gets the owner of the skull, if one exists
     *
     * @return the owner of the skull or null if the skull does not have an owner
     */
    public String getOwner();

    /**
     * Sets the owner of the skull
     * <p>
     * Involves a potentially blocking web request to acquire the profile data for
     * the provided name.
     *
     * @param name the new owner of the skull
     * @return true if the owner was successfully set
     */
    public boolean setOwner(String name);

    /**
     * Set the owner and appearance of this skull. A skull with this data set
     * does not need to fetch anything remotely.
     *
     * @param name Username of the skull's owner, can be null (appears in item tooltip)
     * @param uuid UUID of the skull's owner
     * @param skin Skull owner's skin
     */
    void setOwner(String name, UUID uuid, Skin skin);

    /**
     * Clear any owner data in this skull
     */
    void clearOwner();

    /**
     * Gets the rotation of the skull in the world
     *
     * @return the rotation of the skull
     */
    public BlockFace getRotation();

    /**
     * Sets the rotation of the skull in the world
     *
     * @param rotation the rotation of the skull
     */
    public void setRotation(BlockFace rotation);

    /**
     * Gets the type of skull
     *
     * @return the type of skull
     */
    public SkullType getSkullType();

    /**
     * Sets the type of skull
     *
     * @param skullType the type of skull
     */
    public void setSkullType(SkullType skullType);
}
