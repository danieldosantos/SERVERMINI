# Servidor D&D 5e em Minecraft

Este repositório reúne a configuração mínima de um servidor Paper focado em reproduzir mecânicas de Dungeons & Dragons 5ª Edição. Os plugins centrais são LuckPerms para permissões/meta, ValhallaMMO para atributos/rolagens, MythicMobs para habilidades e EssentialsX para utilidades gerais.

## Documentação de magias e condições
- [Implantação de magias e condições](docs/magias-condicoes.md): resumo das listas por classe, efeitos prioritários para MythicMobs e tabela de condições para espelhar em LuckPerms.
- [Equipamentos do Livro do Jogador](docs/equipamentos.md): custos, pesos e propriedades de armas, armaduras, kits e montarias do capítulo 5 para replicar loots e lojas. Os itens já estão definidos no MythicMobs em `mythicmobs/items/dnd_equipment.yml` para spawnar via `/mm items get <ItemName>` ou reutilizar em tabelas de loot.

## Capacidade geral do servidor
- **Atributos principais:** Strength, Dexterity, Constitution, Intelligence, Wisdom e Charisma, todos com intervalo 1–30 e valor inicial 10, mapeados pela ValhallaMMO. Modificadores usam a fórmula `floor((score - 10) / 2)`.【F:valhallammo/config.yml†L1-L44】
- **Bônus de Proficiência:** tabela completa de níveis 1–20, podendo ser definida por meta de LuckPerms ou pela tabela de fallback configurada.【F:valhallammo/config.yml†L45-L69】
- **Rolagens de teste:** salvaguardas e perícias usam `d20 + modificador + proficiência (quando aplicável)` e respeitam flags de vantagem/desvantagem vindas do meta do jogador.【F:valhallammo/config.yml†L71-L81】
- **Combate simplificado:** pontos de vida base `8 + CON mod por nível`, dado de vida padrão 5 e classe de armadura `10 + DES mod + bônus de armadura`.【F:valhallammo/config.yml†L83-L89】
- **Recursos de classe:** Ki de Monge definido como recurso por nível, regenera em descanso curto; skills do MythicMobs aplicam Action Surge, Rage, Flurry of Blows, extra ataques e rest reset.【F:valhallammo/config.yml†L91-L97】【F:mythicmobs/skills/dnd_features.yml†L12-L75】

## Raças
As entradas de LuckPerms criam grupos de raça com bônus de atributo e algumas permissões temáticas.【F:luckperms/commands.txt†L12-L68】
- **Wood Elf:** +2 DEX, +1 WIS, Visão no Escuro, proficiência em armas simples.【F:luckperms/commands.txt†L13-L18】
- **Hill Dwarf:** +2 CON, +1 WIS, Visão no Escuro, proficiência em machado de batalha e machado de mão.【F:luckperms/commands.txt†L24-L29】
- **Lightfoot Halfling:** +2 DEX, +1 CHA.【F:luckperms/commands.txt†L31-L33】
- **Forest Gnome:** +2 INT, +1 DEX, Visão no Escuro.【F:luckperms/commands.txt†L35-L38】
- **Half-Elf:** +2 CHA, +1 DEX, +1 CON, Visão no Escuro.【F:luckperms/commands.txt†L40-L44】
- **Half-Orc:** +2 STR, +1 CON, Visão no Escuro, proficiência em armas marciais.【F:luckperms/commands.txt†L46-L50】
- **Dragonborn:** +2 STR, +1 CHA, permissão de "breath weapon" para habilidades de sopro.【F:luckperms/commands.txt†L52-L55】
- **Human:** +1 em todas as habilidades (STR, DEX, CON, INT, WIS, CHA).【F:luckperms/commands.txt†L57-L63】
- **Tiefling:** +1 INT, +2 CHA, Visão no Escuro.【F:luckperms/commands.txt†L65-L68】

## Classes
LuckPerms organiza classes por escadas de nível que herdam permissões e concedem características. Todas as 12 classes básicas estão mapeadas com cadeias de grupos de nível e proficiências de salvaguarda.【F:luckperms/commands.txt†L70-L487】

- **Fighter:**
  - Estrutura de grupos de nível 1–20 encadeados para herdar características.【F:luckperms/commands.txt†L72-L88】
  - Características: Second Wind (nível 1), Action Surge (2), Extra Attack (5), Extra Attack 2 (11), Extra Attack 3 (20).【F:luckperms/commands.txt†L90-L95】
  - Salvaguardas: proficiência em testes de STR e CON.【F:luckperms/commands.txt†L97-L99】
  - Skills correspondentes no MythicMobs: Action Surge concede ação extra e partículas; Extra Attack aplica múltiplos ataques.【F:mythicmobs/skills/dnd_features.yml†L3-L24】

