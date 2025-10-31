# Races Effects Datapack

This datapack wires the tabletop race tags used by the server (`race_darkvision`, etc.)
into in-game effects so administrators no longer have to run ad-hoc commands after each
restart.  The pack is meant to be placed directly inside the world's `datapacks/`
folder or zipped and uploaded through the `/datapack` command.

## Features

* Registers `races:load` and `races:tick` so Paper can call them automatically.
* Applies night vision to any player that has the `race_darkvision` tag **and** is
  currently standing in an area with block light level 7 or lower.
* Exposes the predicate `races:is_dark` for use in manual commands or other datapacks.

## Installation

1. Copy the `races_effects` folder into `world/datapacks/` (or compress it as
   `races_effects.zip` with the folder structure preserved).
2. Run `/datapack enable "file/races_effects"` (or restart the server so Paper loads it
   automatically).
3. Use `/tag <player> add race_darkvision` to mark eligible characters.  The effect will
   refresh automatically whenever the player stands in darkness.

To remove the automation simply disable or delete the datapack.
