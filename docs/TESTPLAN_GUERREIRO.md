# Plano de Testes – Classe Guerreiro

## Metodologia
- Utilizar servidor de staging com ProSkillAPI, MythicMobs, Skript e PlaceholderAPI ativos.
- Habilitar scoreboard HUD do Guerreiro para monitorar `%skills_fighter_action_surge_cd%`, `%skills_fighter_second_wind_cd%`, `%skills_fighter_major_cd%` e mana (EK).
- Empregar `/kit_guerreiro` para setup padrão; limpar efeitos entre tentativas.
- Registrar métricas: DPS médio, tempo para matar (TTK), dano recebido, uptime de Postura Sólida, número de procs de passivas.
- Validar limites: velocidade total ≤0.05 adicional, resistências ≤45%, cura por golpe ≤25% do dano causado.

## Cenários PvE (10)
1. **T1 – Lobos**: Campeão vs 5 lobos. Meta: TTK < 25s, dano recebido < 60% HP, Postura Sólida ≤50% uptime.
2. **T1 – Zumbi com escudo**: Battlemaster. Verificar Golpe Desarmante removendo arma (–40% DPS). Medir recarga interna 45s.
3. **T2 – Esquadrão de esqueletos**: Campeão. Usar Desafio do Gladiador + Resiliência. Checar se resistências <45% após buff.
4. **T2 – Golem elemental**: Cavaleiro Élfico (elemento gelo). Confirmar Baluarte de Energia aplica retornos decrescentes para aliados com resistência racial.
5. **T3 – Guarda avançado**: Battlemaster. Executar Comando Tático sobre 3 aliados NPC; verificar -15% CD via logs.
6. **T3 – Boss móvel**: Cavaleiro Élfico. Alternar Teleporte Tático e Lâmina Arcana; monitorar consumo de 20 mana e regen fora de combate.
7. **T3 – Horda mista**: Campeão. Rotacionar Surto de Ação + Triunfo; garantir uptime combinado <55% em 3 minutos.
8. **T4 – Dragão de ferro**: Battlemaster. Giro da Vitória deve aplicar vulnerabilidade 15% por 10s; verificar via dano aliado.
9. **T4 – Titã elemental**: Cavaleiro Élfico com aliados magos. Testar Baluarte de Energia stackado com resistência de draconato → confirmar exaustão aplicada.
10. **T4 – Cerco prolongado**: Campeão com suporte clérigo por 5 minutos. Monitorar consumo de CDs, sem reset indevido de ultimate.

## Cenários PvP (10)
1. **Duelo T1 Campeão vs Bárbaro**: Avaliar TTK ~20–25s. Garantir Golpe Superior não executa >30% HP.
2. **Duelo T1 Battlemaster vs Ladino**: Testar Manobra Precisa + Golpe Desarmante; checar se ladino perde arma por 6s.
3. **Duelo T2 Cavaleiro Élfico vs Paladino**: Monitorar Escudo Arcano absorvendo 150 dano mágico e queda de velocidade após exaustão.
4. **Duelo T2 Campeão vs Monge**: Confirmar Resiliência Inabalável não ultrapassa 45% mitigação combinada.
5. **Duelo T3 Battlemaster vs Ranger**: Uso de Comando Tático em aliado dummy; medir redução de cooldown (logs `skillapi cooldown`).
6. **Duelo T3 Cavaleiro Élfico vs Feiticeiro**: Teleporte Tático deve manter distância sem atravessar barreiras; verificar 75s CD.
7. **3v3 (Campeão + Druida + Ladino) vs (Paladino + Mago + Bárbaro)**: Campeão forçando foco com Desafio do Gladiador. Monitorar sobrevivência ≥20s.
8. **3v3 (Battlemaster + Clérigo + Arqueiro) vs (Necromante + Guerreiro + Bardo)**: Avaliar buffs de Comando Tático no time (velocidade +0.02, cap respeitado).
9. **3v3 (Cavaleiro Élfico + Warlock + Ranger) vs (Assassino + Monge + Barbaro)**: Tempestade da Lâmina não deve deletar alvos full HP; confirmar dano distribuído.
10. **Arena livre**: Misturar 6 jogadores com diferentes raças. Checar logs de `fighter_resist_diminish`/`fighter_resistance_exhaust` acionados ao atingir caps.

## Critérios de Aprovação
- Nenhum erro em console durante carregamento das skills/Skript/MythicMobs.
- Todos os comandos respondem com feedback e respeitam permissões LuckPerms.
- HUD exibe cooldown máximo correto e atualiza ao usar habilidades.
- Resistências nunca excedem 45%; velocidade extra ≤0.05.
- Uptime de passivas ≤60% confirmado por métricas.
- Nenhum combo elimina alvo full-HP instantaneamente; execuções ocorrem somente <30% HP.
