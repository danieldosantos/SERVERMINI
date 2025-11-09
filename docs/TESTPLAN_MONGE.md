# Test Plan - Classe Monge

Este plano cobre 10 cenários PvE e 10 cenários PvP para validar balanceamento, consumo de Ki, cooldowns e integração com sistemas externos.

## Métricas Gerais
- **Tempo para matar (TTK):** medido em segundos ou número de rotações de habilidades.
- **Dano Médio por Segundo (DPS):** observado em alvo de treinamento MythicMobs com log do ProSkillAPI.
- **Mitigação Efetiva (ME%):** comparar dano recebido com/sem habilidades ativas.
- **Ki Consumido/Regenerado:** registrar pontos gastos/ganhos por rotação.
- **Controle:** duração efetiva de CC (empurrões, cegueira, imobilização).

## PvE Scenarios
| # | Tier | Local/Teste | Passos Principais | Métricas Esperadas |
|---|------|-------------|-------------------|--------------------|
| 1 | T1 | Campo de treino (dummy 500 HP) | Usar `/rajada` + auto ataques. | DPS 15–18, consumo 1 Ki, cooldown 45s respeitado. |
| 2 | T1 | Arena goblin arqueiro | Alternar Passo do Vento para evitar flechas. | Mitigação projétil ~20% via passivo, cap de velocidade mantido. |
| 3 | T1 | Suporte aliado NPC | `/maos_curativas` em aliado ferido 50%. | Cura 10% + remoção debuff, cd individual 60s. |
| 4 | T2 | Mini-chefe humanoide | Abrir com Palmada Desestabilizadora → Onda Interior. | Brecha -10% e redução 15% confirmadas, Ki restante ≥3. |
| 5 | T2 | Evento defesa aldeia | Ativar Ritmo Harmonioso durante pausa. | +2 Ki em 6s, cura 6%, sem ultrapassar Ki cap. |
| 6 | T3 | Caverna com mobs rápidos | Usar Caminho do Zéfiro e Salto do Vendaval para reposicionar. | Velocidade <= cap +0.05, sem ignorar limites raciais. |
| 7 | T3 | Magus Elemental (projéteis) | Invocar Muralha Aquática para proteger grupo. | Redução projétil ~60% por 8s, sem bloquear corpo a corpo. |
| 8 | T3 | Caçada furtiva | Passo das Sombras + Névoa Silenciosa. | Teleporte 12 blocos somente em baixa luz, invis 4s, cd respeitado. |
| 9 | T4 | Chefe dragão | Sopro Tranquilo durante rajada de fogo. | Aura 6 blocos, ME total aliados ≤45%, cura 5%/s, consumo 3 Ki. |
|10 | T4 | Cerco elemental | Avatar Elemental ciclo completo. | Fases 5s dano→5s defesa→5 ticks cura 4%/s, cd 240s.

## PvP Scenarios
| # | Tier | Setup | Passos Principais | Métricas Esperadas |
|---|------|-------|-------------------|--------------------|
| 1 | T1 | Duelo vs Guerreiro | Abrir com Rajada + esquiva. | Burst <= 45% HP alvo, controle moderado. |
| 2 | T1 | 2v2 suporte | Alternar Mãos Curativas entre aliados. | Cura média 10% por uso, sem spam (cd 60s). |
| 3 | T2 | Duelo vs Ladino rápido | Uso defensivo de Passo do Vento + Palmada. | Knockback 4 blocos, reduz dano ladino 10%. |
| 4 | T2 | Controle de ponto | Onda Interior em área. | Redução dano inimigo 15% por 8s sem stack. |
| 5 | T3 | Emboscada Monge Sombra vs Mago | Passo das Sombras → Cutilada Sombria. | DPS burst não finaliza alvo full HP; cegueira 3s confirmada. |
| 6 | T3 | Defesa bandeira | Correntes Umbráticas em portador. | Imobiliza 3s, mitigação -10%, cd 75s. |
| 7 | T3 | Mobilidade Elemental | Salto do Vendaval em parkour PvP. | Distância 10 blocos, queda lenta 6s, sem ultrapassar cap. |
| 8 | T4 | Teamfight 4v4 | Sopro Tranquilo vs ultimates inimigas. | Mitigação ≤40%, cura 5%/s, consumo 3 Ki; medir ME% agregado. |
| 9 | T4 | Dupla Sombra | Domínio da Penumbra + aliado. | Sombra viva +70% dano, duração 12s (ou 8s com 2 Ki fallback). |
|10 | T4 | Conflito Elemental | Avatar Elemental vs Paladino. | Sequência completa sem ultrapassar 45% mitigação total; curas 4%/s. |

## Validações Adicionais
- **Integração LuckPerms:** aplicar `/lp user <player> parent add class_monk.shadow` e verificar acesso apenas às skills correspondentes.
- **Itens:** tentar usar itens focais sem permissão para confirmar bloqueio via MythicMobs.
- **Regen de Ki:** sair de combate e medir 1 Ki a cada 30s; receber dano reinicia delay de 10s.
- **Placeholder/HUD:** confirmar actionbar exibindo `{Ki}/{KiMax}` e cooldowns atualizados.
