# Plano de Testes — Raça Tiefling

## Escopo
Validar que Tiefling base e sub-raças Infernal, Abissal e Sombrio carregam corretamente em RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms e PlaceholderAPI.

## Preparação
1. Criar grupos via `plugins/LuckPerms/races_tiefling_groups.txt`.
2. Atribuir ao jogador QA os grupos desejados (`race_tiefling` + subgrupo apropriado).
3. Recarregar scripts: `/sk reload races_tiefling` e `/sk reload races_cmds`.
4. Confirmar carregamento de skills ProSkillAPI (`/psa skill info Tiefling_ChamaInfernal`, etc.).
5. Usar `/kit_racial_tiefling <jogador> infernal|abissal|sombrio|all` para distribuir itens.
6. Checar placeholders `%raceseffects_racial_cd_main%`, `%raceseffects_racial_resistencia_temporaria%`, `%raceseffects_racial_penalidade_luz%`.

## Testes Tiefling Infernal
- **Visão no Escuro Superior**
  - Entrar em caverna/luz ≤6 e medir alcance 32 blocos; sair para luz intensa e confirmar cooldown 36s (uptime ≤58%).
- **Resistência Infernal**
  - Receber dano de fogo em bursts repetidos e garantir redução 20% por 8s com ICD 22s, sem exceder 45% mitigação total com equipamentos Warlock.
  - Verificar placeholder `{racial_resistencia_temporaria}` atualizando durante buff.
- **Aura de Intimidação**
  - Causar golpes críticos em múltiplos mobs e medir redução -5% dano por 4s (ICD 20s).
- **Chama Infernal**
  - Ativar via `/fogo_infernal` e AmuletoInfernus; confirmar cone 6 blocos, dano escalado e status de exaustão (-8% dano/6s).
  - Garantir cooldown 60s visível na HUD e uptime total <60%.

## Testes Tiefling Abissal
- **Fluxo Caótico**
  - Reduzir HP abaixo de 40% repetidamente para observar rolagem aleatória única por ativação, duração 6s, ICD 45s.
  - Confirmar bônus não se acumulam e respeitam caps (velocidade total ≤+0.05, mitigação ≤45%).
- **Afinidade Abissal**
  - Aplicar lentidão/queimadura/sangramento em alvos e medir +5% dano; remover debuffs e verificar retorno ao normal.
- **Explosão Abissal**
  - Ativar via `/abismo` e SigiloAbissal; confirmar raio 6 blocos, dano sombrio (140–155%) e debuff de desorientação 2s sem cegueira.
  - Checar HUD atualizando cooldown 75s e placeholder `{racial_charges}` (exibir valor atual).

## Testes Tiefling Sombrio
- **Toque das Sombras**
  - Ficar em áreas com luz ≤6 (noite/caverna) e medir +0.02 velocidade e +15% esquiva por 8s (ICD 12s, uptime ~40–50%).
  - Sair para luz >6 e confirmar perda imediata do bônus.
- **Vulnerabilidade à Luz**
  - Expor-se a luz ≥13 (holofotes, dia) e validar -10% dano causado por até 6s; monitorar placeholder `{racial_penalidade_luz}`.
- **Visão Sombria Profunda**
  - Em escuridão total, verificar detecção de mobs/players não invisíveis até 10 blocos.
- **Escuridão Umbral**
  - Ativar via `/sombra` e Véu Umbral; medir campo 6 blocos/8s, inimigos -15% precisão, aliados +10% esquiva.
  - Confirmar cooldown 90s e ausência de invisibilidade permanente.

## Integração PvE
- Rodar dungeon de fogo com Tiefling Infernal para validar DR + aura sem ultrapassar limites.
- Enfrentar mobs sombrios com Abissal e Sombrio garantindo resistência de trevas funciona com DR.
- Testar Escuridão Umbral em cavernas com grupo verificando bônus/penalidades simultâneos.

## Integração PvP
- Duelo Infernal vs classe de fogo medindo bursts e penalidade de exaustão (-8% dano/6s).
- Teamfight com Abissal usando Explosão Abissal: monitorar reação visual e impacto da desorientação.
- Skirmish noturna com Sombrio usando Escuridão Umbral; repetir em arena iluminada para testar vulnerabilidade à luz.

## Regressão
- Executar `/racial` sem ser tiefling e confirmar mensagens para outras raças continuam corretas.
- Recarregar RacesEffects/ProSkillAPI/Skript e observar console sem erros YAML.
- Tentar usar itens tiefling sem o grupo correspondente e validar bloqueio via `race-lock`.
- Checar que placeholders permanecem consistentes após trocar sub-raças (remover/adicionar grupos LuckPerms).
