# Plano de Testes – Classe Bardo

## PvE (10 cenários)
1. **Dungeon Tier 1 (Nível 1-4)** – Verificar Inspiração e Canção de Descanso em grupo de 4 jogadores; medir uptime <60% e regeneração de cargas fora de combate.
2. **Evento Mundo Aberto (Elite T2)** – Testar Orquestração Perfeita + Ritmo de Batalha com 6 jogadores; confirmar redução de cooldown combinada ≤25%.
3. **Boss Elemental (Resistência máx)** – Avaliar escudos de Coda Inspirador + Hino da Coragem para garantir mitigação <45% quando combinada com buffs raciais.
4. **Mini-boss Dispel** – Usar Balada dos Segredos contra mobs com buffs; medir remoção de 1 buff por alvo e impacto no DPS aliado.
5. **Canalização Ultimate Lore** – Ativar Sinfonia Onisciente durante raid T3; confirmar canalização 2s e duração dos buffs (15s) sem conflito com outras auras.
6. **Linha de Frente Valor** – Teste Marcha Recuada para kite de adds; medir deslocamento médio 5–6 blocos e confirmar ausência de travamento.
7. **Heal-over-time Glamour** – Refrão Sublime em batalha prolongada; contabilizar cura total (6% a cada 2s por 12s) e limpeza de debuffs.
8. **Mobilidade Grupo** – Passo Férico + Ritmo de Batalha em eventos com lava; checar teleporte de até 2 aliados e buff de velocidade resultante.
9. **Controle Tático** – Encanto Hipnótico em packs de mobs; confirmar duração 3s e respawn da DR.
10. **Economia de Mana** – Avaliar Virtuose em sessão longa; medir redução média de custo (≥9%) para confirmar aplicação do modificador.

## PvP (10 cenários)
1. **Arena 2v2 T1** – Inspiração alternada; verificar bônus de dano 8% e uptime controlado pelo consumo de cargas.
2. **Arena 3v3 (controle)** – Palavra Desconcertante sobre curandeiro inimigo; medir silêncio 4s e DR subsequente.
3. **Arena 4v4 (burst)** – Orquestração Perfeita + Ritmo de Batalha; garantir bônus combinado ≤20% de velocidade/tempo.
4. **Capture the Flag** – Marcha Recuada defensiva; observar reposicionamento e velocidade +0.03 sem atravessar paredes.
5. **Domination** – Brilho Deslumbrante em equipe adversária; verificar -15% precisão e cegueira 4s.
6. **Teamfight Glamour Ult** – Refrão Sublime em ponto de captura; medir cura total e remoção de debuffs a cada pulso.
7. **Resgate Valor Ult** – Cantiga dos Heróis após wipe parcial; garantir revive apenas de jogadores mortos <10s e bloquear em arenas ranqueadas (flag `bard.arena_block`).
8. **Kite vs Melee** – Encanto Hipnótico seguido de Passo Férico; confirmar root curto + teleporte sem stackar CC além do permitido.
9. **Controle de Buffs** – Balada dos Segredos removendo efeitos PvP (Escudos/Absorções); avaliar impacto na sobrevivência inimiga.
10. **Checagem HUD** – Monitorar scoreboard/placeholder em combate intenso; garantir atualização ≤5s e sincronismo com cargas reais.

Cada cenário deve registrar: composição, duração, métricas de DPS/cura (PlaceholderAPI), uptime médio das canções e feedback dos jogadores.
