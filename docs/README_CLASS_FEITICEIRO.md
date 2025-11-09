# Classe Feiticeiro (Sorcerer)

## Visão Geral
O Feiticeiro é o conjurador inato focado em dano explosivo à distância e pequenas janelas de controle. A classe utiliza **Pontos de Feitiçaria (SP)** para alterar feitiços via metamagia, enquanto mantém mobilidade moderada e defesas leves dentro dos limites globais do projeto (mitigação ≤45%, velocidade bônus ≤0,05, lifesteal ≤25%).

- **Função primária:** burst arcano e controle de zona leve.
- **Recurso especial:** `sorcery_points` (3 no tier inicial, +1 por tier, regeneração fora de combate a cada 12s via Skript/RacesEffects).
- **Metamagia:** alterna efeitos temporários (rapidez, alcance, dano extra, conversão elemental, caos, canal elétrico) com custo de 1–2 SP e lockout mínimo de 30s.
- **Integrações chave:** ProSkillAPI (classes/skills), MythicMobs (focos), Skript (HUD e comandos), LuckPerms (gating), PlaceholderAPI (HUD), RacesEffects (resistências).

## Progresso por Tier
| Tier | Destaques | Recursos | Cooldowns principais |
| ---- | --------- | -------- | --------------------- |
| T1 (nível 1) | Raio Arcano, Onda Arcana, Blink Arcano, metamagias básicas | 3 SP, mana 150 | Raio (5s), Onda (45s), Blink (45s) |
| T2 (nível 5) | Acesso às origens iniciais / habilidades 2 | 4 SP, novas sinergias | Escamas Manifestas 75s, Fluxo Instável 90s, Pulso Eólico 60s |
| T3 (nível 11) | Buffs ofensivos fortes | 5 SP, metamagias adicionais | Coração Elemental 105s, Véu Probabilístico 105s, Nuvem de Trovões 90s |
| T4 (nível 17) | Ultimates de 240s, controle zonal amplo | 6 SP, +velocidade 0,03 | Avatar Dracônico, Tempestade Caótica, Olho da Tempestade |

## Metamagias
| Metamagia | Custo SP | Efeito | Lockout |
| --------- | -------- | ------ | ------- |
| Rápida | 1 | Próxima skill instantânea (CD mínimo 50% base) | 30s |
| Alcance Estendido | 1 | Próxima skill recebe +50% alcance/área | 30s |
| Potencializada | 2 | +15% dano final na próxima skill | 30s |
| Conversão Elemental (Dracônica) | 1 | Converte dano para elemento do sangue dracônico ativo | 30s |
| Dado do Caos (Selvagem) | 1 | +10% dano e rolagem de bônus caótico | 30s |
| Canal Elétrico (Tempestade) | 1 | Próxima skill deixa rastro elétrico por 4s | 30s |

## Origem Dracônica
- **Identidade:** dano elemental consistente, mitigação adicional e ultimate de aura.
- **Skills chave:** Sopro Dracônico (cone 6 blocos), Escamas Manifestas (20% mitigação, 15% resistência elemental), Asas Etéreas (+0,05 velocidade planar), Coração Elemental (+20% dano elemental), Avatar Dracônico (aura 6 blocos / 80% dps, 15s, CD 240s).
- **Itens MythicMobs:** DraconicFocus, DraconicScaleCharm, DraconicWingTalisman, DraconicHeartGem, DraconicCrown.
- **Restrições raciais:** RacesEffects garante retornos decrescentes ao empilhar resistências.

## Magia Selvagem
- **Identidade:** efeitos imprevisíveis com risco controlado e ferramentas de suporte.
- **Skills:** Surto Caótico (projétil 150%), Fluxo Instável (-20% CDs 10s com penalidade 12%), Reversão Quântica (rerrola debuffs), Véu Probabilístico (30% negação por 8s), Tempestade Caótica (zona 8 blocos 12s, 70% dps, CD 240s).
- **Itens:** WildMagicOrb, WildMagicRod, WildMagicHourglass, WildMagicCape, WildMagicNexus.
- **Mitigação:** respeita limite de 45% mesmo com véu + buffs raciais.

## Magia da Tempestade
- **Identidade:** mobilidade aérea, empurrões e conversão de dano elétrico.
- **Skills:** Descarga Tempestuosa (linha 140%, raiz 2s), Pulso Eólico (knockback 5 blocos + voo 4s), Nuvem de Trovões (campo 6 blocos, 60%/s), Condutor Vivo (+20 mana por acerto elétrico recebido), Olho da Tempestade (tempestade direcional 12s, 100% dano, +20% velocidade, CD 240s).
- **Itens:** StormSorceryFocus, StormSorceryCharm, StormSorceryOrb, StormSorceryStaff, StormSorceryCrown.
- **Cap de velocidade:** +0,05 total respeitado (via Skript).

## Comandos (Skript)
- `/kit_feiticeiro`: entrega focos base, reseta HUD/SP e limpa combate.
- `/metamagia <tipo>`: ativa metamagias listadas.
- `/blink`, `/sopro_draconico`, `/escamas_draconicas`, `/surto_caotico`, `/tempestade_caotica`, `/tempestade_olho`, `/descarga_tempestuosa`.
- Admin: `/give_sp <jogador> <quantia>`, `/set_origin <jogador> <draconic|wild|storm>`.

## HUD e PlaceholderAPI
- Objetivo `sorcerer_sp`: SP atual.
- Objetivo `sorcerer_metamagic`: índice da metamagia armada (0=nenhum).
- Objetivo `sorcerer_major_cd`: maior cooldown restante (placeholder `%skills_sorcerer_highest_cd%`).
- Action bar exibe SP atual/max e metamagia ativa.

## Integração com RacesEffects
- Skript consulta placeholders `%raceseffects_resistance_<elemento>%` para limitar resistências combinadas.
- Escamas Manifestas e efeitos de tempestade aplicam avisos quando ultrapassam 45%.
- Variações elementais do Sopro/Tempestades respeitam afinidades raciais sem anular fraquezas.

## Itens de Teste e Kits
- Focos base (ArcaneFocus, ArcaneWaveCrystal, ArcaneBlinkCharm) disponíveis via `/kit_feiticeiro` ou kit CMI/Essentials.
- Itens exclusivos por origem utilizam tag NBT `class-lock` para garantir gating por LuckPerms.

## Regras de Balanceamento Respeitadas
- Cooldowns principais ≥45s e ultimates 240s.
- Metamagias com custo 1–2 SP e lockout 30s.
- Áreas padrão 6–8 blocos, velocidade total +0,05.
- Nenhuma skill finaliza alvo >30% HP em PvP (verificações via Skript/PlaceholderAPI).
- Recuperação de SP apenas fora de combate (delay mínimo 8s).

## Próximos Passos
- Ajustar efeitos visuais (MythicMobs) conforme necessidade do servidor.
- Configurar automações de kit via CMI/EssentialsX se utilizados.
- Validar placeholders RacesEffects em produção.
