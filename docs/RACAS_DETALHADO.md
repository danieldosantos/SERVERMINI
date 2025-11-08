# Raças – Traços Detalhados (Projeto Atual)

Este documento descreve, de forma prática, os traços raciais que estão implementados hoje no servidor. Para cada raça, há um resumo do que o jogador recebe, em que condições, e a origem no código (arquivo). Os grupos citados são os do LuckPerms.

Como aplicar uma raça rapidamente:
- Comando: `lp user <nick> parent set <grupo>`
- Ex.: `lp user Daniel parent set race_elf_drow`

Observação: vários efeitos “sempre ativos” são reaplicados em loop curto para não cairem. Onde aparecer “reaplica por Xs”, significa que o script renova periodicamente.

## Elfos

- Elfo (base) – grupo `race_elf`
  - Darkvision (Visão Noturna discreta, sem partículas)
  - Fonte: `raceseffects/src/main/resources/config.yml:88` (tag `race_darkvision`)

- Alto Elfo – grupo `race_elf_high`
  - Darkvision (como acima)
  - Retaliação ao sofrer dano: chance ponderada por evento de dano no atacante
    - 35% nada, 25% Espinhos (dano 2.0), 25% Veneno I por 5s, 15% Náusea I por 5s
    - Fontes: `raceseffects/src/main/resources/config.yml:32` (trait `highElfRetaliate`),
      `plugins/Skript/scripts/elf_high_retaliate.sk:options` e evento `on damage`

- Elfo da Floresta – grupo `race_elf_wood`
  - Darkvision
  - +2% de velocidade de movimento (atributo)
    - Fonte: `raceseffects/src/main/resources/config.yml:18-20` (tag `trait_speed_woodelf`)
  - Camuflagem nas árvores: se ficar imóvel 30s próximo a tronco (com folhas próximas), ganha Invisibilidade por 6s e renova enquanto mantiver a condição
    - Fonte: `plugins/Skript/scripts/elf_wood_hide.sk`

- Drow – grupo `race_elf_drow`
  - Darkvision (pelo projeto, mesmo pacote de “race_darkvision”)
  - Fraqueza I por 7s (reaplica) enquanto estiver acima da camada Y>0; em Y≤0 a fraqueza é removida
    - Fonte: `plugins/Skript/scripts/race_drow.sk:18-27`
  - “Veneno mortal nas profundezas”: ao causar dano em Y≤-39 no Overworld, 45% de chance de aplicar Poison I (6s) + Wither I (3s) no alvo (mobs e jogadores)
    - Fonte: `plugins/Skript/scripts/drow_depth_poison.sk:1-30`
  - Item racial “Teia de Drow”: teleporte curto com 3 cargas e recarga total de 4 min
    - Uso protegido em cliques direito/esquerdo
    - Fontes: `plugins/Skript/scripts/class_items_new_fixed.sk:404-426`,
      `plugins/Skript/scripts/zzz_drow_rightclick_fix.sk`

## Anões

- Anão (base) – grupo `race_dwarf`
  - Darkvision; Resistência a Veneno (trait)
  - Fonte: `raceseffects/src/main/resources/config.yml:129-131` (tags `race_darkvision`, `trait_poison_resist`)

- Anão da Colina – grupo `race_dwarf_hill`
  - Darkvision; Resistência a Veneno
  - +1 coração de vida máxima (+2.0 pontos de vida)
  - Fonte: `raceseffects/src/main/resources/config.yml:129-136` (`trait_hp_hilldwarf`)

- Anão da Montanha – grupo `race_dwarf_mountain`
  - Darkvision
  - Fonte: `raceseffects/src/main/resources/config.yml:131`
  

## Gnomos

- Gnomo (base), da Floresta e da Rocha – grupos `race_gnome`, `race_gnome_forest`, `race_gnome_rock`
  - Darkvision
  - Fonte: `raceseffects/src/main/resources/config.yml:132-134`

## Halflings

- Halfling (base) – grupo `race_halfling`
  - Sem efeito específico no projeto atual
- Halfling Stout – grupo `race_halfling_stout`
  - Resistência a Veneno (trait)
  - Fonte: `raceseffects/src/main/resources/config.yml:137`
- Halfling Lightfoot – grupo `race_halfling_lightfoot`
  - Sem efeito específico no projeto atual

## Meio-Elfo e Meio-Orc

