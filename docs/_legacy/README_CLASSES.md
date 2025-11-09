# Guia Unificado de Classes (PHB)

Todas as classes utilizam o ecossistema: LuckPerms (grupos `class_<id>`), Skript (seleção de classe,
menus, cooldowns e lógica), MythicMobs (habilidades e itens), AuraSkills (atributos/recursos) e
MythicMobs/Items (focos e armas). Este arquivo consolida as referências mínimas para implementação.

## Convenções Gerais
- **Recursos** mapeados no AuraSkills: `mana`, `stamina`, `fury`, `ki`, `divinity`, `focus` conforme a
classe. Use placeholders do AuraSkills para exibir barras.
- **Ativos**: sempre implementar em MythicMobs e acionar por Skript (verbo, cooldown, custo). Adote os
limites de recarga descritos em `README_BALANCEAMENTO.md`.
- **Passivos**: buffs permanentes via RacesEffects (atributos, resistências) ou AuraSkills (modificadores
por nível). Uptime máximo 60 % quando exigem ativação automática.
- **Itens focais**: criar no MythicMobs/Items, proteger com SkBee e restringir via LuckPerms.

## Classe por Classe
### Bárbaro
- **Função**: tanque/bruiser corpo a corpo.
- **Recursos (AuraSkills)**: `fury` (ganho por dano causado/recebido, gasto para Fúria e Totens).
- **Habilidades Ativas (Skript → MythicMobs)**: `/rage` (Fúria 120 s/180 s), `/reckless`, Totens (Urso,
Águia, Lobo, Fênix) com cooldowns 60–240 s.
- **Habilidades Passivas**: Defesa sem armadura, Percepção do Perigo e bônus situacionais configurados em
RacesEffects (`apply.classes.barbarian.*`).
- **Itens Focais**: `BarbarianRageCharm` (ativa Fúria), totens específicos (`TotemBearCharm`,
`BerserkerWarHorn`, etc.).

### Bardo
- **Função**: suporte híbrido (buff, cura leve, controle).
- **Recursos**: `mana` para magias e `focus` para Inspiração (3 cargas máximas escalonadas por nível).
- **Habilidades Ativas**: `/inspire`, `/song`, itens de colégios (Conhecimento, Valor, Glamour) como
`LoreSongbook`, `ValorWarDrum`, `GlamourTiara`.
- **Habilidades Passivas**: bônus sociais e redução de cooldown de instrumentos (RacesEffects) + escalas
de AuraSkills (buff em aliados limitado a +10 %).
- **Itens Focais**: Lira (`BardLute`), Livros de Conhecimento, Medaillons de subclasse com controle via
LuckPerms (`class_bard_*`).

### Bruxo
- **Função**: dano/controlador à distância.
- **Recursos**: `mana` como slots de pacto e `focus` para invocações rápidas.
- **Habilidades Ativas**: `/hex`, rajada mística (`EldritchWand`), habilidades por patrono (Fiend,
Archfey, Great Old One) implementadas como itens (`FiendWhip`, `ArchfeyMirror`, `GreatOldOneEye`).
- **Habilidades Passivas**: bônus de pacto (resistências, mobilidade) aplicados via RacesEffects com
uptime ≤60 % (ex.: Armadura de Sombras 40 s on/80 s off).
- **Itens Focais**: Tomo de Pacto, varinhas, selos elementais controlados por LuckPerms (`class_warlock_*`).

### Clérigo
- **Função**: suporte/curador.
- **Recursos**: `divinity` (cargas de Canalizar Divindade) + `mana` para magias.
- **Habilidades Ativas**: Canalizar Divindade (`LifeHolySymbol`, `WarSacredHammer`, `TwilightLantern`),
`/wordheal`, barreiras radiais (cooldowns 45–240 s).
- **Habilidades Passivas**: aura de resistência 10 % com uptime controlado; escalonamento de cura via
AuraSkills (WIS); resistência situacional em RacesEffects.
- **Itens Focais**: Símbolo Sagrado, cajados/urndomínio (MythicMobs) ligados aos domínios (Vida, Guerra,
Crepúsculo) por LuckPerms.

### Druida
- **Função**: controle territorial e suporte situacional.
- **Recursos**: `mana` + `nature_essence` (pool para Forma Selvagem, 2 cargas padrão).
- **Habilidades Ativas**: transformações (`MoonTotem`, `LandStoneStaff`, `ShepherdTotem`), raízes e neblina
com cooldowns 45–240 s.
- **Habilidades Passivas**: regeneração leve em biomas naturais, afinidades elementares (resistência 20 %)
vinculadas ao RacesEffects por círculo.
- **Itens Focais**: Cajados druidicos, sementes/totens por círculo vinculados aos grupos `class_druid_*`.

### Feiticeiro
- **Função**: blaster arcano.
- **Recursos**: `mana` (slots) + `sorcery_points` (metamagia) sincronizados com AuraSkills.
- **Habilidades Ativas**: rajadas elementais (`SorcererOrb`), Metamagias (acelerar, gêmea) aplicadas via
Skript como modificadores temporários; ultimates como `StormNova` (240 s).
- **Habilidades Passivas**: afinidade elemental conforme linhagem (Fogo, Tempestade, Dragônica) via
RacesEffects; bônus de dano 12 % uptime 45/90.
- **Itens Focais**: Orbes e Bastões de linhagem (`DraconicFocus`, `StormRod`) protegidos por LuckPerms.

