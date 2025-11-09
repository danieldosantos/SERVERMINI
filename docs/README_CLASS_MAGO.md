# Classe Mago (Minecraft x D&D 5e)

## Visão Geral
O Mago é um controlador de utilidade com janelas de burst situacional, inspirado no PHB de D&D 5e (tiers T1/T2/T3/T4 ≈ níveis 1/5/11/17). A classe utiliza **Mana regenerativa** (4% fora de combate, 2% em combate) e **Slots Preparados** para limitar habilidades mais fortes, reforçando a fantasia de estudo e preparação diária.

* **Função principal:** Controle do campo, proteção de aliados e dano elemental pontual.
* **Recursos:** `mana` (regeneração dinâmica via Skript) e `prepared_slots` (T1=3, T2=5, T3=7, T4=9).
* **Mobilidade:** `Teleporte Curto` para reposicionamento com cooldown médio (45s).
* **Mitigação:** Baixa base; depende de paredes/arcanismos e das escolas.
* **Sinergia com Raças:** Scripts integram RacesEffects para aplicar exaustão de resistência quando buffs sobrepõem resistências raciais.

## Árvore por Tier
| Tier | Skills Ativas | Passivas |
| ---- | ------------- | -------- |
| T1   | Teleporte Curto, Parede Arcana, Preparação Arcana | Estudo Rigoroso, Foco Mental |
| T2   | mesmas de T1 (maior mana / slots) | mesmas |
| T3   | mesmas de T1 | mesmas |
| T4   | mesmas de T1 | mesmas |

> **Nota:** As habilidades preparáveis (`Parede Arcana` e as listadas nas subclasses) exigem consumo de slots via comando/GUI `/preparar`.

## Recursos e HUD
* **PlaceholderAPI:** `{mana_pct}`, `{prepared_slots_used}`, `{prepared_slots_max}`, `{cooldown_next}`.
* **Scoreboards:** `wizard_mana`, `wizard_prep`, `wizard_cd` atualizados pelo Skript `wizard_cmds.sk`.
* **Regeneração:** `skillapi resource add` ajustado pelo estado de combate (flag em Skript com delay de 8s).

## Comandos (Skript `wizard_cmds.sk`)
| Comando | Descrição |
| ------- | --------- |
| `/preparar` | Abre GUI para alternar skills preparáveis. Apenas fora de combate. |
| `/teleporte` | Atalho para `Teleporte Curto` (15 mana / 45s). |
| `/parede` | Atalho para `Parede Arcana` (25 mana / 90s, exige preparo). |
| `/escola_evocacao`, `/escola_abjuracao`, `/escola_ilusao` | Concede temporariamente a permissão da escola correspondente (via LuckPerms). |
| `/kit_mago` | Fornece grimório, cajado e itens focais de teste; limpa HUDs. |

## Itens Focais (MythicMobs `wizard_items.yml`)
* **Base:** `WizardBlinkRune`, `WizardBarrierSigil`, `WizardPreparationGrimoire` – vinculados ao grupo `class_wizard`.
* **Evocação:** `EvocationStaff`, `EvocationFocus`, `EvocationSpear`, `EvocationCrystal`, `EvocationGrimoire` – requerem `class_wizard.evocation`.
* **Abjuração:** `AbjurationShield`, `AbjurationTalisman`, `AbjurationRune`, `AbjurationMirror`, `AbjurationCodex` – requerem `class_wizard.abjuration`.
* **Ilusão:** `IllusionDeck`, `IllusionCloak`, `IllusionSphere`, `IllusionLantern`, `IllusionGrimoire` – requerem `class_wizard.illusion`.

Todos os itens possuem `psapi-skill` e `class-lock`, impedindo uso fora da subclasse correta e atualizando HUD/cooldowns via Skript.

## Subclasses
### Escola de Evocação
Foco em dano direto e explosões controladas. Marcas de `Instabilidade` reduzem resistência mágica e permitem bursts sem quebrar o cap PvP (6–8 blocos de área, CDs 45–240s). Skills: Orbe Flamejante (preparável), Nova Arcana, Lança Etérea, Catalisador de Poder, Cataclismo Controlado (ULT). Recursos extras: ganchos Skript para exaustão se o alvo já possuir resistência elemental racial.

### Escola de Abjuração
Ênfase em proteção e negação. Barreira Protetora (200 pontos), Contra-Feitiço (preparável, janela de 3s), Selo Rúnico (-25% dano elemental), Reflexo Prismático (30% reflect) e Fortaleza Arcana (ULT com domo 6 blocos). Scripts evitam ultrapassar mitigação global (45%) e mantêm fraquezas raciais.

### Escola de Ilusão
Controle brando e mobilidade tática. Imagem Múltipla (preparável), Véu Invisível (+20% velocidade/invisibilidade), Labirinto Mental (imobiliza), Fantasmagoria (medo e -15% precisão), Mundo Espelhado (ULT duplicador). Scripts limitam stealth/velocidade conforme caps globais e aplicam desorientação sem causar one-shot.

## Preparação Diária
1. Use `/preparar` fora de combate para abrir a GUI.
2. Cada habilidade preparável consome um slot até o limite (`prepared_slots_max`).
3. Trocas só são permitidas fora de combate; o sistema registra a lista e impede conjuração de habilidades não preparadas.
4. `Estudo Rigoroso` reduz em 10% o cooldown das habilidades preparadas; `Foco Mental` concede +5% de poder mágico acima de 80% de mana.

## Integrações
* **LuckPerms:** `plugins/LuckPerms/wizard_groups.txt` com heranças e permissões de skills/itens.
* **PlaceholderAPI:** HUD dinâmico e verificação de recursos no Skript.
* **RacesEffects:** Hooks em skills de dano elemental e buffs defensivos para aplicar exaustão de resistência quando necessário.
* **MythicMobs:** Itens com partículas diferenciadas por escola (Portal, Explosion, Soul, etc.) e telemetria telegráfica para ultimates.
* **ProSkillAPI:** Classe `wizard.yml` (mana + prepared_slots) e skills YAML organizados em `plugins/ProSkillAPI/skills/wizard/`.

## Limites e Caps
* **Velocidade adicional:** máx. +0.05 (Véu Invisível aplica +0.2 multiplicativo, respeitando limite global).
* **Mitigação total:** ≤45% (Abjuração considera caps em passivas e domo).
* **Área padrão:** 6–8 blocos para AoE (Nova Arcana, Cataclismo, Fantasmagoria, Fortaleza Arcana).
* **Ultimates:** cooldowns entre 180–240s, custos elevados (55–60 mana).

## Checklist de Implementação
- [x] Classe base `wizard.yml` com recursos, atributos e habilidades.
- [x] Subclasses (`wizard_evocation.yml`, `wizard_abjuration.yml`, `wizard_illusion.yml`).
- [x] Skills em `plugins/ProSkillAPI/skills/wizard/` com tags e hooks para Skript.
- [x] Itens MythicMobs `wizard_items.yml` integrados com LuckPerms.
- [x] Skript `wizard_cmds.sk` para comandos, HUD e preparação.
- [x] Documentação e planilha de balanceamento.
