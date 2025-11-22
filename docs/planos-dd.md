Implementação dos Planos de Existência

Este guia descreve como implantar os primeiros planos — Plano Material, Faéria, Umbra, o hub Astral e os Planos Elementais — usando Multiverse-Core, Multiverse-Portals, WorldGuard e Skript. Siga as seções abaixo para criar mundos, aplicar regras temáticas, ligar tudo com portais persistentes e oferecer uma rota alternativa via item mágico.

1) Configuração inicial de mundos (Multiverse-Core)

Execute os comandos no console ou in-game com as permissões adequadas.

A. Plano Material (world)

/mv modify set pvp false world — PvP desabilitado por padrão (pode ser flexibilizado com WorldGuard em regiões específicas).

/mv modify set difficulty normal world — Dificuldade normal para progressão inicial.

B. Faéria (Plano das Fadas)

/mv create faeria normal -g Flat — Cria o mundo faeria (use outro gerador, se preferir).

/mv modify set pvp false faeria — PvP desabilitado para foco em exploração.

/mv modify set respawnWorld faeria — Garante respawn dentro de Faéria.

/mv modify set time 12000 faeria — Pôr do sol permanente (crepúsculo).

C. Umbra (Plano das Sombras)

/mv create umbra normal — Cria o mundo umbra.

/mv modify set pvp true umbra — PvP habilitado (alto risco/alto retorno).

/mv modify set respawnWorld umbra — Respawn dentro de Umbra.

/mv modify set difficulty hard umbra — Dificuldade máxima para mobs mais perigosos.

/mv modify set time 18000 umbra — Meia-noite permanente.

D. Plano Astral (Lobby)

/mv create astral normal -g Flat — Cria o hub seguro.

/mv modify set pvp false astral — PvP desabilitado no lobby.

/mv modify set difficulty peaceful astral — Dificuldade pacífica para zona de espera.

/mv modify set respawnWorld astral — Respawn dentro do Astral.

E. Planos Elementais (Interiores)

Fogo: /mv create elemental_fire normal -t nether — Mundo inspirado no Nether.

/mv modify set pvp true elemental_fire

/mv modify set difficulty hard elemental_fire

/mv modify set entryfee 500 elemental_fire — Custo de entrada para conter abusos.

Ar: /mv create elemental_air normal -g Void

/mv modify set pvp true elemental_air

/mv modify set difficulty hard elemental_air

Água: /mv create elemental_water normal

/mv modify set pvp true elemental_water

/mv modify set difficulty hard elemental_water

Terra: /mv create elemental_earth normal

/mv modify set pvp true elemental_earth

/mv modify set difficulty hard elemental_earth

2) Regras por plano (WorldGuard)

Execute os comandos do WorldGuard já dentro do mundo desejado ou com o parâmetro -w <mundo>.

Umbra — perigo extremo

/rg flag __global__ mob-spawning allow -w umbra — Permite spawn de mobs (inclui MythicMobs se configurado).

/rg flag __global__ fire-spread deny -w umbra — Impede alastramento de fogo para manter controle do cenário.

/rg flag __global__ fall-damage deny -w umbra (opcional) — Substitua ou ajuste para um efeito temático de dano.

Faéria — segurança e imersão

/rg flag __global__ tnt deny -w faeria — Sem explosões para proteger o bioma.

/rg flag __global__ greeting "Bem-vindo à Faéria, aventureiro!" -w faeria — Mensagem de entrada temática.

/rg flag __global__ time-lock 12000 -w faeria — Trava de tempo alternativa ao comando do Multiverse.

Astral — lobby de gravidade reduzida

/rg flag __global__ effect LEVITATION -w astral — Simula gravidade reduzida.

/rg flag __global__ keep-inventory true -w astral — Impede perda de itens em mortes acidentais.

Planos Elementais — perigos ambientais

Fogo:

/rg flag __global__ damage-fire 1.0 -w elemental_fire — Dano de fogo constante.

/rg flag __global__ use deny -w elemental_fire — Impede uso de baldes de água.

/rg flag __global__ lighter deny -w elemental_fire — Mantém fogo como perigo sempre presente.

Ar:

/rg flag __global__ damage-fall 1.5 -w elemental_air — Aumenta dano de queda.

/rg flag __global__ fire-spread deny -w elemental_air

/rg flag __global__ weather clear -w elemental_air

Água:

/mv modify set weather R elemental_water — Chuva constante (alternativa ao flag).

/rg flag __global__ damage-drowning 1.0 -w elemental_water

Terra:

/rg flag __global__ tnt deny -w elemental_earth

/rg flag __global__ entity-item-spawn deny -w elemental_earth — Reduz farm de recursos.

3) Conexão dos planos (Multiverse-Portals)

Use o machado do WorldEdit para selecionar o quadro do portal. Depois crie e preencha.

Material → Faéria

Selecione o quadro no world.

/mvp create faeria_in c:faeria:250,65,250 — Cria portal para as coordenadas indicadas em faeria.

/mvp fill faeria_in ender_portal — Preenche com bloco de portal (ou portal do Nether, à escolha).

Faéria → Material

Selecione o quadro em faeria.

/mvp create material_out c:world:0,65,0 — Cria o retorno para o Plano Material.

/mvp fill material_out ender_portal — Ajuste o preenchimento conforme o visual desejado.

Material ↔ Umbra

Ida (Material → Umbra): selecione no world e execute /mvp create umbra_in c:umbra:250,65,250 seguido de /mvp fill umbra_in ender_portal.

