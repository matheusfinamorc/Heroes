package com.example.heroes.model

import java.io.Serializable

data class HeroesItem(
    var id: Int,
    var name: String,
    var powerstats: List<Int>
): Serializable

data class HeroesPowerstats(
    var intelligence: Int,
    var strenght: Int,
    var speed: Int,
    var power: Int,
    var combat: Int
): Serializable