# Tabela de Magias do Artífice (Artificer)

Colunas: **Skill** | **Tipo** | **TIER** | **Custo** | **Duração** | **CD** | **Área / Alcance** | **Observações e Integrações**

## Magias Comuns da Classe

| Skill | Tipo | TIER | Custo | Duração | CD | Área / Alcance | Observações e Integrações |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Infundir Item | Ativa (infusão) | T1 | 1 Carga | 12s | 60s | Alvo único | Atribui via ProSkillAPI + Skript `/infundir`; escolhe módulo leve (+10% dano ou mitigação ou +0.03 velocidade); consome `infusoes_charges`. |
| Campo Rúnico Menor | Ativa (zona) | T1 | 20 Mana | 8s | 60s | Raio 5 blocos | Dano 25%/s por MythicMobs skill `artificer_rune_minor`; respeita limite de área base. |
| Sobrecarregar | Ativa (buff) | T1 | 1 Carga | 8s | 75s | Self + aliados 6 blocos | +15% velocidade de ataque/conjuração; cooldown track via PlaceholderAPI `{skills_next_cd}`. |
| Ferramentas de Tinker | Passiva | T1 | — | — | — | Equipamentos focais | +10% eficiência em itens/reatores (ProSkillAPI passive); incrementa sucesso em MythicMobs focais. |
| Campo Rúnico Estável | Ativa (zona) | T2 | 30 Mana | 10s | 75s | Raio 6 blocos | Upgrade do campo com 30% dano/s; ativa efeitos leves via MythicMobs `artificer_rune_mid`. |
| Bateria Arcana | Passiva | T2 | — | — | — | Self | Recupera 1 carga após 45s fora de combate; controlado por Skript monitorando tag `{infusoes_charges}`. |
| Contramedida Técnica | Ativa (defensiva) | T2 | 20 Mana | 6s | 60s | Self | -15% dano recebido; aplica mitigação via ProSkillAPI `defense_mod` (cap ≤45%). |
| Catalisador Elemental | Ativa (empower) | T2 | 1 Carga | Ataque seguinte | 75s | Arma atual | Próximo ataque causa +140% dano elemental; vincular a item focal com MythicMobs trigger `SkillDamage`. |
| Campo Rúnico Superior | Ativa (zona) | T3 | 40 Mana | 12s | 90s | Raio 7 blocos | Dano 40%/s; gera partículas reforçadas; respeita caps de área/dano. |
| Sobrecarga Coordenada | Ativa (buff em área) | T3 | 1 Carga | 10s | 120s | Aliados 6 blocos | +20% velocidade de ação para grupo; uptime ≤60%; ativa placeholder `{infusoes_charges}` por Skript. |
| Foco Estabilizado | Ativa (reduc. CD) | T3 | 30 Mana | 12s | 90s | Self | Reduz CDs das skills do Artífice em 10%; configurado via ProSkillAPI `cooldown_multiplier`. |
| Lente Arcano-Magnética | Ativa (revelação) | T3 | 20 Mana | 6s | 60s | Raio 10 blocos | Revela furtivos/invisíveis; usa partículas MythicMobs + scoreboard marcador. |
| Núcleo Excedente | Ultimate | T4 | — | 15s | 210s | Self | Enquanto ativo, custos de mana -20% e cargas não consomem `infusoes_charges`; indicado na HUD `{mana_arcana}`. |
| Campo de Impulso | Ativa (dome) | T4 | 40 Mana | 10s | 120s | Domo 8 blocos | Reduz projéteis recebidos 50%; MythicMobs dome + efeito leve. |
| Sobrecarga Final | Ativa (burst) | T4 | 1 Carga | 8s | 150s | Self | +25% dano e +0.05 velocidade (sem acumular); monitorar caps de velocidade global. |
| Reator Ascendente | Passiva | T4 | — | 25% escudo | 120s interno | Self | Ao cair <30% HP, aplica escudo equivalente a 25% HP; ProSkillAPI triggered passive com Skript cooldown. |

## Especialização: Alquimista

