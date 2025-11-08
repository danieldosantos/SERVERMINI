# Comandos de RaÃ§a (Itens de AplicaÃ§Ã£o)

Este guia lista os comandos de admin para entregar itens que aplicam raÃ§as aos jogadores. Requer permissÃ£o `raceseffects.admin`.

- Sintaxe geral
  - `/raceitem give <jogador> <tipo>`
  - Pode ser executado no console do servidor (com ou sem barra)
    - Ex.: `raceitem give Steve highelf`
    - Ex.: `/raceitem give Steve tiefling`
  - Se o comando nÃ£o aparecer, recarregue o Skript: `skript reload race_items.sk`

- Tipos disponÃ­veis
  - Alto Elfo: `highelf` | `elf_high` | `alto`
    - Aplica: `race_darkvision` + retaliaÃ§Ã£o passiva (Espinhos/Veneno/NÃ¡usea/Nada)
    - Comando: `/raceitem give <jogador> highelf`
  - Meioâ€‘Elfo: `halfelf` | `half_elf` | `meioelfo` | `meio-elfo`
    - Aplica: `race_darkvision` + `race_dolphins_grace` (GraÃ§a Marinha permanente)
    - Entrega: `Pena do Meioâ€‘Elfo` (usa para ganhar Queda Lenta por ~7s)
    - Comando: `/raceitem give <jogador> halfelf`
  - Elfo da Floresta: `elfwood` | `elf_wood` | `floresta`
    - Aplica: `race_darkvision` (efeitos adicionais/velocidade conforme RacesEffects)
    - Habilidade: camuflagem nas florestas (invisibilidade ao ficar parado ~30s ao lado de tronco com folhas; renova enquanto nas condiÃ§Ãµes)
    - Entrega: `Broche da Floresta` (MythicMobs: `ElfWoodBrooch`)
    - Comando: `/raceitem give <jogador> elfwood`
  - Drow: `drow` | `elf_drow` | `darkelf`
    - Aplica: `race_darkvision`
    - MaldiÃ§Ã£o: Fraqueza I durante o dia sob cÃ©u aberto (nÃ£o ativa por tochas/fogueiras)
    - Entrega: `Teia de Drow` (MythicMobs: `DrowWeb`)
    - Uso do item: botÃ£o direito â†’ teleporte aleatÃ³rio (raio ~7); 3 cargas; recarga total 4 minutos
    - Comando: `/raceitem give <jogador> drow`
  - Meioâ€‘Orc: `halforc` | `half_orc` | `meioorc` | `meio-orc`
    - Aplica: `race_darkvision` + `trait_relentless` (Relentless Endurance com cooldown)
    - Comando: `/raceitem give <jogador> halforc`
  - Tiefling: `tiefling`
    - Aplica: `race_darkvision` + `resist_fire`
    - Ativa: permissÃ£o virtual `group.race_tiefling` (Velocidade II no Nether via Skript)
    - Comando: `/raceitem give <jogador> tiefling`
  - Humano: `human` | `humano`
    - Aplica: permissÃµes `group.race_human` + `raceseffects.abilities` (habilita `/abilities`); efeitos de regeneraÃ§Ã£o conforme configuraÃ§Ã£o/servidor
    - Comando: `/raceitem give <jogador> human`

- ObservaÃ§Ãµes
  - Os itens sÃ£o consumidos ao usar e aplicam as tags/efeitos imediatamente.
  - Caso use a versÃ£o em Skript (sem novo JAR), basta recarregar: `skript reload race_items.sk` e `skript reload tiefling_soulspeed.sk`.
  - Para que efeitos passivos funcionem conforme grupos, use `/raceseffects reload` apÃ³s editar YAML.
  - Para remover estados antigos (tags), use `/raceseffects reset <player>` antes de aplicar uma nova raÃ§a, se necessÃ¡rio.
  - Elfo da Floresta: o broche Ã© reentregado no respawn; se o inventÃ¡rio estiver cheio, vai para o Ender Chest. Comandos de suporte: `/checkitems`, `/fixelfwood`.

## Elfo da Floresta

- Comando: `/raceitem give <jogador> elfwood` (aliases: `elf_wood`, `floresta`)
- Aplica: `race_darkvision` (demais efeitos conforme RacesEffects)
- Habilidade: camuflagem nas florestas (invisibilidade ao ficar parado ~30s ao lado de tronco com folhas; renova enquanto nas condiÃ§Ãµes)
- Item entregue: `Broche da Floresta` (MythicMobs: `ElfWoodBrooch`)
- ObservaÃ§Ã£o: reentrega automÃ¡tica no respawn; se o inventÃ¡rio estiver cheio, o item vai para o Ender Chest. Comandos de suporte: `/checkitems`, `/fixelfwood`.

