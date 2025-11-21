# Como adicionar mais truques e magias

O pacote atual inclui apenas um conjunto reduzido de truques e magias essenciais para facilitar testes rápidos no servidor. A lista completa do Livro do Jogador não foi incorporada por duas razões:

1. **Escopo e equilíbrio:** adicionar todas as magias exigiria ajustes extensos de dano, custos de recurso e recargas para manter a jogabilidade equilibrada no Minecraft.
2. **Licenciamento:** a Wizards of the Coast só libera parte do conteúdo sob a OGL/SRD; magias exclusivas do Livro do Jogador não podem ser distribuídas integralmente aqui.

## Passo a passo para criar novas magias
1. Copie um bloco existente de cantrip ou magia em `dnd_features.yml` como base (por exemplo, `Cantrip.Firebolt`).
2. Altere o nome para algo como `Cantrip.NomeDaMagia` ou `Spell.NomeDaMagia`.
3. Ajuste:
   - **`Cooldown`:** defina o tempo de recarga em segundos (ex.: 0 para cantrips, 6–12 para magias de 1º nível).
   - **`Skills`:** configure `projectile`, `damage`, `potion`, `ignite` e outros efeitos conforme o tema da magia.
   - **Valores de dano/efeito:** use fórmulas com `<caster.pb>` e modificadores (`<caster.mod:INT>`, `<caster.mod:WIS>`, `<caster.mod:CHA>`) para escalar com nível e atributo.
4. Teste no servidor com `/mm reload` e use o gatilho apropriado para validar partículas, alcance e balanceamento.

## Modelo de referência (cantrip ofensivo)
```yaml
Cantrip.ExampleBolt:
  Cooldown: 0
  Skills:
  - projectile{onHit=ExampleBoltHit;v=24;i=1;hR=0.4;vR=0.4}

ExampleBoltHit:
  Skills:
  - damage{amount="1 + <caster.pb> + <caster.mod:INT>";type=MAGIC}
  - potion{type=SLOW;duration=40;level=0} @target
```

## Dicas rápidas de balanceamento
- **Cantrips:** mantenha dano base próximo a `1 + PB + mod` e poucos efeitos secundários.
- **Magias de 1º nível:** recarga de 8–12s e dano em torno de `3–4 + PB + mod` ou efeito de controle moderado.
- **Magias de níveis superiores:** aumente recarga e efeitos em blocos separados, ou crie variantes (`Spell.Fireball2`, `Spell.Fireball3`) se precisar simular níveis superiores.

Seguindo este guia você pode expandir o pacote com os truques e magias que faltam do Livro do Jogador, mantendo a consistência com as mecânicas já configuradas.
