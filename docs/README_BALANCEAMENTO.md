# Análise e Diretrizes de Balanceamento

Este relatório avalia o estado atual das raças e classes do servidor, identifica vantagens desproporcionais e define recomendações numéricas/qualitativas para manter a experiência equilibrada em PvE e PvP.

## Metodologia Resumida
1. Consolidação dos traços raciais e itens ativos a partir de `README_RACAS_UNIFICADO.md`.
2. Avaliação dos efeitos previstos para classes conforme documentação existente (Bárbaro, Bardo, Bruxo) e diretrizes do PHB para classes ainda em adaptação.
3. Classificação dos impactos em cinco eixos: atributos, resistências, mobilidade, itens exclusivos e passivos de classe.

## Indicadores-Chave
- **Faixa de bônus de atributo recomendada**: até ±4 pontos totais distribuídos, com máximo de +2 por atributo básico.
- **Redução de dano/resistências**: limitar imunidades completas a cenários de nicho; resistências amplas devem equivaler a 20–25 % de mitigação efetiva.
- **Modificadores de velocidade**: manter entre ±10 % comparado ao padrão (±0,02 em `generic.movement_speed`).
- **Itens raciais**: cooldown mínimo de 2 min para habilidades de controle forte e 3–5 min para mobilidade instantânea.
- **Passivos de classe**: uptime médio não superior a 60 % sem custo ativo.

## Riscos de Desequilíbrio Identificados
- **Acúmulo de resistências elementais** em raças como Dragonborn (subtipos) e Tiefling pode trivializar encontros elementais sem contrapartida.
- **Efeitos de regeneração humana** têm impacto alto em PvP prolongado; necessita de janela de reativação clara (≥20 s) para impedir “loop infinito”.
- **Dragonborn Drow/Profundezas** combinados com classes de burst podem forçar composições específicas para resposta.
- **Itens com teleporte (Teia de Drow)** representam vantagem de fuga; manter limitação de cargas e checagem de combate.

## Recomendações Numéricas
### Bônus de Atributos
- Ajustar qualquer bônus racial inicial para o intervalo +2/+1 (ex.: +2 DEX Elfo da Floresta, +1 WIS) quando forem integrados ao sistema de atributos do ProSkillAPI.
- Para classes, aplicar progressões específicas por tier: +1 atributo principal a cada 2 marcos de nível e +1 secundário a cada 3 marcos.

### Resistências
- Converter resistências completas em reduções percentuais:
  - Tiefling e Dragonborn Fogo: manter Fire Resistance, mas introduzir “exaustão de resistência” (perde o efeito por 30 s após absorver 3 fontes de dano intenso).
  - Dragonborn Ácido/Veneno: reduzir imunidade para 60 % de mitigação e permitir que efeitos de chefes ignorem 20 % da redução.
  - Halfling Stout: garantir que a resistência a veneno seja equivalente a Resistência I (20 %) com tempo de reativação de 15 s.

### Velocidade
- Limitar bônus permanentes a +0,02; valores acima devem ser temporários (≤15 s) com cooldown ≥90 s.
- Para penalidades (ex.: Drow ao sol), limitar a -0,02 para evitar inviabilidade total.

### Itens Raciais
- Padronizar recargas: teleportes ≥240 s; invisibilidade ≥180 s; sopros de Dragonborn ≥45 s entre usos.
- Habilitar logs de entrega para auditoria (`/checkitems`) e definir stack máximo = 1.
- Incluir requisito de “sem combate por 5 s” antes de ativar itens utilitários (pena, broche, teia) para evitar uso abusivo em perseguições.

### Efeitos Passivos de Classe
- Garantir que passivos defensivos (ex.: Fúria do Bárbaro) reduzam dano entre 20–25 % e possuam custo (consumo de recurso, tempo limitado).
- Buffs de grupo (Bardo, Paladino futuro) devem aplicar +10 % de eficácia de dano/cura e nunca acumular com buffs idênticos.
- Controlar habilidades de “seguro de vida” (Relentless, Second Wind, etc.) com cooldown global ≥5 min.

