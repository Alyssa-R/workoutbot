package aly.riv.workoutbot

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class ExerciseManager(
    @Value("classpath:exercise_list.json") private var exerciseListFile: File
){
    private var allExercises: List<Exercise> = loadExercisesFromFile()

    private fun loadExercisesFromFile(): List<Exercise> {
        val objectMapper = ObjectMapper()
        allExercises = objectMapper.readValue(exerciseListFile, object : TypeReference<List<Exercise>>() {})
        println(allExercises)
        return allExercises
    }

    fun size(): Int {
        return allExercises.size
    }
    // todo makeSuperSet(size)
    // todo makeWorkoutPlan
}
