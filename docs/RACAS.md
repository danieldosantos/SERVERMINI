# Raças (Cheat‑Sheet Completo)

Após editar o YAML: `/raceseffects reload`
Para ler `commands.yml` (aliases), reinicie o servidor.

Notas gerais
- As raças são aplicadas por grupos do LuckPerms mapeados em `plugins/RacesEffects/config.yml` (apply.groups).
- Recomendações: `hardNoRaceMode: false` e `enforceGroupTags: true` em `plugins/RacesEffects/config.yml`.
- Verifique tags ativas: `tag <nick> list` (ou `scoreboard players tag <nick> list`).

## Elfos
- Elfo (race_elf)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_elf`
- Alto Elfo (race_elf_high)
  - Efeitos: Darkvision + Sorte I (bônus moderado de “Inteligência”)
  - LuckPerms: `lp user <nick> parent set race_elf_high`
- Elfo da Floresta (race_elf_wood)
  - Efeitos: Darkvision + Velocidade +0.02 (atributo)
  - LuckPerms: `lp user <nick> parent set race_elf_wood`
- Drow (race_elf_drow)
  - Efeitos: Darkvision
  - Aliases opcionais: `/drowdarkness` (Darkness 10s) e `/drowsun` (Weakness 10s)
  - Permissões (aliases): `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.selector`
  - LuckPerms: `lp user <nick> parent set race_elf_drow`

## Anões
- Anão (race_dwarf)
  - Efeitos: Resistência a veneno (−50% poison)
  - LuckPerms: `lp user <nick> parent set race_dwarf`
- Anão da Colina (race_dwarf_hill)
  - Efeitos: +2.0 MAX_HEALTH (+1 coração)
  - LuckPerms: `lp user <nick> parent set race_dwarf_hill`
- Anão da Montanha (race_dwarf_mountain)
  - Efeitos: Força I
  - LuckPerms: `lp user <nick> parent set race_dwarf_mountain`

## Gnomos
- Gnomo (race_gnome)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_gnome`
- Gnomo da Floresta (race_gnome_forest)
  - Efeitos: Pressa I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_forest`
- Gnomo das Rochas (race_gnome_rock)
  - Efeitos: Absorção I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_rock`

## Halflings
- Lightfoot (race_halfling_lightfoot)
  - Efeitos: Supersalto I (curto)
  - LuckPerms: `lp user <nick> parent set race_halfling_lightfoot`
- Stout / Robusto (race_halfling_stout)
  - Efeitos: Saturação (curta)
  - LuckPerms: `lp user <nick> parent set race_halfling_stout`

## Humanos
- Humano (race_human)
  - Efeitos: Regeneração II (contínua) + bônus moderado em todos os atributos
  - Como aplicar os atributos: `/humanasi` (aplica +1 em STR, DEX, CON, INT, WIS, CHA)
  - LuckPerms: `lp user <nick> parent set race_human`

## Dragonborn (Draconato)
- Base (race_dragonborn)
  - Efeitos: +1.0 MAX_HEALTH (Vida extra Nível 1)
  - LuckPerms: `lp user <nick> parent set race_dragonborn`
- Fire (race_dragonborn_fire)
  - Efeitos: Sopro de fogo via alias
  - Comando: `/breath` lança `small_fireball`
  - LuckPerms: `lp user <nick> parent set race_dragonborn_fire`
- Cold (race_dragonborn_cold)
  - Efeitos: Sopro de frio via alias
  - Comando: `/breath` aplica Slowness em nuvem ao redor
  - LuckPerms: `lp user <nick> parent set race_dragonborn_cold`
- Lightning (race_dragonborn_lightning)
  - Efeitos: Sopro elétrico via alias
  - Comando: `/breath` invoca `lightning_bolt` à frente
  - LuckPerms: `lp user <nick> parent set race_dragonborn_lightning`
- Acid (race_dragonborn_acid)
  - Efeitos: Sopro ácido via alias
  - Comando: `/breath` cria nuvem com Dano Instantâneo
  - LuckPerms: `lp user <nick> parent set race_dragonborn_acid`
- Poison (race_dragonborn_poison)
  - Efeitos: Sopro venenoso via alias
  - Comando: `/breath` cria nuvem de Veneno
  - LuckPerms: `lp user <nick> parent set race_dragonborn_poison`
- Permissões para `/breath`: `minecraft.command.execute`, `minecraft.command.summon`, `minecraft.command.effect`, `minecraft.command.selector`

