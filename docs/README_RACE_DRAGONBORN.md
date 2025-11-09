# Draconato — Implementação Minecraft + D&D 5e

## Visão Geral
Os draconatos são descendentes diretos de dragões ancestrais, trazendo poder elemental canalizado em sopros devastadores e habilidades únicas por linhagem. Esta implementação foca em equilíbrio PvE/PvP respeitando limites globais do servidor (mitigação ≤45%, controle ≤4s, velocidade ≤+0.05).

## Estrutura de Arquivos
- `plugins/RacesEffects/races/dragonborn_base.yml`: núcleo da raça (passivos, resistência, HUD).
- `plugins/RacesEffects/races/dragonborn_<elemento>.yml`: variações de fogo, gelo, ácido, relâmpago e veneno.
- `plugins/ProSkillAPI/skills/races/dragonborn/*.yml`: habilidades ativas (sopro e focos elementais).
- `plugins/MythicMobs/Items/races_dragonborn_items.yml`: itens focais, partículas e projetores.
- `plugins/Skript/scripts/races_dragonborn_cmds.sk`: comandos, integração com SkillAPI e itens.
- `plugins/LuckPerms/races_dragonborn_groups.txt`: grupos e hierarquia de permissões.
- Documentação de QA: `docs/TESTPLAN_RACE_DRAGONBORN.md`, `docs/BALANCE_SHEET_RACE_DRAGONBORN.csv`.

## Passivos Base (RacesEffects)
- **Ascendência Dracônica:** marcador que sinaliza o elemento ativo para habilidades e itens.
- **Presença Dracônica:** aura visual + aumento de 5% no alcance da voz em combate.
- **Escamas Sintonizadas:** resistência elemental leve com DR (15% por 8s ao sofrer dano correspondente). Resistência base 10% com teto 45%.

## Habilidade Ativa Base
- **Sopro Dracônico (`Dragonborn_Breath`)**
  - Cone fixo de 6 blocos, dano 120% (T1) até 180% (T4).
  - Consome 1 carga de sopro, recarga natural fora de combate (45–55s conforme tier).
  - Cooldown fixo em 55s → uptime ≈ 1/minuto.
  - DOT elemental reduzido em PvP via configuração ProSkillAPI.

## Linhagens Elementais
| Linhagem | Passivo Principal | Habilidade Ativa | Pontos Fracos |
| --- | --- | --- | --- |
| Fogo | `Sangue Ígneo`: +6% dano vs alvos queimando | `Lâmina Ígnea` (arma flamejante 4s, CD 60s) | +10% dano de gelo quando habilidade está em recarga |
| Gelo | `Gélido`: sopro aplica lentidão 15%/3s | `Congelamento Breve` (root 2s, CD 75s) | +8% dano de fogo se sem Carapaça |
| Ácido | `Corrosão`: -5% defesa, até 2x | `Erosão Concentrada` (marca 10s, CD 60s) | +10% dano de raio sem Vapores ativos |
| Relâmpago | `Frenesi Elétrico`: críticos recuperam 5% da carga (CD 15s) | `Estalo Dracônico` (dash 6 blocos, CD 65s) | +10% dano ácido se Frenesi em CD |
| Veneno | `Névoa Tóxica`: sopro aplica veneno 4s | `Bruma Ácida` (nuvem 6 blocos/6s, CD 75s) | +12% dano radiante sob sol sem Detox |

Todas as resistências usam DR quadrática compartilhada com o arquivo base para garantir mitigação total ≤45%.

## Itens Focais (MythicMobs)
- `DraconicCharm`: ativa o sopro (checa cargas via Skript/HUD).
- `FlameScaleAmulet`, `FrostShardTalisman`, `CorrosiveSpineCharm`, `StormGlandBand`, `ToxicDrakeCharm`: acessos às habilidades das linhagens.
- Skills visuais (`DragonbornBreathCone`, `DragonfireTrail`, etc.) garantem partículas temáticas e projetor cônico.

## Comandos (Skript)
- `/sopro_draco`: dispara o sopro (requer permissão `race_dragonborn`).
- `/foco_draco_<fogo|gelo|acido|raio|veneno>`: habilidades situacionais por linhagem.
- `/kit_racial_draco [jogador] [modo]`: entrega itens para QA (`base`, `fogo`, `gelo`, `acido`, `raio`, `veneno`, `all`).
- `/racial` recebe novos atalhos integrados (via `races_dwarf_cmds.sk`).

## Permissões (LuckPerms)
```
lp creategroup race_dragonborn
lp creategroup race_dragonborn.fire
...
lp parent set race_dragonborn.fire race_dragonborn
```
As habilidades checam tanto `group.<permissão>` quanto `lp.<permissão>` para compatibilidade.

## HUD e PlaceholderAPI
Scoreboard definido em `dragonborn_base.yml`:
- Linha 1: cooldown do sopro.
- Linha 2: cargas atuais (`breath_charge`).
- Linha 3: status da resistência temporal (`draconic_resistance`).

## Balanceamento
- Cooldowns: Sopro 55s, habilidades 60–75s.
- Uptime de resistências ≤60% através de `uptime_cap` e DR.
- Controle limitado (root 2s, stun ≤1.5s, veneno/queimadura ≤4s).
- Pontos fracos explícitos por elemento para preservar counterplay em PvP.

## Integração com Regeneração de Cargas
`races_cmds.sk` possui rotina de regeneração quando o jogador fica 15s fora de combate → executa `raceseffects charges <player> regen`, garantindo recuperação gradual de sopro.

## Dependências
- RacesEffects ≥ versão que suporta `diminishing_returns` e HUD por scoreboard.
- ProSkillAPI com suporte a recursos personalizados (`breath_charge`).
- MythicMobs para partículas/projetores.
- Skript 2.6+ (funções, `parsed as player`).
- PlaceholderAPI para HUD.

## Boas Práticas
- Ajustar dano base do sopro através do multiplicador no SkillAPI caso novas classes impactem o meta.
- Utilizar MythicMobs para definir materiais alternativos mantendo tags NBT `race-lock` e `skill`.
- Em PvP competitivo, considerar reduzir `max_targets` do sopro se necessário (default 8).
