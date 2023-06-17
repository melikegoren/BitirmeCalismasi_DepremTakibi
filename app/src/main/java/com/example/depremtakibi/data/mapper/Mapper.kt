package com.example.depremtakibi.data.mapper


interface Mapper<I, O> {
    fun map(input: I): O
}

interface HomeListMapper<I, O> : Mapper<List<I>, List<O>>

interface DetailsMapper<I, O> : Mapper<I, O>


