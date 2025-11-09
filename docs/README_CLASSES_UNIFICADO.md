# Guia Unificado de Classes

Este documento substitui materiais anteriores e resume a implementação oficial das classes do PHB no
servidor. Ele conecta LuckPerms, Skript, AuraSkills, RacesEffects e MythicMobs para cada função de
combate. Consulte também `README_BALANCEAMENTO.md` para limites numéricos e diretrizes de uptime.

## Convenções
- **Grupos LuckPerms**: `class_<classe>` para base e `class_<classe>_<arquétipo>` para subclasse.
- **Recursos AuraSkills**: administrados por scoreboard interno + comandos dedicados nos Skripts
criadas neste repositório. Ajuste regeneração/custos em `plugins/AuraSkills/` conforme necessidade.
- **MythicMobs**: cada habilidade listada usa uma skill ou item com o mesmo identificador descrito na
tabela abaixo. Garanta que o MythicMobs exporte skills e itens com esses nomes.
- **Skript**: os comandos ficam em `plugins/Skript/scripts/cmd_<classe>.sk` e dependem de
`classes_core.sk` para cooldown, mensagens e sincronia com AuraSkills.
- **Passivos (≤60 % uptime)**: configure no RacesEffects usando tags `apply.classes.<classe>` ou em
AuraSkills (atributos, resistências).

## Classes

### Bárbaro (`class_barbarian`)
- **Papel**: tanque/bruiser corpo a corpo.
- **Recursos (AuraSkills)**: `fury` (gera ao causar/receber dano).
- **Habilidades Ativas**:
  | Comando | Skill MythicMobs | Custo | Cooldown | Duração | Alvo |
  | --- | --- | --- | --- | --- | --- |
  | `/furia` | `Barbarian_Rage` | 30 fury | 180 s | 120 s | próprio |
  | `/temerario` | `Barbarian_Reckless` | 15 fury | 60 s | 20 s | próprio |
  | `/totem <urso/águia/lobo/fênix>` | `Barbarian_Totem_*` | 20 fury | 90 s | 10–15 s | próprio/aliados próximos |
  | `/ultimate_brb` | `Barbarian_Ultimate` | 50 fury | 210 s | 15 s | próprio |
- **Passivos (≤60 %)**: Defesa sem armadura (RacesEffects `apply.classes.barbarian.unarmoredDefense`),
Percepção do Perigo (redução 25 % a explosões com janela morta), Movimento Rápido (+0,02 quando sem armadura).
- **Itens focais**: `BarbarianRageCharm`, totens (`TotemBearCharm`, etc.) via MythicMobs.
- **Comandos Skript**: `/furia`, `/temerario`, `/totem`, `/ultimate_brb`.
- **LuckPerms**: `class_barbarian`, `class_barbarian_berserker`, `class_barbarian_totem_*`.
- **Sinergias de Raça**: Meio-Orc (Relentless + Fúria com cooldown global), Anão da Montanha (STR extra).

### Bardo (`class_bard`)
- **Papel**: suporte híbrido (buff, cura leve, controle).
- **Recursos**: `focus` (Inspiração) e `mana` (magias musicais).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/inspirar [alvo]` | `Bard_Inspiration` | 1 focus | 60 s | 30 s buff | aliado
  | `/cancao` | `Bard_Song` | 25 mana | 75 s | 30 s | área 8 blocos
  | `/collegio <conhecimento/valor/glamour>` | `Bard_College_*` | 30 mana | 90 s | 12 s | área/aliados
  | `/ultimate_brd` | `Bard_Ultimate` | 45 mana | 240 s | 15 s | área 12 blocos
- **Passivos**: Inspiração armazenada (3 cargas máx.), bônus sociais +10 %, redução 10 % cooldown de
instrumentos; uptime ≤60 % controlado por triggers automáticos (RacesEffects `apply.classes.bard`).
- **Itens focais**: `BardLute`, `LoreSongbook`, `ValorWarDrum`, `GlamourTiara`.
- **LuckPerms**: `class_bard`, `class_bard_lore`, `class_bard_valor`, `class_bard_glamour`.
- **Sinergias**: Meio-Elfo (CHA/DEX), Halfling Pés-Leves (controle adicional), Alto Elfo (INT extra).

### Bruxo (`class_warlock`)
- **Papel**: dano/controlador à distância.
- **Recursos**: `mana` para rajadas/hex e `focus` para pactos rápidos.
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/hex <alvo>` | `Warlock_Hex` | 25 mana | 45 s | 18 s debuff | alvo único
  | `/rajada` | `Warlock_EldritchBlast` | 10 mana | 10 s | 3 projéteis em 10 s | linha reta
  | `/pacto <fiend/archfey/oldone>` | `Warlock_Pact_*` | 30 focus | 90 s | 12 s | próprio/área
  | `/ultimate_wlk` | `Warlock_Ultimate` | 55 mana | 240 s | 12 s | área 8 blocos
