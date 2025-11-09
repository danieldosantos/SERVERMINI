# Classe Bardo (Minecraft + D&D 5e)

## Visão geral
A classe **Bardo** combina suporte híbrido com controle leve e utilidade social. O kit usa as cargas de **Inspiração Bardica** (0–3) como recurso principal, reforçando buffs temporários e canções. A regeneração ocorre fora de combate (10s sem receber/causar dano) ou via **Canção de Descanso**.

- Função primária: suporte de buffs/debuffs.
- Mitigação baixa, foco em posicionamento e mobilidade controlada.
- Placeholders (PlaceholderAPI):
  - `%skills_inspiration_charges%`
  - `%skills_bard_song_timer%`
  - `%skills_bard_major_cd%`

HUD configurada no `bard_cmds.sk` usando scoreboard (`bard_inspiration`, `bard_song_duration`, `bard_major_cd`, `bard_ooc`).

## Progressão por Tier
| Tier | Conteúdo destravado |
| --- | --- |
| **T1 (nível 1)** | Inspiração, Canção de Descanso, Virtuose, Talento Versátil. |
| **T2 (nível 5)** | Mantém habilidades T1, ganha mais cargas (2) e integrações de instrumentos mais baratos. |
| **T3 (nível 11)** | Passiva Maestro da Cadência (−5% CD para aliados próximos) e cargas máximas 3. |
| **T4 (nível 17)** | Rotação completa com colégios ativos/ultimates.

## Habilidades base (ProSkillAPI)
- **Inspiração** (`bard_inspiration`): buff de 20s, CD 60s, consome 1 carga.
- **Canção de Descanso** (`bard_song_of_rest`): aura 30s, raio 8 blocos, recarrega 1 carga ao término. CD 60s.
- **Virtuose** (`bard_virtuoso`): passiva que aplica -10% custo em instrumentos.
- **Talento Versátil** (`bard_talento_versatil`): bônus leves fora de combate (Luck + Speed). 
- **Maestro da Cadência** (`bard_maestro_da_cadencia`): ativa no T3+, -5% CD em aliados próximos por 8s (uptime <60%).

## Colégios
### Colégio do Conhecimento (`class_bard.lore`)
- Controle tático, dispell e redução de cooldown.
- Skills: Verso Analisador, Balada dos Segredos, Palavra Desconcertante, Orquestração Perfeita, Sinfonia Onisciente (ULT).
- Instrumentos: Livro dos Segredos, Pena Disruptiva, Harpa Onisciente.

### Colégio do Valor (`class_bard.valor`)
- Linha de frente, escudos e reanimações controladas.
- Skills: Hino da Coragem, Ritmo de Batalha, Coda Inspirador, Marcha Recuada, Cantiga dos Heróis (ULT).
- Instrumentos: Tambor da Coragem, Berrante de Batalha, Alaúde Sagrado.

### Colégio do Glamour (`class_bard.glamour`)
- Encantamento, mobilidade e escudos móveis.
- Skills: Encanto Hipnótico, Manto de Inspiração, Brilho Deslumbrante, Passo Férico, Refrão Sublime (ULT).
- Instrumentos: Tiara Hipnótica, Amuleto Férico, Lira Estelar.

## Comandos (Skript `bard_cmds.sk`)
- `/kit_bardo`: entrega itens focais e inicializa scoreboard.
- `/inspirar`, `/cancao`: habilidades base.
- `/lore_verso`, `/lore_balada`, `/lore_palavra`, `/lore_orquestra`, `/lore_sinfonia`.
- `/valor_hino`, `/valor_ritmo`, `/valor_coda`, `/valor_marcha`, `/valor_cantiga` (bloqueio arena via flag).
- `/glamour_encanto`, `/glamour_manto`, `/glamour_brilho`, `/glamour_passo`, `/glamour_refrao`.
- Bindings automáticos em cliques direitos dos itens MythicMobs correspondentes.

## Itens (MythicMobs `bard_items.yml`)
Cada instrumento possui `NBT` `class-lock` que deve casar com o grupo LuckPerms, evitando uso indevido. Exemplos:
- `BardLute` / `BardLoreBook`: classe base.
- `LoreRelicHarp`, `ValorSacredLute`, `GlamourStarLyre`: ultimates de cada colégio.

## LuckPerms
Arquivo `plugins/LuckPerms/bard_groups.txt` cria grupos:
- `class_bard` (base) + subgrupos `class_bard.lore`, `.valor`, `.glamour`.
- Permissões para ProSkillAPI, MythicMobs, comandos Skript e placeholders.
- Subgrupos herdam `class_bard` e recebem os itens/skills exclusivos.

## Integração com RacesEffects
- Nenhuma skill adiciona bônus diretos de dano baseados em raças.
- Uptime de resistências respeita teto de 45% (escudos + buffs rotacionados).
- Scripts não modificam arquivos de raça; apenas consultam placeholders e respeitam DR manualmente.

## Placeholders / HUD
- Scoreboards atualizados a cada 5s e após cada habilidade.
- Objetivos: `bard_inspiration`, `bard_song_duration`, `bard_major_cd`, `bard_ooc`.
- `bard_cmds.sk` marca `bard_ooc = 1` após 10s sem combate (usado para regeneração fora de combate).

## Observações de Balanceamento
- CDs principais ≥60s, ultimates 240s.
- Auras com duração 30s, uptime <60% devido ao cooldown.
- Buffs de dano/cura ≤15% exceto curas de ultimate (6%/tick em 12s).
- Controle forte (silêncio, root) limitado a 3–4s e com DR via script/arena.

