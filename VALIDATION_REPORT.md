# Validation Report

## Resumo Geral
| Categoria | Status | Observações |
| --- | --- | --- |
| Sintaxe | PASS | Todos os `.yml` abriram com `yaml.safe_load_all` sem erros fatais; `.sk` legíveis. |
| Referências Cruzadas | **FAIL** | Itens focais de Bardo usam `class_bard.knowledge`, mas LuckPerms/SkillAPI usam `class_bard.lore`; permissões citam itens inexistentes (`LoreSongbook`, `LoreRelicHarp`). |
| Duplicações / Conflitos | **FAIL** | `/racial` definido em múltiplos Skripts, além de coexistência de subclasses `Monk_OpenHand` e `Monk_WayOfTheOpenHand`. |
| Balanceamento & Caps | **FAIL** | Várias skills ativas com `cooldown-base < 45s` (`sorcerer_arcane_bolt`), contrariando limites de habilidades-chave. |
| Diminishing Returns | WARN | Arquivos de raça mencionam DR, porém não há validação automática para combos classe+item; revisão manual recomendada. |
| PvP Sanity | WARN | Ausência de `notes_pvp` estruturado em todas as skills dificulta rastreio de DR/imunidades. |
| Performance | PASS | Sem loops de partículas agressivos identificados; construtos limitados por jogador. |
| Documentação | **FAIL** | Planilhas e READMEs citam itens/IDs divergentes (`LoreRelicHarp`, `LoreSongbook`) e não cobrem skills `bard_knowledge_*`. |

## Matriz de Problemas
| Arquivo | Linha/Chave | Severidade | Regra violada | Sugestão |
| --- | --- | --- | --- | --- |
| `plugins/MythicMobs/Items/bard_items.yml` | linhas 3-24 | FAIL | Prefixos/IDs + Cross-ref (classes) | Renomear `classlock` e coleções `knowledge` para `class_bard.lore` e alinhar IDs de skills chamados via Skript. 【F:plugins/MythicMobs/Items/bard_items.yml†L1-L24】 |
| `plugins/Skript/scripts/bard_cmds.sk` | linhas 205-238 | FAIL | Referência cruzada (LuckPerms ↔ itens) | Atualizar checagens/permissões para `class_bard.lore` (ou criar grupo `class_bard.knowledge`) e sincronizar comandos. 【F:plugins/Skript/scripts/bard_cmds.sk†L205-L238】 |
| `plugins/ProSkillAPI/subclasses/bard_lore.yml` | linhas 1-36 | WARN | Naming/IDs + Cross-ref | Avaliar migração para prefixo único (`bard_lore_*` ou `bard_knowledge_*`) e garantir que itens/comandos usem a mesma família. 【F:plugins/ProSkillAPI/subclasses/bard_lore.yml†L1-L36】 |
| `plugins/LuckPerms/bard_groups.txt` | linhas 2-35 | FAIL | LuckPerms integridade | Criar/ajustar grupo `class_bard.knowledge` ou alinhar permissões aos itens reais (`Bard_Knowledge_*` inexistentes no LP). 【F:plugins/LuckPerms/bard_groups.txt†L1-L35】 |
| `plugins/Skript/scripts/races_dwarf_cmds.sk` | linhas 70-100 | FAIL | Comandos duplicados | Consolidar `/racial` em único handler com roteamento; manter aliases específicos como `/racial_dwarf`. 【F:plugins/Skript/scripts/races_dwarf_cmds.sk†L70-L100】 |
| `plugins/Skript/scripts/races_cmds.sk` | linhas 11-39 | FAIL | Comandos duplicados | Unificar `/racial` para evitar conflitos com comandos de submódulos. 【F:plugins/Skript/scripts/races_cmds.sk†L1-L39】 |
| `plugins/ProSkillAPI/skills/sorcerer/sorcerer_arcane_bolt.yml` | linhas 1-24 | FAIL | Cooldowns mínimos | Elevar `cooldown-base` ≥ 45s ou reclassificar como habilidade básica com custo alternativo; documentar justificativa PvP. 【F:plugins/ProSkillAPI/skills/sorcerer/sorcerer_arcane_bolt.yml†L1-L24】 |
| `docs/README_CLASS_BARDO.md` | linhas 54-57 | FAIL | Documentação ≠ implementação | Atualizar itens citados para nomes existentes (`Bard_Knowledge_T*`, `BardLute`) e remover referências órfãs (`LoreRelicHarp`). 【F:docs/README_CLASS_BARDO.md†L54-L57】 |
| `docs/BALANCE_SHEET_BARDO.csv` | linhas 1-11 | WARN | Planilha incompleta | Adicionar linhas para skills `bard_knowledge_*` (presentes nos YAML de college) para manter 100% de cobertura. 【F:docs/BALANCE_SHEET_BARDO.csv†L1-L11】 |
| `plugins/MythicMobs/Items/class_items.yml` | linhas 66-88 | WARN | Itens focais desatualizados | Revisar lista `BardLoreBook` vs `LoreSongbook` e ajustar LuckPerms/Docs. 【F:plugins/MythicMobs/Items/class_items.yml†L66-L88】 |
| `plugins/ProSkillAPI/skills/bard/college_knowledge.yml` | linhas 1-24 | WARN | Falta `notes_pvp`/`tier` explícitos | Inserir metadados `notes_pvp` e `tier` conforme padrão global para todas as skills. 【F:plugins/ProSkillAPI/skills/bard/college_knowledge.yml†L1-L24】 |

