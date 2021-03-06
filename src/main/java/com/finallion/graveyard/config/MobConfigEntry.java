package com.finallion.graveyard.config;

import java.util.List;

public class MobConfigEntry {
    public final boolean enabled;
    public final int weight;
    public final int minGroup;
    public final int maxGroup;
    public final boolean canBurnInSunlight;
    public final boolean canBeWithered;
    public final List<String> whitelist;
    public final List<String> blacklist;
    public final List<String> modWhitelist;

    private MobConfigEntry(boolean enabled, int weight, int minGroup, int maxGroup, boolean canBurnInSunlight, boolean canBeWithered, List<String> whitelist, List<String> blacklist, List<String> modWhitelist) {
        this.enabled = enabled;
        this.weight = weight;
        this.minGroup = minGroup;
        this.maxGroup = maxGroup;
        this.canBurnInSunlight = canBurnInSunlight;
        this.canBeWithered = canBeWithered;
        this.whitelist = whitelist;
        this.blacklist = blacklist;
        this.modWhitelist = modWhitelist;
    }

    public static MobConfigEntry of(boolean enabled, int weight, int minGroup, int maxGroup, boolean canBurnInSunlight, boolean canBeWithered, List<String> whitelist, List<String> blacklist, List<String> modWhitelist) {
        return new MobConfigEntry(enabled, weight, minGroup, maxGroup, canBurnInSunlight, canBeWithered, whitelist, blacklist, modWhitelist);
    }
}
