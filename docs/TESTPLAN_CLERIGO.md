# Plano de Testes – Classe Clérigo

## Objetivos Gerais
- Validar cooldowns, custos de mana e consumo de cargas.
- Confirmar que a cura/mitigação não ultrapassa limites definidos.
- Garantir que itens MythicMobs respeitam permissões LuckPerms.
- Assegurar bloqueios de PvP (Milagre Vital) e diminishing returns.

## Pré-requisitos
1. Plugins carregados: ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI, CMI/EssentialsX, RacesEffects.
2. Jogadores de teste com raças diferentes (humano, anão, elfo, orc).
3. Mundo de arena com regiões marcadas contendo `arena` no nome para validar bloqueios.
4. Perfil com níveis correspondentes aos tiers T1–T4.

---

## Cenários PvE (10)
1. **T1 - Palavra Curativa**
   - Passos: Aplicar veneno leve no tanque; usar `/cura`.
   - Métricas: Cura ≥14%, debuff removido, CD por alvo iniciado.

2. **T2 - Proteção Sagrada + Aura de Fé**
   - Passos: Ativar escudo em aliado; após 10s ativar aura.
   - Métricas: Escudo absorve 120+ dano; aura dura 12s com CD ≥90s.

3. **T2 - Canalizar Vida (Fonte Restauradora)**
   - Passos: Gastar 1 carga via `/canalizar bencao`.
   - Métricas: Carga reduzida, cura 18% + debuff removido.

4. **T3 - Escudo de Fé + Bênção Abundante empilhados**
   - Passos: Lançar ambos em grupo.
   - Métricas: Resistência total ≤45%; bônus de cura 20% por 12s.

5. **T3 - Toque Purificador em enxame de veneno**
   - Passos: Aplicar veneno/withering em grupo; lançar skill.
   - Métricas: Debuffs removidos; HoT residual 5% aplicado.

6. **T3 - Marcha do Zelote em masmorra**
   - Passos: Buff em aliados correndo corredor.
   - Métricas: Velocidade +0,04; KB resist 40%; dura 8s.

7. **T3 - Manto do Poente versus arqueiros**
   - Passos: Spawn 3 skeleton archers; usar Manto.
   - Métricas: Dano recebido reduzido ≈25%; visão noturna aplicada.

8. **T4 - Bruma Reconfortante**
   - Passos: Em batalha longa, medir cura total (30% em 6s).
   - Métricas: Remoção de medo/confusão; sem ultrapassar caps com raças.

9. **T4 - Coroa do Campeão**
   - Passos: Aliados atacam golem por 12s.
   - Métricas: Dano radiante adicional 50%, cura 5% por acerto, CD ≥210s.

10. **T4 - Eclipse Sagrado**
    - Passos: Confronto com mobs em área 8 blocos.
    - Métricas: Visão inimiga reduzida, cura recebida +20%, duração 15s.

---

## Cenários PvP (10)
1. **Kit & Permissões**
   - Passos: Jogador sem permissão tenta usar itens; verificar bloqueio.
   - Métricas: Sem permissão → mensagem de erro; com permissão → funcionam.

2. **Canalizar CD Global**
   - Passos: Usar `/canalizar golpe` duas vezes seguidas.
   - Métricas: Segunda tentativa bloqueada por 120s.

3. **Palavra Curativa vs anti-heal**
   - Passos: Adversário aplica efeito de redução de cura; usar `/cura`.
   - Métricas: Cura respeita penalidade e não excede 60%.

4. **Proteção Sagrada + Raça com Resistência**
   - Passos: Jogador anão (resist 20%) recebe skill.
   - Métricas: Resultado final ≤45% mitigação.

5. **Escudo de Fé Stack Check**
   - Passos: Tentar empilhar com aura/resist racial.
   - Métricas: Log confirma diminishing returns aplicado.

6. **Golpe Divinamente Guiado Burst**
   - Passos: Buff aliado assassino; medir dano crítico.
   - Métricas: Dano adicional 30% sem ignorar armadura extra.

7. **Refúgio Noturno vs Flechas**
   - Passos: Atirador dispara; verificar bloqueio total durante 8s.
   - Métricas: Sem flechas atravessando domo.

8. **Milagre Vital em Arena**
   - Passos: Matar aliado dentro de região `arena`; tentar `/canalizar milagre`.
   - Métricas: Skill bloqueada, mensagem informada.

9. **Bruma Reconfortante e Controle Mental**
   - Passos: Adversário usa efeito de medo; lançar bruma.
   - Métricas: Efeito removido imediatamente.

10. **Eclipse Sagrado Visibilidade**
    - Passos: Adversário relata visão reduzida; comparar com logs.
    - Métricas: Visão reduzida ~70%; cura aliada aumentada 20%.

## Critérios de Sucesso
- Todas as skills carregam sem erros em `/skillapi reload` e `/mythicmobs reload`.
- Testes PvE/PvP atingem métricas definidas sem ultrapassar caps.
- A HUD apresenta valores consistentes com consumo/recarga real.
- Bloqueios e permissões evitam abuso cross-classe.
