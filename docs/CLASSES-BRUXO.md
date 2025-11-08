# Classe: Bruxo (RacesEffects)

Após editar o YAML: `/raceseffects reload`

## Comandos essenciais
- Livro da classe: `/classitem give <jogador> warlock`
- Aplicar classe via LuckPerms: `lp user <nick> parent add class_warlock`

Comandos in‑game do Bruxo (placeholders via alias)
- Hex: `/hex <jogador>`
  - Fraqueza (Weakness I) por 45s no alvo.
  - Destaque (Glowing) por 5s no alvo.
- Rajada Mística: `/eldritch`
  - Som de conjuração (evoker.cast_spell) e bônus breve conforme patrono (10s):
    - Arquifada: Speed I.
    - Diabo: Strength I.
    - Grande Antigo: Resistance I.
- Atributos: `/abilities`, `/abilities set <STR|DEX|CON|INT|WIS|CHA> <valor>`, `/abilities add ...`, `/abilities reset`

Principais características (PHB‑like)
- Magia de Pacto com foco em Carisma (CHA): distribuição e melhorias automáticas por nível.
- Hex: maldição rápida e de recarga curta (placeholder via alias).
- Rajada Mística: ação ofensiva recorrente (placeholder via alias) com sutis diferenças por Patrono.

Como criar os grupos (LuckPerms)
- Classe (obrigatório)
  - `lp creategroup class_warlock`
  - `lp group class_warlock setdisplayname "Classe: Bruxo"`
  - `lp group class_warlock setweight 50`
- Patronos (escolha um)
  - Arquifada
    - `lp creategroup class_warlock_archfey`
    - `lp group class_warlock_archfey setdisplayname "Patrono: Arquifada"`
  - Diabo
    - `lp creategroup class_warlock_fiend`
    - `lp group class_warlock_fiend setdisplayname "Patrono: Diabo"`
  - Grande Antigo
    - `lp creategroup class_warlock_oldone`
    - `lp group class_warlock_oldone setdisplayname "Patrono: Grande Antigo"`

Como atribuir ao jogador
- Classe: `lp user <nick> parent set class_warlock`
- Patrono (um deles):
  - `lp user <nick> parent add class_warlock_archfey`
  - ou `lp user <nick> parent add class_warlock_fiend`
  - ou `lp user <nick> parent add class_warlock_oldone`
- Verificar: `lp user <nick> info`

Detalhe dos Patronos (valores exatos)
- Arquifada — grupo `class_warlock_archfey`
  - Rajada Mística concede `Speed I` (10s) ao lançar `/eldritch`.
- Diabo — grupo `class_warlock_fiend`
  - Rajada Mística concede `Strength I` (10s) ao lançar `/eldritch`.
- Grande Antigo — grupo `class_warlock_oldone`
  - Rajada Mística concede `Resistance I` (10s) ao lançar `/eldritch`.
- Hex — todos os bruxos (`class_warlock`)
  - Aplica `Weakness I` por 45s e `Glowing` por 5s no alvo de `/hex`.

Ajustes de UI
- `apply.classes.warlock.ui.actionbar: true` para exibir status (quando migrar para o JAR nativo).
- `apply.classes.warlock.ui.showCooldown: true` para exibir recargas.

Onde ajustar valores
- Arquivo ativo: `plugins/RacesEffects/config.yml`
- Mapas de grupos → tags (para condicionar aliases):
  - `apply.groups.class_warlock`, `class_warlock_archfey`, `class_warlock_fiend`, `class_warlock_oldone`
- Classe/Patronos/Abilities (auto‑distribuição e melhorias por nível):
  - `apply.classes.warlock.*`
- Aliases (efeitos/som dos placeholders):
  - `commands.yml` (entradas `eldritch` e `hex`)

Recomendações de atributos (PHB)
- Ordem: `[CHA, CON, DEX, WIS, INT, STR]`
- Array inicial: `[15, 14, 13, 12, 10, 8]`
- Bônus racial sugerido: `CHA` (+3)
- O plugin já aplica automaticamente a distribuição inicial e os `levelUps` em: `apply.classes.warlock.abilities.*`

Permissões (para os aliases funcionarem, sem JAR nativo)
- Conceder ao grupo `class_warlock`:
  - `minecraft.command.execute`, `minecraft.command.effect`, `minecraft.command.playsound`, `minecraft.command.selector`
  - Ex.: `lp group class_warlock permission set minecraft.command.execute true`

Após editar o YAML: `/raceseffects reload` (e reinicie o servidor para recarregar `commands.yml`).
