# Raça Meio-Elfo (Minecraft + D&D 5e)

Os meio-elfos combinam diplomacia humana com a finesse élfica. A implementação
abaixo cobre o núcleo de versatilidade e três heranças distintas, cada uma com
itens focais, habilidades ProSkillAPI e integrações MythicMobs/Skript.

## Visão Geral Rápida

| Variação | Passivos principais | Ativos (custo/duração/CD/área) | Item focal | Sinergias e restrições | Caps reforçados |
| --- | --- | --- | --- | --- | --- |
| Padrão – Versatilidade | Versatilidade Meio-Élfica (2 mini-bônus rotativos), Descendência Mista (12% reação encanto/medo) | **Tato Social** – 20 mana, 12s, 90–80s, raio 6; buff de mitigação/cura (secundário 50%) | HalfElfFeather | Alinha com Bardos/Paladinos mantendo mitigação ≤45%; velocidade bônus respeita limite +0.05 | Mitigação total 45%, velocidade +0.05, resistência mental 35% |
| Herança Alto Elfo | Astúcia Arcana (+5% eficiência mágica condicional), Estudo Rápido (slot de truque) | **Truque Arcano** – 14 mana, 45–55s; executa Faísca, Luz ou Mão (área padrão 6–7) | HighLoreSigil | DR compartilhada com classes arcanas; truques não ultrapassam dano de habilidades leves | Mitigação/precisão ≤ caps de classe; eficiência mágica ≤15% combinada |
| Herança Drow | Visão no Escuro 28 blocos, Sensibilidade à Luz (-5% precisão, -0.01 vel.), Afinidade Umbrática (+10% duração stealth) | **Luzes Feéricas** – 18 mana, 6–7s, 75s, raio 6–7; **Passo Sombrio** – 12 stamina, instantâneo, 90s, 6–8 blocos | DrowMoonbrooch | Penalidade solar garante contrajogo; Passo Sombrio exige luz ≤7; DR evita furtividade permanente | Velocidade +0.05, evasão reduzida ≤25%, mitigação projétil ≤45% |
| Herança Elfo da Floresta | Passo Silvestre (+0.02 velocidade/8s em biomas florestais), Sentidos Aguçados (+10% detecção) | **Camuflagem Silvestre** – 16 stamina, 10–12s, 90–80s, self; **Flecha do Bosque** – 12 stamina, 6s marca, 60–55s, projétil | GroveCharm | DR com Patrulheiro em alcance/dano de projéteis; camuflagem exige ≥60% blocos naturais | Velocidade +0.05, bônus projétil total ≤15%, uptime ≤60% |

### Escolha por Estilo de Jogo

- **Suporte Tático (Padrão – Versatilidade):** reforça cura/mitigação de aliados e
troca mini-bônus para responder a encontros. Ideal para paladinos, bardos e
curadores híbridos.
- **Controle/Utilidade Arcana (Herança Alto Elfo):** fornece truque personalizável
com escolha rápida entre dano leve, utilidade de luz ou manipulação. Excelente
para magos, feiticeiros ou bruxos que precisam de solução leve sem gastar slots.
- **Furtividade Situacional (Herança Drow):** combina visão no escuro com
teleporte curto e marcação anti-furtividade. Ideal para ladinos, monges sombrios
e exploradores subterrâneos; atenção à penalidade solar.
- **Mobilidade Natural (Herança Elfo da Floresta):** aceleração condicional e
camuflagem em biomas de floresta; ótima para patrulheiros, arqueiros e classes
baseadas em alcance móvel.

### PlaceholderAPI/HUD

- `{racial_cd_main}` mostra o cooldown principal da variação atual.
- `{racial_uptime}` rastreia Versatilidade/Passo Silvestre/Afinidade Umbrática.
- `{racial_resist_mental}` e `{racial_resist_fae}` indicam resistências ativas.
- `{racial_charges}` exibe cargas de habilidades raciais.

### Integrações

- **RacesEffects:** `plugins/RacesEffects/races/half_elf.yml` define passivos,
  reações condicionais, penalidades e vínculos com grupos LuckPerms.
- **ProSkillAPI:** habilidades em `plugins/ProSkillAPI/skills/races/half_elf/`
  fornecem escalas T1–T4 consistentes com caps globais.
- **MythicMobs:** itens focais em
  `plugins/MythicMobs/Items/races_half_elf_items.yml` restringem uso por NBT.
- **Skript:** `plugins/Skript/scripts/races_half_elf_cmds.sk` implementa
  comandos `/meioelfo_modo`, `/truque_arcano`, `/fae_lights`, `/shadow_step`,
  `/camuflagem_silvestre` e `/kit_racial_halfelf` com checagem de permissão.
- **LuckPerms:** scripts em `plugins/LuckPerms/races_half_elf_groups.txt`
  criam grupos, heranças e permissões de acesso a skills/itens.

### Restrições e Balanceamento

- Uptime de todos os passivos condicionais limitado a ≤60% pelo design
  (cooldowns internos e verificações de bioma/luz).
- Cooldown principal das habilidades raciais entre 45s e 90s conforme diretriz.
- Mitigação total respeita limite global de 45%; velocidade extra soma com DR.
- Resistências mentais aplicam diminishing returns ao combinar com classes ou
  itens, evitando imunidades absolutas.

### Fluxo de Ativação

1. Jogador aplica `race_half_elf` e recebe a Pena do Meio-Elfo.
2. Seleciona mini-bônus via `/meioelfo_modo escolher <opção>` (máximo 2 ativos).
3. Se optar por herança, staff aplica grupo `race_half_elf.<variação>` e entrega
   item focal correspondente.
4. PlaceholderAPI atualiza HUD com uptime, cargas e resistências.
5. QA executa `/kit_racial_halfelf` para receber itens de teste.

### Notas de Sinergia

- Buffs de suporte convertem excesso de mitigação em DR compartilhada, evitando
  ultrapassar 45% mesmo em grupos com Paladinos/Tanques.
- Truques arcanos respeitam custo de mana leve para coexistir com economias de
  recursos de classe.
- Passo Sombrio e Camuflagem Silvestre verificam luz/bioma antes de executar,
  evitando abuso em arenas que não seguem o tema.
