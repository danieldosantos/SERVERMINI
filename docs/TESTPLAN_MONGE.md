# Plano de Testes – Classe Monge

## Cenários PvE (10 casos)
1. **T1 – Tutorial de Rajada:** medir DPS médio durante Rajada de Golpes em boneco (HP 200). Registre dano total, tempo para consumir 2 hits e Estamina restante.
2. **T1 – Mobilidade Passo do Vento:** cronometrar deslocamento em circuito 40 blocos com e sem skill; validar redução de dano de queda de 12 blocos (esperado ≤30% do dano base).
3. **T2 – Cavernas Rasas:** enfrentar 6 zumbis em corredor usando Defletir Projéteis vs esqueletos; avaliar dano mitigado (%) e número de reflexões bem-sucedidas.
4. **T2 – Queda Controlada (Open Hand):** saltar de 30 blocos com Queda Suave ativo; validar redução 80% e bônus de velocidade +0.02 sem romper cap global (+0.05 total com Passo do Vento).
5. **T2 – Passo das Sombras em penumbra:** teleporte entre pontos iluminância 0–7; garantir falha com mensagem se lux >7 e sucesso quando válido.
6. **T3 – Combo Sustentado:** simular rotação 60s alternando Rajada/Passo do Vento com disciplina ativa; capturar uptime de combo médio (alvo 4–6 stacks) e consumo total de Estamina/Ki.
7. **T3 – Controle em cone (Open Hand):** usar Empurrão Harmonioso contra mobs conjuradores (Evoker). Verificar interrupção da magia e empurrão 3 blocos.
8. **T3 – Pele de Pedra + Postura:** ativar ambos contra boss com dano constante. Garantir mitigação total ≤45% e aplicar exaustão caso raça tenha resistência nativa.
9. **T4 – Tormenta dos Quatro:** medir dano médio por pulso em 8 mobs (zumbis/pillagers), tempo de permanência e integridade do puxão leve. Checar cooldown (240s).
10. **T4 – Palma Retumbante:** avaliar dano acumulado (70%/s por 6s) e root de entrada 2s respeitando imunidade decrescente. Monitorar uptime total da aura ≤6s.

## Cenários PvP (10 casos)
1. **Duelos espelhados T1:** Monge vs Monge (sem subclasse) – registrar TTK, Estamina final e número de Rajadas por duelo.
2. **Defletir vs Arqueiro:** medir taxa de acertos refletidos e dano recebido com janela de 3s. Garantir que apenas um projétil retorna por ativação.
3. **Open Hand – Palmada + Empurrão:** testar combo para derrubar alvo de penhasco (6 blocos). Checar que controle total ≤4s e aplica DR subsequente.
4. **Shadow – Passo + Golpe:** avaliar burst ao teleportar atrás de oponente. Verificar cegueira 2s e respeito a cooldowns (60s / 45s).
5. **Elements – Punho Ígneo + Onda de Gelo:** testar sinergia contra alvo “molhado” (efeito chuva). Confirmar bônus +20% dano e queimadura 6%/s (cap de cura ≤25% do dano).
6. **Shadow – Véu Silencioso:** medir redução de agro/ruído vs mobs neutros durante PvP organizado; checar +15% chance de esquiva (logs de hits anulados).
7. **Open Hand – Cura aliada:** avaliar Mão que Cura em aliado 40% HP. Confirmar cura 18% e respeito ao cap (≤25%) sem overheal.
8. **Elements – Pele de Pedra em PvP:** combinar com Postura + raça resistente (ex.: Anão). Garantir mitigação ≤45% e aplicação de Exaustão de Resistência.
9. **Tormenta dos Quatro vs grupo:** medir DPS em 3 jogadores defensores. Confirmar pulso a cada 2s (10s totais) e puxão leve sem hard CC.
10. **Sombra Vinculante:** marcar alvo, contabilizar redução de resistência (–12% por acerto, máximo –24%) e tempo de expiração (12s). Validar remoção ao sair do range/combo.

### Métricas a capturar
- DPS médio e dano evitado (%).
- Tempo para matar (TTK) / sobreviver.
- Consumo de Estamina (gasto total e Estamina mínima atingida).
- Consumo e recarga de Ki (cargas usadas, tempo para recuperar).
- Uptime de Postura de Foco (%), combos médios (stacks) e cooldowns críticos.
- Eventos de exaustão de resistência disparados.
