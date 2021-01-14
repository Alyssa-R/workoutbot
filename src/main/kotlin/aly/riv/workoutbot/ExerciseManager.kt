package aly.riv.workoutbot

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import kotlin.math.floor

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

    private fun getRandomExercise(): Exercise {
        return allExercises.random()
    }

    // feels like this should live somewhere else
    fun generateWorkoutPlan(numSuperSets: Int, numExercisesPerSuperSet: Int): WorkoutPlan{
        val wap = WorkoutPlan(numSuperSets)

        // build superset
        // todo: buggy, if same type pulled then addExersiseToSuperSet returns false and loop stops adding to superset
        for (i in 1..numSuperSets){
            var newSuperSet = SuperSet(numExercisesPerSuperSet)
            var setHasRoom = true
            while (! newSuperSet.fullSet) {
                newSuperSet.add(getRandomExercise())
                //setHasRoom = newSuperSet.add(getRandomExercise())
            }
            wap.addSuperSet(newSuperSet)
        }

        return wap
    }
}
