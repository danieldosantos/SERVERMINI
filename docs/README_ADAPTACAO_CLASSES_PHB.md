# Guia de Adaptação das Classes do PHB para Minecraft

Este guia descreve um passo a passo padronizado para implementar todas as classes do Player's Handbook (D&D 5E) no servidor, respeitando as diretrizes definidas em `README_BALANCEAMENTO.md`.

## Estrutura Geral de Implementação
1. **Definir Função no Combate**: tanque, dano, suporte ou controle. Permite calibrar atributos conforme limites recomendados.
2. **Mapear Habilidades Ativas**: ações acionadas por comando/item. Cada habilidade deve especificar custo (recurso, cooldown, consumível).
3. **Mapear Habilidades Passivas**: bônus contínuos sujeitos às restrições de uptime (<60 %).
4. **Estabelecer Regras de Cooldown**: basear-se em tiers (níveis) para escalar recarga e duração.
5. **Selecionar Itens Focais**: item MythicMobs/Skript para acionar skills ou representar foco arcano/divino.
6. **Definir Progressão por Tier**: marcos narrativos (ex.: Iniciado → Veterano → Mestre) equivalendo a níveis 1/5/11/17 do PHB.
7. **Sinergia com Raças**: garantir que nenhum combo elimine fraquezas raciais listadas no guia de balanceamento.

## Padrões Numéricos
- **Cooldowns**: habilidades-chave ≥45 s; habilidades situacionais ≥90 s; ultimates ≥180 s.
- **Recursos**: utilizar contadores (scoreboards) ou mana do ProSkillAPI com regeneração previsível.
- **Itens Focais**: 1 item principal por classe + itens de subclasse (máx. 2) com stack limitado a 1.

## Classes
Cada classe abaixo segue o formato padrão.

### Bárbaro
- **Função no Combate**: tanque/dano corpo a corpo.
- **Habilidades Ativas**: Fúria (`/rage`), Ataque Temerário (`/reckless`), Totens (itens dedicados).
- **Habilidades Passivas**: Defesa sem armadura, Percepção do Perigo (redução extra a explosões).
- **Regras de Cooldown**: Fúria 120 s duração / 180 s recarga; Ataque Temerário 20 s duração / 60 s recarga.
- **Itens Focais**: `BarbarianRageCharm`, totens (`TotemBearCharm`, etc.).
- **Progressão por Tiers**: desbloquear novos totens nos tiers 2 e 3; ampliar bônus de Fúria somente no tier 3.
- **Sinergia com Raças**: evitar combinação com Resistência extra (ex.: Dragonborn Fogo) sem aplicar exaustão de resistência.

#### Habilidades por Arquétipo
- **Berserker (manobras)**
  1. **Grito Bestial** – item `BerserkerWarHorn`; aplica Fraqueza (-10 % dano) nos inimigos num raio de 6 blocos por 12 s. Custo: 1 carga de Fúria, cooldown 75 s.
  2. **Investida Sanguinária** – item `BerserkerBattleAxe`; avança 5 blocos, causando 160 % do dano da arma. Se atingir, concede 20 % de redução por 6 s. Cooldown 45 s.
  3. **Retaliação Frenética** – item `BerserkerSpikedShield`; devolve 30 % do dano recebido em 8 s. Ativável apenas abaixo de 50 % de vida. Cooldown 90 s.
  4. **Impetus Irrefreável** – item `BerserkerBoots`; remove efeitos de lentidão e fornece +0,04 de velocidade por 10 s. Cooldown 60 s.
  5. **Carnificina Implacável (Ultimate)** – item `BerserkerTotem`; canaliza por 3 s e ativa modo por 15 s: ataques atingem em área de 3 blocos e convertem 25 % do dano em cura. Cooldown 210 s.
- **Guardião Totêmico (manobras)**
  1. **Totem do Urso** – item `TotemBearCharm`; reduz 35 % do dano físico nos aliados a 5 blocos por 10 s. Custo: 1 carga de Fúria. Cooldown 120 s.
  2. **Totem da Águia** – item `TotemEagleCharm`; concede visão noturna e alcance de ataque +1 bloco por 15 s. Cooldown 75 s.
  3. **Totem do Lobo** – item `TotemWolfCharm`; marca inimigo primário para aliados, aumentando dano em 12 % por 12 s. Cooldown 90 s.
  4. **Totem do Tigre** – item `TotemTigerCharm`; concede +30 % velocidade de ataque por 8 s. Cooldown 70 s.
  5. **Totem da Fênix (Ultimate)** – item `TotemPhoenixCharm`; cria aura de 8 blocos por 12 s que revive aliados caídos com 40 % de vida (1 vez cada). Cooldown 240 s.
- **Arauto da Tempestade (manobras elementais)**
  1. **Martelo do Trovão** – item `StormWarHammer`; golpe frontal com 140 % dano + atordoamento de 2 s. Cooldown 45 s.
  2. **Surto Galvânico** – item `StormChargeGauntlet`; adiciona 3 descargas consecutivas (40 % dano cada) em 8 s. Cooldown 60 s.
  3. **Capa de Ventos** – item `StormMantle`; concede +50 % de resistência a projéteis por 10 s e empurra inimigos a 4 blocos. Cooldown 90 s.
  4. **Caldeirão da Tempestade** – item `StormDrum`; invoca chuva elétrica em área de 6 blocos causando 50 % dano/2 s por 10 s. Cooldown 120 s.
  5. **Olho do Furacão (Ultimate)** – item `StormEyeTotem`; cria vórtice de 8 blocos por 12 s que puxa inimigos lentamente e aplica Vulnerabilidade elétrica (+20 % dano). Cooldown 210 s.