- **Passivos**: Armadura de Sombras (40 s ativos / 80 s inativos), Velocidade +0,01 ao lançar magia,
resistência elemental por patrono (RacesEffects `apply.classes.warlock`).
- **Itens focais**: Tomo de Pacto, `EldritchWand`, selos de patrono.
- **LuckPerms**: `class_warlock`, `class_warlock_fiend`, `class_warlock_archfey`, `class_warlock_oldone`.
- **Sinergias**: Tiefling (fogo), Drow (sombreamento), Humano (pontos flexíveis).

### Clérigo (`class_cleric`)
- **Papel**: suporte e cura de grupo.
- **Recursos**: `divinity` (cargas de Canalizar) + `mana` para palavras curativas.
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/canalizar` | `Cleric_ChannelDivinity` | 1 divinity | 120 s | 8 s | área 6 blocos
  | `/palavradivina <alvo>` | `Cleric_WordHeal` | 20 mana | 30 s | instantâneo | aliado único
  | `/dominio <vida/guerra/crepúsculo>` | `Cleric_Domain_*` | 1 divinity | 90 s | 12 s | área/aliado
  | `/ultimate_clr` | `Cleric_Ultimate` | 2 divinity | 240 s | 12 s | área 10 blocos
- **Passivos**: Aura protetora (+10 % mitigação, 24 s ativos/16 s inativos), resistência situacional por
subdomínio (RacesEffects `apply.classes.cleric`).
- **Itens focais**: `LifeHolySymbol`, `WarSacredHammer`, `TwilightLantern`.
- **LuckPerms**: `class_cleric`, `class_cleric_life`, `class_cleric_war`, `class_cleric_twilight`.
- **Sinergias**: Anão da Colina (vida extra), Humano (pontos flexíveis), Meio-Elfo (CHA/WIS).

### Druida (`class_druid`)
- **Papel**: controle territorial e suporte situacional.
- **Recursos**: `nature_essence` (formas) + `mana` (magias).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/formaselvagem` | `Druid_Wildshape` | 1 essência | 180 s | 60 s | próprio
  | `/raizes` | `Druid_EntanglingRoots` | 20 mana | 60 s | 8 s | área 6 blocos
  | `/circulo <terra/lua/pastor>` | `Druid_Circle_*` | 30 mana | 90 s | 10–15 s | aliados/área
  | `/ultimate_drd` | `Druid_Ultimate` | 2 essência | 240 s | 18 s | área 8 blocos
- **Passivos**: Regeneração leve em biomas naturais (20 s ativos/20 s inativos), resistência elemental por
círculo (RacesEffects `apply.classes.druid`).
- **Itens focais**: `LandStoneStaff`, `MoonTotem`, `ShepherdTotem`.
- **LuckPerms**: `class_druid`, `class_druid_land`, `class_druid_moon`, `class_druid_shepherd`.
- **Sinergias**: Elfo da Floresta (mobilidade), Dragonborn Ácido (resistência ambiental), Humano.

