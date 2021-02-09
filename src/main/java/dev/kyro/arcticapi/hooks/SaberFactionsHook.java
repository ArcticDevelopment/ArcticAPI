package dev.kyro.arcticapi.hooks;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import dev.kyro.arcticapi.interfaces.FactionsPlugin;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SaberFactionsHook implements FactionsPlugin {

    @Override
    public boolean isFriendly(Player player, Player otherPlayer) {

        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
        FPlayer fOtherPlayer = FPlayers.getInstance().getByPlayer(player);

        if(fPlayer == null || fOtherPlayer == null) return false;

        if(fOtherPlayer.getFaction().isPeaceful()) return true;

        return true;
    }

    @Override
    public boolean inOwnTerritory(Player player) {
        return false;
    }

    @Override
    public boolean inUnclaimed(Player player) {
        return false;
    }

    @Override
    public boolean canBreakBlock(Player player, Block block) {
        return false;
    }

    @Override
    public boolean inWilderness(FPlayer fPlayer) {
        return false;
    }
}
