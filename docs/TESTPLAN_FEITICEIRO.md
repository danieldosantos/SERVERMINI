# Plano de Testes - Classe Feiticeiro

## Metodologia
- Ambiente: servidor de staging com ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI e RacesEffects ativos.
- Personagens: usar `/kit_feiticeiro` e itens focais de cada origem; verificar permissões via LuckPerms.
- Métricas capturadas: DPS médio, pico de burst (3s), TTK vs alvo dummy, consumo de mana/SP, uptime de buffs, respeito aos caps globais.

## Cenários PvE (10)
1. **Raio Arcano sem metamagia** – medir DPS sustentado em boneco (tier 1) por 60s.
2. **Onda Arcana + empurrão** – validar área 5 blocos e knockback leve, comparar com cap de controle.
3. **Blink Arcano** – checar teleporte 8 blocos, janela antistun 1s e travessia segura.
4. **Recuperação de Feitiçaria** – permanecer 30s fora de combate e garantir +1 SP a cada 12s.
5. **Origem Dracônica: Sopro + Coração Elemental** – medir dano combinado e interação com resistências raciais de fogo/gelo.
6. **Origem Dracônica: Avatar Dracônico** – confirmar aura 6 blocos, duração 15s, DPS 0.8/s e antic knockback.
7. **Magia Selvagem: Fluxo Instável** – executar 10 vezes, registrar taxa de penalidade (~12%) e redução de CDs.
8. **Magia Selvagem: Tempestade Caótica** – avaliar efeitos randômicos (fogo/frio/raio) e manter DPS 0.7/s.
9. **Magia da Tempestade: Nuvem + Condutor Vivo** – medir ganho de mana por acerto elétrico e uptime do campo 8s.
10. **Magia da Tempestade: Olho da Tempestade** – acompanhar tempestade móvel, confirmar velocidade +0.02 e respeito à área 7 blocos.

## Cenários PvP (10)
1. **Burst básico** – Raio Arcano + Potencializada em alvo jogador; confirmar dano extra de 15% sem matar alvo >30% HP.
2. **Metamagia Rápida** – aplicar em Onda Arcana e validar redução de cast/lockout 30s.
3. **Conversão Elemental** – trocar elemento para frio contra alvo com resistência de fogo (via RacesEffects), confirmar diminishing returns.
4. **Escamas Manifestas + Raça resistente** – empilhar buff com resistência racial e garantir limite 45% via aviso do Skript.
5. **Asas Etéreas** – testar velocidade final (≤0.05) e planar 8s em combate, evitando abuso com raças voadoras.
6. **Fluxo Instável + Dado do Caos** – medir DPS de pico com metamagias aplicadas e assegurar CDs >=50% do original.
7. **Véu Probabilístico** – simular 20 hits e confirmar ~30% de negação sem exceder mitigação global.
8. **Canal Elétrico + Descarga Tempestuosa** – validar rastro elétrico via Placeholder e checar knockback/raiz corretos.
9. **Condutor Vivo** – receber 5 golpes elétricos (MythicMobs) e confirmar +20 mana por acerto.
10. **Ultimates cruzados** – comparar Avatar Dracônico vs Olho da Tempestade em duelo controlado, garantindo que nenhum elimina alvo full HP sem prévio dano (<30%).

## Checks Adicionais
- PlaceholderAPI: `%skills_sorcery_points%`, `%skills_sorcerer_metamagic%`, `%skills_sorcerer_highest_cd%` atualizando corretamente.
- Skript: `/give_sp` e `/set_origin` executam sem erro e respeitam permissões.
- MythicMobs: focos respeitam NBT `class-lock` e invocam skills corretas.
- LuckPerms: comandos `sorcerer_groups.txt` aplicados e heranças conferidas.
- Logs: ausência de erros ao carregar YAML/skript (via `/sk reload sorcerer_cmds`).
