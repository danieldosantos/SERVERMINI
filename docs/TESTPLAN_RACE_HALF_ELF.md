# Plano de Testes – Raça Meio-Elfo

Objetivo: validar equilíbrio PvE/PvP, integrações com plugins e respeito aos caps
de uptime, cooldowns e resistências.

## Preparação Geral

1. Aplicar grupo LuckPerms apropriado (`race_half_elf` ou subgrupo).
2. Entregar item focal via MythicMobs e confirmar NBT bloqueando uso indevido.
3. Garantir scoreboard PlaceholderAPI exibindo `{racial_cd_main}`, `{racial_uptime}`
   e `{racial_charges}` com valores atualizados.
4. Usar `/kit_racial_halfelf` para receber itens de QA quando necessário.

## Cenários PvE

### 1. Cerco Diplomático (Padrão – Versatilidade)
- **Setup:** Dungeon chefe T2 com party Paladino (Tank), Bardo (Suporte), Meio-Elfo
  padrão (Tier 2), Arqueiro (DPS).
- **Passos:**
  1. Selecionar `diplomata` + `calma` via `/meioelfo_modo`.
  2. Monitorar uptime de cada mini-bônus (<60%) enquanto o jogador alterna entre
     Tato Social e suporte ativo.
  3. Registrar mitigação total do alvo marcado (≤45%).
- **Resultados esperados:** buff secundário distribui ~50% do primário; nenhuma
  combinação ultrapassa caps defensivos.

### 2. Ritual Arcano (Herança Alto Elfo)
- **Setup:** Encontro PvE com ondas de mobs resistentes à luz, grupo com Mago e
  Meio-Elfo alto (Tier 3).
- **Passos:**
  1. Selecionar `Faísca` via `/truque_arcano selecionar faisca`.
  2. Alternar entre Faísca e Luz Reveladora em rotação de 3 minutos.
  3. Monitorar consumo de mana (≤12 por uso) e cooldown efetivo (45–55s).
- **Resultados esperados:** truques produzem dano/controle equivalente a habilidade
  leve, sem trivializar resistência à luz.

### 3. Emboscada Noturna (Herança Drow)
- **Setup:** Expedição subterrânea T3 com Ladino Sombra + Meio-Elfo drow.
- **Passos:**
  1. Confirmar visão 28 blocos em cavernas.
  2. Usar Passo Sombrio em áreas com luz ≤7; tentar ativar sob luz 14+ para validar
     bloqueio.
  3. Avaliar Luzes Feéricas contra mobs invisíveis.
- **Resultados esperados:** penalidade solar ativa em áreas descobertas, DR evita
  stealth permanente (duração total ≤60%).

### 4. Patrulha Silvestre (Herança Elfo da Floresta)
- **Setup:** Bioma floresta T2 com Ranger + Meio-Elfo floresta.
- **Passos:**
  1. Rodar Passo Silvestre entrando/saindo da floresta; medir uptime médio (~50%).
  2. Usar Camuflagem Silvestre enquanto o Ranger marca inimigos.
  3. Lançar Flecha do Bosque alternando com habilidades de classe para confirmar DR
     do bônus de projétil.
- **Resultados esperados:** velocidade total ≤0.05, marca adiciona ≤15% dano
  combinado.

## Cenários PvP

### 5. Arena 2v2 Controle (Herança Alto Elfo)
- **Setup:** Meio-Elfo alto (Tier 2) + Mago vs Ladino + Clérigo.
- **Passos:**
  1. Alternar entre truques Faísca e Mão conforme situação.
  2. Monitorar cooldowns com `{racial_cd_main}` e garantir 45–55s reais.
  3. Registrar efetividade do reveal contra furtividade do Ladino.
- **Resultados esperados:** controle auxilia sem anular furtividade; custo de mana
  compatível com rotação de 1 min.

### 6. Arena Iluminada vs Subterrânea (Herança Drow)
- **Setup:** Duas lutas 1v1 (Tier 3) vs Guerreiro; primeira em arena solar, segunda
  subterrânea.
- **Passos:**
  1. Em arena solar, medir impacto do debuff (-5% precisão, -0.01 velocidade).
  2. Em arena subterrânea, usar Passo Sombrio e Luzes Feéricas na rotação.
  3. Comparar TTK entre arenas (diferença esperada ≥12%).
- **Resultados esperados:** penalidade solar compensa mobilidade extra; Passo
  Sombrio permanece limitado a 90s CD.

### 7. Skirmish Florestal 5v5 (Herança Elfo da Floresta)
- **Setup:** Combate 5v5 com composição Ranger/Monge/Meio-Elfo floresta + 2 DPS vs
  party focada em melee.
- **Passos:**
  1. Registrar uptime de Passo Silvestre ({racial_uptime}).
  2. Confirmar camuflagem cancelando ao atacar.
  3. Avaliar marca da Flecha do Bosque com e sem buffs de Ranger.
- **Resultados esperados:** bônus de velocidade/dano permanecem dentro dos caps;
  camuflagem não concede invisibilidade total.

## Cenário Misto de Sinergia

### 8. Operação de Apoio Móvel
- **Setup:** Equipe com Paladino (Tank), Bardo (Suporte), Ranger (DPS), Meio-Elfo
  padrão e Meio-Elfo floresta.
- **Passos:**
  1. Meio-Elfo padrão usa Tato Social no Paladino enquanto o Ranger recebe Flecha
     do Bosque para burst.
  2. Medir mitigação combinada (≤45%) e dano adicional do Ranger (≤15%).
  3. Registrar comunicação HUD (actionbar, scoreboard) para ambos os meio-elfos.
- **Resultados esperados:** efeitos convergem sem ultrapassar caps; HUD mostra
  cooldowns independentes; nenhum jogador obtém stealth permanente.

## Critérios de Aprovação

- Todos os comandos Skript executam sem permissão incorreta.
- MythicMobs só ativa skills se o jogador estiver no grupo apropriado.
- Logs não apresentam erros ao carregar YAML/Skript/Itens.
- Caps globais respeitados nos cenários acima.
