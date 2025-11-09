# Plano de Testes — Raça Anão

## Preparação
1. Aplicar grupos LuckPerms de acordo com `plugins/LuckPerms/races_dwarf_groups.txt`.
2. Garantir que os itens do MythicMobs estejam carregados (`/mm reload items`).
3. Recarregar os scripts Skript (`/sk reload races_dwarf_cmds`, `races_cmds`).
4. Configurar scoreboard/HUD com placeholders `skill_fortitude_anao`, `skill_postura_de_pedra`, `skill_crescer_duergar`, `skill_invisibilidade_cinzenta`.

## Casos PvE
1. **Dungeon tóxica (T2):** enfrentar enxames venenosos por 3 ciclos de 12s; medir mitigação média 25% e uptime da Resiliência ≤33%.
2. **Boss de cerco (T3):** provocar dano físico contínuo para validar Treinamento de Forja com uptime ≤15% e redução de knockback visível.
3. **Exploração de colinas:** alternar combate/descanso verificando Vigor de Colina (3 ativações por minuto) e cura total < 25% do HP.
4. **Guardiã de Montanha:** permitir 3 hits rápidos em 5s para ativar Rigidez Granítica e confirmar DR total ≤45% com classe tanque.
5. **Ritual Duergar subterrâneo:** enfrentar inimigo psíquico, confirmar Resistência Psíquica ativa ≤33% do tempo e uso de Crescer Duergar sem luz.

## Casos PvP
1. **Duelo arena venenos:** assassino vs anão base; usar Fortitude Anã 1 vez/90s e confirmar escudo 120–180 conforme tier.
2. **Escaramuça 3v3 colina:** monitorar Sabedoria Terrosa abaixo de 50% HP; verificar redução de cooldown defensivo em 5% sem ultrapassar uptime 16%.
3. **Controle de ponte montanha:** sofrer burst <40% HP e validar Ancoragem (knockback -40%) com Postura de Pedra ativa a cada 90s.
4. **Infiltração duergar subterrânea:** usar Crescer Duergar + Invisibilidade Cinzenta (sequência 90s CD) para garantir rotação sem sol, sem ultrapassar bônus de velocidade global.
5. **Assalto em superfície:** expor duergar à luz solar por 10s; confirmar mensagem de falha nas habilidades ativas e aplicação de penalidade de precisão/dano de projéteis.

## Critérios de Aprovação
- Nenhum passivo excede 60% de uptime.
- Mitigação total combinada ≤45% e velocidade ≤ +0.05.
- Crescer/Invisibilidade bloqueadas sob luz solar; mensagens de feedback exibidas.
- HUD/action bar confirma execução das habilidades.
- Testes de QA com `/kit_racial_anao` fornecem itens corretos.
