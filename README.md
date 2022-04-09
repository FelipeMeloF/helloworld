
# Informações Iniciais
Como sou da equipe de QA, programo testes em javascript, e tenho experiência em C/C++, portanto não tinha experiência alguma como dev, e também não sabia programar em kotlin, tive que aprender do zero em um curto período de tempo, desde já, peço desculpas ao estado do projeto, fiz por aprendizado. 

# Como executar o app

Eu programo na própria IDE do Android Studio, e executo pela mesma, utilizando o emulador Pixel 3 API 29 (Android 10). Basta clonar a branch "dev", abrir o código no Android Studio, selecionar o emulador corretamente, abrir o emulador e por fim abrir o app "helloworld" que corresponde ao app desenvolvido.

# Desenvolvimento
Inicialmente fiz engenharia reversa no projeto Hope para entender como funcionava os layouts e como pegava as APIs, tentei seguir o modelo MVVM para separar o código do front, o Android Studio deu uma mãozinha nisso e ajudou-me a organizar melhor. 

Após a engenharia reversa, comecei a desenvolver pela Splash Screen, bastou fazer uma animação na logo da Fuze e pular para a segunda screen. 
# Problemas

Tive um problema que quando eu rolava todas as partidas, o app crashava, descobri que o problema era ocasionado pelo index dos oponentes que ainda estavam para ser definidos, então para solucionar criei dois IFs para caso houvesse cards com um ou dois oponentes faltando, simplesmente não mostrará o card da partida.

# O que faltou ser implementado

Parei na terceira screen por falta de tempo mesmo, passei muito tempo na segunda screen tentando pegar os dados da API e tentando utilizar os dados obtidos, mas depois de algumas pesquisas, consegui fazer funcionar, quando eu finalmente peguei embalo, o tempo estava esgotado e não consegui implementar os jogadores por partida da terceira screen.

Eu também iria implementar um filtro, embora não tenha sido previsto no figma, para poder filtrar por liga, algo semelhante com o que temos no hope, porém seria no canto superior direito da segunda screen.



