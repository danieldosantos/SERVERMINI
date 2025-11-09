# Comandos de Raça — Dragonborn (Draconato)

Este guia documenta como aplicar cada subtipo de Dragonborn e como dar os itens/tags do sopro. Diferente de outras raças, o Dragonborn NÃO usa `/raceitem give` — a aplicação é por subtipo direto no LuckPerms e os itens vêm via `forcegiveitems`.

## Aplicar subtipo (LuckPerms)

- Fogo: `lp user <nick> parent set race_dragonborn_fire`
- Frio: `lp user <nick> parent set race_dragonborn_cold`
- Raio: `lp user <nick> parent set race_dragonborn_lightning`
- Ácido: `lp user <nick> parent set race_dragonborn_acid`
- Veneno: `lp user <nick> parent set race_dragonborn_poison`

Depois de definir o subtipo, rode:

- `forcegiveitems <nick>` — entrega o item de sopro do subtipo e aplica a tag de imunidade correspondente:
  - Fire → `db_fire`
  - Cold → `db_cold`
  - Lightning → `db_lightning`
  - Acid → `db_acid`
  - Poison → `db_poison`

## Uso do item de sopro

- Clique com o botão direito segurando o item para ativar.
- Recarga: 10s por jogador.
- Efeitos por subtipo (resumo funcional do servidor):
  - Fire: cone de chamas à frente; ignora entidades com `db_fire`.
  - Cold: área 5x5 com Slowness; `db_cold` imune (também imune a congelamento em neve fofa).
  - Lightning: linha de 7 relâmpagos; o subtipo é imune ao dano de raio nas proximidades do impacto.
  - Acid: chuva 5x5 por ~6s, 1 de dano/seg (Ruptura simulada); `db_acid` imune.
  - Poison: área com Poison; `db_poison` imune.

## Dicas rápidas

- Se o sopro não ativar ao clicar, confira:
  - O jogador está no grupo correto (`lp user <nick> info` → `race_dragonborn_<tipo>`)?
  - Após trocar de subtipo, rodou `forcegiveitems <nick>` para atualizar itens/tags?
  - Para depurar, recarregue o Skript: `skript reload breath_cloud.sk`.

