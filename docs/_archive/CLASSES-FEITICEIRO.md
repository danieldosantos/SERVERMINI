# Classe: Feiticeiro (Sorcerer) — RacesEffects

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> sorcerer`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_sorcerer`

## Principais habilidades
- **Pontos de Feitiçaria**: pool base de 3, regenera 1 ponto a cada 30s (`sorceryPoints`).
- **Metamagia**: custos (2/2/3 pontos) com recarga global de 10s (`metamagic`).
- **Arsenal Arcano**: dano em área baseado em 105% do dano padrão; ultimate com recarga de 180s (`spellcasting`).

## Linhagens (LuckPerms)
- Dracônica — `class_sorcerer_draconic`
  - `lp creategroup class_sorcerer_draconic`
  - `lp group class_sorcerer_draconic setdisplayname "Linhagem: Dracônica"`
- Magia Selvagem — `class_sorcerer_wild`
  - `lp creategroup class_sorcerer_wild`
  - `lp group class_sorcerer_wild setdisplayname "Linhagem: Magia Selvagem"`
- Tempestade — `class_sorcerer_storm`
  - `lp creategroup class_sorcerer_storm`
  - `lp group class_sorcerer_storm setdisplayname "Linhagem: Tempestade"`

Atribuir ao jogador:
```
lp user <nick> parent add class_sorcerer
lp user <nick> parent add class_sorcerer_<linhagem>
```

## Itens focais
- Primário: `ArcaneCrystal`
- Secundário: `MetamagicTome`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.sorcerer.sorceryPoints`, `metamagic`, `spellcasting`, `origins.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: CAR → CON → DES → INT → SAB → FOR.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
