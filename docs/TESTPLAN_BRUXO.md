# Plano de Testes – Classe Bruxo

## Setup geral
- Carregar plugins: ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI, RacesEffects, CMI/EssentialsX.
- Executar `/lp applyedits warlock_groups.txt` (ou comandos equivalentes) para garantir grupos criados.
- `/reload` dos arquivos relevantes: `psapi reload`, `/mm reload items`, `/skript reload warlock_cmds`, `/raceseffects reload`.
- Conceder permissões temporárias ao testador (ex.: `lp user Tester parent add class_warlock.fiend`).
- Usar `/kit_bruxo` para inicializar itens e HUD.

## Métricas monitoradas
- DPS médio (PlaceholderAPI ou medidor externo).
- TTK (time-to-kill) alvo dummy de 5k HP.
- Consumo de Mana Pactual / Espaços de Pacto.
- Controle aplicado (tempo total de root/stun/sono/silêncio).
- Verificação de caps: mitigação ≤45%, lifesteal ≤25%.

## Casos de teste PvE (10)
| # | Cenário | Procedimento | Resultados esperados |
| - | ------- | ------------ | -------------------- |
| 1 | Hex básico | `/warp dummy` > `/hex` > confirmar HUD (1 stack) e actionbar. | Hex ativa 12–14s; cooldown individual 45s; scoreboard atualiza. |
| 2 | Rajada Mística | `/eldritch` > observar janela de 10s (scoreboard) e VFX MythicMobs. | 3 projéteis lançados; HUD mostra tempo restante; cooldown global 5s. |
| 3 | Regeneração de pacto | Gaste todos espaços com habilidades de patrono > aguardar 45s fora de combate. | `warlock_invocation_reserve` devolve 1 espaço, mensagem no actionbar. |
| 4 | Fiend – Chicote + Calor | Alternar `/fiend_whip` em dummy e observar stacks de Calor via dano recebido. | +5% dano por stack, máximo 3; mensagem de resistência se placeholder >45%. |
| 5 | Fiend – Armadilha | `/fiend_trap` no solo, esperar 4s. | Explosão 140% + root 2s; trilha visual ativa. |
| 6 | Fiend – Portal Ígneo | `/fiend_gate` mirando 12 blocos. | Teleporte após telegraph de ~0,6s; rastro 4s; sem ultrapassar 45% mitigação. |
| 7 | Archfey – Passo Brumoso | `/archfey_step` em combate com esqueleto. | Teleporte 10 blocos, clones aplicam perda de alvo 2s; cooldown 60s. |
| 8 | Archfey – Cortejo | `/archfey_cortejo` em grupo de 3 aliados. | Aura 8 blocos por 15s, ticks 70% arcano/2s; reduções de CD 20% visíveis via placeholder. |
| 9 | GOO – Marionete | `/oldone_marionete` em dummy móvel. | Alvo anda 3 blocos e fica silenciado 3s; telegraph sonoro. |
| 10 | GOO – Abismo | `/oldone_abismo` em pack de mobs. | Área puxa inimigos por 12s, aplica medo periódico; mitigação +20% sem exceder 45% total. |

## Casos de teste PvP (10)
| # | Cenário | Procedimento | Resultados esperados |
| - | ------- | ------------ | -------------------- |
| 1 | Hex vs jogador | Jogador A (Bruxo) x Jogador B (guerreiro). | Hex aumenta dano recebido de B por 18%; cooldown individual mantido. |
| 2 | Rajada + Hex | Ativar `/hex` seguido de `/eldritch` contra jogador esquivando. | Projetéis rastreiam dentro de 26 blocos; janela respeita 3 tiros/10s. |
| 3 | Fiend – Contrato de Chamas | Duelo prolongado, medir HP ganho. | Lifesteal 12% por 10s sem exceder 25% total; ícone/efeitos exibidos. |
| 4 | Fiend – Inferno Pactuado | Usar ultimate em área 6 blocos em arena. | CD 240s; dano + queimadura aplicados; nenhum alvo fica imune >45% mitigação. |
| 5 | Archfey – Domínio do Sonho | Usar em alvo >70% HP. | Sono 4s, quebra se alvo recebe dano >10% HP; sem reaplicar antes de 90s. |
| 6 | Archfey – Laço Feérico | Prender 3 jogadores em luta. | Root 5s, slow residual 30%/3s; telegraph visível; sem stack infinito. |
| 7 | GOO – Visões Alienígenas | Jogador invisível com poção. | Revela e aplica slow 30%; HUD mostra cooldown 75s. |
| 8 | GOO – Cascata + Loucura | Aplicar 3 stacks via Sussurros antes de ultimate. | Dano aumenta para 130%; verificação de resistência psíquica sem exceder 45%. |
| 9 | Abismo Sussurrante mitigação | Monitorar dano recebido pelo Bruxo durante ultimate. | Mitigação extra +20% mas total ≤45%; mensagem de alerta se exceder. |
| 10 | Integração LuckPerms | Tentar usar `/fiend_whip` sem permissão de patrono. | Comando bloqueado; mensagem de permissão ausente; itens MythicMobs não utilizáveis. |

## Pós-testes
- Revisar logs de Skript e ProSkillAPI para garantir ausência de erros.
- Validar que HUD retorna a zero após `/class reset` ou remover permissões.
- Atualizar planilha de balanceamento (`docs/BALANCE_SHEET_BRUXO.csv`) com métricas reais coletadas.
