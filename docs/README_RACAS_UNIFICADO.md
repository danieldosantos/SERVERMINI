# Guia Unificado de Raças

Este arquivo consolida toda a implementação atual de raças no servidor Paper 1.21.x. Use-o como
fonte de verdade para configurar efeitos, comandos e permissões. Sempre alinhe ajustes com as
regras descritas em `README_BALANCEAMENTO.md`.

## Convenções Gerais
- **Aplicação padrão**: entregar livro via `/raceitem give <jogador> <id>` (Skript) ou aplicar o
grupo LuckPerms correspondente (`lp user <nick> parent set race_<id>`).
- **Passivos**: definidos em **RacesEffects** (`raceseffects/src/main/resources/config.yml`). O plugin
aplica tags e atributos automaticamente enquanto o jogador estiver no grupo `race_*`.
- **Ativos**: disparados por **Skript** e executados em **MythicMobs** (habilidades e itens). Use
SkBee para proteger NBT e skRayFall apenas para efeitos visuais temporários.
- **Sinergia de atributos**: os bônus recomendados consideram o sistema de pontos do
`docs/ATRIBUTOS.md`. Configure ajustes finos no AuraSkills para sincronizar com classes.
- **Cooldowns**: respeitar limites de uptime ≤60 % e recargas mínimas (45 s/90 s/180 s+) conforme o
guia de balanceamento.

## Famílias de Raças

### Elfo — `race_elf`
- **Visão geral**: elfo base com mobilidade equilibrada, indicado para builds de precisão.
- **Passivos (RacesEffects)**: `race_darkvision` (visão noturna persistente).
- **Ativos (Skript + MythicMobs)**: nenhum ativo dedicado.
- **Sinergias (AuraSkills)**: priorizar DEX e WIS para aproveitar bônus de velocidade e percepção.
- **Grupos LuckPerms**: `race_elf`.
- **Comandos**:
  - `/raceitem give <nick> elf`
  - `lp user <nick> parent set race_elf`
- **Plugins envolvidos**: LuckPerms, RacesEffects.

### Alto Elfo — `race_elf_high`
- **Visão geral**: elfo arcano com retaliação elementar moderada.
- **Passivos**: Darkvision; retaliação randômica (35 % nada, 25 % Espinhos 2.0, 25 % Veneno I 5 s,
15 % Náusea I 5 s) com intervalo interno de 5 s.
- **Ativos**: Amuleto Alto Elfo (`ElfHighAmulet`, MythicMobs) reforça a retaliação on-demand (Skript
`elf_high_retaliate.sk`).
- **Sinergias**: INT primária, WIS secundária para builds de mago ou bardo.
- **Grupos**: `race_elf_high`.
- **Comandos**:
  - `/raceitem give <nick> highelf`
  - `lp user <nick> parent set race_elf_high`
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs.

### Elfo da Floresta — `race_elf_wood`
- **Visão geral**: explorador veloz com camuflagem situacional.
- **Passivos**: Darkvision; `trait_speed_woodelf` (+0,02 velocidade); camuflagem em árvores (Skript
`elf_wood_hide.sk`).
- **Ativos**: Broche da Floresta (`ElfWoodBrooch`) concede Invisibilidade 10 s, cooldown 180 s (Skript
`class_items_new_fixed.sk`).
- **Sinergias**: DEX primária; CHA secundária para furtividade e suporte bardo/ladino.
- **Grupos**: `race_elf_wood`.
- **Comandos**:
  - `/raceitem give <nick> elfwood`
  - `lp user <nick> parent set race_elf_wood`
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs, SkBee (proteção NBT).

### Drow — `race_elf_drow`
- **Visão geral**: especialista subterrâneo com venenos ofensivos.
- **Passivos**: Darkvision; Fraqueza I quando Y>0; chance de 45 % de Poison I (6 s) + Wither I (3 s) em
targets abaixo de Y≤-39 (`drow_depth_poison.sk`).
- **Ativos**: Teia de Drow (`DrowWeb`) — teleporte curto com 3 cargas, recarga global 240 s (MythicMobs
+ Skript `class_items_new_fixed.sk`).
- **Sinergias**: DEX e INT para builds de ladino/warlock.
- **Grupos**: `race_elf_drow`.
- **Comandos**:
  - `/raceitem give <nick> drow`
  - `lp user <nick> parent set race_elf_drow`
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs, SkBee, skRayFall (efeito da teia).

### Anão — `race_dwarf`
- **Visão geral**: resiliência contra veneno e combate pesado.
- **Passivos**: Darkvision; `trait_poison_resist` (mitigação 50 %).
- **Ativos**: Picareta Anã (`DwarfRacialPick`) indestrutível (MythicMobs) para mineração.
- **Sinergias**: CON e STR para builds de tanque (bárbaro/paladino).
- **Grupos**: `race_dwarf`.
- **Comandos**:
  - `/raceitem give <nick> dwarf`
  - `lp user <nick> parent set race_dwarf`
- **Plugins**: LuckPerms, RacesEffects, MythicMobs, SkBee.

