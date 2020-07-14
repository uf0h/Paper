package org.bukkit.craftbukkit.entity;

import java.util.Collection;
import java.util.Set;

import net.minecraft.server.EntityMinecartCommandBlock;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class CraftMinecartCommand extends CraftMinecart implements CommandMinecart {

    private final PermissibleBase perm = new PermissibleBase(this);

    public CraftMinecartCommand(CraftServer server, EntityMinecartCommandBlock entity) {
        super(server, entity);
    }

    @Override
    public String getCommand() {
        return ((EntityMinecartCommandBlock) getHandle()).getCommandBlock().getCommand();
    }

    @Override
    public void setCommand(String command) {
        ((EntityMinecartCommandBlock) getHandle()).getCommandBlock().setCommand(command != null ? command : "");
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART_COMMAND;
    }

    @Override
    public void sendMessage(String message) {
    }

    @Override
    public void sendMessage(String[] messages) {
    }

    @Override
    public String getName() {
        return ((EntityMinecartCommandBlock) getHandle()).getCommandBlock().getName();
    }

    @Override
    public void setName(String name) {
        ((EntityMinecartCommandBlock) getHandle()).getCommandBlock().setName(name != null ? name : "@");
    }

    @Override
    public String getName(org.bukkit.command.CommandSender viewer) {
        return this.getName();
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of a minecart");
    }

    @Override
    public boolean isPermissionSet(String name) {
        return perm.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return this.perm.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return perm.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return this.perm.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return perm.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return perm.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return perm.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return perm.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        perm.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        perm.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return perm.getEffectivePermissions();
    }

    @Override
    public boolean removeAttachments(Plugin plugin) {
        return perm.removeAttachments(plugin);
    }

    @Override
    public boolean removeAttachments(String name) {
        return perm.removeAttachments(name);
    }

    @Override
    public boolean removeAttachments(Permission permission) {
        return perm.removeAttachments(permission);
    }

    @Override
    public boolean removeAttachments(Plugin plugin, String name) {
        return perm.removeAttachments(plugin, name);
    }

    @Override
    public boolean removeAttachments(Plugin plugin, Permission permission) {
        return perm.removeAttachments(plugin, permission);
    }

    @Override
    public PermissionAttachmentInfo getEffectivePermission(String name) {
        return perm.getEffectivePermission(name);
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments() {
        return perm.getAttachments();
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin) {
        return perm.getAttachments(plugin);
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments(String name) {
        return perm.getAttachments(name);
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments(Permission permission) {
        return perm.getAttachments(permission);
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin, String name) {
        return perm.getAttachments(plugin, name);
    }

    @Override
    public Collection<PermissionAttachmentInfo> getAttachments(Plugin plugin, Permission permission) {
        return perm.getAttachments(plugin, permission);
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

}
