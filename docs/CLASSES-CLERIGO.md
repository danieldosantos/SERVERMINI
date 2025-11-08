# Classe: Clérigo (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> cleric`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_cleric`

## Principais habilidades
- **Canalizar Divindade**: cura em área de 8 corações em um raio de 10 blocos e pode banir criaturas por 10s. Recarga de 120s (`channelDivinity.cooldownTicks`).
- **Palavra Curativa**: cura alvo em até 6 corações a 16 blocos, com recarga individual de 30s (`healingWord.targetCooldownTicks`).
- **Aura de Proteção**: resistência leve (+10%) que dura 60s, com tempo de recarga de 60s (`aura.durationTicks`).

## Domínios (LuckPerms)
- Vida — `class_cleric_life`
  - `lp creategroup class_cleric_life`
  - `lp group class_cleric_life setdisplayname "Domínio: Vida"`
- Luz — `class_cleric_light`
  - `lp creategroup class_cleric_light`
  - `lp group class_cleric_light setdisplayname "Domínio: Luz"`
- Guerra — `class_cleric_war`
  - `lp creategroup class_cleric_war`
  - `lp group class_cleric_war setdisplayname "Domínio: Guerra"`

Atribuir ao jogador (escolher um domínio):
```
lp user <nick> parent add class_cleric
lp user <nick> parent add class_cleric_<dominio>
```

## Itens focais
- Primário: `SacredSymbol`
- Secundário: `PrayerBook`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções principais: `apply.classes.cleric.channelDivinity`, `healingWord`, `aura`, `domains.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: SAB → CON → CAR → FOR → INT → DES (`abilities.recommendedOrder`).
- Array inicial sugerido: `15, 14, 13, 12, 10, 8` (`abilities.startArray`).

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags/RacesEffects: `tag <nick> list`
