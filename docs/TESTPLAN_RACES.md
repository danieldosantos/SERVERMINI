# Plano de Testes – Raças D&D 5e

Os testes abaixo validam equilíbrio PvE/PvP, integrações e limites. Executar cada cenário com kits de QA (`/kit_racial_<nome>`) e placeholders ativos.

## PvE
1. **Fortaleza Venenosa (Anão da Colina + Halfling Robusto)**
   - Ambiente: masmorra de veneno T2 com DoTs constantes.
   - Métricas: mitigação média ≤ 40%, uso de Fortitude Anã 1 vez por 90s, regen combinada ≤ 6%/s.
   - Verificar DR compartilhada de veneno entre raça, classe e itens.
2. **Câmara Sísmica (Anão da Montanha + Meio-Orc)**
   - Ambiente: boss que empurra a cada 12s.
   - Métricas: Postura de Pedra ativa 8s por 90s, Tenacidade Implacável dispara no máximo 1x por luta.
   - Confirmar knockback resist ≤ 75% e DPS de execute ≤ 10% do total.
3. **Ritual Elemental (Draconato + Tiefling)**
   - Ambiente: sala com rajadas de fogo e mobs elementais.
   - Métricas: Resistência combinada ≤ 45%, Sopro Dracônico limpa adds em até 2 casts por fase, Lâmina das Chamas usada 1 vez a cada 120s.
   - Verificar DR compartilhada com equipamentos elementais.
4. **Patrulha das Copas (Elfo da Floresta + Gnomo da Floresta)**
   - Ambiente: escolta em floresta com emboscadas furtivas.
   - Métricas: Passos Feéricos usado ≤ 2 vezes por minuto; Canto Cativante controla no máximo 6 mobs por uso.
   - Confirmar camuflagem combinada ≤ 60%.
5. **Conclave Diplomático (Humano, Humano Variante, Meio-Elfo)**
   - Ambiente: evento social com fases de combate leve.
   - Métricas: buffs econômicos aplicados apenas a missões civis; Harmonia Híbrida aumenta mitigação total do grupo para 42% no pico.
   - Validar troca de talentos a cada 180s sem quebrar CDs globais.

## PvP
1. **Duelo Sombras vs Sol (Drow vs Paladino Solar)**
   - Mapas: arena externa com exposição solar.
   - Métricas: Fragilidade à Luz aplica debuff de +8% dano recebido; Luzes Feéricas revela furtivos por 6–8s.
   - Garantir que Manto das Sombras não remove penalidade solar fora da área.
2. **Skirmish Forestal (Elfo da Floresta + Halfling Pés-Leves vs Patrulheiro Humano)**
   - Mapas: floresta fechada.
   - Métricas: velocidade total ≤ 0.05; evasão Halfling não dispara mais de 1 vez a cada 18s.
   - Verificar que camuflagem e invisibilidade não excedem 60% simultâneos.
3. **Arena Infernal (Tiefling vs Guerreiro Fogo)**
   - Mapas: arena de magma.
   - Métricas: Resistência Infernal + itens ≤ 45%; Toque Ígneo + Lâmina não produzem one-shot (TTK ≥ 10s).
   - Checar redução de bônus de fogo para PvP (Toque 120%, Lâmina 6–8%).
4. **Controle de Bandeira (Draconato, Gnomo da Rocha, Meio-Orc vs Trinca Mista)**
   - Objetivo: capturar ponto com rotações de 2 minutos.
  - Métricas: Engenho Gnomesco absorve ≤ 24% dano; Sopro Dracônico usado 1 vez por 60s; Tenacidade ativa no máximo 1 vez por rodada.
   - Avaliar se mitigação combinada permanece ≤ 45%.
5. **Operação Urbana (Humano Variante + Meio-Elfo + Tiefling vs Time Assassino)**
   - Mapa: cidade noturna com becos.
   - Métricas: Talento Variável alterna efeitos sem ultrapassar +8% dano PvP; Harmonia Híbrida mantém buffs a cada 90s; Resistência Infernal aplica DR com itens de fogo.
   - Confirmar que uptime de passivos permanece ≤ 60% com rotações corretas.

## Cenário Híbrido (Mix Classes Populares)
- **Assalto Subterrâneo (Drow + Ladino Sombrio + Guerreiro Tanque)**
  - Objetivo: limpar dungeon com fases de luz e escuridão.
  - Métricas: uso de Luzes Feéricas a cada 75s para revelar inimigos invisíveis; tank não ultrapassa 45% mitigação mesmo sob Manto das Sombras.
  - Garantir que debuff solar continue ativo fora da cúpula.

## Checklist de Integração
- LuckPerms: aplicar grupo base + subgrupo e verificar permissões de itens, Skills e Skript.
- MythicMobs: confirmar NBT `race-lock` bloqueia uso por outras raças.
- ProSkillAPI: garantir escalas T1–T4 e limites PvP/PvE distintos onde indicado.
- RacesEffects: monitorar placeholders `{racial_uptime}`, `{racial_cd_main}`, `{racial_resist_x}`, `{racial_charges}` no HUD durante testes.
- Skript: validar comandos rápidos (`/racial`, `/sopro_draco`, etc.) e kits QA.
