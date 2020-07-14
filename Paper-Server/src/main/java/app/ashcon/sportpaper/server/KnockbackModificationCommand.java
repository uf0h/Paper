package app.ashcon.sportpaper.server;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.github.paperspigot.PaperSpigotConfig;

public class KnockbackModificationCommand extends Command {

    // Default values
    private final double knockbackFriction, knockbackHorizontal, knockbackVertical, knockbackVerticalLimit,
            knockbackExtraHorizontal, knockbackExtraVertical;

    public KnockbackModificationCommand(String name, double knockbackFriction, double knockbackHorizontal,
                                        double knockbackVertical, double knockbackVerticalLimit,
                                        double knockbackExtraHorizontal, double knockbackExtraVertical) {
        super(name);
        this.description = "Modify the knockback configuration";
        this.usageMessage = "/knockback " +
                "<friction|horizontal|vertical|vertical-limit|extra-horizontal|extra-vertical|reset|show> <value>";
        this.setPermission("bukkit.command.knockback");
        this.knockbackFriction = knockbackFriction;
        this.knockbackHorizontal = knockbackHorizontal;
        this.knockbackVertical = knockbackVertical;
        this.knockbackVerticalLimit = knockbackVerticalLimit;
        this.knockbackExtraHorizontal = knockbackExtraHorizontal;
        this.knockbackExtraVertical = knockbackExtraVertical;
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) {
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return true;
        }


        try {
            switch (args[0].toLowerCase()) {
                case "friction": {
                    double oldVal = PaperSpigotConfig.knockbackFriction;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackFriction = newVal;
                    updated(sender, "friction", oldVal, newVal);
                    break;
                }
                case "horizontal": {
                    double oldVal = PaperSpigotConfig.knockbackHorizontal;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackHorizontal = newVal;
                    updated(sender, "horizontal knockback", oldVal, newVal);
                    break;
                }
                case "vertical": {
                    double oldVal = PaperSpigotConfig.knockbackVertical;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackVertical = newVal;
                    updated(sender, "vertical knockback", oldVal, newVal);
                    break;
                }
                case "vertical-limit": {
                    double oldVal = PaperSpigotConfig.knockbackVerticalLimit;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackVerticalLimit = newVal;
                    updated(sender, "vertical limit", oldVal, newVal);
                    break;
                }
                case "extra-horizontal": {
                    double oldVal = PaperSpigotConfig.knockbackExtraHorizontal;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackExtraHorizontal = newVal;
                    updated(sender, "horizontal (extra)", oldVal, newVal);
                    break;
                }
                case "extra-vertical": {
                    double oldVal = PaperSpigotConfig.knockbackExtraVertical;
                    double newVal = parseValue(args);
                    PaperSpigotConfig.knockbackExtraVertical = newVal;
                    updated(sender, "vertical (extra)", oldVal, newVal);
                    break;
                }
                case "reset":
                    PaperSpigotConfig.knockbackFriction = knockbackFriction;
                    PaperSpigotConfig.knockbackHorizontal = knockbackHorizontal;
                    PaperSpigotConfig.knockbackVertical = knockbackVertical;
                    PaperSpigotConfig.knockbackVerticalLimit = knockbackVerticalLimit;
                    PaperSpigotConfig.knockbackExtraHorizontal = knockbackExtraHorizontal;
                    PaperSpigotConfig.knockbackExtraVertical = knockbackExtraVertical;
                    sender.sendMessage(ChatColor.GREEN + "Knockback config reset to values from file.");
                    break;
                case "show":
                    sender.sendMessage(ChatColor.GOLD + "Knockback Configuration");
                    sendValue(sender, "Friction", PaperSpigotConfig.knockbackFriction);
                    sendValue(sender, "Horizontal Knockback", PaperSpigotConfig.knockbackHorizontal);
                    sendValue(sender, "Vertical Knockback", PaperSpigotConfig.knockbackVertical);
                    sendValue(sender, "Vertical Limit", PaperSpigotConfig.knockbackVerticalLimit);
                    sendValue(sender, "Horizontal (Extra)", PaperSpigotConfig.knockbackExtraHorizontal);
                    sendValue(sender, "Vertical (Extra)", PaperSpigotConfig.knockbackExtraVertical);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            }
        } catch (RuntimeException ex) {
            sender.sendMessage(ChatColor.RED + ex.getMessage());
        }

        return true;
    }

    private double parseValue(String[] args) {
        if (args.length != 2)
            throw new RuntimeException("Please specify a single value to set the option to.");

        try {
            return Double.parseDouble(args[1]);
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Invalid value specified!");
        }
    }

    private void updated(CommandSender viewer, String name, double oldVal, double newVal) {
        viewer.sendMessage(ChatColor.GREEN + "Updated " + ChatColor.GOLD + name + ChatColor.GREEN + " from " +
                ChatColor.BLUE + oldVal + ChatColor.GREEN + " to " + ChatColor.BLUE + newVal);
    }

    private void sendValue(CommandSender viewer, String name, double value) {
        viewer.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + name + ChatColor.RESET +
                ": " + ChatColor.BLUE + value);
    }
}
