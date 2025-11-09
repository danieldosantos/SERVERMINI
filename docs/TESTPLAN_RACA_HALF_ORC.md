# Plano de Testes — Raça Meio-Orc

## Escopo
Garantir que a raça Meio-Orc (base) e as variações Tribal/Gladiador carregam corretamente em RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms e HUD PlaceholderAPI.

## Preparação
1. Criar grupos conforme `plugins/LuckPerms/races_half_orc_groups.txt`.
2. Atribuir ao jogador de QA o grupo adequado (`race_half_orc`, `race_half_orc.tribal`, `race_half_orc.gladiador`).
3. Recarregar scripts: `/sk reload races_half_orc` e `/sk reload races_cmds`.
4. Entregar itens via `/kit_racial_half_orc <jogador> [base|tribal|gladiador|all]`.
5. Confirmar placeholders ativos com `%raceseffects_racial_cd_main%`, `%raceseffects_racial_tenacidade%`, `%raceseffects_racial_tracker%`, `%raceseffects_racial_combo%`.

## Testes Base (Meio-Orc)
- **Visão no Escuro**
  - Entrar em cavernas (nível de luz baixo) e verificar alcance de 24 blocos; testar cooldown 45s ao alternar para luz intensa.
- **Tenacidade Implacável**
  - Forçar queda para <35% HP e medir mitigação +15% por 6s; confirmar ICD 45s exibido em `/tenacidade` e uptime ≤13%.
  - Combinar com classe tank para validar DR e respeitar cap 45%.
- **Ameaça Orc**
  - Combate controlado onde o mob atinge o jogador; medir +5% dano apenas após receber golpe dentro de 4s.
  - Garantir que em aberturas (antes de ser atingido) não aplica bônus.
- **Fúria Controlada**
  - Ativar com `/furia_orc` e pelo item OrcBattleTalisman; confirmar buff 8s, Fatiga 6s e cooldown 75s.
  - Verificar HUD: `/tenacidade` mostra CD atualizado e status de Fatiga via placeholder `{racial_furia_fadiga}`.

## Testes Sub-raça Tribal
- **Instinto do Rastreador**
  - Usar `/tribal` para marcar alvo; checar vestígios visuais leves por 10s e ausência de revelação completa.
  - Confirmar que habilidades de invisibilidade total continuam ocultas.
- **Passo Selvagem**
  - Percorrer florestas/taigas/pântanos/savanas e observar +0.02 velocidade por 8s (ICD 30s, uptime ≤40%).
  - Fora desses biomas, verificar que o bônus não ativa.
- **Chamado da Caça**
  - Testar penetração +12% em boneco de treino (log de dano) e em jogador com armadura pesada.
  - Medir HUD `{racial_tracker}` exibindo status do alvo marcado.

## Testes Sub-raça Gladiador
- **Ritmo de Arena**
  - Executar sequência de 3 golpes em ≤6s para ganhar +10% velocidade de ataque por 4s; confirmar ICD 20s.
  - Validar que buffs externos não ultrapassam limite global de velocidade (+0.05 cumulativo).
- **Grito de Desafio**
  - Utilizar item/skill associado (gatilho manual) provocando mobs em 4 blocos; verificar que cada alvo só pode ser provocado 1x/90s.
- **Estouro de Arena**
  - Ativar `/arena` e item ArenaWarBangle; medir +25% dano por 6s e penalidade -20% velocidade de ataque por 6s após expirar.
  - Confirmar que combinação com manobras do Guerreiro respeita caps e aplica DR onde necessário.

## Integração PvE/PvP
- **PvE**
  - Enfrentar lobo/urso/guardião com cada sub-raça monitorando DPS/TTK e acionamento de Tenacidade.
  - Luta de chefe em arena: avaliar Estouro de Arena + Ritmo e checar penalidade pós-burst.
- **PvP**
  - Duelo vs Ladino/Guerreiro/Paladino verificando janelas de burst e contrajogos (Fatiga, Exaustão, DR).
  - Monitorar uptime combinado com habilidades de classe (Bárbaro Fúria, Blood Hunter Lycan) ≤60%.

## Regressão
- Executar `/racial` com outras raças para garantir que o hook `halfOrcRacialHelp` não interfere.
- Recarregar plugins (RacesEffects, ProSkillAPI, MythicMobs) e observar console sem erros YAML/Skript.
- Testar itens raciais em jogadores sem permissão para confirmar bloqueio via `race-lock`.
