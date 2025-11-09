# Blood Hunter — Lista de Magias por Tier

Este documento estrutura as magias da classe **Blood Hunter** para uso em servidores Minecraft com temática D&D 5e, integrando os plugins ProSkillAPI, MythicMobs, Skript, LuckPerms e PlaceholderAPI. Os custos e parâmetros respeitam as diretrizes de balanceamento fornecidas.

## Diretrizes Gerais

- **Recurso principal:** Hemocraft (base 100, +25 por Tier desbloqueado).
- **Auto-dano máximo:** 12% do HP no T1/T2 e 15% no T3/T4. As habilidades não reduzem o usuário abaixo de 1 HP.
- **Tempo de Recarga:** Habilidades chave ≥ 45 s, situacionais ≥ 90 s e Ultimates entre 180–240 s.
- **Áreas de efeito:** Limitadas entre 5 e 8 blocos, quando aplicável.
- **Controle de grupo:** Medo e silêncio até 3–4 s com imunidade decrescente.
- **Tempo ativo de passivas:** ≤ 60%.
- **Permissões:** `class_blood_hunter` é requisito geral; cada Ordem adiciona `class_blood_hunter.<order>`.
- **HUD PlaceholderAPI:**
  - `%bloodhunter_hemocraft%`
  - `%bloodhunter_marca%`
  - `%bloodhunter_mutageno%`
  - `%bloodhunter_licantropia%`

Cada magia abaixo inclui sugestões de cadastro no ProSkillAPI (ID, custo, auto-dano, recarga, duração, área e escalas), itens focais MythicMobs, binds Skript e observações relevantes.

---

## Magias Comuns da Classe
Permissão: `class_blood_hunter`. Itens focais padrão MythicMobs: `ritual_dagger`, `blood_tome`.

### Tier 1 (T1)

| ID (ProSkillAPI) | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas (MythicMobs / Skript) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `bh_rito_carmesim` | Rito Carmesim | Ativa | 30 | 8% HP | 60 s | 120 s buff / arma | +3% dano elemental por nível | Item: `ritual_dagger`; comando `/rito`. |
| `bh_marca_presa` | Marca de Presa | Ativa | 20 | — | 45 s | 10 s alvo | +1% dano por nível | Usa talismã `scarlet_brand`; HUD marca ativa. |
| `bh_concentracao_sangue` | Concentração de Sangue | Ativa | 20 | — | 75 s | 8 s buff | +1% penetração por 2 níveis | Bind `/concentrar`; aplica debuff -10% dano por 6 s após expirar. |
| `bh_sangue_frio` | Sangue Frio | Passiva | — | — | Interno 45 s | 6 s efeito | +1% mitigação por 3 níveis | Pró-cast com `blood_tome`. |

### Tier 2 (T2)

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `bh_rito_aprimorado` | Rito Aprimorado | Modificador | +10 adicional ao Rito | +2% HP total | — | — | +5% dano elemental / nível | Upgrade vinculado ao `/rito`. |
| `bh_ferir_alma` | Ferir a Alma | Ativa | 25 | — | 60 s | Golpe alvo | +5% sangramento / 2 níveis | Usa `ritual_dagger`. |
| `bh_marcacao_cruenta` | Marcação Cruenta | Ativa | 15 | — | 60 s | 6 s visão | — | Efeito MythicMobs de partículas vermelhas. |
| `bh_tecnica_presa` | Técnica da Presa | Passiva | — | — | Interno 30 s | 5 s após troca | +1% velocidade por nível | Ativada ao usar `/maldicao` para mudar marca. |

