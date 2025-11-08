# Classe: Druida (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> druid`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_druid`

## Principais habilidades
- **Forma Selvagem**: bônus de +8 de vida, +4 de armadura, +0,03 de velocidade e +2 de dano por 60s. Recarga: 180s (`wildShape`).
- **Controle de Terreno**:
  - `Entangle`: raio de 6 blocos, duração 10s, recarga 45s (`terrain.entangle`).
  - `Fog`: raio de 8 blocos, duração 8s, recarga 60s (`terrain.fog`).

## Círculos Druidicos (LuckPerms)
- Terra — `class_druid_land`
  - `lp creategroup class_druid_land`
  - `lp group class_druid_land setdisplayname "Círculo: Terra"`
- Lua — `class_druid_moon`
  - `lp creategroup class_druid_moon`
  - `lp group class_druid_moon setdisplayname "Círculo: Lua"`
- Esporos — `class_druid_spores`
  - `lp creategroup class_druid_spores`
  - `lp group class_druid_spores setdisplayname "Círculo: Esporos"`

Atribuir ao jogador (escolher um círculo):
```
lp user <nick> parent add class_druid
lp user <nick> parent add class_druid_<circulo>
```

## Itens focais
- Primário: `DruidicStaff`
- Secundário: `MoonstoneTotem`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.druid.wildShape`, `terrain.*`, `circles.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: SAB → CON → DES → INT → CAR → FOR.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags RacesEffects: `tag <nick> list`
