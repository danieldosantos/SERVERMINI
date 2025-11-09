# Plano de Testes – Classe Patrulheiro

## Metodologia
- Ambiente: servidor de staging com ProSkillAPI + MythicMobs + Skript + PlaceholderAPI + LuckPerms + RacesEffects.
- Ferramentas: `/kit_ranger`, `/marca`, `/armadilha`, `/companheiro`, `/hunter_scope`, `/beast_sync`, `/gloom_cloak`.
- Métricas coletadas: DPS médio, tempo para matar (TTK), dano recebido, uptime da Marca do Caçador, uptime das Armadilhas, dano do companheiro (quando aplicável), stacks de Foco consumidos.
- Logs: PlaceholderAPI scoreboard (`{hunt_focus}`, `{mark_time_left}`, `{pet_hp}`) e saída do Skript em debug.

## Cenários PvE (10)
| # | Cenário | Objetivo | Métricas | Critério de Sucesso |
| - | ------- | -------- | -------- | ------------------- |
| 1 | Golem de Ferro solo (T1) | Validar DPS consistente com Marca | DPS, uptime marca (>40%), consumo de foco | Golem derrotado sem ficar sem foco por >10s |
| 2 | Enxame de zumbis (10 mobs) | Testar Armadilha Tática + Rajada | Dano total, n.º armadilhas, hits múltiplos | >=70% dos zumbis atingidos pela armadilha |
| 3 | Guardião Ancião (T2) | Avaliar dano sustentado + Terreno Favorito aquático | DPS, uptime bônus bioma | DPS dentro de 10% da meta de classe (comparado ao Guerreiro) |
| 4 | Boss Mythic rank A (solo) | Checar cooldowns longos (ultimate Hunter) | Dano por ciclo, uptime `Chuva de Flechas` | Ultimate causa <25% do HP do boss por ciclo |
| 5 | Evento raid com adds (T3 Hunter) | Garantir limite de traps (2) | Armadilhas simultâneas, TTK adds | Nunca >2 traps ativas; adds limpos em ≤30s |
| 6 | Dungeon selva com pet (T3 Beastmaster) | Avaliar Fúria Primal e Proteção Instintiva | Dano pet, mitigação dividida | Pet não morre; dano dividido ≈50/50 |
| 7 | Chefe Voadores (dragão custom) | Validar Rajada Predatória + alcance | DPS aéreo, foco gasto | Sem overcap de velocidade/alcance após RacesEffects |
| 8 | Expedição noturna (T3 Gloom) | Testar emboscada inicial | TTK do primeiro alvo, uptime invis | TTK < 5s com alvo >70% HP inicial |
| 9 | Missão escolta (grupo) | Checar utilidade do Compasso | Rastros corretos, sem friendly fire | Caminho mantido sem perder NPC |
|10| Boss final (T4 party) | Sinergia geral com outras classes | Dano total, uptime marca/armadilha/pet | Contribuição ≥20% DPS do grupo sem ultrapassar caps |

## Cenários PvP (10)
| # | Cenário | Objetivo | Métricas | Critério de Sucesso |
| - | ------- | -------- | -------- | ------------------- |
| 1 | Duelo vs Guerreiro (T1) | Testar burst controlado | Dano por rajada, uptime marca | Nenhum one-shot; duelo dura >12s |
| 2 | Duelo vs Ladino (T2) | Validar respostas a mobilidade | Uso de Armadilha, hits rajada | Armadilha deve acertar ≥1 vez por duelo |
| 3 | 2v2 (Hunter + Cleric) vs magos | Checar controle de área | Uptime rede, dano zona | Chuva de Flechas força deslocamento mas não mata instant |
| 4 | 3v3 (Beastmaster comp) | Avaliar dividir dano em focus fire | Dano recebido por Guardião, HP pet | Proteção Instintiva reduz pico <45% |
| 5 | Captura de ponto (Gloom) | Testar Caça Sombria em objetivo | Uptime invis aliados, dano zona | Inimigos recebem dano periódico previsível (telegraph visível) |
| 6 | Arena ranqueada (Hunter) | Garantir gating de itens | Verificação LuckPerms | Itens negados para jogadores sem permissão |
| 7 | Arena ranqueada (Beastmaster) | Checar regra sem montaria | Tentativa de montar pet | Montaria bloqueada, mensagem exibida |
| 8 | Skirmish 4v4 noite (Gloom) | Avaliar controle leve | Uso de medo/salto, uptime buff | Ninguém fica chain-CC >4s |
| 9 | Duelo cross-race (raça com velocidade extra) | Testar exaustão de resistência | Velocidade total, logs RacesEffects | Velocidade final ≤0.05 acima do base |
|10| Batalha massiva 10v10 | Monitorar cargas de foco em caos | Consumo/regeneração foco, scoreboard | Jogador nunca trava em 0 foco por >20s; HUD estável |

## Pós-Testes
- Ajustar coeficientes de dano/cooldown conforme logs.
- Revisar mensagens e placeholders caso existam inconsistências.
- Registrar feedback de jogadores-teste para QoL adicional.
