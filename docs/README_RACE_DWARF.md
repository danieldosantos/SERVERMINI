# Raça Anão (Base + Sub-raças)

Implementação para servidores Minecraft + D&D 5e utilizando RacesEffects, ProSkillAPI, MythicMobs, Skript e LuckPerms.

## Estrutura de Arquivos
- `plugins/RacesEffects/races/dwarf_base.yml`: traços base da raça anã.
- `plugins/RacesEffects/races/dwarf_hill.yml`: sub-raça Anão da Colina.
- `plugins/RacesEffects/races/dwarf_mountain.yml`: sub-raça Anão da Montanha.
- `plugins/RacesEffects/races/dwarf_deep.yml`: sub-raça Anão das Profundezas / Duergar.
- `plugins/ProSkillAPI/skills/races/dwarf/*.yml`: habilidades ativas raciais por tier.
- `plugins/MythicMobs/Items/races_dwarf_items.yml`: itens focais que disparam habilidades raciais.
- `plugins/Skript/scripts/races_dwarf_cmds.sk`: comandos, integrações de itens e HUD.
- `plugins/LuckPerms/races_dwarf_groups.txt`: criação e herança de grupos.
- `docs/TESTPLAN_RACE_DWARF.md`: plano de QA.
- `docs/BALANCE_SHEET_RACE_DWARF.csv`: planilha de balanceamento.

## Visão Geral da Raça
- **Tema central:** robustez física, disciplina forjada e tradição contra venenos.
- **Passivos base:**
  - Resiliência Anã — mitigação contra venenos com DR.
  - Treinamento de Forja — redução de knockback após dano físico.
  - Constituição Anã — bônus escalonado de HP base por tier.
- **Ativo base:** Fortitude Anã (escudo 120–180, 8s, CD 90s).
- **Itens chave:** Runa da Forja, Coração da Colina, Fivela Rúnica, Sigilo de Ferro e Anel da Penumbra.

## Sub-raças
- **Colina:** foco em regeneração fora de combate e redução de cooldown defensivo quando ferido.
- **Montanha:** mitigação física temporária e âncora anti-knockback quando debilitado.
- **Profundezas / Duergar:** resistência psíquica, penalidade sob sol, e habilidades Crescer/Invisibilidade com restrições de luz.

## Integrações Importantes
- **LuckPerms:** grupos `race_dwarf` + subgrupos; comandos e itens verificam `group.*` ou `lp.*` equivalentes.
- **ProSkillAPI:** habilidades com tiers T1–T4 (níveis 1/5/11/17) respeitando cooldowns ≥ 90s.
- **RacesEffects:** passivos com uptime ≤ 60%, mitigação máxima global ≤ 45%, velocidade adicional ≤ 0.05.
- **MythicMobs:** itens possuem NBT `race-lock` + `skill` para travas e identificação via Skript.
- **Skript:** `/racial`, `/fortitude_anao`, `/postura_pedra`, `/crescer_duergar`, `/invis_duergar`, `/kit_racial_anao` e cliques em itens.
- **PlaceholderAPI / HUD:** action bar confirma ativação e orienta sobre cooldowns.

## Observações de Balanceamento
- Controle CC ≤ 4s (Postura e Crescer não adicionam controle duro).
- Mitigação total combinada (classe + raça + itens) limitada a 45% via DR.
- Cura on-break da Fortitude Profunda respeita limite de 25% por golpe.
- Crescer/Invisibilidade indisponíveis sob sol graças a checagem de luz em Skript.

## Deploy
1. Criar grupos no LuckPerms conforme arquivo `.txt`.
2. Copiar YAMLs de raça e skills para os diretórios correspondentes.
3. Importar itens do MythicMobs (`/mm reload items`).
4. Recarregar Skript (`/sk reload races_dwarf_cmds` e scripts relacionados).
5. Atualizar scoreboard/HUD para consumir placeholders `skill_*`.

## Referências Cruzadas
- Balanceamento detalhado: `docs/BALANCE_SHEET_RACE_DWARF.csv`.
- Casos de teste PvE/PvP: `docs/TESTPLAN_RACE_DWARF.md`.
