package aly.riv.workoutbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@ConfigurationPropertiesScan
class WorkoutbotApplication

fun main(args: Array<String>) {
    runApplication<WorkoutbotApplication>(*args)
}

@RestController
class ArnoldController(
    val exerciseManager: ExerciseManager
){
    @GetMapping("/getpumped", produces = ["application/json"])
    fun assignWorkout() : WorkoutPlan { //todo needs to return mvcview
        return exerciseManager.generateWorkoutPlan(numSuperSets = 4, numExercisesPerSuperSet = 3)
    }
}
