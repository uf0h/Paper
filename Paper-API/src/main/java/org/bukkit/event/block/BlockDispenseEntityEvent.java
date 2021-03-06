package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Called when an entity is dispensed from a block.
 */
public class BlockDispenseEntityEvent extends BlockDispenseEvent implements Cancellable {
    private final Entity entity;

    public BlockDispenseEntityEvent(final Block block, final ItemStack dispensed, final Vector velocity, final Entity entity) {
        super(block, dispensed, velocity);
        this.entity = entity;
    }

    /**
     * Gets the entity that is being dispensed.
     *
     * @return An Entity for the item being dispensed
     */
    public Entity getEntity() {
        return entity;
    }
}