- Meio‑Elfo – grupo `race_half_elf`
  - Darkvision; “Dolphin’s Grace” curto (mobilidade aquática)
  - Fonte: `raceseffects/src/main/resources/config.yml:138` (tag `race_dolphins_grace`)

- Meio‑Orc – grupo `race_half_orc`
  - Darkvision; “Relentless Endurance” (um salvamento/seguro de vida em cooldown)
  - Fonte: `raceseffects/src/main/resources/config.yml:139` (trait `relentless`)

## Humanos

- Humano – grupo `race_human`
  - Regeneração condicional: quando a vida cai a 50% ou menos, aplica Regeneration II por 15s e renova automaticamente enquanto permanecer sob o limiar (com controle de janela para não empilhar).
  - Implementação baseada em checagem periódica sem `wait`, usando timestamps para confiabilidade.
  - Fonte: `plugins/Skript/scripts/human_regen.sk` (opções `check_ticks`, `human_regen_seconds`, `human_regen_amp`)

## Tiefling

- Tiefling – grupo `race_tiefling`
  - Darkvision; Resistência a Fogo (Fire Resistance reaplicada)
  - Velocidade II no Nether enquanto permanecer lá (remove ao sair)
  - Fontes: `raceseffects/src/main/resources/config.yml:140` (tag `resist_fire`),
    `plugins/Skript/scripts/tiefling_soulspeed.sk`

## Dragonborn (Draconato)

- Dragonborn (base) – grupo `race_dragonborn`
  - Sem efeito fixo; usa variações elementais abaixo

- Dragonborn Fogo – grupo `race_dragonborn_fire`
  - Resistência a Fogo; Sopro de Fogo (cone curto, incendeia)
  - Fontes: `raceseffects/src/main/resources/config.yml:142` (tag `resist_fire`),
    `plugins/Skript/scripts/breath_cloud.sk`

- Dragonborn Gelo – grupo `race_dragonborn_cold`
  - Sopro de Gelo; imunidade a congelamento na Powder Snow (zera `TicksFrozen` e cancela dano de freeze)
  - Fonte: `plugins/Skript/scripts/breath_cloud.sk`, `plugins/Skript/scripts/powder_snow_immunity.sk`

- Dragonborn Raio – grupo `race_dragonborn_lightning`
  - Sopro de Raio; janela de imunidade a dano de relâmpago logo após um raio cair perto (2s)
  - Fonte: `plugins/Skript/scripts/breath_cloud.sk`, `plugins/Skript/scripts/lightning_immunity.sk`

- Dragonborn Ácido – grupo `race_dragonborn_acid`
  - Sopro de Ácido; limpeza periódica do efeito Wither (imunidade prática)
  - Fonte: `plugins/Skript/scripts/breath_cloud.sk`, `plugins/Skript/scripts/acid_immunity.sk`

- Dragonborn Venenoso – grupo `race_dragonborn_poison`
  - Sopro Venenoso; integrações por tag `db_poison`
  - Fonte: `plugins/Skript/scripts/breath_cloud.sk`

## Tags e efeitos simples que se aplicam em várias raças

- `race_darkvision` → Visão Noturna (300 ticks, sem partículas/ícone)
- `race_dolphins_grace` → Dolphins Grace (300 ticks, sem partículas/ícone)
- `resist_fire` → Fire Resistance (300 ticks)
- `trait_speed_woodelf` → +0.02 em `GENERIC_MOVEMENT_SPEED`
- `trait_hp_hilldwarf` → +2.0 em vida máxima (1 coração)
- `trait_poison_resist` → Resistência a Veneno
- `trait_relentless` → “Relentless Endurance” com cooldown
- Fonte geral: `raceseffects/src/main/resources/config.yml`

## Itens raciais úteis

- Teia de Drow – teleporte com cargas e recarga (ver Drow acima)
- Pena do Meio‑Elfo; Broche do Elfo da Floresta – entregues/reatribuidos automaticamente pelo script de itens
  - Fonte: `plugins/Skript/scripts/class_items_new_fixed.sk`

## Dicas de administração

- Recarregar apenas os Skripts: `/skript reload <arquivo>`
- Recarregar todos: `/skript reload all`
- Recarregar o plugin Java das raças: `/raceseffects reload`
- Conferir efeitos ativos: `/effect list <nick>`