### Bardo
- **Função no Combate**: suporte híbrido.
- **Habilidades Ativas**: Inspiração (`/inspire`), Canção de Descanso (`/song`).
- **Habilidades Passivas**: bônus situacional a habilidades sociais (interações NPC) e recarga reduzida de instrumentos.
- **Regras de Cooldown**: Inspiração 60 s; Canção 60 s recarga / 30 s duração.
- **Itens Focais**: `BardLute`, `BardLoreBook`, medalhões de subclasse.
- **Progressão por Tiers**: ampliar número de cargas de Inspiração (até 3 simultâneas) e raio da canção no tier 3.
- **Sinergia com Raças**: combos com Meio-Elfo concedem bônus sociais adicionais mas não aumentam dano; manter limites de buff do balanceamento.

#### Magias por Colégio
- **Colégio do Conhecimento**
  1. **Verso Analisador** – item `LoreSongbook`; revela vida e buffs do alvo e reduz resistência mágica em 10 % por 8 s. Cooldown 45 s.
  2. **Balada dos Segredos** – item `LoreLute`; remove 1 buff aleatório de inimigos a 6 blocos. Custo: 20 mana, cooldown 75 s.
  3. **Palavra Desconcertante** – item `LoreFocusQuill`; aplica silenciamento de 4 s em alvo único. Cooldown 60 s.
  4. **Orquestração Perfeita** – item `LoreConductorsBaton`; reduz em 20 % os cooldowns dos aliados a 8 blocos por 12 s. Cooldown 120 s.
  5. **Sinfonia Onisciente (Ultimate)** – item `LoreRelicHarp`; canaliza por 2 s, revelando todos os inimigos no mapa por 10 s e concedendo +15 % dano e +15 % cura para aliados por 15 s. Cooldown 240 s.
- **Colégio do Valor**
  1. **Hino da Coragem** – item `ValorWarDrum`; concede +10 % mitigação e imunidade a medo por 12 s. Cooldown 60 s.
  2. **Ritmo de Batalha** – item `ValorWarHorn`; aumenta velocidade de ataque em 15 % e concede 2 projéteis extras para arqueiros por 10 s. Cooldown 75 s.
  3. **Coda Inspirador** – item `ValorBanner`; aplica escudo de 120 pontos aos aliados mais próximos. Cooldown 90 s.
  4. **Marcha Recuada** – item `ValorTrumpet`; reposiciona aliados a 6 blocos para trás e concede +0,03 velocidade por 8 s. Cooldown 105 s.
  5. **Cantiga dos Heróis (Ultimate)** – item `ValorSacredLute`; reanima aliados caídos nos últimos 10 s com 35 % de vida e concede invulnerabilidade por 3 s. Cooldown 240 s.
- **Colégio do Glamour**
  1. **Encanto Hipnótico** – item `GlamourTiara`; seduz inimigos a 5 blocos forçando-os a ficar imóveis por 3 s. Cooldown 45 s.
  2. **Manto de Inspiração** – item `GlamourCloak`; distribui 60 pontos de escudo a até 5 aliados e permite deslocamento livre por 3 s. Cooldown 75 s.
  3. **Brilho Deslumbrante** – item `GlamourCrystal`; cega inimigos por 4 s e aplica -15 % precisão. Cooldown 60 s.
  4. **Passo Férico** – item `GlamourFaerieCharm`; teleporta o bardo e até 2 aliados para 12 blocos escolhidos. Cooldown 120 s.
  5. **Refrão Sublime (Ultimate)** – item `GlamourStarLyre`; cria área de 7 blocos que cura 6 % da vida máxima a cada 2 s e remove debuffs por 12 s. Cooldown 240 s.

### Bruxo (Warlock)
- **Função no Combate**: dano/controlador à distância.
- **Habilidades Ativas**: Hex (`/hex`), Rajada Mística (`/eldritch`), habilidades de patrono.
- **Habilidades Passivas**: pactos fornecem bônus de mobilidade/resistência moderada.
- **Regras de Cooldown**: Hex 45 s por alvo; rajada com janela de 3 projéteis por 10 s e recarga de 5 s.
- **Itens Focais**: `EldritchWand`, `HexTalisman`, marcas de patrono.
- **Progressão por Tiers**: slots de invocação extras a cada tier; desbloqueio de invocações maiores no tier 3.
- **Sinergia com Raças**: restringir acumulação com Tiefling (foco em fogo) limitando bônus de resistência conforme diretriz.

#### Magias por Patrono
- **Pacto do Fiend**
  1. **Chicote Infernal** – item `FiendWhip`; causa 180 % dano de fogo em cone de 5 blocos. Gera 1 stack de Calor Infernal (até 3) aumentando dano recebido em 5 %. Cooldown 45 s.
  2. **Armadilha de Brasas** – item `FiendBrand`; coloca sigilo que explode após 4 s causando 140 % dano e enraizando por 2 s. Cooldown 75 s.
  3. **Contrato de Chamas** – item `FiendPactScroll`; converte 12 % do dano causado em cura por 10 s. Cooldown 90 s.
  4. **Portal Ígneo** – item `FiendGateKey`; teleporta o bruxo para alvo visível a 15 blocos deixando trilha de fogo (40 % dano/seg por 4 s). Cooldown 120 s.
  5. **Inferno Pactuado (Ultimate)** – item `FiendHellcore`; chama meteoro que causa 250 % dano em 6 blocos e aplica queimadura 8 %/s por 8 s. Cooldown 240 s.
