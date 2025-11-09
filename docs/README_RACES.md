# Raças D&D 5e – Implementação Servidor Mini

Este documento consolida as raças do Livro do Jogador (PHB) adaptadas para o servidor. Todas respeitam caps globais, uptime máximo de 60% para passivos e utilizam integração com LuckPerms, RacesEffects, ProSkillAPI, MythicMobs e Skript.

## Resumo Geral

| Raça / Subraça | Identidade | Passivos (uptime/caps) | Ativos (custo / duração / CD / área) | Item Racial (MythicMobs) | Sinergias & Restrições | Caps observados |
| --- | --- | --- | --- | --- | --- | --- |
| Anão da Colina | Fortitude espiritual subterrânea | Resiliência da Colina (45% uptime, +8% HP, DR veneno até 45% com DR), Calma Ancestral (regen 3% em montanha, 40% uptime) | Fortitude Anã – stamina 0, 8–10s, 90–80s CD, alvo: si + aliado | Pedra da Fortitude | Combo saudável com Guardião veneno; excesso de DR converte em absorção | Mitigação total ≤ 45% |
| Anão da Montanha | Pele pétrea e disciplina | Pele de Granito (10s/45s, 12% DR física), Olhos da Forja (darkvision 18 blocos) | Postura de Pedra – stamina 10, 8–9s, 90–80s CD, self | Totem da Postura | Tanques convertem mitigação extra em estabilidade; reduz velocidade 10% | Knockback resist ≤ 75% |
| Draconato | Soldado elemental | Escamas Elementais (10s/45s, 15% DR elementar), Sangue Ardente (8s/60s, +12% dano <50% HP) | Sopro Dracônico – mana 35, cone 6 blocos, CD 60–50s | Talismã Dracônico | Warlock fogo aplica DR extra; sem one-shot (PvP 130% limite) | Mitigação fogo ≤ 45% |
| Alto Elfo | Arcanista preciso | Disciplina Arcanista (12s/45s, +8% regen mana, +12% precisão), Memória Ancestral (reconfigura truque fora de combate) | Truque Arcano – mana 20, alvo único, CD 60–50s | Foco Arcano Élfico | Proíbe precisão total >20%; combos com magos de controle | Precisão bônus ≤ 20% |
| Elfo da Floresta | Patrulheiro ágil | Passo Ligeiro (15s/45s, +0.02 velocidade em floresta), Sentidos Aguçados (18s/60s, revela 8 blocos) | Passos Feéricos – stamina 25, teleporte 6–7 blocos, CD 75–60s | Pluma dos Bosques | Sombra/invis reduzida a 60%; patrulheiro ganha mobilidade controlada | Velocidade total ≤ 0.05 |
| Drow | Intrigante subterrâneo | Visão Superior (24 blocos, 30s/45s), Fragilidade à Luz (debuff 12s/30s, +8% dano recebido) | Luzes Feéricas – mana 25, área 8 blocos, CD 75–65s; Manto das Sombras – mana 35, cúpula 6 blocos 8–9s, CD 120–105s | Broche da Lua Profunda | Invi/PvP reduzida; luz solar mantém fraqueza ativa | Mitigação sombria ≤ caps |
| Gnomo da Floresta | Encantador de feras | Amigo dos Animais (12s/75s, charme PvE 6 blocos), Mente Ligeira (8s/60s, 15% DR mental) | Canto Cativante – mana 25, área 6–8 blocos, CD 90–80s | Apito Encantador | Charme não afeta bosses T3+; PvP redução dano 10% | Controle ≤ 6s |
| Gnomo da Rocha | Engenheiro resiliente | Pele Metaloide (8s/45s, 10% DR mágico), Afinidade Artífice (20s/90s, +15% sucesso craft) | Engenho Gnomesco – mana 30, escudo 10–12s, CD 90–80s | Núcleo Girognomo | Absorção extra vira estabilidade se classe já usa escudos | Absorção ≤ 24% |
| Halfling Pés-Leves | Evasivo sortudo | Sorte Halfling (proc 18s, ignora 1 golpe, uptime 30%), Passo Suave (camuflagem 40% por 12s) | Passos Silenciosos – stamina 15, dash 5–6 blocos, CD 75–60s | Moeda da Sorte | Invis + camuflagem ≤60%; evita one-shot mas respeita CD | Evasão controlada |
| Halfling Robusto | Vitalidade caseira | Resistência Halfling (12s/45s, 20% DR veneno), Vigor Caseiro (15s/60s, regen 4%) | Fortalecer a Família – mana 20, aura 6 blocos 10–12s, CD 90–80s | Concha de Lar | Cura extra convertida em HoT leve; sinergia com bardos | Cura ≤ 25% dano |
| Humano Padrão | Adaptável e organizado | Adaptabilidade (20s/120s, realoca +1 atributo), Empreendedorismo (30s/180s, +10% social) | Investida Coordenada – stamina 20, aura 8 blocos 10–12s, CD 90–80s | Estandarte Coordenado | Buff social não afeta loot competitivo; velocidade cap 0.05 | Velocidade ≤ 0.05 |
| Humano Variante | Talentos específicos | Talento Flexível (swap talento 180s), Foco Intenso (8s/60s, +10% dano PvE / +6% PvP) | Talento Variável – mana 15, stance 8–9s, CD 75–60s | Medalhão de Talento | Reduz CD adicional se classe já otimizada; talentos equilibrados | CD total ≥ 45s |
| Meio-Elfo | Diplomata híbrido | Eco Élfico (opção com -20%), Eco Humano (-20%), Diplomacia Natural (15s/60s, -10% CDs sociais) | Harmonia Híbrida – mana 25, aura 6 blocos 10–12s, CD 90–80s | Orbe da Harmonia | DR compartilhado converte excedente em absorção 6% | Mitigação ≤ 45% |
| Meio-Orc | Berserker resiliente | Ferocidade do Sangue (10s/45s, +10% PvE/+6% PvP execute), Músculos Marcados (8s/60s, 12% DR <40% HP) | Tenacidade Implacável – reação, 5s, CD 240–220s | Marca de Guerra | Imortalidades extras viram escudo 20% HP | DR + ferocidade ≤ caps |
| Tiefling | Sangue infernal | Resistência Infernal (10s/45s, 25% DR fogo), Herança Sombria (darkvision 20 blocos) | Toque Ígneo – mana 15, melee 140%, CD 60–50s; Lâmina das Chamas – mana 30, 12s, CD 120–105s | Marca Infernal / Manopla Ígnea | Warlocks fogo convertem DR excedente em absorção 10% | Resistência fogo ≤ 45% |

