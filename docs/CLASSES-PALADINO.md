# Classe: Paladino (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> paladin`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_paladin`

## Principais habilidades
- **Golpe Divino**: +4 de dano adicional por 5s, recarga 45s (`smite`).
- **Imposição de Mãos**: 5 cargas, cada uma cura 6 pontos; recarrega a cada 6 minutos (`layOnHands`).
- **Aura do Juramento**: +15% de resistência em um raio de 8 blocos por 30s, recarga 90s (`aura`).

## Juramentos (LuckPerms)
- Devoção — `class_paladin_devotion`
  - `lp creategroup class_paladin_devotion`
  - `lp group class_paladin_devotion setdisplayname "Juramento: Devoção"`
- Ancestrais — `class_paladin_ancients`
  - `lp creategroup class_paladin_ancients`
  - `lp group class_paladin_ancients setdisplayname "Juramento: Ancestrais"`
- Vingança — `class_paladin_vengeance`
  - `lp creategroup class_paladin_vengeance`
  - `lp group class_paladin_vengeance setdisplayname "Juramento: Vingança"`

Atribuir ao jogador:
```
lp user <nick> parent add class_paladin
lp user <nick> parent add class_paladin_<juramento>
```

## Itens focais
- Primário: `SacredBlade`
- Secundário: `OathEmblem`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.paladin.smite`, `layOnHands`, `aura`, `oaths.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: FOR → CAR → CON → SAB → DES → INT.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