### Feiticeiro (`class_sorcerer`)
- **Papel**: burst elemental.
- **Recursos**: `mana` + `sorcery_points` (metamagia).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/rajadaarcana` | `Sorcerer_ElementalBurst` | 25 mana | 30 s | canalização 3 s | cone 6 blocos
  | `/metamagia <acelerar/gêmea/potente>` | `Sorcerer_Metamagic_*` | 2 pontos | 45 s | 12 s | próprio
  | `/linhagem <dracônica/tempestade/selvagem>` | `Sorcerer_Lineage_*` | 1 ponto | 90 s | 15 s | próprio
  | `/ultimate_src` | `Sorcerer_Ultimate` | 3 pontos | 240 s | 12 s | área 8 blocos
- **Passivos**: Afinidade elemental (12 % bônus dano ≤60 %), regeneração 1 ponto de metamagia/30 s fora
de combate (AuraSkills script).
- **Itens focais**: `SorcererOrb`, `StormRod`, `DraconicFocus`.
- **LuckPerms**: `class_sorcerer`, `class_sorcerer_draconic`, `class_sorcerer_storm`, `class_sorcerer_wild`.
- **Sinergias**: Dragonborn fogo/raio, Tiefling, Alto Elfo (INT).

### Guerreiro (`class_fighter`)
- **Papel**: dano sustentado/tanque secundário.
- **Recursos**: `battle_focus` (surto) + `stamina` (manobras).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/surto` | `Fighter_ActionSurge` | 1 foco | 120 s | instantâneo | próprio
  | `/segundovento` | `Fighter_SecondWind` | 30 stamina | 180 s | 6 s HoT | próprio
  | `/arquetipo <campeão/mestre/arcano>` | `Fighter_Archetype_*` | 25 stamina | 60 s | 8–12 s | próprio/área
  | `/ultimate_fgt` | `Fighter_Ultimate` | 2 foco | 210 s | 15 s | próprio
- **Passivos**: Resistência knockback +0,05; proficiência armas; Action Surge concede 15 % velocidade
ataque (15 s uptime/45 s off). Configurar em RacesEffects `apply.classes.fighter`.
- **Itens focais**: `ChampionGreatsword`, `BattlemasterBanner`, `EldritchKnightBlade`.
- **LuckPerms**: `class_fighter`, `class_fighter_champion`, `class_fighter_battlemaster`, `class_fighter_eldritch`.
- **Sinergias**: Meio-Orc (burst), Anão (resistência), Humano (versatilidade).

### Ladino (`class_rogue`)
- **Papel**: burst e infiltração.
- **Recursos**: `stamina` (mobilidade) + `focus` (ataque furtivo).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/furtivo` | `Rogue_Stealth` | 15 stamina | 30 s | 12 s | próprio
  | `/golpefurtivo <alvo>` | `Rogue_SneakAttack` | 25 focus | 6 s | instantâneo | alvo único
  | `/truque <ladrão/assassino/arcano>` | `Rogue_Archetype_*` | 20 focus | 75 s | 10 s | próprio/área
  | `/ultimate_rog` | `Rogue_Ultimate` | 40 focus | 210 s | 12 s | próprio
- **Passivos**: Evasão (50 % redução projéteis 18 s on/18 s off), Expertise (buff em interações).
- **Itens focais**: `RogueKit`, `ShadowstepDagger`, `DistractionCoin`.
- **LuckPerms**: `class_rogue`, `class_rogue_thief`, `class_rogue_assassin`, `class_rogue_arcane`.
- **Sinergias**: Halfling Pés-Leves, Elfo da Floresta, Drow.

### Mago (`class_wizard`)
- **Papel**: controle de campo e utilidade arcana.
- **Recursos**: `arcane_charges` + `mana`.
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/preparar <escola>` | `Wizard_SpellPrep` | 1 carga | 120 s | aplica loadout | próprio
  | `/grimorio` | `Wizard_ArcaneVolley` | 30 mana | 45 s | 6 projéteis | área 6 blocos
  | `/escola <evocação/ilusão/abjuração>` | `Wizard_School_*` | 35 mana | 90 s | 12 s | área/próprio
  | `/ultimate_wiz` | `Wizard_Ultimate` | 3 cargas | 240 s | 15 s | área 10 blocos
- **Passivos**: Livro preparado concede +10 % redução cooldown ao ficar imóvel 6 s; resistência a magia
por escola (RacesEffects `apply.classes.wizard`).
- **Itens focais**: `EvocationCodex`, `IllusionTome`, `AbjurationWard`.
- **LuckPerms**: `class_wizard`, `class_wizard_evocation`, `class_wizard_illusion`, `class_wizard_abjuration`.
- **Sinergias**: Alto Elfo, Humano, Gnomo das Rochas (INT/STR).