| Skill | Tipo | TIER | Custo | Duração | CD | Área / Alcance | Observações e Integrações |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Frasco Explosivo | Ativa (projétil) | T1 | 20 Mana | Queimadura 6s | 45s | 18 blocos | Projétil 120% dano + DoT 5%/s; MythicMobs projétil `alch_flask_explosive`. |
| Tônico Vivificante | Ativa (cura) | T1 | 1 Carga | Instantâneo | 60s | 6 blocos | Cura 12% HP + remove veneno; respeita limite de cura ≤25% do dano. |
| Catalisador Ácido | Ativa (zona) | T1 | 20 Mana | 6s | 60s | Raio 5 blocos | -10% resistência; aplica debuff via MythicMobs aura `acidic_field`. |
| Kit de Alquimia | Passiva | T1 | — | — | — | Self | +10% eficácia em frascos; aumenta duração de debuffs 0.5s máx. |
| Frasco de Gelo Rúnico | Ativa (projétil) | T2 | 25 Mana | Lentidão 6s | 60s | 18 blocos | -30% velocidade; aplica via Skript `freeze_tag` (limite global). |
| Elixir de Regeneração | Ativa (cura ao longo do tempo) | T2 | 1 Carga | 8s | 75s | Self ou aliado | Cura 4%/s (total 32% cap) com ícone HUD. |
| Catalisador Volátil | Passiva (sobrevivência) | T2 | — | 160% explosão | 90s interno | Self | Ao cair <40% HP, explode 160% dano; efeito leve em MythicMobs `volatile_catalyst`. |
| Névoa Corrosiva | Ativa (zona) | T2 | 30 Mana | 10s | 90s | Raio 6 blocos | -15% mitigação; acumula com Catalisador Ácido até limite 45%. |
| Bomba Eufórica | Ativa (suporte) | T3 | 30 Mana | 10s | 90s | 6 blocos | Aliados recebem +20% cura recebida; procs integrados via ProSkillAPI buff. |
| Serum Clarividente | Ativa (revelação) | T3 | 20 Mana | 8s | 75s | Raio 10 blocos | Revela inimigos ocultos; stack com Lente Arcano-Magnética. |
| Frasco Mutagênico | Ativa (buff arriscado) | T3 | 1 Carga | 12s | 105s | Self ou aliado | +15% dano mas -10% armadura; monitora mitigação global via Skript. |
| Perfusão Vital | Ativa (revive) | T3 | 35 Mana | Instantâneo | 240s | 4 blocos | Revive aliado caído com 30% HP; fora de combate. |
| Panaceia Suprema | Ultimate | T4 | 45 Mana | 12s | 240s | Zona 8 blocos | Cura 6%/s e limpa debuffs; respeita cura ≤25%/tick; usa MythicMobs área luminosa. |
| Gás de Dissolução | Ativa (debuff) | T4 | 40 Mana | 8s | 150s | Raio 6 blocos | -20% mitigação inimigos; combina com Névoa até cap 45%. |
| Tônico de Ascensão | Ativa (buff) | T4 | 1 Carga | 10s | 150s | Self ou aliado | +25% velocidade de ataque/conjuração; stack com Sobrecarregar ≤35% total haste. |
| Frasco Final | Ativa (explosão) | T4 | 40 Mana | Cegueira 3s | 150s | 18 blocos | Explosão 200% dano; aplica cegueira leve (DR PvP). |

## Especialização: Armeiro (Guardiã / Infiltradora)

| Skill | Tipo | TIER | Custo | Duração | CD | Área / Alcance | Observações e Integrações |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Mudar Modo | Ativa (toggle) | T1 | 1 Carga | Instantâneo | 45s | Self | Alterna Guardiã ⇆ Infiltradora; Skript `/modo` atualiza LuckPerms `class_artificer.armorer.<mode>`. |
| Punho Trovão | Ativa (melee) | T1 | 20 Mana | Stun 1.5s | 60s | 4 blocos | 140% dano; Guardiã apenas; MythicMobs impacto elétrico com stun DR. |
| Descarga Silenciosa | Ativa (burst) | T1 | 25 Mana | Silêncio 3s | 60s | 10 blocos | 120% dano; Infiltradora apenas; usa particulas sonoras. |
| Passo Amplificado | Ativa (mobilidade) | T1 | 1 Carga | 6s | 45s | Self | +0.03 velocidade; se Infiltradora, recebe camuflagem leve. |
| Campo Repulsor | Ativa (dome) | T2 | 30 Mana | 8s | 90s | Domo 6 blocos | Bloqueia projéteis em 50%; Guardiã destaque. |
| Manto de Fase | Ativa (mobilidade) | T2 | 1 Carga | 2s | 60s | Self | Infiltradora; invis curta + reposiciona 6 blocos. |
| Pancada Resonante | Ativa (melee) | T2 | 25 Mana | Instantâneo | 75s | 4 blocos | 150% dano + quebra escudo inimigo; sem stackar com outras quebras. |
| Reforço Estrutural | Ativa (defensiva) | T2 | 1 Carga | 8s | 90s | Self | +15% mitigação; aplica via ProSkillAPI `damage_reduction`. |
| Cinturão de Pulso | Ativa (implosão) | T3 | 30 Mana | Instantâneo | 90s | Raio 5 blocos | Puxa levemente inimigos; sem jogadas letais. |
| Calibração Óptica | Ativa (revelação) | T3 | 25 Mana | 10s | 75s | Raio 10 blocos | Detecta furtivos; compartilha CD com sensores. |
| Malha Condutora | Ativa (buff aliado) | T3 | 30 Mana | 10s | 120s | 6 blocos | Aliados ganham +15% penetração; integra MythicMobs aura. |
| Núcleo Arcano | Ativa (defensiva) | T3 | 1 Carga | 12s | 210s | Self | +20% mitigação + pulsos 60%/2s; Guardiã foco, ultimate auxiliar. |
| Sobrecarga Total | Ultimate | T4 | — | 12s | 240s | Self | Ativa todos módulos simultaneamente; custo zero durante efeito; combina com `Sobrecarga Final`. |
| Escudo Prismático | Ativa (reflexão) | T4 | 40 Mana | 8s | 120s | Self | Reflete 25% dano recebido; respeita cap 45%. |
| Estabilizador Final | Ativa (tenacidade) | T4 | 1 Carga | 10s | 150s | Self | Imunidade a empurrões/derrubões; guard rails anti-abuso. |
| Poder de Reator | Ativa (burst) | T4 | 1 Carga | 8s | 150s | Self | +25% dano +0.05 velocidade; usado preferencialmente por Infiltradora. |