### Tier 3 (T3)

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `bh_rito_superior` | Rito Superior | Modificador | — | +2% HP adicional | — | — | +5% dano elemental / nível | Aumenta efeito do Rito ativo. |
| `bh_dilacerar` | Dilacerar | Ativa | 30 | — | 75 s | Golpe alvo | +10% dano / nível | Remove escudos leves. |
| `bh_selo_sangue` | Selo de Sangue | Ativa | 30 | — | 120 s | Zona 6 blocos / 8 s | +5% redução cura / nível | MythicMobs: marcação `blood_seal_circle`. |
| `bh_recuperacao_rudimentar` | Recuperação Rudimentar | Passiva | — | — | Interno 15 s | Cura 3% por acerto | +0.5% cura / nível | Cap de 25% HP por ativação. |

### Tier 4 (T4)

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `bh_crimson_ascension` | Crimson Ascension (ULT) | Ativa | 0 | +2% HP por golpe | 210 s | 15 s buff | +2% dano / nível | Ativada via `/rito`; efeito MythicMobs `scarlet_wings`. |
| `bh_marca_exterminio` | Marca do Extermínio | Ativa | 35 | — | 120 s | 10 s alvo | +1% dano aliado / nível | Talismã `execution_brand`. |
| `bh_controle_hematico` | Controle Hemático | Ativa | 30 | — | 90 s | Raio 6 blocos | +0.5 bloco alcance / 3 níveis | Puxão leve com partículas vermelhas. |
| `bh_escarlatum_final` | Escarlatum Final | Passiva | — | — | Interno 120 s | Escudo 8 s | +2% escudo / nível | Ativado automaticamente <25% HP. |

---

## Ordem Ghostslayer (Matador de Espectros)
Permissão adicional: `class_blood_hunter.ghostslayer`. Itens focais: `spectral_stake`, `sanctified_relic`.

### Tier 1

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `gh_rito_espectro` | Rito do Espectro | Ativa | 25 | 8% HP | 60 s | 120 s | +3% dano radiante / nível | Usa `/rito`. |
| `gh_golpe_purificador` | Golpe Purificador | Ativa | 20 | — | 45 s | — | +5% dano vs mortos-vivos | Remove 1 buff. |
| `gh_estaca_espectral` | Estaca Espectral | Ativa | 25 | — | 60 s | Projétil / raiz 2 s | +0.5 s raiz / 4 níveis | Item MythicMobs `spectral_stake`. |
| `gh_visao_mortos` | Visão dos Mortos | Passiva | — | — | — | Raio 12 blocos | — | HUD `%bloodhunter_marca%` indica espectros. |

### Tier 2

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `gh_veu_consagrado` | Véu Consagrado | Ativa | 30 | — | 75 s | Aura 6 blocos / 8 s | +2% redução necrótica / nível | Bind `/rito`. |
| `gh_golpe_solar` | Golpe Solar | Ativa | 25 | — | 60 s | — | +5% dano / nível | Aplica queimadura radiante 5%/s. |
| `gh_sentenca_caidos` | Sentença dos Caídos | Ativa | 20 | — | 75 s | 10 s alvo | +1% dano extra / nível | Stacks com Marca. |
| `gh_purificacao_menor` | Purificação Menor | Ativa | 15 | — | 45 s | — | — | Remove veneno/decadência. |

### Tier 3

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `gh_coroa_luz` | Coroa de Luz | Ativa | 30 | — | 90 s | 10 s | +2 s duração / 4 níveis | Revela furtivos/espectros. |
| `gh_rasgo_sagrado` | Rasgo Sagrado | Ativa | 35 | — | 90 s | Cone 5 blocos | +10% dano / nível | Visual `holy_rift`. |
| `gh_escudo_sepultura` | Escudo de Sepultura | Ativa | 20 | — | 75 s | 6 s | +2% mitigação / 2 níveis | Stacka com Concentração. |
| `gh_lamina_consagrada` | Lâmina Consagrada | Passiva | — | — | Interno 20 s | 8 s debuff | +1% redução resistência / nível | Aplica -10% resistência mágica. |