### Anão da Colina — `race_dwarf_hill`
- **Visão geral**: versão de alta vitalidade.
- **Passivos**: herda base + `trait_hp_hilldwarf` (+2,0 vida máxima).
- **Ativos**: idem ao anão base.
- **Sinergias**: CON/WIS para clérigos defensivos.
- **Grupos**: `race_dwarf_hill`.
- **Comandos**: `/raceitem give <nick> hilldwarf` | `lp user <nick> parent set race_dwarf_hill`.
- **Plugins**: LuckPerms, RacesEffects.

### Anão da Montanha — `race_dwarf_mountain`
- **Visão geral**: ofensivo pesado.
- **Passivos**: Darkvision; Força I permanente.
- **Ativos**: picareta temática.
- **Sinergias**: STR/CON para bárbaros e guerreiros.
- **Grupos**: `race_dwarf_mountain`.
- **Comandos**: `/raceitem give <nick> mountaindwarf` | `lp user <nick> parent set race_dwarf_mountain`.
- **Plugins**: LuckPerms, RacesEffects.

### Anão das Profundezas — `race_dwarf_deep`
- **Visão geral**: controle com cegueira no atacante.
- **Passivos**: base + 45 % de aplicar Cegueira 10 s com cooldown interno 20 s.
- **Ativos**: Picareta com partículas extras via skRayFall.
- **Sinergias**: CON principal; WIS para resistências adicionais.
- **Grupos**: `race_dwarf_deep`.
- **Comandos**: `/raceitem give <nick> deepdwarf` | `lp user <nick> parent set race_dwarf_deep`.
- **Plugins**: LuckPerms, RacesEffects, skRayFall.

### Gnomo — `race_gnome`
- **Visão geral**: suporte técnico e arcano.
- **Passivos**: Darkvision.
- **Ativos**: Engrenagem Gnômica (`GnomeCog`) com efeitos aleatórios (Skript `class_items_new_fixed.sk`).
- **Sinergias**: INT e CHA para bardos/feiticeiros.
- **Grupos**: `race_gnome`.
- **Comandos**: `/raceitem give <nick> gnome` | `lp user <nick> parent set race_gnome`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs.

### Gnomo da Floresta — `race_gnome_forest`
- **Visão geral**: foco em evasão e furtividade.
- **Passivos**: Darkvision; Speed III condicional ≤50 % HP; 35 % chance de invisibilidade 10 s ao sofrer
crítico.
- **Ativos**: Engrenagem Gnômica ofensiva (buff 6 s, cooldown 420 s).
- **Sinergias**: DEX e INT para builds de ladino/bardo.
- **Grupos**: `race_gnome_forest`.
- **Comandos**: `/raceitem give <nick> gnome_forest` | `lp user <nick> parent set race_gnome_forest`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs, SkBee.

### Gnomo das Rochas — `race_gnome_rock`
- **Visão geral**: suporte ofensivo de curta distância.
- **Passivos**: Darkvision; Força II curta ao causar dano; knockback ampliado.
- **Ativos**: Engrenagem Gnômica defensiva (escudo instantâneo).
- **Sinergias**: INT e STR para guerreiros arcanos.
- **Grupos**: `race_gnome_rock`.
- **Comandos**: `/raceitem give <nick> gnome_rock` | `lp user <nick> parent set race_gnome_rock`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs.

### Halfling Pés-Leves — `race_halfling_lightfoot`
- **Visão geral**: controle leve e mobilidade.
- **Passivos**: Supersalto I; Sorte II; bonificadores de furtividade via AuraSkills.
- **Ativos**: Trombeta Halfling (`HalflingHorn`) — paralisa até 5 alvos por 7 s, cooldown 120 s.
- **Sinergias**: DEX e CHA, ideal para ladinos/bardos.
- **Grupos**: `race_halfling_lightfoot`.
- **Comandos**: `/raceitem give <nick> halfling_lightfoot` | `lp user <nick> parent set race_halfling_lightfoot`.
- **Plugins**: LuckPerms, RacesEffects, MythicMobs, Skript, skRayFall.

### Halfling Robusto — `race_halfling_stout`
- **Visão geral**: resistência veneno e suporte utilitário.
- **Passivos**: Pressa I curta; Sorte II; mitigação de veneno 20 % com recarga 15 s.
- **Ativos**: Trombeta compartilhada.
- **Sinergias**: CON e DEX para builds tanque-suporte.
- **Grupos**: `race_halfling_stout`.
- **Comandos**: `/raceitem give <nick> halfling_stout` | `lp user <nick> parent set race_halfling_stout`.
- **Plugins**: LuckPerms, RacesEffects, Skript.

### Meio-Elfo — `race_half_elf`
- **Visão geral**: híbrido móvel com suporte leve.
- **Passivos**: Darkvision; `Dolphin's Grace` periódico; Queda Lenta automática acima de 6 blocos.
- **Ativos**: Pena do Meio-Elfo (`HalfElfFeather`) — Queda Lenta 7 s, 2 cargas, cooldown 180 s.
- **Sinergias**: CHA e DEX (bardos, paladinos).
- **Grupos**: `race_half_elf`.
- **Comandos**: `/raceitem give <nick> halfelf` | `lp user <nick> parent set race_half_elf`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs.

