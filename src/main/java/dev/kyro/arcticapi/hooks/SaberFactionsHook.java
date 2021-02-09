package dev.kyro.arcticapi.hooks;

//import com.massivecraft.factions.Board;
//import com.massivecraft.factions.FLocation;
//import com.massivecraft.factions.FPlayer;
//import com.massivecraft.factions.FPlayers;
//import dev.kyro.arcticapi.interfaces.FactionsPlugin;
//import org.bukkit.Location;
//import org.bukkit.block.Block;
//import org.bukkit.entity.Player;
//
//public class SaberFactionsHook implements FactionsPlugin {
//
//    @Override
//    public boolean isFriendly(Player player, Player otherPlayer) {
//
//        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
//        FPlayer fOtherPlayer = FPlayers.getInstance().getByPlayer(player);
//
//        if(fOtherPlayer.getFaction().isPeaceful()) return true;
//
//        return !inUnclaimed(player) && !inUnclaimed(otherPlayer) &&
//                (fPlayer.getFaction() == fOtherPlayer.getFaction()
//                        || fPlayer.getRelationTo(fOtherPlayer).isAlly()
//                        || fPlayer.getRelationTo(fOtherPlayer).isTruce());
//    }
//
//    @Override
//    public boolean inOwnTerritory(Player player) {
//
//        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
//
//        return !inUnclaimed(player) && fPlayer.isInOwnTerritory();
//    }
//
//    @Override
//    public boolean inUnclaimed(Player player) {
//
//        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
//        if(fPlayer == null) return false;
//
//        return fPlayer.getFaction().isWilderness();
//    }
//
//    @Override
//    public boolean inUnclaimed(Location location) {
//
//        FLocation fLocation = new FLocation(location);
//
//        return Board.getInstance().getFactionAt(fLocation).isWilderness();
//    }
//
//    @Override
//    public boolean canBreakBlock(Player player, Block block) {
//        return false;
//    }
//}
