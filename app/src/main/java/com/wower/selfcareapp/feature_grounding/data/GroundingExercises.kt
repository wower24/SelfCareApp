package com.wower.selfcareapp.feature_grounding.data

import com.wower.selfcareapp.R

val groundingExercises :List<GroundingExercise> = listOf(
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="5-4-3-2-1",
        instructions = listOf(
            "Name 5 things you can see",
            "Name 4 things you can touch",
            "Name 3 things you can hear",
            "Name 2 things you can smell",
            "Name 1 thing you can taste"
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="Find and Touch something that's...",
        instructions = listOf(
            "Red",
            "Orange",
            "Yellow",
            "Green",
            "Blue",
            "Purple"
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="Count Down",
        instructions = listOf(
            "Count down from 100 by 7s",
            "100, 93, 86..."
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="A-Z",
        instructions = listOf(
            "1. Find a category (animals, fruits, etc.)",
            "2. Name an object from the category that starts with A, B, C, etc."
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="Touch the grass",
        instructions = listOf(
            "Put your hands on the grass (or floor)",
            "How does it feel? Is it warm or cold?"
        )
    )
)