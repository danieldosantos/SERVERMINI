# Plano de Testes – Classe Druida

## Ambiente
- Servidor Paper 1.20.x com ProSkillAPI (ou SkillAPI compatível), MythicMobs, Skript, LuckPerms, PlaceholderAPI, RacesEffects, CMI/Essentials para kits.
- Jogador com raça ajustável (elfo da floresta, humano, anão) para validar diminishing returns.
- Plugins de logging de dano/curas habilitados (CombatLogX, etc.) para aferição de caps.

## Testes PvE (10 casos)
1. **Forma Selvagem – duração e recarga**
   - Passos: `/kit_druida`, ativar `/forma`, medir duração (cronômetro) e observar placeholder `%skills_wildshape_time%`.
   - Resultado: 60s ±1s, cooldown base 180s, recarga de 1 carga automática após 180s fora de combate.
2. **Conjuração de Terreno (Land)**
   - Passos: habilitar grupo `class_druid.land`, usar `/terra_solo` sobre mobs; verificar slow 40% por 8s em raio 6 blocos.
   - Resultado: Mobs com Slowness II (~40%), lama não excede 8 blocos.
3. **Aura do Bosque**
   - Passos: usar `/terra_aura` em aliados NPC; medir regen 20% natural + veneno 50% resist.
   - Resultado: HUD confirma regen adicional sem exceder 6%/s total.
4. **Chicote de Vinhas**
   - Passos: testar em mob médio e chefe; medir puxão 6 blocos, chefe sofre metade.
   - Resultado: mob comum puxado, chefe <=3 blocos.
5. **Coração do Mundo**
   - Passos: ativar e monitorar cura 4%/s e resistência 25% por 15s em 10 blocos.
   - Resultado: cura acumulada 60% (cap 6%/s) e resistência não excede 45% total.
6. **Forma Ursina Primordial**
   - Passos: trocar para círculo da Lua, ativar `/lua_urso`; medir bônus de vida e cleave.
   - Resultado: HP aumenta 30%, dano em cone atinge múltiplos mobs.
7. **Rugido Lunar**
   - Passos: usar `/lua_rugido` em mobs; medir medo 3s e debuff -15% dano por 8s.
   - Resultado: inimigos fogem por ≤3s, dano reduzido em logs.
8. **Chamado do Lobo Espiritual**
   - Passos: com `class_druid.shepherd`, invocar lobo; medir duração 12s e dano 80% a cada 2s.
   - Resultado: lobo desaparece no tempo correto e dano respeita intervalo.
9. **Chuva Revigorante**
   - Passos: ativar em aliados com queimadura; garantir remoção e cura 4%/s por 8s.
   - Resultado: queimaduras limpas e cura acumulada 32% (cap mantido).
10. **Conclave Primal**
    - Passos: ativar ULT, verificar buffs (resist +15%, velocidade +0.03, cura recebida +12%) e invocações simultâneas.
    - Resultado: três espíritos presentes por 16s, buffs aplicados apenas até limites.

## Testes PvP (10 casos)
1. **Forma Selvagem sem imunidades totais**
   - Passos: duelista aplica controles (stun 2s); confirmar que druida ainda pode ser controlado.
   - Resultado: controle aplicado normalmente, sem imunidade.
2. **Caps de velocidade**
   - Passos: usar Forma Felina + buff racial de elfo da floresta; medir velocidade ≤ +0.05.
   - Resultado: Placeholder de velocidade confirma limite respeitado (aplica 1,25× máximo).
3. **Medo Rugido Lunar**
   - Passos: usar `/lua_rugido` em jogador com alta resistência; confirmar medo ≥1.5s ≤3s.
   - Resultado: duração reduzida por diminishing returns, nunca >3s.
4. **Solo Entrópico em jogadores**
   - Passos: aplicar em duelistas; observar slow 40% e DoT 5%/s (não crítico).
   - Resultado: logs mostram dano constante e slow compatível.
5. **Chicote de Vinhas**
   - Passos: puxar jogador com armadura pesada; medir deslocamento 6 blocos e sangramento 5%/s.
   - Resultado: jogador arrastado, DoT cumulativo ≤30% total.
6. **Escudo Ancestral compartilhado**
   - Passos: formar party 3 jogadores, ativar `/pastor_escudo`, registrar absorção total 200 pontos.
   - Resultado: logs mostram absorção dividida (≈66 por aliado) e cura final 30 pontos.
7. **Convergência de Terreno (Lua)**
   - Passos: com círculo da Lua, usar `/terra_*` base; verificar camuflagem 50% uptime e redução projéteis 20%.
   - Resultado: invisibilidade intermitente (1s on/1s off) e dano ranged reduzido em logs.
8. **Vínculo Natural em PvP**
   - Passos: curar aliado em combate; medir HoT 3%/s por 4s sem exceder 6%/s com outras fontes.
   - Resultado: log de cura respeita cap e remove queimaduras.
9. **Chuva Revigorante + Forma Selvagem**
   - Passos: aliado em Forma Selvagem na chuva; medir resistência elemental adicional 5% (não excedendo 45%).
   - Resultado: total de redução contabilizado ≤45%.
10. **Avatar da Lua**
    - Passos: ativar `/lua_avatar` e usar consecutivamente `/lua_urso` e `/lua_felina`; confirmar ausência de custo adicional e duração 18s.
    - Resultado: habilidades funcionam sem gastar cargas extras, buff expira no tempo esperado.

## Métricas de Aprovação
- Nenhum teste excede caps definidos (velocidade, cura em área, resistência total).
- Todas as habilidades disparam via itens MythicMobs e comandos Skript correspondentes.
- Placeholders atualizam a HUD a cada 5 segundos sem mensagens vazias.
- Formas animais não concedem imunidade total a dano ou controle.
