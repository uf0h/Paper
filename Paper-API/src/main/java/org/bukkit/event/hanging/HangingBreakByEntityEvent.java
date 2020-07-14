package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.event.EntityAction;

/**
 * Triggered when a hanging entity is removed by an entity
 */
public class HangingBreakByEntityEvent extends HangingBreakEvent implements EntityAction {
    private final Entity remover;

    public HangingBreakByEntityEvent(final Hanging hanging, final Entity remover) {
        super(hanging, RemoveCause.ENTITY);
        this.remover = remover;
    }

    /**
     * Gets the entity that removed the hanging entity
     *
     * @return the entity that removed the hanging entity
     */
    public Entity getRemover() {
        return remover;
    }

    @Override
    public Entity getActor() {
        return getRemover();
    }
}