## HUD e Placeholders
- Scoreboard: `{race_active}`, `{racial_uptime}`, `{racial_resist_x}`, `{racial_charges}`, `{racial_cd_main}`.
- Actionbar: mensagens para ativação/recarga de traits.

## Fluxo de Permissões
1. LuckPerms concede grupos `race_<nome>` e `race_<nome>.<sub>`. Subgrupos herdam base.
2. Permissões de SkillAPI (`psa.skill.*`), MythicMobs (`mythicmobs.item.*`) e Skript (`skript.command.*`) garantem acesso controlado.
3. Itens possuem NBT `race-lock` usado pelo Skript para validação adicional.

## Interações e Caps
- Resistências compartilham tabela de diminishing returns quando combinadas com itens/classe do mesmo tipo (fogo, veneno, físico etc.).
- Nenhuma raça ultrapassa +0.05 de velocidade, 45% de mitigação ou 25% de cura por golpe.
- Passivos condicionais ou com duração limitada mantêm uptime ≤ 60%, exigindo gerenciamento de bioma, vida ou tempo.

## Selecionando sua Raça
- **Defesa/Tanque:** Anões, Meio-Orc, Halfling Robusto – ideais para contenção e mitigação controlada.
- **Controle/Utilidade:** Elfos (Truque Arcano, Passos Feéricos, Luzes Feéricas), Gnomo da Floresta, Tiefling (Lâmina para zonar).
- **Suporte Social/Econômico:** Humanos, Meio-Elfo – bônus econômicos e auras híbridas.
- **Burst Ofensivo:** Draconato, Tiefling, Humano Variante – habilidades com janelas curtas mas fortes.

Consulte `docs/TESTPLAN_RACES.md` para validações PvE/PvP e `docs/BALANCE_SHEET_RACES.csv` para métricas numéricas detalhadas.
