# Classe: Monge (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> monk`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_monk`

## Principais habilidades
- **Ki**: pool base de 4 pontos, regenera 1 ponto a cada 30s fora de combate (`ki`).
- **Rajada de Golpes**: custo 1 Ki, +2 ataques extras por 3s, recarga curta (4s) (`flurry`).
- **Passo do Vento**: custo 1 Ki, +0,04 velocidade e +1 salto por 5s (`stepOfTheWind`).
- **Mãos Curativas**: custo 2 Ki, cura 6 pontos de vida, recarga 10s (`handsOfHealing`).

## Tradições Monásticas (LuckPerms)
- Mão Aberta — `class_monk_openhand`
  - `lp creategroup class_monk_openhand`
  - `lp group class_monk_openhand setdisplayname "Tradição: Mão Aberta"`
- Sombra — `class_monk_shadow`
  - `lp creategroup class_monk_shadow`
  - `lp group class_monk_shadow setdisplayname "Tradição: Sombra"`
- Quatro Elementos — `class_monk_elements`
  - `lp creategroup class_monk_elements`
  - `lp group class_monk_elements setdisplayname "Tradição: Quatro Elementos"`

Atribuir ao jogador:
```
lp user <nick> parent add class_monk
lp user <nick> parent add class_monk_<tradicao>
```

## Itens focais
- Primário: `KiBracelet`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.monk.ki`, `flurry`, `stepOfTheWind`, `handsOfHealing`, `traditions.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: DES → SAB → CON → FOR → CAR → INT.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
