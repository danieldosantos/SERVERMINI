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

### Bardo
- **Função no Combate**: suporte híbrido.
- **Habilidades Ativas**: Inspiração (`/inspire`), Canção de Descanso (`/song`).
- **Habilidades Passivas**: bônus situacional a habilidades sociais (interações NPC) e recarga reduzida de instrumentos.
- **Regras de Cooldown**: Inspiração 60 s; Canção 60 s recarga / 30 s duração.
- **Itens Focais**: `BardLute`, `BardLoreBook`, medalhões de subclasse.
- **Progressão por Tiers**: ampliar número de cargas de Inspiração (até 3 simultâneas) e raio da canção no tier 3.
- **Sinergia com Raças**: combos com Meio-Elfo concedem bônus sociais adicionais mas não aumentam dano; manter limites de buff do balanceamento.

### Bruxo (Warlock)
- **Função no Combate**: dano/controlador à distância.
- **Habilidades Ativas**: Hex (`/hex`), Rajada Mística (`/eldritch`), habilidades de patrono.
- **Habilidades Passivas**: pactos fornecem bônus de mobilidade/resistência moderada.
- **Regras de Cooldown**: Hex 45 s por alvo; rajada com janela de 3 projéteis por 10 s e recarga de 5 s.
- **Itens Focais**: `EldritchWand`, `HexTalisman`, marcas de patrono.
- **Progressão por Tiers**: slots de invocação extras a cada tier; desbloqueio de invocações maiores no tier 3.
- **Sinergia com Raças**: restringir acumulação com Tiefling (foco em fogo) limitando bônus de resistência conforme diretriz.

### Clérigo
- **Função no Combate**: suporte/curador.
- **Habilidades Ativas**: Canalizar Divindade (cura em área, banimento), Palavra Curativa (cura pontual), proteções.
- **Habilidades Passivas**: aura de resistência leve (+10 % mitigação em aliados próximos).
- **Regras de Cooldown**: Canalizar 120 s; Palavra Curativa 30 s por alvo; aura permanente com uptime ≤60 % (ativação manual).
- **Itens Focais**: Símbolo sagrado (cajado ou totem), livro de oração por domínio.
- **Progressão por Tiers**: desbloqueio de um segundo uso de Canalizar no tier 3; domínios concedem habilidade única no tier 2.
- **Sinergia com Raças**: humanos e anões recebem bônus temáticos (cura adicional 5 %), mantendo limites de cura do balanceamento.

### Druida
- **Função no Combate**: controle e flexibilidade.
- **Habilidades Ativas**: Forma Selvagem (transformações), conjurações de terreno (raízes, neblina).
- **Habilidades Passivas**: regeneração leve em biomas naturais, afinidade elemental.
- **Regras de Cooldown**: Forma Selvagem 60 s duração / 180 s recarga; habilidades de terreno 45–90 s.
- **Itens Focais**: Cajado druidico, sementes/totens por círculo.
- **Progressão por Tiers**: novas formas e efeitos ambientais a cada tier; imunidades parciais somente no tier 4.
- **Sinergia com Raças**: Elfos da Floresta recebem bônus de duração, mas invisibilidade não acumula (usar multiplicador máximo 1,25×).

### Guerreiro (Fighter)
- **Função no Combate**: dano sustentado/tanque secundário.
- **Habilidades Ativas**: Action Surge (reset de cooldowns menores), Second Wind (cura própria), manobras.
- **Habilidades Passivas**: proficiência em armas/armaduras, bônus leve de estabilidade.
- **Regras de Cooldown**: Action Surge 120 s; Second Wind 180 s; manobras 30–45 s.
- **Itens Focais**: grimório de manobras, medalhas de arquétipo (Champion/Battlemaster/Eldritch Knight).
- **Progressão por Tiers**: +1 uso de Action Surge no tier 4; desbloqueio de manobras especiais no tier 3.
- **Sinergia com Raças**: meio-orcs podem ampliar dano de baixo HP; respeitar cooldown global de Relentless.

