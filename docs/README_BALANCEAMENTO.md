# Diretrizes de Balanceamento — Raças e Classes

Este documento define limites obrigatórios para desenvolvimento de raças, classes e habilidades no
servidor. Toda mudança deve ser validada aqui antes de ir para produção.

## Regras Globais
- **Uptime Máximo**: qualquer passivo automático deve permanecer ativo no máximo 60 % do tempo em combate.
Use janelas mortas claras (ex.: 24 s ativos, 16 s inativos).
- **Cooldowns**
  - Habilidades chave: ≥45 s entre usos.
  - Habilidades situacionais: ≥90 s.
  - Ultimates / transformações maiores: 180–240 s.
- **Caps Numéricos**
  - Velocidade adicional permanente ≤ +0,02 (≈+10 %). Buffs temporários podem atingir +0,05 desde que
durem ≤15 s e respeitem cooldown ≥90 s.
  - Redução de dano/resistência plana ≤60 %; imunidades completas apenas em cenários de nicho com janela
morta ≥30 s.
  - Chance crítica ofensiva ≤25 % extra além do equipamento; dano crítico adicional limitado a +50 %.
  - Cura direta não pode exceder 35 % da vida máxima por cast; efeitos de HoT limitados a 6 %/s.

## Orientações por Sistema
### Raças
- **Empilhamento de Resistências**: não acumular mais de duas resistências >40 % na mesma raça. Se um
jogador somar raça + classe, aplicar multiplicador de 0,8 para evitar imunidades totais.
- **Itens Raciais**: teleportes/escape ≥240 s; controle de grupo ≥120 s; sopros elementais padronizados
em 45 s com dano escalado por AuraSkills.
- **Atributos Bônus**: distribua +2/+1 ou +1/+1/+1 ao aplicar pontos no AuraSkills. Variações fora desse
formato devem ser compensadas com desvantagem passiva.

### Classes
- **Recursos**: regen base de mana = 5 %/5 s; fury/ki/divinity recuperam 1 carga a cada 30 s fora de
combate. Bloquear regen enquanto estiver sob efeitos de controle forte.
- **Buffs de Grupo**: somatórios máximos de +15 % dano/cura. Buffs iguais não acumulam; manter apenas o
mais forte ativo.
- **Habilidades de Sobrevivência**: efeitos como Relentless, Second Wind, Lay on Hands compartilham
cooldown global de 300 s para evitar “cadeia de vida”.
- **Pets e Invocações**: vida escalonada em 50 % da vida do invocador e recebem 200 % de dano de área para
impedir bloqueio permanente.

## Comparativo e Ajustes Recomendados
### Raças
| Raça/Subtipo | Pontos Fortes | Ajuste Necessário | Observação |
| --- | --- | --- | --- |
| Alto Elfo | Retaliação automatizada | Aplicar intervalo interno 5 s e limitar dano a 2,0 | Evita burst infinito |
| Elfo da Floresta | Mobilidade + invisibilidade | Garantir requisito de ambiente (tronos com folhas) | Ajustar Skript para checar bloco |
| Drow | Teleporte + debuffs em profundidade | Bloquear teleporte se recebeu dano nos últimos 3 s | Mantém identidade sem fuga infinita |
| Anão Profundezas | Cegueira automática | Limitar a 10 s e adicionar cooldown interno 20 s | Reduz frustração |
| Gnomo Floresta | Invisibilidade condicional | Aumentar cooldown do item para 420 s (já aplicado) | Monitorar abuso em PvP |
| Halfling | Controle em área (trombeta) | Compartilhar cooldown 120 s entre raças | Evita stack |
| Meio-Orc | Relentless Endurance | Definir cooldown global 300 s + exibir mensagem de recarga | Transparência |
| Humano | Regeneração | Aplicar janela morta 20 s após cada ativação | Evita looping |
| Tiefling | Resistência a fogo + velocidade | Introduzir “exaustão de resistência” após 3 hits fortes | Já mapeado no Skript |
| Dragonborn | Sopro elemental | Padronizar dano por nível e recarga 45 s | Escala com AuraSkills STR/CHA |

### Classes
| Classe | Pontos Fortes | Ajuste Necessário | Observação |
| --- | --- | --- | --- |
| Bárbaro | Fúria alta + redução | Associar custo de `fury` por ativação e limitar 3 usos consecutivos | Evita uptime >60 % |
| Bardo | Cadeia de buffs | Limitar Inspiração a 3 alvos e Canção em 30 s duração | Controla empilhamento |
| Bruxo | Controle + dano em área | Adicionar cooldown global 10 s por alvo para Hex/rajada | Evita spam |
| Clérigo | Cura explosiva | Forçar cap 35 % de cura por cast e HoT 6 %/s | Mantém risco |
| Druida | Controle de terreno | Formas fortes ≤60 s com recarga 180 s | Segue regra global |
| Feiticeiro | Burst elemental | Metamagia consome 1–2 `sorcery_points`; regen apenas fora de combate | Impede loops |
| Guerreiro | Action Surge + manobras | Cooldown 120 s e custo de `battle_focus` | Balanceia burst |
| Ladino | Ataque furtivo | Reaplicar cooldown 6 s via scoreboard global | Impede reset via logout |
| Mago | Controle massivo | Aplicar diminishing returns (−30 % duração por reaplicação) | Evita CC infinito |
| Monge | Mobilidade constante | Regeneração de Ki pausada em combate | Mantém mobilidade situacional |
| Paladino | Aura permanente | Uptime 60 % com custo de `divinity` por 10 s de aura | Evita invulnerabilidade |
| Patrulheiro | Marca + pet | Compartilhar cooldown de pet 90 s e vulnerabilidade a dano em área | Ajuste PvP |

## Checklist de Teste
1. Validar logs de MythicMobs e Skript para confirmar cooldowns reais.
2. Medir dano/cura médio em arenas PvE e PvP a cada atualização major.
3. Atualizar este documento sempre que introduzir nova subclasse ou raça experimental.

## Procedimento de Revisão
- Revisão cruzada mensal entre equipe de desenvolvimento e staff de eventos.
- Registrar ajustes aplicados no changelog interno e anexar métricas (uptime, DPS, HPS).
- Se a regra for violada em produção, priorizar hotfix e informar jogadores no Discord oficial.
