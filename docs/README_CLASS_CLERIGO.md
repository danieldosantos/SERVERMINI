# Classe Clérigo (Minecraft + D&D 5e)

## Visão Geral
O Clérigo é um suporte híbrido dedicado a manter o grupo vivo através de curas consistentes, mitigação temporária e utilidade adaptável. A classe utiliza mana como recurso primário e **cargas de Canalizar Divindade** como recurso secundário, garantindo decisões táticas a cada encontro.

- **Função:** Suporte / Curador com mitigação utilitária
- **Recursos:** Mana crescente por tier e 1–2 cargas de Canalizar Divindade (T1=1, T3+=2)
- **Equipamentos focais:** Símbolos, maças, lanterna e itens temáticos fornecidos via MythicMobs
- **Cooldowns chave:** Palavra Curativa (30s alvo), Proteção Sagrada (60–75s), Aura de Fé (90–105s), Canalizar Divindade (120s compartilhado)

## Progressão por Tier
| Tier | Nível PHB | Desbloqueios principais |
|------|-----------|-------------------------|
| T1   | 1–4       | Palavra Curativa, Proteção Sagrada, recursos base |
| T2   | 5–10      | Aura de Fé, escolha de Domínio, Canalizar Divindade |
| T3   | 11–16     | 2ª carga de Canalizar, upgrades de aura e escudo |
| T4   | 17–20     | Ultimates de domínio e maximização de mitigação |

## Skills Base
- **Palavra Curativa:** cura percentual instantânea e limpa um debuff leve, respeitando CD individual de 30s por alvo.
- **Proteção Sagrada:** escudo escalável (120–240) por 12s.
- **Aura de Fé:** aura ativada manualmente (12s/90s) oferecendo mitigação até 15% com diminishing returns.
- **Canalizar Divindade:** consome cargas para ativar efeitos específicos do domínio, com CD interno de 120s.

## Domínios (Subclasses)
### Vida
Foco máximo em cura e estabilidade defensiva.
- Fonte Restauradora, Escudo de Fé, Toque Purificador, Bênção Abundante e Milagre Vital (ressurreição 60% HP + 4s invuln). 
- Ideal para raças robustas (humanos/ anões). Caso a raça já ofereça resistência, a aura respeita o **cap de 45%** via diminishing returns.

### Guerra
Ofensivo e agressivo, amplificando a linha de frente.
- Golpe Divinamente Guiado, Estilhaço Consagrado, Estandarte Sagrado, Marcha do Zelote e Coroa do Campeão.
- Sinergias com raças combativas recebem controle para não ultrapassar mitigação total.

### Crepúsculo
Controle de campo e proteção contra projéteis.
- Manto do Poente, Raio Crepuscular, Refúgio Noturno, Bruma Reconfortante e Eclipse Sagrado.
- Combina bem com raças de visão noturna, sem remover fraquezas inerentes.

## Itens Focais
Itens criados via `plugins/MythicMobs/Items/cleric_items.yml` garantem ativação rápida das habilidades e exigem permissões específicas de LuckPerms.

- **Vida:** LifeHolySymbol, LifeGuardianMace, LifePrayerBeads, LifeSanctuaryUrn, LifeDivineRelic
- **Guerra:** WarSacredHammer, WarHolySpear, WarBattleBanner, WarWarhorn, WarCelestialCrown
- **Crepúsculo:** TwilightLantern, TwilightOrb, TwilightSanctum, TwilightCenser, TwilightDiadem

Cada item verifica o grupo correto antes de disparar comandos Skript que chamam as skills no ProSkillAPI.

## Comandos
- `/cura`, `/escudo`, `/aura` – Acessos rápidos às skills básicas
- `/canalizar <efeito>` – Consome carga de Canalizar Divindade para o efeito desejado
- `/cleric_cast <alias>` – Utilitário silencioso para integrações de itens
- `/kit_clerigo` – Entrega itens focais de teste (via CMI/EssentialsX)

## HUD & Integrações
- PlaceholderAPI fornece `%skillapi_mana%`, `%skillapi_resource_divinity%` e cooldowns chave para action bar.
- Scoreboard/Action bar atualiza a cada 5s exibindo mana, cargas e cooldown principal.
- RacesEffects: flags registradas para controlar sinergias e aplicar diminishing returns quando necessário.

## Limitações & Salvaguardas
- Nenhuma imunidade dura ultrapassa 4s (Milagre Vital = 4s).
- Canalizar Divindade possui trava de 120s compartilhada entre domínios.
- Ultimates seguem CD ≥210s e cura máxima ≤60% HP alvo.
- Scripts bloqueiam Milagre Vital em arenas (regiões com `arena` no nome) para evitar abuso em PvP.
