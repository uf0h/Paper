package org.bukkit.permissions;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.bukkit.plugin.Plugin;

/**
 * Represents an object that may be assigned permissions
 */
public interface Permissible extends ServerOperator {

    /**
     * Checks if this object contains an override for the specified
     * permission, by fully qualified name
     *
     * @param name Name of the permission
     * @return true if the permission is set, otherwise false
     */
    public boolean isPermissionSet(String name);

    /**
     * Checks if this object contains an override for the specified {@link
     * Permission}
     *
     * @param perm Permission to check
     * @return true if the permission is set, otherwise false
     */
    public boolean isPermissionSet(Permission perm);

    /**
     * Gets the value of the specified permission, if set.
     * <p>
     * If a permission override is not set on this object, the default value
     * of the permission will be returned.
     *
     * @param name Name of the permission
     * @return Value of the permission
     */
    public boolean hasPermission(String name);

    /**
     * Gets the value of the specified permission, if set.
     * <p>
     * If a permission override is not set on this object, the default value
     * of the permission will be returned
     *
     * @param perm Permission to get
     * @return Value of the permission
     */
    public boolean hasPermission(Permission perm);

    /**
     * Adds a new {@link PermissionAttachment} with a single permission by
     * name and value
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *     or disabled
     * @param name Name of the permission to attach
     * @param value Value of the permission
     * @return The PermissionAttachment that was just created
     */
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value);

    /**
     * Adds a new empty {@link PermissionAttachment} to this object
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *     or disabled
     * @return The PermissionAttachment that was just created
     */
    public PermissionAttachment addAttachment(Plugin plugin);

    /**
     * Temporarily adds a new {@link PermissionAttachment} with a single
     * permission by name and value
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *     or disabled
     * @param name Name of the permission to attach
     * @param value Value of the permission
     * @param ticks Amount of ticks to automatically remove this attachment
     *     after
     * @return The PermissionAttachment that was just created
     */
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks);

    /**
     * Temporarily adds a new empty {@link PermissionAttachment} to this
     * object
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *     or disabled
     * @param ticks Amount of ticks to automatically remove this attachment
     *     after
     * @return The PermissionAttachment that was just created
     */
    public PermissionAttachment addAttachment(Plugin plugin, int ticks);

    /**
     * Removes the given {@link PermissionAttachment} from this object
     *
     * @param attachment Attachment to remove
     * @throws IllegalArgumentException Thrown when the specified attachment
     *     isn't part of this object
     */
    public void removeAttachment(PermissionAttachment attachment);

    // SportPaper - start
    /**
     * Remove from this object all of the {@link PermissionAttachment}s belonging to the given {@link Plugin}.
     * @return true if anything was removed, false if nothing changed
     */
    @Deprecated
    default boolean removeAttachments(Plugin plugin) {
        return false;
    }

    /**
     * Remove from this object all of the {@link PermissionAttachment}s that apply to the given permission.
     * @return true if anything was removed, false if nothing changed
     */
    @Deprecated
    default boolean removeAttachments(String name) {
        return false;
    }

    /**
     * Remove from this object all of the {@link PermissionAttachment}s that apply to the given {@link Permission}.
     * @return true if anything was removed, false if nothing changed
     */
    @Deprecated
    default boolean removeAttachments(Permission permission) {
        return false;
    }

    /**
     * Remove from this object all of the {@link PermissionAttachment}s belonging to the given {@link Plugin}
     * and applying to the given permission.
     * @return true if anything was removed, false if nothing changed
     */
    @Deprecated
    default boolean removeAttachments(Plugin plugin, String name) {
        return false;
    }

    /**
     * Remove from this object all of the {@link PermissionAttachment}s belonging to the given {@link Plugin}
     * and applying to the given {@link Permission}.
     * @return true if anything was removed, false if nothing changed
     */
    @Deprecated
    default boolean removeAttachments(Plugin plugin, Permission permission) {
        return false;
    }
    // SportPaper - end

    /**
     * Recalculates the permissions for this object, if the attachments have
     * changed values.
     * <p>
     * This should very rarely need to be called from a plugin.
     */
    public void recalculatePermissions();

    /**
     * Gets a set containing all of the permissions currently in effect by
     * this object
     *
     * @return Set of currently effective permissions
     */
    public Set<PermissionAttachmentInfo> getEffectivePermissions();

    // SportPaper - start
    @Deprecated
    default PermissionAttachmentInfo getEffectivePermission(String name) {
        return null;
    }

    /**
     * @return Info about all {@link PermissionAttachment}s attached to this object, effective or not.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments() {
        return Collections.emptyList();
    }

    /**
     * @return Info about all attached {@link PermissionAttachment}s belonging to the given {@link Plugin}.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin) {
        return Collections.emptyList();
    }

    /**
     * @return Info about all attached {@link PermissionAttachment}s applying to the given permission.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments(String name) {
        return Collections.emptyList();
    }

    /**
     * @return Info about all attached {@link PermissionAttachment}s applying to the given {@link Permission}.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments(Permission permission) {
        return Collections.emptyList();
    }

    /**
     * @return Info about all attached {@link PermissionAttachment}s belonging to the given {@link Plugin}
     * and applying to the given permission.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin, String name) {
        return Collections.emptyList();
    }

    /**
     * @return Info about all attached {@link PermissionAttachment}s belonging to the given {@link Plugin}
     * and applying to the given {@link Permission}.
     */
    @Deprecated
    default Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin, Permission permission) {
        return Collections.emptyList();
    }
    // SportPaper - end
}
