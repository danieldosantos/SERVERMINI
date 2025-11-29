# Visão geral do projeto

Este diretório contém os arquivos de servidor para o modpack **Dungeon Heroes** (versão 2.4.6) direcionado ao **Minecraft 1.21.1** com **Fabric Loader 0.17.2**. O manifest indica **8 GB** de RAM recomendados para a instância do servidor.

## Conteúdo principal

- `fabric-server-launch.jar` e a pasta `libraries/`: bootstrappers do servidor Fabric que acompanham o modpack.
- `manifest.json`: lista oficial de mods do modpack, compatível com CurseForge/packwiz.
- `overrides/`: configurações, datapacks, resourcepacks, scripts e um único mod já incluso (`AdaptiveTooltips-1.3.0-fabric-1.21.jar`).
- `server.jar`: jar do Paper 1.21.1 (útil apenas se optar por servidor de plugins em vez de mods Fabric).
- `start.sh` / `start.bat`: scripts atualizados para iniciar o servidor Fabric com 8 GB de RAM máxima e 4 GB mínima.

## Como preparar o servidor Fabric

1. **Baixe todos os mods do manifest:** use CurseForge ou `packwiz cf import manifest.json` para popular `overrides/mods/` com todos os mods listados.
2. **Copie as overrides:** mova o conteúdo de `overrides/` (mods, configs, datapacks, resourcepacks, scripts, shaderpacks) para o diretório onde o servidor será executado.
3. **Aceite a EULA:** após a primeira execução, edite `eula.txt` para `eula=true`.
4. **Inicie o servidor:** execute `start.sh` (Linux/macOS) ou `start.bat` (Windows) para rodar o `fabric-server-launch.jar` com a memória recomendada. Ajuste os valores `-Xms` e `-Xmx` se tiver menos RAM disponível.

> Observação: usar `server.jar` (Paper) não carregará os mods Fabric listados no manifest. Escolha-o apenas para um servidor de plugins sem mods Fabric.

## Manual rápido de comandos para jogadores

Os comandos abaixo são os mais úteis assim que você entra no servidor. Alguns dependem de permissões ou de plugins/mods presentes; caso não funcionem, peça ajuda a um moderador.

### Navegação e ajuda
- `/help` – lista de comandos disponíveis para você e pequenos resumos de uso.
- `/list` – mostra quem está online e quantos jogadores estão conectados.
- `/info` ou `/motd` – exibe regras e links úteis, quando configurados pela staff.

### Comunicação
- `/msg <jogador> <mensagem>` – envia mensagem privada para outro jogador.
- `/r <mensagem>` – responde a quem falou com você por mensagem privada.
- `/me <ação>` – envia uma mensagem em estilo de narração para o chat global.

### Navegação rápida (se permitido)
- `/spawn` – teleporta para o ponto principal do servidor.
- `/sethome` e `/home` – define um ponto pessoal e volta para ele rapidamente.
- `/tpa <jogador>`, `/tpahere <jogador>`, `/tpaccept` e `/tpdeny` – pedidos de teleporte para outros jogadores.

### Reclamações e suporte
- `/report <jogador> <motivo>` – abre um ticket para a staff (use em casos de bug, grief ou conduta inadequada).
- `/rules` – relembra as regras oficiais do servidor.

### Etiqueta recomendada
- Evite spam no chat e use `/msg` para conversas privadas prolongadas.
- Use `/help` para confirmar sintaxe de comandos antes de testar em situação crítica.
- Se um comando não funcionar, confirme com a staff se ele está liberado para sua categoria de jogador.
