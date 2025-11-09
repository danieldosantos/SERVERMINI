# Classe Ladino (Rogue)

## Visão Geral
- **Função:** DPS oportunista com controles leves e mobilidade acrobática.
- **Recurso:** Estamina (pool 90/110/130/150) com regeneração de 4/s em combate e 7/s fora de combate.
- **Pilares:** furtividade de curta duração, Golpe Furtivo condicionado, mitigação situacional (≤45%) e velocidade adicional limitada a +0.05.
- **Integrações:** PlaceholderAPI para HUD (`{stamina}`, `{sneak_cd}`, `{duelist_mark}`, `{cooldown_next}`), MythicMobs para itens focais, Skript para lógica de Golpe Furtivo e dummies de teste, LuckPerms para gating de classe/subclasse, RacesEffects para leitura de DR racial.

## Atributos por Tier
| Tier | Vida (HP) | Estamina | Defesa Física | Velocidade | Chance Crítica |
|------|-----------|----------|----------------|------------|----------------|
| T1 (Nível 1) | 28 | 90 | +4 | +0.00 | 12% |
| T2 (Nível 5) | 46 | 110 | +5 | +0.01 | 14% |
| T3 (Nível 11) | 68 | 130 | +7 | +0.02 | 16% |
| T4 (Nível 17) | 94 | 150 | +9 | +0.03 | 18% |

- **Regra de Estamina:** custo mínimo 1 após reduções. Zerado → Skript aplica “Exaustão Física” (-20% dano/velocidade por 4s).
- **Caps globais:** velocidade adicional ≤0.05, mitigação total ≤45%, controles ≤4s com imunidade decrescente, cura por golpe ≤25%, áreas 6–8 blocos.

## Skills Base
| Skill | Tipo | CD | Custo | Descrição |
|-------|------|----|-------|-----------|
| Furtividade Tática | Ativa | 60s | 25 | 6s de furtividade (2s invis + 4s camuflagem, -60% ruído). Cancela ao atacar/receber dano. |
| Evasão | Ativa | 75s | 25 | 4s com -50% dano de área/projéteis e +0.03 velocidade (cap de mitigação/velocidade respeitado). |
| Pique Acrobático | Ativa | 45s | 20 | Avanço de 6 blocos; próximo golpe em 3s recebe +10% penetração. |
| Golpe Furtivo | Ativa | — (6s/alvo) | 15 | Buffa próximo ataque em até 3s se houver vantagem. Bônus +70/+110/+150/+190%. |
| Mãos Leves | Passiva | — | — | +15% interação com cordas/ganchos/fechaduras. Usa MythicMobs/Skript. |
| Finesse | Passiva | — | — | -10% custo de Estamina em mobilidade (mínimo 1). |

### Golpe Furtivo – Regras
- Requer **vantagem** (flanqueamento, camuflagem, marca de arquétipo ou Desafio do Duelista).
- Consome 15 Estamina e abre janela de 3s. Se não usar, buff expira.
- CD interno de 6s por alvo (armazenado via `rogue_cmds.sk`).
- Multiplicador de dano: T1 1.7x, T2 2.1x, T3 2.5x, T4 2.9x. PvP não recebe crítico adicional.
- Thief Smoke Bomb e Assassin Mark contam como vantagem; Swashbuckler ignora requisito contra alvo marcado.

### HUD / PlaceholderAPI
- `{stamina}` → `%skills_rogue_stamina%`
- `{sneak_cd}` → `%skills_rogue_sneak_cd%`
- `{duelist_mark}` → `%skills_rogue_advantage%` (1 quando Golpe Furtivo pronto ou alvo marcado)
- `{cooldown_next}` → `%skills_cooldown_next%`
- `{mobility}` → `%skills_rogue_mobility%`

