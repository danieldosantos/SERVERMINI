# Classe Artífice (Minecraft + D&D 5e)

## Visão Geral
- **Função:** suporte/controle híbrido com gadgets e reforços mecano-mágicos.
- **Recursos:**
  - `Mana Arcana` (pool base 100, escalando +25 por Tier até 200). Regeneração estável configurada no ProSkillAPI.
  - `Cargas de Infusão` (2 → 5 do T1 ao T4). Recuperação fora de combate a cada 45 s (passiva + Skript).
- **Identidade:** alterna entre buffs de precisão, campos rúnicos, constructos e frascos alquímicos. Passivos garantem eficiência com itens focais e mitigam stacking excessivo via integração com RacesEffects (exaustão de resistência).
- **Caps Respeitados:** velocidade adicional ≤ +0.05, mitigação total ≤45%, cura por golpe ≤25%, áreas padrão 6–8 blocos.

## Progressão por Tier
| Tier | Nível aproximado | Vida média | Mana Arcana | Cargas | Ganhos chave |
|------|------------------|------------|-------------|--------|--------------|
| T1   | 1–4              | 28 HP      | 125         | 2      | Campo Rúnico Menor, Sobrecarregar, Ferramentas de Tinker. |
| T2   | 5–10             | 38 HP      | 150         | 3      | Campo Rúnico Estável, Bateria Arcana e desbloqueio das subclasses. |
| T3   | 11–16            | 50 HP      | 175         | 4      | Campos superiores, redução de CDs, construtos aprimorados. |
| T4   | 17–20            | 62 HP      | 200         | 5      | Ultimates de núcleo/reatores, uptime controlado <60%. |

## Árvores de Skills (Core)
- **Infundir Item:** módulo leve ativo por 12 s (buff de +10% dano, +10% mitigação ou +0.03 velocidade). Consome 1 carga, CD 60 s. Aplicado via `/infundir` e binds de itens focais.
- **Campos Rúnicos:** progressões Menor/Estável/Superior cobrindo 5→7 blocos por 8–12 s, causando 25→40% dano/s. Custos 20/30/40 Mana, CDs 60/75/90 s, controlados pelo MythicMobs.
- **Sobrecarregar & Sobrecarga Coordenada:** haste pessoal T1 (8 s, +15%, CD 75 s) evolui para versão em grupo T3 (10 s, +20%, CD 120 s). Ambas usam cargas de infusão.
- **Ferramentas de Tinker (passivo):** +10% eficiência em itens/reatores, bônus aplicado direto nos focos do MythicMobs.
- **Foco Estabilizado & Núcleo Excedente:** redução global de CD (10% por 12 s, CD 90 s) e ultimate que reduz custos em 20% e remove gasto de cargas por 15 s (CD 210 s).
- **Bateria Arcana:** recuperação automática de 1 carga após 45 s fora de combate via Skript.

> **Tabela completa de magias:** ver [READMESKILLS_ARTIFICE](READMESKILLS_ARTIFICE.md) para detalhes de custo, duração, área, integrações com plugins e observações PvP.

## Subclasses
### Armeiro (Armorer)
- Modos Guardiã/Infiltradora alternados com custo de carga (`/modo`). Guardiã recebe controle de área (Punho Trovão, Campo Repulsor, Núcleo Arcano) enquanto Infiltradora foca em debuffs e mobilidade (Descarga Silenciosa, Manto de Fase, Passo Amplificado).
- Sobrecarga Total (ult T4) ativa todos módulos simultaneamente por 12 s; combos com Sobrecarga Final respeitam cap de velocidade.
- Itens foco: Núcleo ArcArmor, Manopla de Trovão, Botas Faseadas, estabilizador prismático.

### Ferreiro de Batalha (Battle Smith)
- Pet `Defensor de Aço` (MythicMobs) com barra dedicada e comandos `/defensor` para reparar, sincronizar mitigação e ativar guardas.
- Buffs chave: `Sintonizar Arma`, `Comando Reparar`, `Sincronia Defensiva`, `Interferência`, `Golpe de Fornalha`, `Lança-Calor`.
- Ultimates e Tier 4: `Protocolo Guardião`, `Golpe Titânico`, `Ferrita Arcana`, `Núcleo Blindado`.
- Itens: Martelo da Forja, Kit de Sintonização, Console do Defensor, Reator Guardião.

### Alquimista (Alchemist)
- Controle em área e suporte através de frascos/elixires com cargas e mana escalando por Tier.
- Destaques: `Frasco Explosivo`, `Tônico Vivificante`, `Névoa Corrosiva`, `Frasco Mutagênico`, `Perfusão Vital` (revive), `Panaceia Suprema` (ultimate).
- Tier 4 inclui `Gás de Dissolução`, `Tônico de Ascensão` e `Frasco Final` para burst controlado.
- Itens: Kit Alquímico, Bolsa de Elixires, Cajado Catalisador, Reator de Panaceia.

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
- Uptime de buffs ≤60%: infusões duram 12 s com CD 60 s e dependem de `infusoes_charges`, campos e sobrecargas mantêm janelas curtas.
- Constructo (Ferreiro de Batalha) escala moderadamente, sem DPS infinito: pulsos e feixes respeitam caps de área (6–8 blocos) e CDs ≥75 s.
- Mitigações se acumulam com cautela: Contramedida Técnica, Reforço Estrutural, Núcleo Arcano e Sincronia Defensiva obedecem limite global 45% + exaustão via RacesEffects.
- PvP seguro: DoTs (Frasco Explosivo, Golpe de Fornalha) e debuffs respeitam DR; nenhuma habilidade isolada excede os caps de velocidade/cura/dano estabelecidos.
