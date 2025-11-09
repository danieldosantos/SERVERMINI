# Guerreiro — Sistema de Estamina

Implementação completa da classe Guerreiro (não-conjuradora) com Estamina como recurso primário, suportada por ProSkillAPI, MythicMobs, Skript, LuckPerms e PlaceholderAPI.

## Escalonamento de Estamina

| Tier | Nível | Estamina Máx | Regeneração | Observações |
|----:|------:|-------------:|------------:|-------------|
| T1  | 1–4   | 100          | 4/s         | habilidades básicas, combo inicial |
| T2  | 5–10  | 140          | 6/s         | desbloqueio de dash e controle leve |
| T3  | 11–16 | 180          | 8/s         | posturas ofensivas/defensivas |
| T4  | 17–20 | 220          | 10/s        | ultimates e debuffs finais |

A drenagem/recuperação avançada é orquestrada via `warrior_cmds.sk`, mantendo exaustão física (-20% dano/velocidade por 4s) ao zerar a Estamina e respeitando os limites globais de +0.05 velocidade e 45% mitigação.

## Skills Comuns

### Tier 1
- **Golpe Rápido** (`warrior_quick_strike`): golpe 120% + interação com Postura Preparada e combo.
- **Empurrão** (`warrior_shove`): empurra 2 blocos + -10% velocidade (4s) com atenuação de controle.
- **Desviar** (`warrior_deflect`): bandeira de redução de 30% do próximo dano em até 6s.
- **Postura Preparada** (`warrior_prepared_stance`): próximo Golpe Rápido recebe +20% dano (12s para consumir).

### Tier 2
- **Golpe Atordoante** (`warrior_stunning_blow`): 130% dano + atordoamento curto.
- **Avanço de Linha** (`warrior_line_dash`): dash de 6 blocos com proteção de velocidade.
- **Corte Profundo** (`warrior_deep_cut`): 110% dano inicial + sangramento 5%/s por 6s.
- **Postura Defensiva** (`warrior_defensive_stance`): +20% mitigação por 6s drenando 2 Estamina/s.

### Tier 3
- **Ruptura** (`warrior_rend_armor`): 160% dano + -10% resistência inimiga (8s).
- **Giro de Arena** (`warrior_arena_spin`): cleave 140% em raio 5 blocos.
- **Caça Alvo** (`warrior_target_lock`): marca único alvo (+15% dano por 10s).
- **Postura Agressiva** (`warrior_aggressive_stance`): +15% ataque por 8s drenando 3 Estamina/s.

### Tier 4
- **Golpe Partidor** (`warrior_cleaving_execution`): 220% dano com execução abaixo de 25% HP.
- **Turbilhão** (`warrior_whirlwind`): cleave contínuo de 12s em 5 blocos.
- **Assalto de Pressão** (`warrior_pressure_assault`): ataques acumulam -2% defesa (máx. -20%) por 8s.
- **Coração de Aço** (`warrior_iron_heart`): ultimate defensivo, +30% mitigação por 8s.

## Arquétipos

### Campeão
1. **Golpe Preciso** (`warrior_champion_precise_strike`): +20% chance crítica no próximo ataque.
2. **Fúria Metódica** (`warrior_champion_methodical_fury`): +10% dano com HP cheio (12s).
3. **Pressão Constante** (`warrior_champion_relentless_pressure`): passiva +2% dano acumulativo (até +20%).
4. **Glória do Campeão** (`warrior_champion_glory`): ultimate +30% crit e +20% dano (10s).

### Mestre de Batalha
1. **Manobra: Derrubar** (`warrior_battlemaster_trip`): 120% dano + derrubar 1s.
2. **Manobra: Ameaça** (`warrior_battlemaster_threaten`): marca alvo com -15% dano (6s).
3. **Manobra: Comandar Linha** (`warrior_battlemaster_command_line`): aliados a 6 blocos +10% mitigação (8s).
4. **Chamado de Guerra** (`warrior_battlemaster_war_call`): ultimate aliados a 10 blocos +15% dano e velocidade (10s).

### Samurai
1. **Foco da Lâmina** (`warrior_samurai_blade_focus`): +15% penetração (8s).
2. **Postura Iaijutsu** (`warrior_samurai_iaijutsu_stance`): próximo ataque crítico garantido (10s).
3. **Retaliação** (`warrior_samurai_retaliation`): passiva +40% dano no próximo ataque após sofrer golpe (CD 6s).
4. **Espírito Guerreiro** (`warrior_samurai_war_spirit`): ultimate +20% regen de Estamina e +25% dano (12s).

## Integrações Complementares
- **MythicMobs**: `plugins/MythicMobs/Items/warrior_items.yml` fornece armas focais (espadas, lanças e estandartes) com binds diretos às skills principais/ultimates.
- **Skript**: `plugins/Skript/scripts/warrior_cmds.sk` gerencia comandos (/golpe, /postura, /manobra, /foco, /ult, /kit_warrior), HUD via PlaceholderAPI, combos, posturas, drenagem de Estamina e mitigação/exaustão.
- **LuckPerms**: `plugins/LuckPerms/warrior_groups.txt` cria e relaciona grupos de permissões `class_warrior` e subgrupos de arquétipos.
- **PlaceholderAPI**: HUD expõe `{stamina}`, `{stamina_regen}`, `{combo_stacks}`, `{posture_active}` através do scoreboard configurado em `warrior.yml`.

## Observações
- Todo controle duro (stuns/knockdowns) é limitado a 1–3s com efeito de imunidade decrescente via `warrior_apply_diminishing`.
- Posturas drenam Estamina com comandos `skillapi mana` e respeitam o teto de velocidade via `warrior_check_speed_cap`.
- As execuções e bônus críticos utilizam verificações de saúde/variáveis do Skript para manter fidelidade às regras.
