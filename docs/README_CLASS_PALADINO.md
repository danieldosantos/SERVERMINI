# Classe Paladino (Minecraft + D&D 5e)

## Visão Geral
- **Função:** Suporte/Tanque híbrido com auras situacionais, curas burst e controle moderado.
- **Recurso exclusivo:** `Fé` (charges). Inicia com 3 cargas e expande para 4 a partir do tier 3 (nível ~11).
- **Uptime controlado:** Auras ativas limitadas a 30s com recarga mínima de 90s (≤60%).
- **Limites globais aplicados:** Mitigação total até 45%, bônus de velocidade máxima +0.05, curas por golpe ≤25% do dano.
- **Sinergia com RacesEffects:** Scripts aplicam exaustão quando resistências raciais ultrapassam limites previstos.

## Progressão por Tier
| Tier | Níveis aproximados | Vida base (aprox.) | Mitigação física | Bônus radiante |
|------|--------------------|--------------------|------------------|----------------|
| T1   | 1–4                | 40                 | 8                | +8%            |
| T2   | 5–10               | 68                 | 11               | +10%           |
| T3   | 11–16              | 106                | 15               | +12%           |
| T4   | 17+                | 144                | 18               | +14%           |

## Skills da Classe Base
| Skill | Tipo | Descrição resumida | Custo | Cooldown |
|-------|------|---------------------|-------|----------|
| **Golpe Divino** | Ativa | Próximo ataque recebe +100%→140% dano radiante e consome 1 Fé. | 1 Fé | 45s |
| **Imposição de Mãos** | Ativa | Cura 18%→25% da vida máx. e remove 1 debuff leve. Possui 2→3 cargas com recarga de 180s. | Nenhum | 30s (entre usos) |
| **Canalizar Voto** | Ativa | A aura base concede +10% mitigação em 6 blocos por 30s. Subclasses sobrepõem efeitos. | 1 Fé | 90s |
| **Aura de Proteção** | Passiva | Garante que a aura base respeite o cap global de mitigação e sincroniza HUD. | Passivo | — |
| **Resistência ao Medo** | Passiva | Reduz duração de efeitos de medo em 30% (via RacesEffects). | Passivo | — |

## Recurso Fé
- Gerido por Skript (`paladin_cmds.sk`) com regeneração fora de combate: 1 carga a cada 35s se o jogador estiver há ≥30s sem entrar em combate.
- Consumo explícito para Golpe Divino, Canalizar Voto e habilidades de burst das subclasses.
- PlaceholderAPI/Scoreboard expõem `{faith_charges}`, `{cooldown_next}` e `{aura_time_left}` para HUD.

## Juramentos (Subclasses)
### Juramento da Devoção
- Identidade: foco em purificação, mitigação direta e milagres de cura.
- Skills principais: Golpe Sagrado, Aura de Pureza, Luz Orientadora, Escudo Devoto, Milagre da Fé.
- Ultimate (Milagre da Fé) fornece regen pesada (via poção) + imunidade a medo/charme por 6s (CD 240s).
- Itens temáticos: Lâmina da Devoção, Relíquia do Juramento, Lanterna Orientadora, Escudo Devoto, Códice dos Milagres.

### Juramento da Vingança
- Identidade: caça a um alvo marcado, mobilidade agressiva e execução controlada.
- Skills principais: Juramento de Inimizade (marca 12s, +20% dano), Passo do Redentor (dash 12 blocos com imunidade CC), Cadeia do Pecado (puxão + lentidão), Fúria Celestial (+30% atk speed) e Execução Divina (250% radiante + finisher <25% HP).
- Itens: Sigilo da Vingança, Lâmina Implacável, Botas do Redentor, Corrente do Pecado, Relíquia da Execução.

### Juramento dos Anciões
- Identidade: proteção elemental, cura sustentada e mobilidade mística.
- Skills: Vinha Restritiva (raiz 3s, -15% dano), Aura da Natureza (+15% resistência elemental por 12s), Luz Radiante (cega inimigos e cura aliados), Asas de Safira (planar +0.05 velocidade) e Harmonia Primordial (zona de 8 blocos com +25% cura recebida e -20% dano sofrido por 15s).
- Itens: Lança das Vides, Coroa da Canópia, Lanterna Radiante, Manto Safira, Totem Harmônico.

## Itens e Integrações (MythicMobs + Skript)
- `paladin_items.yml` contém itens focais com `PersistentData` que restringe uso aos grupos LuckPerms da subclasse.
- Cada item chama `/paladin_cast <skill>` via eventos de clique, aplicando cooldowns e custos corretos.
- `/kit_paladino` entrega um conjunto de testes (1 item chave por juramento) e ressincroniza recursos.

## HUD e Placeholder
- Actionbar atualiza a cada 5s mostrando `Fé atual / máxima`, cargas de Imposição de Mãos e tempo restante da aura ativa.
- `paladin_cmds.sk` também define títulos temáticos para Canalizar Voto e mensagens de recarga.

## Limites com RacesEffects
- `paladin_cmds.sk` consulta `%raceseffects_resistance_bonus%` para aplicar Fadiga de Mineração ao ativar Escudo Devoto se houver resistência acumulada >30%.
- Aura de Pureza e Aura da Natureza utilizam `diminishing-cap: 0.45` nos YAML para respeitar o teto de mitigação.

## Resumo Operacional
1. Jogador aprende skills automaticamente ao progredir nos tiers da classe Paladino.
2. Itens de juramento exigem permissões LuckPerms específicas e disparam skills via `/paladin_cast`.
3. Regeneração de Fé ocorre automaticamente fora de combate; cargas adicionais de Imposição de Mãos restauram após 180s.
4. Auras só ficam ativas durante Canalizar Voto (ou skills específicas), garantindo uptime máximo de 60%.