### Tier 4

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `gh_anatema` | Anátema (ULT) | Ativa | 40 | — | 210 s | Zona 8 blocos / 12 s | +5% dano radiante / nível | Item `sanctified_relic`; comando `/rito`. |
| `gh_estrela_sepulcral` | Estrela Sepulcral | Ativa | 35 | — | 120 s | Raio 6 blocos | +0.5 s medo / 4 níveis | Medo 2 s, imunidade decrescente. |
| `gh_crenca_inabalavel` | Crença Inabalável | Ativa | 25 | — | 120 s | 10 s | +1 s duração / 3 níveis | Concede imunidade a medo. |
| `gh_luz_final` | Luz Final | Ativa | — | — | 150 s | Raio 6 blocos | +2% cura / nível | Cura aliados em 20% HP. |

---

## Ordem Profane Soul (Alma Profana)
Permissão adicional: `class_blood_hunter.profanesoul`. Itens focais: `eldritch_tome`, `void_focus`.

### Tier 1

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ps_rito_eldritch` | Rito Eldritch | Ativa | 25 | 8% HP | 60 s | 120 s | +2% dano projétil / nível | 3 projéteis a 60% cada. |
| `ps_maldicao_patrono` | Maldição do Patrono | Ativa | 30 | — | 75 s | 10 s alvo | +1% redução resistência / nível | Bind `/maldicao`. |
| `ps_passo_abissal` | Passo Abissal | Ativa | 20 | — | 60 s | 8 blocos | +0.5 bloco / 5 níveis | Deixa rastro arcano. |
| `ps_sangue_bruxaria` | Sangue da Bruxaria | Passiva | — | — | — | Permanente | +2% resistência arcana / 2 níveis | HUD `%bloodhunter_mutageno%` alterna para indicação arcana. |

### Tier 2

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ps_contrafeitico_profano` | Contrafeitiço Profano | Ativa | 25 | — | 90 s | — | +0.5 s silêncio / 4 níveis | Cancela conjuração e silencia 2 s. |
| `ps_vento_oblivio` | Vento de Oblívio | Ativa | 30 | — | 60 s | Cone 6 blocos | +8% dano / nível | Visual roxo com `void_focus`. |
| `ps_marca_eldritch` | Marca Eldritch | Ativa | 20 | — | 60 s | 8 s alvo | +1% dano mágico aliado / nível | Stacka com Marca da classe. |
| `ps_olhar_vazio` | Olhar do Vazio | Ativa | 15 | — | 45 s | 6 s visão | +1 s / 4 níveis | Revela 10 blocos. |

### Tier 3

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ps_rasgo_vazio` | Rasgo do Vazio | Ativa | 40 | — | 120 s | Zona 6 blocos / 10 s | +0.5 s / 3 níveis | Vórtice que puxa levemente. |
| `ps_veu_sombrio` | Véu Sombrio | Ativa | 25 | — | 75 s | 10 s | +1% redução precisão / nível | Aplica cegueira leve. |
| `ps_chama_profana` | Chama Profana | Ativa | 35 | — | 75 s | — | +10% dano / nível | Queimadura arcana 6 s. |
| `ps_distorcao` | Distorção | Ativa | 20 | — | 90 s | 8 s | +1% redução dano / nível | Absorve 10% dano. |

### Tier 4

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ps_portal_abissal` | Portal Abissal (ULT) | Ativa | 45 | — | 240 s | Zona 8 blocos / 12 s | +6% dano / nível | Partículas abissais. |
| `ps_marca_ruina` | Marca de Ruína | Ativa | 35 | — | 120 s | 10 s alvo | +1% mitigação reduzida / nível | Combina com Marca comum. |
| `ps_corte_abismo` | Corte do Abismo | Ativa | 40 | — | 120 s | — | +12% dano / nível | Golpe pesado, sem one shot. |
| `ps_nexo_profano` | Nexo Profano | Ativa | 35 | — | 150 s | 10 s | +2% dano mágico aliado / nível | Aura 6 blocos. |

