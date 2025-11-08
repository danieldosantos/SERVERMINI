# Guia Unificado de Raças

Este documento consolida todos os materiais anteriores sobre raças, características, itens e integrações de scripts do servidor. Utilize-o como referência única para configuração, administração e comunicação com a equipe.

## Procedimentos Gerais
- Plugins envolvidos: LuckPerms, RacesEffects, ProSkillAPI, Skript, MythicMobs (itens e habilidades), datapack `races_effects`.
- Aplicação via item: `/raceitem give <jogador> <tipo>` (executável no console sem barra). Recarregue o Skript se necessário: `skript reload race_items.sk`.
- Aplicação direta via LuckPerms: `lp user <nick> parent set race_<id>`.
- Sempre remova a raça anterior antes de definir uma nova (ou use `parent set` para sobrescrever). Se houver efeitos residuais, execute `/raceseffects reset <jogador>`.
- Após editar YAML ou scripts, use `/raceseffects reload` e, quando aplicável, `/skript reload <arquivo>`.

## Estrutura por Raça
Cada raça possui três blocos: **Características Raciais** (efeitos contínuos), **Itens Raciais** (entregues ou vinculados), e **Observações** (scripts, subgrupos e notas operacionais).

### Elfos

#### Elfo (base)
- **Características Raciais**
  - Visão no Escuro discreta (Darkvision) mantida via tag `race_darkvision`.
- **Itens Raciais**
  - Sem item exclusivo (base de herança para sub-raças).
- **Observações**
  - Grupo LuckPerms: `race_elf`.
  - Fonte: `raceseffects/src/main/resources/config.yml`.

#### Alto Elfo
- **Características Raciais**
  - Darkvision.
  - Retaliação ao sofrer dano: 35 % sem efeito, 25 % Espinhos (2.0 de dano), 25 % Veneno I por 5 s, 15 % Náusea I por 5 s.
- **Itens Raciais**
  - Amuleto Alto Elfo (ornamento, vinculado à raça).
- **Observações**
  - Grupo: `race_elf_high`.
  - Scripts: `plugins/Skript/scripts/elf_high_retaliate.sk`.

#### Elfo da Floresta
- **Características Raciais**
  - Darkvision.
  - +0,02 em `generic.movement_speed` (tag `trait_speed_woodelf`).
  - Camuflagem: ficar imóvel 30 s próximo a tronco com folhas concede Invisibilidade por 6 s e renova enquanto a condição persistir.
- **Itens Raciais**
  - Broche da Floresta (`ElfWoodBrooch`): clique direito concede Invisibilidade por 10 s; recarga 3 min.
- **Observações**
  - Grupo: `race_elf_wood`.
  - Scripts: `plugins/Skript/scripts/elf_wood_hide.sk`, `class_items_new_fixed.sk`.

#### Drow
- **Características Raciais**
  - Darkvision.
  - Fraqueza I reaplicada durante o dia a céu aberto (Y>0); removida em profundidade.
  - Em Y ≤ −39 no Overworld, 45 % de chance de causar Poison I (6 s) + Wither I (3 s) ao atingir um alvo.
- **Itens Raciais**
  - Teia de Drow (`DrowWeb`): teleporte aleatório (~7 blocos), 3 cargas com recarga total de 4 min.
- **Observações**
  - Grupo: `race_elf_drow`.
  - Scripts: `plugins/Skript/scripts/race_drow.sk`, `drow_depth_poison.sk`, `class_items_new_fixed.sk`, `zzz_drow_rightclick_fix.sk`.

### Anões

#### Anão (base)
- **Características Raciais**
  - Darkvision.
  - Resistência a Veneno (trait `trait_poison_resist`).
- **Itens Raciais**
  - Sem item dedicado; utiliza itens específicos conforme sub-raça, se configurado.
- **Observações**
  - Grupo: `race_dwarf`.
  - Fonte: `raceseffects/src/main/resources/config.yml`.

#### Anão da Colina
- **Características Raciais**
  - Darkvision.
  - Resistência a Veneno.
  - +2.0 pontos de vida máxima (1 coração) pela trait `trait_hp_hilldwarf`.
- **Itens Raciais**
  - Sem item exclusivo definido.
- **Observações**
  - Grupo: `race_dwarf_hill`.

#### Anão da Montanha
- **Características Raciais**
  - Darkvision.
- **Itens Raciais**
  - Sem item exclusivo definido.
- **Observações**
  - Grupo: `race_dwarf_mountain`.

### Gnomos

#### Gnomo (base)
- **Características Raciais**
  - Darkvision.
- **Itens Raciais**
  - Não há itens vinculados na configuração atual.
- **Observações**
  - Grupo: `race_gnome`.

#### Gnomo da Floresta
- **Características Raciais**
  - Darkvision.
- **Itens Raciais**
  - Não há itens vinculados.
- **Observações**
  - Grupo: `race_gnome_forest`.

#### Gnomo das Rochas
- **Características Raciais**
  - Darkvision.
- **Itens Raciais**
  - Não há itens vinculados.
- **Observações**
  - Grupo: `race_gnome_rock`.

### Halflings

#### Halfling (base)
- **Características Raciais**
  - Sem efeitos ativos definidos.
- **Itens Raciais**
  - Não há itens vinculados.