- **Pacto do Archfey**
  1. **Explosão Encantadora** – item `ArchfeyWand`; dano arcano 150 % + confusão de 3 s. Cooldown 40 s.
  2. **Passo Brumoso** – item `ArchfeyMirror`; teleporta 10 blocos e deixa clones que distraem (perda de alvo por 2 s). Cooldown 60 s.
  3. **Domínio do Sonho** – item `ArchfeyDreamOrb`; adormece alvo por 4 s ou até receber dano pesado. Cooldown 90 s.
  4. **Laço Feérico** – item `ArchfeyGroveCharm`; raízes surgem em 7 blocos aplicando enredamento de 5 s. Cooldown 120 s.
  5. **Cortejo da Rainha (Ultimate)** – item `ArchfeyScepter`; convoca espíritos que orbitam por 15 s, causando 70 % dano arcano/2 s e reduzindo cooldowns em 20 % para aliados dentro. Cooldown 240 s.
- **Pacto do Grande Antigo**
  1. **Sussurros Devastadores** – item `GreatOldOneTome`; dano psíquico 120 % e aplica Desespero (+15 % dano recebido) por 8 s. Cooldown 45 s.
  2. **Visões Alienígenas** – item `GreatOldOneRelic`; revela inimigos camuflados a 12 blocos e causa lentidão 30 % por 6 s. Cooldown 75 s.
  3. **Marionete Mental** – item `GreatOldOneChain`; obriga alvo a andar 3 blocos na direção escolhida e silencia por 3 s. Cooldown 90 s.
  4. **Cascata de Loucura** – item `GreatOldOneCrystal`; aplica dano em área (100 % + 10 % por stack de Loucura) em 6 blocos. Cooldown 120 s.
  5. **Abismo Sussurrante (Ultimate)** – item `GreatOldOneEye`; cria fenda por 12 s sugando inimigos e aplicando medo, gerando 20 % de mitigação ao bruxo. Cooldown 240 s.

### Clérigo
- **Função no Combate**: suporte/curador.
- **Habilidades Ativas**: Canalizar Divindade (cura em área, banimento), Palavra Curativa (cura pontual), proteções.
- **Habilidades Passivas**: aura de resistência leve (+10 % mitigação em aliados próximos).
- **Regras de Cooldown**: Canalizar 120 s; Palavra Curativa 30 s por alvo; aura permanente com uptime ≤60 % (ativação manual).
- **Itens Focais**: Símbolo sagrado (cajado ou totem), livro de oração por domínio.
- **Progressão por Tiers**: desbloqueio de um segundo uso de Canalizar no tier 3; domínios concedem habilidade única no tier 2.
- **Sinergia com Raças**: humanos e anões recebem bônus temáticos (cura adicional 5 %), mantendo limites de cura do balanceamento.

#### Magias por Domínio
- **Domínio da Vida**
  1. **Fonte Restauradora** – item `LifeHolySymbol`; cura 18 % da vida máxima de um aliado e remove 1 debuff. Cooldown 45 s.
  2. **Escudo de Fé** – item `LifeGuardianMace`; concede escudo de 150 pontos e +15 % resistência por 8 s. Cooldown 60 s.
  3. **Toque Purificador** – item `LifePrayerBeads`; dissipa veneno/enfermidade em área de 5 blocos. Cooldown 75 s.
  4. **Bênção Abundante** – item `LifeSanctuaryUrn`; aumenta cura recebida por aliados em 20 % por 12 s. Cooldown 105 s.
  5. **Milagre Vital (Ultimate)** – item `LifeDivineRelic`; ressuscita aliado caído com 60 % de vida e imunidade a dano por 4 s. Cooldown 240 s.
- **Domínio da Guerra**
  1. **Golpe Divinamente Guiado** – item `WarSacredHammer`; buffa próximo ataque de aliado com +30 % dano e penetração. Cooldown 45 s.
  2. **Estilhaço Consagrado** – item `WarHolySpear`; lança projétil de luz causando 150 % dano e aplicando Vulnerabilidade 10 % por 8 s. Cooldown 60 s.
  3. **Estandarte Sagrado** – item `WarBattleBanner`; aumenta em 10 % o dano aliado e 10 % mitigação por 10 s. Cooldown 90 s.
  4. **Marcha do Zelote** – item `WarWarhorn`; concede +0,04 velocidade e resistência a empurrão por 8 s. Cooldown 75 s.
  5. **Coroa do Campeão (Ultimate)** – item `WarCelestialCrown`; transforma ataques aliados em dano radiante bônus (50 %) por 12 s e concede cura de 5 % ao acertar. Cooldown 210 s.
- **Domínio do Crepúsculo**
  1. **Manto do Poente** – item `TwilightLantern`; concede visão noturna e redução de dano à distância 25 % por 12 s. Cooldown 60 s.
  2. **Raio Crepuscular** – item `TwilightOrb`; dispara feixe que causa 140 % dano radiante e desacelera em 40 %. Cooldown 50 s.
  3. **Refúgio Noturno** – item `TwilightSanctum`; cria domo de 6 blocos bloqueando projéteis por 8 s. Cooldown 90 s.
  4. **Bruma Reconfortante** – item `TwilightCenser`; cura 5 %/s por 6 s aliados dentro e remove efeitos mentais. Cooldown 120 s.
  5. **Eclipse Sagrado (Ultimate)** – item `TwilightDiadem`; alterna dia/noite local reduzindo visão inimiga e concedendo +20 % cura por 15 s. Cooldown 240 s.

