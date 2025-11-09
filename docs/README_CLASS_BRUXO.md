# Classe Bruxo (Minecraft + D&D 5e)

## Visão geral
A classe **Bruxo** é um DPS/controlador de longo alcance que alterna entre janelas de rajada e debuffs persistentes. O recurso principal combina **Mana Pactual** com **Espaços de Pacto** (0–3 conforme o tier), usados para conjurar invocações específicas de cada patrono.

- Função primária: dano mágico sustentado com controle situacional.
- Mitigação moderada (passiva) limitada a 45% com verificação de raças via RacesEffects.
- Placeholders principais (PlaceholderAPI):
  - `%skills_warlock_hex_stacks%`
  - `%skills_warlock_hex_timer%`
  - `%skills_warlock_eldritch_window%`
  - `%skills_warlock_major_cd%`

O HUD é atualizado por `warlock_cmds.sk`, alimentando os objetivos de scoreboard `warlock_hex_stacks`, `warlock_eldritch_window` e `warlock_major_cd`.

## Progressão por Tier
| Tier | Conteúdo destravado |
| --- | --- |
| **T1 (nível 1)** | Hex, Rajada Mística, passiva Pacto Protetor. 1 espaço de pacto. |
| **T2 (nível 5)** | +1 espaço de pacto, passiva Estudo de Hex (penetração), acesso a primeiras skills dos patronos. |
| **T3 (nível 11)** | Reserva de Invocações (regen adicional de pacto), teleporte situacional (por patrono), habilidades de controle médio. |
| **T4 (nível 17)** | Ultimates de cada patrono e rotação completa com CDs 210–240s. |

## Habilidades base (ProSkillAPI)
- **Hex** (`warlock_hex`): marca único alvo por 12s (+18% dano recebido do Bruxo). Cada alvo possui cooldown individual de 45s, rastreado pelo Skript.
- **Rajada Mística** (`warlock_eldritch_blast`): abre janela de 10s com 3 projéteis (65% arcano cada). Cooldown global 5s.
- **Pacto Protetor** (`warlock_pact_ward`): +6% resistência mágica e +5% velocidade enquanto houver Hex ativo; aplica exaustão de resistência se o jogador exceder o limite global.
- **Estudo de Hex** (`warlock_hex_study`): +2s de duração de Hex e +4% penetração mágica por alvo marcado (máx. 12%), além de atualizar HUD.
- **Reserva de Invocações** (`warlock_invocation_reserve`): regenera 1 Espaço de Pacto a cada 45s fora de combate através do Skript.

## Patronos
### Pacto do Diabólico (`class_warlock.fiend`)
Identidade: dano explosivo de fogo + autosustentação moderada.
- **Chicote Infernal** (`warlock_fiend_chicote_infernal`): cone 5 blocos, 180% fogo, aplica Calor Infernal (+5% dano recebido, máx. 3). CD 45s.
- **Armadilha de Brasas** (`warlock_fiend_armadilha_de_brasas`): sigilo detona em 4s (140% fogo + root 2s). CD 75s.
- **Contrato de Chamas** (`warlock_fiend_contrato_de_chamas`): lifesteal 12% por 10s (cap global 25%). CD 90s.
- **Portal Ígneo** (`warlock_fiend_portal_igneo`): teleporta 15 blocos, deixa rastro 40%/s por 4s; telegraph de 0,6s. CD 120s.
- **Inferno Pactuado** (`warlock_fiend_inferno_pactuado`): meteoro 6 blocos, 250% dano + queimadura 8%/s por 8s. CD 240s.

### Pacto da Arquifada (`class_warlock.archfey`)
Identidade: controle mental leve + mobilidade.
- **Explosão Encantadora** (`warlock_archfey_explosao_encantadora`): projétil arcano 150% + confusão 3s. CD 40s.
- **Passo Brumoso** (`warlock_archfey_passo_brumoso`): teleporte 10 blocos com clones que quebram alvo por 2s. CD 60s.
- **Domínio do Sonho** (`warlock_archfey_dominio_do_sonho`): induz sono 4s (quebra com dano forte). CD 90s.
- **Laço Feérico** (`warlock_archfey_laco_feerico`): raízes em 7 blocos (root 5s + slow 30% pós-root). CD 120s.
- **Cortejo da Rainha** (`warlock_archfey_cortejo_da_rainha`): espíritos orbitam 8 blocos por 15s (70% arcano a cada 2s) e -20% CD aliados. CD 240s.

