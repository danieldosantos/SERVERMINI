# Plano de Testes — Raça Draconato

## Preparação
1. Configurar servidor em ambiente de staging com plugins RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms e PlaceholderAPI.
2. Aplicar comandos do arquivo `plugins/LuckPerms/races_dragonborn_groups.txt`.
3. Garantir que recursos personalizados (`breath_charge`) estejam definidos no ProSkillAPI.
4. Carregar MythicMobs e Skript, conferindo ausência de erros na console.
5. Distribuir itens focais via `/kit_racial_draco` para cada tester.

## Testes PvE (≥5)
1. **Dungeon Elemental (Tier 2):**
   - Objetivo: validar que o sopro acerta cone de 6 blocos e consome carga.
   - Critério: dano alinhado a 120–135% (T1/T2) sem ultrapassar uptime de resistência (≤57%).
2. **Chefe de Gelo (Tier 3):**
   - Objetivo: verificar vulnerabilidade dos draconatos de fogo a dano gélido quando `Lâmina Ígnea` em recarga.
   - Critério: dano recebido +10% durante janela de vulnerabilidade.
3. **Assalto em Fortaleza (Tier 3):**
   - Objetivo: medir empilhamento de `Corrosão` + `Erosão Concentrada` sem passar de -15% defesa total.
   - Critério: stacks limitados a 2 + marca extra ≤8% adicional, total ≤18%.
4. **Campo Nevado (Tier 2):**
   - Objetivo: conferir `Carapaça Invernal` ativando em biomas frios e uptime ≤40%.
   - Critério: log de combate confirma buff 12s com cooldown 45s.
5. **Pântano Venenoso (Tier 4):**
   - Objetivo: validar duração da `Bruma Ácida` (6s) e veneno leve.
   - Critério: uptime efetivo da área ≈8% (6/75) e sem superar caps de dano ao longo do encontro.

## Testes PvP (≥5)
1. **Arena 1v1 — Sopro vs Guerreiro:**
   - Objetivo: confirmar cooldown global de 55s e recarga de carga somente fora de combate (>15s sem dano).
   - Critério: no máximo 1 sopro a cada minuto; indicador HUD atualiza.
2. **Arena 2v2 — Dash Relâmpago:**
   - Objetivo: validar `Estalo Dracônico` percorre 6 blocos e stun ≤1.5s.
   - Critério: registro de movimento confirma distância, log de controle respeita limite <4s combinados.
3. **Arena 3v3 — Controle Gélido:**
   - Objetivo: usar `Congelamento Breve` + passivo `Gélido` mantendo controle total ≤4s.
   - Critério: root 2s + slow 3s com DR aplicado, sem exceder limites.
4. **Arena 1v1 — Duelista Ácido:**
   - Objetivo: verificar vulnerabilidade a raio quando `Vapores Tóxicos` está inativo.
   - Critério: dano elétrico recebido +10% durante janela de recarga.
5. **Objetivo King-of-the-Hill — Nuvem Venenosa:**
   - Objetivo: medir eficácia da `Bruma Ácida` em zoneamento sem permanência exagerada.
   - Critério: área ativa ≤8% do tempo, veneno não ultrapassa 25% de dano por golpe.

## Pós-Testes
- Registrar feedback de balanceamento e ajustar multiplicadores apenas via SkillAPI.
- Revisar logs de RacesEffects para uptime de passivos e garantir DR aplicado.
- Atualizar `docs/BALANCE_SHEET_RACE_DRAGONBORN.csv` se ajustes forem necessários.
