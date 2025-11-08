# Comandos de Raça (Itens de Aplicação)

Este guia lista os comandos de admin para entregar itens que aplicam raças aos jogadores. Requer permissão `raceseffects.admin`.

- Sintaxe geral
  - `/raceitem give <jogador> <tipo>`
  - Pode ser executado no console do servidor (com ou sem barra)
    - Ex.: `raceitem give Steve highelf`
    - Ex.: `/raceitem give Steve tiefling`
  - Se o comando não aparecer, recarregue o Skript: `skript reload race_items.sk`

- Tipos disponíveis
  - Alto Elfo: `highelf` | `elf_high` | `alto`
    - Aplica: `race_darkvision` + retaliação passiva (Espinhos/Veneno/Náusea/Nada)
    - Comando: `/raceitem give <jogador> highelf`
  - Meio‑Elfo: `halfelf` | `half_elf` | `meioelfo` | `meio-elfo`
    - Aplica: `race_darkvision` + `race_dolphins_grace` (Graça Marinha permanente)
    - Entrega: `Pena do Meio‑Elfo` (usa para ganhar Queda Lenta por ~7s)
    - Comando: `/raceitem give <jogador> halfelf`
  - Elfo da Floresta: `elfwood` | `elf_wood` | `floresta`
    - Aplica: `race_darkvision` (efeitos adicionais/velocidade conforme RacesEffects)
    - Habilidade: camuflagem nas florestas (invisibilidade ao ficar parado ~30s ao lado de tronco com folhas; renova enquanto nas condições)
    - Entrega: `Broche da Floresta` (MythicMobs: `ElfWoodBrooch`)
    - Comando: `/raceitem give <jogador> elfwood`
  - Drow: `drow` | `elf_drow` | `darkelf`
    - Aplica: `race_darkvision`
    - Maldição: Fraqueza I durante o dia sob céu aberto (não ativa por tochas/fogueiras)
    - Entrega: `Teia de Drow` (MythicMobs: `DrowWeb`)
    - Uso do item: botão direito → teleporte aleatório (raio ~7); 3 cargas; recarga total 4 minutos
    - Comando: `/raceitem give <jogador> drow`
  - Meio‑Orc: `halforc` | `half_orc` | `meioorc` | `meio-orc`
    - Aplica: `race_darkvision` + `trait_relentless` (Relentless Endurance com cooldown)
    - Comando: `/raceitem give <jogador> halforc`
  - Tiefling: `tiefling`
    - Aplica: `race_darkvision` + `resist_fire`
    - Ativa: permissão virtual `group.race_tiefling` (Velocidade II no Nether via Skript)
    - Comando: `/raceitem give <jogador> tiefling`
  - Humano: `human` | `humano`
    - Aplica: permissões `group.race_human` + `raceseffects.abilities` (habilita `/abilities`); efeitos de regeneração conforme configuração/servidor
    - Comando: `/raceitem give <jogador> human`

- Observações
  - Os itens são consumidos ao usar e aplicam as tags/efeitos imediatamente.
  - Caso use a versão em Skript (sem novo JAR), basta recarregar: `skript reload race_items.sk` e `skript reload tiefling_soulspeed.sk`.
  - Para que efeitos passivos funcionem conforme grupos, use `/raceseffects reload` após editar YAML.
  - Para remover estados antigos (tags), use `/raceseffects reset <player>` antes de aplicar uma nova raça, se necessário.
  - Elfo da Floresta: o broche é reentregado no respawn; se o inventário estiver cheio, vai para o Ender Chest. Comandos de suporte: `/checkitems`, `/fixelfwood`.

## Elfo da Floresta

- Comando: `/raceitem give <jogador> elfwood` (aliases: `elf_wood`, `floresta`)
- Aplica: `race_darkvision` (demais efeitos conforme RacesEffects)
- Habilidade: camuflagem nas florestas (invisibilidade ao ficar parado ~30s ao lado de tronco com folhas; renova enquanto nas condições)
- Item entregue: `Broche da Floresta` (MythicMobs: `ElfWoodBrooch`)
- Observação: reentrega automática no respawn; se o inventário estiver cheio, o item vai para o Ender Chest. Comandos de suporte: `/checkitems`, `/fixelfwood`.

## Drow

- Comando: `/raceitem give <jogador> drow` (aliases: `elf_drow`, `darkelf`)
- Aplica: `race_darkvision`
- Maldição: Fraqueza I durante o dia sob céu aberto (não conta luz de tochas/fogueiras)
- Item entregue: `Teia de Drow` (MythicMobs: `DrowWeb`)
- Uso do item: botão direito ativa teleporte aleatório para posição vazia próxima (raio ~7). O item possui 3 cargas e, ao esgotar, entra em recarga total de 4 minutos para recuperar todas as cargas.
- LuckPerms: `lp user <nick> parent set race_elf_drow`
- Observação: o item é protegido (não pode ser colocado/droppado) e é reentregado no respawn se ausente.

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
  - Todos os anoes recebem: `Picareta Anao &8(Racial)` — MythicMobs `DwarfRacialPick` (IRON_PICKAXE com Eficiencia I e Fortuna I; indestrutivel).

- LuckPerms
  - Base: `lp user <nick> parent set race_dwarf`
  - Colina: `lp user <nick> parent set race_dwarf_hill`
  - Montanha: `lp user <nick> parent set race_dwarf_mountain`
  - Profundezas: `lp user <nick> parent set race_dwarf_deep`

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
    - Entrega o item de “Sopro …” do subtipo (ativa com clique; recarga 10s).

- Resumo dos sopros
  - Fire: cone de chamas à frente; ignora entidades com `db_fire`.
  - Cold: área 5x5 com Slowness; `db_cold` imune (e imune a congelamento em neve fofa).
  - Lightning: linha com 7 relâmpagos; o subtipo é imune ao dano de raio próximo ao impacto.
  - Acid: chuva 5x5 por ~6s, 1 de dano/seg (Ruptura simulada); `db_acid` imune.
  - Poison: área com Poison; `db_poison` imune.