---

## Ordem Mutant (Mutante)
Permissão adicional: `class_blood_hunter.mutant`. Itens focais: `alchemical_vial`, `mutagen_gloves`.

### Tier 1

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `mt_mutageno` | Mutágeno | Ativa | — | — | 90 s recarga | 90 s | +2% bônus / nível | Três perfis aplicados via `/mutageno`. |
| `mt_coagulo_alquimico` | Coágulo Alquímico | Ativa | 20 | — | 60 s | — | +2% cura / nível | Utiliza `alchemical_vial`. |
| `mt_explosao_hematica` | Explosão Hemática | Ativa | 25 | — | 45 s | Cone 5 blocos | +6% sangramento / nível | Hemocraft visual vermelho. |
| `mt_ligadura_rapida` | Ligadura Rápida | Ativa | 15 | — | 60 s | 8 s | +1 s duração / 4 níveis | Imunidade a sangramento. |

### Tier 2

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `mt_mutageno_avancado` | Mutágeno Avançado | Modificador | — | +2% HP ao aplicar | — | — | +3% bônus / nível | Melhora Mutágeno base. |
| `mt_catalisador_circulatorio` | Catalisador Circulatório | Ativa | 20 | — | 75 s | 8 s | +2% cura recebida / nível | Partículas douradas. |
| `mt_ferida_acelerada` | Ferida Acelerada | Passiva | — | +2% HP quando ativa | Interno 30 s | — | +1% sangramento / nível | Aumenta DoT. |
| `mt_sangue_volatil` | Sangue Volátil | Passiva | — | — | Interno 90 s | — | +10% dano explosão / nível | Explosão 150% ao <40% HP. |

### Tier 3

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `mt_catalise_suprema` | Catalise Suprema | Ativa | 35 | — | 210 s | 15 s | +0.5 s duração / 4 níveis | Reduz CD de Explosão Hemática por sangramentos. |
| `mt_regeneracao_instavel` | Regeneração Instável | Ativa | 25 | — | 105 s | 6 s cura | +1% cura / nível | Após efeito: -10% mitigação 6 s. |
| `mt_ruptura_interna` | Ruptura Interna | Ativa | 35 | — | 90 s | — | +8% dano / nível | Detona sangramentos do alvo. |
| `mt_fluxo_carmesim` | Fluxo Carmesim | Ativa | 20 | — | 75 s | 10 s | +1% velocidade / nível | Sinergia com cleave Mutágeno. |

### Tier 4

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `mt_transmutacao_final` | Transmutação Final (ULT) | Ativa | — | +3% HP por hit | 240 s | 20 s | +3% dano / nível | Alterna modelo `mutagen_gloves`. |
| `mt_corte_devorador` | Corte Devorador | Ativa | 40 | — | 120 s | — | +10% dano / nível | 220% + sangramento 8%/s. |
| `mt_serum_foco` | Serum de Foco | Ativa | 25 | — | 120 s | 10 s | +2% penetração / nível | Visual de partículas azuis. |
| `mt_hemostasia_total` | Hemostasia Total | Ativa | — | — | 150 s | — | +3% cura / nível | Remove sangramentos e cura 25%. |

---

## Ordem Lycan (Lícano)
Permissão adicional: `class_blood_hunter.lycan`. Itens focais: `lupine_claws`, `hunter_talisman`.

