# Guia Operacional de Raças

Este documento substitui todos os materiais anteriores sobre raças e descreve a configuração oficial
para o servidor. Utilize-o como referência única para integração de plugins, entrega de itens e
orientações de balanceamento. Sempre recarregue o RacesEffects (`/raceseffects reload`) e os scripts
relevantes após qualquer alteração.

## Fluxo Padrão
- **Aplicação**: itens gerados por Skript (`/raceitem give <jogador> <id>`) ou atribuição direta de
grupo via LuckPerms (`lp user <nick> parent set race_<id>`).
- **Passivos**: implementar em **RacesEffects** com tags como `race_darkvision`, `trait_*` ou
`apply.attributeModifiers`.
- **Ativos**: habilidades acionadas por itens/comandos devem ser criadas em **MythicMobs** e
controladas por **Skript** (cooldown, condições, cargas).
- **Atributos**: sinergias listadas abaixo utilizam o sistema do **AuraSkills** para distribuir pontos
e recalcular modificadores.

## Elfos
### Elfo (base)
- **Passivos (RacesEffects)**: `race_darkvision`.
- **Ativos (Skript + MythicMobs)**: nenhum.
- **Atributos sugeridos (AuraSkills)**: DEX para mobilidade geral.
- **Grupo LuckPerms**: `race_elf`.

### Alto Elfo
- **Passivos**: Darkvision; retaliação elementar aleatória (Espinhos 2, Veneno I 5 s, Náusea I 5 s,
ou sem efeito) com intervalo interno de 5 s.
- **Ativos**: Amuleto Alto Elfo (`ElfHighAmulet`) configurado em MythicMobs para ativar a retaliação
manual quando necessário.
- **Atributos sugeridos**: INT como principal e WIS secundário para builds arcanas.
- **Grupo LuckPerms**: `race_elf_high`.

### Elfo da Floresta
- **Passivos**: Darkvision; `trait_speed_woodelf` (+0,02 velocidade base).
- **Ativos**: Broche da Floresta (`ElfWoodBrooch`) concede Invisibilidade 10 s; cooldown total 180 s
controlado por Skript.
- **Atributos sugeridos**: DEX principal para velocidade e CHA para furtividade social.
- **Grupo LuckPerms**: `race_elf_wood`.

### Drow
- **Passivos**: Darkvision; Fraqueza I sob céu aberto; chance de 45 % de aplicar Poison I 6 s +
Wither I 3 s ao atingir alvos em profundidade; integração com `trait_relentless` desativada.
- **Ativos**: Teia de Drow (`DrowWeb`) — teleporte aleatório em raio 7, 3 cargas, recarga global 240 s.
- **Atributos sugeridos**: DEX para mobilidade e INT para efeitos sombrios.
- **Grupo LuckPerms**: `race_elf_drow`.

## Anões
### Anão (base)
- **Passivos**: Darkvision; `trait_poison_resist` (mitigação 50 % contra veneno).
- **Ativos**: Picareta Anã (`DwarfRacialPick`) com tag de indestrutibilidade; sem efeitos ativos.
- **Atributos sugeridos**: CON primário; STR secundário para mineração e combate.
- **Grupo LuckPerms**: `race_dwarf`.

### Anão da Colina
- **Passivos**: herda base; `trait_hp_hilldwarf` (+2,0 vida máxima).
- **Ativos**: idem ao base.
- **Atributos sugeridos**: CON e WIS para resiliência.
- **Grupo LuckPerms**: `race_dwarf_hill`.

### Anão da Montanha
- **Passivos**: herda base; Força I permanente.
- **Ativos**: idem ao base.
- **Atributos sugeridos**: STR e CON.
- **Grupo LuckPerms**: `race_dwarf_mountain`.

### Anão das Profundezas
- **Passivos**: herda base; 45 % de chance de aplicar Cegueira 10 s no atacante ao sofrer dano.
- **Ativos**: mesma Picareta Anã com efeitos visuais extras via skRayFall.
- **Atributos sugeridos**: CON primário, WIS secundário para resistência.
- **Grupo LuckPerms**: `race_dwarf_deep`.

## Gnomos
### Gnomo (base)
- **Passivos**: Darkvision.
- **Ativos**: nenhum.
- **Atributos sugeridos**: INT para dispositivos e CHA para suportes.
- **Grupo LuckPerms**: `race_gnome`.

### Gnomo da Floresta
- **Passivos**: Darkvision; Speed III condicional quando ≤50 % da vida; 35 % de chance de
Invisibilidade 10 s ao sofrer dano crítico (≤50 % vida).
- **Ativos**: Engrenagem Gnômica (`GnomeCog`) gera poção aleatória (5 cargas, recarga 420 s).
- **Atributos sugeridos**: DEX principal; INT secundário.
- **Grupo LuckPerms**: `race_gnome_forest`.