### Meio-Orc — `race_half_orc`
- **Visão geral**: ofensivo resiliente com salvamento extra.
- **Passivos**: Darkvision; `Relentless Endurance` (sobrevive com 20 % HP, cooldown 300 s com feedback).
- **Ativos**: Talismã Berserker (`HalfOrcBerserkCharm`) — +15 % dano 12 s, cooldown 90 s.
- **Sinergias**: STR e CON (bárbaro/guerreiro).
- **Grupos**: `race_half_orc`.
- **Comandos**: `/raceitem give <nick> halforc` | `lp user <nick> parent set race_half_orc`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs.

### Humano — `race_human`
- **Visão geral**: versátil com regeneração condicional.
- **Passivos**: Regeneration II 15 s quando ≤50 % HP, com janela morta 20 s.
- **Ativos**: Medalha Humana (`HumanMedal`) — redistribui 3 pontos extras do AuraSkills 1×/dia.
- **Sinergias**: distribuição flexível (+3 pontos livres); combine com classes híbridas.
- **Grupos**: `race_human`.
- **Comandos**: `/raceitem give <nick> human` | `lp user <nick> parent set race_human`.
- **Plugins**: LuckPerms, RacesEffects, Skript, AuraSkills.

### Tiefling — `race_tiefling`
- **Visão geral**: especialista em fogo e mobilidade no Nether.
- **Passivos**: Darkvision; `resist_fire`; Velocidade II no Nether até absorver 3 hits fortes (gera exaustão
30 s).
- **Ativos**: Selo Tiefling (`TieflingSigil`) — orbe de fogo 140 % dano, cooldown 90 s.
- **Sinergias**: CHA e INT (warlock/sorcerer).
- **Grupos**: `race_tiefling`.
- **Comandos**: `/raceitem give <nick> tiefling` | `lp user <nick> parent set race_tiefling`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs, skRayFall.

### Dragonborn (base) — `race_dragonborn`
- **Visão geral**: recebe +1,0 vida e habilita sopro elemental.
- **Passivos**: bônus genérico de vida + tags elementais por subtipo.
- **Ativos**: Livro entrega item `DragonBreath` específico.
- **Sinergias**: STR/CHA para builds corpo-a-corpo ou casters carismáticos.
- **Grupos**: `race_dragonborn` (transitório) e subgrupos abaixo.
- **Comandos**: `/raceitem give <nick> dragonborn_<elemento>`.
- **Plugins**: LuckPerms, RacesEffects, Skript, MythicMobs, SkBee.

#### Dragonborn do Fogo — `race_dragonborn_fire`
- **Passivos**: `resist_fire`.
- **Ativos**: Sopro de fogo em cone (dano + queimadura 6 s), cooldown 45 s.
- **Sinergias**: STR e CHA.
- **Comandos adicionais**: `/breath` (Skript `breath_cloud.sk`).
- **Plugins**: MythicMobs (skill `Dragonborn_FireBreath`).

#### Dragonborn do Gelo — `race_dragonborn_cold`
- **Passivos**: Imunidade a Powder Snow; tag `db_cold`.
- **Ativos**: Sopro gélido 5×5 com Lentidão II 8 s, cooldown 45 s.
- **Sinergias**: CON e WIS.

#### Dragonborn do Relâmpago — `race_dragonborn_lightning`
- **Passivos**: Imunidade breve a dano elétrico (2 s pós-raio).
- **Ativos**: Linha de relâmpago com `lightning_bolt`, stun 1,5 s.
- **Sinergias**: DEX e INT.

#### Dragonborn do Ácido — `race_dragonborn_acid`
- **Passivos**: Mitigação corrosiva 60 %; limpa Wither a cada 30 s.
- **Ativos**: Nuvem ácida (DOT 6 s, enfraquecimento).
- **Sinergias**: CON e STR.

#### Dragonborn Venenoso — `race_dragonborn_poison`
- **Passivos**: `db_poison` (mitigação 60 % veneno).
- **Ativos**: Nuvem venenosa 5 s (DOT 4 %/s), cooldown 45 s.
- **Sinergias**: CON e WIS.

## Plugins por Função
- **LuckPerms**: gerenciamento de grupos `race_*` e `race_<subtipo>`.
- **RacesEffects**: aplica passivos, atributos e tags.
- **Skript**: entrega itens, valida seleção, controla cooldowns.
- **MythicMobs**: habilidades de sopro, itens temáticos e efeitos visuais.
- **SkBee**: proteção de NBT nos itens raciais.
- **skRayFall**: partículas extras nas habilidades ativas (ex.: Drow Web, Selo Tiefling).
- **AuraSkills**: sincroniza atributos e pontos extras de raça conforme recomendado.

Mantenha este arquivo atualizado ao introduzir novas raças ou revisar habilidades existentes.