### Guerreiro
- **Função**: dano sustentado/tanque secundário.
- **Recursos**: `stamina` para manobras; `battle_focus` para Action Surge (2 cargas máximo em níveis altos).
- **Habilidades Ativas**: `/secondwind`, `/actionsurge`, manobras por arquétipo (`ChampionGreatsword`,
`BattlemasterBanner`, `EldritchKnightBlade`).
- **Habilidades Passivas**: bônus de estabilidade (knockback resist 0,05), proficiência em armas via
RacesEffects; incremento de dano plano por nível via AuraSkills.
- **Itens Focais**: medalhas e armas de arquétipo; grimório de manobras.

### Ladino
- **Função**: burst de dano e infiltração.
- **Recursos**: `stamina` para Passo Furtivo e `focus` para Ataque Furtivo (cooldown global 6 s).
- **Habilidades Ativas**: `/stealth`, `SmokeKnife`, `Shadowstep` (teleporte curto), `DistractionCoin`.
- **Habilidades Passivas**: Evasão (redução 50 % projéteis), Expertise (bônus em habilidades de interação)
configurados no RacesEffects.
- **Itens Focais**: Adaga de Ataque Furtivo, kit de ferramentas (`RogueKit`) liberados para grupos
`class_rogue_*`.

### Mago
- **Função**: controle de campo e utilidade arcana.
- **Recursos**: `mana` + `arcane_charges` (renova ao descansar; 3 cargas base).
- **Habilidades Ativas**: grimórios por escola (`EvocationCodex`, `IllusionTome`, `AbjurationWard`),
`/spellprep` para trocar loadout.
- **Habilidades Passivas**: livro de feitiços aumenta slots + redução 10 % cooldown ao permanecer imóvel;
RacesEffects aplica resistências situacionais por escola.
- **Itens Focais**: Bastões, varinhas, grimórios específicos por escola (LuckPerms `class_wizard_*`).

### Monge
- **Função**: mobilidade e controle corpo a corpo.
- **Recursos**: `ki` (pool inicial 4, regeneração 1 a cada 30 s fora de combate via AuraSkills).
- **Habilidades Ativas**: Rajada de Golpes (`FlurryWraps`), Passo do Vento (`WindCharm`), Mãos Curativas
(`MonkPrayerBeads`), Estilo de Tradição (Sombra, Quatro Elementos, Sol Nascente).
- **Habilidades Passivas**: Defesa sem armadura, Deflexão de Projéteis, redução de dano de queda; configurar
em RacesEffects.
- **Itens Focais**: Braceletes, mantos e pergaminhos por tradição (`class_monk_*`).

### Paladino
- **Função**: tanque/suporte.
- **Recursos**: `divinity` para imposição de mãos e `mana` para magias.
- **Habilidades Ativas**: `LayOnHands` (cura alvo, cooldown 90 s), `DivineSmite` (gasta 1 divinity), auras
ativáveis (`DevotionBanner`, `VengeanceSigil`).
- **Habilidades Passivas**: Aura de proteção (mitigação 10 % uptime 60 %), imunidade a medo condicional;
modificadores de AuraSkills aplicam bônus de CHA a resistência.
- **Itens Focais**: Espada sagrada, grimório de juramento (Devoção, Vingança, Antigo) com restrição LuckPerms.

### Patrulheiro (Ranger)
- **Função**: dano sustentado e controle em terreno.
- **Recursos**: `stamina` para manobras físicas e `focus` para habilidades de caçada.
- **Habilidades Ativas**: `/mark` (Marca do Caçador), Companheiro (`RangerHorn`), armadilhas como
`SnareTrap`, `VolleyBow` com cooldowns 45–120 s.
- **Habilidades Passivas**: bônus contra alvos marcados, exploração de bioma (velocidade 10 %); pets recebem
redução de dano 20 % configurada em RacesEffects.
- **Itens Focais**: Arco focal (`RangerLongbow`), punhal de floresta, apitos do companheiro.

### Feiticeiro vs Bruxo e demais classes
- As combinações multiclasse devem manter apenas um grupo LuckPerms ativo por vez (`/class set`).
- Recursos que compartilham mesmo nome devem ser sincronizados via scoreboard único para evitar exploits.

## Processo de Seleção de Classe
1. Jogador recebe item `ClassSelector` (Skript) ao concluir tutorial.
2. Seleção aplica grupo LuckPerms (`lp user <nick> parent set class_<id>`), remove grupos antigos e
entrega itens focais via MythicMobs.
3. AuraSkills ajusta atributos por nível de classe (`auraskills player <nick> attribute set`).
4. Scripts registram cooldowns em `plugins/Skript/data/class_cd.yml` para auditoria.

## Observações de Implementação
- Utilize `skRayFall` apenas para efeitos ativos (ultimates) para respeitar limites de partículas.
- Scripts de classe devem chamar habilidades MythicMobs pelo identificador (`mm skills cast`) evitando
comandos `/mm` ingame.
- Sempre documente custos e escalas no banco `docs/README_BALANCEAMENTO.md` após ajustes futuros.
