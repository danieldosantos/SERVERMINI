# Implantação de magias e condições

Este resumo foi estruturado a partir do arquivo `magais e condições.txt` na raiz do projeto para facilitar a implementação das magias e das condições de status no servidor Paper.

## Magias prioritárias (detalhes de implementação)

| Magia | Nível | Alcance | Duração | Componentes | Observações de comportamento |
| --- | --- | --- | --- | --- | --- |
| Sugestão | 2º (Encantamento) | 9 m | Concentração, até 8 horas | V, M (língua de cobra e favo de mel ou gota de azeite doce) | Influencia uma criatura a seguir um curso de ação razoável; encerra se receber dano. |
| Sugestão em Massa | 6º (Encantamento) | 18 m | 24 horas (10 dias no 7º nível, 30 dias no 8º, 1 ano e 1 dia no 9º) | V, M (língua de cobra e favo de mel ou gota de azeite doce) | Afeta até 12 criaturas; cada alvo faz teste de Sabedoria; termina para o alvo se ele receber dano. |
| Sussurros Dissonantes | 1º (Encantamento) | 18 m | Instantânea | V | 3d6 de dano psíquico (1d6 extra por nível acima do 1º); alvo se afasta na reação se falhar na resistência de Sabedoria. |
| Taumaturgia | Truque (Transmutação) | 9 m | Até 1 minuto | V | Permite três efeitos menores simultâneos (voz amplificada, tremores leves, sons instantâneos, abrir/fechar portas destrancadas, alterar olhos). |
| Teia | 2º (Conjuração) | 18 m | Concentração, até 1 hora | V, S, M (pedaço de teia de aranha) | Preenche cubo de 6 m com terreno difícil e escuridão leve; cai se não houver suporte adequado; pode ser incendiada (2d4 de dano de fogo). |

> Use estes parâmetros para criar habilidades no MythicMobs e efeitos persistentes (silêncio, enfeitiçado, terreno difícil) compatíveis com o sistema de atributos da ValhallaMMO.

## Listas de magias por classe (para priorização)

### Bardo
- Truques: Amizade, Ataque Certeiro, Consertar, Globos de Luz, Ilusão Menor, Luz, Mãos Mágicas, Mensagem, Prestidigitação, Proteção contra Lâminas, Zombaria Viciosa.
- 1º nível: Amizade Animal, Compreender Idiomas (ritual), Curar Ferimentos, Detectar Magia (ritual), Disfarçar-se, Enfeitiçar Pessoa, Escrita Ilusória (ritual), Falar com Animais (ritual), Fogo das Fadas, Heroísmo, Identificação (ritual), Imagem Silenciosa, Onda Trovejante, Queda Suave, Palavra Curativa, Passos Longos, Perdição, Riso Histérico de Tasha, Servo Invisível (ritual), Sono, Sussurros Dissonantes.
- 2º nível: Acalmar Emoções, Aprimorar Habilidade, Arrombar, Boca Encantada (ritual), Cativar, Cegueira/Surdez, Coroa da Loucura, Esquentar Metal, Despedaçar, Força Fantasmagórica, Detectar Pensamentos, Imobilizar Pessoa, Invisibilidade, Localizar Animais ou Plantas (ritual), Localizar Objeto, Mensageiro Animal (ritual), Nuvem de Adagas, Restauração Menor, Silêncio (ritual), Sugestão, Ver o Invisível, Zona da Verdade.
- 3º nível: Ampliar Plantas, Clarividência, Dificultar Detecção, Dissipar Magia, Enviar Mensagem, Falar com os Mortos, Falar com Plantas, Forjar Morte (ritual), Glifo de Vigilância, Idiomas, Imagem Maior, Medo, Névoa Fétida, Padrão Hipnótico, Pequena Cabana de Leomund (ritual), Rogar Maldição.
- 4º nível: Confusão, Compulsão, Movimentação Livre, Invisibilidade Maior, Localizar Criatura, Metamorfose, Porta Dimensional, Terreno Alucinógeno.
- 5º nível: Âncora Planar, Animar Objetos, Círculo de Teletransporte, Conhecimento Lendário, Curar Ferimentos em Massa, Despertar, Despistar, Dominar Pessoa, Imobilizar Monstro, Missão, Modificar Memória, Restauração Maior, Reviver os Mortos, Similaridade, Sonho, Vidência.
- 6º nível: Ataque Visual, Dança Irresistível de Otto, Encontrar o Caminho, Ilusão Programada, Proteger Fortaleza, Sugestão em Massa, Visão da Verdade.
- 7º nível: Espada de Mordenkainen, Forma Etérea, Miragem, Mansão Magnífica de Mordenkainen, Prisão de Energia, Projetar Imagem, Regeneração, Ressurreição, Símbolo, Teletransporte.
- 8º nível: Dominar Monstro, Enfraquecer Intelecto, Limpar a Mente, Loquacidade, Palavra de Poder Atordoar.
- 9º nível: Palavra de Poder Curar, Palavra de Poder Matar, Metamorfose Verdadeira, Sexto Sentido.

