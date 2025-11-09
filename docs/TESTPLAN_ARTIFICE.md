# Test Plan – Classe Artífice

## Objetivos
Validar usabilidade, balanceamento e integrações (ProSkillAPI, MythicMobs, Skript, LuckPerms, RacesEffects) para a classe Artífice e subclasses.

## Ambiente
- Servidor de testes com ProSkillAPI, MythicMobs, Skript, PlaceholderAPI, RacesEffects, LuckPerms.
- Habilitar scoreboard com placeholders `{infusoes_charges}`, `{mana_arcana}`, `{cooldown_next}`, `{defender_hp}`.
- Conta com permissões adequadas (`class_artificer` + subgrupo correspondente).

## Métricas Observadas
- DPS médio e pico.
- Cura total e por segundo.
- Tempo até matar (TTK) do Defensor e do Artífice.
- Uptime de buffs (infusões, Sobrecarregar, núcleos/tonics).
- Consumo de Mana Arcana / recarga de Infusão.
- Cumprimento de caps (velocidade, mitigação, cura por golpe).

## Cenários PvE (10)
1. **Campo Rúnico + Sobrecarregar vs enxame zumbi** – medir DPS somado e uptime de Sobrecarregar ≤60%.
2. **Infundir Item em tanque frontliner** – verificar duração 12–20 min e consumo de cargas/regen out-of-combat.
3. **Armeiro Guardião: Punho Trovão + Campo Repulsor** – controlar knockback e mitigação, garantir projéteis reduzidos 50% por 8 s.
4. **Armeiro Infiltrador: Passo Amplificado em dungeon stealth** – confirmar boost de +0.05 velocidade, invis de 2–3 s.
5. **Núcleo Arcano vs elite** – medir mitigação adicional e dano pulsante (60%/2s) sem ultrapassar limites de área.
6. **Battle Smith: invocar Defensor e mantê-lo 5 minutos** – checar HUD `{defender_hp}`, IA seguindo/atacando, TTK razoável.
7. **Comando Reparar sob focus** – validar cura 20% + redução 20% por 6 s, sem exceder caps.
8. **Interferência cone contra mobs ranged** – confirmar Lentidão 40% e -10% dano, sem bug visual.
9. **Alquimista: Frasco Explosivo + Nuvem Corrosiva** – medir dano base + DoT, aplicar exaustão de resistência se alvo com buff racial.
10. **Panaceia em raid boss** – monitorar cura 5%/s, cleanse e resistência 10% sem acumular com outras mitigações além do cap.

## Cenários PvP (10)
1. **1v1 espadachim vs Artífice base** – avaliar pacing, verificar ausência de one-shot, consumo de cargas.
2. **Armeiro Guardião defendendo ponto** – testar Campo Repulsor contra arqueiros, certificar que mitigação total ≤45%.
3. **Armeiro Infiltrador gank** – garantir silêncio de 3 s e que burst não supera 30% HP alvo saudável.
4. **Battle Smith duelo com Defensor ativo** – medir tempo para abater o construto; garantir sem escalonamento infinito.
5. **Battle Smith suporte ranged** – aplicar Sintonizar Arma em aliado; verificar buff termina após 12 s.
6. **Alquimista duelo longo** – monitorar consumo de Mana Arcana, DoT de Frasco não stacka além de 6 s.
7. **Tônico Acelerante em teamfight** – checar somatório com Sobrecarregar, mantendo buff total ≤35% haste.
8. **Panaceia defensiva** – garantir que cura 5%/s não excede 25% por golpe e cleanse remove apenas venenos/queimaduras.
9. **Infundir Item em raça com resistência** – confirmar RacesEffects aplica exaustão e evita cap excedido.
10. **Switch rápido de modos (Armeiro) + Sobrecarregar** – validar cooldown 45 s no toggle e 90 s no buff, sem spam.

## Critérios de Sucesso
- Todos os comandos e skills executam sem erro nos logs.
- Placeholders atualizados após cada uso relevante.
- Constructo respeita FriendlyFire off e responde a `/defensor` (teleporte se vivo).
- Nenhum teste viola caps de velocidade/mitigação/cura estabelecidos.
- Jogadores não conseguem ativar itens/skills sem pertencer ao grupo LuckPerms correspondente.
