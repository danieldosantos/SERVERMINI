# Raça Humano (Base + Subraças)

## Identidade Geral
Os humanos são versáteis, resilientes e orientados à cooperação. A nova implementação integra RacesEffects, ProSkillAPI, MythicMobs, Skript e LuckPerms para oferecer perfis alternáveis, bônus contextuais e talentos configuráveis sem quebrar os limites globais de balanceamento.

### Visão Rápida
| Componente | Descrição |
|------------|-----------|
| Grupo LuckPerms base | `race_human` |
| Subgrupos | `race_human.standard`, `race_human.variant` |
| Itens raciais | VersatilityCharm, HumanBanner, HumanMedal (padrão), TalentToken & HumanSignet (variante) |
| Ativos principais | Versatilidade (perfil), Renome (buff de grupo), Esforço Coordenado (padrão), Talento (variante) |
| Placeholders HUD | `{racial_cd_main}`, `{racial_uptime}`, `{racial_charges}`, `{talent_choice}` |

## Núcleo Humano

### Passivos (RacesEffects)
- **Adaptabilidade Humana** – três perfis alternáveis com uptime ≤ 60% (ativados via Versatilidade):
  - *Tática*: -10% CD de habilidades não-ultimates por 12s (DR aplicada).
  - *Disciplinada*: +8% mitigação por 10s (respeita cap de 45%).
  - *Oportunista*: +10% crítico e +8% penetração por 8s (observa limite global de crítico).
- **Ambição Comunal** – +5% XP de profissões/serviços em vilas ou postos de guilda; expire se entrar em combate.

### Ativos (ProSkillAPI)
- **Versatilidade** (`Human_Versatility_Switch`) – troca de perfil fora de combate; 120–105s de CD por tier; aplica fadiga (-5% dano/6s).
- **Renome** (`Human_Renown_Shout`) – buff de 6 blocos (+8% velocidade, +10-12% resistência a empurrões por 8s); CD 75–65s.

### Fluxo de Alternância de Perfil
1. Jogador usa `/versatilidade` ou clica no VersatilityCharm fora de combate.
2. Skript verifica cooldown, atualiza metadados e envia o ProSkillAPI (`Human_Versatility_Switch` + `Human_Profile_*`).
3. RacesEffects aplica o perfil correspondente e atualiza HUD (`{metadata:human_profile}`).
4. Trocas subsequentes obedecem ao cooldown e ao requisito de segurança fora de combate.

## Subraça: Humano Padrão (`race_human.standard`)

### Passivos
- **Versado** – +1/+1 em dois atributos leves definidos pelo servidor (ex.: Vitalidade e Perícia). Sem exceder caps.
- **Produtividade** – +5% de regeneração de recurso de classe fora de combate (checado a cada 5s, uptime ≤ 60%).

### Ativo
- **Esforço Coordenado** (`Human_Coordinated_Effort`) – marca 1 aliado em 10 blocos, -10~12% CD de habilidades menores por 8s; CD 90–80s; DR compartilhado com Adaptabilidade Tática.

### Itens
- **VersatilityCharm** (compartilhado) – alterna perfis.
- **HumanBanner** – ativa Renome.
- **HumanMedal** – atalho visual/sonoro para Esforço Coordenado.

## Subraça: Humano Variante (`race_human.variant`)

### Passivos
- **Aptidão Seletiva** – +1 em um atributo leve (configurável via `/talento`, apenas staff pode resetar).
- **Talento** – escolha única entre:
  1. **Perito Pragmático** – +15~18% alcance de projéteis e +5~7% precisão por 8s, CD 75–60s.
  2. **Robustez Prática** – escudo de 120~165 pontos por 6s, CD 90–75s (DR com outras fontes de escudo).
  3. **Iniciativa Afiada** – +0.04 vel. (cap global +0.05) e resistência a empurrões por 4s, CD 75–60s.

### Ativo
- **Talento (Ativação)** – `/talento ativar` ou shift+clique no TalentToken dispara o pacote escolhido. `/talento` abre GUI se nenhum talento definido.

### Itens
- **TalentToken** – abre GUI e ativa talento.
- **HumanSignet** – atalho adicional para `/versatilidade` (qualquer variante).

## Integrações e Permissões
- **LuckPerms**: comandos no arquivo `races_groups.txt` criam grupos, heranças e permissões para itens/skills/comandos.
- **MythicMobs**: itens raciais no `races_items.yml` possuem `lp_required` (ex.: `race_human.variant`) e chamam skills/ comandos corretos.
- **Skript**: `races_cmds.sk` implementa `/versatilidade`, `/renome`, `/talento`, GUI de talentos, bloqueios de combate e sincronização de metadados.
- **PlaceholderAPI/Scoreboard**: HUD mostra perfil ativo, cooldowns e talento escolhido com placeholders atualizados via Skript.

## Boas Práticas & Sinergia
- **DR de Cooldown**: Adaptabilidade Tática + Esforço Coordenado verificam a flag `cooldown_minor` para evitar reduções acumulativas além dos caps.
- **Mitigação**: Perfil Disciplinada + classes defensivas aplicam `diminishing_returns: mitigation` (configurado em RacesEffects).
- **Velocidade**: Renome + Iniciativa Afiada respeitam o cap global +0.05. Skript monitora ativação simultânea e envia alertas se excedido.
- **Talentos & Classes**: Robustez Prática ativa `diminishing_returns: shielding`; Perito Pragmático não altera chance de crítico além do limite do servidor.

## Fluxos de QA
1. `/kit_racial_humano` entrega VersatilityCharm + HumanBanner + HumanMedal e reseta cooldowns.
2. `/kit_racial_humano_variante` inclui TalentToken + HumanSignet.
3. `/versatilidade` fora de combate alterna perfis em ordem Tática → Disciplinada → Oportunista.
4. `/talento` (variante) abre GUI se vazio; `/talento ativar` usa o talento escolhido.

## Referências Cruzadas
- Configuração RacesEffects: `plugins/RacesEffects/races/human.yml`
- Skills ProSkillAPI: `plugins/ProSkillAPI/skills/races/human/*.yml`
- Itens MythicMobs: `plugins/MythicMobs/Items/races_items.yml`
- Skript Glue: `plugins/Skript/scripts/races_cmds.sk`
- LuckPerms: `plugins/LuckPerms/races_groups.txt`