### Druida
- **Função no Combate**: controle e flexibilidade.
- **Habilidades Ativas**: Forma Selvagem (transformações), conjurações de terreno (raízes, neblina).
- **Habilidades Passivas**: regeneração leve em biomas naturais, afinidade elemental.
- **Regras de Cooldown**: Forma Selvagem 60 s duração / 180 s recarga; habilidades de terreno 45–90 s.
- **Itens Focais**: Cajado druidico, sementes/totens por círculo.
- **Progressão por Tiers**: novas formas e efeitos ambientais a cada tier; imunidades parciais somente no tier 4.
- **Sinergia com Raças**: Elfos da Floresta recebem bônus de duração, mas invisibilidade não acumula (usar multiplicador máximo 1,25×).

#### Magias por Círculo
- **Círculo da Terra**
  1. **Pilares de Pedra** – item `LandStoneStaff`; ergue pilares que empurram inimigos 3 blocos e causam 120 % dano. Cooldown 60 s.
  2. **Aura do Bosque** – item `LandEmeraldCharm`; concede +20 % regeneração e resistência veneno 50 % por 12 s. Cooldown 90 s.
  3. **Solo Entrópico** – item `LandTotem`; transforma o chão em lama retardando 40 % por 8 s. Cooldown 75 s.
  4. **Chicote de Vinhas** – item `LandVineWhip`; puxa alvo em 6 blocos e aplica sangramento 5 %/s por 6 s. Cooldown 55 s.
  5. **Coração do Mundo (Ultimate)** – item `LandPrimalSeed`; fortalece aliados a 10 blocos com +25 % resistência e cura 4 %/s por 15 s. Cooldown 240 s.
- **Círculo da Lua**
  1. **Forma Ursina Primordial** – item `MoonTotem`; transforma em urso com +30 % vida e golpe em área por 15 s. Cooldown 120 s.
  2. **Forma Felina** – item `MoonClawRing`; concede +0,05 velocidade e ataques críticos garantidos em alvos isolados por 10 s. Cooldown 75 s.
  3. **Rugido Lunar** – item `MoonPendant`; causa medo em 6 blocos por 3 s e reduz dano inimigo em 15 % por 8 s. Cooldown 90 s.
  4. **Reversão Natural** – item `MoonRebirthStone`; cancela controle negativo de aliados e cura 12 %. Cooldown 105 s.
  5. **Avatar da Lua (Ultimate)** – item `MoonCelestialOrb`; assume forma híbrida com acesso simultâneo às habilidades anteriores por 18 s. Cooldown 240 s.
- **Círculo do Pastor**
  1. **Chamado do Lobo Espiritual** – item `ShepherdTotem`; invoca espírito aliado que causa 80 % dano/2 s por 12 s. Cooldown 60 s.
  2. **Escudo Ancestral** – item `ShepherdDrum`; concede escudo de 200 pontos dividido entre aliados próximos. Cooldown 90 s.
  3. **Vínculo Harmonioso** – item `ShepherdCharm`; aumenta em 15 % cura recebida por espíritos invocados por 10 s. Cooldown 75 s.
  4. **Chuva Revigorante** – item `ShepherdFlute`; cura 4 %/s e remove queimaduras por 8 s. Cooldown 105 s.
  5. **Conclave Primal (Ultimate)** – item `ShepherdCircleStone`; convoca 3 espíritos (urso, águia, cervo) com efeitos combinados por 16 s. Cooldown 240 s.

### Guerreiro (Fighter)
- **Função no Combate**: dano sustentado/tanque secundário.
- **Habilidades Ativas**: Action Surge (reset de cooldowns menores), Second Wind (cura própria), manobras.
- **Habilidades Passivas**: proficiência em armas/armaduras, bônus leve de estabilidade.
- **Regras de Cooldown**: Action Surge 120 s; Second Wind 180 s; manobras 30–45 s.
- **Itens Focais**: grimório de manobras, medalhas de arquétipo (Champion/Battlemaster/Eldritch Knight).
- **Progressão por Tiers**: +1 uso de Action Surge no tier 4; desbloqueio de manobras especiais no tier 3.
- **Sinergia com Raças**: meio-orcs podem ampliar dano de baixo HP; respeitar cooldown global de Relentless.

#### Manobras por Arquétipo
- **Campeão**
  1. **Golpe Superior** – item `ChampionGreatsword`; ataque de 160 % dano com +25 % chance de crítico. Cooldown 45 s.
  2. **Resiliência Inabalável** – item `ChampionShield`; concede 20 % mitigação e cura 6 % por 8 s. Cooldown 75 s.
  3. **Desafio do Gladiador** – item `ChampionStandard`; força inimigo a focar o guerreiro por 6 s. Cooldown 90 s.
  4. **Salto do Coliseu** – item `ChampionBoots`; avanço de 6 blocos com imobilização de 2 s. Cooldown 60 s.
  5. **Triunfo Inesquecível (Ultimate)** – item `ChampionCrown`; garante +40 % chance de crítico e +25 % velocidade de ataque por 15 s. Cooldown 210 s.
- **Mestre de Batalha**
  1. **Golpe Desarmante** – item `BattlemasterWhip`; remove arma temporariamente (ataques -40 % dano) por 6 s. Cooldown 45 s.
  2. **Manobra Precisa** – item `BattlemasterManual`; dá ao próximo ataque +35 % penetração. Cooldown 40 s.
  3. **Postura Defensiva** – item `BattlemasterShield`; reduz dano recebido em 30 % por 10 s. Cooldown 75 s.
  4. **Comando Tático** – item `BattlemasterBanner`; reduz cooldown aliado em 15 % e concede +0,02 velocidade por 8 s. Cooldown 90 s.
  5. **Giro da Vitória (Ultimate)** – item `BattlemasterHalberd`; ataque circular 5 blocos, 200 % dano e aplica vulnerabilidade 15 % por 10 s. Cooldown 210 s.
