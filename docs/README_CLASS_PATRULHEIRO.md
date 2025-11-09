# Classe Patrulheiro

## Visão Geral
O Patrulheiro é o especialista em dano consistente à distância e controle de terreno. A classe utiliza o recurso **Foco de Caça**, um pool leve de cargas que alimenta Marca do Caçador, Armadilha Tática e ordens ao companheiro. O foco regenera fora de combate (1 carga a cada 50s após 12s sem lutar) e é exibido na HUD via PlaceholderAPI (`{hunt_focus}`).

- **Função primária:** DPS consistente com utilitário de controle.
- **Escopo PvE/PvP:** sustentação de dano sem one-shot, telegraph visível para controle de área e caps globais respeitados (velocidade +0.05, mitigação 45%, 1 pet ativo).
- **Integrações:** ProSkillAPI (classe/skills), MythicMobs (itens focais e efeitos), Skript (recursos/glue/comandos), LuckPerms (gating), RacesEffects (exaustão de sinergias raciais), PlaceholderAPI/Scoreboard (HUD).

## Recurso: Foco de Caça
| Tier | Cargas Máximas | Observações |
| --- | --- | --- |
| T1 | 3 | Início da progressão, usado para marca e armadilhas. |
| T2 | 3 | Mantém pressão constante, sem ampliar spam. |
| T3 | 4 | Desbloqueia segunda armadilha via passiva Mestre das Armadilhas. |
| T4 | 4 | Recursos focados em ultimates e comandos do pet. |

Regeneração: 1 carga a cada 50s fora de combate (delay 12s). Placeholder `{cooldown_next}` indica próxima recarga; `{hunt_focus}` mostra cargas ativas.

## Árvore de Skills por Tier
### Tier 1
- **Marca do Caçador** – marca alvo, revela por 12s e amplia dano direcionado.
- **Armadilha Tática** – coloca armadilha de 3.5 blocos (10s) com telegraph de 1.5s; aplica Lentidão II e dano leve.
- **Comando de Disparo** – 3 projéteis com 75% de dano e spread controlado.
- **Terreno Favorito (passiva)** – bônus situacional de 12% em biomas/presas; uptime ≤60% via comandos de rastreio.
- **Compasso do Caçador (passiva)** – partículas de rastreio e HUD do alvo marcado.

### Tier 2
Mantém kit principal com escalas em dano da marca (até +12%) e trap cooldown (80s).

### Tier 3
Adiciona **Mestre das Armadilhas** – libera segunda armadilha simultânea e +15% dano vs. alvo marcado. Marca escala a +14%, armadilha a 70% dano total.

### Tier 4
Escalas finais: Marca 16% dano, Armadilha 80%, Comando de Disparo com cooldown reduzido (40s). Reforça integração com ultimates das subclasses.

## Subclasses (Conclaves)
### Conclave do Caçador (PHB)
- **Tema:** Versatilidade ofensiva entre presa única e hordas.
- **Skills:** Rajada Predatória, Rede Enredante, Olho do Predador, Passo Silvestre, Chuva de Flechas (ULT).
- **Itens focais:** HunterBow, HunterNet, HunterScope, HunterBoots, HunterQuiver.

### Conclave do Mestre das Feras (PHB)
- **Tema:** Sinergia com companheiro animal único.
- **Skills:** Chamado Alpha, Sincronia Selvagem, Investida Coordenada, Proteção Instintiva, Fúria Primal (ULT).
- **Itens focais:** BeastWhistle, BeastCharm, BeastHarness, BeastShield, BeastTotem.
- **Regras do pet:** 1 companheiro ativo, HUD `{pet_hp}` com barra de lealdade, sem montaria em PvP ranqueado.

### Conclave do Espreitador Noturno (adaptação)
- **Tema:** Emboscada, mobilidade umbrática, controle leve.
- **Skills:** Sombras Caçadoras, Salto Umbrático, Manto Penumbra, Disparo Envenenado, Caça Sombria (ULT).
- **Itens focais:** GloomDagger, GloomCloak, GloomHood, GloomBow, GloomRelic.

## Comandos (Skript)
- `/kit_ranger` – entrega kit de teste com itens focais conforme permissões, limpa estado, reseta HUD.
- `/marca`, `/armadilha`, `/rajada` – atalhos para skills base.
- `/companheiro` – aciona Chamado Alpha se for Mestre das Feras.
- `/rastrear_on` / `/rastrear_off` – ativa/desativa partículas do Compasso.
- Alias específicos: `/hunter_scope`, `/beast_sync`, `/gloom_cloak`.

Flags de combate controlam regeneração de Foco. Itens de MythicMobs executam skills correspondentes e exigem permissões LuckPerms.

## Integrações com Raças
- Ao detectar bonus de alcance/velocidade/resistência via RacesEffects, aplica exaustão de resistência para respeitar caps globais (45% mitigação, velocidade extra ≤0.05).
- Passivos do Patrulheiro não removem fraquezas raciais; apenas mitigam dentro do limite.

## Scoreboard e HUD
- `{hunt_focus}` – cargas atuais de Foco.
- `{mark_time_left}` – tempo restante da marca ativa.
- `{cooldown_next}` – estimativa para próxima recarga de foco.
- `{pet_hp}` – status de lealdade/vida do companheiro (subclasse Mestre das Feras).

## QoL
- Mensagens temáticas (titles/action bar) para marca, armadilhas e ordens do pet.
- Telegráficos via partículas e MythicMobs (`ranger_arrow_storm`, `ranger_shadow_hunt`) indicam áreas antes do dano.
- Compatível com /kit de testes via CMI/EssentialsX se instalados.
