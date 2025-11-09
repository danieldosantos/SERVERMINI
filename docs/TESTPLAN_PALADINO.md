# Plano de Testes – Classe Paladino

## Metodologia
- Ambiente: Servidor de testes 1.20.x com ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI e RacesEffects ativos.
- Build recomendada: carga inicial 3 Fé, equipamentos de teste fornecidos por `/kit_paladino`.
- Métricas coletadas: **DPS (dano por segundo)**, **TTK (tempo para derrotar alvo)**, **cura total**, **uptime de aura**, **controle aplicado**, **cargas de Fé restantes**.
- Ferramentas: `/paladin_cast <skill>`, Scoreboard `{faith_charges}`, logs do MythicMobs para dano, `/sk reload paladin_cmds` após ajustes.

## Cenários PvE (10)
| # | Objetivo | Setup | Métricas | Critérios de Aceite |
|---|----------|-------|----------|---------------------|
| 1 | Validar regeneração de Fé fora de combate | Paladino T1 sem juramento | Tempo para regenerar 1 carga, ação de HUD | ≤35s para recuperar 1 carga; HUD mostra valores corretos |
| 2 | Golpe Divino + ataque corpo a corpo | Zumbi com 200 HP | DPS, carga consumida | +100% dano aplicado e consumo de 1 Fé |
| 3 | Imposição de Mãos em aliado com veneno | Jogador aliado T1 | Cura total, debuff removido, cargas restantes | Remove 1 debuff leve e aplica recarga de 180s |
| 4 | Canalizar Voto aura base | 3 aliados próximos | Uptime de mitigação, aura timer | Aura encerra em 30s, mitigação ≤10% e HUD mostra contagem |
| 5 | Golpe Sagrado (Devoção) | Esqueleto Elite 400 HP | Dano bônus, consumo de Fé | Incremento 100%→140% conforme tier |
| 6 | Escudo Devoto vs dano mágico | Bruxa lança veneno | Dano refletido, exaustão racial | 25% reflexo ativo por 6s, Fadiga se bônus racial >30% |
| 7 | Milagre da Fé | Grupo 4 aliados com 50% HP | Cura total em 6s, imunidade medo/charme | Aliados chegam a ≥95% HP, efeitos imunes |
| 8 | Juramento de Inimizade + Execução Divina | Guardião 1000 HP | TTK, threshold execução | Execução apenas <25% HP, sem one-shot acima |
| 9 | Aura da Natureza (Anciões) + resistência racial | Aliado elfo com resistência elemental | Mitigação combinada | Total ≤45%, mensagem de diminishing |
|10 | Harmonia Primordial | 5 aliados em arena PvE | Uptime, cura recebida | Zona ativa 15s, +25% cura e -20% dano verificados |

## Cenários PvP (10)
| # | Objetivo | Setup | Métricas | Critérios |
|---|----------|-------|----------|----------|
| 1 | Consumo de Fé em duelos 1v1 | Paladino T1 vs Lutador T1 | Cargas restantes, DPS | Após 60s, ≥1 carga regenerada fora de combate |
| 2 | Imposição de Mãos + Golpe Divino combo | Paladino T2 vs Bárbaro T2 | TTK, cura | Cura ≤25% do dano recebido, sem reset injusto |
| 3 | Passo do Redentor evasão | Paladino Vingança vs Arqueiro | Distância percorrida, controle sofrido | Dash cobre até 12 blocos e ignora CC durante 2s |
| 4 | Cadeia do Pecado puxando jogador | Paladino Vingança vs Mago | Pull, lentidão | Alvo puxa ~4 blocos e aplica -40% velocidade por 6s |
| 5 | Execução Divina limitação arena | Arena PvP flag PvP | Execução log | Bloqueio caso alvo >25% HP, sem matar instantaneamente |
| 6 | Aura de Pureza em grupo PvP | Paladino Devoção + 3 aliados | Mitigação, stacks com raças | Não ultrapassa 45% total, remove efeitos leves |
| 7 | Luz Orientadora vs Ladino invisível | Paladino Devoção vs Ladino | Visibilidade, crítico aliado | Inimigo recebe glowing 8s e aliados +10% crítico |
| 8 | Asas de Safira kite | Paladino Anciões vs Guerreiro | Velocidade extra, tempo de voo | Bônus +0.05 e slow falling por 8s, sem exceder cap |
| 9 | Harmonia Primordial em captura | Paladino Anciões vs 2 inimigos | Cura recebida, dano sofrido | Bônus +25% cura e -20% dano permanecem 15s |
|10 | Canalizar Voto uptime total | Paladino qualquer juramento | Ciclo 3 lutas | Uptime ≤60% ao longo de 5 minutos |

## Observações de Balanceamento
- Ajustar `paladin_cmds.sk` se logs indicarem regeneração fora de combate muito rápida/lenta.
- Monitorar que `paladin_vinganca_exec` não ultrapasse políticas de dano verdadeiro (10 de dano verdadeiro apenas abaixo de 25% HP).
- Confirmar através de `/lp info` que itens MythicMobs só funcionam com grupos de juramento apropriados.
