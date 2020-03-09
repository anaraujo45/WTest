package pt.nextengineering.wtest.models

data class PostalCodes (
    var cod_distrito: Int,
    var cod_concelho: Int,
    var cod_localidade: Int,
    var nome_localidade: String,
    var cod_arteria: Int,
    var tipo_arteria: Int,
    var prep1: Int,
    var titulo_arteria: String,
    var prep2: Int,
    var nome_arteria: String,
    var local_arteria: String,
    var troco: Int,
    var porta: Int,
    var cliente: String,
    var num_cod_postal: Int,
    var ext_cod_postal: Int,
    var desig_postal: String
)