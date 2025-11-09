# Classe Guerreiro (Fighter)

## Visão Geral
- **Função:** DPS sustentado / off-tank com foco em controle tático.
- **Recurso:** Vigor (sem mana base) e integração com mana para Cavaleiro Élfico.
- **Pilares:** recargas menores via Surto de Ação, auto-sustentação com Segundo Fôlego e passivas condicionais (<60% uptime).
- **Integrações:** PlaceholderAPI para HUD de recargas, RacesEffects para limites de resistência, LuckPerms para gating de itens/skills.

## Atributos por Tier
| Tier | Vida (HP) | Defesa Física | Resist. Knockback | Velocidade |
|------|-----------|----------------|-------------------|------------|
| T1 (Nível 1) | 34 | +6 | +0.05 | +0.00 |
| T2 (Nível 5) | 58 | +9 | +0.07 | +0.01 |
| T3 (Nível 11) | 92 | +13 | +0.09 | +0.015 |
| T4 (Nível 17) | 126 | +16 | +0.10 | +0.02 |

- **Cap de velocidade total:** +0.05 acima do base.
- **Cap de resistência total:** 45% (após multiplicadores raciais aplicar retornos decrescentes).

## Skills Base
| Skill | Tipo | CD | Descrição |
|-------|------|----|-----------|
| Surto de Ação | Ativa | 120s | Reseta CDs menores e concede +15% velocidade de ataque por 6s. Não afeta ultimates. |
| Segundo Fôlego | Ativa | 180s | Cura 12% instantâneo + 8% em 6s. Respeita limite de cura por golpe. |
| Treinamento Marcial | Passiva | — | Ataques básicos aplicam 12% penetração por 4s (ICD 8s). |
| Postura Sólida | Passiva | — | Após bloquear, -18% dano por 6s (ICD 12s, uptime 50%). |

### HUD / PlaceholderAPI
- `%skills_fighter_action_surge_cd%` → maior recarga base.
- `%skills_fighter_second_wind_cd%` → CD de Segundo Fôlego.
- `%skills_fighter_major_cd%` → CD do ultimate da subclasse equipada.
- `%skills_combat_state%` → verificação fora de combate (Skript `/fighter_ooc`).
- `%skills_blocking_state%` → sincronização de Postura Sólida.

## Subclasses (Arquétipos)
### Campeão (Champion)
- **Identidade:** críticas aumentadas, mitigação consistente.
- **Itens-Chave:** Lâmina do Coliseu, Escudo do Triunfo, Estandarte do Gladiador, Grevas do Coliseu, Coroa do Triunfo.
- **Skills:** Golpe Superior (45s), Resiliência Inabalável (75s), Desafio do Gladiador (90s), Salto do Coliseu (60s), Triunfo Inesquecível (210s).
- **Notas:** buffs respeitam caps globais; ultimate com uptime <50%.

### Mestre de Batalha (Battlemaster)
- **Identidade:** controle situacional, suporte aliado.
- **Itens-Chave:** Chicote Desarmante, Manual de Manobras, Escudo Estratégico, Estandarte de Comando, Alabarda da Vitória.
- **Skills:** Golpe Desarmante (45s), Manobra Precisa (40s), Postura Defensiva (75s), Comando Tático (90s), Giro da Vitória (210s).
- **Notas:** debuffs usam Skript para reduzir dano/cooldowns sem burlar resistências raciais.

### Cavaleiro Élfico (Eldritch Knight)
- **Identidade:** híbrido marcial + magia elemental.
- **Itens-Chave:** Lâmina Arcana, Foco Arcano, Runa de Dobra, Sigilo de Baluarte, Códice Tempestuoso.
- **Skills:** Lâmina Arcana (45s, 20 mana), Escudo Arcano (60s), Teleporte Tático (75s), Baluarte de Energia (90s), Tempestade da Lâmina (210s).
- **Notas:** utiliza mana padrão fora de combate; resistências respeitam retornos decrescentes via Skript.

## Comandos (Skript)
- `/kit_guerreiro` – entrega itens focais e inicializa placares (LuckPerms: `class_fighter`).
- `/surto`, `/folego`, `/postura` – atalho para skills base.
- `/manobra` – Manobra Precisa (Battlemaster).
- `/champ_golpe`, `/champ_triuno` – habilidades chave do Campeão.
- `/battle_comando`, `/battle_giro` – suporte ofensivo Battlemaster.
- `/eld_escudo`, `/eld_tempestade`, `/eld_elemento <fogo|gelo|raio>` – Cavaleiro Élfico.
- `/fighter_ooc on|off` – admin (força estado fora de combate).

## Sinergias com Raças (RacesEffects)
- Leitura de `%raceseffects_*%` para limitar stacking de resistências.
- Buffs físicos/elementais aplicam **exaustão de resistência** via `fighter_resist_diminish` e `fighter_resistance_exhaust`.
- Não remove fraquezas raciais; apenas mitiga até o cap global de 45%.

## Integração com LuckPerms
- Grupo base `class_fighter`; subgrupos herdam e liberam itens/skills.
- Permissões para comandos Skript e itens MythicMobs divididos por arquétipo.

## Itens MythicMobs (Resumo)
- Cada item possui `PersistentData.classlock` exigindo o grupo correspondente.
- Ativa skills via `skill{s=...}` em cliques apropriados.
- Uso combinado com `fighter_cmds.sk` garante que jogadores sem permissão recebam feedback temático.

## Limites e Balanceamento
- Cooldowns principais ≥45s, ultimates 210s.
- Cura por golpe ≤25% (Segundo Fôlego distribui em duas fontes).
- Áreas máximas 6–8 blocos (Desafio do Gladiador, Comando Tático, Baluarte de Energia).
- Execuções apenas abaixo de 30% HP (Golpe Superior condiciona via placeholder).

## Fluxo de Testes
Consulte `docs/TESTPLAN_GUERREIRO.md` para cenários PvE e PvP, métricas-alvo de DPS/TTK e verificações de caps.
