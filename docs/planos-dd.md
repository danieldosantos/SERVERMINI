# Implementação dos Planos de Existência

Este guia resume como adaptar o esquema de cosmologia de Dungeons & Dragons ao servidor, criando mundos e portais inspirados no "Apêndice C" (Plano Material, ecos, planos interiores, exteriores e semiplanos). Use as sugestões abaixo como ponto de partida para configurar plugins e assets.

## Requisitos e plugins úteis
- **Gerenciamento de múltiplos mundos**: Multiverse-Core ou plugins equivalentes para criar mundos temáticos (Material, Faéria, Umbra, planos elementais etc.).
- **Portais persistentes**: Multiverse-Portals, AdvancedPortals ou CommandPanels para criar portais fixos entre planos.
- **Proteção e regras**: WorldGuard/RegionProtect para definir regras por mundo (PvP, dano de queda, explosões). LuckPerms já presente para permissões.
- **Ambiente e imersão**: Resource packs por mundo (via Geyser/Floodgate ou plugins de per-mundo), efeitos de clima e iluminação com Denizen/Skript para eventos especiais.

## Mapeamento sugerido dos planos
- **Plano Material**: mundo principal do servidor. Manter ciclo normal de dia/noite e progressão de sobrevivência.
- **Faéria (Plano das Fadas)**: mundo com iluminação suave e flora densa. Ajuste `time` permanente para pôr do sol e use partículas leves; criaturas amistosas com loot temático.
- **Umbra (Plano das Sombras)**: mundo com cores dessaturadas. Configure escuridão permanente, fog densa e mobs reforçados; use shaders/packs que removam cores vibrantes.
- **Planos Elementais**: crie mundos dedicados a Fogo, Água, Ar e Terra. Limite blocos e mobs disponíveis para refletir cada elemento e use danos ambientais (fogo, sufocamento, queda) para reforçar o tema.
- **Plano Astral e Etéreo (transição)**: lobbies de viagem com gravidade reduzida (efeitos de levitação), partículas cintilantes e portais para planos exteriores/interiores.
- **Planos Exteriores**: escolha planos relevantes para a campanha (ex.: Celestia, Abismo, Nove Infernos). Defina regras de facção ou alinhamento e diferencie loot/risco por plano.
- **Semiplanos**: áreas instanciadas para quests ou chefes, criadas com mundos temporários ou regiões isoladas carregadas sob demanda.

## Rotas de viagem e progressão
1. **Portais fixos**: espalhe portais no Material com requisitos de chave (itens, conquistas ou saldo) para acesso. Documente as chaves em livros/menus.
2. **Magias/itens**: use scripts para permitir teleporte temporário (ex.: pergaminho de "Viagem Planar" com cooldown). Itens consumíveis evitam abuso e reforçam a fantasia.
3. **Progressão por poder**: bloqueie planos mais perigosos atrás de conquistas (chefes elementais → acesso aos Planos Exteriores; reputação com facções → acesso a Ysgard ou Celestia).

## Considerações de balanceamento
- Ajuste drops e XP para compensar riscos específicos de cada plano.
- Use limites de inventário ou encadeamento de missões para evitar que recursos raros inundem o Material.
- Teste caminhos de retorno seguros (botões de emergência, home temporária) para evitar perda de jogadores em regiões extremas.

## Próximos passos rápidos
- Defina a lista de planos que realmente aparecerão na campanha.
- Crie o Plano Material e dois ecos (Faéria e Umbra) como MVP; configure portais e regras básicas.
- Itere adicionando Planos Elementais e um ou dois Planos Exteriores conforme a história avançar.