- **Barbarian:** Rage, Reckless Attack e Danger Sense logo no início; Extra Attack no nível 5 e capstone Primal Champion no 20.【F:luckperms/commands.txt†L107-L132】 Salvaguardas em STR e CON.【F:luckperms/commands.txt†L134-L135】

- **Bard:** Bardic Inspiration, Jack of All Trades e Expertise nas primeiras camadas, com Magical Secrets e Superior Inspiration como marcos superiores.【F:luckperms/commands.txt†L140-L161】 Salvaguardas em DEX e CHA.【F:luckperms/commands.txt†L163-L164】

- **Cleric:** Divine Domain, Channel Divinity, Destroy Undead e melhorias de Divine Intervention ao longo dos níveis.【F:luckperms/commands.txt†L170-L191】 Salvaguardas em WIS e CHA.【F:luckperms/commands.txt†L193-L194】

- **Druid:** Druidic e Wild Shape de base, com evolução para Primal Strike, Elemental Shapes e Archdruid no nível 20.【F:luckperms/commands.txt†L200-L221】 Salvaguardas em INT e WIS.【F:luckperms/commands.txt†L223-L224】

- **Monk:** Martial Arts, Ki pool, Deflect Missiles, Extra Attack e toques de alto nível como Diamond Soul e Perfect Self.【F:luckperms/commands.txt†L230-L251】 Salvaguardas em STR e DEX.【F:luckperms/commands.txt†L253-L254】

- **Paladin:** Lay on Hands, Divine Smite, Extra Attack, auras de proteção/coragem e Holy Nimbus como capstone.【F:luckperms/commands.txt†L260-L281】 Salvaguardas em WIS e CHA.【F:luckperms/commands.txt†L283-L284】

- **Ranger:** Favored Enemy, Natural Explorer, Primeval Awareness, Extra Attack e culmina em Foe Slayer no nível 20.【F:luckperms/commands.txt†L290-L311】 Salvaguardas em STR e DEX.【F:luckperms/commands.txt†L313-L314】

- **Rogue:** Sneak Attack, Cunning Action, Assassinate, Uncanny Dodge, Evasion e Stroke of Luck no final.【F:luckperms/commands.txt†L320-L341】 Salvaguardas em DEX e INT.【F:luckperms/commands.txt†L343-L344】

- **Sorcerer:** Spellcasting, Font of Magic, Metamagic, Elemental Affinity e capstone Sorcerous Restoration.【F:luckperms/commands.txt†L350-L371】 Salvaguardas em CON e CHA.【F:luckperms/commands.txt†L373-L374】

- **Warlock:** Pact Magic, Eldritch Invocations, Pact Boon, Mystic Arcanum e Eldritch Master no topo.【F:luckperms/commands.txt†L380-L401】 Salvaguardas em WIS e CHA.【F:luckperms/commands.txt†L403-L404】

- **Wizard:** Arcane Recovery e Tradition iniciais; Arcane Ward, Overchannel e Signature Spell como destaques de nível alto.【F:luckperms/commands.txt†L410-L431】 Salvaguardas em INT e WIS.【F:luckperms/commands.txt†L433-L434】

## Sistema de XP de classe
- O progresso de classe agora é automático via `plugins/Skript/scripts/class_progression.sk`: ganhar XP ao matar mobs, minerar, craftar e reproduzir animais.
- O comando `/classe <nome>` define a classe ativa (ex.: Fighter, Barbarian). O script remove grupos antigos da classe e aplica o grupo LuckPerms correto (`class_<classe>_lvlN`) conforme o nível.
- A tabela de XP por nível segue os valores oficiais de D&D 5e (níveis 1–20). A barra de ação mostra o progresso para o próximo nível enquanto se joga.

## Backgrounds (BG)
Backgrounds são aplicados como grupos LuckPerms que concedem proficiências e kits.【F:luckperms/commands.txt†L489-L548】
- **Acolyte:** proficiências em Insight e Religion, mais kit inicial temático.【F:luckperms/commands.txt†L490-L495】
- **Guild Artisan:** Insight e Persuasion para representar negociação e ofício, com kit de ferramentas de artesão.【F:luckperms/commands.txt†L497-L502】
- **Criminal:** Deception e Stealth para infiltração e mentiras, com kit furtivo.【F:luckperms/commands.txt†L504-L509】
- **Hermit:** Medicine e Religion para o eremita estudioso, com kit de isolamento e pesquisa.【F:luckperms/commands.txt†L511-L516】
- **Folk Hero:** Animal Handling e Survival para o herói rural, com kit de campo.【F:luckperms/commands.txt†L518-L523】
- **Noble:** History e Persuasion para diplomacia e erudição, com kit de nobreza.【F:luckperms/commands.txt†L525-L530】
- **Outlander:** Athletics e Survival para exploração e resistência, com kit de viajante.【F:luckperms/commands.txt†L532-L537】
- **Sage:** Arcana e History para conhecimento arcano e acadêmico, com kit de pesquisa.【F:luckperms/commands.txt†L539-L544】
- **Soldier:** Athletics e Intimidation para combate e presença marcial, com kit militar.【F:luckperms/commands.txt†L546-L551】
- **Urchin:** Sleight of Hand e Stealth para furtividade urbana, com kit improvisado.【F:luckperms/commands.txt†L553-L558】

