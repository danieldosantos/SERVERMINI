# Test Plan – Raça Humano

## Objetivos
Garantir que a raça Humano (base, Padrão e Variante) respeite limites de uptime, caps globais e sinergia com classes ao longo de diferentes modos (PvE/PvP).

## Ambiente de Teste
- Servidor de QA com RacesEffects, ProSkillAPI, MythicMobs, Skript, LuckPerms, PlaceholderAPI.
- Contas de teste com permissões `race_human.standard` e `race_human.variant`.
- Ferramentas auxiliares: `/kit_racial_humano`, `/kit_racial_humano_variante`, PlaceholderAPI debug.

## Métricas Monitoradas
- Uptime dos perfis (meta `{racial_uptime}` ≤ 60%).
- Cooldowns (via `{racial_cd_main}` e logs ProSkillAPI).
- Mitigação/velocidade total (≤ 45% / +0.05).
- DR aplicado quando combinado com buffs de classe/itens.

## Cenários PvE
1. **Defesa de Aldeia** – Guerreiro humano padrão + arqueiro aliado; alternar perfis para segurar ondas; validar Ambição Comunal e Renome.
2. **Chefe Multi-fase** – Humano padrão com clérigo aliado; garantir troca de perfil fora de combate entre fases e que Esforço Coordenado respeite DR com habilidades de suporte do clérigo.
3. **Dungeon Urbano** – Humano variante (talento Perito) escolta NPC em vila; medir bônus de alcance/precisão e XP de profissões.
4. **Cerco à Guilda** – Humano variante (talento Robustez) em grupo defensivo com Paladino; monitorar escudos empilhados e aplicação de DR.
5. **Resgate Rápido** – Humano variante (talento Iniciativa) junto a Ladino; testar mobilidade + Renome em corridas com cronômetro, garantindo cap +0.05 de velocidade.

## Cenários PvP
1. **Duelo 1v1 (Burst)** – Humano variante (talento Robustez) vs. Assassino; verificar se escudo impede one-shot sem ultrapassar caps.
2. **Duelo 1v1 (Controle)** – Humano padrão vs. Mago com redução de cooldown; validar DR combinado entre Adaptabilidade Tática e Esforço Coordenado.
3. **Arena 3v3 Suporte** – Time com humano padrão como suporte; usar Renome em rotações e medir tempo médio de buff nos aliados.
4. **Arena 3v3 Mobilidade** – Humano variante (Iniciativa) + Bardo; confirmar que sprint + buffs do Bardo respeitam cap de velocidade e que cooldowns se mantêm ≥ 60s.
5. **Skirmish 5v5** – Dois humanos (padrão + variante) na mesma equipe; checar sinergia cruzada (esforço + talentos diferentes) e ausência de travamento de controle acima de 4s.

## Checklist de Aceite
- [ ] Versatilidade bloqueada em combate e troca apenas fora de combate.
- [ ] Perfil atual exibido no HUD e sincronizado após relog.
- [ ] Renome aplica buff apenas a aliados em 6 blocos e respeita caps de velocidade.
- [ ] Talento GUI bloqueia reescolha sem reset de staff.
- [ ] Talentos ativados refletem no placeholder `{talent_choice}`.
- [ ] Kits entregam itens corretos e resetam cooldowns.

## Observações
Documentar números de uptime, tempos de recarga e qualquer interação inesperada (por exemplo, classes que ignoram DR). Ajustar notas de balanceamento na planilha caso surjam discrepâncias.
