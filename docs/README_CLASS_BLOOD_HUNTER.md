# Classe Blood Hunter (Caçador de Sangue)

A classe **Blood Hunter** combina risco e recompensa ao converter o próprio sangue em poder ofensivo. Esta implementação cobre a classe base e as quatro ordens (Ghostslayer, Profane Soul, Mutant e Lycan) com integrações para ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI e RacesEffects.

## Visão Geral
- **Função primária:** DPS híbrido com controle situacional e auto-dano verdadeiro controlado.
- **Recurso chave:** `Hemocraft` (pool inicial 100 no tier 1, +25 por tier, regen 5/s após 8s fora de combate).
- **Risco:** Cada rito ou maldição relevante consome Hemocraft e aplica auto-dano limitado (12% T1/T2, 15% T3/T4) sem permitir morte.
- **Ferramentas principais:** Rito Carmesim (dano elemental), Marca de Presa (foco em alvo único), Concentração de Sangue (janela ofensiva).
- **HUD PlaceholderAPI:** `{hemocraft}`, `{mark_time_left}`, `{mutagen_active}`, `{lycan_time_left}`, `{cooldown_next}`.

## Estrutura ProSkillAPI
- Classe base em `plugins/ProSkillAPI/classes/blood_hunter.yml` com atributos escalonados, recursos e árvore de skills.
- Subclasses em `plugins/ProSkillAPI/subclasses/`:
  - `bh_ghostslayer.yml`: anti-morto-vivo com dano radiante e controle espectral.
  - `bh_profanesoul.yml`: dano arcano e maldições de patrono.
  - `bh_mutant.yml`: mutágenos com buffs/penalidades e sangramentos.
  - `bh_lycan.yml`: forma híbrida agressiva e perseguição.
- Skills individuais em `plugins/ProSkillAPI/skills/blood_hunter/` com comentários, custos e cooldowns alinhados aos tiers do PHB.

## Itens MythicMobs
Arquivo `plugins/MythicMobs/Items/blood_hunter_items.yml` define os focais de cada ordem:
- **Base:** `CrimsonRiteBlade`, `BloodMarkCharm`, `BloodFocusVial`.
- **Ghostslayer:** `GhostRitualDagger`, `SanctifiedVial`, `GravewardCharm`.
- **Profane Soul:** `ProfaneTome`, `HexBrandSigil`, `EldritchNeedle`.
- **Mutant:** `MutagenKit`, `CoagulantVial`, `CatalystInjector`.
- **Lycan:** `LycanMoonTalisman`, `LycanClaw`, `HunterFetters`.
Todos possuem `PersistentData.classlock` apontando para o grupo LuckPerms da ordem correspondente.

## Integrações Skript
`plugins/Skript/scripts/blood_hunter_cmds.sk` provê:
- Comandos `/rito`, `/maldicao`, `/concentrar`, `/mutageno`, `/lycan`, `/kit_blood`.
- Gestão de Hemocraft, auto-dano não letal, marcas, penalidades de mutágeno e timers de forma licana.
- Gating por permissões LuckPerms e leitura de `RacesEffects` para aplicar exaustão quando resistências raciais coincidirem com ritos/maldições.

## LuckPerms
`plugins/LuckPerms/blood_hunter_groups.txt` inclui comandos para criar o grupo base `class_blood_hunter` e as ordens, herdando permissões da classe e habilitando itens e skills específicos.

## PlaceholderAPI / Scoreboard
A classe registra placeholders `skills_blood_*` para HUD de Hemocraft, marca, foco, mutágeno e licantropia. `blood_hunter_cmds.sk` chama `placeholderapi refresh` sempre que os recursos são atualizados.

## Interações com RacesEffects
- Antes de aplicar mitigações ou resistências extras (ex.: Rito do Espectro, Véu Consagrado, Rito Eldritch), o script consulta `%raceseffects_resistance_<tipo>%` e aplica **mining fatigue** curta como exaustão de resistência.
- Ritos não removem fraquezas raciais e respeitam caps globais (mitigação ≤45%, velocidade ≤0.05).

## Árvores por Tier (resumo)
| Tier | Ativos Principais | Passivos |
| ---- | ----------------- | -------- |
| T1 (1) | Rito Carmesim, Marca de Presa, Golpe/Mutágeno/Uivo iniciais conforme ordem | Sangue Frio |
| T2 (5) | Concentração de Sangue, habilidades de controle (Véu, Contrafeitiço, Explosão, Investida) | Técnica do Caçador |
| T3 (11) | Mobilidade/controle avançados (Estaca, Passo Abissal, Ligadura, Rastreio) | +Passivos situacionais |
| T4 (17) | Ultimates (Anátema, Rasgo do Vazio, Catalise Suprema, Luar Carmesim) | Especializações completas |

## Riscos e Limitações
- Auto-dano nunca reduz abaixo de 1 HP e é bloqueado se não houver Hemocraft suficiente.
- Auras (Véu Consagrado, Luar Carmesim) têm uptime ≤60% por custos/cooldowns.
- Maldições e efeitos de controle respeitam duração curta (≤4s) e imunidade decrescente via timers no Skript.
- Cura e roubo de vida obedecem ao limite global de 25% do dano por golpe (implementado no ajuste de lifesteal e sangramentos).

## Testes Rápidos
Use `/kit_blood` para receber itens focais de teste. `/rito`, `/maldicao` e `/concentrar` verificam custos/cooldowns básicos. Cada ordem requer pertencer ao grupo LuckPerms correspondente para acessar seus itens e skills.
