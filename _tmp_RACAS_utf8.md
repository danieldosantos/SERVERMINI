# Racas (Cheat-Sheet Completo)

Depois de editar YAML: `/raceseffects reload`
Para ler `commands.yml` (aliases), reinicie o servidor.

Notas gerais
- As racas sao aplicadas por grupos do LuckPerms mapeados em `plugins/RacesEffects/config.yml` (apply.groups).
- Recomendacoes: `hardNoRaceMode: false` e `enforceGroupTags: true` em `plugins/RacesEffects/config.yml`.
- Verifique tags ativas: `tag <nick> list` (ou `scoreboard players tag <nick> list`).

## Elfos
- Elfo (race_elf)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_elf`
- Alto Elfo (race_elf_high)
  - Efeitos: Darkvision + bonus situacional
  - LuckPerms: `lp user <nick> parent set race_elf_high`
- Elfo da Floresta (race_elf_wood)
  - Efeitos: Darkvision + Velocidade +0.02 (atributo)
  - LuckPerms: `lp user <nick> parent set race_elf_wood`
- Drow (race_elf_drow)
  - Efeitos: Darkvision
  - Aliases opcionais: `/drowdarkness` (Darkness 10s) e `/drowsun` (Weakness 10s)
  - Permissoes (aliases): `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.selector`
  - LuckPerms: `lp user <nick> parent set race_elf_drow`

## Anaos
- Anao (race_dwarf)
  - Efeitos: Resistencia a veneno (-50% poison)
  - LuckPerms: `lp user <nick> parent set race_dwarf`
- Anao da Colina (race_dwarf_hill)
  - Efeitos: +2.0 MAX_HEALTH (+1 coracao)
  - LuckPerms: `lp user <nick> parent set race_dwarf_hill`
- Anao da Montanha (race_dwarf_mountain)
  - Efeitos: Forca I
  - LuckPerms: `lp user <nick> parent set race_dwarf_mountain`

## Gnomos
- Gnomo (race_gnome)
  - Efeitos: Darkvision
  - LuckPerms: `lp user <nick> parent set race_gnome`
- Gnomo da Floresta (race_gnome_forest)
  - Efeitos: Pressa I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_forest`
- Gnomo das Rochas (race_gnome_rock)
  - Efeitos: Absorcao I (curto)
  - LuckPerms: `lp user <nick> parent set race_gnome_rock`

## Halflings
- Lightfoot (race_halfling_lightfoot)
  - Efeitos: Supersalto I (curto)
  - LuckPerms: `lp user <nick> parent set race_halfling_lightfoot`
- Stout / Robusto (race_halfling_stout)
  - Efeitos: Satuacao (curta)
  - LuckPerms: `lp user <nick> parent set race_halfling_stout`

## Humanos
- Humano (race_human)
  - Efeitos: Regeneracao condicional — Regeneration II por 15s sempre que vida <= 50% do maximo; reativa automaticamente enquanto permanecer <= 50%.
  - Como aplicar atributos: `/humanasi` (exemplo de distribuicao de pontos)
  - LuckPerms: `lp user <nick> parent set race_human`

## Dragonborn (Draconato)
- Base (race_dragonborn)
  - Efeitos: +1.0 MAX_HEALTH (vida extra inicial)
  - LuckPerms: `lp user <nick> parent set race_dragonborn`
- Fire (race_dragonborn_fire)
  - Efeitos: sopro de fogo via alias
  - Comando: `/breath` lanca `small_fireball`
  - LuckPerms: `lp user <nick> parent set race_dragonborn_fire`
- Cold (race_dragonborn_cold)
  - Efeitos: sopro de frio via alias
  - Comando: `/breath` aplica Slowness em nuvem ao redor
  - LuckPerms: `lp user <nick> parent set race_dragonborn_cold`
- Lightning (race_dragonborn_lightning)
  - Efeitos: sopro eletrico via alias
  - Comando: `/breath` invoca `lightning_bolt` a frente
  - LuckPerms: `lp user <nick> parent set race_dragonborn_lightning`
- Acid (race_dragonborn_acid)
  - Efeitos: sopro acido via alias
  - Comando: `/breath` cria nuvem com Dano Instantaneo
  - LuckPerms: `lp user <nick> parent set race_dragonborn_acid`
- Poison (race_dragonborn_poison)
  - Efeitos: sopro venenoso via alias
  - Comando: `/breath` cria nuvem de Veneno
  - LuckPerms: `lp user <nick> parent set race_dragonborn_poison`
- Permissoes para `/breath`: `minecraft.command.execute`, `minecraft.command.summon`, `minecraft.command.effect`, `minecraft.command.selector`

## Meio-Elfo
- Meio-Elfo (race_half_elf)
  - Efeitos: Darkvision + Queda Lenta (curta) + Graca marinha (curta)
  - LuckPerms: `lp user <nick> parent set race_half_elf`

## Meio-Orc
- Meio-Orc (race_half_orc)
  - Efeitos: Relentless Endurance (com cooldown)
  - LuckPerms: `lp user <nick> parent set race_half_orc`

## Tiefling
- Tiefling (race_tiefling)
  - Efeitos: Darkvision + Resistencia ao Fogo + Velocidade II no Nether (ativo enquanto no Nether; remove ao sair)
  - LuckPerms: `lp user <nick> parent set race_tiefling`

## Referencias rapidas (config)
- Efeitos simples: `apply.tags.*`
- Modificadores de atributo: `apply.attributeModifiers.*`
- Mapeamento de grupos para tags: `apply.groups`
