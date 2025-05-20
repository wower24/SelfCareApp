package com.wower.selfcareapp.feature_grounding.data

import com.wower.selfcareapp.R

val groundingExercises :List<GroundingExercise> = listOf(
    GroundingExercise(
        imageResId = R.drawable.dog,
        title = "Name...",
        instructions = listOf(
            "5 things you can see",
            "4 things you can touch",
            "3 things you can hear",
            "2 things you can smell",
            "1 thing you can taste"
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="Find and Touch something...",
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
            "Pick a category\n(animals, fruits, etc.)",
            "Name an object from\nthe category\nthat starts with A, B, C, etc."
        )
    ),
    GroundingExercise(
        imageResId = R.drawable.dog,
        title ="Touch the grass",
        instructions = listOf(
            "Put your hands on the grass (or floor)",
            "Is it warm or cold?"
        )
    )
)