### Monge (`class_monk`)
- **Papel**: mobilidade e controle corpo a corpo.
- **Recursos**: `ki`.
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/rajada` | `Monk_FlurryOfBlows` | 1 ki | 30 s | 4 golpes | corpo a corpo
  | `/passo` | `Monk_StepOfTheWind` | 1 ki | 45 s | 8 s | próprio
  | `/tradicao <mãos/sombras/elementos>` | `Monk_Tradition_*` | 2 ki | 90 s | 12 s | próprio/área
  | `/ultimate_mnk` | `Monk_Ultimate` | 4 ki | 210 s | 15 s | próprio
- **Passivos**: Defesa sem armadura (Dex+Wis), Deflexão de Projéteis (18 s on/18 s off), redução de dano
de queda (Skript passivo em AuraSkills).
- **Itens focais**: `FlurryWraps`, `WindCharm`, `MonkPrayerBeads`.
- **LuckPerms**: `class_monk`, `class_monk_openhand`, `class_monk_shadow`, `class_monk_elements`.
- **Sinergias**: Elfo da Floresta, Humano, Halfling.

### Paladino (`class_paladin`)
- **Papel**: tanque/suporte.
- **Recursos**: `divinity` + `mana` secundário.
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/golpe <alvo>` | `Paladin_DivineSmite` | 1 divinity | 45 s | instantâneo | alvo único
  | `/aura` | `Paladin_AuraToggle` | 1 divinity | 90 s | 12 s | área 8 blocos
  | `/voto <devoção/vingança/anciões>` | `Paladin_Oath_*` | 1 divinity | 120 s | 15 s | próprio/aliados
  | `/ultimate_pld` | `Paladin_Ultimate` | 3 divinity | 240 s | 12 s | área 10 blocos
- **Passivos**: Aura de proteção (uptime 60 %), imunidade a medo enquanto aura ativa; bônus CHA →
resistência (AuraSkills script).
- **Itens focais**: `DivineSmiteSword`, `DevotionBanner`, `VengeanceSigil`, `AncientsGrimoire`.
- **LuckPerms**: `class_paladin`, `class_paladin_devotion`, `class_paladin_vengeance`, `class_paladin_ancients`.
- **Sinergias**: Humano (flex), Anão da Colina (vida), Dragonborn fogo (dano adicional).

### Patrulheiro (`class_ranger`)
- **Papel**: dano sustentado + controle de terreno.
- **Recursos**: `focus` (marca e conclave) + `stamina` (companheiro).
- **Habilidades Ativas**:
  | Comando | Skill | Custo | Cooldown | Duração | Alvo |
  | `/marca <alvo>` | `Ranger_HuntersMark` | 20 focus | 45 s | 30 s | alvo único
  | `/companheiro` | `Ranger_CompanionSummon` | 30 stamina | 90 s | 20 s | pet aliado
  | `/conclave <caçador/bestas/trevas>` | `Ranger_Conclave_*` | 25 focus | 90 s | 12 s | próprio/área
  | `/ultimate_rng` | `Ranger_Ultimate` | 45 focus | 210 s | 15 s | área 12 blocos
- **Passivos**: Bônus contra alvo marcado (+12 % dano), velocidade 10 % em bioma favorável, pet recebe
redução 20 % (RacesEffects `apply.classes.ranger`).
- **Itens focais**: `RangerLongbow`, `RangerHorn`, `SnareTrap`, `VolleyBow`.
- **LuckPerms**: `class_ranger`, `class_ranger_hunter`, `class_ranger_beast`, `class_ranger_gloom`.
- **Sinergias**: Elfo da Floresta, Meio-Elfo, Dragonborn raio (burst à distância).

## Referências Rápidas
- **Skript**: `plugins/Skript/scripts/classes_core.sk` + `cmd_<classe>.sk`.
- **MythicMobs**: crie skills com os IDs listados e associe a itens/permissões.
- **AuraSkills**: configure recursos (`mana`, `fury`, `ki`, etc.) em `auraskills-playerdata.yml` ou via API.
- **LuckPerms**: mantenha pesos coerentes (classes peso 50, subclasses 55) para facilitar ordenação em
envolvimento de menus.

Atualize este guia sempre que criar novas subclasses, alterar custos ou introduzir itens focais.
