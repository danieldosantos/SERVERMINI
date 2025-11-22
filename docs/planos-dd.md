Multiverse-Core (console)
Plano Material (já existe):

mv modify world set pvp false
mv modify world set difficulty normal
Faéria:

mv create faeria normal -g Flat
mv modify faeria set pvp false
Spawn/respawn: use /mv setspawn estando em faeria (ver bloco in‑game).
Tempo: use WorldGuard (ver abaixo) ou gamerule in‑game.
Umbra:

mv create umbra normal
mv modify umbra set pvp true
mv modify umbra set difficulty hard
Tempo: via WorldGuard (abaixo).
Astral:

mv create astral normal -g Flat
mv modify astral set pvp false
mv modify astral set difficulty peaceful
Elementais:

Fogo: mv create elemental_fire nether --world-type NETHER
mv modify elemental_fire set pvp true
mv modify elemental_fire set difficulty hard
mv modify elemental_fire set entryfee 500
Ar: mv create elemental_air normal -g Void
mv modify elemental_air set pvp true
mv modify elemental_air set difficulty hard
Água: mv create elemental_water normal
mv modify elemental_water set pvp true
mv modify elemental_water set difficulty hard
Terra: mv create elemental_earth normal
mv modify elemental_earth set pvp true
mv modify elemental_earth set difficulty hard
Clima Água (opção console MV):

mv modify elemental_water set weather R
WorldGuard (console, usa -w <mundo>)
Faéria:

rg flag __global__ tnt deny -w faeria
rg flag __global__ greeting "Bem-vindo à Faéria, aventureiro!" -w faeria
rg flag __global__ time-lock 12000 -w faeria
Umbra:

rg flag __global__ mob-spawning allow -w umbra
rg flag __global__ fire-spread deny -w umbra
(opcional) rg flag __global__ fall-damage deny -w umbra
rg flag __global__ time-lock 18000 -w umbra
Astral:

rg flag __global__ effect LEVITATION -w astral
rg flag __global__ keep-inventory true -w astral
Elemental_fire:

rg flag __global__ damage-fire 1.0 -w elemental_fire
rg flag __global__ use deny -w elemental_fire
rg flag __global__ lighter deny -w elemental_fire
Elemental_air:

rg flag __global__ damage-fall 1.5 -w elemental_air
rg flag __global__ fire-spread deny -w elemental_air
rg flag __global__ weather clear -w elemental_air
Elemental_water:

rg flag __global__ damage-drowning 1.0 -w elemental_water
Elemental_earth:

rg flag __global__ tnt deny -w elemental_earth
rg flag __global__ entity-item-spawn deny -w elemental_earth
Portais (Multiverse-Portals)
Requer seleção com WorldEdit in‑game; o comando pode ser no console, mas a seleção precisa existir. Exemplo Material → Faéria:

selecionar quadro no mundo Material
console/in‑game: mvp create faeria_in c:faeria:250,65,250
mvp fill faeria_in ender_portal
Repita ajustando nomes/coords conforme seu guia (Material↔Umbra, Material↔Astral, Astral→Elementais).
Skript (console)
Criar arquivo plugins/Skript/scripts/viagem_planar.sk com o conteúdo do guia. Depois no console:

sk reload viagem_planar
Comandos que precisam ser in‑game (não via console)
Definir respawn/spawn do mundo: entrar no mundo e usar /mv setspawn (ou /setspawn <mundo> se usar EssentialsSpawn). Faça em faeria, umbra, astral etc.
Seleção de portais com o machado do WorldEdit antes de rodar mvp create/fill.
Gamerules/tempo caso prefira: estando no mundo, gamerule doDaylightCycle false e time set <valor>.
Nota WorldGuard “regions.yml” faltando
Crie o arquivo vazio se ainda houver aviso: plugins/WorldGuard/worlds/faeria/regions.yml (e para outros mundos se necessário), depois reinicie.