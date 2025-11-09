# Classe Bárbaro (Minecraft + D&D 5e)

## Visão Geral
O Bárbaro é um frontliner híbrido DPS/Tank que alterna janelas explosivas de dano com mitigação controlada. Ele usa **Estamina** como recurso principal (100/120/140/160 por tier) e acumula **Fúria** em pilhas ao sofrer ou causar dano corporal. O limite de velocidade adicional permanece em **+0.05**, a mitigação total em **45%** e o roubo de vida em **25%**, alinhado aos caps globais do servidor.

## Recursos
- **Estamina**: regenera 4/s em combate e 10/s fora de combate. Skills custam 15–35 pontos.
- **Fúria (rage_stacks)**: até 3 cargas nos tiers 1/2 e 4 nos tiers 3/4. Consumida para janelas de poder como Fúria, Totens e Ultimates. Decai gradualmente fora de combate.
- **HUD**: scoreboard/PlaceholderAPI exibem Estamina atual, Fúria disponível e principais recargas. `/kit_barbaro` inicializa itens focais e ativa a HUD.

## Habilidades de Classe
- **Fúria**: +15/20/25/30% dano corpo a corpo e +10/12/15/18% resistência física por 20s (consome 1 Fúria, 25 Estamina, CD 90s).
- **Ataque Temerário**: reforça penetração ao agachar ou chance crítica em pé (+15%) por 8s, recebendo +10% dano (20 Estamina, CD 60s).
- **Investida Brutal**: avanço de 5 blocos com 140% dano e 20% mitigação por 4s ao conectar (25 Estamina, CD 45s).
- **Instinto Perigoso** (passivo): ao sofrer dano em área reduz explosões/projéteis em 25% por 6s (CD interno 20s).
- **Defesa sem Armadura** (passivo): bônus defensivo leve enquanto fora de armadura pesada, respeitando o cap.
- **Vigor Selvagem** (passivo): cura 1% HP/s por 6s após 6s sem tomar dano (CD interno 20s).

## Caminhos Primitivos
### Berserker
Explosões arriscadas, refletores e mobilidade curta.
- **Grito Bestial**: -10% dano inimigo em 6 blocos +10% velocidade de ataque (1 Rage).
- **Retaliação Frenética**: devolve 30% do dano recebido (cap 200) por 8s.
- **Carnificina**: 3 cleaves de 120%, gera 1 Rage se acertar 3+ alvos.
- **Ímpeto Irrefreável**: remove lentidão, +0.04 velocidade por 10s.
- **Carnificina Implacável**: ultimate de 15s com cleave contínuo e 25% lifesteal (1 Rage, CD 210s).

### Guardião Totêmico
Totens situacionais para aura, marca e suporte.
- **Totem do Urso**: -35% dano físico aliados 5b/10s (1 Rage).
- **Totem da Águia**: +1 alcance melee e visão 15s.
- **Totem do Lobo**: marca alvo por 12s, aliados causam +12% dano.
- **Totem do Tigre**: +30% velocidade de ataque 8s.
- **Totem da Fênix**: aura 8b/12s com revive (40% HP) e -20% dano recebido (1 Rage, CD 240s).

### Arauto da Tempestade
Controle de zona e mitigação de projéteis.
- **Martelo do Trovão**: 140% dano + atordoamento 2s (CD 45s).
- **Surto Galvânico**: 3 descargas automáticas (40% cada) em 8s (CD 60s).
- **Capa de Ventos**: -50% projéteis e empurra inimigos ao ativar (CD 90s).
- **Caldeirão da Tempestade**: zona 6b/10s causando 50%/2s (CD 120s).
- **Olho do Furacão**: vórtice 8b/12s com +20% vulnerabilidade elétrica (1 Rage, CD 210s).

## Integração de Plugins
- **ProSkillAPI**: classe, recursos, skills e tiers definidos em `plugins/ProSkillAPI/...`.
- **MythicMobs**: itens focais com NBT de classe em `plugins/MythicMobs/Items/barbarian_items.yml`.
- **Skript**: comandos rápidos, HUD, reflexos, lifesteal e efeitos auxiliares em `plugins/Skript/scripts/barbarian_cmds.sk`.
- **LuckPerms**: grupos `class_barbarian` e subclasses em `plugins/LuckPerms/barbarian_groups.txt`.
- **PlaceholderAPI/Scoreboard**: controla HUD de Estamina/Fúria/CD.
- **RacesEffects**: leitura para sinergias e retornos decrescentes (sem escrita automática).

## Dicas de Uso
- Abra combos com **Investida Brutal** + **Ataque Temerário**, ativando **Fúria** apenas quando tiver 1–2 cargas reservas para Totens/Ultimates.
- Gerencie Estamina: uptime alvo abaixo de 60%; alternar janelas de dano e mitigação.
- Em PvP, priorize controles curtos (<3s) e use **Capa de Ventos** ou **Totem do Urso** para negar projéteis.
- Revise caps antes de combinar buffs raciais para evitar perda por exceder limites.

## Limitações Conhecidas
- Totem da Águia usa comando `/attribute` e pode conflitar com outros modificadores de alcance; reverte após expirar.
- Totem da Fênix revive apenas uma vez por alvo a cada ativação.
- Olho do Furacão aplica vulnerabilidade elétrica genérica (não distingue tipos de dano elétrico externos).
- Scripts assumem presença do PlaceholderAPI e acesso ao comando `skillapi`. Ajuste se o ambiente mudar.
