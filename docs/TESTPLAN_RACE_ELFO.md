# Plano de Testes — Raça Elfo

## Preparação
1. Aplicar grupos LuckPerms listados em `plugins/LuckPerms/races_groups.txt`.
2. Recarregar plugins: `raceseffects reload`, `/psa reload skills`, `/mm reload` (itens),
   `/sk reload races_cmds`.
3. Conceder kit apropriado via `/kit_racial_elfo*` ou `give_race_kit` para cada sub-raça.
4. Ativar HUD/scoreboard com placeholders `{cooldown:skill_fae_passos}` e derivados
   para monitorar tempo de recarga.

## Casos de Teste

### 1. Base — Fae Passos
- **Objetivo**: validar dash e mitigação de knockback.
- **Passos**:
  1. Entrar em combate e aguardar sair (triggando Graça Élfica).
  2. Usar `/fae_passos`; conferir deslocamento 5 blocos e resistência a empurrões por 2s.
  3. Confirmar cooldown mínimo de 45s no placeholder.
- **Critérios de Sucesso**: velocidade efetiva ≤ +0.05, cooldown respeitado, buff de knockback
  visível no scoreboard.

### 2. Alto Elfo — Trocas de Truque
- **Objetivo**: garantir seleção e execução de cada truque.
- **Passos**:
  1. Com permissão `race_elfo.alto`, executar `/truque_arcano barreira` e confirmar mensagem.
  2. Usar `/truque_arcano` sem argumentos → lança Barreira Cintilante (escudo 6s).
  3. Repetir para opções `seta` e `piscar`; validar cooldowns (45s, 75s, 60s).
  4. Observar Disciplina Mística: após cada cast, verificar redução de CD em habilidade de utilidade.
- **Critérios**: apenas uma opção ativa por vez, redução de CD máxima 5%, sem cooldowns quebrados.

### 3. Drow — Luz & Sombra
- **Objetivo**: checar penalidade solar e mobilidade sombria.
- **Passos**:
  1. Em área iluminada pelo sol, monitorar debuff (-5% precisão e conjuração).
  2. Entrar em cavernas; confirmar Visão Superior e Sutileza Sombria respeitando uptime ≤60%.
  3. Usar `/luzes_feericas` (CD 75s) e `/passo_umbratico` apenas em sombra (CD 60s).
- **Critérios**: penalidade permanece enquanto exposto, teleporte bloqueado em luz alta, revelação
  marca inimigos por 8s.

### 4. Elfo da Floresta — Mobilidade em Bioma
- **Objetivo**: validar buffs contextuais de velocidade e camuflagem.
- **Passos**:
  1. Em floresta, medir Passo Silvestre: +0.02 velocidade por 15s, uptime ≤60%.
  2. Fora da floresta, iniciar combate e conferir buff temporário +0.01/8s com CD interno 30s.
  3. Ativar `/marca_bosque` (10s de marca) e `/salto_galho` (impulso + slow fall 3s).
- **Critérios**: velocidade total ≤ +0.05, alcance adicional +1 bloco apenas em alvo marcado.

### 5. Transe & Regeneração
- **Objetivo**: garantir regen fora de combate apenas.
- **Passos**:
  1. Sofrer dano, sair de combate e observar regen 2%/s por 10s.
  2. Repetir antes de 45s para garantir cooldown bloqueando reaplicação.
- **Critérios**: regen não ativa em combate, uptime ≤40%.

### 6. Integração de Kits & Permissões
- **Objetivo**: assegurar itens bloqueados por LuckPerms.
- **Passos**:
  1. Com conta sem permissão, tentar usar item -> mensagem de bloqueio (SkillAPI).
  2. Conceder permissão e repetir, confirmando disparo da skill.
- **Critérios**: itens corretos (`ElvenMoonbrooch`, `HighElfCrystal`, `DrowMoonbrooch`,
  `ForestLeafCharm`) só funcionam com permissões herdadas.

## Regressão
- Reexecutar `/racial` para cada sub-raça e confirmar mensagens contextuais.
- Monitorar scoreboard para garantir placeholders atualizados após recargas.
