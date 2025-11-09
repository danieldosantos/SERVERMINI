# Plano de Testes - Classe Bárbaro

## Premissas
- Servidor rodando com ProSkillAPI, MythicMobs, Skript, PlaceholderAPI e LuckPerms ativos.
- Jogador com permissões adequadas (`group.class_barbarian` e subclasses conforme teste).
- HUD inicializada via `/kit_barbaro`.
- Caps globais: velocidade +0.05, mitigação 45%, lifesteal 25%.

## Testes PvE (10 cenários)
1. **Fúria + Ataque Temerário em mob individual**: verificar consumo de 25 Estamina + 1 Rage, buffs por 20s e +10% dano recebido durante 8s.
2. **Investida Brutal em alvo único**: confirmar dash de ~5 blocos, dano 140% e mitigação +20% por 4s apenas se conectar.
3. **Instinto Perigoso**: posicionar-se em explosão de Creeper; confirmar redução de dano por 6s e cooldown interno de 20s.
4. **Regeneração fora de combate**: após 6s sem dano, garantir cura de 1% HP/s por 6s e que o efeito não reaplica antes de 20s.
5. **Berserker - Carnificina**: acertar 3 mobs; verificar cleaves, contagem via Skript e geração de 1 Rage.
6. **Berserker - Retaliação Frenética**: receber golpes fortes, checar reflexo de 30% (máx. 200) e expiração após 8s.
7. **Totem do Urso**: posicionar aliados NPCs em 5 blocos e validar redução de 35% dano físico via comparativo.
8. **Totem da Fênix**: derrubar aliado dentro da aura; garantir revive único com 40% HP e mitigação -20% durante 12s.
9. **Storm - Caldeirão da Tempestade**: medir zona 6 blocos, dano periódico (50%/2s) e duração total 10s.
10. **Storm - Olho do Furacão**: manter mobs dentro do vórtice, verificar puxão via Skript e dano aumentado (+20%) por habilidades elétricas.

## Testes PvP (10 cenários)
1. **Fúria + Ataque Temerário x jogador**: validar que buffs seguem caps de mitigação/velocidade e que alvo não sofre one-shot.
2. **Retaliação Frenética**: duelando outro jogador, conferir reflexo limitado a 200 por golpe e que habilidades em área não ultrapassam cap.
3. **Carnificina Implacável**: testar lifesteal de 25% e duração de 15s; garantir cooldown 210s.
4. **Ímpeto Irrefreável**: aplicar efeito de lentidão no bárbaro e confirmar limpeza imediata + velocidade extra por 10s respeitando +0.05.
5. **Totem da Águia**: verificar alcance aumentado (comando /attribute) e retorno ao valor base após expirar; assegurar ausência de stack permanente.
6. **Totem do Lobo**: marcar adversário, conferir glow 12s e aumento de dano de aliados +12% somente contra o alvo.
7. **Totem do Tigre**: testar velocidade de ataque +30% sem ultrapassar limite global de velocidade.
8. **Totem da Fênix em arena**: confirmar revive único e que fora da aura o efeito não dispara.
9. **Capa de Ventos**: enfrentar arqueiro; observar empurrão inicial e redução de 50% em projéteis por 10s.
10. **Olho do Furacão**: em duelo múltiplo, garantir que inimigos próximos recebem dano extra de habilidades elétricas e que controle não excede 12s/8b.

## Pós-Testes
- Validar HUD após cada cenário (`/scoreboard objectives get`).
- Verificar log do servidor para garantir ausência de erros em YAML/Skript.
- Ajustar parâmetros conforme feedback de dano médio versus uptime alvo (<60%).
