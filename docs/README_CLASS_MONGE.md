# Classe Monge (Minecraft + D&D 5e)

A classe Monge foi construída para o ecossistema ProSkillAPI + MythicMobs + Skript, trazendo a fantasia de artes marciais móveis com suporte tático. O kit prioriza controle leve, mobilidade precisa e mitigação situacional, respeitando todos os limites globais definidos para o servidor.

## Visão Geral
- **Função:** controlador móvel com dano sustentado leve e ferramentas de suporte.
- **Recurso:** `Ki` (4/6/8/10 por tier) com regeneração de 1 ponto a cada 30s fora de combate (gerenciada pelo Skript `monk_cmds.sk`).
- **Limites Globais:** bônus de velocidade respeitam cap +0.05, mitigação total abaixo de 45% e autocuras ≤20% do dano recebido.
- **Sinergia com Raças:** bônus raciais de esquiva/velocidade recebem retornos decrescentes; técnicas do monge nunca anulam fraquezas raciais, apenas aproximam do teto seguro.

## Progressão por Tier
| Tier | Conteúdo Base | Destaques |
|------|---------------|-----------|
| T1   | Rajada de Golpes, Passo do Vento, Mãos Curativas, passivos defensivos. | Mobilidade inicial e mitigação contra projéteis com uptime baixo. |
| T2   | Acrescenta Equilíbrio de Ki (HUD) e acesso às primeiras técnicas de subclasse. | Regeneração controlada via Skript e combos básicos de controle. |
| T3   | Desbloqueia mobilidade avançada/subclasse mid tier. | Ferramentas de debuff (Sombra) ou suporte ampliado (Mão Aberta) ou versatilidade elemental. |
| T4   | Ultimates das tradições + atributos máximos. | Mitigação em área, sombra viva ou avatar elemental com rotação de elementos. |

## Comandos Principais
- `/rajada`, `/passo_vento`, `/maos_curativas`: habilidades base.
- `/kit_monge`: entrega braceletes, sandálias, contas e itens de subclasse (se houver permissão).
- `/mao_aberta_*`, `/sombra_*`, `/elemento_*`: atalhos para cada técnica das tradições.

Todos os comandos verificam permissões LuckPerms e aplicam mensagens/actionbars temáticas. Os itens focais também executam as skills via clique direito/esquerdo.

## Itens Focais (MythicMobs)
Arquivo `plugins/MythicMobs/Items/monk_items.yml` inclui:
- **Base:** `MonkBracers`, `MonkSandals`, `MonkPrayerBeads` (ativam Rajada, Passo do Vento e Mãos Curativas).
- **Mão Aberta:** `OpenHandGloves`, `OpenHandFocus`, `OpenHandRelic` (controle, regeneração de Ki e ultimate defensiva).
- **Sombra:** `ShadowCloak`, `ShadowBlade`, `ShadowObsidianOrb` (furtividade, burst e ultimate sombra viva).
- **Elementos:** `ElementsFireFan`, `ElementsWaterVial`, `ElementsPrism` (fogo, parede aquática e avatar elemental).

Cada item possui `PersistentData` ligando à skill correspondente e exige o grupo LuckPerms correto.

## HUD e PlaceholderAPI
- Objetivo de scoreboard `monk_ki` mostra `{ki atual}/{ki máximo}`.
- Placeholder de estados (`%skills_monk_states%`) registra invisibilidade/fora de combate e é espelhado no scoreboard `monk_states`.
- `monk_cmds.sk` envia actionbar a cada 5s com Ki, cooldown principal e estado (Invisível, Passo do Vento, Fora de Combate).

## Integração com RacesEffects
- O Skript monitora status fora de combate para evitar ganhos de Ki durante lutas.
- Skills de mobilidade verificam caps via descrições e buffs moderados; se uma raça já concede velocidade/esquiva, os ganhos extras apenas aproximam o limite.
- Passivos não removem penalidades raciais (ex.: fraqueza a projéteis), apenas amortecem dentro do teto de 45%.

## Árvores de Tradição
- **Caminho da Mão Aberta:** foco em empurrões, debuffs de dano e ultimate defensivo de 10s.
- **Caminho da Sombra:** teleporte curto entre sombras, cegueira e invocação da Sombra Viva para burst controlado.
- **Caminho dos Quatro Elementos:** arsenal elemental com muro anti-projétil, salto aéreo e avatar rotativo (dano→defesa→cura).

## Cooldowns e Custos
- Habilidades núcleo ≥45s, situacionais 60–90s, ultimates 210–240s conforme diretrizes.
- Custos: padrão 1 Ki; poderosas 2 Ki; ultimates 3 Ki (Shadow ULT aceita fallback de 2 Ki com duração reduzida).

## Testes e Balanceamento
Consulte `docs/TESTPLAN_MONGE.md` para roteiros PvE/PvP e `docs/BALANCE_SHEET_MONGE.csv` para valores numéricos detalhados.

## Integração com Kits/CMDs
- `monk_cmds.sk` faz broadcast ao carregar, entrega itens via MythicMobs, atualiza scoreboard e gerencia regeneração de Ki.
- LuckPerms em `monk_groups.txt` cria grupos `class_monk` e tradições, concedendo acesso a comandos, itens e casts.

## Considerações de PvP/PvE
- Rajada de Golpes oferece burst controlado (buff temporário), não explode alvos full HP.
- Passo do Vento mantém mobilidade responsiva, porém respeita o cap global e sinergia racial.
- Tradições mantêm identidades distintas: controle, furtividade/debuff e versatilidade elemental.
