package dev.kyro.arcticapi.interfaces;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface ClaimPlugin {

    boolean isFriendly(Player player, Player otherPlayer);

    boolean inOwnTerritory(Player player);

    boolean inUnclaimed(Player player);

    boolean canBreakBlock(Player player, Block block);
}
