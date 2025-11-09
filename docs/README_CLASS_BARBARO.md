# Classe Bárbaro (Minecraft + D&D 5e)

## Visão Geral
- **Função:** Híbrido tanque/DPS corpo a corpo com gerenciamento de **Fúria**.
- **Recurso:** `rage_stacks` (0–3 no Tier 1-2, +1 adicional a partir do Tier 3). Regenera fora de combate; consumo explícito em skills e refresh de Fúria.
- **Identidade:** Controle de janelas de poder, risco/recompensa, mitigação física limitada a 45% e interação com resistências raciais via retornos decrescentes.

## Progressão por Tier
| Tier | Vida Base (aprox.) | Mitigação adicional | Velocidade bônus | Skills chave |
| --- | --- | --- | --- | --- |
| T1 (nível 1) | 36 HP | +5 física | +0.00 | Fúria, Ataque Temerário, Defesa sem Armadura |
| T2 (nível 5) | 58 HP | +8 física | +0.01 | Vigor fora de combate, Percepção do Perigo aprimorada |
| T3 (nível 11) | 92 HP | +12 física | +0.015 | +1 Rage máximo, acesso a ultimates de subclasse |
| T4 (nível 17) | 128 HP | +15 física | +0.02 | Window management completo + mitigação situacional |

## Skills da Classe Base
- **Fúria** (`barbarian_fury`): buff de 120s, 180s CD. +18% dano melee, +18% resistência física, imunidade a empurrões por 6s ao ativar. Reaplicar consome 1 Rage para estender 60s.
- **Ataque Temerário** (`barbarian_reckless_attack`): 20s de ofensiva (crit/penetração). Recebe +15% dano durante o efeito; CD 60s.
- **Percepção do Perigo** (`barbarian_danger_sense`): gatilho pós-aoe; -25% dano de explosões/projéteis por 6s com ICD 12s.
- **Defesa sem Armadura** (`barbarian_unarmored_defense`): bônus moderado de mitigação e esquiva enquanto sem armadura pesada.
- **Vigor Primal** (`barbarian_vigor`): cura 1.5% HP a cada 4s após 8s fora de combate.

## Caminhos Primitivos
### Caminho do Berserker
- **Tema:** Burst e risco adicional.
- **Itens:** Berrante Berserker, Machado Sanguinário, Ídolo da Carnificina.
- **Skills:**
  - `barbarian_berserker_bestial_roar`: debuff -10% dano inimigo (6 blocos/12s, custo 1 Rage, CD 75s).
  - `barbarian_berserker_bloody_charge`: dash 5 blocos, 160% dano +20% mitigação/6s (CD 45s).
  - `barbarian_berserker_frenzied_retaliation`: reflete 30% dano por 8s quando <50% HP (CD 90s).
  - `barbarian_berserker_relentless_impetus`: remove lentidão, +0.04 velocidade/10s (CD 60s).
  - `barbarian_berserker_carnage` (ULT): 15s cleave 3 blocos com 25% lifesteal; consome 1 Rage (CD 210s).
  - Passiva `barbarian_berserker_risk_reward`: +10% dano recebido se Fúria + Ataque Temerário ativos.

### Guardião Totêmico
- **Tema:** Auras tribais e suporte físico.
- **Itens:** Totens do Urso, Lobo, Fênix (mais águia e tigre via SkillAPI).
- **Skills:**
  - `barbarian_totem_bear`: -35% dano físico aliados (5 blocos/10s; custa 1 Rage; CD 120s).
  - `barbarian_totem_eagle`: visão noturna + +1 bloco de alcance/15s (CD 75s).
  - `barbarian_totem_wolf`: marca inimigo e concede +12% dano aliado por 12s (CD 90s).
  - `barbarian_totem_tiger`: +30% velocidade de ataque/8s (CD 70s).
  - `barbarian_totem_phoenix` (ULT): aura 8 blocos/12s, revive aliado 1x com 40% HP; bloqueio em arenas via flag; CD 240s.
  - Passiva `barbarian_totem_spirit_link`: aplica retorno decrescente com resistências raciais.

### Arauto da Tempestade
- **Tema:** Controle elemental e zonas de tempestade.
- **Itens:** Martelo do Trovão, Manopla Galvânica, Olho do Furacão.
- **Skills:**
  - `barbarian_storm_thunder_hammer`: 140% dano + stun 2s (CD 45s).
  - `barbarian_storm_galvanic_surge`: 3 descargas de 40% dano em 8s (CD 60s).
  - `barbarian_storm_wind_cloak`: empurra 4 blocos e +50% resistência a projéteis/10s (CD 90s).
  - `barbarian_storm_tempest_cauldron`: chuva elétrica 6 blocos (50%/2s por 10s; CD 120s).
  - `barbarian_storm_eye` (ULT): vórtice 8 blocos/12s com vulnerabilidade elétrica +20% (CD 210s).
  - Passiva `barbarian_storm_tempest_aegis`: escudo elétrico com integração RacesEffects.

## Recursos e HUD
- PlaceholderAPI: `%skills_rage_stacks%`, `%skills_fury_time%`, `%skills_major_cd%` mapeados para scoreboard `barbarian_rage`, `barbarian_fury_time`, `barbarian_major_cd`.
- Rage regenera 1 stack a cada 6s após 12s sem combate.

## Comandos (Skript)
| Comando | Permissão | Efeito |
| --- | --- | --- |
| `/kit_barbaro` | `group.class_barbarian` | Entrega itens focais e reinicializa HUD |
| `/furia` (`/rage`) | `group.class_barbarian` | Cast Fúria |
| `/temerario` | `group.class_barbarian` | Cast Ataque Temerário |
| `/berserk` | `group.class_barbarian.berserker` | Cast Carnificina Implacável |
| `/totem_urso`, `/totem_lobo`, `/totem_fenix` | `group.class_barbarian.totem` | Totens principais |
| `/storm_olho` | `group.class_barbarian.storm` | Olho do Furacão |
| `/barbaro_ooc on/off` | `op` | Força estado fora de combate para testes |

## Itens MythicMobs
- **BarbarianRageCharm:** ativa Fúria e revela Rage no HUD.
- **BerserkerWarHorn / BattleAxe / Totem:** gatilhos das skills de burst.
- **TotemBearCharm / Wolf / Phoenix:** suportes para Guardião Totêmico.
- **StormWarHammer / ChargeGauntlet / EyeTotem:** habilidades elétricas do Arauto.
- Todos carregam `PersistentData.classlock` para validação via LuckPerms.

## Integração com RacesEffects
- Skills que concedem resistência consultam `%raceseffects_resistance_elemental%` e disparam scripts para aplicar **exaustão de resistência**.
- Buffs não removem fraquezas raciais; apenas mitigam até o limite global de 45%.

## Limites e Caps
- Velocidade máxima adicionada pelo kit: +0.05 (considerando Impetus e bônus base).
- Resistência total pré-multiplicadores: 45% (Fúria + Totem Urso + passivas).
- Roubo de vida máximo: 25% (Carnificina Implacável), respeitando teto em PvP.
- Áreas padrão: 3–8 blocos conforme especificado nas skills.

## Checklist de Carregamento
1. Copiar arquivos para diretórios indicados.
2. Executar comandos LuckPerms do arquivo `plugins/LuckPerms/barbarian_groups.txt`.
3. `/skript reload barbarian_cmds` para registrar comandos.
4. `/mm reload items` para habilitar itens focais.
5. Validar HUD via PlaceholderAPI (`/papi parse me %skills_rage_stacks%`).
