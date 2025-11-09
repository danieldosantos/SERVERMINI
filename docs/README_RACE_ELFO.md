# Raça Élfica (Base e Sub-raças)

## Visão Geral
A linha élfica recebeu atualização completa para o servidor Minecraft + D&D 5e.
Os traços foram implementados com base nas diretrizes de uptime ≤60%, cooldowns mínimos
compatíveis e limites globais de velocidade/mitigação.

### Plugins e Integrações
- **RacesEffects**: define passivos condicionais (bioma, luz e combate) e referencia
  os gatilhos de Fae Passos, Truques Arcanos, Luzes Feéricas, Passo Umbrático,
  Marca do Bosque e Salto de Galho.
- **ProSkillAPI**: habilidades raciais ativas com curvas por tier (T1/T4) em
  `plugins/ProSkillAPI/skills/races/elfo/`.
- **MythicMobs**: itens raciais com bloqueio LuckPerms em
  `plugins/MythicMobs/Items/races_items.yml`.
- **Skript**: comandos `/racial`, `/fae_passos`, `/truque_arcano`, `/luzes_feericas`,
  `/passo_umbratico`, `/marca_bosque`, `/salto_galho` e kits de QA.
- **LuckPerms**: grupos `race_elfo` e `race_elfo.<sub>` com permissões de skills,
  itens e comandos.
- **PlaceholderAPI/Scoreboard**: placeholders sugeridos (`{cooldown:skill_*}` e
  `{status:*}`) exibidos na seção `placeholders` das raças.

## Traços por Raça

### Base — Elfo
- **Graça Élfica**: +0.01 → +0.02 velocidade por 12s ao sair de combate (CD 30s).
- **Sentidos Aguçados**: alcance de detecção +5% com uptime máximo de 60%.
- **Transe**: regen leve fora de combate (2%/s por 10s, CD 45s).
- **Fae Passos**: dash de 5 blocos, resistência a empurrões por 2s, CD 45s.

### Alto Elfo
- **Erudição Arcana**: +5% eficiência com focos mágicos enquanto empunhados.
- **Disciplina Mística**: após conjurar, reduz em 5% o CD da próxima utilidade por 12s (CD 60s).
- **Truque Arcano**: escolha entre Seta Arcana, Barreira Cintilante ou Piscar Breve via
  `/truque_arcano <opção>`.

### Drow
- **Visão no Escuro Superior**: 26 blocos em baixa luz.
- **Penalidade de Luz Solar**: -5% precisão e -5% potência de conjuração sob luz direta.
- **Sutileza Sombria**: +5% evasão em baixa luz (≤60% uptime).
- **Luzes Feéricas**: revela/marca inimigos (CD 75s).
- **Passo Umbrático**: teleporte 6 blocos apenas em sombras + invisibilidade parcial (CD 60s).

### Elfo da Floresta
- **Passo Silvestre**: +0.02 velocidade em floresta (≤60%); fora dela +0.01 por 8s ao
  entrar em combate (CD 30s).
- **Camuflagem Natural**: -10% chance de ser detectado entre vegetação.
- **Marca do Bosque**: marca um alvo por 10s (+12% dano, +1 bloco de alcance).
- **Salto de Galho**: impulso de 6 blocos com queda lenta por 3s (CD 45s).

## Operação
1. **LuckPerms**: aplicar comandos de `docs/LuckPerms/races_groups.txt` para criar grupos,
   heranças e permissões.
2. **Itens**: liberar kits de QA com `/kit_racial_elfo*` (CMI/EssentialsX) para testes.
3. **Skript**: recarregar `races_cmds.sk` (`/sk reload races_cmds`).
4. **SkillAPI**: reiniciar ou recarregar (`/psa reload skills`) para reconhecer os arquivos.
5. **PlaceholderAPI**: adicionar placeholders às HUDs conforme listados nas seções
   `placeholders` das raças.

## Observações de Balanceamento
- Todos os passivos respeitam uptime ≤60% via `uptime_cap` e cooldowns dedicados.
- Velocidade efetiva nunca excede +0.05: Fae Passos/Passo Silvestre foram ajustados e
  possuem notas de cap.
- Penalidades raciais (ex.: luz solar para drow) foram mantidas.
- Habilidades chave seguem os limites: 45s (dash), 60–75s (situacionais), 180s+ não foi
  necessário para estas habilidades.
- Resistências aplicadas utilizam notas de diminishing returns quando pertinente.

## Diagnóstico Rápido
| Comando | Esperado |
|---------|----------|
| `/racial` | Mensagem com resumo das habilidades disponíveis |
| `/fae_passos` | Dash curto com resistência a knockback |
| `/truque_arcano barreira` | Alterna truque ativo e lança Barreira Cintilante |
| `/luzes_feericas` | Revela inimigos próximos por 8s |
| `/passo_umbratico` | Teleporta apenas em sombra, com invis parcial |
| `/marca_bosque` | Marca alvo e aumenta dano à distância |
| `/salto_galho` | Impulso + slow fall |

## Compatibilidade
- **PvP**: as reduções (evasão, marca) foram mantidas abaixo de 12% para evitar locks.
- **PvE**: testes recomendados em florestas, cavernas e encontros mágicos conforme
  `docs/TESTPLAN_RACE_ELFO.md`.
