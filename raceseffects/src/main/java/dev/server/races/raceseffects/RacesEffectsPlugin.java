package dev.server.races.raceseffects;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.Listener;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.format.NamedTextColor;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class RacesEffectsPlugin extends JavaPlugin implements Listener {

    private int taskId = -1;
    private int periodTicks;
    private final Map<String, Map<String, EffectSpec>> tagEffects = new HashMap<>();
    private final Map<String, List<String>> groupToTags = new HashMap<>();
    private final Map<String, Double> scaleTags = new HashMap<>();
    private final Set<String> managedTags = new HashSet<>();
    private boolean enforceGroupTags = true;
    private boolean hardNoRaceMode = false;
    // Abilities (PHB-like): 72 pontos + 3 da raAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a, cap 20
    private static final List<String> ABILITIES = Arrays.asList("STR","DEX","CON","INT","WIS","CHA");
    private static final int ABILITY_BASE_POOL = 72;
    private static final int ABILITY_RACE_BONUS = 3; // todas as raAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§as
    private NamespacedKey nsk(String key) { return new NamespacedKey(this, key); }
    // Attribute modifiers (aggregated per-attr)
    private static final UUID SPEED_MOD_UUID = UUID.fromString("c4b6d6b0-0b9f-4f62-9d45-6f2fc093fd10");
    private static final UUID MAXHEALTH_MOD_UUID = UUID.fromString("8a0f2b2a-9c1b-4d6a-8b49-60f6b0d272a4");
    private final Map<String, Double> speedTagsAdd = new HashMap<>();
    private final Map<String, Double> maxHealthTagsAdd = new HashMap<>();
    private final Map<String, Map<String, Double>> abilityPerPoint = new HashMap<>();
    private final Map<String, Map<String, Double>> abilityPerMod = new HashMap<>();
    // Traits
    private final Set<String> poisonResistTags = new HashSet<>();
    private String relentlessTag = null;
    private int relentlessCooldownTicks = 20 * 60 * 5; // 5 min
    private final Map<UUID, Integer> relentlessCooldown = new HashMap<>();

    // Barbarian class
    private static class BarbarianConf {
        boolean enabled = true;
        String group = "class_barbarian";
        double fastSpeed = 0.03;
        double udDex = 1.0;
        double udCon = 1.0;
        double udMax = 8.0;
        double rageAttackBonus = 3.0;
        double rageDamageReduction = 0.25;
        int rageDuration = 400;
        int rageCooldown = 1200;
        // Reckless
        double recklessAttackBonus = 2.0;
        double recklessVulnerability = 0.20;
        int recklessDuration = 100;
        int recklessCooldown = 400;
        // Danger Sense
        double dangerExplosionReduction = 0.25;
        double dangerFireReduction = 0.15;
        // UI
        boolean uiActionbar = true;
        boolean uiShowCooldown = true; // actionbar: mostrar CD em tempo real
        boolean uiBossbar = true;
        boolean uiCooldownOnInventoryOpen = true; // mostrar CD apenas quando abrir inventario
    }
    private final BarbarianConf barbConf = new BarbarianConf();
    private static final UUID BARB_FAST_UUID = UUID.fromString("6c930a0b-94d1-4726-8f3a-8a2f0158f2a0");
    private static final UUID BARB_UD_ARMOR_UUID = UUID.fromString("b5f0a0a3-4a1a-4c82-b5a2-1c1c5a2f3f10");
    private static final UUID BARB_RAGE_DMG_UUID = UUID.fromString("f4b2a4f1-6c6b-4a9a-8a1f-2e3c4d5e6f70");
    private static final UUID BARB_RECKLESS_DMG_UUID = UUID.fromString("a3b11c4d-7ee6-47ad-b7cd-1d08fd3b1d04");
    private final Map<UUID, Integer> rageUntil = new HashMap<>();
    private final Map<UUID, Integer> rageCooldownUntil = new HashMap<>();
    private final Map<UUID, Integer> recklessUntil = new HashMap<>();
    private final Map<UUID, Integer> recklessCooldownUntil = new HashMap<>();
    private final Map<UUID, BossBar> barbBoss = new HashMap<>();
    // Ability init + level-ups tracking (per player)
    private final NamespacedKey KEY_INIT_CLASS = new NamespacedKey(this, "abl_init_class");
    private final NamespacedKey KEY_LEVEL_CLAIM = new NamespacedKey(this, "abl_level_claim");

    // Bard class (Bardo)
    private static class BardConf {
        boolean enabled = true;
        String group = "class_bard";
        // Inspiration (buff em alvo)
        double inspireAttackSpeed = 0.10;
        double inspireMoveSpeed = 0.02;
        double inspireDamage = 0.0;
        int inspireDuration = 900;   // 45s
        int inspireCooldown = 1200;  // 60s
        // Song of Rest (aura simples de regen)
        int songAmplifier = 0;       // Regen I
        double songRadius = 12.0;
        int songDuration = 600;      // 30s
        int songCooldown = 1200;     // 60s
        // Colleges (subclasses)
        String valorGroup = "class_bard_valor";
        double valorInspireDamageBonus = 1.0;
        String loreGroup = "class_bard_lore";
        int loreInspireExtraDuration = 200;
        double loreSongRadiusExtra = 4.0;
        // UI
        boolean uiActionbar = true;
        boolean uiShowCooldown = true;
        boolean uiBossbar = false;
    }
    private final BardConf bardConf = new BardConf();
    private static final UUID BARD_INSPIRE_AS_UUID = UUID.fromString("0c9b8f9e-2f24-4f6b-b6a3-6a1b5f30bb00");
    private static final UUID BARD_INSPIRE_MS_UUID = UUID.fromString("4e6f0e5f-d2f8-4b88-9a4f-9f0d0d2b0a10");
    private static final UUID BARD_INSPIRE_DMG_UUID = UUID.fromString("9b1f6f6e-0e3b-4a3e-94a4-2f1d9e3c7c11");
    private final Map<UUID, Integer> inspireUntil = new HashMap<>();          // alvo -> tick fim
    private final Map<UUID, Double> inspireExtraDmg = new HashMap<>();        // alvo -> bonus dmg
    private final Map<UUID, Integer> inspireCooldownUntil = new HashMap<>();  // fonte -> tick fim CD
    private final Map<UUID, Integer> songUntil = new HashMap<>();             // bardo -> tick fim
    private final Map<UUID, Integer> songCooldownUntil = new HashMap<>();     // bardo -> tick fim CD

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadAll();
        getLogger().info("RacesEffects habilitado. Periodo=" + periodTicks + " ticks");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        if (taskId != -1) Bukkit.getScheduler().cancelTask(taskId);
    }

    private void reloadAll() {
        reloadConfig();
        periodTicks = Math.max(1, getConfig().getInt("schedulePeriodTicks", 40));
        enforceGroupTags = getConfig().getBoolean("enforceGroupTags", true);
        hardNoRaceMode = getConfig().getBoolean("hardNoRaceMode", false);

        // Carregar efeitos por tag
        tagEffects.clear();
        ConfigurationSection tagsSec = getConfig().getConfigurationSection("apply.tags");
        if (tagsSec != null) {
            for (String tag : tagsSec.getKeys(false)) {
                ConfigurationSection effs = tagsSec.getConfigurationSection(tag);
                if (effs == null) continue;
                Map<String, EffectSpec> specs = new HashMap<>();
                for (String effName : effs.getKeys(false)) {
                    ConfigurationSection s = effs.getConfigurationSection(effName);
                    if (s == null) continue;
                    specs.put(effName, EffectSpec.fromConfig(s));
                }
                tagEffects.put(tag, specs);
            }
        }

        // Tags gerenciadas = todas as chaves conhecidas nos efeitos + escala
        managedTags.clear();
        managedTags.addAll(tagEffects.keySet());

        // Carregar escalas por tag (opcional)
        scaleTags.clear();
        ConfigurationSection scaleSec = getConfig().getConfigurationSection("apply.scaleTags");
        if (scaleSec != null) {
            for (String tag : scaleSec.getKeys(false)) {
                double v = scaleSec.getDouble(tag, 1.0);
                // limitar entre 0.1 e 2.0 por seguranAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a
                v = Math.max(0.1, Math.min(2.0, v));
                scaleTags.put(tag, v);
            }
        }
        managedTags.addAll(scaleTags.keySet());

        // Carregar grupos -> tags (LuckPerms registra permissao group.<nome>)
        groupToTags.clear();
        ConfigurationSection groupsSec = getConfig().getConfigurationSection("apply.groups");
        if (groupsSec != null) {
            for (String group : groupsSec.getKeys(false)) {
                List<String> list = groupsSec.getStringList(group);
                groupToTags.put(group.toLowerCase(Locale.ROOT), new ArrayList<>(list));
            }
        }

        // Attribute modifiers config
        speedTagsAdd.clear();
        ConfigurationSection speedSec = getConfig().getConfigurationSection("apply.attributeModifiers.speedTags");
        if (speedSec != null) {
            for (String tag : speedSec.getKeys(false)) {
                speedTagsAdd.put(tag, speedSec.getDouble(tag, 0.0));
            }
            managedTags.addAll(speedTagsAdd.keySet());
        }
        maxHealthTagsAdd.clear();
        ConfigurationSection hpSec = getConfig().getConfigurationSection("apply.attributeModifiers.maxHealthTags");
        if (hpSec != null) {
            for (String tag : hpSec.getKeys(false)) {
                maxHealthTagsAdd.put(tag, hpSec.getDouble(tag, 0.0));
            }
            managedTags.addAll(maxHealthTagsAdd.keySet());
        }

        // Traits
        poisonResistTags.clear();
        List<String> pr = getConfig().getStringList("apply.traits.poisonResistanceTags");
        if (pr != null) {
            poisonResistTags.addAll(pr);
            managedTags.addAll(poisonResistTags);
        }
        relentlessTag = getConfig().getString("apply.traits.relentless.tag", null);
        relentlessCooldownTicks = Math.max(20, getConfig().getInt("apply.traits.relentless.cooldownTicks", 20 * 60 * 5));
        if (relentlessTag != null && !relentlessTag.isEmpty()) managedTags.add(relentlessTag);

        // Abilities mapping (per point coefficients)
        abilityPerPoint.clear();
        ConfigurationSection ablPerPoint = getConfig().getConfigurationSection("apply.abilities.perPoint");
        if (ablPerPoint != null) {
            for (String abl : ablPerPoint.getKeys(false)) {
                ConfigurationSection sub = ablPerPoint.getConfigurationSection(abl);
                if (sub == null) continue;
                Map<String, Double> inner = new HashMap<>();
                for (String attrName : sub.getKeys(false)) {
                    inner.put(attrName, sub.getDouble(attrName, 0.0));
                }
                abilityPerPoint.put(abl.toUpperCase(Locale.ROOT), inner);
            }
        }

        // Barbarian class config
        ConfigurationSection cls = getConfig().getConfigurationSection("apply.classes.barbarian");
        if (cls != null) {
            barbConf.enabled = cls.getBoolean("enabled", true);
            barbConf.group = cls.getString("group", barbConf.group);
            ConfigurationSection fm = cls.getConfigurationSection("fastMovement");
            if (fm != null) barbConf.fastSpeed = fm.getDouble("speed", barbConf.fastSpeed);
            ConfigurationSection ud = cls.getConfigurationSection("unarmoredDefense");
            if (ud != null) {
                barbConf.udDex = ud.getDouble("armorPerDexMod", barbConf.udDex);
                barbConf.udCon = ud.getDouble("armorPerConMod", barbConf.udCon);
                barbConf.udMax = ud.getDouble("maxArmorBonus", barbConf.udMax);
            }
            ConfigurationSection rg = cls.getConfigurationSection("rage");
            if (rg != null) {
                barbConf.rageAttackBonus = rg.getDouble("attackBonus", barbConf.rageAttackBonus);
                barbConf.rageDamageReduction = rg.getDouble("damageReduction", barbConf.rageDamageReduction);
                barbConf.rageDuration = rg.getInt("durationTicks", barbConf.rageDuration);
                barbConf.rageCooldown = rg.getInt("cooldownTicks", barbConf.rageCooldown);
            }
            ConfigurationSection rk = cls.getConfigurationSection("reckless");
            if (rk != null) {
                barbConf.recklessAttackBonus = rk.getDouble("attackBonus", barbConf.recklessAttackBonus);
                barbConf.recklessVulnerability = rk.getDouble("vulnerability", barbConf.recklessVulnerability);
                barbConf.recklessDuration = rk.getInt("durationTicks", barbConf.recklessDuration);
                barbConf.recklessCooldown = rk.getInt("cooldownTicks", barbConf.recklessCooldown);
            }
            ConfigurationSection ds = cls.getConfigurationSection("dangerSense");
            if (ds != null) {
                barbConf.dangerExplosionReduction = ds.getDouble("explosionReduction", barbConf.dangerExplosionReduction);
                barbConf.dangerFireReduction = ds.getDouble("fireReduction", barbConf.dangerFireReduction);
            }
            ConfigurationSection ui = cls.getConfigurationSection("ui");
            if (ui != null) {
                barbConf.uiActionbar = ui.getBoolean("actionbar", barbConf.uiActionbar);
                barbConf.uiShowCooldown = ui.getBoolean("showCooldown", barbConf.uiShowCooldown);
                barbConf.uiBossbar = ui.getBoolean("bossbar", barbConf.uiBossbar);
                barbConf.uiCooldownOnInventoryOpen = ui.getBoolean("showCooldownOnInventoryOpen", barbConf.uiCooldownOnInventoryOpen);
            }
        }

        // Abilities mapping (per D&D modifier coefficients)
        abilityPerMod.clear();
        ConfigurationSection ablPerMod = getConfig().getConfigurationSection("apply.abilities.perMod");
        if (ablPerMod != null) {
            for (String abl : ablPerMod.getKeys(false)) {
                ConfigurationSection sub = ablPerMod.getConfigurationSection(abl);
                if (sub == null) continue;
                Map<String, Double> inner = new HashMap<>();
                for (String attrName : sub.getKeys(false)) {
                    inner.put(attrName, sub.getDouble(attrName, 0.0));
                }
                abilityPerMod.put(abl.toUpperCase(Locale.ROOT), inner);
            }
        }

        if (taskId != -1) Bukkit.getScheduler().cancelTask(taskId);
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::tickApply, 20L, periodTicks);
    }

    private void tickApply() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (hardNoRaceMode) {
                // Purga global: remove tags e efeitos de raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a para todos
                for (String t : new java.util.HashSet<>(p.getScoreboardTags())) {
                    if (managedTags.contains(t)) p.removeScoreboardTag(t);
                }
                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            }
            // Determinar tags desejadas por grupos atuais
            Set<String> desired = new HashSet<>();
            for (Map.Entry<String, List<String>> e : groupToTags.entrySet()) {
                String group = e.getKey();
                if (p.hasPermission("group." + group)) {
                    desired.addAll(e.getValue());
                }
            }

            // Enforce: adicionar tags faltantes e remover as nAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o desejadas (apenas as gerenciadas)
            if (enforceGroupTags) {
                // Remover
                for (String tag : new ArrayList<>(p.getScoreboardTags())) {
                    if (managedTags.contains(tag) && !desired.contains(tag)) {
                        p.removeScoreboardTag(tag);
                    }
                }
            }
            // Adicionar
            for (String tag : desired) {
                if (!p.getScoreboardTags().contains(tag)) {
                    p.addScoreboardTag(tag);
                }
            }

            // Aplicar efeitos por tags atuais (somente se houver raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a ou classe)
            boolean allowRaceEffects = !hardNoRaceMode && (hasAnyRaceGroup(p) || isBarbarian(p) || isBard(p));
            if (allowRaceEffects) {
                for (String tag : p.getScoreboardTags()) {
                    Map<String, EffectSpec> specs = tagEffects.get(tag);
                    if (specs == null) continue;
                    for (Map.Entry<String, EffectSpec> se : specs.entrySet()) {
                        PotionEffectType type = resolveEffect(se.getKey());
                        if (type == null) continue;
                        EffectSpec spec = se.getValue();
                        PotionEffect effect = new PotionEffect(type, spec.durationTicks, spec.amplifier, spec.ambient, spec.particles, spec.icon);
                        p.addPotionEffect(effect);
                    }
                }
            } else {
                // Sem raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a/classe: remova tags gerenciadas para evitar reaplicaAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o
                for (String t : new java.util.HashSet<>(p.getScoreboardTags())) {
                    if (managedTags.contains(t)) p.removeScoreboardTag(t);
                }
            }

            // Aplicar escala (tamanho) por tags atuais
            applyScaleFromTags(p);

            // Aplicar modificadores de atributos (velocidade, vida)
            applyAttributeModifiers(p);
            // Classe: Bardo (passivos iniciais)
            applyBardPassives(p);

            // Classe: BAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rbaro (passivos)
            applyBarbarianPassives(p);

            // UI (actionbar) - nÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â£o mostra CD de recarga ao vivo quando desativado em config
            sendBarbarianUI2(p);
            sendBardUI2(p);

            // AutodistribuiAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o inicial + level-ups
            autoAssignClassStart(p);
            autoApplyLevelUps(p);

            // Bloqueio de efeitos de raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a quando nAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o houver classe/raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a
            autoAssignByConfig(p, "bard");
            autoApplyLevelUpsByConfig(p, "bard");
            autoAssignByConfig(p, "warlock");
            autoApplyLevelUpsByConfig(p, "warlock");
            enforceNoRaceEffects(p);
        }
    }

    private void enforceNoRaceEffects(Player p) {
        boolean hasRace = hasAnyRaceGroup(p);
        boolean hasClass = isBarbarian(p) || isBard(p);
        if (hasRace || hasClass) return;
        try {
            if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
            if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            }
        } catch (Throwable ignored) {}
    }

    private void autoAssignClassStart(Player p) {
        try {
            if (!isBarbarian(p)) return;
            org.bukkit.persistence.PersistentDataContainer pdc = p.getPersistentDataContainer();
            if (pdc.has(KEY_INIT_CLASS, org.bukkit.persistence.PersistentDataType.INTEGER)) return;
            ConfigurationSection cls = getConfig().getConfigurationSection("apply.classes.barbarian.abilities");
            if (cls == null) return;
            List<String> order = cls.getStringList("recommendedOrder");
            List<Integer> start = (List<Integer>) (List<?>) cls.getIntegerList("startArray");
            String raceBonusTarget = cls.getString("raceBonusTarget", "STR").toUpperCase(java.util.Locale.ROOT);
            if (order.size() != 6 || start.size() != 6) return;
            // Aplicar 15,14,13,12,10,8 conforme ordem
            for (int i=0;i<6;i++) {
                String attr = order.get(i).toUpperCase(java.util.Locale.ROOT);
                int val = Math.min(20, Math.max(0, start.get(i)));
                setAbilityRaw(p, attr, val);
            }
            // +3 da raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a no alvo
            int cur = getAbility(p, raceBonusTarget);
            setAbilityRaw(p, raceBonusTarget, Math.min(20, cur + 3));
            pdc.set(KEY_INIT_CLASS, org.bukkit.persistence.PersistentDataType.INTEGER, 1);
            p.sendMessage("Classe aplicada: distribuiAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o inicial conforme PHB +3 da raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a.");
        } catch (Throwable ignored) { }
    }

    private void autoApplyLevelUps(Player p) {
        try {
            if (!isBarbarian(p)) return;
            ConfigurationSection cls = getConfig().getConfigurationSection("apply.classes.barbarian.abilities");
            if (cls == null) return;
            List<String> order = cls.getStringList("recommendedOrder");
            List<Map<?,?>> levels = cls.getMapList("levelUps");
            if (order.isEmpty() || levels.isEmpty()) return;
            org.bukkit.persistence.PersistentDataContainer pdc = p.getPersistentDataContainer();
            String claimed = pdc.getOrDefault(KEY_LEVEL_CLAIM, org.bukkit.persistence.PersistentDataType.STRING, "");
            java.util.Set<String> claimedSet = new java.util.HashSet<>(java.util.Arrays.asList(claimed.split(",")));
            int pl = p.getLevel();
            boolean changed = false;
            for (Map<?,?> m : levels) {
                Object lvlObj = m.containsKey("level") ? m.get("level") : 0;
                Object ptsObj = m.containsKey("points") ? m.get("points") : 0;
                int lvl = (lvlObj instanceof Number) ? ((Number)lvlObj).intValue() : 0;
                int pts = (ptsObj instanceof Number) ? ((Number)ptsObj).intValue() : 0;
                if (pl >= lvl && !claimedSet.contains(String.valueOf(lvl)) && pts > 0) {
                    // distribuir pts seguindo ordem, respeitando cap 20
                    int remaining = pts;
                    outer: while (remaining > 0) {
                        for (String a : order) {
                            String attr = a.toUpperCase(java.util.Locale.ROOT);
                            int cur = getAbility(p, attr);
                            if (cur < 20) { setAbilityRaw(p, attr, cur+1); remaining--; changed = true; if (remaining==0) break outer; }
                        }
                        // se jAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ estAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ tudo 20, sai
                        break;
                    }
                    claimedSet.add(String.valueOf(lvl));
                }
            }
            if (changed) {
                pdc.set(KEY_LEVEL_CLAIM, org.bukkit.persistence.PersistentDataType.STRING, String.join(",", claimedSet));
                p.sendMessage("Atributos melhorados por nAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­vel.");
            }
        } catch (Throwable ignored) { }
    }

    private boolean isBarbarian(Player p) {
        if (!barbConf.enabled) return false;
        return p.hasPermission("group." + barbConf.group);
    }

    private boolean isBard(Player p) {
        if (!bardConf.enabled) return false;
        return p.hasPermission("group." + bardConf.group);
    }

    private boolean isWearingAnyArmor(Player p) {
        ItemStack[] armor = p.getInventory().getArmorContents();
        for (ItemStack it : armor) if (it != null && it.getType() != org.bukkit.Material.AIR) return true;
        return false;
    }

    private void applyBarbarianPassives(Player p) {
        if (!isBarbarian(p)) {
            // remove class-specific modifiers
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_MOVEMENT_SPEED"), BARB_FAST_UUID, "RacesEffects-Barb-Fast", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ARMOR"), BARB_UD_ARMOR_UUID, "RacesEffects-Barb-UD", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_DAMAGE"), BARB_RAGE_DMG_UUID, "RacesEffects-Barb-RageDmg", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            return;
        }
        // Fast Movement (se nAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o usar armadura pesada; aproximado: sem armadura)
        double spd = 0.0;
        if (!isWearingAnyArmor(p)) spd = barbConf.fastSpeed;
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_MOVEMENT_SPEED"), BARB_FAST_UUID, "RacesEffects-Barb-Fast", spd, AttributeModifier.Operation.ADD_NUMBER);

        // Unarmored Defense: AC ~ armor baseado em DEX/CON mods
        if (!isWearingAnyArmor(p)) {
            int dex = getAbility(p, "DEX");
            int con = getAbility(p, "CON");
            int dexMod = abilityMod(dex);
            int conMod = abilityMod(con);
            double armorBonus = Math.max(0.0, dexMod * barbConf.udDex + conMod * barbConf.udCon);
            armorBonus = Math.min(armorBonus, barbConf.udMax);
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ARMOR"), BARB_UD_ARMOR_UUID, "RacesEffects-Barb-UD", armorBonus, AttributeModifier.Operation.ADD_NUMBER);
        } else {
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ARMOR"), BARB_UD_ARMOR_UUID, "RacesEffects-Barb-UD", 0.0, AttributeModifier.Operation.ADD_NUMBER);
        }

        // Rage damage bonus if active
        double rageDmg = isRaging(p) ? barbConf.rageAttackBonus : 0.0;
        // Reckless adds on top while active
        double recklessDmg = isReckless(p) ? barbConf.recklessAttackBonus : 0.0;
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_DAMAGE"), BARB_RAGE_DMG_UUID, "RacesEffects-Barb-RageDmg", rageDmg, AttributeModifier.Operation.ADD_NUMBER);
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_DAMAGE"), BARB_RECKLESS_DMG_UUID, "RacesEffects-Barb-RecklessDmg", recklessDmg, AttributeModifier.Operation.ADD_NUMBER);
    }

    private boolean isRaging(Player p) {
        Integer until = rageUntil.get(p.getUniqueId());
        if (until == null) return false;
        return Bukkit.getCurrentTick() < until;
    }

    private boolean isReckless(Player p) {
        Integer until = recklessUntil.get(p.getUniqueId());
        if (until == null) return false;
        return Bukkit.getCurrentTick() < until;
    }

    // Nova UI sem problemas de acentuaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â§ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â£o: usa Adventure + Unicode escapes
    private void sendBarbarianUI2(Player p) {
        if (!isBarbarian(p)) return;
        int now = Bukkit.getCurrentTick();
        Component msg = Component.empty();
        boolean any = false;
        Integer rUntil = rageUntil.get(p.getUniqueId());
        if (rUntil != null && now < rUntil) {
            int secs = Math.max(0, (rUntil - now) / 20);
            msg = msg.append(Component.text("F\u00FAria ", NamedTextColor.RED))
                     .append(Component.text(secs + "s", NamedTextColor.WHITE));
            any = true;
        } else if (barbConf.uiShowCooldown) {
            int cd = rageCooldownUntil.getOrDefault(p.getUniqueId(), 0);
            if (now < cd) {
                int secs = Math.max(0, (cd - now) / 20);
                msg = msg.append(Component.text("F\u00FAria CD ", NamedTextColor.GRAY))
                         .append(Component.text(secs + "s", NamedTextColor.WHITE));
                any = true;
            }
        }
        Integer rkUntil = recklessUntil.get(p.getUniqueId());
        if (rkUntil != null && now < rkUntil) {
            if (any) msg = msg.append(Component.text("  "));
            int secs = Math.max(0, (rkUntil - now) / 20);
            msg = msg.append(Component.text("Temer\u00E1rio ", NamedTextColor.GOLD))
                     .append(Component.text(secs + "s", NamedTextColor.WHITE));
            any = true;
        } else if (barbConf.uiShowCooldown) {
            int cd2 = recklessCooldownUntil.getOrDefault(p.getUniqueId(), 0);
            if (now < cd2) {
                if (any) msg = msg.append(Component.text("  "));
                int secs = Math.max(0, (cd2 - now) / 20);
                msg = msg.append(Component.text("Temer\u00E1rio CD ", NamedTextColor.GRAY))
                         .append(Component.text(secs + "s", NamedTextColor.WHITE));
                any = true;
            }
        }
        if (barbConf.uiActionbar && any) p.sendActionBar(msg);

        // BossBar
        BossBar bar = barbBoss.get(p.getUniqueId());
        if (!barbConf.uiBossbar) {
            if (bar != null) { p.hideBossBar(bar); barbBoss.remove(p.getUniqueId()); }
            return;
        }
        Integer rU = rageUntil.get(p.getUniqueId());
        Integer rkU = recklessUntil.get(p.getUniqueId());
        String title = null; float progress = 0f; BossBar.Color color = BossBar.Color.RED; boolean visible = false;
        if (rU != null && now < rU) {
            int rem = (rU - now); int total = Math.max(1, barbConf.rageDuration);
            progress = Math.max(0f, Math.min(1f, rem / (float) total));
            title = "F\u00FAria"; color = BossBar.Color.RED; visible = true;
        } else if (rkU != null && now < rkU) {
            int rem = (rkU - now); int total = Math.max(1, barbConf.recklessDuration);
            progress = Math.max(0f, Math.min(1f, rem / (float) total));
            title = "Temer\u00E1rio"; color = BossBar.Color.YELLOW; visible = true;
        }
        if (visible) {
            if (bar == null) {
                bar = BossBar.bossBar(Component.text(title), progress, color, BossBar.Overlay.NOTCHED_10);
                barbBoss.put(p.getUniqueId(), bar); p.showBossBar(bar);
            } else {
                bar.name(Component.text(title));
                bar.progress(progress);
                bar.color(color);
            }
        } else {
            if (bar != null) { p.hideBossBar(bar); barbBoss.remove(p.getUniqueId()); }
        }
    }

    private void sendBarbarianUI(Player p) {
        if (!isBarbarian(p) || !barbConf.uiActionbar) return;
        int now = Bukkit.getCurrentTick();
        StringBuilder sb = new StringBuilder();
        boolean any = false;
        Integer rUntil = rageUntil.get(p.getUniqueId());
        if (rUntil != null && now < rUntil) {
            int secs = Math.max(0, (rUntil - now) / 20);
            sb.append("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§cFAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âºria AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§f").append(secs).append("s");
            any = true;
        } else if (barbConf.uiShowCooldown) {
            int cd = rageCooldownUntil.getOrDefault(p.getUniqueId(), 0);
            if (now < cd) {
                int secs = Math.max(0, (cd - now) / 20);
                sb.append("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§7FAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âºria CD AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§f").append(secs).append("s");
                any = true;
            }
        }
        Integer rkUntil = recklessUntil.get(p.getUniqueId());
        if (rkUntil != null && now < rkUntil) {
            if (any) sb.append("  ");
            int secs = Math.max(0, (rkUntil - now) / 20);
            sb.append("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§6TemerAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rio AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§f").append(secs).append("s");
            any = true;
        } else if (barbConf.uiShowCooldown) {
            int cd2 = recklessCooldownUntil.getOrDefault(p.getUniqueId(), 0);
            if (now < cd2) {
                if (any) sb.append("  ");
                int secs = Math.max(0, (cd2 - now) / 20);
                sb.append("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§7TemerAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rio CD AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§f").append(secs).append("s");
                any = true;
            }
        }
        if (any) p.sendActionBar(Component.text(sb.toString()));

        // BossBar opcional
        BossBar bar = barbBoss.get(p.getUniqueId());
        if (!barbConf.uiBossbar) {
            if (bar != null) { p.hideBossBar(bar); barbBoss.remove(p.getUniqueId()); }
            return;
        }
        // Definir barra baseando-se em Rage (prioridade) ou TemerAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rio
        String title = null; float progress = 0f; BossBar.Color color = BossBar.Color.RED; boolean visible = false;
        if (rUntil != null && now < rUntil) {
            int rem = (rUntil - now); int total = Math.max(1, barbConf.rageDuration);
            progress = Math.max(0f, Math.min(1f, rem / (float) total));
            title = "FAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âºria"; color = BossBar.Color.RED; visible = true;
        } else if (rkUntil != null && now < rkUntil) {
            int rem = (rkUntil - now); int total = Math.max(1, barbConf.recklessDuration);
            progress = Math.max(0f, Math.min(1f, rem / (float) total));
            title = "TemerAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rio"; color = BossBar.Color.YELLOW; visible = true;
        }
        if (visible) {
            if (bar == null) {
                bar = BossBar.bossBar(Component.text(title), progress, color, BossBar.Overlay.NOTCHED_10);
                barbBoss.put(p.getUniqueId(), bar); p.showBossBar(bar);
            } else {
                bar.name(Component.text(title));
                bar.progress(progress);
                bar.color(color);
            }
        } else {
            if (bar != null) { p.hideBossBar(bar); barbBoss.remove(p.getUniqueId()); }
        }
    }

    private void applyAttributeModifiers(Player p) {
        // Speed
        double speedAdd = 0.0;
        for (String tag : p.getScoreboardTags()) {
            Double v = speedTagsAdd.get(tag);
            if (v != null) speedAdd += v;
        }
        AttributeInstance speedInst = getAttributeInstance(p, "GENERIC_MOVEMENT_SPEED");
        setOrUpdateModifier(speedInst, SPEED_MOD_UUID, "RacesEffects-Speed", speedAdd, AttributeModifier.Operation.ADD_NUMBER);

        // Max health (hp points)
        double hpAdd = 0.0;
        for (String tag : p.getScoreboardTags()) {
            Double v = maxHealthTagsAdd.get(tag);
            if (v != null) hpAdd += v;
        }
        // Apply as modifier (kept for compatibility)
        AttributeInstance hpInst = getAttributeInstance(p, "GENERIC_MAX_HEALTH");
        setOrUpdateModifier(hpInst, MAXHEALTH_MOD_UUID, "RacesEffects-HP", hpAdd, AttributeModifier.Operation.ADD_NUMBER);

        // Force base max health: 20.0 + (CON perMod) + tag hpAdd
        double conExtra = 0.0;
        Map<String,Integer> abNow = getAllAbilities(p);
        Integer conScore = abNow.get("CON");
        if (conScore != null) {
            int conMod = abilityMod(conScore);
            Map<String, Double> conMap = abilityPerMod.get("CON");
            if (conMap != null) {
                Double perMod = conMap.get("GENERIC_MAX_HEALTH");
                if (perMod != null) conExtra = perMod * conMod;
            }
        }
        if (hpInst != null) {
            try {
                java.lang.reflect.Method getBase = hpInst.getClass().getMethod("getBaseValue");
                java.lang.reflect.Method setBase = hpInst.getClass().getMethod("setBaseValue", double.class);
                double prevBase = ((Number) getBase.invoke(hpInst)).doubleValue();
                double desiredBase = 20.0 + conExtra + hpAdd;
                if (Math.abs(prevBase - desiredBase) > 1e-6) {
                    setBase.invoke(hpInst, desiredBase);
                    // heal up when max increases
                    if (desiredBase > prevBase) {
                        double heal = desiredBase - prevBase;
                        p.setHealth(Math.min(desiredBase, p.getHealth() + heal));
                    } else if (p.getHealth() > desiredBase) {
                        p.setHealth(desiredBase);
                    }
                }
                // Garante que os corações reflitam o max health real (desabilita HealthScale de outros plugins)
                try {
                    java.lang.reflect.Method isScaled = p.getClass().getMethod("isHealthScaled");
                    boolean scaled = (Boolean) isScaled.invoke(p);
                    if (scaled) {
                        java.lang.reflect.Method setScaled = p.getClass().getMethod("setHealthScaled", boolean.class);
                        setScaled.invoke(p, false);
                    }
                } catch (Throwable ignoreScale) { }
            } catch (Throwable ignored) {}
        }

        // Bard class config
        ConfigurationSection bard = getConfig().getConfigurationSection("apply.classes.bard");
        if (bard != null) {
            bardConf.enabled = bard.getBoolean("enabled", true);
            bardConf.group = bard.getString("group", bardConf.group);
            ConfigurationSection insp = bard.getConfigurationSection("inspiration");
            if (insp != null) {
                bardConf.inspireAttackSpeed = insp.getDouble("attackSpeedBonus", bardConf.inspireAttackSpeed);
                bardConf.inspireMoveSpeed = insp.getDouble("moveSpeedBonus", bardConf.inspireMoveSpeed);
                bardConf.inspireDamage = insp.getDouble("damageBonus", bardConf.inspireDamage);
                bardConf.inspireDuration = insp.getInt("durationTicks", bardConf.inspireDuration);
                bardConf.inspireCooldown = insp.getInt("cooldownTicks", bardConf.inspireCooldown);
            }
            ConfigurationSection song = bard.getConfigurationSection("song");
            if (song != null) {
                bardConf.songAmplifier = song.getInt("regenAmplifier", bardConf.songAmplifier);
                bardConf.songRadius = song.getDouble("radius", bardConf.songRadius);
                bardConf.songDuration = song.getInt("durationTicks", bardConf.songDuration);
                bardConf.songCooldown = song.getInt("cooldownTicks", bardConf.songCooldown);
            }
            ConfigurationSection colleges = bard.getConfigurationSection("colleges");
            if (colleges != null) {
                ConfigurationSection valor = colleges.getConfigurationSection("valor");
                if (valor != null) {
                    bardConf.valorGroup = valor.getString("group", bardConf.valorGroup);
                    bardConf.valorInspireDamageBonus = valor.getDouble("inspireDamageBonus", bardConf.valorInspireDamageBonus);
                }
                ConfigurationSection lore = colleges.getConfigurationSection("lore");
                if (lore != null) {
                    bardConf.loreGroup = lore.getString("group", bardConf.loreGroup);
                    bardConf.loreInspireExtraDuration = lore.getInt("inspireExtraDurationTicks", bardConf.loreInspireExtraDuration);
                    bardConf.loreSongRadiusExtra = lore.getDouble("songRadiusExtra", bardConf.loreSongRadiusExtra);
                }
            }
            ConfigurationSection ui = bard.getConfigurationSection("ui");
            if (ui != null) {
                bardConf.uiActionbar = ui.getBoolean("actionbar", bardConf.uiActionbar);
                bardConf.uiShowCooldown = ui.getBoolean("showCooldown", bardConf.uiShowCooldown);
                bardConf.uiBossbar = ui.getBoolean("bossbar", bardConf.uiBossbar);
            }
        }

        // Abilities-based modifiers
        Map<String,Integer> ab = getAllAbilities(p);
        if (!abilityPerMod.isEmpty()) {
            for (Map.Entry<String, Map<String, Double>> e : abilityPerMod.entrySet()) {
                String abl = e.getKey();
                int score = ab.getOrDefault(abl, 0);
                int mod = abilityMod(score);
                if (mod == 0) {
                    // ensure removal if present
                    for (String attrField : e.getValue().keySet()) {
                        AttributeInstance inst = getAttributeInstance(p, attrField);
                        UUID id = uuidForAbilityAttr(abl, attrField);
                        setOrUpdateModifier(inst, id, "RacesEffects-ABL-"+abl+"-"+attrField, 0.0, AttributeModifier.Operation.ADD_NUMBER);
                    }
                    continue;
                }
                for (Map.Entry<String, Double> inner : e.getValue().entrySet()) {
                    String attrField = inner.getKey();
                    double perMod = inner.getValue();
                    double amount = perMod * mod;
                    AttributeInstance inst = getAttributeInstance(p, attrField);
                    UUID id = uuidForAbilityAttr(abl, attrField);
                    setOrUpdateModifier(inst, id, "RacesEffects-ABL-"+abl+"-"+attrField, amount, AttributeModifier.Operation.ADD_NUMBER);
                }
            }
        } else if (!abilityPerPoint.isEmpty()) {
            for (Map.Entry<String, Map<String, Double>> e : abilityPerPoint.entrySet()) {
                String abl = e.getKey();
                int pts = ab.getOrDefault(abl, 0);
                if (pts == 0) {
                    for (String attrField : e.getValue().keySet()) {
                        AttributeInstance inst = getAttributeInstance(p, attrField);
                        UUID id = uuidForAbilityAttr(abl, attrField);
                        setOrUpdateModifier(inst, id, "RacesEffects-ABL-"+abl+"-"+attrField, 0.0, AttributeModifier.Operation.ADD_NUMBER);
                    }
                    continue;
                }
                for (Map.Entry<String, Double> inner : e.getValue().entrySet()) {
                    String attrField = inner.getKey();
                    double perPoint = inner.getValue();
                    double amount = perPoint * pts;
                    AttributeInstance inst = getAttributeInstance(p, attrField);
                    UUID id = uuidForAbilityAttr(abl, attrField);
                    setOrUpdateModifier(inst, id, "RacesEffects-ABL-"+abl+"-"+attrField, amount, AttributeModifier.Operation.ADD_NUMBER);
                }
            }
        }
    }

    private boolean setOrUpdateModifier(AttributeInstance inst, UUID id, String name, double amount, AttributeModifier.Operation op) {
        if (inst == null) return false;
        boolean had = false;
        for (AttributeModifier mod : new ArrayList<>(inst.getModifiers())) {
            if (mod.getUniqueId().equals(id)) {
                inst.removeModifier(mod);
                had = true;
            }
        }
        if (Math.abs(amount) < 1e-9) {
            return had;
        }
        AttributeModifier mod = new AttributeModifier(id, name, amount, op);
        inst.addModifier(mod);
        return true;
    }

    private AttributeInstance getAttributeInstance(Player p, String fieldName) {
        try {
            Class<?> attrClass = Class.forName("org.bukkit.attribute.Attribute");
            Object key = attrClass.getField(fieldName).get(null);
            java.lang.reflect.Method getAttribute = p.getClass().getMethod("getAttribute", attrClass);
            Object inst = getAttribute.invoke(p, key);
            return (AttributeInstance) inst;
        } catch (Throwable t) {
            return null;
        }
    }

    private UUID uuidForAbilityAttr(String ability, String attrField) {
        String basis = getName()+":abl:"+ability+":"+attrField;
        return UUID.nameUUIDFromBytes(basis.getBytes(StandardCharsets.UTF_8));
    }

    private int abilityMod(int score) {
        // D&D 5e modifier table: floor((score - 10) / 2)
        return (int) Math.floor((score - 10) / 2.0);
    }

    // Efeito sonoro ao ativar Fúria, dependendo do caminho do Bárbaro
    private void playRageSound(Player p) {
        Sound s = null; float vol = 1.0f; float pitch = 1.0f;
        String primary = getLuckPermsPrimaryGroup(p);
        if (primary != null) {
            if ("class_barbarian_totem_bear".equalsIgnoreCase(primary)) {
                s = Sound.ENTITY_POLAR_BEAR_WARNING; vol = 1.0f; pitch = 0.9f;
            } else if ("class_barbarian_totem_eagle".equalsIgnoreCase(primary)) {
                s = Sound.ENTITY_PHANTOM_AMBIENT; vol = 0.9f; pitch = 1.2f;
            } else if ("class_barbarian_totem_wolf".equalsIgnoreCase(primary)) {
                s = Sound.ENTITY_WOLF_HOWL; vol = 1.0f; pitch = 1.0f;
            } else if ("class_barbarian_berserker".equalsIgnoreCase(primary)) {
                s = Sound.ENTITY_PILLAGER_CELEBRATE; vol = 1.5f; pitch = 1.0f;
            }
        }
        if (s == null) {
            if (p.hasPermission("group.class_barbarian_totem_bear")) {
                s = Sound.ENTITY_POLAR_BEAR_WARNING; vol = 1.0f; pitch = 0.9f;
            } else if (p.hasPermission("group.class_barbarian_totem_eagle")) {
                s = Sound.ENTITY_PHANTOM_AMBIENT; vol = 0.9f; pitch = 1.2f;
            } else if (p.hasPermission("group.class_barbarian_totem_wolf")) {
                s = Sound.ENTITY_WOLF_HOWL; vol = 1.0f; pitch = 1.0f;
            } else if (p.hasPermission("group.class_barbarian_berserker")) {
                s = Sound.ENTITY_PILLAGER_CELEBRATE; vol = 1.5f; pitch = 1.0f;
            }
        }
        // Fallback: qualquer Bárbaro sem subclasse detectada -> use o grito do Pillager
        if (s == null && isBarbarian(p)) {
            s = Sound.ENTITY_PILLAGER_CELEBRATE; vol = 1.5f; pitch = 1.0f;
        }
        if (s != null) {
            // Toca diretamente para o jogador para garantir audibilidade
            p.playSound(p.getLocation(), s, vol, pitch);
        }
    }

    private boolean isSinging(Player p) {
        Integer until = songUntil.get(p.getUniqueId());
        if (until == null) return false;
        return Bukkit.getCurrentTick() < until;
    }

    private boolean isInspired(Player p) {
        Integer until = inspireUntil.get(p.getUniqueId());
        if (until == null) return false;
        return Bukkit.getCurrentTick() < until;
    }

    private void applyBardPassives(Player p) {
        // Clear if not Bard
        if (!isBard(p)) {
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_SPEED"), BARD_INSPIRE_AS_UUID, "RacesEffects-Bard-InspireAS", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_MOVEMENT_SPEED"), BARD_INSPIRE_MS_UUID, "RacesEffects-Bard-InspireMS", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_DAMAGE"), BARD_INSPIRE_DMG_UUID, "RacesEffects-Bard-InspireDMG", 0.0, AttributeModifier.Operation.ADD_NUMBER);
            return;
        }

        // Apply Inspiration bonuses if this player is a target
        double as = 0.0, ms = 0.0, dmg = 0.0;
        if (isInspired(p)) {
            as = bardConf.inspireAttackSpeed;
            ms = bardConf.inspireMoveSpeed;
            dmg = bardConf.inspireDamage + inspireExtraDmg.getOrDefault(p.getUniqueId(), 0.0);
        }
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_SPEED"), BARD_INSPIRE_AS_UUID, "RacesEffects-Bard-InspireAS", as, AttributeModifier.Operation.ADD_NUMBER);
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_MOVEMENT_SPEED"), BARD_INSPIRE_MS_UUID, "RacesEffects-Bard-InspireMS", ms, AttributeModifier.Operation.ADD_NUMBER);
        setOrUpdateModifier(getAttributeInstance(p, "GENERIC_ATTACK_DAMAGE"), BARD_INSPIRE_DMG_UUID, "RacesEffects-Bard-InspireDMG", dmg, AttributeModifier.Operation.ADD_NUMBER);

        // Song of Rest: simple regen aura while active
        if (isSinging(p)) {
            double radius = bardConf.songRadius;
            if (p.hasPermission("group." + bardConf.loreGroup)) radius += bardConf.loreSongRadiusExtra;
            int dur = 60; // reapply every tick pass
            try {
                PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, dur, Math.max(0, bardConf.songAmplifier), true, false, false);
                p.addPotionEffect(regen);
                for (Player q : Bukkit.getOnlinePlayers()) {
                    if (q == p) continue;
                    if (!q.getWorld().equals(p.getWorld())) continue;
                    if (q.getLocation().distanceSquared(p.getLocation()) <= radius * radius) {
                        q.addPotionEffect(regen);
                    }
                }
            } catch (Throwable ignored) {}
        }
    }

    private void sendBardUI2(Player p) {
        if (!isBard(p) || !bardConf.uiActionbar) return;
        int now = Bukkit.getCurrentTick();
        Component msg = Component.empty();
        boolean any = false;

        Integer sUntil = songUntil.get(p.getUniqueId());
        if (sUntil != null && now < sUntil) {
            int secs = Math.max(0, (sUntil - now) / 20);
            msg = msg.append(Component.text("Canção ", NamedTextColor.LIGHT_PURPLE))
                     .append(Component.text(secs + "s", NamedTextColor.WHITE));
            any = true;
        } else if (bardConf.uiShowCooldown) {
            int scd = songCooldownUntil.getOrDefault(p.getUniqueId(), 0);
            if (now < scd) {
                int secs = Math.max(0, (scd - now) / 20);
                msg = msg.append(Component.text("Canção CD ", NamedTextColor.GRAY))
                         .append(Component.text(secs + "s", NamedTextColor.WHITE));
                any = true;
            }
        }

        int icd = inspireCooldownUntil.getOrDefault(p.getUniqueId(), 0);
        if (now < icd && bardConf.uiShowCooldown) {
            if (any) msg = msg.append(Component.text("  "));
            int secs = Math.max(0, (icd - now) / 20);
            msg = msg.append(Component.text("Inspiração CD ", NamedTextColor.GRAY))
                     .append(Component.text(secs + "s", NamedTextColor.WHITE));
            any = true;
        }

        if (any) p.sendActionBar(msg);
    }
    private void applyScaleFromTags(Player p) {
        if (scaleTags.isEmpty()) return;
        double targetScale = 1.0;
        boolean found = false;
        for (String tag : p.getScoreboardTags()) {
            Double s = scaleTags.get(tag);
            if (s != null) {
                // se mAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âºltiplas, usa a menor (mais "pequeno" prevalece)
                if (!found) {
                    targetScale = s;
                    found = true;
                } else {
                    targetScale = Math.min(targetScale, s);
                }
            }
        }
        try {
            Class<?> attrClass = Class.forName("org.bukkit.attribute.Attribute");
            Object scaleConst = null;
            try {
                scaleConst = attrClass.getField("GENERIC_SCALE").get(null);
            } catch (NoSuchFieldException nsf) {
                // API sem GENERIC_SCALE
                return;
            }

            java.lang.reflect.Method getAttribute = p.getClass().getMethod("getAttribute", attrClass);
            Object inst = getAttribute.invoke(p, scaleConst);
            if (inst != null) {
                java.lang.reflect.Method getBase = inst.getClass().getMethod("getBaseValue");
                java.lang.reflect.Method setBase = inst.getClass().getMethod("setBaseValue", double.class);
                double current = ((Number) getBase.invoke(inst)).doubleValue();
                double desired = found ? targetScale : 1.0;
                if (Math.abs(current - desired) > 1e-6) {
                    setBase.invoke(inst, desired);
                }
            }
        } catch (NoSuchFieldError | Exception ex) {
            // Atributo pode nAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o existir em alguns builds; loga 1x por ciclo grande
            // Evita spam por tick
            if (Bukkit.getCurrentTick() % (20 * 30) == 0) {
                getLogger().warning("Atributo GENERIC_SCALE indisponAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­vel nesta versAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o/API. Escala serAAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ ignorada.");
            }
        }
    }

    private PotionEffectType resolveEffect(String name) {
        if (name == null) return null;
        String u = name.trim().toUpperCase(Locale.ROOT);
        PotionEffectType byName = PotionEffectType.getByName(u);
        if (byName != null) return byName;
        try {
            NamespacedKey key = NamespacedKey.fromString(u.toLowerCase(Locale.ROOT));
            if (key != null) return PotionEffectType.getByKey(key);
        } catch (Exception ignored) {}
        getLogger().warning("Efeito desconhecido no config: " + name);
        return null;
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();

        // Poison resistance (halve poison damage)
        if (e.getCause() == EntityDamageEvent.DamageCause.POISON) {
            for (String tag : p.getScoreboardTags()) {
                if (poisonResistTags.contains(tag)) {
                    e.setDamage(e.getDamage() * 0.5);
                    break;
                }
            }
        }

        // Relentless Endurance (Half-Orc)
        if (relentlessTag != null && p.getScoreboardTags().contains(relentlessTag)) {
            double finalDmg = e.getFinalDamage();
            double hp = p.getHealth();
            if (finalDmg >= hp) {
                int now = Bukkit.getCurrentTick();
                int next = relentlessCooldown.getOrDefault(p.getUniqueId(), 0);
                if (now >= next) {
                    e.setCancelled(true);
                    AttributeInstance mh = getAttributeInstance(p, "GENERIC_MAX_HEALTH");
                    double cap = (mh != null ? mh.getValue() : 1.0);
                    p.setHealth(Math.max(1.0, Math.min(cap, 1.0)));
                    relentlessCooldown.put(p.getUniqueId(), now + relentlessCooldownTicks);
                }
            }
        }

        // Barbarian Rage damage reduction (aproximaAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o: ataques fAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­sicos e projAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â©teis)
        if (isBarbarian(p) && isRaging(p)) {
            EntityDamageEvent.DamageCause c = e.getCause();
            switch (c) {
                case ENTITY_ATTACK:
                case ENTITY_SWEEP_ATTACK:
                case PROJECTILE:
                case ENTITY_EXPLOSION:
                case BLOCK_EXPLOSION:
                    e.setDamage(e.getDamage() * (1.0 - Math.max(0.0, Math.min(1.0, barbConf.rageDamageReduction))));
                    break;
                default:
                    // sem alteraAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o
            }
        }

        // Barbarian Reckless: vulnerabilidade (aumenta dano recebido de ataques e projAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â©teis)
        if (isBarbarian(p) && isReckless(p)) {
            EntityDamageEvent.DamageCause c = e.getCause();
            switch (c) {
                case ENTITY_ATTACK:
                case ENTITY_SWEEP_ATTACK:
                case PROJECTILE:
                    e.setDamage(e.getDamage() * (1.0 + Math.max(0.0, barbConf.recklessVulnerability)));
                    break;
                default:
                    // ignorar
            }
        }

        // Danger Sense: reduAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o extra se nAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o estiver cego
        if (isBarbarian(p) && !p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            EntityDamageEvent.DamageCause c = e.getCause();
            if (c == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || c == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                e.setDamage(e.getDamage() * (1.0 - Math.max(0.0, Math.min(1.0, barbConf.dangerExplosionReduction))));
            }
            if (c == EntityDamageEvent.DamageCause.FIRE || c == EntityDamageEvent.DamageCause.FIRE_TICK || c == EntityDamageEvent.DamageCause.LAVA || c == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                e.setDamage(e.getDamage() * (1.0 - Math.max(0.0, Math.min(1.0, barbConf.dangerFireReduction))));
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        if (isBarbarian(p) && isRaging(p)) {
            // bAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â´nus adicional jAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ aplicado via atributo; aqui poderAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­amos aplicar multiplicador se desejado
            // Mantemos somente atributo para evitar duplicidade
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase(java.util.Locale.ROOT);
        if (cmd.equals("raceseffects")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("raceseffects.admin")) { sender.sendMessage("Sem permissao."); return true; }
                reloadAll(); sender.sendMessage("RacesEffects recarregado."); return true;
            }
            sender.sendMessage("Uso: /" + label + " reload | debug <hp|ud>");
            return true;
        }

        if (cmd.equals("raceseffects") && args.length >= 1 && args[0].equalsIgnoreCase("debug")) {
            if (!(sender instanceof Player)) { sender.sendMessage("Somente in-game."); return true; }
            Player p = (Player) sender;
            String what = args.length >= 2 ? args[1].toLowerCase(java.util.Locale.ROOT) : "hp";
            if (what.equals("hp")) {
                Map<String,Integer> map = getAllAbilities(p);
                int con = map.getOrDefault("CON", 0);
                int conMod = abilityMod(con);
                double perMod = 0.0;
                Map<String, Double> conMap = abilityPerMod.get("CON");
                if (conMap != null) perMod = conMap.getOrDefault("GENERIC_MAX_HEALTH", 0.0);
                double conExtra = perMod * conMod;
                double tagHp = 0.0; for (String t : p.getScoreboardTags()) { tagHp += maxHealthTagsAdd.getOrDefault(t, 0.0); }
                AttributeInstance hp = getAttributeInstance(p, "GENERIC_MAX_HEALTH");
                double base = 0.0, value = 0.0;
                try {
                    if (hp != null) {
                        java.lang.reflect.Method getBase = hp.getClass().getMethod("getBaseValue");
                        java.lang.reflect.Method getVal = hp.getClass().getMethod("getValue");
                        base = ((Number)getBase.invoke(hp)).doubleValue();
                        value = ((Number)getVal.invoke(hp)).doubleValue();
                    }
                } catch (Throwable ignored) {}
                p.sendMessage("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§b[HP Debug] CON="+con+" mod="+conMod+" perMod="+perMod+" conExtra="+String.format(java.util.Locale.ROOT,"%.2f",conExtra)+" tags="+String.format(java.util.Locale.ROOT,"%.2f",tagHp));
                p.sendMessage("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§b[HP Debug] base="+String.format(java.util.Locale.ROOT,"%.2f",base)+" value="+String.format(java.util.Locale.ROOT,"%.2f",value)+" expected="+String.format(java.util.Locale.ROOT,"%.2f",(20.0+conExtra+tagHp)));
                return true;
            }
            if (what.equals("ud")) {
                AttributeInstance armor = getAttributeInstance(p, "GENERIC_ARMOR");
                double ud = 0.0;
                try { if (armor != null) for (AttributeModifier m : armor.getModifiers()) if (m.getUniqueId().equals(BARB_UD_ARMOR_UUID)) ud = m.getAmount(); } catch (Throwable ignored) {}
                p.sendMessage("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§b[UD Debug] UD="+String.format(java.util.Locale.ROOT,"%.2f",ud));
                return true;
            }
            sender.sendMessage("Uso: /" + label + " debug <hp|ud>");
            return true;
        }

        if (cmd.equals("raceseffects") && args.length >= 1 && args[0].equalsIgnoreCase("reset")) {
            if (!sender.hasPermission("raceseffects.admin")) { sender.sendMessage("Sem permissao."); return true; }
            Player target;
            if (args.length >= 2) {
                target = Bukkit.getPlayerExact(args[1]);
                if (target == null) { sender.sendMessage("Jogador offline ou nAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o encontrado."); return true; }
            } else {
                if (!(sender instanceof Player)) { sender.sendMessage("Uso: /"+label+" reset <player>"); return true; }
                target = (Player) sender;
            }
            // Limpa habilidades
            for (String a : ABILITIES) setAbilityRaw(target, a, 0);
            // Limpa PDC de classe
            try {
                target.getPersistentDataContainer().remove(KEY_INIT_CLASS);
                target.getPersistentDataContainer().remove(KEY_LEVEL_CLAIM);
                // também remover chaves por classe
                target.getPersistentDataContainer().remove(nskInitFor("bard"));
                target.getPersistentDataContainer().remove(nskLevelClaimFor("bard"));
                target.getPersistentDataContainer().remove(nskInitFor("warlock"));
                target.getPersistentDataContainer().remove(nskLevelClaimFor("warlock"));
            } catch (Throwable ignored) {}
            // Remove tags gerenciadas
            for (String t : new java.util.HashSet<>(target.getScoreboardTags())) {
                if (managedTags.contains(t)) target.removeScoreboardTag(t);
            }
            // Zera estados (furia/temerario/bossbar)
            rageUntil.remove(target.getUniqueId());
            rageCooldownUntil.remove(target.getUniqueId());
            recklessUntil.remove(target.getUniqueId());
            recklessCooldownUntil.remove(target.getUniqueId());
            BossBar bar = barbBoss.remove(target.getUniqueId());
            if (bar != null) target.hideBossBar(bar);
            sender.sendMessage("Reset concluAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â­do para " + target.getName() + ". Reatribua raAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a/classe pelo LuckPerms se desejar.");
            target.sendMessage("Seu perfil foi resetado. Relogue se necessAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡rio.");
            return true;
        }
        if (cmd.equals("inspire")) {
            if (!(sender instanceof Player)) { sender.sendMessage("Somente in-game."); return true; }
            Player src = (Player) sender;
            if (!src.hasPermission("raceseffects.inspire")) { src.sendMessage("Sem permissao."); return true; }
            if (!isBard(src)) { src.sendMessage("Apenas bardos podem usar inspiração."); return true; }
            if (args.length < 1) { src.sendMessage("Uso: /"+label+" <jogador>"); return true; }
            int now = Bukkit.getCurrentTick();
            int cd = inspireCooldownUntil.getOrDefault(src.getUniqueId(), 0);
            if (now < cd) { src.sendMessage("Inspiração em recarga."); return true; }
            Player tgt = Bukkit.getPlayerExact(args[0]);
            if (tgt == null) { src.sendMessage("Jogador offline ou não encontrado."); return true; }
            int dur = bardConf.inspireDuration;
            if (src.hasPermission("group." + bardConf.loreGroup)) dur += Math.max(0, bardConf.loreInspireExtraDuration);
            double extraDmg = 0.0;
            if (src.hasPermission("group." + bardConf.valorGroup)) extraDmg += bardConf.valorInspireDamageBonus;
            if (bardConf.inspireDamage > 0.0) extraDmg += 0.0; // base já aplicado via atributo abaixo
            inspireUntil.put(tgt.getUniqueId(), now + dur);
            inspireCooldownUntil.put(src.getUniqueId(), now + bardConf.inspireCooldown);
            if (extraDmg > 0.0) inspireExtraDmg.put(tgt.getUniqueId(), extraDmg); else inspireExtraDmg.remove(tgt.getUniqueId());
            src.sendMessage("Você inspira " + tgt.getName() + " por " + (dur/20) + "s.");
            tgt.sendMessage("Você recebeu Inspiração de " + src.getName() + "!");
            return true;
        }
        if (cmd.equals("song")) {
            if (!(sender instanceof Player)) { sender.sendMessage("Somente in-game."); return true; }
            Player p3 = (Player) sender;
            if (!p3.hasPermission("raceseffects.song")) { p3.sendMessage("Sem permissao."); return true; }
            if (!isBard(p3)) { p3.sendMessage("Apenas bardos podem usar canção."); return true; }
            String sub = args.length > 0 ? args[0].toLowerCase(java.util.Locale.ROOT) : "toggle";
            boolean wantOn = sub.equals("on") || sub.equals("start") || (sub.equals("toggle") && !isSinging(p3));
            if (wantOn) {
                int now = Bukkit.getCurrentTick();
                int cd = songCooldownUntil.getOrDefault(p3.getUniqueId(), 0);
                if (now < cd) { p3.sendMessage("Canção em recarga."); return true; }
                songUntil.put(p3.getUniqueId(), now + bardConf.songDuration);
                songCooldownUntil.put(p3.getUniqueId(), now + bardConf.songCooldown);
                p3.sendMessage("Canção ativa por " + (bardConf.songDuration/20) + "s.");
            } else {
                songUntil.remove(p3.getUniqueId()); p3.sendMessage("Canção encerrada.");
            }
            return true;
        }
        if (cmd.equals("abilities")) {
            if (!(sender instanceof org.bukkit.entity.Player)) { sender.sendMessage("Somente in-game."); return true; }
            org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
            if (!p.hasPermission("raceseffects.abilities")) { p.sendMessage("Sem permissao."); return true; }
            if (args.length == 0) { showAbilitiesStatus(p); return true; }
            // Admin/OP shortcut: setar tudo para 20 (ignora pool)
            if (args.length == 1 && args[0].equalsIgnoreCase("max")) {
                if (!p.hasPermission("raceseffects.admin")) { p.sendMessage("Sem permissao."); return true; }
                for (String a : ABILITIES) setAbilityRaw(p, a, 20);
                showAbilitiesStatus(p); return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("reset")) { resetAbilities(p); showAbilitiesStatus(p); return true; }
            if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
                String attr = args[1].toUpperCase(java.util.Locale.ROOT);
                if (!java.util.Arrays.asList("STR","DEX","CON","INT","WIS","CHA").contains(attr)) { p.sendMessage("Atributo invalido."); return true; }
                int val; try { val = java.lang.Integer.parseInt(args[2]); } catch (Exception e) { p.sendMessage("Valor invalido."); return true; }
                setAbility(p, attr, val); showAbilitiesStatus(p); return true;
            }
            if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
                String attr = args[1].toUpperCase(java.util.Locale.ROOT);
                if (!java.util.Arrays.asList("STR","DEX","CON","INT","WIS","CHA").contains(attr)) { p.sendMessage("Atributo invalido."); return true; }
                int delta; try { delta = java.lang.Integer.parseInt(args[2]); } catch (Exception e) { p.sendMessage("Valor invalido."); return true; }
                int cur = getAbility(p, attr); setAbility(p, attr, cur + delta); showAbilitiesStatus(p); return true;
            }
            p.sendMessage("Uso: /" + label + " | set <attr> <valor> | add <attr> <delta> | reset"); return true;
        }
        if (cmd.equals("rage")) {
            if (!(sender instanceof org.bukkit.entity.Player)) { sender.sendMessage("Somente in-game."); return true; }
            org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
            if (!p.hasPermission("raceseffects.rage")) { p.sendMessage("Sem permissao."); return true; }
            if (!isBarbarian(p)) { p.sendMessage("Apenas barbaros podem usar furia."); return true; }
            String sub = args.length > 0 ? args[0].toLowerCase(java.util.Locale.ROOT) : "toggle";
            boolean wantOn = sub.equals("on") || sub.equals("start") || (sub.equals("toggle") && !isRaging(p));
            if (wantOn) {
                int now = org.bukkit.Bukkit.getCurrentTick();
                int cd = rageCooldownUntil.getOrDefault(p.getUniqueId(), 0);
                if (now < cd) { p.sendMessage("Furia em recarga."); return true; }
                rageUntil.put(p.getUniqueId(), now + barbConf.rageDuration);
                rageCooldownUntil.put(p.getUniqueId(), now + barbConf.rageCooldown);
                // Som temAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡tico conforme subclasse ao iniciar a FAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Âºria
                try { playRageSound(p); } catch (Throwable ignored) {}
                p.sendMessage("Voce entra em furia por " + (barbConf.rageDuration/20) + "s.");
            } else {
                rageUntil.remove(p.getUniqueId()); p.sendMessage("Furia encerrada.");
            }
            return true;
        }
        if (cmd.equals("reckless")) {
            if (!(sender instanceof org.bukkit.entity.Player)) { sender.sendMessage("Somente in-game."); return true; }
            org.bukkit.entity.Player p2 = (org.bukkit.entity.Player) sender;
            if (!p2.hasPermission("raceseffects.reckless")) { p2.sendMessage("Sem permissao."); return true; }
            if (!isBarbarian(p2)) { p2.sendMessage("Apenas barbaros podem usar ataque temerario."); return true; }
            String sub2 = args.length > 0 ? args[0].toLowerCase(java.util.Locale.ROOT) : "toggle";
            boolean wantOn2 = sub2.equals("on") || sub2.equals("start") || (sub2.equals("toggle") && !isReckless(p2));
            if (wantOn2) {
                int now2 = org.bukkit.Bukkit.getCurrentTick();
                int cd2 = recklessCooldownUntil.getOrDefault(p2.getUniqueId(), 0);
                if (now2 < cd2) { p2.sendMessage("Temerario em recarga."); return true; }
                recklessUntil.put(p2.getUniqueId(), now2 + barbConf.recklessDuration);
                recklessCooldownUntil.put(p2.getUniqueId(), now2 + barbConf.recklessCooldown);
                p2.sendMessage("Ataque Temerario ativo por " + (barbConf.recklessDuration/20) + "s.");
            } else {
                recklessUntil.remove(p2.getUniqueId()); p2.sendMessage("Ataque Temerario encerrado.");
            }
            return true;
        }
        return false;
    }

    private static class EffectSpec {
        final int durationTicks;
        final int amplifier;
        final boolean ambient;
        final boolean particles;
        final boolean icon;

        private EffectSpec(int durationTicks, int amplifier, boolean ambient, boolean particles, boolean icon) {
            this.durationTicks = durationTicks;
            this.amplifier = amplifier;
            this.ambient = ambient;
            this.particles = particles;
            this.icon = icon;
        }

        static EffectSpec fromConfig(ConfigurationSection s) {
            int d = Math.max(40, s.getInt("durationTicks", 300));
            int a = Math.max(0, s.getInt("amplifier", 0));
            boolean amb = s.getBoolean("ambient", true);
            boolean par = s.getBoolean("particles", false);
            boolean ic = s.getBoolean("icon", false);
            return new EffectSpec(d, a, amb, par, ic);
        }
    }

    private void showAbilitiesStatus(Player p) {
        Map<String,Integer> map = getAllAbilities(p);
        int pool = ABILITY_BASE_POOL + (hasAnyRaceGroup(p) ? ABILITY_RACE_BONUS : 0);
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        int remaining = pool - sum;
        p.sendMessage("Atributos (cap 20) a Pool: " + ABILITY_BASE_POOL + " + BAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â´nus RaAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§a: " + (hasAnyRaceGroup(p)?ABILITY_RACE_BONUS:0) + " = " + pool);
        p.sendMessage("Distribua atAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â© " + pool + " pontos. Ex.: 15,14,13,12,10,8 (72). Nenhum atributo pode passar de 20.");
        String status;
        if (remaining >= 0) {
            status = "Restantes: " + remaining;
        } else {
            status = "Override admin: +" + Math.abs(remaining);
        }
        p.sendMessage("STR " + map.get("STR") + "  DEX " + map.get("DEX") + "  CON " + map.get("CON") + "  INT " + map.get("INT") + "  WIS " + map.get("WIS") + "  CHA " + map.get("CHA") + "  | " + status);
        p.sendMessage("Comandos: /abilities set <attr> <valor> | /abilities add <attr> <delta> | /abilities reset");
    }

    private boolean hasAnyRaceGroup(Player p) {
        for (String group : groupToTags.keySet()) {
            if (group.startsWith("race_") && p.hasPermission("group." + group)) return true;
        }
        return false;
    }

    private void resetAbilities(Player p) {
        for (String a : ABILITIES) setAbilityRaw(p, a, 0);
    }

    private void setAbility(Player p, String attr, int value) {
        if (value < 0) value = 0;
        if (value > 20) { p.sendMessage("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§cValor mAÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¡ximo por atributo: 20."); value = 20; }
        Map<String,Integer> map = getAllAbilities(p);
        int pool = ABILITY_BASE_POOL + (hasAnyRaceGroup(p) ? ABILITY_RACE_BONUS : 0);
        int old = map.getOrDefault(attr, 0);
        map.put(attr, value);
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        if (sum > pool) {
            // rollback visual apenas
            map.put(attr, old);
            p.sendMessage("AÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â§cSem pontos suficientes. Ficaria acima de " + pool + ".");
            return;
        }
        setAbilityRaw(p, attr, value);
    }

    private Map<String,Integer> getAllAbilities(Player p) {
        Map<String,Integer> r = new LinkedHashMap<>();
        for (String a : ABILITIES) r.put(a, getAbility(p, a));
        return r;
    }

    private int getAbility(Player p, String attr) {
        Integer v = p.getPersistentDataContainer().get(nsk("abl_"+attr), org.bukkit.persistence.PersistentDataType.INTEGER);
        return v == null ? 0 : v;
    }
    private void setAbilityRaw(Player p, String attr, int value) {
        p.getPersistentDataContainer().set(nsk("abl_"+attr), org.bukkit.persistence.PersistentDataType.INTEGER, value);
    }

    private NamespacedKey nskInitFor(String cls){ return new NamespacedKey(this, "abl_init_"+cls); }
    private NamespacedKey nskLevelClaimFor(String cls){ return new NamespacedKey(this, "abl_level_claim_"+cls); }

    private boolean hasClassByConfig(Player p, String cls){
        ConfigurationSection sec = getConfig().getConfigurationSection("apply.classes."+cls);
        if (sec == null) return false;
        if (!sec.getBoolean("enabled", true)) return false;
        String group = sec.getString("group", "class_"+cls);
        return p.hasPermission("group."+group);
    }

    private void autoAssignByConfig(Player p, String cls){
        try {
            if (!hasClassByConfig(p, cls)) return;
            org.bukkit.persistence.PersistentDataContainer pdc = p.getPersistentDataContainer();
            NamespacedKey k = nskInitFor(cls);
            if (pdc.has(k, org.bukkit.persistence.PersistentDataType.INTEGER)) return;
            ConfigurationSection abil = getConfig().getConfigurationSection("apply.classes."+cls+".abilities");
            if (abil == null) return;
            java.util.List<String> order = abil.getStringList("recommendedOrder");
            java.util.List<Integer> start = (java.util.List<Integer>)(java.util.List<?>) abil.getIntegerList("startArray");
            String raceBonusTarget = abil.getString("raceBonusTarget", "STR").toUpperCase(java.util.Locale.ROOT);
            if (order.size() != 6 || start.size() != 6) return;
            for (int i=0;i<6;i++) {
                String attr = order.get(i).toUpperCase(java.util.Locale.ROOT);
                int val = Math.min(20, Math.max(0, start.get(i)));
                setAbilityRaw(p, attr, val);
            }
            int cur = getAbility(p, raceBonusTarget);
            setAbilityRaw(p, raceBonusTarget, Math.min(20, cur + 3));
            pdc.set(k, org.bukkit.persistence.PersistentDataType.INTEGER, 1);
            p.sendMessage("Classe "+cls+": distribuição inicial aplicada.");
        } catch (Throwable ignored) { }
    }

    private void autoApplyLevelUpsByConfig(Player p, String cls){
        try {
            if (!hasClassByConfig(p, cls)) return;
            ConfigurationSection abil = getConfig().getConfigurationSection("apply.classes."+cls+".abilities");
            if (abil == null) return;
            java.util.List<String> order = abil.getStringList("recommendedOrder");
            java.util.List<java.util.Map<?,?>> levels = abil.getMapList("levelUps");
            if (order == null || order.isEmpty() || levels == null || levels.isEmpty()) return;
            org.bukkit.persistence.PersistentDataContainer pdc = p.getPersistentDataContainer();
            NamespacedKey k = nskLevelClaimFor(cls);
            String claimed = pdc.getOrDefault(k, org.bukkit.persistence.PersistentDataType.STRING, "");
            java.util.Set<String> claimedSet = new java.util.HashSet<>(java.util.Arrays.asList(claimed.isEmpty()? new String[]{} : claimed.split(",")));
            int pl = p.getLevel(); boolean changed = false;
            for (java.util.Map<?,?> m : levels) {
                Object lvlObj = m.containsKey("level") ? m.get("level") : 0;
                Object ptsObj = m.containsKey("points") ? m.get("points") : 0;
                int lvl = (lvlObj instanceof Number) ? ((Number)lvlObj).intValue() : 0;
                int pts = (ptsObj instanceof Number) ? ((Number)ptsObj).intValue() : 0;
                if (pl >= lvl && !claimedSet.contains(String.valueOf(lvl)) && pts > 0) {
                    int remaining = pts;
                    outer: while (remaining > 0) {
                        for (String a : order) {
                            String attr = a.toUpperCase(java.util.Locale.ROOT);
                            int cur = getAbility(p, attr);
                            if (cur < 20) { setAbilityRaw(p, attr, cur+1); remaining--; changed = true; if (remaining==0) break outer; }
                        }
                        break;
                    }
                    claimedSet.add(String.valueOf(lvl));
                }
            }
            if (changed) {
                pdc.set(k, org.bukkit.persistence.PersistentDataType.STRING, String.join(",", claimedSet));
                p.sendMessage("Atributos melhorados por nível ("+cls+").");
            }
        } catch (Throwable ignored) { }
    }
    private String getLuckPermsPrimaryGroup(Player p) {
        try {
            Class<?> lpProvider = Class.forName("net.luckperms.api.LuckPermsProvider");
            java.lang.reflect.Method get = lpProvider.getMethod("get");
            Object api = get.invoke(null);
            Class<?> luckPermsClass = Class.forName("net.luckperms.api.LuckPerms");
            java.lang.reflect.Method getUserManager = luckPermsClass.getMethod("getUserManager");
            Object userManager = getUserManager.invoke(api);
            Class<?> userManagerClass = Class.forName("net.luckperms.api.model.user.UserManager");
            java.util.UUID uuid = p.getUniqueId();
            java.lang.reflect.Method getUser = userManagerClass.getMethod("getUser", java.util.UUID.class);
            Object user = getUser.invoke(userManager, uuid);
            if (user == null) return null;
            Class<?> userClass = Class.forName("net.luckperms.api.model.user.User");
            java.lang.reflect.Method getPrimaryGroup = userClass.getMethod("getPrimaryGroup");
            Object pg = getPrimaryGroup.invoke(user);
            return pg == null ? null : pg.toString();
        } catch (Throwable t) {
            return null;
        }
    }

}
