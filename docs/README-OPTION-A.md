Resumo: Option A com plugins existentes (LuckPerms + ProSkillAPI).

Objetivo
- Criar grupos do LuckPerms por raça/sub-raça para registrar a escolha do jogador.
- Usar esses grupos/permissões como gatilhos para bônus/efeitos nos plugins (ex.: ProSkillAPI passivas condicionalmente por permissão).

Passo 1 — Criar grupos no LuckPerms
- No console do servidor (janela do Paper), cole os comandos de `races/luckperms-setup.txt`.
- Isso cria grupos como `race_dwarf`, `race_elf`, etc., com `permission: race.id.<id>`.

Passo 2 — Definir como o jogador escolhe a raça
Opção simples (manual):
- Admin roda: `lp user <nick> parent set race_<id>`
- Ex.: `lp user Steve parent set race_elf`

Opção por comando (temporário, sem GUI):
- Deixe os jogadores pedirem no chat e um admin aplica o comando.
- Alternativa: adicionar um plugin de menus/GUI (recomendado) para autoaplicar o grupo.

Passo 3 — Aplicar bônus com ProSkillAPI
- Após o servidor gerar `plugins/ProSkillAPI/` na primeira inicialização, podemos criar “skills/passivas” dinâmicas por raça.
- Cada passiva verifica `permission: race.id.<id>` e aplica buffs/atributos (STR/DEX/CON/INT/WIS/CHA, velocidade, resistências, magias por descanso, etc.) conforme o possível no ProSkillAPI.
- Quando quiser, me peça para gerar os YAML em `plugins/ProSkillAPI/dynamic/skills/` com base no `races/races.yml`.

Observações
- `races/races.yml` já lista as raças/sub-raças e seus bônus (fatos mecânicos).
- LuckPerms guarda dados em banco; por isso fornecemos comandos para colar no console.
- Para impedir múltiplas escolhas, mantenha um procedimento: antes de definir nova raça, remova a anterior (ou use `parent set` para sobrescrever).
