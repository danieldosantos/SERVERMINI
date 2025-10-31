# Re-run this function every tick.
schedule function races:tick 1t replace

# Give night vision to darkvision users in dark areas.
execute as @a[tag=race_darkvision] at @s if predicate races:is_dark run effect give @s minecraft:night_vision 8 0 true