## Meio‑Elfo
- Meio‑Elfo (race_half_elf)
  - Efeitos: Darkvision + Queda Lenta I (curto) + Graça marinha (Dolphin's Grace) curta
  - LuckPerms: `lp user <nick> parent set race_half_elf`

## Meio‑Orc
- Meio‑Orc (race_half_orc)
  - Efeitos: Relentless Endurance
  - Alias opcional: `/berserk` → Strength I + Resistance I por 10s (recomendado uso consciente)
  - Permissões (aliases): `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.selector`
  - LuckPerms: `lp user <nick> parent set race_half_orc`

## Tiefling
- Tiefling (race_tiefling)
  - Efeitos: Darkvision + Resistência ao Fogo
  - LuckPerms: `lp user <nick> parent set race_tiefling`

## Referências rápidas (config)
- Efeitos simples: `apply.tags.*`
- Modificadores de atributo: `apply.attributeModifiers.*`
- Mapeamento de grupos → tags: `apply.groups`
# Raças (Cheat‑Sheet Completo)

Após editar o YAML: `/raceseffects reload`
Para ler `commands.yml` (aliases), reinicie o servidor.

Notas gerais
- As raças são aplicadas por grupos do LuckPerms mapeados em `plugins/RacesEffects/config.yml` (apply.groups).
- Recomendações: `hardNoRaceMode: false` e `enforceGroupTags: true` em `plugins/RacesEffects/config.yml`.
- Verifique tags ativas: `tag <nick> list` (ou `scoreboard players tag <nick> list`).

## Elfos
- Elfo (race_elf)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_elf`
- Alto Elfo (race_elf_high)
  - Efeitos: Darkvision + Sorte I (bônus moderado de “Inteligência”)
  - LuckPerms: `lp user <nick> parent set race_elf_high`
- Elfo da Floresta (race_elf_wood)
  - Efeitos: Darkvision + Velocidade +0.02 (atributo)
  - LuckPerms: `lp user <nick> parent set race_elf_wood`
- Drow (race_elf_drow)
  - Efeitos: Darkvision
  - Aliases opcionais: `/drowdarkness` (Darkness 10s) e `/drowsun` (Weakness 10s)
  - Permissões (aliases): `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.selector`
  - LuckPerms: `lp user <nick> parent set race_elf_drow`

## Anões
- Anão (race_dwarf)
  - Efeitos: Resistência a veneno (−50% poison)
  - LuckPerms: `lp user <nick> parent set race_dwarf`
- Anão da Colina (race_dwarf_hill)
  - Efeitos: +2.0 MAX_HEALTH (+1 coração)
  - LuckPerms: `lp user <nick> parent set race_dwarf_hill`
- Anão da Montanha (race_dwarf_mountain)
  - Efeitos: Força I
  - LuckPerms: `lp user <nick> parent set race_dwarf_mountain`

## Gnomos
- Gnomo (race_gnome)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_gnome`
- Gnomo da Floresta (race_gnome_forest)
  - Efeitos: Pressa I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_forest`
- Gnomo das Rochas (race_gnome_rock)
  - Efeitos: Absorção I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_rock`

## Halflings
- Lightfoot (race_halfling_lightfoot)
  - Efeitos: Supersalto I (curto)
  - LuckPerms: `lp user <nick> parent set race_halfling_lightfoot`
- Stout / Robusto (race_halfling_stout)
  - Efeitos: Saturação (curta)
  - LuckPerms: `lp user <nick> parent set race_halfling_stout`

## Humanos
- Humano (race_human)
  - Efeitos: Regeneração II (contínua) + bônus moderado em todos os atributos
  - Como aplicar os atributos: `/humanasi` (aplica +1 em STR, DEX, CON, INT, WIS, CHA)
  - LuckPerms: `lp user <nick> parent set race_human`

## Dragonborn (Draconato)
- Base (race_dragonborn)
  - Efeitos: +1.0 MAX_HEALTH (Vida extra Nível 1)
  - LuckPerms: `lp user <nick> parent set race_dragonborn`
- Fire (race_dragonborn_fire)
  - Efeitos: Sopro de fogo via alias
  - Comando: `/breath` lança `small_fireball`
  - LuckPerms: `lp user <nick> parent set race_dragonborn_fire`
- Cold (race_dragonborn_cold)
  - Efeitos: Sopro de frio via alias
  - Comando: `/breath` aplica Slowness em nuvem ao redor
  - LuckPerms: `lp user <nick> parent set race_dragonborn_cold`
- Lightning (race_dragonborn_lightning)
  - Efeitos: Sopro elétrico via alias
  - Comando: `/breath` invoca `lightning_bolt` à frente
  - LuckPerms: `lp user <nick> parent set race_dragonborn_lightning`
- Acid (race_dragonborn_acid)
  - Efeitos: Sopro ácido via alias
  - Comando: `/breath` cria nuvem com Dano Instantâneo
  - LuckPerms: `lp user <nick> parent set race_dragonborn_acid`
- Poison (race_dragonborn_poison)
  - Efeitos: Sopro venenoso via alias
  - Comando: `/breath` cria nuvem de Veneno
  - LuckPerms: `lp user <nick> parent set race_dragonborn_poison`
- Permissões para `/breath`: `minecraft.command.execute`, `minecraft.command.summon`, `minecraft.command.effect`, `minecraft.command.selector`

## Meio‑Elfo
- Meio‑Elfo (race_half_elf)
  - Efeitos: Darkvision + Queda Lenta I (curto) + Graça marinha (Dolphin's Grace) curta
  - LuckPerms: `lp user <nick> parent set race_half_elf`

## Meio‑Orc
- Meio‑Orc (race_half_orc)
  - Efeitos: Relentless Endurance
  - Alias opcional: `/berserk` → Strength I + Resistance I por 10s (recomendado uso consciente)
  - Permissões (aliases): `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.selector`
  - LuckPerms: `lp user <nick> parent set race_half_orc`

## Tiefling
- Tiefling (race_tiefling)
  - Efeitos: Darkvision + Resistência ao Fogo
  - LuckPerms: `lp user <nick> parent set race_tiefling`

## Referências rápidas (config)
- Efeitos simples: `apply.tags.*`
- Modificadores de atributo: `apply.attributeModifiers.*`
- Mapeamento de grupos → tags: `apply.groups`