## Especialização: Ferreiro de Batalha (Battle Smith)

| Skill | Tipo | TIER | Custo | Duração | CD | Área / Alcance | Observações e Integrações |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Invocar Defensor de Aço | Ativa (summon) | T1 | 1 Carga | Persistente | 120s | 10 blocos | Conjura MythicMobs `SteelDefender`; HUD `{defender_hp}` atualizado via PlaceholderAPI. |
| Sintonizar Arma | Ativa (buff) | T1 | 25 Mana | 12s | 60s | Self ou aliado | +15% dano +20% penetração; ProSkillAPI buff com ícone. |
| Comando Reparar | Ativa (pet cura) | T1 | 1 Carga | 6s | 60s | Defensor alvo | Cura 20% HP e -20% dano recebido; uso via `/defensor reparar`. |
| Vínculo de Aço | Passiva | T1 | — | — | — | Self | +10% mitigação quando Defensor próximo (≤8 blocos). |
| Interferência | Ativa (cone) | T2 | 30 Mana | 8s | 75s | Cone 5 blocos | Lentidão 40% e -10% dano; mitiga via MythicMobs `steel_interference`. |
| Malho Impactante | Ativa (melee) | T2 | 25 Mana | Instantâneo | 60s | 4 blocos | 150% dano + knockback leve; evita lançar jogadores longe. |
| Reforço de Forja | Ativa (defensiva) | T2 | 20 Mana | 10s | 75s | Self | +20% resistência a sangramento/veneno; integra RacesEffects. |
| Sincronia Defensiva | Ativa (pet buff) | T2 | 1 Carga | 8s | 90s | Defensor | Defensor copia mitigação atual do Artífice; respeita limite 45%. |
| Ordem de Guardião | Ativa (zona defensiva) | T3 | — | 12s | 210s | Raio 8 blocos | Defensor cria guarda reduzindo 25% dano aliado; ProSkillAPI aura + MythicMobs stance. |
| Golpe de Fornalha | Ativa (melee) | T3 | 30 Mana | Queimadura 6s | 75s | 4 blocos | 180% dano + DoT; sem stackar além do limite. |
| Lança-Calor | Ativa (feixe) | T3 | 35 Mana | 4s | 90s | Linha 6 blocos | 60%/tick; exige canalização com partícula térmica. |
| Forja Viva | Ativa (pet cura) | T3 | 0 Mana | 8s | 105s | Defensor | Cura 3% HP/s do Defensor; custo zero porém CD longo. |
| Protocolo Guardião | Ultimate | T4 | — | 8s | 240s | Defensor | +50% HP, provoca inimigos; HUD piscando `{defender_hp}`. |
| Golpe Titânico | Ativa (cleave) | T4 | 40 Mana | Instantâneo | 120s | 120°/3 blocos | 220% dano cleave; aplica knockback moderado. |
| Ferrita Arcana | Ativa (buff) | T4 | 1 Carga | 10s | 150s | Self | +25% penetração; indicado para burst PvE. |
| Núcleo Blindado | Ativa (escudo) | T4 | 1 Carga | 10s | 150s | Self | Escudo 30% HP; mostra na HUD via PlaceholderAPI `{defender_shield}`. |

## Integrações Gerais

- **ProSkillAPI**: define escalas de dano/mitigação por TIER e gere consumo de Mana Arcana (base 100 +25/Tier). Habilidades com cargas usam recurso personalizado `infusoes_charges`.
- **MythicMobs**: fornece projéteis, domos, construtos (Steel Defender) e efeitos visuais leves (`artificer_items.yml`, `artificer_skills.yml`).
- **Skript**: comandos `/infundir`, `/modo`, `/frasco`, `/defensor`, binds de clique e reset de cargas. Controla passivas `Bateria Arcana`, `Catalisador Volátil`, triggers de ultimates.
- **LuckPerms**: permissões `class_artificer`, `class_artificer.alchemist`, `class_artificer.armorer`, `class_artificer.battlesmith` para gating de skills/itens.
- **PlaceholderAPI**: HUD exibe `{infusoes_charges}`, `{mana_arcana}`, `{defender_hp}`, `{defender_shield}` e `{skills_next_cd}` para monitorar recursos.
- **Balanceamento**: Cooldowns chave ≥45s, situacionais ≥90s, ultimates 180–240s; velocidade adicional ≤0.05; mitigação total ≤45%; curas respeitam ≤25% do dano; áreas dentro de 6–8 blocos.
