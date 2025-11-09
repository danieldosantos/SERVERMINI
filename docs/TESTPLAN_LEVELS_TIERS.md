# Plano de Testes – Sistema Global de Níveis & Tiers

## Ambiente
- Servidor Paper 1.20+/1.21 com os plugins: ProSkillAPI, LuckPerms, PlaceholderAPI, Skript (+ dependências), MythicMobs.
- Scripts carregados: `plugins/Skript/scripts/level_system.sk`.
- Placeholders registrados via PlaceholderAPI (`/papi ecloud` conforme necessário).
- Scoreboard ativo pelo Skript.

## Casos de Teste

### 1. Progressão PvE Variada
1. Criar personagem nível 1 sem classe/raça especial.
2. Ativar MythicMobs com mobs normais configurados para usar `xp_normal`.
3. Derrotar uma sequência variada de mobs (≥5 tipos diferentes).
4. **Esperado:**
   - XP sobe conforme tabela (nível 1→2 requer ~100 XP, 2→3 ~303 XP etc.).
   - HUD atualiza após cada kill (XP absoluto, barra e percentual).
   - Nenhuma mensagem de DR, pois kills foram variados.

### 2. Transição de Tier em Nível 5, 11 e 17
1. Elevar personagem até nível 5 utilizando `/dar_xp` (ex.: `/dar_xp <player> 1000`).
2. Repetir para níveis 11 e 17.
3. **Esperado:**
   - Nível 5 → jogador passa para `tier_t2`, recebe mensagem/título.
   - Nível 11 → `tier_t3` aplicado, grupos anteriores removidos.
   - Nível 17 → `tier_t4` aplicado, confirmando hierarquia.
   - Verificar com `/lp user <player> info` se apenas o grupo correto permanece.

### 3. HUD MMORPG
1. Executar `/nivel` e observar scoreboard.
2. Verificar se linhas exibem:
   - Nível atual
   - Tier atual (nome e ícone)
   - Barra `█/▒` coerente
   - XP absoluto (`xp_atual/xp_proximo`)
   - Percentual com uma casa decimal
3. **Esperado:** Nenhuma linha duplicada, atualização a cada ~2s.

### 4. Gate de Habilidades & Itens
1. Selecionar classe que tenha habilidades travadas por Tier (ex.: Guerreiro com habilidades nos nós `tier2/3/4`).
2. Antes do nível 5, tentar aprender habilidade de Tier 2 → deve falhar (falta permissão `tier_t2`).
3. Após subir para nível 5, repetir → habilidade liberada.
4. Repetir para Tier 3/4 (níveis 11 e 17).
5. **Esperado:** Nenhum desbloqueio antecipado.

### 5. Validação Anti-Grind
1. Enfrentar o mesmo mob (mesmo internal name) pelo menos 4 vezes em ≤45s.
2. Monitorar chat/log para mensagem de DR.
3. Confirmar via `/nivel` que XP somado é inferior ao base (40% do valor).
4. Aguardar >45s e matar o mesmo mob novamente → XP volta ao normal.

### 6. Comandos Administrativos
1. `/dar_xp <player> <quantia>` → aplica XP e atualiza HUD.
2. `/set_lvl <player> <1-20>` → ajusta nível e sincroniza Tier.
3. `/tier_info` → mostra próximo Tier e lista de desbloqueios.
4. `/levelxp <player> <xp> <categoria> <mob>` → validar integração com MythicMobs (executar via console).

### 7. Boss Cooldown
1. Configurar um MythicMob chefe usando `xp_boss`.
2. Derrotar o chefe duas vezes em <600s.
3. **Esperado:** a segunda derrota não executa o drop de XP (verificar logs/comando).

### 8. Compatibilidade com Raças
1. Selecionar raça com resistência (ex.: anão com resistência a veneno).
2. Checar se, ao atingir Tier com resistência semelhante, a soma não excede 45%.
3. **Esperado:** Resistências permanecem dentro do cap global.

## Critérios de Aceite
- Nenhum erro em console ao carregar os arquivos (Skript, MythicMobs, ProSkillAPI, LuckPerms).
- Tiers aplicados automaticamente nos níveis 5, 11, 17.
- HUD atualiza sem atrasos perceptíveis.
- Skills/itens permanecem bloqueados até que o Tier correto seja atingido.
- DR anti-grind efetivo, evitando exploração por farm em área.

