package com.effectivelab1.heroapp.presentation.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivelab1.heroapp.data.api.ApiKeys.LIMIT
import com.effectivelab1.heroapp.data.repository.MarvelRepository
import com.effectivelab1.heroapp.network.ApiRepository
import com.effectivelab1.heroapp.util.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CharacterViewModel
    @Inject
    constructor(
        private val repository: MarvelRepository,
        private val apiRepository: ApiRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(UIState())
        val state: StateFlow<UIState> get() = _state

        private var currentOffset = 0

        init {
            loadHeroesFromDatabase()
            calculateOffsetFromDatabase()
        }

        private fun loadHeroesFromDatabase() {
            viewModelScope.launch {
                repository.getCharacters().collect { localHeroes ->
                    if (localHeroes.isEmpty()) {
                        loadHeroes()
                    }
                    _state.update { it.copy(heroes = localHeroes) }
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
            if (_state.value.isLoading) return
            _state.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                try {
                    val newCharacters = apiRepository.getCharacters(currentOffset).map { it.toUI() }
                    repository.refreshCharacters(currentOffset, apiRepository)
                    _state.update { it.copy(heroes = it.heroes + newCharacters) }
                    currentOffset += LIMIT
                } catch (e: Exception) {
                    _state.update { it.copy(errorMessage = "Failed to load heroes: ${e.message}") }
                } finally {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }

        fun onListScrolledToEnd() {
            if (!_state.value.isLoading) {
                loadHeroes()
            }
        }

        fun loadHeroById(heroId: Int) {
            viewModelScope.launch {
                try {
                    val hero = repository.getCharacterById(heroId, apiRepository)
                    _state.update { it.copy(selectedHero = hero) }
                    if (hero == null) {
                        _state.update { it.copy(errorMessage = "Hero not found") }
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(errorMessage = "Failed to load hero: ${e.message}") }
                } finally {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }

        fun clearDatabaseAndReload() {
            viewModelScope.launch {
                repository.clearDatabase()
                currentOffset = 0
                _state.update {
                    it.copy(
                        heroes = emptyList(),
                        selectedHero = null,
                        errorMessage = null,
                    )
                }
                loadHeroes()
            }
        }

        fun selectHero(index: Int) {
            val selectedHero = _state.value.heroes.getOrNull(index)
            _state.update { it.copy(selectedHero = selectedHero, triangleColor = getRandomColor()) }
        }

        fun resetSelectedHero() {
            _state.update { it.copy(selectedHero = null) }
        }

        fun getRandomColor(): Color {
            val random = Random
            return Color(
                red = random.nextFloat(),
                green = random.nextFloat(),
                blue = random.nextFloat(),
                alpha = 1f,
            )
        }
    }
