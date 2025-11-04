# Comandos de Raça (Itens de Aplicação)

Este guia lista os comandos de admin para entregar itens que aplicam raças aos jogadores. Requer permissão `raceseffects.admin`.

- Sintaxe geral
  - `/raceitem give <jogador> <tipo>`
  - Pode ser executado no console do servidor (com ou sem barra)
    - Ex.: `raceitem give Steve highelf`
    - Ex.: `/raceitem give Steve tiefling`
  - Se o comando não aparecer, recarregue o Skript: `skript reload race_items.sk`

- Tipos disponíveis
  - Alto Elfo: `highelf` | `elf_high` | `alto`
    - Aplica: `race_darkvision` + retaliação passiva (Espinhos/Veneno/Náusea/Nada)
    - Comando: `/raceitem give <jogador> highelf`
  - Meio‑Elfo: `halfelf` | `half_elf` | `meioelfo` | `meio-elfo`
    - Aplica: `race_darkvision` + `race_dolphins_grace` (Graça Marinha permanente)
    - Entrega: `Pena do Meio‑Elfo` (usa para ganhar Queda Lenta por ~7s)
    - Comando: `/raceitem give <jogador> halfelf`
  - Meio‑Orc: `halforc` | `half_orc` | `meioorc` | `meio-orc`
    - Aplica: `race_darkvision` + `trait_relentless` (Relentless Endurance com cooldown)
    - Comando: `/raceitem give <jogador> halforc`
  - Tiefling: `tiefling`
    - Aplica: `race_darkvision` + `resist_fire`
    - Ativa: permissão virtual `group.race_tiefling` (Velocidade II no Nether via Skript)
    - Comando: `/raceitem give <jogador> tiefling`
  - Humano: `human` | `humano`
    - Aplica: permissões `group.race_human` + `raceseffects.abilities` (habilita `/abilities`); efeitos de regeneração conforme configuração/servidor
    - Comando: `/raceitem give <jogador> human`

  - Dragonborn (base): `dragonborn`
    - Aplica: `trait_hp_dragonborn` (+1.0 MAX_HEALTH) e alias de sopro via item
    - Entrega itens: `DragonbornScale`
    - Comando: `/raceitem give <jogador> dragonborn`
  - Dragonborn Fogo: `dragonborn_fire`
    - Aplica: grupo `race_dragonborn_fire`
    - Entrega itens: `DragonbornScale` + `DragonbornBreathFire` (clicar aciona `/breath` criando cone de fogo)
    - Comando: `/raceitem give <jogador> dragonborn_fire`
  - Dragonborn Gelo: `dragonborn_cold`
    - Aplica: grupo `race_dragonborn_cold`
    - Entrega itens: `DragonbornScale` + `DragonbornBreathCold` (clicar aciona `/breath` criando área de neve fofa; imune o próprio dragonborn de gelo)
    - Comando: `/raceitem give <jogador> dragonborn_cold`
  - Dragonborn Raio: `dragonborn_lightning`
    - Aplica: grupo `race_dragonborn_lightning`
    - Entrega itens: `DragonbornScale` + `DragonbornBreathLightning` (clicar aciona `/breath` com linha de raios)
    - Comando: `/raceitem give <jogador> dragonborn_lightning`
  - Dragonborn Ácido: `dragonborn_acid`
    - Aplica: grupo `race_dragonborn_acid`
    - Entrega itens: `DragonbornScale` + `DragonbornBreathAcid` (clicar aciona `/breath` com losango e Wither; imune o próprio dragonborn de ácido)
    - Comando: `/raceitem give <jogador> dragonborn_acid`
  - Dragonborn Veneno: `dragonborn_poison`
    - Aplica: grupo `race_dragonborn_poison`
    - Entrega itens: `DragonbornScale` + `DragonbornBreathPoison` (clicar aciona `/breath` com quadrado e Veneno; imune o próprio dragonborn de veneno)
    - Comando: `/raceitem give <jogador> dragonborn_poison`

- Observações
  - Os itens são consumidos ao usar e aplicam as tags/efeitos imediatamente.
  - Caso use a versão em Skript (sem novo JAR), basta recarregar: `skript reload race_items.sk`, `skript reload tiefling_soulspeed.sk` e `skript reload dragonborn_breaths.sk`.
  - Os itens `DragonbornBreath*` acionam o comando `/breath` ao clicar.
  - Para que efeitos passivos funcionem conforme grupos, use `/raceseffects reload` após editar YAML.
  - Para remover estados antigos (tags), use `/raceseffects reset <player>` antes de aplicar uma nova raça, se necessário.
