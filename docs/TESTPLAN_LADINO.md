# Plano de Testes – Classe Ladino

## Metodologia
- Servidor de staging com ProSkillAPI, MythicMobs, Skript, PlaceholderAPI, LuckPerms e RacesEffects.
- Ativar HUD específica (`rogue_stamina`, `rogue_sneak_cd`, `rogue_advantage`, `rogue_mobility`).
- Utilizar `/kit_ladino` após cada reset; limpar buffs, Estamina e marcadores com `/cmi clearinv` ou equivalente.
- Registrar métricas: DPS médio, tempo para matar (TTK), gasto/regen de Estamina, uptime de vantagem (%), taxa de sucesso de Golpe Furtivo.
- Monitorar limites globais: velocidade adicional ≤0.05, mitigação ≤45%, controles ≤4s, cura ≤25% do dano.

## Cenários PvE (10)
1. **T1 – Emboscada contra 5 Saqueadores**: usar Furtividade Tática + Golpe Furtivo. Esperado: nenhum one-shot; Estamina >20 após rotação.
2. **T1 – Mini-boss Zumbi Blindado**: testar Golpe Furtivo vs alvo sem vantagem (deve falhar) e com Hookshot assistido (Thief).
3. **T2 – Patrulha de Arqueiros**: validar Evasão (-50% projéteis) e medir dano recebido (<55% HP).
4. **T2 – Golem Lento**: Assassino com Marcador de Alvo + Emboscada. Confirmar Hemorragia 6%/s por 4s em log.
5. **T3 – Horda de Arachnids**: Bomba de Fumaça cobrindo 6 blocos; medir uptime <60% e camuflagem de aliados.
6. **T3 – Guarda Élite**: Swashbuckler duelando 1 elite. Desafio do Duelista deve permitir Golpe Furtivo sem outras fontes de vantagem.
7. **T3 – Defesa de Objetivo**: Thief ativando Arrombador Mestre; verificar redução de 25% em CDs de mobilidade de aliados no raio (logs `skillapi cooldown`).
8. **T4 – Boss Humanoide**: Execução Calculada apenas <30% HP. Se usado acima, aplicar -15% resistência por 6s; confirmar via dano aliado.
9. **T4 – Dragão Móvel**: Passo de Dança + Evasão para sobreviver a ataques em cone; velocidade total ≤0.05.
10. **T4 – Cerco Prolongado (5 min)**: Rotacionar ultimates (Assassino/Thief/Swash) mantendo Estamina ≥15 e sem travar CDs; monitorar `rogue_sneak_cd`.

## Cenários PvP (10)
1. **Duelo T1 Ladino vs Guerreiro**: medir TTK ~18–24s. Golpe Furtivo não deve ativar sem vantagem confirmada.
2. **Duelo T1 Ladino vs Monge**: verificar Evasão reduzindo dano de projéteis de dardo em 50%.
3. **Duelo T2 Assassino vs Paladino**: Execução Calculada não finaliza >30% HP; confirmar debuff de -15% resistência em log.
4. **Duelo T2 Thief vs Ranger**: Hookshot puxa apenas 3 blocos e falha em bosses/dummies pesados.
5. **Duelo T2 Swashbuckler vs Bárbaro**: Passo de Dança concede ~20% esquiva frontal; sem acumular com Evasão (>60% uptime).
6. **2v2 (Assassino + Clérigo) vs (Paladino + Mago)**: Toxina Preparada reduz cura recebida em 10%; medir no log do Paladino.
7. **2v2 (Thief + Guerreiro) vs (Ranger + Druida)**: Arrombador Mestre reduz CDs de mobilidade aliados (Hookshot/Pique ≤75% recarga).
8. **3v3 (Swashbuckler + Bardo + Paladino) vs (Assassino + Feiticeiro + Monge)**: Clímax do Duelista lifesteal ≤25% do dano; confirmar via scoreboard de cura.
9. **Arena Livre com 6 jogadores**: medir `rogue_sneak_cd` individual por alvo; sem Golpe Furtivo repetido no mesmo jogador <6s.
10. **Capture the Flag**: Thief usa Hookshot + Pique + Mãos Velozes para mobilidade. Confirmar velocidade total ≤0.05 e custo de Estamina respeitando Finesse.

## Critérios de Aprovação
- Zero erros de carregamento em console para ProSkillAPI, MythicMobs e Skript (`rogue_cmds.sk`).
- Golpe Furtivo respeita vantagens e CD por alvo (logs de `rogue.sneak.cd`).
- Estamina nunca negativa; regen 4/s em combate, 7/s fora (monitorar HUD).
- LuckPerms impede uso de itens/skills fora da subclasse autorizada.
- Controles respeitam limite de 4s e aplicam imunidade decrescente (verificar Bomba de Fumaça e Passo de Dança).
- Sem one-shot em PvP; execuções apenas <30% HP.
