package com.effectivelab1.heroapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivelab1.heroapp.constants.ApiKeys.LIMIT
import com.effectivelab1.heroapp.data.MarvelRepository
import com.effectivelab1.heroapp.network.ApiRepository
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
class CharacterViewModel(
    private val repository: MarvelRepository
) : ViewModel() {

    private val apiRepository = ApiRepository()

    private val _selectedHeroIndex = mutableStateOf(0)
    val selectedHeroIndex: State<Int> get() = _selectedHeroIndex

    private val _heroes = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val heroes: StateFlow<List<MarvelCharacter>> get() = _heroes

    private val _selectedHero = MutableStateFlow<MarvelCharacter?>(null)
    val selectedHero: StateFlow<MarvelCharacter?> get() = _selectedHero

    private val _triangleColor = mutableStateOf(getRandomColor())
    val triangleColor: Color get() = _triangleColor.value

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    var isLoading = MutableStateFlow(false)
    private var currentOffset = 0

    init {
        loadHeroesFromDatabase()
        calculateOffsetFromDatabase()
    }

    private fun loadHeroesFromDatabase() {
        viewModelScope.launch {
            repository.getCharacters().collect { localHeroes ->
                _heroes.value = localHeroes
                if (localHeroes.isEmpty()) {
                    loadHeroes()
                }
            }
        }
    }

    private fun calculateOffsetFromDatabase() {
        viewModelScope.launch {
            val localHeroCount = repository.getCharacterCount()
            currentOffset = localHeroCount
        }
    }

    private fun loadHeroes() {
        if (isLoading.value) return
        isLoading.value = true

        viewModelScope.launch {
            try {
                val characters = apiRepository.getCharacters(currentOffset)
                repository.refreshCharacters(currentOffset, apiRepository)
                _heroes.value = characters
                currentOffset += LIMIT
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load heroes: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun onListScrolledToEnd() {
        if (!isLoading.value) {
            loadHeroes()
        }
    }

    fun loadHeroById(heroId: Int) {
        viewModelScope.launch {
            try {
                val hero = repository.getCharacterById(heroId, apiRepository)
                _selectedHero.value = hero
                if (hero == null) {
                    _errorMessage.value = "Hero not found"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load hero: ${e.message}"
            }
        }
    }


    fun selectHero(index: Int) {
        _selectedHeroIndex.value = index
        _triangleColor.value = getRandomColor()
    }

    fun resetSelectedHero() {
        _selectedHero.value = null
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