- **Cavaleiro Élfico**
  1. **Lâmina Arcana** – item `EldritchKnightBlade`; golpe com +120 % dano elemental à escolha. Custo: 20 mana, cooldown 45 s.
  2. **Escudo Arcano** – item `EldritchKnightFocus`; absorve 150 dano mágico por 8 s. Cooldown 60 s.
  3. **Teleporte Tático** – item `EldritchKnightRune`; teleporta 8 blocos e causa explosão 80 % dano. Cooldown 75 s.
  4. **Baluarte de Energia** – item `EldritchKnightSigil`; reduz dano elemental aliado em 25 % por 10 s. Cooldown 90 s.
  5. **Tempestade da Lâmina (Ultimate)** – item `EldritchKnightCodex`; libera combo de 5 cortes (70 % dano cada) com projéteis mágicos. Cooldown 210 s.

### Monge
- **Função no Combate**: mobilidade e controle leve.
- **Habilidades Ativas**: Rajada de Golpes, Passo do Vento, Mãos Curativas.
- **Habilidades Passivas**: Defesa sem armadura, redução de dano projétil.
- **Regras de Cooldown**: utilizar recurso Ki (pool inicial 4, recarga 1 a cada 30 s fora de combate); habilidades custam 1–2 Ki.
- **Itens Focais**: braceletes de ki, pergaminhos de disciplina.
- **Progressão por Tiers**: aumentar pool de Ki (+2 por tier) e adicionar resistências temporárias.
- **Sinergia com Raças**: halflings ganham bônus de esquiva, mas velocidade máxima segue limite de +0,02.

#### Técnicas por Tradição
- **Caminho da Mão Aberta**
  1. **Palmada Desestabilizadora** – item `OpenHandGloves`; causa 130 % dano e empurra 4 blocos. Custo: 1 Ki, cooldown 30 s.
  2. **Onda Interior** – item `OpenHandFocus`; libera cone que reduz dano inimigo em 15 % por 8 s. Custo: 1 Ki, cooldown 45 s.
  3. **Ritmo Harmonioso** – item `OpenHandBeads`; gera +2 Ki e cura 6 % ao longo de 6 s. Cooldown 75 s.
  4. **Caminho do Zéfiro** – item `OpenHandSandals`; concede +0,05 velocidade e passo no ar por 4 s. Custo: 1 Ki, cooldown 60 s.
  5. **Sopro Tranquilo (Ultimate)** – item `OpenHandRelic`; reduz danos recebidos em 40 % para aliados a 6 blocos e cura 5 %/s por 10 s. Cooldown 210 s.
- **Caminho da Sombra**
  1. **Passo das Sombras** – item `ShadowCloak`; teleporta entre sombras a 12 blocos. Custo: 1 Ki, cooldown 35 s.
  2. **Cutilada Sombria** – item `ShadowBlade`; ataque 150 % dano que aplica cegueira 3 s. Custo: 1 Ki, cooldown 40 s.
  3. **Névoa Silenciosa** – item `ShadowSmokeBomb`; cria névoa que reduz visão inimiga e dá invisibilidade 4 s ao monge. Cooldown 60 s.
  4. **Correntes Umbráticas** – item `ShadowChains`; imobiliza alvo por 3 s e reduz mitigação 10 % por 6 s. Custo: 2 Ki, cooldown 75 s.
  5. **Domínio da Penumbra (Ultimate)** – item `ShadowObsidianOrb`; conjura sombra viva por 12 s atacando em sincronia (70 % dano adicional). Cooldown 210 s.
- **Caminho dos Quatro Elementos**
  1. **Rajada Flamejante** – item `ElementsFireFan`; projeta linha de fogo 130 % dano. Custo: 1 Ki, cooldown 40 s.
  2. **Muralha Aquática** – item `ElementsWaterVial`; ergue parede que reduz projéteis em 60 % por 8 s. Custo: 1 Ki, cooldown 60 s.
  3. **Salto do Vendaval** – item `ElementsAirCharm`; impulsiona 10 blocos e concede queda lenta por 6 s. Custo: 1 Ki, cooldown 50 s.
  4. **Punho Terrano** – item `ElementsEarthGauntlet`; causa 160 % dano e atordoa 2 s. Custo: 2 Ki, cooldown 75 s.
  5. **Avatar Elemental (Ultimate)** – item `ElementsPrism`; conjura aura por 15 s alternando elementos (+20 % dano, +20 % mitigação, cura 4 %/s). Cooldown 240 s.

### Paladino
- **Função no Combate**: suporte/tanque híbrido.
- **Habilidades Ativas**: Golpe Divino (burst), Imposição de Mãos (cura alvo), Canalizar Voto (aura situacional).
- **Habilidades Passivas**: aura de proteção, resistência a medo.
- **Regras de Cooldown**: Golpe 45 s; Imposição com charges (5 usos/rest) e cooldown global 30 s; aura dura 30 s com recarga 90 s.
- **Itens Focais**: espada sagrada, ícone de ordem por juramento.
- **Progressão por Tiers**: liberar novos juramentos nos tiers 2 e 3; bônus de aura ampliado no tier 4.
- **Sinergia com Raças**: humanos recebem +1 carga de Imposição; anões ganham resistência extra situacional respeitando limites.

