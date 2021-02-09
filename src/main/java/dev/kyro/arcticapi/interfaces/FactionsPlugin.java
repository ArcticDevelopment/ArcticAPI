package dev.kyro.arcticapi.interfaces;

import com.massivecraft.factions.FPlayer;

public interface FactionsPlugin extends ClaimPlugin {

    boolean inWilderness(FPlayer fPlayer);
}