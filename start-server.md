# Start server

Guia rapido para subir o servidor Paper com os plugins de DnD que estao neste repositorio.

## Requisitos
- Java 21 (Paper 1.21.x requer Java 21; use Java 17 apenas se ficar em 1.20.x).
- Jar do servidor Paper, por exemplo `paper-1.21.4.jar`, colocado na raiz do projeto.
- Porta 25565 liberada no firewall/roteador se for abrir o servidor para a internet.

## Preparar e primeiro start
1. Coloque o jar do servidor na mesma pasta deste arquivo. Se voce so tem `paper-api-*.jar`, baixe o jar do servidor em https://papermc.io/downloads e renomeie para `paper-1.21.4.jar`.
2. Deixe a pasta `plugins/` desta repo na mesma pasta do jar (os plugins ja estao incluidos).
3. Rode o servidor para gerar arquivos iniciais:
   ```
   java -Xmx4G -Xms2G -jar paper-1.21.4.jar nogui
   ```
   Ajuste `-Xmx` e `-Xms` conforme a memoria disponivel.
4. Edite `eula.txt` e mude para `eula=true`.
5. Rode o mesmo comando de start novamente para subir o servidor.

## Aplicar configuracoes inclusas
- ValhallaMMO: copie `valhallammo/config.yml` para `plugins/ValhallaMMO/config.yml`.
- MythicMobs: copie `mythicmobs/items/`, `mythicmobs/mobs/` e `mythicmobs/skills/` para `plugins/MythicMobs/`.
- LuckPerms: execute os comandos de `luckperms/commands.txt` no console do servidor (troque `<player>` pelo nick) para criar racas, classes e backgrounds.
- EssentialsX: substitua `plugins/Essentials/kits.yml` por `essentials/kits.yml`.
- Jars extras: qualquer plugin adicional basta colocar em `plugins/`.

## Uso diario
- Start: `java -Xmx4G -Xms2G -jar paper-1.21.4.jar nogui`
- Start com porta custom: adicione `--port 25565` (mude o numero se precisar).
- Parar: digite `stop` no console.
- Backup recomendado antes de atualizar: `world/`, `world_nether/`, `world_the_end/` e `plugins/` (configs e dados).

## Solução de problemas comuns
- **EssentialsX avisando "você está executando uma versão de servidor não suportada"**: use um build de desenvolvimento mais
  recente do EssentialsX compatível com a sua versão do Paper. Baixe o jar atualizado e substitua em `plugins/EssentialsX*.jar`.
  Se o aviso continuar, verifique se o servidor realmente está na versão mais recente do Paper 1.21.4 e reinicie.
