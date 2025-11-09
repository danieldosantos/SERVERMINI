# Raça Halfling (Base + Subraças)

## Visão Geral
Os halflings combinam sorte controlada, mobilidade leve e resiliência contra venenos. Esta documentação cobre a raça base e as sub-raças **Pés-Leves** e **Robusto**, com suas integrações em RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms e PlaceholderAPI.

## Estrutura de Arquivos
- `plugins/RacesEffects/races/halfling_base.yml`
- `plugins/RacesEffects/races/halfling_lightfoot.yml`
- `plugins/RacesEffects/races/halfling_stout.yml`
- `plugins/ProSkillAPI/skills/races/halfling/*.yml`
- `plugins/MythicMobs/Items/races_halfling_items.yml`
- `plugins/Skript/scripts/races_halfling_cmds.sk`
- `plugins/LuckPerms/races_halfling_groups.txt`
- `docs/TESTPLAN_RACE_HALFLING.md`
- `docs/BALANCE_SHEET_RACE_HALFLING.csv`

## Traços Raciais (RacesEffects)
### Base — Halfling
- **Sortudo**: chance progressiva (20% → 35%) de reduzir dano fatal/crítico em 50% com ICD de 60s.
- **Passo Pequeno, Passo Rápido**: +0.01 velocidade ao sair de combate por 10s (CD 30s).
- Ativo: **Iniciativa Íntima** (45s) gatilho via ProSkillAPI.

### Sub-raça — Pés-Leves
- **Furtividade Natural**: +15% stealth em sombra/vegetação até 60% uptime.
- **Leveza Graciosa**: +0.02 velocidade por 6s ao entrar em combate (ICD 45s).
- Ativos: **Esquiva Halfling** (75s), **Passo Silencioso** (60s), **Sorte em Dobro** (210s, ultimate).

### Sub-raça — Robusto
- **Robustez Stout**: DR 10–15% contra veneno com DR empilhável.
- **Resiliência Compacta**: -10% knockback por 8s a cada 30s (≤60% uptime).
- Ativos: **Forte Como Pedra** (90s), **Respirar Fundo** (75s), **Imutável** (210s, ultimate).

## Habilidades (ProSkillAPI)
Cada habilidade possui arquivo dedicado com tiers T1–T4. Cooldowns seguem diretrizes (≥45s). Modificadores de mitigação respeitam cap global de 45% e velocidade adicional mantém limite +0.05.

## Itens (MythicMobs)
- **HalflingLuckyCoin**: ativa Iniciativa Íntima (race-lock `race_halfling`).
- **LightfootCloak**: ativa Passo Silencioso (race-lock `race_halfling.lightfoot`).
- **StoutStoneCharm**: ativa Forte Como Pedra (race-lock `race_halfling.stout`).

## Comandos (Skript)
- `/racial_halfling`, `/sortes`.
- `/esquiva_halfling`, `/passo_silencioso`, `/sorte_dobrada` (Pés-Leves).
- `/forte_como_pedra`, `/respirar_fundo`, `/imutavel` (Robusto).
- Kits: `/kit_racial_halfling`, `/kit_racial_halfling_lightfoot`, `/kit_racial_halfling_stout`.
- Right-click bindings dos itens MythicMobs.
- Função `halflingRacialHelp` adicionada ao `/racial` global.

## Permissões (LuckPerms)
- Grupo base: `race_halfling`.
- Subgrupos: `race_halfling.lightfoot`, `race_halfling.stout` herdando do base.

## PlaceholderAPI / HUD
Placeholders expostos via RacesEffects:
- `{racial_cd_main}` → `%raceseffects_racial_cd_main%`
- `{racial_esquiva}` → `%raceseffects_racial_esquiva%`
- `{racial_sorte}` → `%raceseffects_racial_sorte%`
- `{racial_resistencia_veneno}` → `%raceseffects_racial_resistencia_veneno%`

Comando `/sortes` mostra os valores atuais e aplica fallback quando placeholder retornar vazio.

## Diretrizes de Balanceamento
- Passivos ≤60% uptime.
- Cooldowns ≥45s, ultimates 210s.
- Caps globais respeitados (velocidade, mitigação, cura, área).
- Sorte nunca fornece imunidade ou substitui rolagem: reduz dano em 50% com ICD.
- Resistências aplicam DR com fontes adicionais.

## Integração
1. Importar grupos no LuckPerms.
2. Carregar arquivos no RacesEffects, ProSkillAPI e MythicMobs.
3. Executar `/sk reload races_halfling_cmds` e `/sk reload races_cmds` após atualização.
4. Configurar scoreboard/HUD usando placeholders.

## Referências Cruzadas
- Plano de testes: `docs/TESTPLAN_RACE_HALFLING.md`.
- Balanceamento quantitativo: `docs/BALANCE_SHEET_RACE_HALFLING.csv`.