Volta (Umbra → Material): selecione em umbra e execute /mvp create material_out_umbra c:world:10,65,10 seguido de /mvp fill material_out_umbra portal.

Material ↔ Astral (novo hub)

Ida (Material → Astral): selecione no world e execute /mvp create astral_in c:astral:0,65,0, depois /mvp fill astral_in ender_portal.

Volta (Astral → Material): selecione em astral e execute /mvp create material_out_astral c:world:20,65,20, depois /mvp fill material_out_astral portal.

Astral → Elementais (portas dedicadas)

No astral, crie um portal por elemento:

Fogo: /mvp create astral_to_fire c:elemental_fire:0,120,0 e /mvp fill astral_to_fire portal

Ar: /mvp create astral_to_air c:elemental_air:0,120,0 e /mvp fill astral_to_air ender_portal

Água: /mvp create astral_to_water c:elemental_water:0,120,0 e /mvp fill astral_to_water portal

Terra: /mvp create astral_to_earth c:elemental_earth:0,120,0 e /mvp fill astral_to_earth portal

4) Rota alternativa por item mágico (Skript)

Crie um pergaminho que teleporta o jogador para Faéria com cooldown.

Renomeie um papel para “Pergaminho de Viagem Planar”.

No diretório do Skript, crie plugins/Skript/scripts/viagem_planar.sk com o conteúdo:

on rightclick:
    if player's tool is paper named "Pergaminho de Viagem Planar":
        if {viagem.cooldown.%player%} is set:
            if difference between now and {viagem.cooldown.%player%} is less than 5 minutes:
                send "&cO pergaminho ainda está em recarga." to player
                stop
        set {viagem.cooldown.%player%} to now
        apply potion of invisibility to player for 5 seconds
        play effect ender signal at player's location
        teleport player to world "faeria"


Recarregue o Skript: /sk reload viagem_planar.

5) Checklist rápido

 Mundos criados (world, faeria, umbra, astral, elemental_fire, elemental_air, elemental_water, elemental_earth) e ajustes aplicados.

 Flags do WorldGuard configuradas em Faéria, Umbra, Astral e Planos Elementais.

 Portais Material ↔ Faéria, Material ↔ Umbra e Material ↔ Astral funcionando.

 Portais Astral → Elementais implantados e posicionados.

 Pergaminho de viagem planar testado e com cooldown ativo.

Com isso, o multiverso inicial (Material, Faéria, Umbra, Astral e Elementais) fica ativo, conectado e pronto para expansão.

Este guia resume como adaptar o esquema de cosmologia de Dungeons & Dragons ao servidor, criando mundos e portais inspirados no "Apêndice C" (Plano Material, ecos, planos interiores, exteriores e semiplanos). Use as sugestões abaixo como ponto de partida para configurar plugins e assets.

Requisitos e plugins úteis

Gerenciamento de múltiplos mundos: Multiverse-Core ou plugins equivalentes para criar mundos temáticos (Material, Faéria, Umbra, planos elementais etc.).

Portais persistentes: Multiverse-Portals, AdvancedPortals ou CommandPanels para criar portais fixos entre planos.

Proteção e regras: WorldGuard/RegionProtect para definir regras por mundo (PvP, dano de queda, explosões). LuckPerms já presente para permissões.

Ambiente e imersão: Resource packs por mundo (via Geyser/Floodgate ou plugins de per-mundo), efeitos de clima e iluminação com Denizen/Skript para eventos especiais.

Mapeamento sugerido dos planos

Plano Material: mundo principal do servidor. Manter ciclo normal de dia/noite e progressão de sobrevivência.

Faéria (Plano das Fadas): mundo com iluminação suave e flora densa. Ajuste time permanente para pôr do sol e use partículas leves; criaturas amistosas com loot temático.

Umbra (Plano das Sombras): mundo com cores dessaturadas. Configure escuridão permanente, fog densa e mobs reforçados; use shaders/packs que removam cores vibrantes.

Planos Elementais: crie mundos dedicados a Fogo, Água, Ar e Terra. Limite blocos e mobs disponíveis para refletir cada elemento e use danos ambientais (fogo, sufocamento, queda) para reforçar o tema.

Plano Astral e Etéreo (transição): lobbies de viagem com gravidade reduzida (efeitos de levitação), partículas cintilantes e portais para planos exteriores/interiores.

Planos Exteriores: escolha planos relevantes para a campanha (ex.: Celestia, Abismo, Nove Infernos). Defina regras de facção ou alinhamento e diferencie loot/risco por plano.

Semiplanos: áreas instanciadas para quests ou chefes, criadas com mundos temporários ou regiões isoladas carregadas sob demanda.

Rotas de viagem e progressão

Portais fixos: espalhe portais no Material com requisitos de chave (itens, conquistas ou saldo) para acesso. Documente as chaves em livros/menus.

Magias/itens: use scripts para permitir teleporte temporário (ex.: pergaminho de "Viagem Planar" com cooldown). Itens consumíveis evitam abuso e reforçam a fantasia.

Progressão por poder: bloqueie planos mais perigosos atrás de conquistas (chefes elementais → acesso aos Planos Exteriores; reputação com facções → acesso a Ysgard ou Celestia).

Considerações de balanceamento

Ajuste drops e XP para compensar riscos específicos de cada plano.

Use limites de inventário ou encadeamento de missões para evitar que recursos raros inundem o Material.

Teste caminhos de retorno seguros (botões de emergência, home temporária) para evitar perda de jogadores em regiões extremas.