#### Magias por Juramento
- **Juramento da Devoção**
  1. **Golpe Sagrado** – item `DevotionBlade`; adiciona 100 % dano radiante ao próximo ataque. Custo: 1 carga de Fé, cooldown 45 s.
  2. **Aura de Pureza** – item `DevotionRelic`; remove doença/enfeitiçamento de aliados em 6 blocos e concede resistência 20 % por 10 s. Cooldown 75 s.
  3. **Luz Orientadora** – item `DevotionLantern`; revela inimigos furtivos e concede vantagem (+10 % crit) por 8 s. Cooldown 90 s.
  4. **Escudo Devoto** – item `DevotionShield`; reflete 25 % do dano recebido por 6 s. Cooldown 105 s.
  5. **Milagre da Fé (Ultimate)** – item `DevotionCodex`; cura 20 %/s por 6 s em aliados próximos e garante imunidade a medo/charme. Cooldown 240 s.
- **Juramento da Vingança**
  1. **Juramento de Inimizade** – item `VengeanceSigil`; marca alvo aumentando dano causado em 20 % por 12 s. Cooldown 60 s.
  2. **Passo do Redentor** – item `VengeanceBoots`; avança até 12 blocos com imunidade a controle durante a investida. Cooldown 75 s.
  3. **Cadeia do Pecado** – item `VengeanceChain`; puxa alvo 4 blocos e aplica lentidão 40 % por 6 s. Cooldown 80 s.
  4. **Fúria Celestial** – item `VengeanceBlade`; concede +30 % velocidade de ataque por 10 s. Cooldown 90 s.
  5. **Execução Divina (Ultimate)** – item `VengeanceRelic`; golpe vertical de 250 % dano radiante que finaliza inimigos abaixo de 20 % de vida. Cooldown 240 s.
- **Juramento dos Anciões**
  1. **Vinha Restritiva** – item `AncientsSpear`; enraiza inimigo por 3 s e reduz dano em 15 %. Cooldown 60 s.
  2. **Aura da Natureza** – item `AncientsCrown`; concede +15 % resistência elemental a aliados por 12 s. Cooldown 90 s.
  3. **Luz Radiante** – item `AncientsLantern`; cega inimigos e concede regeneração 5 %/s por 5 s. Cooldown 75 s.
  4. **Asas de Safira** – item `AncientsMantle`; permite planar e concede +0,05 velocidade por 8 s. Cooldown 105 s.
  5. **Harmonia Primordial (Ultimate)** – item `AncientsTotem`; cria círculo de 8 blocos com +25 % cura recebida e -20 % dano sofrido por 15 s. Cooldown 240 s.

### Patrulheiro (Ranger)
- **Função no Combate**: dano consistente/controle de terreno.
- **Habilidades Ativas**: Marca do Caçador, Companheiro Animal, armadilhas.
- **Habilidades Passivas**: senso de terreno preferido, bônus contra inimigos marcados.
- **Regras de Cooldown**: Marca 60 s; armadilhas 90 s; invocação/ordem do companheiro 30 s.
- **Itens Focais**: arco personalizado, kit de armadilhas, apito de companheiro.
- **Progressão por Tiers**: novos tipos de armadilha e melhorias ao companheiro a cada tier.
- **Sinergia com Raças**: elfos e meio-elfos obtêm bônus de alcance, mas danos seguem limites da tabela de balanceamento.

#### Magias e Técnicas por Conclave
- **Caçador**
  1. **Rajada Predatória** – item `HunterBow`; dispara 3 flechas (80 % dano cada). Cooldown 45 s.
  2. **Rede Enredante** – item `HunterNet`; imobiliza alvo 4 s e aplica vulnerabilidade 12 %. Cooldown 60 s.
  3. **Olho do Predador** – item `HunterScope`; revela pontos fracos concedendo +15 % crit por 10 s. Cooldown 90 s.
  4. **Passo Silvestre** – item `HunterBoots`; concede camuflagem e +0,04 velocidade por 8 s. Cooldown 75 s.
  5. **Chuva de Flechas (Ultimate)** – item `HunterQuiver`; cria zona de 7 blocos com 70 % dano/0,75 s por 8 s. Cooldown 210 s.
- **Mestre das Feras**
  1. **Chamado Alpha** – item `BeastWhistle`; convoca companheiro com +20 % dano por 12 s. Cooldown 60 s.
  2. **Sincronia Selvagem** – item `BeastCharm`; faz companheiro replicar próximo ataque (100 % dano extra). Cooldown 45 s.
  3. **Investida Coordenada** – item `BeastHarness`; ambos avançam 5 blocos causando 120 % dano cada. Cooldown 75 s.
  4. **Proteção Instintiva** – item `BeastShield`; divide dano recebido (50 % cada) por 10 s. Cooldown 90 s.
  5. **Fúria Primal (Ultimate)** – item `BeastTotem`; transforma companheiro em forma alfa com +35 % dano e ataques em área por 15 s. Cooldown 210 s.
- **Espreitador Noturno**
  1. **Sombras Caçadoras** – item `GloomDagger`; lança lâmina que aplica medo 2 s e marca alvo. Cooldown 45 s.
  2. **Salto Umbrático** – item `GloomCloak`; teleporta para trás do alvo e concede invisibilidade 3 s. Cooldown 60 s.
  3. **Manto Penumbra** – item `GloomHood`; reduz detecção e concede +20 % dano ao atacar de emboscada por 10 s. Cooldown 90 s.
  4. **Disparo Envenenado** – item `GloomBow`; flecha que aplica 6 % dano/seg por 6 s. Cooldown 75 s.
  5. **Caça Sombria (Ultimate)** – item `GloomRelic`; cria zona escura que concede invisibilidade a aliados e aplica 80 % dano/2 s em inimigos por 12 s. Cooldown 210 s.