## Subclasses (Arquétipos)
### Assassino (Assassin)
- **Identidade:** emboscadas preparadas, rupturas hemáticas e execução condicional (<30% HP).
- **Itens-Chave:** `AssassinDagger`, `AssassinCloak`, `ToxinVial`, `AssassinExecutionCharm`.
- **Skills:**
  - Marcador de Alvo (45s / 20 Estamina) – +25% Golpe Furtivo e Hemorragia 6%/s por 4s.
  - Ataque de Emboscada (75s / 25) – +120% dano no próximo ataque ao sair de stealth ou fora de combate.
  - Toxina Preparada (90s / 25) – 12s aplicando -10% velocidade e -10% cura.
  - Execução Calculada (210s / 35) – 240% dano <30% HP ou 180% + -15% resistência por 6s.
- **Notas:** Hemorragia respeita limite PvP e não acumula; Execução aplica imunidade de 6s ao mesmo alvo.

### Ladrão (Thief)
- **Identidade:** mobilidade extrema, controle de cenário, suporte utilitário.
- **Itens-Chave:** `ThiefHook`, `ThiefSmokeBomb`, `ThiefBoots`, `ThiefMasterKit`.
- **Skills:**
  - Hookshot (45s / 20) – puxão até 12 blocos ou inimigos leves 3 blocos.
  - Bomba de Fumaça (90s / 25) – área 6b/6s cegando e desacelerando inimigos; aliados ganham camuflagem.
  - Mãos Velozes (45s / 15) – reseta Pique + Hookshot e concede +0.05 velocidade por 4s.
  - Arrombador Mestre (210s / 30) – 12s reduzindo em 25% CDs de mobilidade/utilidade do grupo (8 blocos) e abrindo rotas marcadas.
- **Notas:** Hookshot bloqueia chefes via tag de massa, fumaça respeita uptime ≤60%, aura aplica cooldown tick negativo via Skript.

### Espadachim (Swashbuckler)
- **Identidade:** duelo agressivo, reposicionamento e roubo de vida controlado.
- **Itens-Chave:** `SwashRapier`, `SwashPlume`, `SwashBoots`, `SwashCrest`.
- **Skills:**
  - Desafio do Duelista (60s / 20) – marca 1 alvo por 10s; +10% velocidade/penetração e Golpe Furtivo ignora vantagem no marcado.
  - Passo de Dança (60s / 20) – 6s de +0.04 velocidade e 20% esquiva frontal.
  - Estocada Rápida (45s / 25) – 140% dano com avanço 2b e tentativa de reposicionamento atrás.
  - Clímax do Duelista (210s / 35) – 8s +25% velocidade de ataque e 15% lifesteal (cap 25%).
- **Notas:** Dodge frontal aplica RNG ≤20% e não acumula com Evasão além do limite; Clímax usa RacesEffects para respeitar caps de cura.

## Comandos (Skript)
- `/kit_ladino` – entrega itens focais (LuckPerms `class_rogue`).
- `/furtivo`, `/evasao`, `/pique` – atalhos para skills base.
- `/quebra` – ativa Arrombador Mestre (apenas `class_rogue.thief`).
- `/finese` – habilita passiva de desconto manual para debug.
- Itens MythicMobs possuem NBT `skill` e são lidos por `rogue_cmds.sk` para disparar ProSkillAPI.

## Integração MythicMobs
- `plugins/MythicMobs/Items/rogue_items.yml` define itens focais com `PersistentData.classlock` (LuckPerms) e `stack=1`.
- Dummies furtivos: usar `MythicMobs` mob `RogueDummy` (não incluso) para validar Golpe Furtivo com vantagem.
- Projéteis de Hookshot e efeitos de fumaça utilizam `show smoke` no Skript.

## LuckPerms
- `class_rogue` concede acesso base (skills, comandos, kit).
- Subgrupos `class_rogue.assassin`, `class_rogue.thief`, `class_rogue.swashbuckler` herdam e liberam itens/skills específicos.
- Itens MythicMobs verificam `classlock` contra esses grupos.

## Interações com Raças
- Skript lê `%raceseffects_*%` para aplicar retornos decrescentes se buffs raciais fornecerem velocidade/DR adicionais.
- Se Estamina chegar a 0 com bônus raciais ativos, aplica debuff “Exaustão Física” via RacesEffects.

## Fluxo de Testes
Consulte `docs/TESTPLAN_LADINO.md` para cenários PvE/PvP, métricas de DPS/TTK e checagens de caps (vantagem, Estamina, controle de multidão).