### Pacto do Grande Antigo (`class_warlock.greatoldone`)
Identidade: debuffs psíquicos e manipulação de posicionamento.
- **Sussurros Devastadores** (`warlock_greatoldone_sussurros_devastadores`): 120% psíquico + Desespero (+15% dano recebido) por 8s. CD 45s.
- **Visões Alienígenas** (`warlock_greatoldone_visoes_alienigenas`): revela camuflados 12 blocos e aplica slow 30%/6s. CD 75s.
- **Marionete Mental** (`warlock_greatoldone_marionete_mental`): força alvo a mover 3 blocos e silencia 3s. CD 90s.
- **Cascata de Loucura** (`warlock_greatoldone_cascata_de_loucura`): área 6 blocos, 100% +10% por stack de Loucura. CD 120s.
- **Abismo Sussurrante** (`warlock_greatoldone_abismo_sussurrante`): fenda 12s que puxa inimigos, aplica medo e concede +20% mitigação (respeitando cap). CD 240s.

## Comandos (Skript `warlock_cmds.sk`)
- `/kit_bruxo`: entrega EldritchWand, HexTalisman e inicializa HUD.
- `/hex`, `/eldritch`: habilidades base (aliases `/warlock_hex`, `/warlock_eldritch`).
- Patronos:
  - Fiend: `/fiend_whip`, `/fiend_trap`, `/fiend_contract`, `/fiend_gate`, `/fiend_inferno`.
  - Archfey: `/archfey_burst`, `/archfey_step`, `/archfey_dream`, `/archfey_laco`, `/archfey_cortejo`.
  - Grande Antigo: `/oldone_whispers`, `/oldone_visoes`, `/oldone_marionete`, `/oldone_cascata`, `/oldone_abismo`.
- Funções auxiliares tratam HUD, cooldown individual de Hex, regeneração de pacto e verificação de resistências.

## Itens (MythicMobs `warlock_items.yml`)
Cada item possui `NBT` `class-lock` compatível com o grupo LuckPerms correspondente e, quando aplicável, `psapi-skill` para disparar habilidades via clique.
- Base: `EldritchWand` (Rajada), `HexTalisman`, `PactLedger` (atualiza HUD).
- Fiend: `FiendWhip`, `FiendBrand`, `FiendHellcore`.
- Archfey: `ArchfeyWand`, `ArchfeyMirror`, `ArchfeyGroveCharm`.
- Grande Antigo: `GreatOldOneTome`, `GreatOldOneRelic`, `GreatOldOneEye`.

## LuckPerms
Arquivo `plugins/LuckPerms/warlock_groups.txt`:
- `class_warlock`: habilita classe, comandos base, placeholders e itens genéricos.
- Subgrupos `class_warlock.fiend`, `.archfey`, `.greatoldone`: herdam base, liberam skills de patrono, itens MythicMobs e comandos Skript dedicados.

## Integração com RacesEffects
- `warlock_pact_ward` e skills elementares consultam `%raceseffects_resistance_elemental%` e disparam alertas de exaustão quando o valor >45%.
- Especificamente, Fiend monitora resistências de fogo e Grande Antigo monitora psíquico. Archfey concentra-se em controle, sem bônus defensivos.
- Nenhum combo raça+patrono ultrapassa mitigação total de 45% ou lifesteal >25%.

## Placeholders / HUD
- Scoreboards: `warlock_hex_stacks`, `warlock_eldritch_window`, `warlock_major_cd`.
- HUD atualizada a cada 5s e após cada conjuração relevante (Hex, Rajada, passivas) via `warlock_update_hud`.
- Actionbars temáticos informam Hex aplicado, rajada ativa, alertas de resistência e conjurações de portais/ultimates.

## Observações de Balanceamento
- CDs principais ≥45s; situacionais 60–120s; ultimates 240s conforme diretriz.
- Hex único por alvo evita snowball; janela de Rajada limitada a 3 projéteis/10s.
- Áreas máximas respeitam 6–8 blocos e teleporte com telegraph visível.
- Execuções e mitigação extra apenas sob 20–30% HP via Abismo Sussurrante (medo + puxão, sem dano letal imediato).