### Tier 1

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ly_forma_cacador` | Forma do Caçador | Ativa | 25 | 10% HP | 120 s | 20 s | +1% roubo de vida / nível | Ativa `/lycan`; mostra `%bloodhunter_licantropia%`. |
| `ly_uivo_aterror` | Uivo Aterrador | Ativa | 25 | — | 60 s | Cone 6 blocos | +0.5 s medo / 5 níveis | Medo 2 s com imunidade decrescente. |
| `ly_investida_predatoria` | Investida Predatória | Ativa | 20 | — | 45 s | Dash 6 blocos | +0.5 bloco / 4 níveis | 150% dano ao fim do dash. |
| `ly_rastreio_sanguineo` | Rastreio Sanguíneo | Ativa | 20 | — | 75 s | 12 blocos / 10 s | +1 s / 3 níveis | Revela alvos com sangramento. |

### Tier 2

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ly_presa_lua` | Presa da Lua | Ativa | 20 | — | 60 s | 10 s alvo | +1% dano / nível | +20% dano vs alvo marcado. |
| `ly_instinto_feral` | Instinto Feral | Ativa | 20 | — | 75 s | 8 s | +1% esquiva / nível | Buff evasão. |
| `ly_corte_selvagem` | Corte Selvagem | Ativa | 25 | — | 60 s | — | +6% sangramento / nível | 160% + sangramento 5%/s. |
| `ly_garou_alfa` | Garou Alfa | Ativa | 30 | — | 120 s | Aura 6 blocos / 10 s | +1% dano aliado / nível | Compartilha buff aliados. |

### Tier 3

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ly_luar_carmesim` | Luar Carmesim | Ativa | 35 | — | 210 s | Aura 8 blocos / 12 s | +1% atk speed / nível | Sangramento 5%/s; buff aliados. |
| `ly_garras_destrutivas` | Garras Destrutivas | Ativa | 30 | — | 90 s | 8 s | +2% penetração / nível | 180% dano + 25% penetração. |
| `ly_presa_impiedosa` | Presa Impiedosa | Passiva | — | — | Interno 10 s | 10 s | +1% dano / nível | Stacks até 5x. |
| `ly_corpo_fera` | Corpo de Fera | Ativa | 25 | — | 120 s | 6 s | +1% mitigação / nível | Buff defensivo. |

### Tier 4

| ID | Nome | Tipo | Hemocraft | Auto-dano | CD | Duração/Área | Escalas | Notas |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| `ly_cacada_final` | Caçada Final (ULT) | Ativa | 45 | — | 240 s | 15 s | +3% dano / nível | Mostra rastros completos via HUD. |
| `ly_rasgo_alfa` | Rasgo Alfa | Ativa | 40 | — | 120 s | — | +12% dano / nível | 240% dano + sangramento 8%/s. |
| `ly_devorar_carne` | Devorar Carne | Passiva | — | — | Interno 120 s | — | +2% cura / nível | Cura 25% ao matar alvo marcado. |
| `ly_dominio_bestial` | Domínio Bestial | Ativa | 35 | — | 150 s | Aura 6 blocos / 10 s | +1% dano/mitigação aliados / nível | Sinergia com Garou Alfa. |

---

## Integrações Adicionais

- **Skript Binds:**
  - `/rito` alterna ritos ativos e Ultimates correspondentes.
  - `/maldicao` aplica marcas específicas (classe e Profane Soul).
  - `/concentrar` ativa efeitos de foco (Concentração de Sangue, Distorção etc.).
  - `/mutageno` seleciona mutágenos e upgrades Mutant.
  - `/lycan` alterna Forma do Caçador e rastreios.
- **LuckPerms:**
  - Classe: `lp user <player> permission set class_blood_hunter true`.
  - Ordens: `lp user <player> permission set class_blood_hunter.<order> true` (`ghostslayer`, `profanesoul`, `mutant`, `lycan`).
- **PlaceholderAPI HUD:**
  - `%bloodhunter_hemocraft%` exibe recurso atual.
  - `%bloodhunter_marca%` mostra alvo marcado ou "—".
  - `%bloodhunter_mutageno%` indica mutágeno ativo ou None.
  - `%bloodhunter_licantropia%` informa tempo restante da forma.

Este material serve como guia de configuração para implementações balanceadas e compatíveis com o ecossistema de plugins proposto.