- **Observações**
  - Grupo: `race_halfling`.

#### Halfling Robusto (Stout)
- **Características Raciais**
  - Resistência a Veneno (trait `trait_poison_resist`).
- **Itens Raciais**
  - Não há itens vinculados.
- **Observações**
  - Grupo: `race_halfling_stout`.

#### Halfling Pés-Leves (Lightfoot)
- **Características Raciais**
  - Sem efeitos adicionais configurados.
- **Itens Raciais**
  - Não há itens vinculados.
- **Observações**
  - Grupo: `race_halfling_lightfoot`.

### Meio-Sangues

#### Meio-Elfo
- **Características Raciais**
  - Darkvision.
  - `Dolphin's Grace` recorrente para mobilidade aquática.
- **Itens Raciais**
  - Pena do Meio-Elfo (`HalfElfFeather`): concede Queda Lenta por ~7 s; recarga 3 min.
- **Observações**
  - Grupo: `race_half_elf`.
  - Script: `class_items_new_fixed.sk`.

#### Meio-Orc
- **Características Raciais**
  - Darkvision.
  - `Relentless Endurance`: evita morte uma vez dentro do cooldown.
- **Itens Raciais**
  - Presa do Meio-Orc (`HalfOrcTusk`) e Talismã Berserker (`HalfOrcBerserkCharm`).
- **Observações**
  - Grupo: `race_half_orc`.
  - Trait configurada em `raceseffects/src/main/resources/config.yml`.

### Humanos
- **Características Raciais**
  - Regeneração condicional: quando a vida cai a ≤50 %, aplica Regeneration II por 15 s e renova enquanto permanecer abaixo do limiar.
  - Acesso ao comando `/abilities` (atributos ProSkillAPI).
- **Itens Raciais**
  - Medalha Humana (`HumanMedal`).
- **Observações**
  - Grupo: `race_human`.
  - Script principal: `plugins/Skript/scripts/human_regen.sk`.

### Tiefling
- **Características Raciais**
  - Darkvision.
  - Resistência a Fogo permanente (`resist_fire`).
  - Velocidade II enquanto estiver no Nether.
- **Itens Raciais**
  - Selo Tiefling (`TieflingSigil`).
- **Observações**
  - Grupo: `race_tiefling`.
  - Scripts: `tiefling_soulspeed.sk`, `class_items_new_fixed.sk`.

### Dragonborn (Draconato)
- **Características Raciais Comuns**
  - Tags elementais específicas por subtipo.
  - Sopro elemental com recarga curta (implementado via `plugins/Skript/scripts/breath_cloud.sk`).
- **Itens Raciais**
  - Escama/Sopro do subtipo correspondente (item de clique).
- **Observações Gerais**
  - Livro aplicado define o grupo `race_dragonborn_<elemento>` e reatribui itens via rotina (similar a `forcegiveitems`).

#### Dragonborn do Fogo
- **Características Raciais**
  - Resistência a Fogo.
  - Sopro em cone incendiário.
- **Itens Raciais**
  - Item de Sopro de Fogo.
- **Observações**
  - Tag `db_fire` para imunidades específicas.

#### Dragonborn do Frio
- **Características Raciais**
  - Sopro de gelo em área 5×5 aplicando Lentidão.
  - Imunidade a congelamento em Powder Snow (`powder_snow_immunity.sk`).
- **Itens Raciais**
  - Item de Sopro de Gelo.
- **Observações**
  - Tag `db_cold` remove congelamento.

#### Dragonborn do Relâmpago
- **Características Raciais**
  - Sopro linear com raios.
  - Imunidade a dano de raio por 2 s após impacto próximo.
- **Itens Raciais**
  - Item de Sopro de Relâmpago.
- **Observações**
  - Tag `db_lightning` identifica o subtipo.

#### Dragonborn do Ácido
- **Características Raciais**
  - Sopro que cria chuva ácida (dano por 6 s).
  - Limpeza periódica do efeito Wither.
- **Itens Raciais**
  - Item de Sopro de Ácido.
- **Observações**
  - Tag `db_acid` evita dano corrosivo.

#### Dragonborn Venenoso
- **Características Raciais**
  - Sopro venenoso em área.
- **Itens Raciais**
  - Item de Sopro de Veneno.
- **Observações**
  - Tag `db_poison` integra com outras mecânicas antiveneno.

## Tags e Efeitos Compartilhados
- `race_darkvision`: Night Vision 300 ticks, sem partículas.
- `race_dolphins_grace`: Dolphin's Grace 300 ticks.
- `resist_fire`: Fire Resistance 300 ticks.
- `trait_speed_woodelf`: +0,02 velocidade de movimento.
- `trait_hp_hilldwarf`: +2,0 vida máxima.
- `trait_poison_resist`: resistência a Veneno.
- `trait_relentless`: implementação de Relentless Endurance.

## Itens e Rotinas de Manutenção
- Itens raciais são protegidos e reentregues no respawn; se o inventário estiver cheio, são enviados ao Ender Chest.
- Scripts principais: `plugins/Skript/scripts/class_items_new_fixed.sk` (geral), `race_items.sk` (livros de aplicação).
- Para testes rápidos de dia limpo: `time set noon`, `weather clear 600000`, `gamerule doDaylightCycle false`, `gamerule doWeatherCycle false`.

