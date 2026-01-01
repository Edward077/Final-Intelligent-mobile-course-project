/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.saloneculture.data.interests.impl

import com.example.saloneculture.data.Result
import com.example.saloneculture.data.interests.InterestSection
import com.example.saloneculture.data.interests.InterestsRepository
import com.example.saloneculture.data.interests.TopicSelection
import com.example.saloneculture.utils.addOrRemove
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Implementation of InterestRepository that returns a hardcoded list of
 * topics, people and publications synchronously.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FakeInterestsRepository : InterestsRepository {

    private val topics by lazy {
        listOf(
            InterestSection("Arts & Crafts", listOf("Wood Carving", "Weaving", "Beadwork", "Painting")),
            InterestSection("Music & Dance", listOf("Traditional Music", "Bubu", "Cultural Dance", "Modern Afrobeats")),
            InterestSection("Heritage", listOf("Bunce Island", "Freetown History", "Heritage Preservation", "Museums")),
            InterestSection("Cuisine", listOf("Cassava Leaf", "Groundnut Stew", "Rice Dishes", "Street Food")),
            InterestSection("Languages", listOf("Krio", "Mende", "Temne", "Limba")),
        )
    }


    private val people by lazy {
        listOf(
            "Community Storytellers",
            "Traditional Drummers",
            "Cultural Dancers",
            "Local Visual Artists",
            "Craft Makers",
            "Heritage Guides",
            "Food Vendors & Chefs",
        )
    }


    private val publications by lazy {
        listOf(
            "Salone Culture Digest",
            "Heritage Notes",
            "Arts & Crafts Weekly",
            "Food & Lifestyle",
            "Music & Dance Spotlight",
        )
    }


    // for now, keep the selections in memory
    private val selectedTopics = MutableStateFlow(setOf<TopicSelection>())
    private val selectedPeople = MutableStateFlow(setOf<String>())
    private val selectedPublications = MutableStateFlow(setOf<String>())

    override suspend fun getTopics(): Result<List<InterestSection>> {
        return Result.Success(topics)
    }

    override suspend fun getPeople(): Result<List<String>> {
        return Result.Success(people)
    }

    override suspend fun getPublications(): Result<List<String>> {
        return Result.Success(publications)
    }

    override suspend fun toggleTopicSelection(topic: TopicSelection) {
        selectedTopics.update {
            it.addOrRemove(topic)
        }
    }

    override suspend fun togglePersonSelected(person: String) {
        selectedPeople.update {
            it.addOrRemove(person)
        }
    }

    override suspend fun togglePublicationSelected(publication: String) {
        selectedPublications.update {
            it.addOrRemove(publication)
        }
    }

    override fun observeTopicsSelected(): Flow<Set<TopicSelection>> = selectedTopics

    override fun observePeopleSelected(): Flow<Set<String>> = selectedPeople

    override fun observePublicationSelected(): Flow<Set<String>> = selectedPublications
}
