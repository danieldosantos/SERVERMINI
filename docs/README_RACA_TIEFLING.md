# Raça Tiefling (Infernal, Abissal e Sombrio)

## Visão Geral
Tieflings canalizam ancestrais infernais, alternando chamas controladas, mutações caóticas e manipulação de sombras. Cada linhagem enfatiza um estilo: **Infernal** foca em rajadas ígneas e intimidação, **Abissal** abraça buffs aleatórios e explosões sombrias, enquanto **Sombrio** oferece mobilidade e evasão dependentes de luz.

## Estrutura de Arquivos
- `plugins/RacesEffects/races/tiefling.yml`
- `plugins/RacesEffects/races/tiefling_infernal.yml`
- `plugins/RacesEffects/races/tiefling_abissal.yml`
- `plugins/RacesEffects/races/tiefling_sombrio.yml`
- `plugins/ProSkillAPI/skills/races/tiefling/*.yml`
- `plugins/MythicMobs/Items/tiefling_items.yml`
- `plugins/Skript/scripts/races_tiefling.sk`
- `plugins/Skript/scripts/races_cmds.sk` (hook `/racial`)
- `plugins/LuckPerms/races_tiefling_groups.txt`
- `docs/TESTPLAN_RACA_TIEFLING.md`
- `docs/BALANCE_SHEET_TIEFLING.csv`

## Traços Raciais (RacesEffects)
### Base — Tiefling
- Placeholders compartilhados (`{racial_cd_main}`, `{racial_resistencia_temporaria}`, `{racial_penalidade_luz}`, `{racial_charges}`) atualizados via skills/Skript.

### Sub-raça — Tiefling Infernal
- **Visão no Escuro Superior**: 32 blocos (uptime ≤58% com gatilho de baixa luz).
- **Resistência Infernal**: -20% dano de fogo por 8s (ICD 22s, DR com classe/itens).
- **Aura de Intimidação**: após crítico, inimigos a 4 blocos causam -5% dano por 4s (ICD 20s).
- Ativo: **Chama Infernal** (60s) — cone de 6 blocos (120–160% dano) seguido de exaustão (-8% dano por 6s).

### Sub-raça — Tiefling Abissal
- **Fluxo Caótico**: ao cair <40% HP rola bônus aleatório (dano +10%, velocidade +15% ou mitigação +10%) por 6s, ICD 45s.
- **Afinidade Abissal**: +5% dano contra alvos com lentidão/queimadura/sangramento (uptime ≤50%).
- Ativo: **Explosão Abissal** (75s) — área 6 blocos, 140% dano sombrio + desorientação 2s.

### Sub-raça — Tiefling Sombrio
- **Toque das Sombras**: luz ≤6 concede +0.02 velocidade e +15% esquiva (8s, ICD 12s).
- **Vulnerabilidade à Luz**: luz ≥13 reduz dano causado em 10% até 6s após exposição.
- **Visão Sombria Profunda**: revela entidades não invisíveis em 10 blocos quando imerso em escuridão.
- Ativo: **Escuridão Umbral** (90s) — campo 6 blocos/8s, inimigos -15% precisão, aliados +10% esquiva.

## Habilidades (ProSkillAPI)
- `Tiefling_ChamaInfernal.yml`: cone de fogo escalável (120→160%), aplica status `tiefling_exaustao_infernal` (-8% dano/6s) e atualiza placeholders.
- `Tiefling_ExplosaoAbissal.yml`: esfera 6 blocos, dano sombrio escalável e status `tiefling_desorientacao` (FOV leve) por 2s.
- `Tiefling_EscuridaoUmbral.yml`: campo 6 blocos/8s ajustando precisão e evasão conforme tier.
- Cada skill envia `placeholder_update` para HUD (`{racial_cd_main}`, `{racial_resistencia_temporaria}`, `{racial_penalidade_luz}`, `{racial_charges}`).

## Itens (MythicMobs)
- **AmuletoInfernus** (`race-lock: race_tiefling.infernal`) ativa Chama Infernal.
- **SigiloAbissal** (`race-lock: race_tiefling.abissal`) ativa Explosão Abissal.
- **Véu Umbral** (`race-lock: race_tiefling.sombrio`) ativa Escuridão Umbral.
- Cada item adiciona `skript-bind` para uso rápido via Skript.

## Comandos (Skript)
- `/racial_tiefling` — resumo da sub-raça ativa e atalhos.
- `/fogo_infernal`, `/abismo`, `/sombra` — chamam skills conforme linhagem.
- `/kit_racial_tiefling <jogador> [infernal|abissal|sombrio|all]` — entrega itens de QA.
- Evento de clique verifica display name dos itens MythicMobs e executa a skill correta.
- Função `tieflingRacialHelp` é chamada em `/racial` global via `races_cmds.sk`.

## Permissões (LuckPerms)
- `race_tiefling` (base) + subgrupos `race_tiefling.infernal`, `.abissal`, `.sombrio` herdando da base.
- Permissões adicionam acesso às skills (`psa.skill.*`), itens MythicMobs e comandos Skript.
- Arquivo `plugins/LuckPerms/races_tiefling_groups.txt` contém sequência completa de criação/parent/permissions.

## PlaceholderAPI / HUD
- `{raceseffects_racial_cd_main}` — cooldown atual de qualquer habilidade tiefling.
- `{raceseffects_racial_resistencia_temporaria}` — uptime da Resistência Infernal ou bônus vigente.
- `{raceseffects_racial_penalidade_luz}` — status de penalidade de luz (Sombrio).
- `{raceseffects_racial_charges}` — reservado para exibir cargas/eventos abissais via Skript.

## Diretrizes de Balanceamento
- Cooldowns principais: 60s (Infernal), 75s (Abissal), 90s (Sombrio) respeitando limite ≥45s.
- Buffs condicionais limitados a uptime ≤60% (resistência 36%, fluxo caótico 25%, toque das sombras 40–50%).
- Resistências compartilham DR com fontes de classe/itens (Warlock, Blood Hunter, equipamentos elementais).
- Penalidade de luz (-10% dano) garante contrajogo sem remover viabilidade em ambientes iluminados.
- Área padrão 6 blocos para habilidades, respeitando limite global configurado.

## Integração
1. Criar grupos LuckPerms com `plugins/LuckPerms/races_tiefling_groups.txt`.
2. Carregar YAMLs em RacesEffects e ProSkillAPI; recarregar Skript com `/sk reload races_tiefling` e `/sk reload races_cmds`.
3. Gerar itens via `/kit_racial_tiefling <jogador> infernal|abissal|sombrio|all`.
4. Validar HUD PlaceholderAPI checando `%raceseffects_racial_cd_main%`, `%raceseffects_racial_resistencia_temporaria%`, `%raceseffects_racial_penalidade_luz%`.

## Referências Cruzadas
- Plano de testes: `docs/TESTPLAN_RACA_TIEFLING.md`.
- Planilha de balanceamento: `docs/BALANCE_SHEET_TIEFLING.csv`.
