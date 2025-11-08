# Guia Unificado — Raças, Classes e Comandos

Este documento consolida o essencial (comandos, efeitos e itens) para todas as raças e classes ativas do projeto. Use-o no lugar dos vários READMEs antigos.

## Requisitos e Notas
- Plugins: LuckPerms, Skript, MythicMobs, RacesEffects (onde indicado).
- Itens raciais e de classe são protegidos (vinculados) e reentregues no respawn conforme a raça/classe do jogador.
- Exclusividade: quem já tem uma raça só pode usar itens da própria raça. Ao aplicar uma nova raça por livro, todo o conteúdo da raça anterior é limpo (grupos, tags, variáveis e itens).

## Comandos de Admin (raças)
- Sintaxe: `/raceitem give <jogador> <tipo>` (pode usar no console sem barra)
- Recarregar Skript (se precisar): `skript reload race_items.sk`

Tipos suportados (aliases entre crases):
- Alto Elfo: `highelf` | `elf_high` | `alto`
- Elfo da Floresta: `elfwood` | `elf_wood` | `floresta`
- Drow: `drow` | `elf_drow` | `darkelf`
- Meio‑Elfo: `halfelf` | `half_elf` | `meioelfo` | `meio-elfo`
- Meio‑Orc: `halforc` | `half_orc` | `meioorc` | `meio-orc`
- Tiefling: `tiefling`
- Humano: `human` | `humano`
- Anao (base): `dwarf` | `anao`
- Anao da Colina: `dwarf_hill` | `anao_colina`
- Anao da Montanha: `dwarf_mountain` | `anao_montanha`
 - Anao das Profundezas: `dwarf_deep` | `anao_profundezas`
- Dragonborn (Draconato): `dragonborn_fire` | `dragonborn_cold` | `dragonborn_lightning` | `dragonborn_acid` | `dragonborn_poison`

Ao clicar o livro:
- Remove raça antiga (LuckPerms groups), tags relacionadas e itens raciais antigos.
- Define o grupo da nova raça e entrega o item racial adequado.

## Raças

### Alto Elfo
- Aplicar: `/raceitem give <jogador> highelf`
- Efeitos: `race_darkvision` (ou conforme RacesEffects). Retaliação passiva configurável.
- Item: Amuleto Alto Elfo (ornamento)

### Elfo da Floresta
- Aplicar: `/raceitem give <jogador> elfwood`
- Efeitos: `race_darkvision` (+ ajustes no RacesEffects se desejar).
- Habilidades:
  - Camuflagem periódica: invisibilidade ao ficar parado ~30s ao lado de tronco com folhas.
  - Broche da Floresta (botão direito): invisibilidade 10s, recarga 3min.
  - Proteção contra alvo: mobs não adquirem alvo enquanto a invisibilidade estiver ativa.
- Item (MythicMobs): `ElfWoodBrooch`.

### Drow
- Aplicar: `/raceitem give <jogador> drow`
- Efeitos: `race_darkvision`.
- Maldição: Fraqueza I durante o dia e a céu aberto (não ativa por tochas/fogueiras).
- Item (botão direito): Teia de Drow — teleporte aleatório (raio ~7), 3 cargas; recarga total 4min ao zerar.
- Item (MythicMobs): `DrowWeb`.

### Meio‑Elfo
- Aplicar: `/raceitem give <jogador> halfelf`
- Efeitos: `race_darkvision` + `race_dolphins_grace`.
- Item: Pena do Meio‑Elfo — Queda Lenta 7s (recarga 3min). MM item: `HalfElfFeather`.

### Meio‑Orc
- Aplicar: `/raceitem give <jogador> halforc`
- Efeitos: `race_darkvision` + `trait_relentless` (endurance com cooldown).
- Itens (MM): `HalfOrcTusk`, `HalfOrcBerserkCharm`.

### Tiefling
- Aplicar: `/raceitem give <jogador> tiefling`
- Efeitos: `race_darkvision` + `resist_fire`.
- Item (MM): `TieflingSigil`.

### Humano
- Aplicar: `/raceitem give <jogador> human`
- Efeitos: habilita `/abilities` e ajustes de regeneração conforme configuração.
- Item (MM): `HumanMedal`.

### Anaos
- Anao (base)
  - Aplicar: `/raceitem give <jogador> dwarf` (aliases: `anao`)
  - Efeitos: `race_darkvision` + `trait_poison_resist`
  - Item (MM): `DwarfRacialPick` — IRON_PICKAXE com Eficiencia I e Fortuna I
- Anao da Colina
  - Aplicar: `/raceitem give <jogador> dwarf_hill` (aliases: `anao_colina`)
  - Efeitos: herda efeitos do Anao base + `trait_hp_hilldwarf` (+6.0 Vida Max via RacesEffects)
  - Item (MM): `DwarfRacialPick`
- Anao da Montanha
  - Aplicar: `/raceitem give <jogador> dwarf_mountain` (aliases: `anao_montanha`)
  - Efeitos: herda efeitos do Anao base (`race_darkvision` + `trait_poison_resist`)
  - Item (MM): `DwarfRacialPick`
 - Anao das Profundezas
  - Aplicar: `/raceitem give <jogador> dwarf_deep` (aliases: `anao_profundezas`)
  - Efeitos: herda efeitos do Anao base; 45% de chance de aplicar Cegueira no atacante (mobs/jogadores) ao sofrer dano
  - Implementação: via Skript `dwarf_deep_blind.sk` (usa permissão `group.race_dwarf_deep`)
  - Item (MM): `DwarfRacialPick`

### Dragonborn (Draconato)
- Aplicar por subtipo: `dragonborn_fire|cold|lightning|acid|poison`
- Ao clicar: define grupo LP do subtipo, entrega item/escama e tags (imunidades por subtipo). Alias de sopro disponível conforme setup.

## Classes (itens principais)
Itens principais são entregues automaticamente via rotina (MythicMobs). Exemplos:

### Bárbaro
- Itens (MM): `BarbarianRageCharm` (Fúria 120s), `BarbarianRecklessCharm` (Ataque Temerário), Caminhos Totem (`TotemBearCharm`, `TotemEagleCharm`, `TotemWolfCharm`).

### Bardo
- Itens (MM): `BardLute` (Inspiração), `BardLoreBook` (Canção de Descanso), `BardValorMedal` (subclasse Valor).

### Bruxo (Warlock)
- Itens (MM): `EldritchWand`, `HexTalisman`, e marcas de patrono (`FeyCharm`, `FiendMark`, `EldritchMark`).

## Regras de Exclusividade e Troca de Raça
- Exclusividade de item: itens raciais só funcionam para a raça atual do jogador.
- Troca por livro:
  - Remove grupos de raças anteriores (LuckPerms).
  - Remove tags associadas e variáveis/cooldowns.
  - Remove itens raciais antigos do inventário.
  - Aplica a nova raça e entrega seu item racial.

## Dicas de Teste Rápido
- Dia limpo congelado: `time set noon`, `weather clear 600000`, `gamerule doDaylightCycle false`, `gamerule doWeatherCycle false`.
- Restaurar ciclos: `gamerule doDaylightCycle true`, `gamerule doWeatherCycle true`.

## Problemas conhecidos (avisos do Skript)
- `any of {@logs}` / `any of {@leaves}` pode gerar aviso em algumas versões; é inofensivo.
- Condições como `if {@lava_vision_enable} is true:` sempre verdade — não é erro.

---
Caso queira, posso substituir o arquivo `docs/README.md` por este (e remover os outros READMEs obsoletos) para ficar só um documento. Se preferir, mantenho os antigos como referência.
