# Atributos — Pontos, Modificador (PHB) e Impacto no Jogo

## Visão Geral

- Atributos: STR, DEX, CON, INT, WIS, CHA.
- Pontos para distribuir: 72 (base) + 3 da raça (se o jogador estiver em um grupo `race_*`).
- Limite por atributo: 20 (não pode ultrapassar).
- O bônus aplicado usa o modificador do PHB: `mod = floor((pontuação − 10) / 2)`.
  - Exemplos: 8→−1, 10–11→0, 14–15→+2, 18–19→+4, 20→+5.

## Como distribuir

- Comandos (in‑game):
  - `/abilities` — mostra status atual e pontos restantes.
  - `/abilities set <attr> <valor>` — define um valor (respeita limite e pool).
  - `/abilities add <attr> <delta>` — soma/subtrai do valor atual (respeita limites).
  - `/abilities reset` — zera os 6 atributos.
- O plugin valida a soma (72 + 3 da raça) e o teto 20 por atributo.

## Impactos no Jogo (por modificador do PHB)

O RacesEffects converte o modificador (−5 a +5 com o cap 20) em ajustes de atributos nativos do Minecraft. Coeficientes equilibrados “Heroic+” (moderados‑fortes):

- STR
  - `GENERIC_ATTACK_DAMAGE`: +0.65 por modificador
  - Ex.: 20 (mod +5) → +3.25 de dano
- DEX
  - `GENERIC_MOVEMENT_SPEED`: +0.0045 por modificador (≈ +0.45 m/s por mod; ~+22.5% no +5)
  - `GENERIC_ATTACK_SPEED`: +0.07 por modificador
- CON
  - `GENERIC_MAX_HEALTH`: +1.2 por modificador (1.0 = meio coração)
  - Ex.: 20 (mod +5) → +6.0 de vida (+3 corações)
- INT
  - `GENERIC_LUCK`: +0.25 por modificador
- WIS
  - `GENERIC_KNOCKBACK_RESISTANCE`: +0.06 por modificador
- CHA
  - `GENERIC_LUCK`: +0.15 por modificador

Notas
- Modificadores negativos também aplicam penalidades leves.
- O plugin recalcula periodicamente; mudanças são refletidas em jogo sem reiniciar.

## Exemplo rápido

- Distribuição clássica (72): 15, 14, 13, 12, 10, 8
  - Mods: +2, +2, +1, +1, 0, −1
  - Efeitos (resumo): dano e velocidade sobem moderadamente; CON dá mais vida; CHA negativo reduz um pouco a sorte.

## Ajustes finos (opcional)

- Você pode ajustar os coeficientes em `plugins/RacesEffects/config.yml` no bloco:
  - `apply.abilities.perMod`
- Depois execute `/raceseffects reload` para aplicar.

Arquivos relevantes:
- `plugins/RacesEffects/config.yml`
- `raceseffects/src/main/resources/config.yml`
- `raceseffects/src/main/java/dev/server/races/raceseffects/RacesEffectsPlugin.java`

