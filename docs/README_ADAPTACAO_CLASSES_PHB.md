# Guia de Adaptação das Classes do PHB

Este guia descreve um processo padronizado para portar qualquer classe ou subclasse do Player's
Handbook (D&D 5E) para o servidor Paper 1.21.x. Utilize-o em conjunto com
`README_CLASSES_UNIFICADO.md` e `README_BALANCEAMENTO.md`.

## Pipeline Geral
1. **Defina o papel e nível de poder** (tanque, dano, suporte, controle) com base na tabela de metas de
`README_BALANCEAMENTO.md`.
2. **Mapeie recursos**: escolha barras existentes do AuraSkills (`mana`, `fury`, `divinity`, `ki`,
`sorcery_points`, etc.) ou crie uma nova via API. Documente custos e regeneração.
3. **Liste habilidades** dividindo em passivos e ativos. Para cada ativo, determine custo, cooldown,
duração, alvo e escalonamento por nível.
4. **Distribua efeitos pelos plugins**:
   - **RacesEffects** → passivos constantes (atributos, resistências, buffs com uptime ≤60 %).
   - **AuraSkills** → recursos e modificadores numéricos (dano bônus por nível, pontuação adicional).
   - **MythicMobs** → habilidades, projéteis, summons e itens focais. Nomeie as skills conforme usado
     nos Skripts (`Classe_Acao`), para facilitar manutenção.
   - **Skript** → camada de comando/validação. Crie um arquivo `plugins/Skript/scripts/cmd_<classe>.sk`
     seguindo o padrão de `classes_core.sk`: checar grupo LuckPerms, cooldown (AuraSkills), custo e
     executar `mm skills cast`.
   - **LuckPerms** → grupos `class_<classe>` e `class_<classe>_<arquetipo>` para controlar acesso.
5. **Implemente os comandos** replicando o formato do repositório (4 comandos principais por classe:
habilidade chave, utilidade, subclasse e ultimate). Ajuste mensagens com `classes_play_feedback`.
6. **Integre itens focais** criando MythicItems vinculados a grupos. Utilize SkBee para proteger NBT e
skRayFall para efeitos visuais opcionais.
7. **Teste** seguindo o checklist de balanceamento: logs de MythicMobs/Skript, métricas de DPS/HPS e
validação de recursos via `/auraskills player <nick> info`.

## Estrutura Recomendada de Arquivos
```
plugins/
  AuraSkills/
    configs das barras de recurso (regen, limites)
  MythicMobs/
    Skills/
      Classe_Acao.yml
    Items/
      Classe_ItemFocal.yml
  Skript/
    scripts/
      classes_core.sk
      cmd_<classe>.sk
  RacesEffects/
    config.yml (tags apply.classes.*)
```

## Passos Detalhados
### Passivos → RacesEffects
- Adicione blocos em `apply.classes.<classe>` para atributos, resistências ou buffs temporários com
periodicidade controlada (`refreshTicks`).
- Use tags para condicional (biomas, altitude, dimension) e associe-as a grupos LuckPerms.

### Habilidades → AuraSkills + Skript + MythicMobs
1. **AuraSkills**: registre a habilidade em `skills.yml` (se usar sistema interno) ou apenas defina o
recurso utilizado. Configure regeneração e limites.
2. **MythicMobs**: crie skill com nome exato utilizado no Skript (ex.: `Paladin_DivineSmite`). Inclua
config de dano, efeitos visuais e parâmetros.
3. **Skript**: no comando, chame `classes_cooldown_ready(player, "id", cooldown)` antes de consumir o
recurso. Use `classes_consume_resource` para debitar o custo. Por fim, execute
`mm skills cast <Skill> target=%player%` ou `... target=%arg-1%` quando aplicável.

### Itens → MythicMobs + SkBee
- Declare itens focais em `MythicItems`. Adicione `ItemOptions` para NBT customizado e utilize SkBee para
bloquear renome, reparo ou combinação.
- Vincule a entrega dos itens à seleção de classe (Skript ou menu TuSKe) e ao grupo LuckPerms
correspondente.

### Comandos → Skript
- Estruture conforme `cmd_<classe>.sk`: validar grupo, checar cooldown AuraSkills, consumir recurso,
executar skill MythicMobs e enviar feedback.
- Para habilidades direcionadas, use argumentos `<player>` ou `<text>`; para variantes de subclasse
mantenha o sufixo do cooldown (`classe_habilidade_variacao`).

### Permissões → LuckPerms
- Classe base: `lp creategroup class_<classe>` com peso 50.
- Subclasse/arquétipo: `lp creategroup class_<classe>_<arquetipo>` com peso 55.
- Conceda permissões de itens (`mythicmobs.item.<id>`) e comandos (`skript.command.<cmd>`). Mantenha
política de remoção automática de grupos antigos ao mudar de classe.

## Checklist de Portabilidade
- [ ] Papel definido e alinhado ao comparativo de papéis.
- [ ] Recursos configurados no AuraSkills com regeneração apropriada.
- [ ] Passivos implementados no RacesEffects com uptime ≤60 %.
- [ ] Skills MythicMobs criadas e vinculadas aos comandos.
- [ ] Skripts de comando funcionando (`/skript reload cmd_<classe>` sem erros).
- [ ] Itens focais com NBT protegido via SkBee.
- [ ] Permissões LuckPerms aplicadas (`lp user <nick> parent set class_<classe>` funciona).
- [ ] Testes concluídos conforme `README_BALANCEAMENTO.md`.

Seguindo este fluxo você garante que novas classes mantenham consistência com o ecossistema atual do
servidor e respeitem o balanceamento estabelecido.
