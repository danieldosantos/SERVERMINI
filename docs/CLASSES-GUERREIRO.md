# Classe: Guerreiro (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> fighter`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_fighter`

## Principais habilidades
- **Action Surge**: +0,15 de velocidade de ataque e +2 de dano por 10s, recarga 120s (`actionSurge`).
- **Second Wind**: cura 6 pontos + 25% da vida máxima, recarga 180s (`secondWind`).
- **Manobras**: bônus de dano (+1) e knockback (+0,25) com recarga de 30s (`maneuvers`).

## Arquétipos (LuckPerms)
- Campeão — `class_fighter_champion`
  - `lp creategroup class_fighter_champion`
  - `lp group class_fighter_champion setdisplayname "Arquétipo: Campeão"`
- Mestre de Batalha — `class_fighter_battlemaster`
  - `lp creategroup class_fighter_battlemaster`
  - `lp group class_fighter_battlemaster setdisplayname "Arquétipo: Mestre de Batalha"`
- Cavaleiro Arcano — `class_fighter_eldritch`
  - `lp creategroup class_fighter_eldritch`
  - `lp group class_fighter_eldritch setdisplayname "Arquétipo: Cavaleiro Arcano"`

Atribuir ao jogador:
```
lp user <nick> parent add class_fighter
lp user <nick> parent add class_fighter_<arquetipo>
```

## Itens focais
- Primário: `BattleManual`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.fighter.actionSurge`, `secondWind`, `maneuvers`, `archetypes.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem sugerida: FOR → CON → DES → SAB → CAR → INT.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
