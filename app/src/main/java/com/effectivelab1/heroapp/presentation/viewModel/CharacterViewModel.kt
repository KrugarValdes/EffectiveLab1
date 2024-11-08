package com.effectivelab1.heroapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivelab1.heroapp.constants.ApiKeys
import com.effectivelab1.heroapp.network.MarvelRepository
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CharacterViewModel : ViewModel() {
    private val repository = MarvelRepository()

    private val _heroes = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val heroes: StateFlow<List<MarvelCharacter>> get() = _heroes

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _selectedHeroIndex = mutableStateOf(0)

    private val _triangleColor = mutableStateOf(getRandomColor())
    val triangleColor: Color get() = _triangleColor.value

    private val _selectedHero = MutableStateFlow<MarvelCharacter?>(null)
    val selectedHero: StateFlow<MarvelCharacter?> get() = _selectedHero

    private var isLoading = false
    private var currentOffset = 0

    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            try {
                val newHeroes = repository.getCharacters(currentOffset)
                _heroes.value = _heroes.value + newHeroes
                currentOffset += ApiKeys.LIMIT
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load heroes: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun onListScrolledToEnd() {
        loadHeroes()
    }

    fun loadHeroById(heroId: Int) {
        viewModelScope.launch {
            try {
                val hero = repository.getCharacter(heroId)
                _selectedHero.value = hero
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load hero: ${e.message}"
            }
        }
    }

    fun resetSelectedHero() {
        _selectedHero.value = null
    }

    fun selectHero(index: Int) {
        _selectedHeroIndex.value = index
        _triangleColor.value = getRandomColor()
    }

    private fun getRandomColor(): Color {
        val random = Random
        return Color(
            red = random.nextFloat(),
            green = random.nextFloat(),
            blue = random.nextFloat(),
            alpha = 1f,
        )
    }
}
