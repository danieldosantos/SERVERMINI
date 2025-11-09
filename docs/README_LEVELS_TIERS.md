# Sistema Global de Níveis & Tiers (1-20)

Este documento descreve a implementação do sistema de progressão global utilizado no servidor, combinando ProSkillAPI, LuckPerms, Skript, MythicMobs e PlaceholderAPI. O objetivo é garantir uma progressão linear inspirada em MMORPGs, com desbloqueios por Tier que habilitam conteúdos (habilidades, subclasses, itens focais e PvE) sem ultrapassar os limites globais de poder (+0.05 velocidade, 45% mitigação total).

## Visão Geral

- **Níveis Globais:** 1 a 20. A curva de XP segue `XP_next = 100 * (nível ^ 1.6)` e está registrada em `plugins/ProSkillAPI/settings/levels.yml`.
- **Tiers:**
  - Tier 1 → níveis 1-4
  - Tier 2 → níveis 5-10
  - Tier 3 → níveis 11-16
  - Tier 4 → níveis 17-20
- **Atribuições de poder:** cada Tier concede multiplicadores moderados (vida, dano, resistência, velocidade) dentro dos limites definidos.
- **HUD MMORPG:** scoreboard lateral atualizado a cada 2 segundos com nível, tier, barra de XP, numeração absoluta e percentual.
- **Anti-grind:** redução automática (DR) para abates repetidos do mesmo mob (≥3 em 45s) reduz XP para 40% do valor base.

## Componentes

### ProSkillAPI
- `levels.yml`: contém a tabela de XP (nível 1→20) com referência aos Tiers.
- `scripts/level_rewards.yml`: documentação estruturada das recompensas por nível e atributos por Tier. Utilizada como referência para QA e para ajustes rápidos (mantida em sincronia com o Skript).

### LuckPerms
- `plugins/LuckPerms/tiers_groups.txt`: script com os comandos necessários para criar os grupos `tier_t1` → `tier_t4` e configurar a hierarquia de herança.

### Skript
- `plugins/Skript/scripts/level_system.sk`:
  - Detecta mudanças de XP, sincroniza Tiers e atualiza o HUD.
  - Garante transição automática de grupos LuckPerms e aplica/remover modificadores de atributo via comandos `psapi attrmod`.
  - Implementa comandos administrativos (`/dar_xp`, `/set_lvl`, `/levelxp`) e comandos de jogador (`/nivel`, `/tier_info`).
  - Aplica DR anti-grind com base em repetição de kills.
  - Disponibiliza a interface `/levelxp` para MythicMobs, aceitando parâmetros `player`, `quantia`, `categoria`, `mob`.

### MythicMobs
- `plugins/MythicMobs/Drops/xp_awards.yml`: três drop tables (`xp_normal`, `xp_elite`, `xp_boss`) que executam o comando `/levelxp` com diferentes valores base e condições (distância, combate, cooldown). Inclui bônus opcionais para elites/bosses.

### PlaceholderAPI & Scoreboard
- Placeholders utilizados: `{level}`, `{xp_atual}`, `{xp_proximo}`, `{tier_atual}`. O Skript converte esses valores para alimentar o HUD.
- O scoreboard mostra: Nível, Tier, barra de XP estilo `███▒▒`, numeração absoluta/percentual e um resumo `T1 ● ○ ○ ○`.

## Fluxo de Nível → Tier

1. Jogador ganha XP via `/levelxp` (MythicMobs) ou `/dar_xp` (admin).
2. `level_system.sk` chama `psapi player <player> exp add <valor>` após aplicar DR.
3. Ao detectar mudança de nível, o Skript:
   - Define o Tier correspondente (1:1 com faixa de nível).
   - Remove grupos anteriores `tier_t*` e adiciona o novo via LuckPerms.
   - Reaplica os modificadores ProSkillAPI (`attrmod add global_tier_*`).
   - Atualiza HUD e envia título/mensagem.
4. Com base no Tier, classes e raças podem verificar permissões (`group.tier_tX`) para liberar habilidades, subclasses, itens e conteúdos PvE.

## Comandos

| Comando | Função |
| --- | --- |
| `/nivel` | Mostra nível, tier e atualiza HUD imediatamente. |
| `/tier_info` | Lista próximos desbloqueios e faixa de nível necessária. |
| `/dar_xp <jogador> <quantia>` | (Op) Aplica XP com DR e atualização imediata. |
| `/set_lvl <jogador> <1-20>` | (Op) Força nível global, re-sincronizando Tier. |
| `/levelxp <player> <xp> [categoria] [mob]` | (Console/MythicMobs) Interface para drops. |

## Integração com Conteúdos

- **Classes/Subclasses:** use permissões `group.tier_tX` nos requisitos de habilidades ou itens focais.
- **Raças (RacesEffects):** somente leitura; caso a resistência racial seja idêntica à resistência do Tier, a mitigação adicional não deve ultrapassar 45%. Ajustes devem ocorrer em habilidades/atributos, não nas raças.
- **PvE:** Dungeons, eventos e chefes devem validar grupo LuckPerms ou nível atual via placeholder `{level}`.

## Ajustes e Manutenção

- Para alterar a curva de XP: editar `levels.yml` e atualizar a documentação.
- Para calibrar atributos: ajustar os multiplicadores nas opções do Skript e na planilha `BALANCE_SHEET_LEVELS.csv`.
- Para novas categorias de XP em MythicMobs: criar nova entrada no `xp_awards.yml` e apontar para `/levelxp` com parâmetros apropriados.
- Sempre que alterar tiers/desbloqueios, sincronizar as seções correspondentes em `level_rewards.yml`, `level_system.sk` (options) e documentação.

