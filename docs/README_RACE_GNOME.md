# Raça Gnomo (Minecraft + D&D 5e)

## Visão Geral
- **Identidade:** engenhosos, curiosos e resilientes; combinam magia sutil com engenhocas artesanais.
- **Plugins-chave:**
  - RacesEffects → passivos condicionais, DR mental e escudos escalonados.
  - ProSkillAPI → habilidades ativas com tiers T1–T4, recursos de carga e ultimates situacionais.
  - MythicMobs → focos raciais (`GnomeTinkerTools`, `ForestGnomeCharm`, etc.).
  - Skript → comandos `/engenho`, `/intuicao`, `/sussurro`, `/salto_folhas`, `/silvestre`, `/baluarte`, `/gadget`, `/cupula` e kits QA.
  - LuckPerms → grupos `race_gnome`, `race_gnome.forest`, `race_gnome.rock`.
  - PlaceholderAPI → usa `{racial_cd_main}`, `{racial_uptime}`, `{racial_resist_mental}`, `{racial_shield}`, `{racial_charges}`.

## Passivos e Ativos
| Raça/Sub-raça | Traço | Tipo | Duração | CD | Área | Uptime Máx. | Efeito | Observações |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Gnomo (base) | Astúcia Gnomesca | DR mental (RacesEffects) | 6s | 30s | Self | 20% | -12% dano/control mental | Ativa ao receber efeito mental; DR compartilhada com categoria `mental` |
| Gnomo (base) | Pequena Estatura | Mitigação knockback | 12s | 24s | Self | 50% | -12% knockback, hitbox 0.96 | Só em combate; respeita caps globais |
| Gnomo (base) | Engenho Rápido | Skill ativa (carga) | 8–10s | 75s | Self | — | -10/12% recarga itens | 1 carga racial; não ultrapassa +0.05 velocidade global |
| Gnomo (base) | Intuição Arcana | Skill ativa | 6s janela | 90s | 8–10 blocos | — | Revela buffs; -10/12% CD próxima skill | Consome 20 mana; não reduz ultimates >180s |
| Gnomo da Floresta | Passos Silvestres | Velocidade condicional | 10s | 30s | Self | 33% | +0.02 velocidade | Apenas em biomas `forest`; DR com bônus de classe |
| Gnomo da Floresta | Camuflagem Natural | Stealth condicional | 8s | 16s | Self | 50% | -15% detecção | Requer folhas/vinhas/tall grass próximos |
| Gnomo da Floresta | Sussurro Amigo | Skill ativa | 6–7s | 70–75s | Raio 6–7 | — | Charm PvE (animais/feras) | Sem efeito em PvP/bosses; máximo 6 alvos |
| Gnomo da Floresta | Salto de Galho | Mobilidade | Instantâneo | 55–60s | Dash 5–6 | — | Dash + Slow Fall 2–3s | Requer blocos de folhas/vinhas |
| Gnomo da Floresta | Círculo Silvestre | Ultimate | 10s | 200–210s | Raio 7 | — | -10/12% precisão inimiga, +10/12% velocidade aliados | Requer bioma floresta; respeita cap +0.05 velocidade |
| Gnomo da Rocha | Engenho Gnomesco | Escudo em combate | 6s | 45s | Self | 40% | Escudo 60–100 | Substitui escudos existentes ao reaplicar |
| Gnomo da Rocha | Resiliência a Toxinas | DR veneno/ácido | 8s | 24s | Self | 33% | -15% dano veneno/ácido | Usa DR categoria `poison`; não remove fraquezas |
| Gnomo da Rocha | Baluarte Improvisado | Skill ativa | 6s | 90s | Self | — | Escudo 120–180 | Substitui escudos menores; 1 carga racial |
| Gnomo da Rocha | Gadget Estático | Totem | 8s | 70–75s | Raio 6 | — | -30/35% dano projétil | Totem único; DR com barreiras mágicas |
| Gnomo da Rocha | Cúpula de Engenho | Ultimate | 8s | 200–210s | Raio 6 | — | -15/18% dano, bloqueia 1 projétil pesado | Bloqueio 1 projétil/aliado; respeita cap 45% |

## Itens Raciais
| Item | Plugin | Skill/Comando | Permissão (LuckPerms) | Notas |
| --- | --- | --- | --- | --- |
| `GnomeTinkerTools` | MythicMobs | Engenho Rápido | `race_gnome` | `stack=1`, `Unbreakable`, aciona skill base |
| `ForestGnomeCharm` | MythicMobs | Sussurro Amigo da Floresta | `race_gnome.forest` | Charm PvE, combina com /sussurro |
| `LeafstepBoots` | MythicMobs | Salto de Galho | `race_gnome.forest` | Usa clique direito; mantém cor verde temática |
| `RockGnomeWrench` | MythicMobs | Baluarte Improvisado | `race_gnome.rock` | Substitui escudos menores |
| `StaticGadgetKit` | MythicMobs | Gadget Estático | `race_gnome.rock` | Totem anti-projéteis, sem stack múltiplo |

## Placeholders & HUD
- **Scoreboard padrão:**
  - `{race_active}`, `{racial_uptime}`, `{racial_resist_mental}`, `{racial_shield}`, `{racial_charges}`, `{racial_cd_main}`.
- **Eventos ActionBar:** mensagens enviadas via `races_gnome_cmds.sk` ao acionar habilidades.

## Sinergias & Restrições
- DR mental (`Astúcia Gnomesca`) compartilha pool com Clérigo Twilight e itens psíquicos → usar exaustão após 30%.
- Bônus de recarga de Engenho Rápido entra na mesma categoria de Artífice/Alquimista; aplicar multiplicador decrescente para manter `maxSpeedBonus` (0.05).
- Escudos (`Engenho Gnomesco` + `Baluarte Improvisado`) substituem instâncias anteriores para evitar stacking infinito; mitigações totais ≤45% com a classe.
- Velocidade bônus (Passos Silvestres + efeitos de classe) deve aplicar DR para não ultrapassar +0.05.
- Totem de Gadget Estático e Cúpula de Engenho compartilham categoria de redução com barreiras externas → redução adicional após primeira sobreposição é 50%.

## Cooldowns & Recursos
- **Cargas raciais:** Engenho Rápido e Baluarte Improvisado consomem 1 carga; regeneração automática após 15s fora de combate (`races_gnome_cmds.sk`).
- **Ultimate:** Círculo Silvestre e Cúpula de Engenho → 210s (T4 reduz para 200s).
- **Situações:** Intuição Arcana 90s, Sussurro 75s, Gadget 75s, Salto 60s (condicional por blocos).

## Compatibilidade
- LuckPerms groups garantem gating; itens MythicMobs possuem NBT `race-lock`.
- PlaceholderAPI preparado para scoreboard/actionbar; CMI/Essentials podem entregar kit via `/kit_racial_gnomo`.
- Testes detalhados em `docs/TESTPLAN_RACE_GNOME.md` asseguram PvE (fear, projéteis, hordas) e PvP (controle, captura de ponto).

