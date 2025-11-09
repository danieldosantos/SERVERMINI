Itens de Classe (/classitem)

Este servidor usa livros de classe para aplicar classes e subclasses (LuckPerms) ao jogador. Os livros são dados pelo comando abaixo e, ao clicar, aplicam a classe, ajustam grupos, e disparam a entrega de itens via Skript.

- Script: `plugins/Skript/scripts/class_items_books.sk:1`
- Recarregar: `/sk reload class_items_books` (ou `/sk reload scripts`)

Comando

- Dar um livro: `/classitem give <jogador> <tipo>`
- Clique com o livro na mão para aplicar a classe/subclasse.

Tipos aceitos (inglês e PT-ASCII)

- Classes base
  - barbarian | barbaro
  - bard | bardo
  - warlock | bruxo
  - cleric | clerigo
  - druid | druida
  - fighter | guerreiro
  - monk | monge
  - paladin | paladino
  - ranger | patrulheiro
  - rogue | ladino
  - sorcerer | feiticeiro
  - wizard | mago

- Bárbaro — caminhos
  - berserker
  - totem_bear | urso
  - totem_eagle | aguia
  - totem_wolf | lobo

- Bardo — colégios
  - lore | saber
  - valor

- Bruxo — patronos
  - archfey | arquifada
  - fiend | diabolico
  - oldone | ancestral

- Clérigo — domínios
  - life | vida
  - light | luz
  - war | guerra

- Druida — círculos
  - land | terra
  - moon | lua
  - spores | esporos

- Guerreiro — arquétipos
  - champion | campeao
  - battlemaster | mestrearmas
  - eldritch | arcano

- Monge — trilhas
  - openhand | maosabertas
  - shadow | sombra
  - elements | elementos

- Paladino — juramentos
  - devotion | devocao
  - ancients | ancioes
  - vengeance | vinganca

- Patrulheiro — conclaves
  - hunter | cacador
  - beast | bestas
  - gloom | trevas

- Ladino — arquétipos
  - thief | ladrao
  - assassin | assassino
  - arcane | arcano

- Feiticeiro — linhagens
  - draconic | draconica
  - wild | selvagem
  - storm | tempestade

- Mago — escolas
  - evocation | evocacao
  - abjuration | abjuracao
  - divination | adivinhacao

Como funciona

- O comando entrega um livro nomeado; ao clicar:
  - Remove todos os grupos de classe `class_*` conhecidos (não afeta raças).
  - Adiciona o grupo da classe base e, se for o caso, o da subclasse (ex.: `class_barbarian` + `class_barbarian_berserker`).
  - Chama `giveClassItems(player)` (definido em `plugins/Skript/scripts/class_items_new_fixed.sk`) para entregar itens da classe (atualmente implementado para Bárbaro, Bardo e Bruxo).
  - Consome o livro e toca um som de feedback.

Exemplos

- Classe base: `/classitem give SeuNick paladin`
- Subclasse: `/classitem give SeuNick vengeance` (Paladino — Vingança)
- Patrono: `/classitem give SeuNick fiend` (Bruxo — Diabólico)
- Caminho: `/classitem give SeuNick totem_bear` (Bárbaro — Totem do Urso)

Requisitos e dicas

- Precisa do Skript carregado sem erros. Use `/sk reload class_items_books` e confira o console.
- O console/OP pode executar o comando. Jogadores comuns não (permite apenas OP).
- Tenha 1 espaço livre no inventário para receber o livro (o Skript tenta colocar direto no inventário do alvo; se não couber, pode cair no chão dependendo da versão do Skript).
- A entrega de itens após aplicar classe depende de `giveClassItems(player)` e dos itens do MythicMobs. Hoje, itens estão mapeados para Bárbaro/Bardo/Bruxo; para as demais classes, os grupos e efeitos aplicam, mas sem itens até serem definidos.

Solução de problemas

- “Unknown or incomplete command”: recarregue o Skript (`/sk reload class_items_books`) e veja erros de sintaxe.
- “Não recebi o livro”: verifique espaço no inventário e mensagens no console. Deve aparecer “Livro de classe (X) entregue a Y”.
- Para forçar entrega de itens conforme grupos atuais (sem livro): `/forcegiveitems <jogador>` (script `class_items_tools.sk`).

Arquivos relacionados

- `plugins/Skript/scripts/class_items_books.sk`: comandos e livros de classe.
- `plugins/Skript/scripts/class_items_new_fixed.sk`: entrega automática de itens por classe/raça.
- `plugins/Skript/scripts/class_items_tools.sk`: utilitários (`/forcegiveitems`, `/checkitemsdebug`).
- `plugins/RacesEffects/config.yml`: mapeamento de classes, grupos e efeitos.

