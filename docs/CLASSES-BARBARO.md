# Classe: Bárbaro (RacesEffects)
Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> barbarian`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_barbarian`

Comandos in-game do Bárbaro
- Fúria: `/rage on|off|toggle`
- Ataque Temerário: `/reckless on|off|toggle`
- Atributos: `/abilities`, `/abilities set <STR|DEX|CON|INT|WIS|CHA> <valor>`, `/abilities add ...`, `/abilities reset`

Fúria (valores padrão — sem caminho)
- Dano extra: +3.00 em `generic.attack_damage`.
- Redução de dano: 25% contra ataques corpo‑a‑corpo, varrida, projéteis e explosões.
- Duração: 120s • Recarga: 180s.
- Onde ajustar: `plugins/RacesEffects/config.yml` → `apply.classes.barbarian.rage`

Principais características
- Movimento Rápido: bônus de velocidade quando sem armadura.
- Defesa sem Armadura: usa modificadores de DEX e CON para bônus em `generic.armor` (com teto).
- Fúria: +dano de ataque e redução de dano físico/explosões por um tempo; tem recarga.
- Ataque Temerário: curto +dano, mas recebe mais dano de ataques/projéteis.
- Percepção do Perigo: redução extra contra explosões e fogo/lava quando não estiver cego.

Como criar os grupos (LuckPerms)
- Classe (obrigatório)
  - `lp creategroup class_barbarian`
  - `lp group class_barbarian setdisplayname "Classe: Bárbaro"`
  - `lp group class_barbarian setweight 50`
- Caminhos Primitivos (escolha um)
  - Berserker
    - `lp creategroup class_barbarian_berserker`
    - `lp group class_barbarian_berserker setdisplayname "Caminho: Berserker"`
  - Totem (Urso)
    - `lp creategroup class_barbarian_totem_bear`
    - `lp group class_barbarian_totem_bear setdisplayname "Caminho: Totem (Urso)"`
  - Totem (Águia)
    - `lp creategroup class_barbarian_totem_eagle`
    - `lp group class_barbarian_totem_eagle setdisplayname "Caminho: Totem (Águia)"`
  - Totem (Lobo)
    - `lp creategroup class_barbarian_totem_wolf`
    - `lp group class_barbarian_totem_wolf setdisplayname "Caminho: Totem (Lobo)"`

Como atribuir ao jogador
- Classe: `lp user Tiefling parent add class_barbarian`
- Caminho (um deles):
  - `lp user <nick> parent add class_barbarian_berserker`
  - ou `lp user Tiefling parent add class_barbarian_totem_bear`
  - ou `lp user <nick> parent add class_barbarian_totem_eagle`
  - ou `lp user <nick> parent add class_barbarian_totem_wolf`
- Verificar: `lp user <nick> info`

Comandos in‑game do Bárbaro
- Fúria: `/rage on|off|toggle`


- Ataque Temerário: `/reckless on|off|toggle`
- Atributos: `/abilities`, `/abilities set <STR|DEX|CON|INT|WIS|CHA> <valor>`, `/abilities add ...`, `/abilities reset`

Detalhe dos Caminhos Primitivos (valores exatos)
- Berserker — grupo `class_barbarian_berserker`
  - Dano extra em Fúria: +1.00 em `generic.attack_damage` (além do bônus base da Fúria).
  - Velocidade de ataque: +0.10 em `generic.attack_speed` enquanto a classe estiver ativa.
  - Som ao iniciar Fúria: grito do jogador.
- Totem do Urso — grupo `class_barbarian_totem_bear`
  - Redução de dano extra durante a Fúria: +15% (0.15). Ex.: com base 25% da Fúria, totaliza ~40%.
  - Som ao iniciar Fúria: aviso/rugido de urso polar.
- Totem da Águia — grupo `class_barbarian_totem_eagle`
  - q
  - Som ao iniciar Fúria: grito de phantom (aproximação de ave).
- Totem do Lobo — grupo `class_barbarian_totem_wolf`
  - Resistência a empurrão/knockback: +0.05 em `generic.knockback_resistance` enquanto a classe estiver ativa.
  - Som ao iniciar Fúria: uivo de lobo.

Ajustes de UI
- Para não mostrar “CD” na tela em tempo real: `apply.classes.barbarian.ui.showCooldown: false`.
- Para mostrar o tempo ao abrir o inventário (se habilitado): `apply.classes.barbarian.ui.showCooldownOnInventoryOpen: true`.

Onde ajustar valores
- `fastMovement.speed`
- `unarmoredDefense.armorPerDexMod`, `armorPerConMod`, `maxArmorBonus`
- `rage.attackBonus`, `rage.damageReduction`, `rage.durationTicks`, `rage.cooldownTicks`
- `reckless.attackBonus`, `reckless.vulnerability`, `reckless.durationTicks`, `reckless.cooldownTicks`
- `dangerSense.explosionReduction`, `dangerSense.fireReduction`
- `paths.*` (bônus por subclasse)

Após editar o YAML: `/raceseffects reload`
