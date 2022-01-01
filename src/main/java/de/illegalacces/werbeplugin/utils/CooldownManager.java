package de.illegalacces.werbeplugin.utils;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    private HashMap < UUID, Integer > cooldowns = new HashMap< >();

    public static final int DEFAULT_COOLDOWN = 15;

    public void setCooldown(UUID player, Integer time) {
        if (time == null)
            cooldowns.remove(player);
        else
            cooldowns.put(player, time);
    }

    public int getCooldown(UUID player) {
        return (cooldowns.get(player) == null ? 0 : cooldowns.get(player));
    }

    private CooldownManager() {}

    public static final CooldownManager INSTANCE = new CooldownManager();
}
