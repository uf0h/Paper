package org.bukkit.event.entity;

import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EntityAction;
import org.bukkit.event.HandlerList;
import org.github.paperspigot.event.entity.EntityZapEvent;

/**
 * Stores data for pigs being zapped
 */
public class PigZapEvent extends EntityZapEvent implements Cancellable, EntityAction {
    private boolean canceled;
    private final PigZombie pigzombie;
    private final LightningStrike bolt;

    public PigZapEvent(final Pig pig, final LightningStrike bolt, final PigZombie pigzombie) {
        super(pig, bolt, pigzombie);
        this.bolt = bolt;
        this.pigzombie = pigzombie;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    @Override
    public Pig getEntity() {
        return (Pig) entity;
    }

    /**
     * Gets the bolt which is striking the pig.
     *
     * @return lightning entity
     */
    public LightningStrike getLightning() {
        return bolt;
    }

    @Override
    public LightningStrike getActor() {
        return getLightning();
    }

    /**
     * Gets the zombie pig that will replace the pig, provided the event is
     * not cancelled first.
     *
     * @return resulting entity
     */
    public PigZombie getPigZombie() {
        return pigzombie;
    }
}
