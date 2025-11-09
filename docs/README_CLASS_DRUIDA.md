# Classe Druida (Minecraft + D&D 5e)

## Visão Geral
O Druida ocupa o arquétipo de **controle e flexibilidade**, alternando entre conjurações de terreno, suporte moderado e formas animais temporárias. A classe utiliza o recurso **Forma Selvagem** com cargas limitadas, dura 60s e recarrega uma carga a cada 180s (ganha +1 carga no Tier 3). A HUD integra PlaceholderAPI para exibir tempo restante, cargas e maior cooldown ativo.

- **Recurso primário:** Essência (mana) e cargas de Forma Selvagem.
- **Passivo global:** Afinidade Elemental (redução de 8% a um elemento rotativo, respeitando o limite total de 45%).
- **Identidade:** Controle de área adaptativo, suporte leve e metamorfose sem imunidades absolutas.

## Atributos por Tier
| Tier | Saúde | Mana | Velocidade | Destaques |
| ---- | ----- | ---- | ---------- | --------- |
| T1 (nível 1) | 26 HP | 40 mana | +0.00 | Acesso à Forma Selvagem, Vínculo Natural (HoT), Conjuração de Terreno básica. |
| T2 (nível 5) | 45 HP | 65 mana | +0.01 | Afinidade Elemental + Sentidos Selvagens (visão noturna intermitente). |
| T3 (nível 11) | 70 HP | 95 mana | +0.015 | +1 carga de Forma Selvagem, Maestria reduzindo cooldown, desbloqueio de círculos completos. |
| T4 (nível 17) | 98 HP | 130 mana | +0.02 | Todas as habilidades ativas, ultimates de 240s, sinergia máxima com itens focais. |

## Árvore de Skills (Classe Base)
- **Ativas:**
  - `Forma Selvagem`: transformação 60s, CD 180s (reduzido pelos tiers), +resistência e velocidade limitada a +0.05. Algumas habilidades exigem estar em forma.
  - `Vínculo Natural`: cura 3%/s por 4s (cap de 6%/s), remove queimaduras, +25% resistência a veneno.
  - `Conjuração de Terreno`: zona de 6 blocos por 8s adaptada ao círculo (raízes/névoa/trepadeiras) com comandos `/terra_*`, `/lua_*`, `/pastor_*`.
- **Passivas:**
  - `Afinidade Elemental`: redução elemental rotativa.
  - `Sentidos Selvagens`: visão noturna intermitente, rastreamento básico.
  - `Maestria em Forma Selvagem`: reduz CD em 10–20% e sincroniza segunda carga no Tier 3.

## Círculos (Subclasses)
### Círculo da Terra (Land)
- **Foco:** Terreno pesado, veneno, sustentação.
- **Habilidades-chave:** Pilares de Pedra (empurrão 3 blocos, 120% dano, CD 60s), Aura do Bosque (+regen/+res veneno, 12s, CD 90s), Solo Entrópico (lama 8s, -40% velocidade), Chicote de Vinhas (puxão 6 blocos + sangramento), Coração do Mundo (ULT 15s, +25% resistência + cura 4%/s).
- **Itens MythicMobs:** LandStoneStaff, LandEmeraldCharm, LandTotem.

### Círculo da Lua (Moon)
- **Foco:** Formas reforçadas, controle via medo.
- **Habilidades-chave:** Forma Ursina Primordial (15s, +30% vida, cleave 4 blocos), Forma Felina (+0.05 velocidade, críticos garantidos 10s), Rugido Lunar (medo 3s, -15% dano), Reversão Natural (dispell + cura 12%), Avatar da Lua (ULT 18s, combina formas, +15% dano/resist).
- **Itens MythicMobs:** MoonTotem, MoonClawRing, MoonCelestialOrb.

### Círculo do Pastor (Shepherd)
- **Foco:** Invocações espirituais e suporte em área.
- **Habilidades-chave:** Chamado do Lobo Espiritual (pet 12s, 80% dano/2s), Escudo Ancestral (200 pontos compartilhados + cura final), Vínculo Harmonioso (+15% cura recebida por invocações), Chuva Revigorante (4%/s por 8s), Conclave Primal (ULT 16s, urso/águia/cervo espirituais).
- **Itens MythicMobs:** ShepherdTotem, ShepherdCharm, ShepherdCircleStone.

## Comandos e Integrações
- `/forma`, `/terra_*`, `/lua_*`, `/pastor_*`, `/kit_druida` (Skript).
- **PlaceholderAPI:** `%skills_wildshape_time%`, `%skills_wildshape_charges%`, `%skills_druid_longest_cd%` alimentam HUD.
- **LuckPerms:** grupos `class_druid`, `class_druid.land`, `class_druid.moon`, `class_druid.shepherd` controlam acesso a skills, itens e comandos.
- **MythicMobs:** itens focais com PersistentData `classlock` e `skill` para ativar habilidades.
- **RacesEffects:** comandos automatizados aplicam multiplicador máximo 1,25× em duração/velocidade com diminishing returns.

## Limites e Balanceamento
- Forma Selvagem dura 60s, CD 180s (reduzível até ~144s via maestria) e nunca concede imunidade total.
- Passivos com uptime ≤60% (visão noturna alternada, camuflagem lunar por ticks alternados).
- Caps aplicados: velocidade total até +0.05, resistência total 45%, cura em área ≤6%/s, zonas de terreno de 6–8 blocos.
- Controle forte ≤3–4s (Rugido Lunar medo 3s, raízes iniciais 1.5s via Skript).
- Sinergias raciais ajustadas com diminishing returns (p.ex., elfo da floresta limita duração da camuflagem lunar a 1,25×).

## Itens de Teste
Use `/kit_druida` para receber focais básicos. Kits entregam apenas itens compatíveis com o círculo do jogador. Utilize `/cmi` ou `/essentials kit` apenas em ambientes de QA.

## Checklist de Integrações
- [x] Classe base `druid.yml` com recurso Forma Selvagem.
- [x] Subclasses `druid_land.yml`, `druid_moon.yml`, `druid_shepherd.yml` com árvores completas.
- [x] Skills em `plugins/ProSkillAPI/skills/druid/` alinhadas a cooldowns e limites.
- [x] Itens MythicMobs com gating por LuckPerms.
- [x] Script `druid_cmds.sk` cobrindo comandos, HUD e efeitos de terreno.
- [x] LuckPerms `druid_groups.txt` definindo herança e permissões essenciais.