## Drow

- Comando: `/raceitem give <jogador> drow` (aliases: `elf_drow`, `darkelf`)
- Aplica: `race_darkvision`
- MaldiÃ§Ã£o: Fraqueza I durante o dia sob cÃ©u aberto (nÃ£o conta luz de tochas/fogueiras)
- Item entregue: `Teia de Drow` (MythicMobs: `DrowWeb`)
- Uso do item: botÃ£o direito ativa teleporte aleatÃ³rio para posiÃ§Ã£o vazia prÃ³xima (raio ~7). O item possui 3 cargas e, ao esgotar, entra em recarga total de 4 minutos para recuperar todas as cargas.
- LuckPerms: `lp user <nick> parent set race_elf_drow`
- ObservaÃ§Ã£o: o item Ã© protegido (nÃ£o pode ser colocado/droppado) e Ã© reentregado no respawn se ausente.

## Anaos

- Comandos:
  - Base: `/raceitem give <jogador> dwarf` (aliases: `anao`)
  - Colina: `/raceitem give <jogador> dwarf_hill` (aliases: `anao_colina`)
  - Montanha: `/raceitem give <jogador> dwarf_mountain` (aliases: `anao_montanha`)

- Efeitos aplicados
  - Anao (base): `race_darkvision` + `trait_poison_resist`.
  - Anao da Colina: efeitos do Anao base + `trait_hp_hilldwarf` (+6.0 Vida Max no RacesEffects).
  - Anao da Montanha: efeitos do Anao base (herda `race_darkvision` + `trait_poison_resist`).

- Itens entregues
  - Todos os anoes recebem: `Picareta Anao &8(Racial)` â€” MythicMobs `DwarfRacialPick` (IRON_PICKAXE com Eficiencia I e Fortuna I; indestrutivel).

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
  - Gnomo da Floresta: Velocidade III quando abaixo de 50% da Vida Maxima e 35% de chance de Invisibilidade por 10s ao sofrer dano.
  - Gnomo das Rochas: Forca II e Repulsao II aplicadas ao causar dano em mobs ou jogadores.

- Item entregue
  - Engrenagem Gnomica â€” MythicMobs `GnomeCog`.
  - Gera poções aleatórias: 1 por uso; 5 cargas; recarga de 7 minutos após zerar.

- LuckPerms
  - Base: `lp user <nick> parent set race_gnome`
  - Floresta: `lp user <nick> parent set race_gnome_forest`
  - Rochas: `lp user <nick> parent set race_gnome_rock`

## Halflings

- Comandos:
  - Pés‑Leves`)
  - Robusto: `/raceitem give <jogador> halfling_stout` (aliases: `stout`, `robusto`)

- Efeitos aplicados
  - Lightfoot (Pés‑Leves): Supersalto e Sorte II.
  - Stout (Robusto): Pressa I e Sorte II.

- Item entregue (ambos)
  - Trombeta Halfling — MythicMobs `HalflingHorn`.
  - Uso: paralisa até 5 alvos próximos

- LuckPerms
  - Pés‑Leves: `lp user <nick> parent set race_halfling_lightfoot`
  - Robusto: `lp user <nick> parent set race_halfling_stout`

## Dragonborn (Draconato)

- O Dragonborn Ã© aplicado via `/raceitem give <jogador> <tipo>` (um item por subtipo). Ao clicar no livro recebido, o subtipo Ã© aplicado e os itens/tags sÃ£o entregues automaticamente.

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

- O que o livro faz ao clicar (padrÃ£o do servidor):
  - Define o grupo LuckPerms correspondente (`race_dragonborn_*`).
  - ReforÃ§a itens e tags via rotina interna (equivalente a `forcegiveitems <nick>`):
    - Tags de imunidade: `db_fire`, `db_cold`, `db_lightning`, `db_acid`, `db_poison`.
    - Entrega o item de â€œSopro â€¦â€ do subtipo (ativa com clique; recarga 10s).

- Resumo dos sopros
  - Fire: cone de chamas Ã  frente; ignora entidades com `db_fire`.
  - Cold: Ã¡rea 5x5 com Slowness; `db_cold` imune (e imune a congelamento em neve fofa).
  - Lightning: linha com 7 relÃ¢mpagos; o subtipo Ã© imune ao dano de raio prÃ³ximo ao impacto.
  - Acid: chuva 5x5 por ~6s, 1 de dano/seg (Ruptura simulada); `db_acid` imune.
  - Poison: Ã¡rea com Poison; `db_poison` imune.



