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


- Observações
  - Os itens são consumidos ao usar e aplicam as tags/efeitos imediatamente.
  - Caso use a versão em Skript (sem novo JAR), basta recarregar: `skript reload race_items.sk` e `skript reload tiefling_soulspeed.sk`.
  - Para que efeitos passivos funcionem conforme grupos, use `/raceseffects reload` após editar YAML.
  - Para remover estados antigos (tags), use `/raceseffects reset <player>` antes de aplicar uma nova raça, se necessário.
