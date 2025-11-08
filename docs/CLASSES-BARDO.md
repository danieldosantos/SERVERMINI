# Classe: Bardo (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> bard`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_bard`

Comandos in‑game do Bardo
- Inspiração: `/inspire <jogador>`
- Canção de Descanso: `/song on|off|toggle`
- Atributos: `/abilities`, `/abilities set <STR|DEX|CON|INT|WIS|CHA> <valor>`, `/abilities add ...`, `/abilities reset`

Inspiração (valores padrão)
- Bônus no alvo: `+0.10` em `generic.attack_speed` e `+0.02` em `generic.movement_speed`.
- Dano extra opcional: `+1.00` em `generic.attack_damage` se o Bardo for do Colégio do Valor.
- Duração: 45s • Recarga (no Bardo): 60s.
- Onde ajustar: `plugins/RacesEffects/config.yml` → `apply.classes.bard.inspiration`

Canção de Descanso (valores padrão)
- Aura de Regeneração: aplica Regeneração I (amplifier 0) no Bardo e aliados próximos.
- Raio: 12 blocos (Lore adiciona +4 blocos) • Duração: 30s • Recarga: 60s.
- Onde ajustar: `plugins/RacesEffects/config.yml` → `apply.classes.bard.song`

Principais características (PHB‑like)
- Inspiração: buff direcionado a um aliado, com recarga por Bardo.
- Canção de Descanso: aura simples e situacional de regeneração.
- Sinergias por Colégio: Valor foca mais em combate (dano), Lore em suporte (alcance/duração).

Como criar os grupos (LuckPerms)
- Classe (obrigatório)
  - `lp creategroup class_bard`
  - `lp group class_bard setdisplayname "Classe: Bardo"`
  - `lp group class_bard setweight 50`
- Colégios (escolha um)
  - Valor
    - `lp creategroup class_bard_valor`
    - `lp group class_bard_valor setdisplayname "Colégio: Valor"`
  - Lore
    - `lp creategroup class_bard_lore`
    - `lp group class_bard_lore setdisplayname "Colégio: Lore"`

Como atribuir ao jogador
- Classe: `lp user <nick> parent set class_bard`
- Colégio (um deles):
  - `lp user <nick> parent add class_bard_valor`
  - ou `lp user <nick> parent add class_bard_lore`
- Verificar: `lp user <nick> info`

Comandos in‑game do Bardo (resumo)
- Inspiração: `/inspire <jogador>`
- Canção de Descanso: `/song on|off|toggle`
- Atributos: `/abilities`, `/abilities set <STR|DEX|CON|INT|WIS|CHA> <valor>`, `/abilities add ...`, `/abilities reset`

Detalhe dos Colégios (valores exatos)
- Valor — grupo `class_bard_valor`
  - Inspiração concede dano adicional ao alvo: `+1.00` em `generic.attack_damage` (além dos bônus base de velocidade/locomoção).
- Lore — grupo `class_bard_lore`
  - Inspiração dura mais: `+200 ticks` (10s) de duração extra.
  - Canção tem alcance maior: `+4.0` blocos de raio.

Ajustes de UI
- `apply.classes.bard.ui.actionbar: true` para exibir status.
- `apply.classes.bard.ui.showCooldown: true` para exibir recargas.

Onde ajustar valores
- `inspiration.attackSpeedBonus`, `inspiration.moveSpeedBonus`, `inspiration.damageBonus`, `inspiration.durationTicks`, `inspiration.cooldownTicks`
- `song.regenAmplifier`, `song.radius`, `song.durationTicks`, `song.cooldownTicks`
- `colleges.valor.*` e `colleges.lore.*`

Após editar o YAML: `/raceseffects reload`
