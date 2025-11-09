# Classe Artífice (Minecraft + D&D 5e)

## Visão Geral
- **Função:** suporte/controle híbrido com gadgets e reforços mecano-mágicos.
- **Recursos:**
  - `Mana Arcana` (pool base 100, escalando até 220). Regeneração estável configurada no ProSkillAPI.
  - `Cargas de Infusão` (2 → 5 do T1 ao T4). Recuperação fora de combate a cada 45 s (passiva + Skript).
- **Identidade:** alterna entre buffs de precisão, campos rúnicos, constructos e frascos alquímicos. Passivos garantem eficiência com itens focais e mitigam stacking excessivo via integração com RacesEffects (exaustão de resistência).
- **Caps Respeitados:** velocidade adicional ≤ +0.05, mitigação total ≤45%, cura por golpe ≤25%, áreas padrão 6–8 blocos.

## Progressão por Tier
| Tier | Nível aproximado | Vida média | Mana Arcana | Cargas | Ganhos chave |
|------|------------------|------------|-------------|--------|--------------|
| T1   | 1–4              | 28 HP      | 120         | 2      | Campo Rúnico, Sobrecarregar, Ferramentas de Tinker. |
| T2   | 5–10             | 38 HP      | 150         | 3      | Recuperar Infusão, acesso inicial às subclasses. |
| T3   | 11–16            | 50 HP      | 185         | 4      | Módulos reforçados, cooldowns otimizados, constructo com mais utilidade. |
| T4   | 17–20            | 62 HP      | 220         | 5      | Ultimates das subclasses, uptime controlado de 15–20 min em infusões. |

## Árvores de Skills (Core)
- **Infundir Item:** módulo temporário (12–20 min) aplicado via `/infundir` ou Chave de Infusão. CD 120 s; custo 1 carga.
- **Campo Rúnico:** sigilo 6 blocos por 8–10 s com pulsos de dano PvE + cura leve aliada. Custo 25 Mana.
- **Sobrecarregar:** buff de +15% velocidade de ataque/projétil por 12 s em 6 blocos. Custo 1 carga; CD 90 s.
- **Ferramentas de Tinker (passivo):** +10–15% eficiência em skills/itens, -5% custo de Mana.
- **Recuperar Infusão (passivo):** acelera recarga de cargas fora de combate (até +12%). Integrado ao Skript `artificer_register_regen`.

## Subclasses
### Armeiro (Armorer)
- Modos Guardião/Infiltrador alternados com custo de carga (`Mudar Modo`).
- Guardião: `Punho Trovão`, `Campo Repulsor`, ultimate `Núcleo Arcano` (pulsos 60%/2s, +20% DR por 15s).
- Infiltrador: `Descarga Silenciosa`, `Passo Amplificado` (+0.05 velocidade, invis curta).
- Itens foco: Núcleo ArcArmor, Manopla de Trovão, Botas Faseadas.

### Ferreiro de Batalha (Battle Smith)
- Pet `Defensor de Aço` (MythicMobs) com skills `Bash`/`Guard Stance`, sincronizado com HUD `{defender_hp}`.
- Buffs: `Sintonizar Arma`, `Comando Reparar`, `Interferência` (cone debuff), ultimate `Protocolo Guardião`.
- Itens: Martelo da Forja, Kit de Sintonização, Console do Defensor.

### Alquimista (Alchemist)
- Controle em área e suporte através de frascos/elixires.
- Skills: `Frasco Explosivo` (projétil + DoT via Skript), `Elixir de Vitalidade`, `Nuvem Corrosiva`, `Tônico Acelerante`, ultimate `Panaceia`.
- Itens: Kit Alquímico, Bolsa de Elixires, Cajado Catalisador.

## Integrações
- **MythicMobs:** itens focais (`artificer_items.yml`) e mob `SteelDefender` (`artificer_constructs.yml`). Skills simples em `artificer_skills.yml`.
- **Skript:** comandos `/kit_artifice`, `/infundir`, `/sobrecarregar`, `/defensor`, `/modo_armadura`, `/frasco_*`. Glue para HUD, DoT, regeneração e convocações (`artificer_cmds.sk`).
- **LuckPerms:** grupos `class_artificer` e subclasses com permissões específicas (`artificer_groups.txt`).
- **RacesEffects:** comando `raceseffects applyexhaust` disparado para evitar stacking de resistências.
- **PlaceholderAPI/Scoreboard:** placeholders `{infusoes_charges}`, `{mana_arcana}`, `{cooldown_next}`, `{defender_hp}` atualizados via Skript e ProSkillAPI.

## Itens e HUD
- HUD padrão exibe Mana Arcana, Cargas de Infusão e integridade do Defensor.
- Itens têm `PersistentData` com `classlock` para garantir gating por LuckPerms.
- Cooldowns longos (90–210 s) sinalizados com mensagens e refresh automático de placeholders.

## Considerações de Balanceamento
- Uptime passivos ≤60% (Infundir 12–20 min com CD 120 s + custo/cargas limitadas).
- Constructo possui escalonamento moderado e sem DPS infinito (skills simples, TTK controlado).
- Todas as fontes de mitigação/resistência aplicam exaustão quando raça fornece bônus similar.
- PvP seguro: DoTs e execuções exigem alvo abaixo de 30% HP; nenhum golpe individual excede limites de servidor.