## Métricas
- Classes principais: **15** arquivos (`plugins/ProSkillAPI/classes`).
- Subclasses/arquétipos: **48** arquivos.
- Arquivos de skills ProSkillAPI: **437** (múltiplas skills por arquivo nos colégios/bloodlines).
- Raças/Sub-raças (RacesEffects): **32** arquivos + `config.yml` base.
- Itens MythicMobs (classe/raça): **26** arquivos.
- Comandos Skript únicos: **250**, com colisões detectadas em `/racial` (2 arquivos) e múltiplas repetições internas.
- Documentação/planilhas: **111** arquivos (CSV/MD) — cobertura parcial para novas skills (`bard_knowledge_*`).
- Conflitos críticos identificados: **5** (Bardo cross-ref, LuckPerms itens órfãos, duplicação `/racial`, cooldowns sub-45s, docs desatualizados).
- Violações de caps: **1** confirmada (cooldown < 45s); demais potenciais requerem revisão manual.

## Integridade LuckPerms
```
class_bard
 ├─ class_bard.lore  (OK, mas items/skills referenciam `Lore*` que não existem no MythicMobs atual)
 ├─ class_bard.valor (OK)
 └─ class_bard.glamour (OK)
```
> Ausência de `class_bard.knowledge` quebra itens `Bard_Knowledge_T*` e funções Skript associadas. Ajustar árvore ou renomear itens/skills.

## Lista de Comandos Efetivos (amostragem)
- `/kit_bardo`, `/college_knowledge`, `/college_valor`, `/college_glamour` (Bardo).
- `/racial` (duplicado em `races_cmds.sk` e `races_dwarf_cmds.sk`), `/sopro_draco`, `/talento`, `/versatilidade`, `/furia`, `/golpe`, `/inspire` etc.
- Total contabilizado: **250** comandos/aliases, recomendada planilha consolidada para evitar colisões.

## Observações Adicionais
- Todas as skills analisadas carecem de campo `notes_pvp`/`tier` explícito, dificultando auditoria automática de caps de controle e uptime.
- Subclasses duplicadas (`monk_open_hand.yml` e `monk_openhand.yml`) devem ser consolidadas para prevenir divergências de árvore/tags.
- RacesEffects mantém DR declarada (ex.: `dragonborn_fire` limita `max: 0.45`), porém falta integração automática com buffs de classe/itens.
