# Plano de Testes - Classe Bárbaro

## PvE (Horda/Chefe)
1. **Horda Zumbi T1** – Objetivo: Sustentação com Fúria ativa. Métricas: DPS médio 12, dano recebido < 35/s, uptime Fúria 60%.
2. **Chefe Esqueleto com Arqueiros** – Validar Percepção do Perigo. Métricas: mitigação de projéteis > 20%, stacks Rage consumidos 1-2.
3. **Golem de Ferro T2** – Comparar Ataque Temerário vs risco. Métricas: DPS burst 25+, dano recebido +15%, uptime mitigação Impetus.
4. **Blaze Arena T2** – Testar Totem Urso + Fúria. Métricas: resistência final <= 45%, cura externa necessária < 20% HP.
5. **Raid Pillager (5 ondas)** – Guardião Totêmico suporte. Métricas: uptime Totem Lobo 40%, dano aliado +12% vs chefe.
6. **Mini-chefe Bruxa** – Avaliar Fúria + Vigor fora de combate. Métricas: tempo de recuperação < 30s entre fases.
7. **Horda Piglin Brutal T3** – Berserker Carnificina. Métricas: cleave acerta >=5 alvos, lifesteal <=25% por golpe.
8. **Wither Skeleton Boss** – Storm Herald controle. Métricas: stun 2s garante janela, Olho do Furacão puxa 4 alvos.
9. **Dragão Ender Parcial** – Totem Phoenix revive aliado. Métricas: revive apenas 1x por aliado, cooldown respeitado 240s.
10. **Guardian Elder Subaquático** – Verificar eletro resistência. Métricas: Surto Galvânico acerta 3 ticks, dano eletro +20% quando vulnerável.

## PvP (Duelo/Arena)
1. **Duelo Bárbaro vs Guerreiro** – Garantir que Ataque Temerário não delete alvo full HP. Métricas: TTK > 6s.
2. **Duelo Berserker vs Assassino** – Validar risco +10% dano recebido. Métricas: dano sofrido aumentado visível em logs.
3. **Duelo Totem vs Mago Fogo** – Testar exaustão de resistência. Métricas: resistência final fogo <=45% mesmo com raça resistente.
4. **Duelo Storm vs Arqueiro** – Capa de Ventos reduz projéteis em 50%. Métricas: hits reduzidos >30% durante efeito.
5. **Arena 2v2 (Berserker + Healer)** – Observação: Carnificina não cura >25% por hit. Métricas: lifesteal logs.
6. **Arena 3v3 (Totem, Ranger, Paladino)** – Totem Phoenix bloqueado em flag PvP? Validar com `disable_totem_phoenix` ativo.
7. **Arena 3v3 (Storm, Mago, Rogue)** – Olho do Furacão não ultrapassa 8 blocos; puxão gradual. Métricas: deslocamento <4 blocos/s.
8. **Duelo Espadas Duplas** – Impetus Irrefreável respeita limite +0.05 velocidade. Métricas: velocidade final ≤ base+0.05.
9. **Skirmish 4v4** – Rage regen fora de combate. Métricas: stacks restaurados 1 a cada 6s após 12s sem dano.
10. **Batalha de Cerco** – Avaliar scoreboard e placeholders. Métricas: HUD atualiza Rage e CD principal em tempo real.

## Procedimentos Gerais
- Utilizar `/kit_barbaro` para setup inicial.
- Verificar permissões via LuckPerms antes de cada cenário.
- Registrar métricas em planilha de balanceamento para revisão semanal.
