# Test Plan - Classe Mago

## Ambiente de Testes
* **Versão do servidor:** PaperMC 1.20+/SkillAPI compatível.
* **Plugins ativos:** ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI, RacesEffects, (Opcional) CMI/EssentialsX para kits.
* **Jogadores de teste:** Pelo menos 3 (Mago, aliado, inimigo) para cenários PvP.
* **Ferramentas:** `/kit_mago`, `/preparar`, `/escola_*`, scoreboard de HUD.

## Métricas Monitoradas
* **DPS:** dano médio por segundo (avaliar com `/minecraft:damage` ou logs SkillAPI).
* **TTK:** tempo para derrotar alvo (segundos).
* **Controle efetivo:** duração real de CC (comparar com valores esperados > 0 e ≤ limites).
* **Uptime de escudos/buffs:** percentual de tempo ativo em combates de 60s.
* **Consumo de Mana/Slots:** mana restante e slots ocupados.

## Cenários PvE (10)
1. **Evocação Solo Farm** – Mago T1 com `Orbe Flamejante` preparado enfrenta 10 Zumbis. Registrar DPS (esperado 45-55) e mana restante (>40%).
2. **Evocação Burst Boss** – Mago T3 usa `Catalisador de Poder` + `Cataclismo Controlado` contra boss Mythic (HP 20k). Verificar janela telegráfica (3s aviso) e evitar one-shot. Registrar TTK comparado a Feiticeiro.
3. **Sustentação Abjuração** – Mago T2 com `Barreira Protetora` e `Selo Rúnico` protege 3 aliados em dungeon. Medir uptime de escudo (≤60%) e mitigação ≤45%.
4. **Contra-Feitiço PvE** – Testar `Contra-Feitiço` em caster NPC (Evoker). Garantir cancelamento se acionado ≤3s e silêncio 2s.
5. **Fortaleza Arcana Raid** – Mago T4 ativa domo durante enxame de flechas. Verificar imunidade a projéteis, regen 5%/s e que dano verdadeiro atravessa.
6. **Ilusão Controle Adds** – `Labirinto Mental` em elites com dano alto; confirmar que CC quebra com 2 golpes fortes e CD 90s.
7. **Véu Invisível Exploração** – Testar stealth + velocidade + teleporte para atravessar obstáculo 6 blocos sem exceder cap +0.05 (Placeholder).
8. **Fantasmagoria Crowd** – Em sala 8 mobs, medir -15% precisão (usar scoreboard hits) e medo 3s. Confirmar CD 105s.
9. **Parede Arcana Defesa** – Colocar parede em corredor estreito contra esqueletos. Confirmar bloqueio de projéteis 6s e que skill falha sem preparo.
10. **Passivas** – Monitorar `Estudo Rigoroso` (-10% CD) e `Foco Mental` (+5% poder) via logs. Alternar mana >80% e <80% para comparar.

## Cenários PvP (10)
1. **Duel T1 Teleporte** – Mago vs Guerreiro. Teleporte deve evitar charges, com CD 45s. Avaliar se mobilidade não excede limites.
2. **Parede Arcana Contest** – Defender ponto contra Arqueiro. Verificar que parede impede flechas por 6s sem bloquear aliados.
3. **Evocação Burst Setup** – `Catalisador de Poder` + `Nova Arcana` em alvo com resistência a fogo. Confirmar aplicação de exaustão por RacesEffects e mitigação adequada.
4. **Lança Etérea Line-Up** – Atravessar 3 jogadores alinhados. Confirmar dano 150% e marca Instabilidade apenas no último alvo.
5. **Cataclismo Controlado Arena** – Em duelo 3v3, ultimate deve ter telemetria suficiente para contrajogo. Registrar se nenhum alvo toma >60% HP em único meteoro.
6. **Abjuração Anti-Burst** – `Barreira Protetora` + `Reflexo Prismático` contra mago inimigo. Monitorar reflect 30% e que mitigação total ≤45%.
7. **Contra-Feitiço Tempo** – Cancelar ultimate de Warlock. Logar silêncio 2s e verificar cooldown 75s.
8. **Fortaleza Arcana Objetivo** – Defender estandarte com domo. Garantir que controle (stuns, pulls) não afete quem está dentro e que dano verdadeiro (por ex. `smite`) entra.
9. **Ilusão Confusão** – `Imagem Múltipla` + `Véu Invisível` para reposicionamento. Verificar que clones absorvem hits leves e stealth cai ao dar dano pesado.
10. **Mundo Espelhado Teamfight** – 5v5; medir impacto de ilusões (absorção de 1 golpe cada) e desorientação sem causar travamentos. Avaliar se uptime ≤12s.

## Critérios de Aceite
* Todos os YAML carregam sem erros (ver console). Nenhuma skill excede caps indicados.
* Items MythicMobs só funcionam quando jogador possui grupo apropriado (LuckPerms).
* `/preparar` impede conjuração de habilidades não marcadas.
* HUD atualiza Mana/Slots/CD a cada 5s.
* RacesEffects aplica exaustão de resistência quando buffs e resistências coincidem.
