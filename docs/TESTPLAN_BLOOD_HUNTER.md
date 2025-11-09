# Plano de Testes – Blood Hunter

Este plano cobre 10 cenários PvE e 10 PvP para validar balanceamento, consumo de Hemocraft, auto-dano e identidade das ordens.

## Métricas a coletar
- **DPS médio e pico** (usar PlaceholderAPI/Stat trackers)
- **TTK** (tempo para abater alvo)
- **Auto-dano total** e valor máximo por ativação
- **Consumo / regeneração de Hemocraft**
- **Uptime de ritos/aura/mutágeno/lican** (% do tempo)
- **Controle aplicado** (duração e alvo)

## Cenários PvE
1. **Base T1 vs. Esqueleto Elite (nível 1):** ativar Rito Carmesim, monitorar auto-dano (≤8%) e DPS em alvo único.
2. **Base T2 vs. grupo de zumbis (nível 5):** alternar Marca de Presa entre alvos e verificar refresh de HUD.
3. **Ghostslayer T3 vs. Fantasma (nível 11):** usar Rito do Espectro + Estaca Espectral e medir TTK reduzido.
4. **Ghostslayer T4 vs. boss morto-vivo:** rodar Anátema dos Mortos e confirmar aura ≤60% uptime no encontro.
5. **Profane Soul T2 vs. conjurador NPC:** aplicar Maldição do Patrono + Contrafeitiço (silêncio ≤2s) e verificar imunidade decrescente.
6. **Profane Soul T4 vs. enxame de mobs:** manter Rasgo do Vazio ativo por 10s e confirmar puxão leve (sem impedir movimento completo).
7. **Mutant T2 vs. besta grande:** ativar Mutágeno (ciclo de fórmulas) e garantir penalidades corretas, medindo DPS e auto-dano.
8. **Mutant T4 vs. boss com sangramento:** executar Catalise Suprema e acompanhar sangramento adicional + redução de CD percebida.
9. **Lycan T3 vs. matilha:** usar Forma do Caçador + Rastreio Sanguíneo e monitorar vulnerabilidade (+10% dano recebido) e lifesteal ≤25% do dano.
10. **Lycan T4 vs. evento noturno:** ativar Luar Carmesim e assegurar que buff de ataque aliados não excede caps globais.

## Cenários PvP
1. **Duelo Base T1 vs. Guerreiro:** medir DPS e auto-dano do Rito Carmesim, garantindo que não existe one-shot.
2. **Base T2 vs. Ladino:** usar Concentração de Sangue, observar ganho de penetração e fatiga (-10% dano por 6s).
3. **Ghostslayer vs. Necromante:** validar redução de dano necrótico (25%) e que medo do Anátema dura ≤2s com imunidade.
4. **Ghostslayer vs. Assassino invisível:** checar revelação da Marca de Presa (10s) e controle parcial.
5. **Profane Soul vs. Mago:** executar Contrafeitiço Profano dentro de 2s e medir silêncio aplicado.
6. **Profane Soul vs. Paladino:** medir redução de resistência (-12%) e que auto-dano não excede 10% em tiers altos.
7. **Mutant vs. Bárbaro:** alternar mutágenos para counterplay, monitorar penalidade ativa e consumo de cargas.
8. **Mutant vs. Clérigo:** utilizar Coágulo Alquímico para remover sangramentos, garantindo cura ≤25% dano.
9. **Lycan vs. Ranger:** aplicar Uivo Aterrador (2s) e Investida Predatória, verificando mitigação condicional em alvo marcado.
10. **Lycan vs. Warlock:** avaliar uptime combinado de Forma do Caçador + Luar Carmesim (deve ficar ≤60%) e vulnerabilidade.

## Procedimentos Gerais
- Habilitar logs de PlaceholderAPI para capturar valores `{hemocraft}` e timers.
- Utilizar `/kit_blood` para equipar focais antes de cada cenário.
- Registrar cooldowns reais e comparar com especificações (45s/60s/90s/210s).
- Validar compatibilidade com RacesEffects ativando personagens com resistências de fogo/frio/necrose e observando exaustão aplicada.
- Documentar quaisquer desvios >5% nos limites de auto-dano, mitigação ou velocidade para ajustes futuros.
