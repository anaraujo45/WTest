# WTest

## Sobre
App-Challenge N ext Engineering
Estes projeto consiste na importação de um ficheiro csv e armazenamento dos dados de forma presistente em bd (SQLite).
Era pedido também que fosse feito um parse dos dados inseridos e apenas fosse apresentados dados onde ocorreria match. Esta funcionalidade não ficou feita em tempo util.
Em comentário no código deixei algumas indicações do procedimento (especialmente na parte de importação de dados).

Sobre a importação, gostaria que os dados (guardar o csv e importar para a BD) levassem o menos tempo possível. Mas não foi possível pela minha falta de conhecimento com esta questão de modo geral. Ainda assim, fiquei satisfeita com o resultado final.


## IDE usado 

Android Studio

## Implements necessários

	//recyclerView e cardView
	*implementation 'com.android.support:recyclerview-v7:29.0.0'
    *implementation 'com.android.support:cardview-v7:29.0.0'

    //retrofit
    *implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    *implementation 'com.squareup.retrofit2:converter-gson:2.6.2'

    //para guardar o ficheiro
    *implementation 'org.apache.directory.studio:org.apache.commons.io:2.4'

    //para usar threads diferentes (guardar em bd e apresentar dados em ecra)
    *implementation 'org.jetbrains.anko:anko-common:0.9'

    //usado para manipular o csv
    *androidTestImplementation 'com.android.support.test:rules:1.0.2'
    *implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.7.3")




