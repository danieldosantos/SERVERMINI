# Classe: Mago (Wizard) — RacesEffects

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> wizard`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_wizard`

## Principais habilidades
- **Preparação Arcana**: 6 magias preparadas +1 por tier, swap com recarga 60s (`spellbook`).
- **Controle Arcano**: paredes e teleporte curto com recargas de 90s/45s respectivamente (`controlSpells`).
- **Recuperação Arcana**: restaura até nível 3 de slots a cada 6 minutos (`arcaneRecovery`).

## Escolas de Magia (LuckPerms)
- Evocação — `class_wizard_evocation`
  - `lp creategroup class_wizard_evocation`
  - `lp group class_wizard_evocation setdisplayname "Escola: Evocação"`
- Abjuração — `class_wizard_abjuration`
  - `lp creategroup class_wizard_abjuration`
  - `lp group class_wizard_abjuration setdisplayname "Escola: Abjuração"`
- Adivinhação — `class_wizard_divination`
  - `lp creategroup class_wizard_divination`
  - `lp group class_wizard_divination setdisplayname "Escola: Adivinhação"`

Atribuir ao jogador:
```
lp user <nick> parent add class_wizard
lp user <nick> parent add class_wizard_<escola>
```

## Itens focais
- Primário: `WizardStaff`
- Secundário: `Spellbook`

## Ajustes no YAML
- Arquivo: `plugins/RacesEffects/config.yml`
- Seções: `apply.classes.wizard.spellbook`, `controlSpells`, `arcaneRecovery`, `schools.*`, `focus`, `abilities`

## Distribuição de atributos
- Ordem recomendada: INT → CON → DES → SAB → CAR → FOR.
- Array inicial: `15, 14, 13, 12, 10, 8`.

## Verificações rápidas
- Grupos: `lp user <nick> info`
- Tags: `tag <nick> list`
