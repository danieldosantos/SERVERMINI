# Plano de Testes — Raça Halfling

## Escopo
Validar integração completa da raça base Halfling e sub-raças Pés-Leves / Robusto em RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms e HUD PlaceholderAPI.

## Preparação
1. Aplicar grupos via LuckPerms: `lp creategroup ...` conforme `plugins/LuckPerms/races_halfling_groups.txt`.
2. Atribuir `race_halfling` ao jogador de QA e subgrupos conforme cenário.
3. Garantir recarga dos scripts: `/sk reload races_halfling_cmds` e `/sk reload races_cmds`.
4. Distribuir itens de QA via `/kit_racial_halfling`.

## Testes Base (Halfling)
- **Sortudo**
  - Simular dano fatal e crítico (PvE dummy e PvP) observando redução de 50% com chance condizente ao tier.
  - Verificar ICD de 60s via `/sortes` (placeholder deve exibir cooldown >0 após proc).
- **Passo Pequeno, Passo Rápido**
  - Sair de combate repetidas vezes verificando buff de +0.01 velocidade por 10s e uptime ≤33%.
- **Iniciativa Íntima**
  - `/racial_halfling` e item HalflingLuckyCoin ativando skill (cooldown ≥45s, buff 6s).
  - Conferir HUD `{racial_cd_main}` atualizando.

## Testes Sub-raça Pés-Leves
- **Furtividade Natural**
  - Em sombra/vegetação medir redução de detecção 15% com uptime ≤60%.
- **Leveza Graciosa**
  - Entrar em combate repetindo ativamente; confirmar +0.02 velocidade apenas nos 6s iniciais (cap total ≤+0.05).
- **Esquiva Halfling**
  - `/esquiva_halfling` ignora primeiro ataque físico dentro da janela de 2s (testar mob e jogador).
- **Passo Silencioso**
  - `/passo_silencioso` e LightfootCloak: dash de 5 blocos, invisibilidade 2s, remove lentidão.
- **Sorte em Dobro**
  - `/sorte_dobrada`: verificar duração 10s, chance de Sortudo dobrada sem quebrar ICD, dano causado -10% (log de combate).
- **HUD**
  - `/sortes` deve mostrar placeholders atualizados inclusive para `racial_esquiva`.

## Testes Sub-raça Robusto
- **Robustez Stout**
  - Receber veneno contínuo; medir DR efetiva (10–15%) somada com itens/classe respeitando limite 45%.
- **Resiliência Compacta**
  - Duelo em ponte para validar -10% knockback, uptime ≤60% (8s a cada 30s).
- **Forte Como Pedra**
  - `/forte_como_pedra` e StoutStoneCharm: mitigação 20–25% por 6s respeitando DR global.
- **Respirar Fundo**
  - `/respirar_fundo`: remove veneno/queimadura e cura 12% (verificar cap de cura e buff anti-DoT temporário).
- **Imutável**
  - `/imutavel`: 8s de imunidade a knockback + mitigação 15–18% (CD 210s) sem ultrapassar 45% total.
- **HUD**
  - `/sortes` mostra `racial_resistencia_veneno` atualizado durante buffs.

## Integração / QA Geral
- **Comandos**
  - `/racial` lista dicas halfling quando permissão presente.
  - `/kit_racial_halfling_lightfoot` e `/kit_racial_halfling_stout` entregam itens corretos.
- **Itens**
  - Testar NBT `race-lock`: outros jogadores sem permissão não devem ativar (mensagem de erro do Skript).
- **Cooldowns**
  - Confirmar via `/sortes` que valores retornam a `-` quando placeholder inexistente.
- **PvP Stress**
  - Batalhas 3v3: monitorar que combinação de mitigação não ultrapassa 45% e velocidade total ≤+0.05.
- **Logs**
  - Verificar console sem erros ao carregar YAMLs/Skript/skills.

## Regressão
- Reexecutar comandos de outras raças (/fae_passos, /engenho, etc.) para garantir que ajustes no `/racial` não quebraram lógica existente.
