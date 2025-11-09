# Diretrizes de Balanceamento — Raças e Classes

Este documento define limites obrigatórios para desenvolvimento de raças e classes no servidor. Toda
mudança deve ser validada aqui antes de ir para produção. Os valores consideram Paper 1.21.x com os
plugins LuckPerms, RacesEffects, AuraSkills, MythicMobs e Skript.

## Caps Globais
- **Velocidade**: bônus permanente ≤ +0,02 (`GENERIC_MOVEMENT_SPEED`). Buffs temporários podem chegar a
+0,05 por ≤15 s com cooldown ≥90 s.
- **Resistência**: redução plana ≤60 %. Combinações raça+classe devem multiplicar (não somar) para evitar
imunidades. Imunidade total somente com janela morta ≥30 s.
- **Crítico**: chance extra ≤25 % além do equipamento; dano crítico adicional ≤+50 %.
- **Cura**: cura instantânea ≤35 % da vida máxima; HoTs ≤6 %/s; escudos ≤25 % da vida máxima.
- **Burst ofensivo**: habilidades não-ultimates limitadas a 220 % do dano base em uma janela de 3 s;
ultimates podem chegar a 300 % desde que respeitem cooldown ≥180 s.

## Limites de Uptime
- Passivos automáticos devem manter **≤60 %** de tempo ativo. Configure janelas claras (ex.: 24 s on /
16 s off).
- Buffs de grupo e auras devem compartilhar janelas mortas sincronizadas para impedir empilhamento
indefinido.

## Tiers de Cooldown
| Tipo de habilidade | Cooldown mínimo | Observações |
| --- | --- | --- |
| Chave (rotações principais) | ≥45 s | inclui Fúria, Inspiração, Marca do Caçador |
| Situacional (controle/defesa forte) | ≥90 s | ex.: Totens defensivos, Trombeta Halfling |
| Ultimate / transformação | 180 s – 240 s | permite burst de 12–18 s |

## Parâmetros por Sistema
### Raças
- **Empilhamento de resistências**: máximo de duas fontes >40 %; aplicar multiplicador 0,8 quando raça e
classe oferecem a mesma resistência.
- **Itens raciais**: teleportes/escape ≥240 s; controles em área ≥120 s; sopros elementais padronizados
em 45 s com dano escalado por AuraSkills (STR ou CHA).
- **Atributos bônus**: padrão +2/+1 ou +1/+1/+1. Qualquer exceção deve adicionar desvantagem explícita
(penalidade de atributo ou vulnerabilidade condicional).
- **Cooldown compartilhado**: itens com efeitos similares (ex.: Trombeta Halfling) devem usar o mesmo
cooldown global via Skript para impedir combinação de sub-raças.

### Classes
- **Recursos**: regen base `mana` 5 %/5 s; `fury`, `ki`, `divinity`, `battle_focus`, `sorcery_points`
regeneram 1 carga a cada 30 s fora de combate. Bloqueie regen sob atordoamento ou prisão.
- **Buffs de grupo**: somatório máximo +15 % dano/cura. Se múltiplos buffs ativos, mantenha somente o
mais forte.
- **Habilidades de sobrevivência**: Second Wind, Lay on Hands, Relentless, etc., compartilham cooldown
interno de 300 s.
- **Pets e invocações**: vida máxima = 50 % do invocador, dano em área recebido = 200 % para evitar
bloqueio permanente.
- **Integração AuraSkills**: cada comando deve verificar recurso disponível antes de disparar MythicMobs
(implementado em `classes_core.sk`). Ajuste custos para que a barra de recursos esgote em ~3 rotações.

## Comparativo de Papéis
### Classes
| Papel | Objetivo DPS (PvE) | Mitigação alvo | Ferramentas chave |
| --- | --- | --- | --- |
| Tanque | 55–65 % do DPS topo | 40–50 % DR média | Bárbaro, Paladino, Guerreiro (Champion) |
| Dano | 100 % base | ≤15 % DR | Ladino, Feiticeiro, Guerreiro (Battlemaster), Patrulheiro |
| Suporte | 60–70 % DPS | 20–30 % DR | Bardo, Clérigo, Paladino |
| Controle | 70–80 % DPS | 15–25 % DR | Druida, Mago, Bruxo |

### Raças
| Raça/Subtipo | Força principal | Ajuste recomendado |
| --- | --- | --- |
| Alto Elfo | Retaliação elemental | Verificar danos ≤2,0 e intervalo interno 5 s |
| Elfo da Floresta | Mobilidade + invisibilidade | Exigir tronco com folhas para camuflagem |
| Drow | Teleporte + veneno | Bloquear uso se recebeu dano nos últimos 3 s |
| Anão Profundezas | Cegueira automática | Cooldown interno 20 s, duração máx. 10 s |
| Gnomo Floresta | Invisibilidade condicional | Item com cooldown 420 s compartilhado |
| Halfling | Trombeta de controle | Cooldown global 120 s entre subtipos |
| Meio-Orc | Relentless Endurance | Mensagem clara + cooldown 300 s |
| Humano | Regeneração condicional | Janela morta 20 s após cada ativação |
| Tiefling | Resistência a fogo + velocidade | Exaustão após 3 hits fortes (30 s sem buff) |
| Dragonborn | Sopro elemental | Dano escalado por STR/CHA, cooldown 45 s |

## Ajuste Incremental
- Aplique mudanças graduais de **±5 %** por patch em dano, cura ou mitigação. Valores maiores exigem
testes dedicados e comunicação com a comunidade.
- Sempre registrar métricas antes/depois (DPS médio, HPS, tempo de sobrevivência) em arenas PvE/PvP.

## Checklist de Teste
1. Validar logs de MythicMobs e Skript para confirmar cooldowns reais.
2. Medir dano/cura médio em arenas PvE e PvP a cada atualização major.
3. Garantir que AuraSkills reflita custos definidos nos Skripts (usar `/auraskills player <nick> info`).
4. Atualizar este documento sempre que introduzir nova subclasse ou raça experimental.

## Procedimento de Revisão
- Revisão cruzada mensal entre equipe de desenvolvimento e staff de eventos.
- Registrar ajustes aplicados no changelog interno com métricas de uptime, DPS, HPS e taxa de vitória.
- Em caso de violação dos limites em produção, priorizar hotfix e comunicar jogadores no Discord oficial.
