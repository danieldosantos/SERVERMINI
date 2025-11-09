# Plano de Testes – Raça Gnomo

## Preparação
- Ativar grupos LuckPerms (`race_gnome`, `race_gnome.forest`, `race_gnome.rock`).
- Carregar itens via MythicMobs (`mm reload items`), ProSkillAPI (`/psa reload`), RacesEffects (`/raceseffects reload`) e Skript (`/sk reload races_gnome_cmds`).
- Garantir scoreboard PlaceholderAPI com placeholders `{racial_cd_main}`, `{racial_uptime}`, `{racial_resist_mental}`, `{racial_shield}`, `{racial_charges}`.
- Usar `/kit_racial_gnomo <jogador> all` para QA e resetar cooldowns com `/raceseffects reset`.

## Cenários PvE
1. **Chefe com Fear/Stun (Base):**
   - Ambiente: sala de boss arcano T2 com fear/stun periódico (≥30s).
   - Métrica: `Astúcia Gnomesca` ativa ≤20% do tempo; verificar DR mental na HUD.
   - Garantir que `Pequena Estatura` não ultrapasse caps de mitigação.
2. **Arena de Projéteis (Base):**
   - Ambiente: arena com esqueletos/ballistas T2.
   - Validar redução de knockback durante 12s (50% uptime) e Engenho Rápido reduzindo recarga em 10% por 8–10s.
   - Confirmar charges resetam após 15s fora de combate.
3. **Floresta Densa de Lobos (Floresta):**
   - Ambiente: floresta `biome_tag=forest` com 6 lobos hostis.
   - Usar `Passos Silvestres` e `Sussurro Amigo` → charmar lobos 6s, sem afetar jogadores.
   - Monitorar que velocidade total ≤0.05 acima do normal.
4. **Horda de Animais + Ultimate (Floresta):**
   - Ambiente: clareira com waves de animais neutros/agressivos.
   - Acionar `Círculo Silvestre` (CD 210s) e medir bônus de velocidade +10% na HUD; inimigos -10% precisão.
   - Repetir após 210s para validar uptime.
5. **Corredor de Arqueiros com Ácido (Rocha):**
   - Ambiente: dungeon com arqueiros e slimes ácidos.
   - `Engenho Gnomesco` gera escudo 60–100; `Gadget Estático` reduz projéteis 30% por 8s.
   - Checar que `Resiliência a Toxinas` entra em cooldown (24s) com uptime ≤33%.

## Cenários PvP
1. **Duelo Controlador Mental vs Gnomo (Base):**
   - Jogador A (controle mental) vs Gnomo base.
   - Verificar que Astúcia Gnomesca + Intuição Arcana não ultrapassam 45% mitigação; sem lockdown >4s.
2. **3v3 com Foco em Recargas (Base):**
   - Time inclui gnomo base, artífice e guerreiro.
   - Engenho Rápido + itens de classe respeitam cap +0.05 velocidade de recarga; monitorar scoreboard de charges.
3. **Duelo em Mapa Vegetação (Floresta):**
   - Gnomo floresta vs arqueiro/ranger.
   - Usar `Salto de Galho` entre folhas; garantir mensagem de erro ao tentar usar sem blocos válidos.
   - Camuflagem Natural ativa ≤50% do tempo.
4. **Captura de Ponto com Círculo Silvestre (Floresta):**
   - Partida 4v4; gnomo floresta ativa ultimate para defender ponto.
   - Medir redução de precisão inimiga e bônus de velocidade; validar DR com efeitos de classe.
5. **Defesa contra Spam de Projéteis (Rocha):**
   - Gnomo rocha + time defendem objetivo contra arqueiros/magos.
   - Usar `Gadget Estático` e `Cúpula de Engenho` alternadamente, sem ultrapassar mitigação de 45%.
   - Verificar que cada aliado bloqueia no máximo 1 projétil pesado por ativação.