### Ladino (Rogue)
- **Função no Combate**: burst e utilidade.
- **Habilidades Ativas**: Ataque Furtivo, Passo das Sombras, Ferramentas de Ladrão.
- **Habilidades Passivas**: Evasão, Expertise (bônus em interações específicas).
- **Regras de Cooldown**: Ataque Furtivo 6 s entre execuções completas; Passo das Sombras 45 s.
- **Itens Focais**: adaga/armas leves, kit de ferramentas.
- **Progressão por Tiers**: multiplicador de dano furtivo aumenta a cada tier (1× → 2× → 3× → 4×), mantendo limite de 50 % da vida do alvo em PvP.
- **Sinergia com Raças**: halflings pés-leves ganham vantagem em furtividade, sem ultrapassar limites de velocidade.

#### Técnicas por Arquétipo
- **Ladrão**
  1. **Roubo Rápido** – item `ThiefGloves`; rouba 1 buff ou item consumível do alvo. Cooldown 45 s.
  2. **Acrobacia Felina** – item `ThiefGrapple`; desloca-se 8 blocos ignorando quedas. Cooldown 35 s.
  3. **Lâmina Precisa** – item `ThiefDagger`; ataque 140 % dano + sangramento 4 %/s por 6 s. Cooldown 50 s.
  4. **Fumaça Ocultante** – item `ThiefSmokeBomb`; concede invisibilidade 4 s e desmarca o ladino. Cooldown 60 s.
  5. **Golpe Fantasma (Ultimate)** – item `ThiefShadowBlade`; aplica 3 ataques consecutivos (90 % dano cada) ignorando armadura. Cooldown 210 s.
- **Assassino**
  1. **Preparar Veneno** – item `AssassinVial`; aplica 20 % dano extra no próximo ataque. Cooldown 45 s.
  2. **Emboscada Letal** – item `AssassinCloak`; teleporta atrás do alvo e garante crítico (200 % dano). Cooldown 75 s.
  3. **Golpe Silencioso** – item `AssassinKnife`; reduz cura recebida pelo alvo em 40 % por 8 s. Cooldown 60 s.
  4. **Desaparecer** – item `AssassinSmoke`; remove todos os debuffs e concede invisibilidade 5 s. Cooldown 90 s.
  5. **Execução Sombria (Ultimate)** – item `AssassinRelic`; se o alvo estiver abaixo de 30 % de vida, causa 280 % dano verdadeiro. Cooldown 240 s.
- **Trapaceiro Arcano**
  1. **Dardo Fantasmagórico** – item `ArcaneTricksterWand`; projétil de 120 % dano arcano que marca o alvo. Cooldown 45 s.
  2. **Ilusão Espelhada** – item `ArcaneTricksterMirror`; cria duplicata que absorve 1 golpe e engana inimigos. Cooldown 60 s.
  3. **Truque Explosivo** – item `ArcaneTricksterCard`; bomba arcana 140 % dano em 4 blocos. Cooldown 75 s.
  4. **Passo Dimensionado** – item `ArcaneTricksterSigil`; teleporta 6 blocos e reduz cooldown do Ataque Furtivo em 3 s. Cooldown 90 s.
  5. **Tempestade Ilusória (Ultimate)** – item `ArcaneTricksterTome`; invoca chuva de lâminas e raios (60 % dano/seg) por 10 s enquanto concede +15 % esquiva. Cooldown 240 s.

### Feiticeiro (Sorcerer)
- **Função no Combate**: dano explosivo.
- **Habilidades Ativas**: feitiços de dano em área, metamagia (duplicar alcance, rapidez).
- **Habilidades Passivas**: recuperação gradual de pontos de feitiçaria.
- **Regras de Cooldown**: habilidades de metamagia consomem pontos (pool inicial 3, +1 por tier) com recarga de 30 s entre usos.
- **Itens Focais**: foco arcano (cristal/varinha), tomo de metamagia.
- **Progressão por Tiers**: desbloquear novos tipos de metamagia e aumentar dano base em 5 % por tier.
- **Sinergia com Raças**: draconatos recebem bônus elementais alinhados ao subtipo, limitados pela exaustão de resistência do balanceamento.

#### Magias por Origem
- **Origem Dracônica**
  1. **Sopro Dracônico** – item `DraconicFocus`; cone de 6 blocos com 160 % dano elemental. Custo: 20 mana, cooldown 45 s.
  2. **Escamas Manifestas** – item `DraconicScaleCharm`; concede +20 % mitigação e 15 % resistência ao elemento por 10 s. Cooldown 75 s.
  3. **Asas Etéreas** – item `DraconicWingTalisman`; permite voo limitado (planar) e +0,05 velocidade por 8 s. Cooldown 90 s.
  4. **Coração Elemental** – item `DraconicHeartGem`; aumenta dano elemental em 20 % por 12 s. Cooldown 105 s.
  5. **Avatar Dracônico (Ultimate)** – item `DraconicCrown`; transforma o feiticeiro, causando 80 % dano/seg em área e concedendo imunidade a empurrões por 15 s. Cooldown 240 s.
