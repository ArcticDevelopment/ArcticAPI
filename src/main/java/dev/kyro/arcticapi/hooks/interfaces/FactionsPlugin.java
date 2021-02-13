package dev.kyro.arcticapi.hooks.interfaces;

import dev.kyro.arcticapi.hooks.enums.FactionRank;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface FactionsPlugin extends ClaimPlugin {

    FactionRank getFactionRank(Player player);
}