## Truques e Magias
 Habilidades de combate e truques são configurados como skills do MythicMobs e consomem dados da ValhallaMMO (como bônus de proficiência).【F:mythicmobs/skills/dnd_features.yml†L1-L150】 O pacote inclui apenas um subconjunto essencial de truques e magias para testes rápidos; para adicionar o restante do Livro do Jogador, siga o guia em `mythicmobs/skills/ADDING_SPELLS.md`.
- **Fire Bolt (Cantrip):** projétil com trilha de chamas, causa `2 + PB` de dano de fogo e incendeia o alvo.【F:mythicmobs/skills/dnd_features.yml†L32-L44】
- **Sacred Flame (Cantrip):** rolagem com variação se o alvo tem vantagem marcada; causa `1 + PB` ou `2 + PB` de dano radiante conforme a condição.【F:mythicmobs/skills/dnd_features.yml†L46-L59】
- **Flurry of Blows (Ki):** gasta 1 ponto de Ki e aplica dois golpes extras baseados no modificador de DEX.【F:mythicmobs/skills/dnd_features.yml†L61-L68】
- **Action Surge (Fighter):** concede ação adicional com tempo de recarga e mensagem temática.【F:mythicmobs/skills/dnd_features.yml†L12-L24】
- **Rage (Barbarian):** aumenta dano e resistência por 1 minuto (potions de força e resistência).【F:mythicmobs/skills/dnd_features.yml†L25-L30】
- **Short Rest Reset:** repõe Ki e zera recargas de Action Surge e Rage, simulando descanso curto.【F:mythicmobs/skills/dnd_features.yml†L69-L75】
- **Vicious Mockery (Bard Cantrip):** dano psíquico rápido escalando com CHA e PB, além de Enfraquecimento curto no alvo.【F:mythicmobs/skills/dnd_features.yml†L77-L83】
- **Guidance (Cleric Cantrip):** concede o efeito de Sorte por alguns segundos para reforçar o próximo teste.【F:mythicmobs/skills/dnd_features.yml†L85-L89】
- **Produce Flame (Druid Cantrip):** projétil de fogo contínuo que usa o modificador de WIS e ateia chamas ao acertar.【F:mythicmobs/skills/dnd_features.yml†L91-L100】
- **Divine Smite (Paladin):** golpe corpo a corpo com partículas de totem e dano radiante escalado por CHA e PB.【F:mythicmobs/skills/dnd_features.yml†L102-L107】
- **Hunter's Mark (Ranger):** marca o alvo com brilho, concede bônus temporário de dano e mensagem de feedback.【F:mythicmobs/skills/dnd_features.yml†L109-L114】
- **Chaos Bolt (Sorcerer):** projétil elétrico com trilha crítica, dano escalado por CHA e levitação curta no alvo atingido.【F:mythicmobs/skills/dnd_features.yml†L116-L124】
- **Eldritch Blast (Warlock Cantrip):** rajada arcana que empurra o alvo após causar dano escalonado por CHA.【F:mythicmobs/skills/dnd_features.yml†L126-L132】
- **Magic Missile (Wizard):** três dardos automáticos consecutivos, cada um aplicando dano que soma PB e INT do conjurador.【F:mythicmobs/skills/dnd_features.yml†L134-L140】

## Como aplicar no servidor
1. Instale os plugins listados na pasta `plugins/` em um servidor Paper compatível.
2. Importe o arquivo `valhallammo/config.yml` na pasta de dados do ValhallaMMO.
3. Copie `mythicmobs/skills/dnd_features.yml` para `MythicMobs/Skills/` e recarregue o MythicMobs.
4. Execute os comandos de `luckperms/commands.txt` (ajustando `<player>`) para criar grupos de raças, classes e backgrounds.

## Atalho para abrir os atributos no cliente vanilla
- O comando `/c` foi adicionado como alias para `/valhalla profile`, abrindo a interface de atributos do ValhallaMMO sem depender de cliente modificado.【F:commands.yml†L4-L8】
- Os valores iniciais continuam baseados na configuração de D&D (10 em cada atributo, mínimo 1, máximo 30) e recebem os bônus raciais definidos no LuckPerms ao entrar no servidor.【F:valhallammo/config.yml†L1-L34】【F:luckperms/commands.txt†L12-L68】

