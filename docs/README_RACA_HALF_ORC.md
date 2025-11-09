# Raça Meio-Orc (Base, Tribal e Gladiador)

## Visão Geral
Os meio-orcs combinam sobrevivência agressiva com janelas de explosão controlada. A raça base enfatiza tenacidade e pressão corpo-a-corpo, enquanto as variações **Tribal** e **Gladiador** expandem para caça rastreada e ritmo de arena.

## Estrutura de Arquivos
- `plugins/RacesEffects/races/half_orc.yml`
- `plugins/RacesEffects/races/half_orc_tribal.yml`
- `plugins/RacesEffects/races/half_orc_gladiador.yml`
- `plugins/ProSkillAPI/skills/races/half_orc/*.yml`
- `plugins/MythicMobs/Items/half_orc_items.yml`
- `plugins/Skript/scripts/races_half_orc.sk`
- `plugins/Skript/scripts/races_cmds.sk` (hook /racial)
- `plugins/LuckPerms/races_half_orc_groups.txt`
- `docs/TESTPLAN_RACA_HALF_ORC.md`
- `docs/BALANCE_SHEET_HALF_ORC.csv`

## Traços Raciais (RacesEffects)
### Base — Meio-Orc
- **Visão no Escuro**: alcance 24 blocos em baixa luz.
- **Tenacidade Implacável**: +15% mitigação por 6s abaixo de 35% HP (ICD 45s, uptime ~13%).
- **Ameaça Orc**: +5% dano corpo-a-corpo contra inimigos que atacaram nos últimos 4s.
- Ativo: **Fúria Controlada** (75s) — +10–18% dano por 8s seguido de Fatiga Orc (-10% dano por 6s).

### Sub-raça — Meio-Orc Tribal
- **Instinto do Rastreador**: revela trilhas suaves em alvos marcados por 10s.
- **Passo Selvagem**: +0.02 velocidade por 8s em florestas, taigas, pântanos e savanas (ICD 30s).
- Ativo: **Chamado da Caça** (60s) — marca alvo e concede +12% penetração de armadura contra ele.

### Sub-raça — Meio-Orc Gladiador
- **Ritmo de Arena**: após 3 golpes em 6s, +10% velocidade de ataque por 4s (ICD 20s).
- **Grito de Desafio**: provocação leve PvE em 4 blocos (90s por alvo).
- Ativo: **Estouro de Arena** (120s) — +25% dano corpo-a-corpo por 6s seguido de Exaustão (-20% velocidade de ataque por 6s).

## Habilidades (ProSkillAPI)
- Arquivos individuais para `Fúria Controlada`, `Chamado da Caça` e `Estouro de Arena` com tiers T1–T4.
- Buffs e penalidades pós-burst aplicados via status agendados (`delay`) para garantir uptime ≤60%.
- `raceseffects_trigger` mantém integração com rastros tribais e placeholders de HUD.

## Itens (MythicMobs)
- **OrcBattleTalisman**: ativa Fúria Controlada (`race-lock: race_half_orc`).
- **TribalBoneCharm**: ativa Chamado da Caça (`race-lock: race_half_orc.tribal`).
- **ArenaWarBangle**: ativa Estouro de Arena (`race-lock: race_half_orc.gladiador`).
- Campo `skript-trigger` permite atalho nos eventos do Skript.

## Comandos (Skript)
- `/furia_orc`, `/tenacidade`, `/tribal`, `/arena`, `/kit_racial_half_orc`.
- `/racial` chama `halfOrcRacialHelp` exibindo dicas por sub-raça.
- Função `halfOrcHandleItem` valida NBT `race-lock` e chama a skill correta ao clicar com o item MythicMobs.

## Permissões (LuckPerms)
- Grupo base: `race_half_orc`.
- Subgrupos: `race_half_orc.tribal` e `race_half_orc.gladiador` herdando do grupo base.

## PlaceholderAPI / HUD
- `{racial_cd_main}` → cooldown da skill ativa atual.
- `{racial_tenacidade}` → uptime da Tenacidade Implacável.
- `{racial_furia_fadiga}` → status da Fatiga Orc.
- `{racial_tracker}` e `{racial_combo}` para Tribal/Gladiador respectivamente.

## Diretrizes de Balanceamento
- Uptime efetivo de cada buff ≤60%; principais janelas: Fúria (8/75s ≈ 10.6%), Chamado (10/60s ≈ 16.6%), Estouro (6/120s = 5%).
- Caps globais respeitados (velocidade +0.05, mitigação 45%, cura/dano condicional conforme limites).
- Fadigas obrigatórias previnem empilhamento com habilidades de Bárbaro/Guerreiro/Blood Hunter.
- Resistências compartilhadas aplicam DR quando combinadas com fontes de classe/itens.

## Integração
1. Criar grupos via `plugins/LuckPerms/races_half_orc_groups.txt`.
2. Carregar YAMLs em RacesEffects, ProSkillAPI e MythicMobs; recarregar Skript com `/sk reload races_half_orc` e `/sk reload races_cmds`.
3. Distribuir focos usando `/kit_racial_half_orc <jogador> [base|tribal|gladiador|all]`.
4. Ajustar HUD (PlaceholderAPI) para exibir placeholders de Tenacidade, Fúria/Fadiga, Marca Tribal e Ritmo de Arena.

## Referências Cruzadas
- Plano de testes: `docs/TESTPLAN_RACA_HALF_ORC.md`.
- Planilha de balanceamento: `docs/BALANCE_SHEET_HALF_ORC.csv`.