- **Magia Selvagem**
  1. **Surto Caótico** – item `WildMagicOrb`; projétil aleatório (fogo, frio, relâmpago) 150 % dano. Cooldown 45 s.
  2. **Fluxo Instável** – item `WildMagicRod`; reduz cooldown de todas habilidades em 20 % mas aplica chance de retrocesso (auto-dano 5 %). Dura 10 s. Cooldown 90 s.
  3. **Reversão Quântica** – item `WildMagicHourglass`; rerola debuffs (50 % remover, 50 % converter em buff leve). Cooldown 75 s.
  4. **Véu Probabilístico** – item `WildMagicCape`; concede 30 % chance de ignorar dano por 8 s. Cooldown 105 s.
  5. **Tempestade Caótica (Ultimate)** – item `WildMagicNexus`; libera área de 8 blocos com efeitos aleatórios (raios, cura, puxão) causando 70 % dano/seg por 12 s. Cooldown 240 s.
- **Magia da Tempestade**
  1. **Descarga Tempestuosa** – item `StormSorceryFocus`; raio em linha 140 % dano elétrico e enraiza 2 s. Cooldown 45 s.
  2. **Pulso Eólico** – item `StormSorceryCharm`; empurra inimigos 5 blocos e concede voo rápido por 4 s. Cooldown 60 s.
  3. **Nuvem de Trovões** – item `StormSorceryOrb`; cria nuvem 6 blocos causando 60 % dano/seg por 8 s. Cooldown 90 s.
  4. **Condutor Vivo** – item `StormSorceryStaff`; transforma dano elétrico recebido em mana (20 por acerto) por 10 s. Cooldown 105 s.
  5. **Olho da Tempestade (Ultimate)** – item `StormSorceryCrown`; invoca tempestade global com raios direcionáveis (100 % dano cada) por 12 s, aliado recebe +20 % velocidade. Cooldown 240 s.

### Mago (Wizard)
- **Função no Combate**: controle e utilidade.
- **Habilidades Ativas**: feitiços de controle (paredes, prisões), teleportes curtos.
- **Habilidades Passivas**: preparação flexível de feitiços, bônus em utilidades.
- **Regras de Cooldown**: habilidades de controle forte ≥90 s; teleporte curto 45 s; preparação diária via menu.
- **Itens Focais**: grimório (livro), cajado arcano.
- **Progressão por Tiers**: slots extras e acesso a magias superiores no tier 3/4; especializações (Escola de Magia) concedem efeito único.
- **Sinergia com Raças**: alto elfo recebe bônus de preparação adicional (1 spell) respeitando limites de retaliação.

#### Magias por Escola
- **Escola de Evocação**
  1. **Orbe Flamejante** – item `EvocationStaff`; projétil 160 % dano fogo e deixa campo ardente por 6 s. Cooldown 45 s.
  2. **Nova Arcana** – item `EvocationFocus`; explosão de 6 blocos causando 140 % dano. Cooldown 75 s.
  3. **Lança Etérea** – item `EvocationSpear`; perfura 3 alvos em linha 150 % dano. Cooldown 60 s.
  4. **Catalisador de Poder** – item `EvocationCrystal`; aumenta dano de feitiços em 18 % por 12 s. Cooldown 105 s.
  5. **Cataclismo Controlado (Ultimate)** – item `EvocationGrimoire`; convoca meteoros sequenciais (5 impactos de 120 % dano) em área. Cooldown 240 s.
- **Escola de Abjuração**
  1. **Barreira Protetora** – item `AbjurationShield`; cria escudo de 200 pontos por 10 s. Cooldown 45 s.
  2. **Contra-Feitiço** – item `AbjurationTalisman`; cancela habilidade inimiga (até 3 s após ativação) e aplica silêncio 2 s. Cooldown 75 s.
  3. **Selo Rúnico** – item `AbjurationRune`; reduz dano elemental em 25 % para aliados a 6 blocos por 12 s. Cooldown 90 s.
  4. **Reflexo Prismático** – item `AbjurationMirror`; devolve 30 % do dano mágico recebido por 8 s. Cooldown 105 s.
  5. **Fortaleza Arcana (Ultimate)** – item `AbjurationCodex`; cria domo invulnerável de 6 blocos por 8 s e regenera 5 %/s. Cooldown 240 s.
- **Escola de Ilusão**
  1. **Imagem Múltipla** – item `IllusionDeck`; convoca 3 cópias que confundem inimigos por 6 s. Cooldown 45 s.
  2. **Véu Invisível** – item `IllusionCloak`; concede invisibilidade 8 s e +20 % velocidade. Cooldown 75 s.
  3. **Labirinto Mental** – item `IllusionSphere`; prende inimigo em labirinto (imobilização 4 s). Cooldown 90 s.
  4. **Fantasmagoria** – item `IllusionLantern`; causa medo e reduz precisão 15 % por 10 s. Cooldown 105 s.
  5. **Mundo Espelhado (Ultimate)** – item `IllusionGrimoire`; replica todos os aliados em ilusões que absorvem 1 golpe e desorientam inimigos por 12 s. Cooldown 240 s.

## Checklist de Implementação
1. Criar grupos LuckPerms `class_<nome>` e subgrupos por arquétipo.
2. Definir itens MythicMobs/Skript conforme nomeação padronizada (`ClassNameCoreItem`).
3. Implementar habilidades em `plugins/RacesEffects/config.yml` ou ProSkillAPI utilizando as métricas de balanceamento.
4. Validar sinergias com raças usando as restrições do arquivo de balanceamento (resistências, velocidade, passivos).
5. Documentar comandos e recargas na wiki/GUI in-game antes do lançamento.

## Manutenção e Testes
- Executar testes de carga com grupos mistos (PvE e PvP) em cada tier.
- Registrar feedback e ajustar atributos seguindo os limites definidos.
- Atualizar este guia após cada patch significativo para manter consistência documental.

