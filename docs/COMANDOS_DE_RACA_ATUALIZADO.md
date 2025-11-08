# Comandos de Raça (Itens de Aplicação)

Este guia lista os comandos de admin para entregar itens que aplicam raças aos jogadores. Requer permissão `raceseffects.admin`.

- Sintaxe geral
  - `/raceitem give <jogador> <tipo>`
  - Pode ser executado no console do servidor (com ou sem barra)
  - Se o comando não aparecer, recarregue o Skript: `skript reload race_items.sk`

- Tipos disponíveis
  - Alto Elfo: `highelf` | `elf_high` | `alto`
  - Meio-Elfo: `halfelf` | `half_elf` | `meioelfo` | `meio-elfo`
  - Elfo da Floresta: `elfwood` | `elf_wood` | `floresta`
  - Drow: `drow` | `elf_drow` | `darkelf`
  - Meio-Orc: `halforc` | `half_orc` | `meioorc` | `meio-orc`
  - Tiefling: `tiefling`
  - Humano: `human` | `humano`
  - Gnomo: `gnome` | `gnomo`
  - Gnomo da Floresta: `gnome_forest` | `gnomo_floresta`
  - Gnomo das Rochas: `gnome_rock` | `gnomo_rochas`
  - Halfling Pés‑Leves: `halfling_lightfoot` | `lightfoot` | `pes-leves`
  - Halfling Robusto: `halfling_stout` | `stout` | `robusto`

- Observações
  - Os itens são consumidos ao usar e aplicam as tags/efeitos imediatamente.
  - Para que efeitos passivos funcionem conforme grupos, use `/raceseffects reload` após editar YAML.
  - Para remover estados antigos (tags), use `/raceseffects reset <player>` antes de aplicar uma nova raça, se necessário.

## Elfo da Floresta

- Comando: `/raceitem give <jogador> elfwood` (aliases: `elf_wood`, `floresta`)
- Efeitos: `race_darkvision` (demais efeitos conforme RacesEffects)
- Habilidade: camuflagem nas florestas (invisibilidade ao ficar parado ~30s ao lado de tronco com folhas; renova enquanto nas condições)
- Item entregue: `Broche da Floresta` (MythicMobs: `ElfWoodBrooch`)

## Drow

- Comando: `/raceitem give <jogador> drow` (aliases: `elf_drow`, `darkelf`)
- Efeitos: `race_darkvision`
- Maldição: Fraqueza I durante o dia sob céu aberto (não conta luz de tochas/fogueiras)
- Item entregue: `Teia de Drow` (MythicMobs: `DrowWeb`)
- Uso do item: botão direito ativa teleporte aleatório para posição vazia próxima (raio ~7). O item possui 3 cargas e, ao esgotar, entra em recarga total de 4 minutos para recuperar todas as cargas.
- LuckPerms: `lp user <nick> parent set race_elf_drow`

## Anões

- Comandos:
  - Base: `/raceitem give <jogador> dwarf` (aliases: `anao`)
  - Colina: `/raceitem give <jogador> dwarf_hill` (aliases: `anao_colina`)
  - Montanha: `/raceitem give <jogador> dwarf_mountain` (aliases: `anao_montanha`)
  - Profundezas: `/raceitem give <jogador> dwarf_deep` (aliases: `anao_profundezas`)

- Efeitos aplicados
  - Anão (base): `race_darkvision` + `trait_poison_resist`.
  - Anão da Colina: herda base + `trait_hp_hilldwarf` (+6.0 Vida Máx via RacesEffects).
  - Anão da Montanha: herda base + Força I passiva (via `trait_strength_mountain`).
  - Anão das Profundezas: herda base; passiva de 45% de chance de Cegueira 10s no atacante (via Skript `dwarf_deep_blind.sk`).

- Itens entregues
  - Todos os anões recebem: `Picareta Anão (Racial)` — MythicMobs `DwarfRacialPick` (IRON_PICKAXE com Eficiência I e Fortuna I; indestrutível).

- LuckPerms
  - Base: `lp user <nick> parent set race_dwarf`
  - Colina: `lp user <nick> parent set race_dwarf_hill`
  - Montanha: `lp user <nick> parent set race_dwarf_mountain`
  - Profundezas: `lp user <nick> parent set race_dwarf_deep`

## Gnomos

- Comandos:
  - Base: `/raceitem give <jogador> gnome` (aliases: `gnomo`)
  - Floresta: `/raceitem give <jogador> gnome_forest` (aliases: `gnomo_floresta`)
  - Rochas: `/raceitem give <jogador> gnome_rock` (aliases: `gnomo_rochas`)

- Efeitos aplicados
  - Gnomo (base): `race_darkvision`.
  - Gnomo da Floresta: Speed III quando ≤ 50% da Vida Máx; 35% de chance de Invisibilidade 10s ao sofrer dano estando ≤ 50%.
  - Gnomo das Rochas: ao causar dano, ganha Força II por alguns segundos e empurra o alvo (repulsão).

- Item entregue
  - Engrenagem Gnômica — MythicMobs `GnomeCog`.
  - Gera poções aleatórias: 1 por uso; 5 cargas; recarga de 7 minutos após zerar.

- LuckPerms
  - Base: `lp user <nick> parent set race_gnome`
  - Floresta: `lp user <nick> parent set race_gnome_forest`
  - Rochas: `lp user <nick> parent set race_gnome_rock`

## Halflings

- Comandos:
  - Pés‑Leves: `/raceitem give <jogador> halfling_lightfoot` (aliases: `lightfoot`, `pes-leves`)
  - Robusto: `/raceitem give <jogador> halfling_stout` (aliases: `stout`, `robusto`)

- Efeitos aplicados
  - Lightfoot (Pés‑Leves): Supersalto e Sorte II.
  - Stout (Robusto): Pressa I e Sorte II.

- Item entregue (ambos)
  - Trombeta Halfling — MythicMobs `HalflingHorn`.
  - Uso: paralisa até 5 alvos próximos por 7s (players e mobs). Cooldown de 2 minutos por jogador.

- LuckPerms
  - Pés‑Leves: `lp user <nick> parent set race_halfling_lightfoot`
  - Robusto: `lp user <nick> parent set race_halfling_stout`

## Dragonborn (Draconato)

- O Dragonborn é aplicado via `/raceitem give <jogador> <tipo>` (um item por subtipo). Ao clicar no livro recebido, o subtipo é aplicado e os itens/tags são entregues automaticamente.

- Tipos aceitos no comando:
  - `dragonborn_fire`
  - `dragonborn_cold`
  - `dragonborn_lightning`
  - `dragonborn_acid`
  - `dragonborn_poison`

- Exemplos (console):
  - `raceitem give Steve dragonborn_fire`
  - `raceitem give Steve dragonborn_cold`
  - `raceitem give Steve dragonborn_lightning`
  - `raceitem give Steve dragonborn_acid`
  - `raceitem give Steve dragonborn_poison`

- O que o livro faz ao clicar (padrão do servidor):
  - Define o grupo LuckPerms correspondente (`race_dragonborn_*`).
  - Reforça itens e tags via rotina interna (equivalente a `forcegiveitems <nick>`):
    - Tags de imunidade: `db_fire`, `db_cold`, `db_lightning`, `db_acid`, `db_poison`.
    - Entrega o item de “Sopro” do subtipo (ativa com clique; recarga 10s).
