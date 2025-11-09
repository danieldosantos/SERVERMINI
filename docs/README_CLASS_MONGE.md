# Classe Monge (Minecraft + D&D 5e)

## Visão geral
- **Função:** lutador ágil de curta distância com mobilidade elevada, controle leve e defesas reativas.
- **Recursos:** combina **Estamina** (pool base 100 escalando até 130) com **cargas de Ki** (2/3/4/5 por tier). Estamina regenera 6/s fora de combate e 2/s em combate; cargas de Ki recarregam 1 a cada 45s fora de combate.
- **Postura ativa:** Postura de Foco garante +10% mitigação e +10% precisão enquanto reduz em 30% a regeneração de Estamina. Skript limita uptime efetivo a 60%.
- **Integrações:** PlaceholderAPI expõe `{stamina}`, `{ki_charges}`, `{combo}`, `{postura_active}` e `{cooldown_next}`. LuckPerms gateia itens e skills; MythicMobs fornece focos; RacesEffects aplica retornos decrescentes quando resistências raciais somam.

## Progressão por tier
| Tier | Destaques | Recursos |
|------|-----------|----------|
| T1 (nível ~1) | Mobilidade básica (Passo do Vento), primeira janela de burst (Rajada) e defesa reativa (Defletir). | Estamina 100, Ki 2 | 
| T2 (nível ~5) | Ganho de combo estável via Disciplina, acesso às primeiras técnicas de subclasse impactantes. | Estamina 110, Ki 3 |
| T3 (nível ~11) | Soft cap de controle/AoE nas subclasses e melhor sinergia de Postura com itens focais. | Estamina 120, Ki 4 |
| T4 (nível ~17) | Ultimates por subclasse, mitigação utilitária e combos prolongados. | Estamina 130, Ki 5 |

## Skills da classe base
| Skill | Tipo | Custo | Cooldown | Efeito | Observações |
|-------|------|-------|----------|--------|-------------|
| Rajada de Golpes | Ativa (Ki) | 1 carga | 45s | Abre janela de 3s para até dois golpes adicionais (80% dano cada). | `/golpe_ki` ou item *MonkPrayerBeads*.
| Passo do Vento | Ativa (Estamina) | 30 estamina | 45s | +0.05 velocidade, salto ampliado e redução de queda leve por 6s. | `/passo_vento` ou *MonkWindWraps*.
| Defletir Projéteis | Ativa (Estamina) | 25 estamina | 60s | Mitiga 60% de projéteis por 3s e reflete um projétil (120% dano). | `/defletir`.
| Postura de Foco | Ativa toggle | — | 6s (alternância) | +10% mitigação/precisão com -30% regen de Estamina. | `/postura` ou *MonkFocusBand*.
| Defesa sem Armadura | Passiva | — | — | Mitigação leve escalando com DEX+SAB (capado a 15%). | Calculado pelo Skript.
| Disciplina | Passiva | — | — | Ao esquivar/cancelar dano, restaura 10 de Estamina (CD 12s). | Gerido por eventos do Skript.

### Combos e HUD
- Cada acerto corpo-a-corpo gera stacks até 6 (variáveis `{monk.combo}`) com timeout de 6s.
- PlaceholderAPI alimenta scoreboard com Estamina, Ki, combo atual, Postura e próximo cooldown crítico.
- `/combo` exibe stacks atuais; `/kit_monge` entrega focais de teste e reseta HUD.

## Subclasses
### Caminho da Mão Aberta
- **Identidade:** controle de campo, empurrões e cura tática. Técnicas respeitam caps de 3–4s de controle com imunidade decrescente.
- **Skills:** Palmada Tranquilizante, Empurrão Harmonioso, Queda Suave, Mão que Cura, Palma Retumbante (ult 6 blocos/6s).
- **Itens focais:** `OpenHandWraps`, `SereneSash`, `PalmCharm`.

### Caminho da Sombra
- **Identidade:** furtividade e debuffs de visão/resistência. Teleporte requer luz ≤7, com neblina e marcações debilitantes.
- **Skills:** Passo das Sombras, Pelota de Fumaça, Golpe Escurecedor, Véu Silencioso, Sombra Vinculante (marca -12% resist acumulando até -24%).
- **Itens:** `ShadowCowl`, `SmokePellet`, `NightSilkBelt`.

### Caminho dos Quatro Elementos
- **Identidade:** conversão de Ki em pulsos elementais (fogo/gelo/vento/pedra) balanceados para PvE/PvP.
- **Skills:** Punho Ígneo, Onda de Gelo, Passo do Vórtice, Pele de Pedra, Tormenta dos Quatro (ult 8 blocos/10s alternando pulsos).
- **Itens:** `ElementScrollFire`, `ElementScrollFrost`, `ElementScrollGale`, `ElementStoneBand`, `ElementStormFocus`.

## Comandos e permissões
| Comando | Descrição |
|---------|-----------|
| `/golpe_ki` | Cast de Rajada de Golpes (verifica permissão `class_monk`). |
| `/passo_vento` | Cast de Passo do Vento. |
| `/defletir` | Ativa janela defensiva contra projéteis. |
| `/postura` | Alterna Postura de Foco com uptime controlado. |
| `/combo` | Mostra stacks de combo ativos. |
| `/estilo_openhand`, `/estilo_shadow`, `/estilo_elements` | Alternam subclasse via LuckPerms. |
| `/kit_monge` | Fornece kit de teste (itens MythicMobs) e limpa estados. |

LuckPerms configura grupos `class_monk` e subgrupos herdados (`class_monk.openhand`, `class_monk.shadow`, `class_monk.elements`), garantindo acesso somente às skills/itens correspondentes.

## Integrações adicionais
- **MythicMobs:** itens focais com PersistentData bloqueando uso por grupo. Eventos de clique executam `skillapi cast` e enviam actionbars temáticas.
- **Skript:** `monk_cmds.sk` controla combos, regeneração fora de combate, limites de Postura e efeitos avançados (raios elementais, marca da sombra etc.).
- **PlaceholderAPI/Scoreboard:** objetivos `monk_stamina`, `monk_ki`, `monk_combo`, `monk_posture`, `monk_cooldowns` exibem HUD dinâmico.
- **RacesEffects:** chamadas dedicadas (`raceseffects apply_resistance_exhaust`) evitam que mitigações raciais somem além do cap global de 45%.
- **CMI/EssentialsX:** `/kit_monge` depende de `/cmi heal` para reset rápido em ambientes de teste.

## Boas práticas / limites
- Caps globais aplicados: velocidade bônus total ≤ +0.05; mitigação combinada ≤45%; controle duro ≤4s com imunidade decrescente; cura instantânea ≤25% do HP alvo.
- Combinações raciais que já oferecem resistência recebem Exaustão de Resistência ao ativar Pele de Pedra ou similares.
- Ultimates possuem CDs entre 210–240s garantindo impacto sem quebrar PvP.

## Checklist de implantação
1. Importar arquivos `monk.yml` (classe) e subclasses no ProSkillAPI.
2. Copiar skills em `plugins/ProSkillAPI/skills/monk/`.
3. Registrar itens focais via MythicMobs (`monk_items.yml`).
4. Adicionar `monk_cmds.sk` ao Skript e recarregar (`/sk reload monk_cmds`).
5. Executar comandos de LuckPerms listados em `plugins/LuckPerms/monk_groups.txt`.
6. Validar HUD/PlaceholderAPI e sinergias raciais em conjunto com RacesEffects.
