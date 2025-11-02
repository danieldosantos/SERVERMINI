# Exemplo: Como adicionar Raça e Classe a um jogador

Este documento mostra um modelo passo-a-passo para atribuir uma raça e uma classe (com patrono) a um jogador, usando LuckPerms e o plugin RacesEffects do servidor.

Objetivo
- Tornar o jogador `Daniel3` Meio‑Elfo e Bruxo (Patrono: Diabo / Infernal).

Resumo rápido (comandos prontos)

- Adicionar raça (sem sobrescrever outros grupos):

```
lp user Daniel3 parent add race_half_elf
```

- Garantir classe e patrono (usar `add` para não sobrescrever):

```
lp user Daniel3 parent add class_warlock
lp user Daniel3 parent add class_warlock_fiend
```

- Sincronizar LuckPerms e recarregar o plugin para aplicar tags/efeitos:

```
lp sync
/raceseffects reload
```

- Verificações:

```
lp user Daniel3 info
tag Daniel3 list
```

Passo a passo detalhado

1) Conferir estado atual do jogador

- Veja quais grupos o usuário já tem (útil para decidir entre `set` ou `add`):

```
lp user Daniel3 info
```

2) Adicionar a raça (sem apagar outros parents)

- Use `parent add` para não sobrescrever classes já atribuídas:

```
lp user Daniel3 parent add race_half_elf
```

- Se quiser substituir todos os parents (cuidado):

```
lp user Daniel3 parent set race_half_elf
```

3) Adicionar classe e patrono

- Use `add` para cada grupo auxiliar que represente a classe e o patrono:

```
lp user Daniel3 parent add class_warlock
lp user Daniel3 parent add class_warlock_fiend
```

4) Aplicar/Sincronizar

- Peça ao LuckPerms aplicar as mudanças (às vezes necessário em setups distribuídos):

```
lp sync
```

- Recarregue o plugin de efeitos para forçar a aplicação de tags configuradas em `apply.groups`:

```
/raceseffects reload
```

- Em alternativa, peça para o jogador reconectar (logout/login).

5) Verificar que as tags e efeitos foram aplicados

- Conferir grupos no LuckPerms:
```
lp user Daniel3 info
```

- Conferir tags no scoreboard do jogador (RacesEffects usa tags para mapear efeitos):
```
tag Daniel3 list
```

- Saída esperada: `tag Daniel3 list` deve listar tags como `race_darkvision`, `trait_slowfall_halfelf`, `trait_dolphin_halfelf` (dependendo do `apply.groups` configurado) e quaisquer tags relacionadas ao patrono/classe.

Notas importantes

- `lp user <nick> parent set <group>` substitui todos os parents/contextuais do usuário — use com cuidado. Para adicionar sem remover, prefira `parent add`.
- Certifique-se que os grupos (`race_half_elf`, `class_warlock`, `class_warlock_fiend`) existam no LuckPerms. Se não existirem, crie-os com `lp creategroup <nome>` ou rode os scripts de setup fornecidos no repositório (`races/luckperms-setup.txt`).

Comandos úteis para criar/gravar grupos (se preciso):
```
lp creategroup race_half_elf
lp group race_half_elf setdisplayname "Raça: Meio‑Elfo"
lp group race_half_elf permission set race.id.half_elf true

lp creategroup class_warlock
lp group class_warlock setdisplayname "Classe: Bruxo"
# Criar patronos
lp creategroup class_warlock_fiend
lp group class_warlock_fiend setdisplayname "Patrono: Diabo"
```

Soluções rápidas se as tags não aparecem

- Recarregue o plugin e sincronize LuckPerms:
```
lp sync
/raceseffects reload
```

- Peça para o jogador reconectar.
- Verifique `plugins/RacesEffects/config.yml` e confirme que existe a seção `apply.groups` com o mapeamento de grupos -> tags (ex.: `race_half_elf: [race_darkvision, trait_slowfall_halfelf]`).
- Caso o config esteja corrompido ou com indentação errada, abra e corrija (o plugin logará erros no console ao tentar recarregar).

Comando utilitário do plugin (reset) — para forçar limpeza de profile antes de reatribuir:
```
/raceseffects reset Daniel3
```

Exemplo final (sequência pronta para colar no console)

```
# Criar grupos caso não existam (opcional)
lp creategroup race_half_elf
lp creategroup class_warlock
lp creategroup class_warlock_fiend

# Atribuir ao jogador (sem sobrescrever outros parents)
lp user Daniel3 parent add race_half_elf
lp user Daniel3 parent add class_warlock
lp user Daniel3 parent add class_warlock_fiend

# Sincronizar e recarregar
lp sync
/raceseffects reload

# Verificar
lp user Daniel3 info
tag Daniel3 list
```

Se quiser, posso criar também um arquivo `.txt` com essa sequência pronta para colar no console do servidor.

---
Documento gerado automaticamente pelo script de auxílio do repositório.
