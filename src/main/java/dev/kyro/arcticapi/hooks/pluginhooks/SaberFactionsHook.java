package dev.kyro.arcticapi.hooks.pluginhooks;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import dev.kyro.arcticapi.hooks.enums.FactionRank;
import dev.kyro.arcticapi.hooks.enums.FactionRelation;
import dev.kyro.arcticapi.hooks.interfaces.FactionsPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class SaberFactionsHook implements FactionsPlugin {

	@Override
	public boolean isFriendly(Player player, Player otherPlayer) {

		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
		FPlayer fOtherPlayer = FPlayers.getInstance().getByPlayer(player);

		if(fOtherPlayer.getFaction().isPeaceful()) return true;

		return !inUnclaimed(player) && !inUnclaimed(otherPlayer) &&
				(fPlayer.getFaction() == fOtherPlayer.getFaction()
						|| fPlayer.getRelationTo(fOtherPlayer).isAlly()
						|| fPlayer.getRelationTo(fOtherPlayer).isTruce());
	}

	@Override
	public boolean inOwnTerritory(Player player) {

		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);

		return !inUnclaimed(player) && fPlayer.isInOwnTerritory();
	}

	@Override
	public boolean inUnclaimed(Player player) {

		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);

		return fPlayer.getFaction().isWilderness();
	}

	@Override
	public boolean inUnclaimed(Location location) {

		FLocation fLocation = new FLocation(location);

		return Board.getInstance().getFactionAt(fLocation).isWilderness();
	}

	@Override
	public boolean canBreakBlock(Player player, Block block) {
		return false;
	}

	@Override
	public FactionRank getFactionRank(Player player) {

		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);

		switch(fPlayer.getRole()) {
			case LEADER:
				return FactionRank.LEADER;
			case COLEADER:
				return FactionRank.COLEADER;
			case MODERATOR:
				return FactionRank.MODERATOR;
			case NORMAL:
	            return FactionRank.MEMBER;
            case RECRUIT:
                return FactionRank.RECRUIT;
        }
        return null;
    }

    @Override
    public FactionRelation getRelation(Player player, Player otherPlayer) {

        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
        FPlayer fOtherPlayer = FPlayers.getInstance().getByPlayer(player);

        Relation relation = fPlayer.getRelationTo(fOtherPlayer);
        switch(relation) {
            case MEMBER:
                return FactionRelation.MEMBER;
            case ALLY:
                return FactionRelation.ALLY;
            case TRUCE:
                return FactionRelation.TRUCE;
            case NEUTRAL:
                return FactionRelation.NEUTRAL;
            case ENEMY:
                return FactionRelation.ENEMY;
        }
        return null;
    }
}
