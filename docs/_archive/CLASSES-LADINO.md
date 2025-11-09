# Classe: Ladino (Rogue) — RacesEffects

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> rogue`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_rogue`

## Principais habilidades
- **Ataque Furtivo**: multiplicador de 2× com limite de 50% da vida do alvo; recarga 6s (`sneakAttack`).
- **Passo das Sombras**: teleporte de até 18 blocos, aplica lentidão no alvo por 3s; recarga 45s (`shadowStep`).
- **Ferramentas de Ladrão**: buffs utilitários por 60s, recarga 15s (`thievesTools`).

## Arquétipos (LuckPerms)
- Ladrão — `class_rogue_thief`
  - `lp creategroup class_rogue_thief`
  - `lp group class_rogue_thief setdisplayname "Arquétipo: Ladrão"`
- Assassino — `class_rogue_assassin`
  - `lp creategroup class_rogue_assassin`
  - `lp group class_rogue_assassin setdisplayname "Arquétipo: Assassino"`
- Trapaceiro Arcano — `class_rogue_arcane`
  - `lp creategroup class_rogue_arcane`
  - `lp group class_rogue_arcane setdisplayname "Arquétipo: Trapaceiro Arcano"`

Atribuir ao jogador:
```
lp user <nick> parent add class_rogue
lp user <nick> parent add class_rogue_<arquetipo>
```

## Itens focais
- Primário: `RogueDagger`
- Secundário: `ThievesKit`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.rogue.sneakAttack`, `shadowStep`, `thievesTools`, `archetypes.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem sugerida: DES → CAR → CON → SAB → INT → FOR.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