## Tabelas Comparativas
### Raças
| Raça/Subtipo | Forças Principais | Fraquezas Existentes | Ajustes/Nerfs Propostos | Possíveis Buffs |
| --- | --- | --- | --- | --- |
| Alto Elfo | Retaliação automática, Darkvision | Sem mobilidade extra | Introduzir cooldown interno de 5 s por retaliação | Opcional: conceder foco arcano com carga limitada |
| Elfo da Floresta | Velocidade + invisibilidade | Dependente de ambiente | Exigir linha de visão livre para invisibilidade | Adicionar bônus de salto curto (≤15 s) |
| Drow | Teleporte, efeitos em profundidade | Fraqueza à luz | Manter 3 cargas; bloquear uso em combate após dano nos últimos 3 s | Reduzir fraqueza noturna para -10 % dano apenas |
| Anão Colina | Vida extra, resistência | Baixa mobilidade | Nenhum (equilibrado) | Bônus situacional em mineração (haste curta) |
| Gnomos | Darkvision universal | Sem diferencial | Adicionar proficiência em itens mecânicos (cooldown reduzido 10 %) | — |
| Halfling Stout | Resistência a veneno | Sem mobilidade | Ajustar para Resistência I com cooldown | Buff opcional de furtividade em PvE |
| Meio-Elfo | Mobilidade aquática, slow fall | Sem defesa extra | Limitar pena a 2 cargas antes de recarga total | Conceder bônus social (desconto NPCs) |
| Meio-Orc | Relentless, itens ofensivos | Pouco controle | Cooldown do Relentless ≥5 min | Buff: bônus de dano sob 30 % de vida |
| Humano | Regeneração, acesso a habilidades | Sem resistências | Adicionar janela morta de 20 s após cura | Bônus adaptativo +5 % XP |
| Tiefling | Resistência a fogo, velocidade Nether | Vulnerável a água | Aplicar exaustão de resistência após 3 hits | Buff: visão no Nether (night vision curta) |
| Dragonborn Elementais | Sopro forte, resistências | Cooldown curto | Padronizar cooldown ≥45 s; dano escalonado por nível | Buff: resistência parcial cruzada (+10 %) |

### Classes (Planejamento PHB)
| Classe | Forças Atuais/Previstas | Riscos | Nerf/Buff Sugerido |
| --- | --- | --- | --- |
| Bárbaro | Dano + redução 25 %, Fúria 120 s | Tank excessivo em PvP | Adicionar consumo de recurso (ex.: 3 usos/dia) |
| Bardo | Buff de aliado, canção de cura | Empilhamento de buffs | Limitar Inspiração ativa a 3 alvos simultâneos |
| Bruxo | Hex, rajada com bônus | Controle em rajada | Cooldown global de 10 s no Hex por alvo |
| Clérigo | Cura em área, proteção | Sobrecura | Limitar cura máxima por ciclo a 30 % vida total |
| Druida | Transformações, controle terreno | Mobilidade extrema | Taxar formas fortes com tempo máximo 60 s |
| Guerreiro | Burst com Action Surge | Abuso de resets | Action Surge com 120 s de recarga |
| Monge | Alta mobilidade, deflexão | Evasão quase constante | Recurso de ki com pool de 4 usos + recarga 30 s |
| Paladino | Aura defensiva, cura spot | Aura permanente | Aura ativa somente enquanto recurso sagrado >0 |
| Patrulheiro | Dano consistente, suporte animal | Animais invencíveis | Tornar familiares vulneráveis ao dano em área |
| Ladino | Furtividade, ataque furtivo | Burst em PvP | Cooldown de 6 s entre ataques furtivos completos |
| Feiticeiro | Magia de dano, metamagia | Spam de projéteis | Limitar conversão de feitiços em slots extras |
| Mago | Controle utilitário | Encadeamento de efeitos | Diminishing returns em efeitos de controle repetidos |

## Diretrizes PvP vs PvE
- **PvP**: priorizar janelas de vulnerabilidade claras após cada habilidade forte (teleporte, sopro, cura). Tolerância máxima de 2 habilidades defensivas consecutivas antes de uma janela morta obrigatória.
- **PvE**: escalonar criaturas e chefes para explorar fraquezas raciais (ex.: mobs venenosos contra raças sem mitigação). Fornecer contrajogadas em loot (poções, runas) para evitar “hard counters” sem saída.
- **Progressão**: alinhar desbloqueios de efeitos poderosos a marcos narrativos (tiers) para evitar picos abruptos. A cada tier, executar revisão cruzada das métricas de dano médio, tempo para abate e sobrevivência média.

## Manutenção Contínua
- Registrar métricas de combate (dano causado/recebido, cura, uptime de buffs) via placeholder API ou logs customizados.
- Executar ciclos trimestrais de balanceamento com checklist: verificar resistências acumuladas, itens raros e novas sinergias de scripts.
- Envolver jogadores em testes A/B controlados antes de aplicar mudanças permanentes.