### Monge
- **Função no Combate**: mobilidade e controle leve.
- **Habilidades Ativas**: Rajada de Golpes, Passo do Vento, Mãos Curativas.
- **Habilidades Passivas**: Defesa sem armadura, redução de dano projétil.
- **Regras de Cooldown**: utilizar recurso Ki (pool inicial 4, recarga 1 a cada 30 s fora de combate); habilidades custam 1–2 Ki.
- **Itens Focais**: braceletes de ki, pergaminhos de disciplina.
- **Progressão por Tiers**: aumentar pool de Ki (+2 por tier) e adicionar resistências temporárias.
- **Sinergia com Raças**: halflings ganham bônus de esquiva, mas velocidade máxima segue limite de +0,02.

### Paladino
- **Função no Combate**: suporte/tanque híbrido.
- **Habilidades Ativas**: Golpe Divino (burst), Imposição de Mãos (cura alvo), Canalizar Voto (aura situacional).
- **Habilidades Passivas**: aura de proteção, resistência a medo.
- **Regras de Cooldown**: Golpe 45 s; Imposição com charges (5 usos/rest) e cooldown global 30 s; aura dura 30 s com recarga 90 s.
- **Itens Focais**: espada sagrada, ícone de ordem por juramento.
- **Progressão por Tiers**: liberar novos juramentos nos tiers 2 e 3; bônus de aura ampliado no tier 4.
- **Sinergia com Raças**: humanos recebem +1 carga de Imposição; anões ganham resistência extra situacional respeitando limites.

### Patrulheiro (Ranger)
- **Função no Combate**: dano consistente/controle de terreno.
- **Habilidades Ativas**: Marca do Caçador, Companheiro Animal, armadilhas.
- **Habilidades Passivas**: senso de terreno preferido, bônus contra inimigos marcados.
- **Regras de Cooldown**: Marca 60 s; armadilhas 90 s; invocação/ordem do companheiro 30 s.
- **Itens Focais**: arco personalizado, kit de armadilhas, apito de companheiro.
- **Progressão por Tiers**: novos tipos de armadilha e melhorias ao companheiro a cada tier.
- **Sinergia com Raças**: elfos e meio-elfos obtêm bônus de alcance, mas danos seguem limites da tabela de balanceamento.

### Ladino (Rogue)
- **Função no Combate**: burst e utilidade.
- **Habilidades Ativas**: Ataque Furtivo, Passo das Sombras, Ferramentas de Ladrão.
- **Habilidades Passivas**: Evasão, Expertise (bônus em interações específicas).
- **Regras de Cooldown**: Ataque Furtivo 6 s entre execuções completas; Passo das Sombras 45 s.
- **Itens Focais**: adaga/armas leves, kit de ferramentas.
- **Progressão por Tiers**: multiplicador de dano furtivo aumenta a cada tier (1× → 2× → 3× → 4×), mantendo limite de 50 % da vida do alvo em PvP.
- **Sinergia com Raças**: halflings pés-leves ganham vantagem em furtividade, sem ultrapassar limites de velocidade.

### Feiticeiro (Sorcerer)
- **Função no Combate**: dano explosivo.
- **Habilidades Ativas**: feitiços de dano em área, metamagia (duplicar alcance, rapidez).
- **Habilidades Passivas**: recuperação gradual de pontos de feitiçaria.
- **Regras de Cooldown**: habilidades de metamagia consomem pontos (pool inicial 3, +1 por tier) com recarga de 30 s entre usos.
- **Itens Focais**: foco arcano (cristal/varinha), tomo de metamagia.
- **Progressão por Tiers**: desbloquear novos tipos de metamagia e aumentar dano base em 5 % por tier.
- **Sinergia com Raças**: draconatos recebem bônus elementais alinhados ao subtipo, limitados pela exaustão de resistência do balanceamento.

### Mago (Wizard)
- **Função no Combate**: controle e utilidade.
- **Habilidades Ativas**: feitiços de controle (paredes, prisões), teleportes curtos.
- **Habilidades Passivas**: preparação flexível de feitiços, bônus em utilidades.
- **Regras de Cooldown**: habilidades de controle forte ≥90 s; teleporte curto 45 s; preparação diária via menu.
- **Itens Focais**: grimório (livro), cajado arcano.
- **Progressão por Tiers**: slots extras e acesso a magias superiores no tier 3/4; especializações (Escola de Magia) concedem efeito único.
- **Sinergia com Raças**: alto elfo recebe bônus de preparação adicional (1 spell) respeitando limites de retaliação.

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