### Gnomo das Rochas
- **Passivos**: Darkvision; Força II curta ao causar dano; empurrão no alvo (knockback extra).
- **Ativos**: Engrenagem Gnômica compartilha configuração com variação ofensiva (buff de dano 6 s).
- **Atributos sugeridos**: INT e STR.
- **Grupo LuckPerms**: `race_gnome_rock`.

## Halflings
### Halfling Pés-Leves
- **Passivos**: Supersalto I; Sorte II; furtividade aprimorada em AuraSkills (DEX+CHA).
- **Ativos**: Trombeta Halfling (`HalflingHorn`) paralisa até 5 alvos por 7 s; cooldown 120 s.
- **Atributos sugeridos**: DEX principal; CHA secundário.
- **Grupo LuckPerms**: `race_halfling_lightfoot`.

### Halfling Robusto
- **Passivos**: Pressa I curta; Sorte II; `trait_poison_resist` light (20 % mitigação com recarga 15 s).
- **Ativos**: Trombeta Halfling compartilhada.
- **Atributos sugeridos**: CON e DEX.
- **Grupo LuckPerms**: `race_halfling_stout`.

## Meio-Sangues
### Meio-Elfo
- **Passivos**: Darkvision; `Dolphin's Grace` periódico; Queda Lenta automática em alturas >6 blocos.
- **Ativos**: Pena do Meio-Elfo (`HalfElfFeather`) — Queda Lenta 7 s, 2 cargas, recarga 180 s.
- **Atributos sugeridos**: CHA e DEX.
- **Grupo LuckPerms**: `race_half_elf`.

### Meio-Orc
- **Passivos**: Darkvision; `Relentless Endurance` (sobrevive a 1 golpe fatal a cada 5 min com 20 % vida).
- **Ativos**: Talismã Berserker (`HalfOrcBerserkCharm`) — +15 % dano 12 s, cooldown 90 s.
- **Atributos sugeridos**: STR e CON.
- **Grupo LuckPerms**: `race_half_orc`.

## Humanos
- **Passivos**: Regeneração condicional — Regeneration II 15 s quando ≤50 % da vida com janela morta de 20 s.
- **Ativos**: Medalha Humana (`HumanMedal`) redistribui 3 pontos bônus de AuraSkills 1 vez por dia (reset via Skript).
- **Atributos sugeridos**: distribuição flexível; bônus racial +3 pontos livres.
- **Grupo LuckPerms**: `race_human`.

## Tiefling
- **Passivos**: Darkvision; `resist_fire`; Velocidade II no Nether com exaustão de resistência após absorver 3
instâncias de dano (30 s sem resistência).
- **Ativos**: Selo Tiefling (`TieflingSigil`) — orbe de fogo 140 % dano, cooldown 90 s.
- **Atributos sugeridos**: CHA principal; INT secundário.
- **Grupo LuckPerms**: `race_tiefling`.

## Dragonborn (Draconato)
Aplicados via livro específico. Todos compartilham +1,0 vida máxima e item de Sopro com cooldown base 45 s
controlado por Skript.

### Dragonborn do Fogo
- **Passivos**: Resistência a fogo; tag `db_fire`.
- **Ativos**: Sopro de fogo em cone (dano + queimadura 6 s).
- **Atributos sugeridos**: STR e CHA.
- **Grupo LuckPerms**: `race_dragonborn_fire`.

### Dragonborn do Frio
- **Passivos**: Imunidade a Powder Snow; tag `db_cold`.
- **Ativos**: Sopro gélido em área 5×5 aplicando Lentidão II 8 s.
- **Atributos sugeridos**: CON e WIS.
- **Grupo LuckPerms**: `race_dragonborn_cold`.

### Dragonborn do Relâmpago
- **Passivos**: Resistência breve a dano elétrico (2 s após raio); tag `db_lightning`.
- **Ativos**: Sopro linear que invoca `lightning_bolt` (stun 1,5 s).
- **Atributos sugeridos**: DEX e INT.
- **Grupo LuckPerms**: `race_dragonborn_lightning`.

### Dragonborn do Ácido
- **Passivos**: Tag `db_acid` reduz dano corrosivo em 60 %; limpeza de Wither a cada 30 s.
- **Ativos**: Nuvem ácida persistente (dano por 6 s; aplica Enfraquecimento).
- **Atributos sugeridos**: CON e STR.
- **Grupo LuckPerms**: `race_dragonborn_acid`.

### Dragonborn Venenoso
- **Passivos**: Tag `db_poison` concede 60 % mitigação a veneno.
- **Ativos**: Nuvem venenosa com DOT 4 %/s por 5 s.
- **Atributos sugeridos**: CON e WIS.
- **Grupo LuckPerms**: `race_dragonborn_poison`.

## Tags Compartilhadas e Scripts
- Reaplicar itens em respawn usando `class_items_new_fixed.sk`.
- Utilizar `skBee` para proteger NBT dos itens raciais.
- Habilitar partículas com `skRayFall` apenas em habilidades ativas para não ultrapassar o limite de
uptime (≤60 %).