### Bruxo (recorte rápido)
- Truques: Amizade, Ataque Certeiro, Ilusão Menor, Mãos Mágicas, Prestidigitação, Proteção contra Lâminas, Rajada de Veneno, Rajada Mística, Toque Arrepiante.
- 1º nível: Armadura de Agathys, Braços de Hadar, Bruxaria, Compreender Idiomas (ritual), Enfeitiçar Pessoa, Escrita Ilusória (ritual), Proteção contra o Bem e Mal, Raio de Bruxa, Recuo Acelerado, Repreensão Infernal, Servo Invisível (ritual).
- 2º nível: Cativar, Coroa da Loucura, Despedaçar, Escuridão, Imobilizar Pessoa, Invisibilidade, Nuvem de Adagas, Passo Nebuloso, Patas de Aranha, Raio do Enfraquecimento, Reflexos, Sugestão.
- 3º nível: Círculo Mágico, Contramágica, Dissipar Magia, Fome de Hadar, Forma Gasosa, Idiomas, Imagem Maior, Remover Maldição, Medo, Padrão Hipnótico, Toque Vampírico, Voo.
- 4º nível: Banimento, Porta Dimensional, Malogro, Terreno Alucinógeno, Palavra Curativa, Perdição, Contato Extraplanar (ritual), Imobilizar Monstro, Sonho, Vidência.
- 6º nível: Ataque Visual, Círculo da Morte, Conjurar Fada, Criar Mortos-Vivos, Carne para Pedra, Portal Arcano, Sugestão em Massa, Visão da Verdade.
- 7º nível: Dedo da Morte, Forma Etérea, Prisão de Energia, Viagem Planar.
- 8º nível: Dominar Monstro, Enfraquecer o Intelecto, Loquacidade, Palavra de Poder Atordoar, Semiplano.
- 9º nível: Aprisionamento, Metamorfose Verdadeira, Palavra de Poder Matar, Projeção Astral, Sexto Sentido.

## Condições (efeitos de status)

- **Enfeitiçado:** não pode atacar quem o enfeitiçou nem usar habilidades nocivas; quem enfeitiçou tem vantagem em testes sociais contra o alvo.
- **Envenenado:** sofre desvantagem em jogadas de ataque e testes de habilidade.
- **Impedido:** deslocamento 0 e não recebe bônus de movimento; ataques contra o alvo têm vantagem e os ataques do alvo têm desvantagem; testes de resistência de Destreza com desvantagem.
- **Incapacitado:** não pode realizar ações ou reações.
- **Inconsciente:** incapacitado, não se move ou fala, larga o que segura, fica caído, falha automaticamente em testes de Força ou Destreza; ataques contra o alvo têm vantagem e ataques corpo a corpo que acertem são críticos automáticos.
- **Exaustão:** níveis cumulativos — 1) desvantagem em testes de habilidade; 2) deslocamento reduzido à metade; 3) desvantagem em jogadas de ataque e testes de resistência; 4) máximo de PV reduzido à metade; 5) deslocamento 0; 6) morte. Um descanso longo reduz 1 nível.
- **Invisível:** impossível de ver sem magia; considerado em escuridão densa para se esconder; ataques contra ele têm desvantagem e os ataques dele têm vantagem.
- **Paralisado:** incapacitado, não se move ou fala; falha testes de Força e Destreza; ataques contra o alvo têm vantagem e acertos corpo a corpo são críticos automáticos.
- **Petrificado:** transformado em substância sólida; incapacitado, falha testes de Força e Destreza; resistência a todos os danos, imune a veneno e doenças (efeitos suspensos), ataques contra ele têm vantagem.
- **Surdo:** falha automaticamente em testes de habilidade que exijam audição.

> Cada condição acima deve ser espelhada em metadados de LuckPerms (vantagem/desvantagem) e efeitos temporários de MythicMobs para manter compatibilidade com o sistema de rolagens.
