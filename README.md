# Servidor D&D 5e em Minecraft

Este repositório reúne a configuração mínima de um servidor Paper focado em reproduzir mecânicas de Dungeons & Dragons 5ª Edição. Os plugins centrais são LuckPerms para permissões/meta, ValhallaMMO para atributos/rolagens, MythicMobs para habilidades e EssentialsX para utilidades gerais.

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
LuckPerms organiza classes por escadas de nível que herdam permissões e concedem características. O exemplo implementado é o **Fighter (Guerreiro)**; os demais (barbarian, bard, cleric, druid, monk, paladin, ranger, rogue, sorcerer, warlock, wizard) devem seguir o mesmo padrão.【F:luckperms/commands.txt†L70-L105】

- **Fighter:**
  - Estrutura de grupos de nível 1–20 encadeados para herdar características.【F:luckperms/commands.txt†L72-L88】
  - Características: Second Wind (nível 1), Action Surge (2), Extra Attack (5), Extra Attack 2 (11), Extra Attack 3 (20).【F:luckperms/commands.txt†L90-L95】
  - Salvaguardas: proficiência em testes de STR e CON.【F:luckperms/commands.txt†L97-L99】
  - Skills correspondentes no MythicMobs: Action Surge concede ação extra e partículas; Extra Attack aplica múltiplos ataques.【F:mythicmobs/skills/dnd_features.yml†L3-L24】

- **Outras Classes (estrutura a replicar):** barbarian (inclui Rage), bard, cleric, druid, monk (usa recurso de Ki e Flurry of Blows), paladin, ranger, rogue, sorcerer, warlock, wizard.【F:luckperms/commands.txt†L104-L105】【F:mythicmobs/skills/dnd_features.yml†L25-L75】

## Backgrounds (BG)
Backgrounds são aplicados como grupos LuckPerms que concedem proficiências e kits.【F:luckperms/commands.txt†L106-L114】
- **Acolyte:** proficiências em Insight e Religion, mais kit inicial temático.【F:luckperms/commands.txt†L108-L112】
- **Outros backgrounds sugeridos:** guild_artisan, criminal, hermit, folk_hero, noble, outlander, sage, soldier, urchin.【F:luckperms/commands.txt†L114-L114】

## Truques e Magias
Habilidades de combate e truques são configurados como skills do MythicMobs e consomem dados da ValhallaMMO (como bônus de proficiência).【F:mythicmobs/skills/dnd_features.yml†L1-L75】
- **Fire Bolt (Cantrip):** projétil com trilha de chamas, causa `2 + PB` de dano de fogo e incendeia o alvo.【F:mythicmobs/skills/dnd_features.yml†L32-L44】
- **Sacred Flame (Cantrip):** rolagem com variação se o alvo tem vantagem marcada; causa `1 + PB` ou `2 + PB` de dano radiante conforme a condição.【F:mythicmobs/skills/dnd_features.yml†L46-L59】
- **Flurry of Blows (Ki):** gasta 1 ponto de Ki e aplica dois golpes extras baseados no modificador de DEX.【F:mythicmobs/skills/dnd_features.yml†L61-L68】
- **Action Surge (Fighter):** concede ação adicional com tempo de recarga e mensagem temática.【F:mythicmobs/skills/dnd_features.yml†L12-L24】
- **Rage (Barbarian):** aumenta dano e resistência por 1 minuto (potions de força e resistência).【F:mythicmobs/skills/dnd_features.yml†L25-L30】
- **Short Rest Reset:** repõe Ki e zera recargas de Action Surge e Rage, simulando descanso curto.【F:mythicmobs/skills/dnd_features.yml†L69-L75】

## Como aplicar no servidor
1. Instale os plugins listados na pasta `plugins/` em um servidor Paper compatível.
2. Importe o arquivo `valhallammo/config.yml` na pasta de dados do ValhallaMMO.
3. Copie `mythicmobs/skills/dnd_features.yml` para `MythicMobs/Skills/` e recarregue o MythicMobs.
4. Execute os comandos de `luckperms/commands.txt` (ajustando `<player>`) para criar grupos de raças, classes e backgrounds.

