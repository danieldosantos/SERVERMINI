# PlaceholderAPI e dependências

Para que os scripts e comandos que usam placeholders funcionem corretamente, é necessário instalar manualmente os seguintes plugins no servidor:

- [PlaceholderAPI](https://www.spigotmc.org/resources/6245/) – habilita a API de placeholders consumida pelo Skript e pelos comandos customizados.
- [Vault](https://www.spigotmc.org/resources/34315/) – opcional, mas recomendado para que comandos que dependem de permissões/eco do Essentials funcionem.

Após instalar o PlaceholderAPI, carregue as expansões utilizadas pelos scripts executando no console do servidor (ou com permissão `placeholderapi.admin`):

```text
/papi ecloud download Skills
/papi ecloud download SkillAPI
/papi ecloud download Server
/papi reload
```

Adapte a lista acima às expansões realmente utilizadas no servidor. Consulte os scripts `.sk` para identificar placeholders adicionais – todos os placeholders começam com `%skills_`, `%skillapi_`, `%raceseffects_` e `%placeholderapi_`.

> **Importante:** o `plugins/skript-placeholders` permanece desativado até que o PlaceholderAPI esteja presente. Instale o jar e reinicie o servidor para que o carregamento seja concluído sem erros de placeholders ausentes.
