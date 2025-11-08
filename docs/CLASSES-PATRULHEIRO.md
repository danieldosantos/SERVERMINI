# Classe: Patrulheiro (Ranger) — RacesEffects

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> ranger`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_ranger`

## Principais habilidades
- **Marca do Caçador**: +2 de dano contra alvo marcado por 60s; recarga 60s (`huntersMark`).
- **Companheiro Animal**: invoca aliado por 90s com 30 de vida e 6 de dano; recarga 30s (`companion`).
- **Armadilhas**: armadilha de lentidão (Slowness II) em raio de 4 blocos por 10s; recarga 90s (`traps`).

## Conclaves (LuckPerms)
- Caçador — `class_ranger_hunter`
  - `lp creategroup class_ranger_hunter`
  - `lp group class_ranger_hunter setdisplayname "Conclave: Caçador"`
- Mestre das Feras — `class_ranger_beast`
  - `lp creategroup class_ranger_beast`
  - `lp group class_ranger_beast setdisplayname "Conclave: Mestre das Feras"`
- Andarilho das Sombras — `class_ranger_gloom`
  - `lp creategroup class_ranger_gloom`
  - `lp group class_ranger_gloom setdisplayname "Conclave: Andarilho das Sombras"`

Atribuir ao jogador:
```
lp user <nick> parent add class_ranger
lp user <nick> parent add class_ranger_<conclave>
```

## Itens focais
- Primário: `RangerBow`
- Secundário: `TrapKit`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.ranger.huntersMark`, `companion`, `traps`, `conclaves.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: DES → SAB → CON → FOR → INT → CAR.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
